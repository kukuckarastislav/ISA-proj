<template>
    <div style="height: 60px;"></div>
    <div class="container">
        <div class="row">
            <div class="col-sm-6">

            <CarouselView></CarouselView>

            <button class="btn btn-primary">Add Image</button>
            

            </div>
            <div class="col-sm-6">

                <h1 class="text-start">name: <input v-model="adventure.name" type="text"> </h1>  <br>
                
                <h5 class="text-start">Description: </h5>
                <textarea class="form-control" v-model="adventure.description" rows="3"></textarea>
                <br>

                
                <h5 class="text-start">Behaviour: </h5>
                <textarea class="form-control" v-model="adventure.behaviourRules" rows="2"></textarea> <br>

                <h5 class="text-start">Max number of people: <input v-model="adventure.maxNumberOfPeople" style="width: 60px;" type="number" min=0></h5>
                
                <h5 class="text-start">Reservation Cancellation Conditions: 
                <select v-model="adventure.reservationCancellationConditions" class="cenaCss">
                    <option value="FREE" selected>FREE</option>
                    <option value="FIVE_PERCENT">FIVE_PERCENT</option>
                    <option value="TEN_PERCENT">TEN_PERCENT</option>
                    <option value="FIFTEEN_PERCENT">FIFTEEN_PERCENT</option>
                    <option value="TWENTY_PERCENT">TWENTY_PERCENT</option>
                </select>
                </h5>
                
                <h5 class="text-start ">Additional Equipments: </h5>
    
                <div class="d-flex justify-content-start">
                    <input type="text" v-model="newAdditionalEq" style="width: 50%;" class="form-control m-2"> 
                    <button v-on:click="addNewAdditionalEq()" class="btn btn-primary m-2">Add</button>
                </div> 
                
                <div class="list-group text-start overflow-auto card" style="height: 150px;">
                    <label v-for="additionalEq in allAdditionalEquipment" :key="additionalEq" class="list-group-item">
                        <input class="form-check-input me-1" type="checkbox" value="" v-on:click="clickAdditionalEq(additionalEq)">
                        {{additionalEq.name}}
                    </label>
                </div>
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

        

        <br> <br> <br>
        <div class="row">
          <div class="col">
            <div class="card">
              <div class="card-body">
                  <div style="height: 200px;" class="row">
                      <div class="col-sm-9">
                          <div style="background-color: gray; height:100%;"> PROSTOR ZA MAPU ?</div>
                      </div>
                      <div v-if="adventure.address != undefined" class="col-sm-3">
                          <h6 class="card-title text-start">Country: {{adventure.address.country}}</h6>
                          <h6 class="card-title text-start">City: {{adventure.address.city}}</h6>
                          <h6 class="card-title text-start">Street: {{adventure.address.street}} {{adventure.address.number}}</h6>
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
            images: [],
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
        }
    }
  },
  mounted: function(){
      this.loadData();
  },
  methods: {
    loadData: function(){
        axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
        console.log("AHHAHAHAHAHAH");
        axios.get('http://localhost:8180/api/additionalequipment/getadditionalequipment').then(resp => {
            this.allAdditionalEquipment = resp.data;
            console.log(resp.data);
        });
        axios.get('http://localhost:8180/api/itemprice/getitemprices').then(resp => {
            this.allItemPrices = resp.data;
            console.log(resp.data);
        });
    },
    setImg: function(image){
      return 'http://localhost:8180/'+image.path;
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
    }
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
