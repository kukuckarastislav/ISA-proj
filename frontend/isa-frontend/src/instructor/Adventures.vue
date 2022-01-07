<template>
    <div style="height: 60px;"></div>
    <div class="container">
        <div class="row">
            <div class="col-sm-3">
                <div class="card align-items-start">
                    <div class="card-body justify-content-start text-start">

                        <select v-on:change="sortAdventures" v-model="sortType" class="form-select" aria-label="Default select example" >
                            <option value="NameAZ" selected>Name A-Z</option>
                            <option value="NameZA">Name Z-A</option>
                            <option value="CityAZ">City A-Z</option>
                            <option value="CityZA">City Z-A</option>
                            <option value="CountryAZ">Country A-Z</option>
                            <option value="CountryZA">Country Z-A</option>
                            <option value="MaxNumOfPeopleASC">Max number of people Ascend</option>
                            <option value="MaxNumOfPeopleDES">Max number of people Descend</option>
                        </select>


                        
   
                    </div>
                </div>
            </div>
            <div class="col-sm-9">
                <div v-for="adventure in adventures" :key="adventure">
                    
                        <div class="card" v-on:click="moreDetails(adventure)">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-sm-5">
                                        <img class="img-fluid" v-bind:src="getImg(adventure)">
                                    </div>
                                    <div class="col-sm-6">
                                        <h5 class="card-title text-start">{{adventure.name}}</h5> 
                                        <br>
                                        <h6 class="card-title text-start">Country: {{adventure.address.country}}</h6>
                                        <h6 class="card-title text-start">City: {{adventure.address.city}}</h6>
                                        <h6 class="card-title text-start">Street: {{adventure.address.street}} {{adventure.address.number}}</h6>
                                        <br>
                                        <h6 class="card-title text-start">Maximum number of people: {{adventure.maxNumberOfPeople}}</h6>
                                        <h6 class="card-title text-start">description: {{adventure.description}}</h6>
                                    </div>
                                </div>
                            </div>         
                        </div>
                    
                    <br>
                </div>
                 
              

            </div>
        </div>
    </div>

</template>

<script>
import axios from "axios";
export default {
  name: 'Adventures',
  components: {
    
  },
  data: function(){
    return {
        adventures: [],
        sortType: 'NameAZ'
    }
  },
  mounted: function(){
     this.loadData();
  },
  methods: {
    loadData: function(){
        axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
        axios.get('http://localhost:8180/api/adventure/byinstructor').then(resp => {
            this.adventures = resp.data;
               this.sortAdventures();
            console.log(resp.data);
        });
    },
    getImg: function(adventure){
        var minNum = 10000;
        var imgPath = adventure.images[0].path;
        for(var i=0; i<adventure.images.length; i++){
            var num = Number(adventure.images[i].name.split(".")[0].substring(3));
            if(num < minNum){
                minNum = num;
                imgPath = adventure.images[i].path;
            }

        }
        return 'http://localhost:8180/'+imgPath;
    },
    moreDetails: function(adventure){
        this.$router.push({ path: '/adventure/'+encodeURIComponent(adventure.name)});
    },
    sortAdventures: function () {
            if (this.sortType == 'NameAZ') {
				this.adventures.sort((a, b) => (a.name > b.name) ? 1 : ((b.name > a.name) ? -1 : 0));
			} else if (this.sortType == 'NameZA') {
				this.adventures.sort((b, a) => (a.name > b.name) ? 1 : ((b.name > a.name) ? -1 : 0));
			} else if (this.sortType == 'CityAZ') {
				this.adventures.sort((a, b) => (a.address.city > b.address.city) ? 1 : ((b.address.city > a.address.city) ? -1 : 0));
			} else if (this.sortType == 'CityZA') {
				this.adventures.sort((b, a) => (a.address.city > b.address.city) ? 1 : ((b.address.city > a.address.city) ? -1 : 0));
			} else if (this.sortType == 'CountryAZ') {
				this.adventures.sort((a, b) => (a.address.country > b.address.country) ? 1 : ((b.address.country > a.address.country) ? -1 : 0));
			} else if (this.sortType == 'CountryZA') {
				this.adventures.sort((b, a) => (a.address.country > b.address.country) ? 1 : ((b.address.country > a.address.country) ? -1 : 0));
			} else if (this.sortType == 'MaxNumOfPeopleASC') {
				this.adventures.sort((a, b) => (a.maxNumberOfPeople > b.maxNumberOfPeople) ? 1 : ((b.maxNumberOfPeople > a.maxNumberOfPeople) ? -1 : 0));
			} else if (this.sortType == 'MaxNumOfPeopleDES') {
				this.adventures.sort((b, a) => (a.maxNumberOfPeople > b.maxNumberOfPeople) ? 1 : ((b.maxNumberOfPeople > a.maxNumberOfPeople) ? -1 : 0));
			} 
		},
  }
}
</script>


<style scoped>



</style>
