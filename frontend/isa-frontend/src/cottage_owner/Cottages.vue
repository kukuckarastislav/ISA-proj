<template>
    <div style="height: 60px;"></div>
    <div class="container">
        <div class="row">
            <div class="col-sm-3">
                <div class="card align-items-start">
                    <div class="card-body justify-content-start text-start">

                        <select v-on:change="sortCottages" v-model="sortType" class="form-select" aria-label="Default select example" >
                            <option value="NameAZ" selected>Name A-Z</option>
                            <option value="NameZA">Name Z-A</option>
                            <option value="CityAZ">City A-Z</option>
                            <option value="CityZA">City Z-A</option>
                            <option value="CountryAZ">Country A-Z</option>
                            <option value="CountryZA">Country Z-A</option>
                            <option value="MaxNumOfPeopleASC">Max number of people Ascend</option>
                            <option value="MaxNumOfPeopleDES">Max number of people Descend</option>
                        </select>
                        
                        <br> <br>
                        <a href="/newcottage" class="btn btn-primary" style="width: 100%;">Add New Cottage</a>
                       
                        
   
                    </div>
                </div>
            </div>
            <div class="col-sm-9">
                <div v-for="cottage in cottages" :key="cottage">
                    
                        <div class="card" v-on:click="moreDetails(cottage)">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-sm-5">
                                        <img class="img-fluid" v-bind:src="getImg(cottage)">
                                    </div>
                                    <div class="col-sm-6">
                                        <h5 class="card-title text-start">{{cottage.name}}</h5> 
                                        <br>
                                        <h6 class="card-title text-start">Country: {{cottage.address.country}}</h6>
                                        <h6 class="card-title text-start">City: {{cottage.address.city}}</h6>
                                        <h6 class="card-title text-start">Street: {{cottage.address.street}} {{cottage.address.number}}</h6>
                                        <br>
                                        <h6 class="card-title text-start">Maximum number of people: {{cottage.maxNumberOfPeople}}</h6>
                                        <h6 class="card-title text-start">description: {{cottage.description}}</h6>
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
  name: 'Cottages',
  components: {
    
  },
  data: function(){
    return {
        cottages: [],
        sortType: 'NameAZ'
    }
  },
  mounted: function(){
     this.loadData();
  },
  methods: {
    loadData: function(){
        axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
        axios.get('http://localhost:8180/api/cottages/byowner').then(resp => {
            this.cottages = resp.data;
               this.sortCottages();
            console.log(resp.data);
        });
    },
    getImg: function(cottages){
        var minNum = 10000;
        var imgPath = cottages.images[0].path;
        for(var i=0; i<cottages.images.length; i++){
            var num = Number(cottages.images[i].name.split(".")[0].substring(3));
            if(num < minNum){
                minNum = num;
                imgPath = cottages.images[i].path;
            }

        }
        return 'http://localhost:8180/api/entityImage/'+imgPath;
    },
    moreDetails: function(cottage){
        this.$router.push({ path: '/cottageOwnerCottage/'+encodeURIComponent(cottage.name)});
    },
    sortCottages: function () {
            if (this.sortType == 'NameAZ') {
				this.cottages.sort((a, b) => (a.name > b.name) ? 1 : ((b.name > a.name) ? -1 : 0));
			} else if (this.sortType == 'NameZA') {
				this.cottages.sort((b, a) => (a.name > b.name) ? 1 : ((b.name > a.name) ? -1 : 0));
			} else if (this.sortType == 'CityAZ') {
				this.cottages.sort((a, b) => (a.address.city > b.address.city) ? 1 : ((b.address.city > a.address.city) ? -1 : 0));
			} else if (this.sortType == 'CityZA') {
				this.cottages.sort((b, a) => (a.address.city > b.address.city) ? 1 : ((b.address.city > a.address.city) ? -1 : 0));
			} else if (this.sortType == 'CountryAZ') {
				this.cottages.sort((a, b) => (a.address.country > b.address.country) ? 1 : ((b.address.country > a.address.country) ? -1 : 0));
			} else if (this.sortType == 'CountryZA') {
				this.cottages.sort((b, a) => (a.address.country > b.address.country) ? 1 : ((b.address.country > a.address.country) ? -1 : 0));
			} else if (this.sortType == 'MaxNumOfPeopleASC') {
				this.cottages.sort((a, b) => (a.maxNumberOfPeople > b.maxNumberOfPeople) ? 1 : ((b.maxNumberOfPeople > a.maxNumberOfPeople) ? -1 : 0));
			} else if (this.sortType == 'MaxNumOfPeopleDES') {
				this.cottages.sort((b, a) => (a.maxNumberOfPeople > b.maxNumberOfPeople) ? 1 : ((b.maxNumberOfPeople > a.maxNumberOfPeople) ? -1 : 0));
			} 
		},
  }
}
</script>


<style scoped>



</style>
