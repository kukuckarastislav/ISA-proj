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

export default {
  name: 'BoatView',
  components: {
    CarouselView
  },
  data: function(){
    return {
        boatName: "",
        boat: {},
    }
  },
  mounted: function(){
    this.boatName = decodeURI(window.location.pathname.split('/')[2]);
    this.loadData();

  },
  methods: {
    loadData: function(){
       axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
        axios.get('http://localhost:8180/api/boats/byowner/'+encodeURIComponent(this.boatName)).then(resp => {
            this.boat = resp.data;
            console.log(resp.data);
            console.log("Ucitano je "+this.boat.address.latitude)


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
