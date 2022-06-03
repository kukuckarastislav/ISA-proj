<template>
    <div style="height: 60px;"></div>
    <div class="container">
        <div class="row">
            <div class="col-sm-6">

            <CarouselView v-bind:server="true" v-bind:images="adventure.images"></CarouselView>
            

            </div>
            <div v-if="adventure.name != undefined" class="col-sm-6">

                <h1 class="text-start">{{adventure.name}}</h1> <br>

                <h3 class="text-start stars">
                  <span v-for="index in Math.round(adventure.averageGrade)" :key="index">&#9733;</span> 
                  <span v-for="index in Math.round(5-adventure.averageGrade)" :key="index">&#9734;</span> 
                  {{adventure.averageGrade}}
                </h3>
                
                <h5 class="text-start">Description: {{adventure.description}} </h5>
                <br>
                
                <h5 class="text-start">Behaviour: {{adventure.behaviourRules}}</h5>
                <h5 class="text-start">Max number of people: {{adventure.maxNumberOfPeople}}</h5>
                
                <h5 class="text-start">Reservation Cancellation Conditions: <b class="stars">{{adventure.reservationCancellationConditions}}</b></h5>
                
                <h5 class="text-start">Additional Equipments: </h5>
                <div v-if="adventure.additionalEquipments.length > 0" class="card">
                  <div class="card-body">
                      <span v-for="addEq in adventure.additionalEquipments" :key="addEq" class="badge bg-primary m-1">{{addEq.name}}</span> 
                  </div>
                </div>

                <br>
                <div class="row">
                  <div class="col-sm-3">
                      <div v-if="adventure.price != undefined" class="card">
                        <div class="card-body">
                          <div class="row">
                            <div class="col cenaCss">
                              <h2><b>${{adventure.price.price}}</b></h2>
                              <h6><b>{{adventure.price.priceType}}</b></h6>
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
                            <div class="col-sm-10">
                                <h5 class="card-title text-start">Instructor {{adventure.instructorFirstName}} {{adventure.instructorLastName}}</h5>
                                <h6 class="card-title text-start">{{adventure.biography}}
                                    
                                </h6>
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
              <div v-for="priceItem in adventure.pricelist" :key="priceItem" class="priceItem">
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
                      <div class="col-sm-10">
                          <div style="height:100%;" id = "map" ></div>
                      </div>
                      <div v-if="adventure.address != undefined" class="col-sm-2">
                          <h6 class="card-title text-start">Country: {{adventure.address.country}}</h6>
                          <h6 class="card-title text-start">City: {{adventure.address.city}}</h6>
                          <h6 class="card-title text-start">Street: {{adventure.address.street}} {{adventure.address.number}}</h6>
                      </div>
                  </div>
              </div>         
            </div>
          </div>
        </div>

      <div class="row" style="margin-top: 40px;">
        <div class="col">
          <h1>Fast Reservations</h1>
          <button v-on:click="openFastReservationForm()" v-if="!fastReservationForm.visible" class="btn btn-primary">Create New Fast Reservation</button>
          <div v-if="fastReservationForm.visible" class="card">
            <div class="card-body">
              <div class="row">
                <div class="col-4">
                  <Datepicker v-model="fastReservationForm.date" range></Datepicker> <br> <br> <br>
                  <div class="row justify-content-between">
                    <div class="col">
                        <input type="text" v-model="fastReservationForm.address.country" class="form-control" placeholder="Country">
                        <br>
                        <input type="text" v-model="fastReservationForm.address.city" class="form-control" placeholder="City">
                        <br>
                        <input type="text" v-model="fastReservationForm.address.street" class="form-control" placeholder="street">
                    </div>
                    <div class="col">
                        <input type="text" v-model="fastReservationForm.address.number" class="form-control" placeholder="number">
                        <br>
                        <input type="number" v-model="fastReservationForm.address.latitude" class="form-control" placeholder="latitude">
                        <br>
                        <input type="number" v-model="fastReservationForm.address.longitude" class="form-control" placeholder="longitude">
                    </div>
                  </div>
                </div>
                <div class="col-8">
                    <div class="list-group text-start overflow-auto card" style="height: 210px">
                    <label v-for="itPrice in adventure.pricelist" :key="itPrice" class="list-group-item">
                        <div class="row">
                            <div class="col-sm-1">
                                <input class="form-check-input me-1" type="checkbox" value="" v-on:click="addItemPriceFastReservation(itPrice)">
                            </div>
                            <div class="col-sm-3">{{itPrice.name}}</div>
                            <div class="col-sm-6">{{itPrice.description}}</div>
                            <div class="col-sm-2">{{itPrice.price}}</div>
                        </div>
                    </label>
                    </div>
                    <br>
                    <div class="row">
                      <div class="col-2"></div>
                      <div class="col-2 text-end">Max number of People</div>
                      <div class="col-2">
                        <input type="number" v-model="fastReservationForm.maxNumberOfPeople" class="form-control">
                      </div>
                      <div class="col-2 text-end">Price</div>
                      <div class="col-2">
                        <input type="number" v-model="fastReservationForm.price" class="form-control">
                      </div>
                    </div>
                </div>
                <div class="row" style="margin-top: 30px;">
                <hr>
                <div class="col">
                  <button class="btn btn-danger m-1" v-on:click="fastReservationForm.visible = false">Cancel</button>
                  <button class="btn btn-primary m-1" v-on:click="sendNewFastReservation()">Create</button>
                </div>
              </div>
              </div>
            </div>
          </div>
        </div>
      </div>




        <br><br><br>
        <div class="row">
          <div class="col">
            <h1>Calendar</h1>
            <FullCalendar :options="calendarOptions" />
            <br>
          </div>
        </div>

        <div class="row">
          <div class="col-4"></div>
          <div class="col-4"></div>
          <div class="col-4">
              <CalendarLegend/>
              <br>
          </div>
        </div>


        <!-- SELECTED Reservation -->
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
import CarouselView from '@/components/CarouselView.vue'
import ReservationViewComponent from '../instructor/ReservationViewComponent.vue'

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

