<template>
    <div style="height: 60px;"></div>
    <div class="container">
        <div class="row">
            <div class="col-sm-6">

            <CarouselView v-bind:server="true" v-bind:images="boat.images"></CarouselView>
            

            </div>
            <div style="width: 300px; height: 300px;">
            </div>
            <div v-if="boat.name != undefined" class="col-sm-6">

                <h1 class="text-start">{{boat.name}}</h1> <br>
                <button class="card" v-on:click="editData(boat)">Edit boat data</button>
                <h3 class="text-start stars">
                  <span v-for="index in Math.round(boat.averageGrade)" :key="index">&#9733;</span> 
                  <span v-for="index in Math.round(5-boat.averageGrade)" :key="index">&#9734;</span> 
                  {{boat.averageGrade}}
                </h3>
                
                <h5 class="text-start">Description: {{boat.promotionalDescription}} </h5>
                <br>
                
                <h5 class="text-start">Behaviour: {{boat.behaviourRules}}</h5>

                <h5 class="text-start">Type:  {{boat.type}}</h5>
                <h5 class="text-start">EngineNumber:  {{boat.engineNumber}}</h5>
                <h5 class="text-start">Length:  {{boat.length}}</h5>
                <h5 class="text-start">EnginePower:  {{boat.enginePower}}</h5>
                <h5 class="text-start">MaxSpeed:  {{boat.maxSpeed}}</h5>

                <h5 class="text-start">Max number of people: {{boat.capacity}}</h5>
                
                <h5 class="text-start">Reservation Cancellation Conditions: <b class="stars">{{boat.reservationCancellationConditions}}</b></h5>
                
                

                <br>
                <div class="row">
                  <div class="col-sm-3">
                      <div v-if="boat.price != undefined" class="card">
                        <div class="card-body">
                          <div class="row">
                            <div class="col cenaCss">
                              <h2><b>${{boat.price.price}}</b></h2>
                              <h6><b>{{boat.price.priceType}}</b></h6>
                            </div>
                          </div>
                        </div>
                      </div>
                  </div>
                  <div class="col-sm-9">
                       <div class="card">
                        <div class="card-body">
                          <div class="row">
                            <div class="col-sm-2">
                              <img src="../assets/abstract-user.png" class="img-fluid rounded-start" >
                            </div>
                            
                        </div>
                      </div>
                    </div>
                  </div>

                </div>
            </div>
        </div>
        
        <!-- PriceList -->
        <br>
        <div class="row">
          <div class="col">
            <h2 class="text-start">Price list</h2>
            <div class="row row-cols-auto">
              <div v-for="priceItem in boat.additionalServices" :key="priceItem" class="priceItem">
                <h2 style="font-weight: bold">{{priceItem.name}}</h2>
                <h5>{{priceItem.description}}</h5>
                <h2 style="font-weight: bold">${{priceItem.price}}</h2>
              </div>
            </div>
          </div>
        </div>

        

        <br> <br> <br>
        <div class="row">
          <div class="col">
            <div class="card">
              <div class="card-body">
                  <div style="height: 400px;" class="row">
                      <div class="col-sm-9">
                          <div style="background-color: gray; height:100%;" id = "map"> Boat location</div>
                      </div>
                      <div v-if="boat.address != undefined" class="col-sm-3">
                          <h6 class="card-title text-start">Country: {{boat.address.country}}</h6>
                          <h6 class="card-title text-start">City: {{boat.address.city}}</h6>
                          <h6 class="card-title text-start">Street: {{boat.address.street}} {{boat.address.number}}</h6>
                      </div>
                  </div>
              </div>         
            </div>
          </div>
        </div>

      <div class="row" style="margin-top:30px;">
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

    
    </div>

</template>

<script>
import axios from "axios";
import CarouselView from '@/components/CarouselView.vue'
import { Map, View,Feature } from 'ol';
import { Tile as TileLayer,Vector as VectorLayer } from 'ol/layer';
import {OSM, Vector as VectorSource} from 'ol/source';
import {fromLonLat,transform} from 'ol/proj';
import {Geometry} from 'ol/geom'
import {Point} from 'ol/geom';
import {Circle, Fill, Style, Icon} from 'ol/style';
import {defaults} from 'ol/control';


