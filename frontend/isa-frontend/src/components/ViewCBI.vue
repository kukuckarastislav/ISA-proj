<template>
<div v-if="!isFetching">
    <div>

        <div style="height: 80px;"></div>

        <section class="text-light p-5">
            <div class="container">
                <div class="row">
                    <div class="d-md-flex align-items-center justify-content-center">
                        <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
                            <input type="radio" class="btn-check" name="btnradio" id="btnradio1" autocomplete="off" checked v-on:click="button1Clicked">
                            <label class="btn btn-outline-primary" for="btnradio1">Cotages</label>

                            <input type="radio" class="btn-check" name="btnradio" id="btnradio2" autocomplete="off" v-on:click="button2Clicked">
                            <label class="btn btn-outline-primary" for="btnradio2">Boats</label>

                            <input type="radio" class="btn-check" name="btnradio" id="btnradio3" autocomplete="off" v-on:click="button3Clicked">
                            <label class="btn btn-outline-primary" for="btnradio3">Adventures</label>
                        </div>
                    </div>
                </div>
            </div>
        </section>



        <section class="text-light p-5">
            <div class="container ">
                    <h3 class="mb-3 mb-md-0 isa-text-color-darkBlue">Neki parametri za pretgragu???</h3>
                
                <div class="d-md-flex align-items-center">

                    <div class="input-group news-input">
                        <input type="text" class="form-control" placeholder="parametar pretrage 1" />
                        <input type="text" class="form-control" placeholder="parametar pretrage 2" />
                        <input type="text" class="form-control" placeholder="parametar pretrage 3" />
                        <button class="btn btn-dark btn-lg" type="button">Search</button>
                    </div>
                </div>
               <!-- <div class="form-check form-switch">
                    <label class="form-check-label isa-text-color-darkBlue" for="flexSwitchCheckDefault">Default switch checkbox input</label>
                    <input class="form-check-input" type="checkbox" id="flexSwitchCheckDefault">
                </div> -->
                <div style="text-align:left; background-color:white">
                <button style="background-color:white; border:none;"><img style="height:2em;"  src="../assets/filter.svg"></button>
                </div>
            </div>
        </section>



    <section v-if="button3" id="cardss" class="p-5 isa-color-standard">
        <div class="container">
            <div class="row g-4">

               
                <div  v-for="n in adventures" :key="n" class="col-md-6 col-lg-4">
                    <div class="card bg-light">
                        <div class="card-body text-center">
                            <img v-bind:src="'http://localhost:8180/' + n.images[0].path" class="imgCard" alt=""/>
                            <h3 style="margin-top:5%" class="card-title mb-3">{{n.name}}</h3>
                            <p class="card-text">
                                Instructor: {{n.instructor.firstName}} {{n.instructor.lastName}}<br>
                                Description: {{n.description}}<br>
                                Address: {{n.address.country}}, {{n.address.city}}, {{n.address.street}} {{n.address.number}}
                            </p>
                            <button class="btn isa-btn-more-detail btn-sm" type="button" v-on:click="showAdventure(n.id)">More details</button>
                        </div>
                    </div>
                </div>




            </div>
        </div>
    </section>

    <section v-if="button1" id="cardss" class="p-5 isa-color-standard">
        <div class="container">
            <div class="row g-4">

               
                <div  v-for="n in cottages" :key="n" class="col-md-6 col-lg-4">
                    <div class="card bg-light">
                        <div class="card-body text-center">
                            <img v-bind:src="'http://localhost:8180/' + n.images[0].path" class="imgCard" alt=""/>
                            <h3 class="card-title mb-3">{{n.name}}</h3>
                            <p class="card-text">
                            Address: {{n.address.country}}, {{n.address.city}}, {{n.address.street}} {{n.address.number}}
                            </p>
                            <button class="btn isa-btn-more-detail btn-sm" type="button" v-on:click="showCottage(n.id)">More details</button>
                        </div>
                    </div>
                </div>




            </div>
        </div>
    </section>


    <section v-if="button2" id="cardss" class="p-5 isa-color-standard">
        <div class="container">
            <div class="row g-4">

               
                <div  v-for="n in boats" :key="n" class="col-md-6 col-lg-4">
                    <div class="card bg-light">
                        <div class="card-body text-center">
                            <img v-bind:src="'http://localhost:8180/' + n.images[0].path" class="imgCard" alt=""/>
                            <h3 class="card-title mb-3">{{n.name}}</h3>
                            <p class="card-text">
                            Address: {{n.address.country}}, {{n.address.city}}, {{n.address.street}} {{n.address.number}}
                            </p>
                            <button class="btn isa-btn-more-detail btn-sm" type="button" v-on:click="showBoat(n.id)">More details</button>
                        </div>
                    </div>
                </div>




            </div>
        </div>
    </section>



    <div class="btn-toolbar justify-content-center isa-color-standard " role="toolbar" aria-label="Toolbar with button groups">
        <div class="btn-group" role="group" aria-label="First group">
            <button type="button" class="btn btn-outline-secondary">1</button>
            <button type="button" class="btn btn-outline-secondary">2</button>
            <button type="button" class="btn btn-outline-secondary">3</button>
            <button type="button" class="btn btn-outline-secondary">4</button>
        </div>
    </div>
    <div style="height:80px;" class="isa-color-standard"></div>



    </div>
</div>
</template>

<script>
import axios from "axios";
export default {
  name: 'ViewCBI',
  data: function(){
    return {
      msgFromBackend: '',
      adventures: [],
      button1: true,
      button2: false,
      button3: false,
      cottages: [],
      boats: [],
      isFetching: true
    }
  },
  mounted() {
    
    axios.get('http://localhost:8180/api/person/adventures')
			.then(response => {this.adventures = response.data
            axios.get('http://localhost:8180/api/person/cottages')
			.then(response => {this.cottages = response.data
            axios.get('http://localhost:8180/api/person/boats')
			.then(response => {this.boats = response.data
            this.isFetching = false}).catch(err => (alert(err)));
            }).catch(err => (alert(err)));
            }).catch(err => (alert(err)));
    
     
      
  },
  methods: {
        button3Clicked : function(){
                this.button3 = true
                this.button1 = false
                this.button2 = false
        },
        button1Clicked : function(){
                this.button3 = false
                this.button1 = true
                this.button2 = false
        },
        button2Clicked : function(){
                this.button3 = false
                this.button2 = true
                this.button1 = false
        },
        showAdventure: function(IdNum){
            this.$router.push({ path: '/customerAdventurePage/'+IdNum});
        },
        showCottage: function(IdNum){
            this.$router.push({ path: '/customerCottagePage/'+IdNum});
        },
        showBoat: function(IdNum){
            this.$router.push({ path: '/customerBoatPage/'+IdNum});
        }
  }

}
</script>
<style scoped>
.imgCard{
    width: 380px;
    height: 250px;
}

</style>
