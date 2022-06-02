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
  name: 'CottageView',
  components: {
    CarouselView
  },
  data: function(){
    return {
        cottageName: "",
        cottage: {},
    }
  },
  mounted: function(){
    this.cottageName = decodeURI(window.location.pathname.split('/')[2]);
    this.loadData();
  },
  methods: {
    loadData: function(){
       axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
        axios.get('http://localhost:8180/api/cottages/byowner/'+encodeURIComponent(this.cottageName)).then(resp => {
            this.cottage = resp.data;
            console.log(resp.data);





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
