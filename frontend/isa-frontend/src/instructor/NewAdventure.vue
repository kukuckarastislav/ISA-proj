<template>
    <div style="height: 60px;"></div>
    <div class="container">
        <div class="row">
            <div class="col-sm-6">
            <!--
            <CarouselView v-bind:server="false" v-bind:images="imagesForFront"></CarouselView>
            -->

            <input class="form-control" type="file" @change="onFileChange">
            <br>
            <div class="overflow-auto card pb-3" style="height: 660px;">
                <div v-for="(image, index) in imagesForFront" :key="(image, index)">
                    <button v-on:click="removeImage(image, index)" class="btn btn-danger rounded-circle removeImageBtn end-0 m-4">X</button>
                    <img class="ms-3 me-3 mt-3 newImage img-fluid" v-bind:src="image.path" >
                </div>
            </div>


            

            </div>
            <div class="col-sm-6">

                <h1 class="text-start">Name:  </h1>
                <input v-model="adventure.name" class="form-control" type="text">
                <br>
                
                <h5 class="text-start">Description: </h5>
                <textarea class="form-control" v-model="adventure.description" rows="3"></textarea>
                <br>

                
                <h5 class="text-start">Behaviour: </h5>
                <textarea class="form-control" v-model="adventure.behaviourRules" rows="2"></textarea> <br>

                <div class="row">
                    <div class="col-sm-7"><h5 class="text-start">Max number of people: </h5></div>
                    <div class="col-sm-5 d-flex"><input class="form-control" v-model="adventure.maxNumberOfPeople" style="width: 60px;" type="number" min=0></div>
                </div>

                <div class="row">
                    <div class="col-sm-7"><h5 class="text-start">Reservation Cancellation Conditions: </h5></div>
                    <div class="col-sm-5 d-flex">
                        <select v-model="adventure.reservationCancellationConditions" class="form-select cenaCss">
                            <option value="FREE" selected>FREE</option>
                            <option value="FIVE_PERCENT">FIVE_PERCENT</option>
                            <option value="TEN_PERCENT">TEN_PERCENT</option>
                            <option value="FIFTEEN_PERCENT">FIFTEEN_PERCENT</option>
                            <option value="TWENTY_PERCENT">TWENTY_PERCENT</option>
                         </select>
                    </div>
                </div>
            

                <br>    
                <h5 class="text-start ">Additional Equipments: </h5>
    
                <div class="d-flex justify-content-start">
                    <input type="text" v-model="newAdditionalEq" style="width: 50%;" class="form-control m-2"> 
                    <button v-on:click="addNewAdditionalEq()" class="btn btn-primary m-2">Add</button>
                </div> 
                
                <div class="list-group text-start overflow-auto card m-2" style="height: 150px; width: 50%;">
                    <label v-for="additionalEq in allAdditionalEquipment" :key="additionalEq" class="list-group-item">
                        <input class="form-check-input me-1" type="checkbox" value="" v-on:click="clickAdditionalEq(additionalEq)">
                        {{additionalEq.name}}
                    </label>
                </div> <br>
                <div v-if="adventure.additionalEquipments.length > 0" class="card">
                  <div class="card-body">
                      <span v-for="addEq in adventure.additionalEquipments" :key="addEq" class="badge bg-primary m-1">{{addEq.name}}</span> 
                  </div>
                </div>

                <br>
                <div class="row">
                <div class="col-sm-4"></div>
                  <div class="col-sm-4">
                      <div class="card">
                        <div class="card-body">
                          <div class="row">
                            <div class="col cenaCss">
                              <h2><b>$<input class="cenaCss" style="width: 120px;" type="number" min=0 v-model="adventure.price.price"></b></h2>
                              <h6><b>
                              <select v-model="adventure.price.priceType" class="form-select cenaCss" aria-label="Default select example" >
                                <option value="PER_HOUR" selected>PER HOUR</option>
                                <option value="PER_DAY">PER DAY</option>
                              </select>
                              </b></h6>
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
            
            <div class="row">
                <div class="col-sm-7">
                    <div class="list-group text-start overflow-auto card" style="height: 200px">
                    <label v-for="itPrice in allItemPrices" :key="itPrice" class="list-group-item">
                        <div class="row">
                            <div class="col-sm-1">
                                <input class="form-check-input me-1" type="checkbox" value="" v-on:click="clickItemPrice(itPrice)">
                            </div>
                            <div class="col-sm-3">{{itPrice.name}}</div>
                            <div class="col-sm-6">{{itPrice.description}}</div>
                            <div class="col-sm-2">{{itPrice.price}}</div>
                        </div>
                    </label>
                    </div>
                </div>
                <div class="col-sm-5">
                    <div v-if="addNewItemPriceVisible" class="row">
                        <div class="priceItem col-sm-6">
                            <h2 style="font-weight: bold">{{newPriceItem.name}}</h2>
                            <h5>{{newPriceItem.description}}</h5>
                            <h2 style="font-weight: bold">${{newPriceItem.price}}</h2>
                        </div>
                        <div class="col-sm-6">
                            <br> 
                            <input v-model="newPriceItem.name" type="text" class="form-control"> <br>
                            <input v-model="newPriceItem.description" type="text" class="form-control"> <br>
                            <input v-model="newPriceItem.price" type="number" class="form-control" min=0> <br>
                            <button class="btn btn-danger m-2" v-on:click="cancelAddNewItemPrice()">Cancel</button>
                            <button class="btn btn-primary m-2">Add</button>
                        </div>
                    </div>
                    <div v-else>
                        <button class="d-flex btn btn-primary" v-on:click="addNewItemPriceVisible=true">Add New Item Price</button>
                    </div>
                </div>
            </div> <br>
            <div class="row row-cols-auto">
              <div v-for="priceItem in adventure.pricelist" :key="priceItem" class="priceItem">
                <h2 style="font-weight: bold">{{priceItem.name}}</h2>
                <h5>{{priceItem.description}}</h5>
                <h2 style="font-weight: bold">${{priceItem.price}}</h2>
              </div>
            </div>
          </div>
        </div>

        
        <!-- MAPA -->
        <br> <br> <br>
        <div class="row">
          <div class="col">
            <div class="card">
              <div class="card-body">
                  <div style="height: 400px;" class="row">
                      <div class="col-sm-9">
                          <div style="background-color: gray; height:100%;" id = "map"> Adventure location</div>
                      </div>
                      <div v-if="adventure.address != undefined" class="col-sm-3">
                          <h6 class="card-title text-start">Country: <input type="text" v-model="adventure.address.country"></h6>
                          <h6 class="card-title text-start">City: <input type="text" v-model="adventure.address.city"></h6>
                          <h6 class="card-title text-start">Street: <input type="text" v-model="adventure.address.street"></h6>
                         <h6 class="card-title text-start">Number: <input type="text" v-model="adventure.address.number"></h6>

                      </div>
                  </div>
              </div>         
            </div>
          </div>
        </div>

        <br> <br>
        <button v-on:click="createNewAdventure()" class="btn btn-primary m-5 p-3">Create New Adventure</button>

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


