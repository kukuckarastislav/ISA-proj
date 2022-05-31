<template>
    <div style="margin-top: 60px;"></div>
    <div class="container" style="margin-bottom: 30px;">

        <div class="row">
          <div class="col">
            <h1>Calendar</h1>
            <FullCalendar :options="calendarOptions" />
            <br>
          </div>
        </div>

        <div class="row">
          <div class="col-4">
            <div class="d-flex justify-content-start">
            <button v-if="!createNewTerm.formVisible && !showSelectedReservation" class="btn btn-primary mx-2" v-on:click="createNewTerm.formVisible=true">Add Term</button>
            </div>
          </div>
        </div>

        <div v-if="createNewTerm.formVisible" class="row" style="margin-top: 30px;">
          <div class="col-4">
            <div class="card">
              <div class="card-body">
                <h4 class="card-title">Create new Term</h4>
                <Datepicker v-on:click="showCreateNewTermForm()" v-model="createNewTerm.date" range></Datepicker>
                <select class="form-select" v-model="createNewTerm.type" style="margin-top: 30px;">
                  <option value="AVAILABILE">AVAILABILE</option>
                  <option value="UNAVAILABLE">UNAVAILABLE</option>
                </select> <br>

                <p class="card-text text-danger">{{createNewTerm.msg}}</p>
                <button class="btn btn-danger m-1" v-on:click="createNewTerm.formVisible=false">Cancel</button>
                <button :disabled="!createNewTerm.valid" class="btn btn-success m-1" v-on:click="addNewTerm()">Add</button>
              </div>
            </div>
          </div>
        </div>

        <div v-if="showSelectedReservation" class="row" style="margin-top:30px;">
        <div class="col-1"></div>
          <div class="col-10">
              <ReservationViewComponent v-bind:reservation="selectedReservation" />
              <button class="btn btn-primary m-2" v-on:click="closeSelectedReservation()">Close</button>
          </div>
          <div class="col-1"></div>
        </div>

    </div>
</template>

<script>
import axios from "axios";
import ReservationViewComponent from '../instructor/ReservationViewComponent.vue'

import Datepicker from 'vue3-date-time-picker';
import 'vue3-date-time-picker/dist/main.css'

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
    FullCalendar,
    Datepicker,
    ReservationViewComponent
  },
  data: function(){
    return {
        terms: [],

        selectedReservation: {},
        showSelectedReservation: false,

        createNewTerm:{
          date: [],
          formVisible: false,
          type: 'AVAILABILE',
          msg: '',
          valid: true,
        },

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
      this.calendarOptions.select = this.selectInCalendar;
      this.calendarOptions.eventClick = this.eventClickCalendar;
  },
  methods: {
    loadData: function(){
       axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
        axios.get('http://localhost:8180/api/instructorterms').then(resp => {
            console.log(resp.data);
            this.terms = resp.data;
            this.calendarOptions.events = [];
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
            if(e.isa_statusOfReservation === 'CANCELLED'){
                e.backgroundColor = "#35013d"
                e.borderColor = "#35013d" 
            }
            else if(e.isa_statusOfReservation === 'ACTIVE'){
                e.backgroundColor = "#a100ba"
                e.borderColor = "#a100ba" 
            }
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
    showCreateNewTermForm: function(){
      this.createNewTerm.msg = ''
      this.createNewTerm.formVisible = true;
      this.createNewTerm.valid = true;
    },
    selectInCalendar: function(selectedDate){
      if(this.showSelectedReservation) return;
      this.createNewTerm.msg = ''
      this.createNewTerm.valid = true;
      this.createNewTerm.date[0] = new Date(selectedDate.start)
      this.createNewTerm.date[1] = new Date(new Date(selectedDate.end) - 1000) // TODO: minuti59
      this.createNewTerm.formVisible = true;
      if(this.overlap()){
          this.createNewTerm.msg = 'Error, new term have overlap with other term'
          this.createNewTerm.valid = false;
      }
    },
    addNewTerm: function(){
        if(this.overlap()){
          this.createNewTerm.msg = 'Error, new term have overlap with other term'
          this.createNewTerm.valid = false;
          return;
        }

        const startTimeForBackend = new Date(Date.UTC(this.createNewTerm.date[0].getFullYear(), this.createNewTerm.date[0].getMonth(), this.createNewTerm.date[0].getDate(), this.createNewTerm.date[0].getHours(), this.createNewTerm.date[0].getMinutes()))
        const endTimeForBackend = new Date(Date.UTC(this.createNewTerm.date[1].getFullYear(), this.createNewTerm.date[1].getMonth(), this.createNewTerm.date[1].getDate(), this.createNewTerm.date[1].getHours(), this.createNewTerm.date[1].getMinutes()))

        axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
        axios.post('http://localhost:8180/api/instructorterms/term', {
          "termAvailability":this.createNewTerm.type,
          "startTime":startTimeForBackend,
          "endTime":endTimeForBackend,
        }).then(
          (resp) => {
            this.loadData();
          }, 
          (err)=>{
            alert(err)
        });
    },
    overlap: function(){
        for(const event of this.calendarOptions.events){
            if(!((new Date(event.end) < this.createNewTerm.date[0]) || (this.createNewTerm.date[1] < new Date(event.start)))){
              console.log('OVERLAP')
              return true;
            }
        }
        return false;
    },
    eventClickCalendar: function(info){
        if(this.createNewTerm.formVisible) return;
        
        let e = info.event._def.extendedProps
        if(e.isa_termType != 'TERM'){
          this.loadDataSelectedReservation(e)
        }
    },
    loadDataSelectedReservation: function(event){
      //alert('ID ' + event.isa_idTerm)
      this.selectedReservation = null;
      this.showSelectedReservation = false; 

      axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
      axios.get('http://localhost:8180/api/instructorterms/reservation/'+event.isa_termType+'/'+event.isa_idTerm).then(
        (resp) => {
          console.log(resp.data)
          this.selectedReservation = resp.data;
          this.showSelectedReservation = true; 
        }, 
        (err)=>{
          alert(err)
      });
    },
    closeSelectedReservation: function(){
       this.selectedReservation = null;
       this.showSelectedReservation = false; 
    }
  }
}
</script>


<style scoped>



</style>
