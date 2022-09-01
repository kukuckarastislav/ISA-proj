<template>
    <div style="height: 60px;"></div>
    <div class="container">
        <div class="row">
            <div class="col-sm-3">
                <div class="card align-items-start">
                    <div class="card-body justify-content-start text-start">

                        <select v-on:change="sortBoats" v-model="sortType" class="form-select" aria-label="Default select example" >
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
                        <a href="/newboat" class="btn btn-primary" style="width: 100%;">Add New Boat</a>
                       
                        
   
                    </div>
                </div>
            </div>
            <div class="col-sm-9">
                <div v-for="boat in boats" :key="boat">
                    
                        <div v-if="showIf(boat)" class="card">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-5" v-on:click="moreDetails(boat)">
                                        <img class="img-fluid" v-bind:src="getImg(boat)">
                                    </div>
                                    <div class="col-5" v-on:click="moreDetails(boat)">
                                        <h5 class="card-title text-start">{{boat.name}}</h5> 
                                        <br>
                                        <h6 class="card-title text-start">Country: {{boat.address.country}}</h6>
                                        <h6 class="card-title text-start">City: {{boat.address.city}}</h6>
                                        <h6 class="card-title text-start">Street: {{boat.address.street}} {{boat.address.number}}</h6>
                                        <br>
                                        <h6 class="card-title text-start">Maximum number of people: {{boat.capacity}}</h6>
                                        <h6 class="card-title text-start">description: {{boat.promotionalDescription}}</h6>
                                    </div>
                                    <div class="col-2 text-end">
                                        <button class="btn btn-danger" v-on:click="deleteClick(boat)">Delete</button>
                                    </div>
                                </div>
                            </div>         
                        </div>
                    
                    <br>
                </div>
                 
              

            </div>
















            
            <!--<div class="col-sm-9">
            //     <div v-for="boat in boats" :key="boat">
                    
            //             <div class="card" v-on:click="moreDetails(boat)">
            //                 <div class="card-body">
            //                     <div class="row">
            //                         <div class="col-sm-5">
            //                             <img class="img-fluid" v-bind:src="getImg(boat)">
            //                         </div>
            //                         <div class="col-sm-6">
            //                             <h5 class="card-title text-start">{{boat.name}}</h5> 
            //                             <br>
            //                             <h6 class="card-title text-start">Country: {{boat.address.country}}</h6>
            //                             <h6 class="card-title text-start">City: {{boat.address.city}}</h6>
            //                             <h6 class="card-title text-start">Street: {{boat.address.street}} {{boat.address.number}}</h6>
            //                             <br>
            //                             <h6 class="card-title text-start">Maximum number of people: {{boat.maxNumberOfPeople}}</h6>
            //                             <h6 class="card-title text-start">description: {{boat.description}}</h6>
            //                         </div>
            //                     </div>
            //                 </div>         
            //             </div>
                    
            //         <br>
            //     </div>
                 
              
            // </div>-->


        </div>
    </div>

</template>

<script>
import axios from "axios";
export default {
  name: 'Boats',
  components: {
    
  },
  data: function(){
    return {
        boats: [],
        sortType: 'NameAZ',
        filterByName: ''
    }
  },
  mounted: function(){
     this.loadData();
  },
  methods: {
    loadData: function(){
        axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
        axios.get('http://localhost:8180/api/boats/byowner').then(resp => {
            this.boats = resp.data;
               this.sortBoats();
            console.log(resp.data);
        });
    },
    getImg: function(boats){
        var minNum = 10000;
        var imgPath = boats.images[0].path;
        for(var i=0; i<boats.images.length; i++){
            var num = Number(boats.images[i].name.split(".")[0].substring(3));
            if(num < minNum){
                minNum = num;
                imgPath = boats.images[i].path;
            }

        }
        return 'http://localhost:8180/api/entityImage/'+imgPath;
    },
    moreDetails: function(boat){
        this.$router.push({ path: '/boatOwnerBoat/'+encodeURIComponent(boat.name)});
    },
    sortBoats: function () {
            if (this.sortType == 'NameAZ') {
				this.boats.sort((a, b) => (a.name > b.name) ? 1 : ((b.name > a.name) ? -1 : 0));
			} else if (this.sortType == 'NameZA') {
				this.boats.sort((b, a) => (a.name > b.name) ? 1 : ((b.name > a.name) ? -1 : 0));
			} else if (this.sortType == 'CityAZ') {
				this.boats.sort((a, b) => (a.address.city > b.address.city) ? 1 : ((b.address.city > a.address.city) ? -1 : 0));
			} else if (this.sortType == 'CityZA') {
				this.boats.sort((b, a) => (a.address.city > b.address.city) ? 1 : ((b.address.city > a.address.city) ? -1 : 0));
			} else if (this.sortType == 'CountryAZ') {
				this.boats.sort((a, b) => (a.address.country > b.address.country) ? 1 : ((b.address.country > a.address.country) ? -1 : 0));
			} else if (this.sortType == 'CountryZA') {
				this.boats.sort((b, a) => (a.address.country > b.address.country) ? 1 : ((b.address.country > a.address.country) ? -1 : 0));
			} else if (this.sortType == 'MaxNumOfPeopleASC') {
				this.boats.sort((a, b) => (a.capacity > b.capacity) ? 1 : ((b.capacity > a.capacity) ? -1 : 0));
			} else if (this.sortType == 'MaxNumOfPeopleDES') {
				this.boats.sort((b, a) => (a.capacity > b.capacity) ? 1 : ((b.capacity > a.capacity) ? -1 : 0));
			} 
		},
        showIf: function(adventure){
        return adventure.name.toLowerCase().includes(this.filterByName.toLowerCase())
        },
        deleteClick: function (adventure) {
            let confirmAction = confirm("Are you sure? Delete user " + adventure.name);
            if (!confirmAction) {
                return
            }
            axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
            axios.delete('http://localhost:8180/api/boat/'+boat.id).then(resp => {
                alert(resp.data)
                this.loadData();
            }, (err) => {alert("Error")});
        },
  }
}
</script>


<style scoped>



</style>