import CalendarLegend from '../components/CalendarLegened.vue'
import Datepicker from 'vue3-date-time-picker';
import 'vue3-date-time-picker/dist/main.css'

export default {
  name: 'AdventureView',
  components: {
    CarouselView,
    FullCalendar,
    ReservationViewComponent,
    CalendarLegend,
    Datepicker
  },
  data: function(){
    return {
        adventureName: "",
        adventure: {},

        terms: [],

        selectedReservation: null,
        showSelectedReservation: false,

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
    this.adventureName = decodeURI(window.location.pathname.split('/')[2]);
    this.loadData(true);
    this.calendarOptions.eventClick = this.eventClickCalendar;
  },
  methods: {
    loadData: function(reloadMap){
       axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
        axios.get('http://localhost:8180/api/adventure/byinstructor/'+encodeURIComponent(this.adventureName)).then(resp => {
            this.adventure = resp.data;
            this.loadCalendarData();
            console.log("podaci avanture");
            console.log(this.adventure);
            console.log("*****************");

            if(reloadMap)
              this.loadMap();

        });
    },
    loadMap: function(){
        var iconFeature = new Feature({
					geometry: new Point(fromLonLat([this.adventure.address.longitude, this.adventure.address.latitude])),
					name: 'adventure',
				  });

           var lat = this.adventure.address.latitude;
           var lng = this.adventure.address.longitude;
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
    },
    loadCalendarData: function(){
      axios.get('http://localhost:8180/api/instructorterms/adventure/'+this.adventure.id.toString()).then(resp => {
            console.log(resp.data);
            this.terms = resp.data;
            this.calendarOptions.events = [];
            for (let e of this.terms) {
                console.log(e);
                this.calendarOptions.events.push(this.eventTransform(e));
            }
        });
    },
    setImg: function(image){
      return 'http://localhost:8180/api/entityImage/'+image.path;
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
    },
    openFastReservationForm: function(){
      this.fastReservationForm.visible = true;
      this.fastReservationForm.address = this.adventure.address
      this.fastReservationForm.maxNumberOfPeople = this.adventure.maxNumberOfPeople
      this.fastReservationForm.price = this.adventure.price.price
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
      axios.post('http://localhost:8180/api/instructorterms/fast-reservation', newFastReservationDTO).then(
        (resp) => {
          console.log(resp.data)
          if(resp.data.startsWith("error")){
            alert(resp.data)
          }else{
            this.loadData(false);
          }
        }, 
        (err)=>{
          alert(err)
      });
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
