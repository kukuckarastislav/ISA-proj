<template>
    <div style="height: 60px;"></div>
    <div class="container">
        <div class="row">
            <div class="col-sm-6">

            <CarouselView v-bind:images="adventure.images"></CarouselView>

            </div>
            <div class="col-sm-6">

                <h1 class="text-start">{{adventure.name}}</h1> <br>
                
                <h5 class="text-start">Description: {{adventure.description}} Lorem ipsum dolor sit amet consectetur, adipisicing elit. Ducimus, sit ipsa voluptatem expedita odit consequatur aliquid, nam, tenetur reiciendis optio deleniti quibusdam inventore iure quas hic dolore alias totam modi.</h5>
                <br>
                
                <h5 class="text-start">Behaviour: {{adventure.behaviourRules}}</h5>
                <h5 class="text-start">Max number of people: {{adventure.maxNumberOfPeople}}</h5>

                <h5 class="text-start">Reservation Cancellation Conditions: <b>{{adventure.reservationCancellationConditions}}</b></h5>

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
                                <h5 class="card-title text-start">Instructor IME PREZIME</h5>
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



    </div>

</template>

<script>
import axios from "axios";
import CarouselView from '@/components/CarouselView.vue'
export default {
  name: 'AdventureView',
  components: {
    CarouselView
  },
  data: function(){
    return {
        adventureName: "",
        adventure: {}
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

.cenaCss{
  color: coral;
}

</style>