export default {
  name: 'NewAdventure',
  components: {
    CarouselView
  },
  data: function(){
    return {
        adventure: {
            name: "",
            address: {
                country: "",
                city: "",
                street: "",
                number: "",
                latitude: 14.0,
                longitude: 24.0
            },
            description: "",
            imagesForBackend: [],
            maxNumberOfPeople: 0,
            behaviourRules: "",
            pricelist: [],
            reservationCancellationConditions: "FREE",
            price: {
                price: 0,
                priceType: "PER_HOUR"
            },
            additionalEquipments: []
        },

        allAdditionalEquipment: [],
        newAdditionalEq: "",
        allItemPrices: [],

        addNewItemPriceVisible: false,
        newPriceItem: {
            id: -1,
            price: 0,
            name: "name",
            description: "description"
        },

        newLon : 0.0,
        newLat : 0.0,

        imagesForFront: [],
        imagesForBackend: [],
        imageCount: 0,
        map: {}
    }
  },
  mounted: function(){
      this.loadData();
      console.log(this.imagesForFront);


            var lat = this.adventure.address.latitude;
            var lng = this.adventure.address.longitude;
            var iconGeometry = new Point(transform([lng, lat], 'EPSG:4326', 'EPSG:3857'));
            var iconFeature = new Feature({
            geometry: iconGeometry,
            name: 'adventure',
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
            zoom: 5
        })
        });

        this.map.on('singleclick', function (evt) {
                //  alert("nesto: "+transform(evt.coordinate, 'EPSG:3857', 'EPSG:4326')) 
                var transformed_xy = transform(evt.coordinate, 'EPSG:3857', 'EPSG:4326');
                this.newLon = transformed_xy[0];//neradi 
                //  this.adventure.address.latitude = transformed_xy[0];
                //  this.adventure.address.longitude = transformed_xy[1];
                this.newLat = transformed_xy[1];
                iconGeometry.setCoordinates(evt.coordinate);
            });


  },
  methods: {
    loadData: function(){
        axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
        axios.get('http://localhost:8180/api/additionalequipment/getadditionalequipment').then(resp => {
            this.allAdditionalEquipment = resp.data;
            console.log(resp.data);
        });
        axios.get('http://localhost:8180/api/itemprice/getitemprices').then(resp => {
            this.allItemPrices = resp.data;
            console.log(resp.data);
        });
    },
    clickAdditionalEq: function(additionalEq){
        for (var i = 0; i < this.adventure.additionalEquipments.length; i++) {
            if(this.adventure.additionalEquipments[i].id == additionalEq.id){
                //remove
                this.adventure.additionalEquipments.splice(i, 1);
                console.log("removed element from additionEq with id="+additionalEq.id);
               
                return;
            }
        }
        //add
        this.adventure.additionalEquipments.push(additionalEq);
        console.log("added element from additionEq with id="+additionalEq.id);
     
    },
    addNewAdditionalEq: function(){
        // poslati na bekend novi add equipment
    },
    clickItemPrice: function(itemPrice){
        for (var i = 0; i < this.adventure.pricelist.length; i++) {
            if(this.adventure.pricelist[i].id == itemPrice.id){
                //remove
                this.adventure.pricelist.splice(i, 1);
                console.log("removed element from itemPrice with id="+itemPrice.id);
                return;
            }
        }
        //add
        this.adventure.pricelist.push(itemPrice);
        console.log("added element from itemPrice with id="+itemPrice.id);
    },
    cancelAddNewItemPrice: function(){
        this.newPriceItem.price = 0;
        this.newPriceItem.name = "Name";
        this.newPriceItem.description = "description";
        this.addNewItemPriceVisible = false;
    },
    onFileChange: function(e){
        console.log('fajl select');
        const file = e.target.files[0];
        this.createBase64Image(file);
        this.imageCount++;
        this.imagesForFront.push({id: this.imageCount, name: ""+this.imageCount, path: URL.createObjectURL(file)});
        console.log('Nice');
        console.log(this.imagesForFront);
    },
    createBase64Image(file){
        const reader= new FileReader();
        reader.onload = (e) =>{
            this.imagesForBackend.push(e.target.result);
        }
        reader.readAsDataURL(file);
        console.log('Images for beckend');
        console.log(this.imagesForBackend);
    },
    removeImage: function(image, index){
        this.imageCount--;
        this.imagesForFront.splice(index,1);
        this.imagesForBackend.splice(index,1);
    },
    createNewAdventure: function(){
        console.log('NEW ADVENTURE')
        this.adventure.imagesForBackend = this.imagesForBackend;
        this.adventure.address.longitude = this.map.newLon;
        this.adventure.address.latitude = this.map.newLat;

        // console.log("Kreiranje avanture")
        // console.log(this.adventure)

        axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
        axios.put('http://localhost:8180/api/adventure/addnewadventure',this.adventure)
          .then(response => {
              console.log('Odgoovr');
              console.log(response.data);
          }).catch(err => {
              alert('DOSLO JE DO GRESKE')
          }); 


    }
  }
}
</script>


<style scoped>

.removeImageBtn{
    position: absolute;
}

.newImage {
    width: 580px;
   /* height: 200px; */
}

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