// FULL CALNEDAR
import '@fullcalendar/core/vdom' // solves problem with Vite
import FullCalendar from '@fullcalendar/vue3'
import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid';
import listPlugin from '@fullcalendar/list';
import interactionPlugin, { Draggable } from '@fullcalendar/interaction'

import Datepicker from 'vue3-date-time-picker';
import 'vue3-date-time-picker/dist/main.css'

export default {
  name: 'BoatView',
  components: {
    CarouselView,
    FullCalendar,
    Datepicker,
  },
  data: function(){
    return {
        boatName: "",
        boat: {},

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
            
          ]
        },
        createNewTerm:{
          date: [],
          formVisible: false,
          type: 'AVAILABILE',
          msg: '',
          valid: true,
        },

    }
  },
  mounted: function(){
    this.boatName = decodeURI(window.location.pathname.split('/')[2]);
    this.calendarOptions.select = this.selectInCalendar;
    this.loadData();

  },
  methods: {
    loadData: function(){
       axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
        axios.get('http://localhost:8180/api/boats/byowner/'+encodeURIComponent(this.boatName)).then(resp => {
            this.boat = resp.data;
            console.log(resp.data);
            console.log("Ucitano je "+this.boat.address.latitude)

            this.loadDataCalendar();

            var iconFeature = new Feature({
					geometry: new Point(fromLonLat([this.boat.address.longitude, this.boat.address.latitude])),
					name: 'boat',
				  });


                    var lat = this.boat.address.latitude;
                    var lng = this.boat.address.longitude;
            var iconGeometry = new Point(transform([lng, lat], 'EPSG:4326', 'EPSG:3857'));
            var iconFeature = new Feature({
              geometry: iconGeometry,
              name: 'The icon',
              population: 4000,
              rainfall: 500
            });

            var iconStyle = new Style({
              image: new Icon( /** @type {olx.style.IconOptions} */ ({
                anchor: [0.5, 46],
                anchorXUnits: 'fraction',
                anchorYUnits: 'pixels',
                src: 'https://openlayers.org/en/v4.6.5/examples/data/icon.png'
              }))
            });

            iconFeature.setStyle(iconStyle);

            var vectorSource = new VectorSource({
              features: [iconFeature]
            });

            var vectorLayer = new VectorLayer({
              source: vectorSource
            });

            var rasterLayer = new TileLayer({
              source: new OSM()
            });

            this.map = new Map({
              layers: [rasterLayer, vectorLayer],
              target: 'map',
              controls: defaults({
                attributionOptions: {
                  collapsible: false,
                },
                attribution: false
              }),
              view: new View({
                center: fromLonLat([lng, lat]),
                zoom: 7
              })
            });


                    });
    },
    loadDataCalendar: function(){
       axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
        axios.get('http://localhost:8180/api/boat-term/'+this.boat.id.toString()).then(resp => {
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
        axios.post('http://localhost:8180/api/boat-term', {
          "idEntity" : this.boat.id,
          "termAvailability":this.createNewTerm.type,
          "startTime":startTimeForBackend,
          "endTime":endTimeForBackend,
        }).then(
          (resp) => {
            this.loadDataCalendar();
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
    setImg: function(image){
      return 'http://localhost:8180/api/entityImage/'+image.path;
    },
    editData: function(boat){
        this.$router.push({ path: '/editMyBoat/'+encodeURIComponent(boat.name)});
    },
  }
}
</script>


<style scoped>

.priceItem{
  font-weight: bold;
  padding: 20px 0px 0px 0px;
  margin: 15px;
  width: 176px;
  height: 180px;

  background: #E7E7E7;
  box-shadow: 0px 16px 16px rgba(0, 0, 0, 0.25);
  border-radius: 28px;
}

.stars{
  color: coral;
}

.cenaCss{
  color: coral;
}

</style>
