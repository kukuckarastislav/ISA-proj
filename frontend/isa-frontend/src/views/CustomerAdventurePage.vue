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
        
        <!-- PriceList -->
        <br>
        <div v-if="role==='ROLE_CUSTOMER'" class="row">
          <div class="col">
            <h2 class="text-start">Price list</h2>
            <div style="text-align:left;color: coral;">Please select additional services you'd like to use:</div>
            <div class="row row-cols-auto">
              <div style="cursor: pointer;" v-on:click="selectPriceItem(index)" v-for="(priceItem,index) in adventure.pricelist" :key="priceItem" :id="'priceItem'+index" class="priceItem">
                <span v-if="inItemList(index)" style="color:green"> Item added</span>
                <h2 style="font-weight: bold">{{priceItem.name}}</h2>
                <h5>{{priceItem.description}}</h5>
                <h2 style="font-weight: bold">${{priceItem.price}}</h2>
              </div>
            </div>
          </div>
        </div>


        <div v-else class="row">
          <div class="col">
            <h2 class="text-start">Price list</h2>
            <div class="row row-cols-auto">
              <div v-for="(priceItem,index) in adventure.pricelist" :key="priceItem" :id="'priceItem'+index" class="priceItem">
                <h2 style="font-weight: bold">{{priceItem.name}}</h2>
                <h5>{{priceItem.description}}</h5>
                <h2 style="font-weight: bold">${{priceItem.price}}</h2>
              </div>
            </div>
          </div>
        </div>

        
        <br>
        <br>
        <br>
        <button v-if="role==='ROLE_CUSTOMER'" type="button" style="zoom:1.5;background-color:coral;border-color:coral" class="btn btn-primary">RESERVE</button>
        <br>
        <br>
        <br>
        <br>


    </div>

</template>

<script>
import axios from "axios";
import CarouselView from '@/components/CarouselView.vue'
export default {
  name: "CustomerAdventurePage",
  components: {CarouselView},
  data: function () {
    return {
      adventure: {},
      instructor: null,
      adventureId: 0,
      isFetching: true,
      indexList: [],
      role: ''
    };
  },
  mounted: function () {
    this.adventureId = decodeURI(window.location.pathname.split("/")[2]);
    
    axios
      .get("http://localhost:8180/api/person/adventures/" + this.adventureId)
      .then((response) => {
        this.isFetching = false
        this.adventure = response.data;
        this.instructor = response.data.instructor;
        this.role = window.sessionStorage.getItem("role")
      if (this.role == null) this.role = ""
      })
      .catch((err) => alert(err));
  },
  methods: {
    setImg: function (image) {
      return "http://localhost:8180/" + image.path;
    },
    selectPriceItem: function(index){
      if(this.indexList.includes(index)){
        this.indexList.splice(this.indexList.indexOf(index), 1);
      }
      else this.indexList.push(index)
    },
    inItemList: function(index){
      if(this.indexList.includes(index)){
        return true;
      }
      return false;
    }
  },
};
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