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
                <input v-model="newBoatName" class="form-control" type="text">
                <br>
                
                <h5 class="text-start">Description: </h5>
                <textarea class="form-control" v-model="boat.promotionalDescription" rows="3"></textarea>
                <br>

                
                <h5 class="text-start">Behaviour: </h5>
                <textarea class="form-control" v-model="boat.behaviourRules" rows="2"></textarea> <br>

                <h5 class="text-start">Type:  </h5>
                <input v-model="boat.type" class="form-control" type="text">
                <br>
                <h5 class="text-start">EngineNumber:  </h5>
                <input v-model="boat.engineNumber" class="form-control" type="text">
                <br>
                <h5 class="text-start">Length:  </h5>
                <input v-model="boat.length" class="form-control" type="text">
                <br>
                <h5 class="text-start">EnginePower:  </h5>
                <input v-model="boat.enginePower" class="form-control" type="text">
                <br>
                <h5 class="text-start">MaxSpeed:  </h5>
                <input v-model="boat.maxSpeed" class="form-control" type="text">
                <br>

                <div class="row">
                    <div class="col-sm-7"><h5 class="text-start">Max number of people: </h5></div>
                    <div class="col-sm-5 d-flex"><input class="form-control" v-model="boat.capacity" style="width: 60px;" type="number" min=0></div>
                </div>

                <div class="row">
                    <div class="col-sm-7"><h5 class="text-start">Reservation Cancellation Conditions: </h5></div>
                    <div class="col-sm-5 d-flex">
                        <select v-model="boat.reservationCancellationConditions" class="form-select cenaCss">
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
                        <input class="form-check-input me-1" type="checkbox" value="" v-model="additionalEq.selected" v-on:click="clickAdditionalEq(additionalEq)">
                        {{additionalEq.name}}
                    </label>
                </div> <br>
                <div v-if="boat.navigationalEquipment.length > 0" class="card">
                  <div class="card-body">
                      <span v-for="addEq in boat.navigationalEquipment" :key="addEq" class="badge bg-primary m-1">{{addEq.name}}</span> 
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
                              <h2><b>$<input class="cenaCss" style="width: 120px;" type="number" min=0 v-model="boat.price.price"></b></h2>
                              <h6><b>
                              <select v-model="boat.price.priceType" class="form-select cenaCss" aria-label="Default select example" >
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
                                <input v-model="itPrice.selected" class="form-check-input me-1" type="checkbox" value="" v-on:click="clickItemPrice(itPrice)"><!--<input class="form-check-input me-1" type="checkbox" value="" v-on:click="clickItemPrice(itPrice)">-->
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
                            <button class="btn btn-primary m-2" v-on:click="addNewItemPrice()">Add</button><!--<button class="btn btn-primary m-2">Add</button>-->
                        </div>
                    </div>
                    <div v-else>
                        <button class="d-flex btn btn-primary" v-on:click="addNewItemPriceVisible=true">Add New Item Price</button>
                    </div>
                </div>
            </div> <br>
            <div class="row row-cols-auto">
              <div v-for="priceItem in boat.additionalServices" :key="priceItem" class="priceItem">
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
                          <div style="background-color: gray; height:100%;" id = "map"> Boat location</div>
                      </div>
                      <div v-if="boat.address != undefined" class="col-sm-3">
                          <h6 class="card-title text-start">Country: <input type="text" v-model="boat.address.country"></h6>
                          <h6 class="card-title text-start">City: <input type="text" v-model="boat.address.city"></h6>
                          <h6 class="card-title text-start">Street: <input type="text" v-model="boat.address.street"> </h6>
                          <h6 class="card-title text-start">Number: <input type="text" v-model="boat.address.number"> </h6>
                      </div>
                  </div>
              </div>         
            </div>
          </div>
        </div>

        <br> <br>
        <button v-on:click="updateBoatData()" class="btn btn-primary m-5 p-3">Update boat data</button>

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
  name: 'BoatUpdate',
  components: {
    CarouselView
  },
  data: function(){
    return {
        boatName: "",
        boat: {
            name: "",
            type: "",
            engineNumber: 1,
            length: 0,
            enginePower: 0,
            maxSpeed: 0,
            address: {
                country: "",
                city: "",
                street: "",
                number: "",
                latitude: 0.0,
                longitude: 0.0
            },
            promotionalDescription: "",
            imagesForBackend: [],
            imagesForBackendDelete: [],
            capacity: 0,
            behaviourRules: "",
            additionalServices: [],
            reservationCancellationConditions: "FREE",
            price: {
                price: 0,
                priceType: "PER_HOUR"
            },
            navigationalEquipment: []
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
        newBoatName : "",
        newLon : 0.0,
        newLat : 0.0,
        imagesForFront: [],
        imagesForBackend: [],
        imagesForBackendDelete: [],
        imagesForBackendIDS: [],
        imageCount: 0,
        map: {}
    }
  },
  created() {
      console.log('created called.');
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
            this.newBoatName = this.boat.name;
            console.log("**********************************")
            console.log(resp.data);
            this.loadBoatImages();
            this.loadMap();

            console.log("sve u brodu");
            console.log(this.boat.additionalServices);

            // var is
            for(let tempAllItem in this.allItemPrices) {

               console.log("temp item je:");
               console.log(this.allItemPrices[tempAllItem]);

              for(let tempItem in this.boat.additionalServices)
              {
                console.log("item u brodu:");
                console.log(this.boat.additionalServices[tempItem]);
                if(this.allItemPrices[tempAllItem].id == this.boat.additionalServices[tempItem].id)
                {
                  //da se oznaci
                  console.log("NASAO NA POKLAPANJE CENA PREDMETA");
                }
                this.allItemPrices.push({id:this.boat.additionalServices[tempItem].id,price:this.boat.additionalServices[tempItem].price,
                  name:this.boat.additionalServices[tempItem].name })
              }

            }
        });

        axios.get('http://localhost:8180/api/additionalequipment/getadditionalequipment').then(resp => {
            this.allAdditionalEquipment = resp.data;
            console.log("navigationalEquipment in boat")
            console.log(this.boat.navigationalEquipment)

            for(let tempNavEqv in this.boat.navigationalEquipment)
            {
               this.allAdditionalEquipment.push({id:tempNavEqv.id,name:tempNavEqv.name })
            }
        });
        axios.get('http://localhost:8180/api/itemprice/getitemprices').then(resp => {
            this.allItemPrices = resp.data;
            console.log("all prices");
            console.log(this.allItemPrices);

            console.log(this.allItemPrices);
            
        });
    },
    loadBoatImages: function(){
      this.imageCount = 0;
      for(let tempImage in this.boat.images)
      {
        this.imageCount++;
        this.imagesForFront.push({id: this.imageCount, name: ""+this.imageCount, path:tempImage.path});
      }
      
    },
    loadMap: function(){

        var lat = this.boat.address.latitude;
                var lng = this.boat.address.longitude;
        var iconGeometry = new Point(transform([lng, lat], 'EPSG:4326', 'EPSG:3857'));
        var iconFeature = new Feature({
          geometry: iconGeometry,
          name: 'Boat',
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
            //  this.boat.address.latitude = transformed_xy[0];
            //  this.boat.address.longitude = transformed_xy[1];
             this.newLat = transformed_xy[1];
             iconGeometry.setCoordinates(evt.coordinate);
        });


    },
    clickAdditionalEq: function(additionalEq){
        for (var i = 0; i < this.boat.navigationalEquipment.length; i++) {
            if(this.boat.navigationalEquipment[i].id == additionalEq.id){
                //remove
                this.boat.navigationalEquipment.splice(i, 1);
                console.log("removed element from additionEq with id="+additionalEq.id);
               
                return;
            }
        }
        //add
        this.boat.navigationalEquipment.push(additionalEq);
        console.log("added element from additionEq with id="+additionalEq.id);
     
    },
    addNewAdditionalEq: function(){
        let newAdditiona = { "name": this.newAdditionalEq }
            axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
            axios.post('http://localhost:8180/api/additionalequipment', newAdditiona).then(resp => {
                if (resp.data.name === newAdditiona.name) {
                    this.allAdditionalEquipment.push(resp.data);
                    this.newAdditionalEq = '';
                }
                console.log(resp.data);
            });
    },
    clickItemPrice: function(itemPrice){
        for (var i = 0; i < this.boat.additionalServices.length; i++) {
            if(this.boat.additionalServices[i].id == itemPrice.id){
                //remove
                this.boat.additionalServices.splice(i, 1);
                console.log("removed element from itemPrice with id="+itemPrice.id);
                return;
            }
        }
        //add
        this.boat.additionalServices.push(itemPrice);
        console.log("added element from itemPrice with id="+itemPrice.id);
    },
    cancelAddNewItemPrice: function(){
        this.newPriceItem.price = 0;
        this.newPriceItem.name = "Name";
        this.newPriceItem.description = "description";
        this.addNewItemPriceVisible = false;
    },
    addNewItemPrice: function () {
            let newItemPrice = {"name":this.newPriceItem.name, "description":this.newPriceItem.description, "price":this.newPriceItem.price}
            axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
            axios.post('http://localhost:8180/api/itemprice', newItemPrice).then(resp => {
                if (resp.data.name === newItemPrice.name) {
                    this.allItemPrices.push(resp.data);
                    this.cancelAddNewItemPrice()
                }
                console.log(resp.data);
            });
    },
    onFileChange: function(e){
            console.log('fajl select');
            const file = e.target.files[0];
            this.createBase64Image(file);
            this.imageCount--;
            this.imagesForFront.push({fromBackend: false, id: this.imageCount, name: ""+this.imageCount, path: URL.createObjectURL(file)});
            this.imagesForBackendIDS.push(this.imageCount)
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
    removeImage: function (image, index) {
            if (image.fromBackend) {
                this.imagesForBackendDelete.push(image)
            } else {
                for (let i = 0; i < this.imagesForBackendIDS.length; i++){
                    if (this.imagesForBackendIDS[i] == image.id) {
                        this.imagesForBackend.splice(i, 1);   
                        this.imagesForBackendIDS.splice(i, 1);   
                        break;       
                    }
                }
            }
            this.imagesForFront.splice(index,1);
        },
    updateBoatData: function(){
        console.log('NEW BOAT')
        // this.boat.imagesForBackend = this.imagesForBackend;
        this.boat.imagesForBackend = this.imagesForBackend;
        this.boat.imagesForBackendDelete = this.imagesForBackendDelete
        this.boat.address.longitude = this.map.newLon;
        this.boat.address.latitude = this.map.newLat;
        
        this.boat.newName = this.newBoatName;
        console.log(this.boat)
        axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
        axios.post('http://localhost:8180/api/boats/updateboat',this.boat)
          .then(response => {
              console.log('Odgoovr');
              console.log(response.data);
              alert("You updated boat")
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
