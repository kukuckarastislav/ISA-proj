<template>
    <div style="height: 60px;"></div>
    
    <div class="options" v-if="role != 'ROLE_CLIENT' && role!=''">
            <button class="btn"  v-if="role != 'ROLE_ADMIN'">Edit boat</button>
            <button class="btn" >Delete boat</button>
        </div>

    <div class="container">
        <div class="row">
            <div class="col-sm-6">

            <CarouselView v-bind:server="true" v-bind:images="boat.images"></CarouselView>
            

            </div>
            <div v-if="boat.name != undefined" class="col-sm-6">

                <h1 class="text-start">{{boat.name}}</h1> <br>

                <h3 class="text-start stars">
                  <span v-for="index in Math.round(boat.averageGrade)" :key="index">&#9733;</span> 
                  <span v-for="index in Math.round(5-boat.averageGrade)" :key="index">&#9734;</span> 
                  {{boat.averageGrade}}
                </h3>
                
                <h5 class="text-start">Description: {{boat.description}} </h5>
                <br>
                
                <h5 class="text-start">Behaviour: {{boat.behaviourRules}}</h5>
                <h5 class="text-start">Max number of people: {{boat.maxNumberOfPeople}}</h5>
                
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
              <div v-for="priceItem in boat.pricelist" :key="priceItem" class="priceItem">
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
        });
    },
    setImg: function(image){
      return 'http://localhost:8180/'+image.path;
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
