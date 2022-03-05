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

        <br><br><br>
        <div class="row">
          <div class="col">
            <h1>Calendar</h1>
            <FullCalendar :options="calendarOptions" />
            <br><br><br>
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
  name: 'AdventureView',
  components: {
    CarouselView,
    FullCalendar
  },
  data: function(){
    return {
        adventureName: "",
        adventure: {},


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
            {
              title: 'Evo je prvi event',
              start: '2022-03-10T10:00:00',
              end: '2022-03-12T16:00:00',
              display: 'auto',
              backgroundColor: "rgb(255,0,0)",
              borderColor: "rgb(255,0,255)",
              description: "opis neki",
              editable: true,
              overlap: false,
            },
            {
              title: 'Dnevni event',
              start: '2022-03-12',
              editable: true,
            },
          ]
        }




    }
  },
  mounted: function(){
    this.adventureName = decodeURI(window.location.pathname.split('/')[2]);
    this.loadData();
  },
  methods: {
    loadData: function(){
       axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
        axios.get('http://localhost:8180/api/adventure/byinstructor/'+encodeURIComponent(this.adventureName)).then(resp => {
            this.adventure = resp.data;
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
