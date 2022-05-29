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
          ]
        }
    }
  },
  mounted: function(){
    this.calendarOptions.events.push({
              title: 'Drugi',
              start: '2022-05-29T10:00:00',
              end: '2022-05-30T16:00:00',
              display: 'auto',
              backgroundColor: "rgb(0,0,200)",
              borderColor: "rgb(255,0,255)",
              description: "HAHAH",
              editable: false,
              overlap: false,
            })
  },
  methods: {
    loadData: function(){
       axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
        axios.get('http://localhost:8180/api/instructor/calendar/').then(resp => {
            console.log(resp.data);
        });
    },
  }
}
</script>


<style scoped>



</style>
