<template>
    <div style="height: 60px;"></div>
    <div class="container">
        <div class="row">
            <div class="col-sm-6">

            <CarouselView v-bind:server="true" v-bind:images="cottage.images"></CarouselView>
            

            </div>
            <div v-if="cottage.name != undefined" class="col-sm-6">

                <h1 class="text-start">{{cottage.name}}</h1> <br>
                <button class="card" v-on:click="editData(cottage)">Edit cottage data</button>
                <h3 class="text-start stars">
                  <span v-for="index in Math.round(cottage.averageGrade)" :key="index">&#9733;</span> 
                  <span v-for="index in Math.round(5-cottage.averageGrade)" :key="index">&#9734;</span> 
                  {{cottage.averageGrade}}
                </h3>
                
                <h5 class="text-start">Description: {{cottage.description}} </h5>
                <br>
                
                <h5 class="text-start">Behaviour: {{cottage.behaviourRules}}</h5>
                <h5 class="text-start">Max number of people: {{cottage.maxNumberOfPeople}}</h5>
                
                <h5 class="text-start">Reservation Cancellation Conditions: <b class="stars">{{cottage.reservationCancellationConditions}}</b></h5>
                
                

                <br>
                <div class="row">
                  <div class="col-sm-3">
                      <div v-if="cottage.price != undefined" class="card">
                        <div class="card-body">
                          <div class="row">
                            <div class="col cenaCss">
                              <h2><b>${{cottage.price.price}}</b></h2>
                              <h6><b>{{cottage.price.priceType}}</b></h6>
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
              <div v-for="priceItem in cottage.pricelist" :key="priceItem" class="priceItem">
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
                          <div style="background-color: gray; height:100%;" id = "map"> Cottage location </div>
                      </div>
                      <div v-if="cottage.address != undefined" class="col-sm-3">
                          <h6 class="card-title text-start">Country: {{cottage.address.country}}</h6>
                          <h6 class="card-title text-start">City: {{cottage.address.city}}</h6>
                          <h6 class="card-title text-start">Street: {{cottage.address.street}} {{cottage.address.number}}</h6>
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
          <div class="col-4"></div>
          <div class="col-4">
              <CalendarLegend/>
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

import CalendarLegend from '../components/CalendarLegened.vue'

export default {
  name: 'CottageView',
  components: {
    CarouselView,
    FullCalendar,
    Datepicker,
    CalendarLegend
  },
  data: function(){
    return {
        cottageName: "",
        cottage: {},

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
        fastReservationForm: {
          visible: false,
          date: [],
          itemPrices: [],
          address: {
                country: '',
                city: '',
                street: '',
                number: '',
                latitude: 0,
                longitude: 0
          },
          maxNumberOfPeople: 0,
          price: 0,
        },



    }
  },
  mounted: function(){
    this.cottageName = decodeURI(window.location.pathname.split('/')[2]);
    this.calendarOptions.select = this.selectInCalendar;
    this.loadData();
  },
  methods: {
    loadData: function(){
       axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
        axios.get('http://localhost:8180/api/cottages/byowner/'+encodeURIComponent(this.cottageName)).then(resp => {
            this.cottage = resp.data;
            console.log(resp.data);
            this.loadDataCalendar();

             var iconFeature = new Feature({
				    	geometry: new Point(fromLonLat([this.cottage.address.longitude, this.cottage.address.latitude])),
				    	name: 'cottage',
				         });

              var lat = this.cottage.address.latitude;
              var lng = this.cottage.address.longitude;
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
    setImg: function(image){
      return 'http://localhost:8180/'+image.path;
    },
    editData: function(cottage){
        this.$router.push({ path: '/editMyCottage/'+encodeURIComponent(this.cottage.name)});
    },
    loadDataCalendar: function(){
       axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
        axios.get('http://localhost:8180/api/cottage-term/'+this.cottage.id.toString()).then(resp => {
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
    eventClickCalendar: function(info){
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
      axios.get('http://localhost:8180/api/cottage-term/reservation/'+event.isa_termType+'/'+event.isa_idTerm).then(
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
    },
    openFastReservationForm: function(){
      this.fastReservationForm.visible = true;
      this.fastReservationForm.address = this.cottage.address
      this.fastReservationForm.maxNumberOfPeople = this.cottage.capacity
      this.fastReservationForm.price = this.cottage.price.price
      this.fastReservationForm.itemPrices = []
    },
    addItemPriceFastReservation: function(itPrice){
       for(let i = 0; i < this.fastReservationForm.itemPrices.length; i++) {
            if(this.fastReservationForm.itemPrices[i].id == itPrice.id){
                //remove
                this.fastReservationForm.itemPrices.splice(i, 1);
                console.log("removed element from itemPrices with id="+itPrice.id);
                return;
            }
        }
        this.fastReservationForm.itemPrices.push(itPrice)
    },
    sendNewFastReservation: function(){

      const startTimeForBackend = new Date(Date.UTC(this.fastReservationForm.date[0].getFullYear(), this.fastReservationForm.date[0].getMonth(), this.fastReservationForm.date[0].getDate(), this.fastReservationForm.date[0].getHours(), this.fastReservationForm.date[0].getMinutes()))
      const endTimeForBackend = new Date(Date.UTC(this.fastReservationForm.date[1].getFullYear(), this.fastReservationForm.date[1].getMonth(), this.fastReservationForm.date[1].getDate(), this.fastReservationForm.date[1].getHours(), this.fastReservationForm.date[1].getMinutes()))


      let newFastReservationDTO = {
          "idAdventure" : this.adventure.id,
          "startTime" : startTimeForBackend,
          "endTime" : endTimeForBackend,
          "maxNumberOfPeople" : this.fastReservationForm.maxNumberOfPeople,
          "address" : this.fastReservationForm.address,
          "itemPrices" : this.fastReservationForm.itemPrices,
          "price" : this.fastReservationForm.price
      }


      axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
      axios.post('http://localhost:8180/api/boat-terms/fast-reservation', newFastReservationDTO).then(
        (resp) => {
          console.log(resp.data)
          if(resp.data.startsWith("error")){
            alert(resp.data)
          }else{
            this.openFastReservationForm();
            this.fastReservationForm.visible = false;
            this.loadData(false);
          }
        }, 
        (err)=>{
          alert(err)
      });
    },
    UpdateAdventurePage: function () {
        this.$router.push({ path: '/cottage-update/'+encodeURIComponent(this.adventure.name)});
    },
    convertDate: function(date){
        return new Date(date).toLocaleString();
    },
    showRevisions: function (x) {
      if (x) {
        this.revisions = this.adventure.instructorRevisions
      } else {
        this.revisions = this.cottage.cottageRevisions
      }
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
        axios.post('http://localhost:8180/api/cottage-term', {
          "idEntity" : this.cottage.id,
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
