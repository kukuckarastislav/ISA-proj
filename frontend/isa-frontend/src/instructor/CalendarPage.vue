<template>
    <div style="height: 60px;"></div>
    <div class="container">

        <div class="row">
          <div class="col">
            <h1>Calendar</h1>
            <FullCalendar :options="calendarOptions" />
            <br><br><br>
          </div>
        </div>

    </div>
</template>

<script>
import axios from "axios";

// FULL CALNEDAR
import '@fullcalendar/core/vdom' // solves problem with Vite
import FullCalendar from '@fullcalendar/vue3'
import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid';
import listPlugin from '@fullcalendar/list';
import interactionPlugin, { Draggable } from '@fullcalendar/interaction'
import { Map, View,Feature } from 'ol';
import { Tile as TileLayer,Vector as VectorLayer } from 'ol/layer';
import {OSM, Vector as VectorSource} from 'ol/source';
import {fromLonLat,transform} from 'ol/proj';
import {Geometry} from 'ol/geom'
import {Point} from 'ol/geom';
import {Circle, Fill, Style, Icon} from 'ol/style';
import {defaults} from 'ol/control';



export default {
  name: 'InstructorCalendarPage',
  components: {
    FullCalendar
  },
  data: function(){
    return {
        terms: [],
        calendarOptions: {
          plugins: [ dayGridPlugin, listPlugin, timeGridPlugin, interactionPlugin ],
          initialView: 'dayGridMonth', 
          selectable: true,
          droppable: true,
          headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay'
          },
          events: [
              /*
            {
              title: 'Evo je prvi event',
              start: '2022-05-29T10:00:00',
              end: '2022-05-30T16:00:00',
              display: 'auto',
              backgroundColor: "rgb(255,0,0)",
              borderColor: "rgb(255,0,255)",
              description: "opis neki",
              editable: true,
              overlap: false,
            },
            */
          ]
        }
    }
  },
  mounted: function(){
      this.loadData();
  },
  methods: {
    loadData: function(){
       axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
        axios.get('http://localhost:8180/api/instructorterms').then(resp => {
            console.log(resp.data);
            this.terms = resp.data;
            for (let e of this.terms) {
                console.log(e);
                this.calendarOptions.events.push(this.eventTransform(e));
            }

        });
    },
    eventTransform: function(e){
        e.display = 'auto'
        if(e.isa_termType === 'TERM'){
            e.textColor = "black"
            if(e.isa_termAvailability === 'UNAVAILABLE'){
                e.backgroundColor = "#ffe3e3"
                e.borderColor = "#ffe3e3"
            }else if(e.isa_termAvailability === 'AVAILABILE'){
                e.backgroundColor = "#ecffe3"
                e.borderColor = "#ecffe3"    
            }
        }else if(e.isa_termType === 'RESERVATION'){
            e.backgroundColor = "#a100ba"
            e.borderColor = "#a100ba"
        }else if(e.isa_termType === 'FAST_RESERVATION'){
            if(e.isa_isTakenFastReservation){
                e.backgroundColor = "#0057ba"
                e.borderColor = "#0057ba"
            }else{
                e.backgroundColor = "#06ba00"
                e.borderColor = "#06ba00"
            }
        }
        e.description = "opis neki"
        e.editable = false
        e.overlap = false
        return e;
    },
  }
}
</script>


<style scoped>



</style>
