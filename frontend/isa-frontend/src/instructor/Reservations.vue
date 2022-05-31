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
                        <br>
                        <input v-model="filterByName" type="text" class="form-control" />
                    
                        
   
                    </div>
                </div>
            </div>
            <div class="col-sm-9">
                <div v-for="reservation in reservations" :key="reservation">
                    
                        <div v-if="showIf(reservation)">
                            <ReservationViewComponent v-bind:reservation="reservation" />
                            <br>
                        </div>
                    
                </div>
            </div>
        </div>
    </div>

</template>

<script>
import axios from "axios";
import ClientViewComponent from '../components/ClientViewComponent.vue'
import ReservationViewComponent from '../instructor/ReservationViewComponent.vue'
export default {
  name: 'Reservation',
  components: {
    ClientViewComponent,
    ReservationViewComponent
  },
  data: function(){
    return {
        reservations: [],
        sortType: 'NameAZ',
        filterByName: '',
    }
  },
  mounted: function(){
     this.loadData();
  },
  methods: {
    loadData: function(){
        axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
        axios.get('http://localhost:8180/api/instructorterms/reservation').then(resp => {
            this.reservations = resp.data;
            this.sortAdventures();
            console.log(resp.data);
        });
    },
    sortAdventures: function () {
            /*
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
            */
	},
    showIf: function(reservation){
        return reservation.adventureName.toLowerCase().includes(this.filterByName.toLowerCase())
    },
  }
}
</script>


<style scoped>

</style>
