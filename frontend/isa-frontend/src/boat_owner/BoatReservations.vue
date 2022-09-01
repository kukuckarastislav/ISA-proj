<template>sortAdventures
    <div style="height: 60px;"></div>
    <div class="container">
        <div class="row">
            <div class="col-sm-3">
                <div class="card align-items-start">
                    <div class="card-body justify-content-start text-start">
                        
                        <h5>Sort</h5>
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
                        <hr>
                        <h5>Boat Name</h5>
                        <input v-model="filter.boatName" type="text" class="form-control" />
                        
                        <hr>
                        <h5>Client Name</h5>
                        <input v-model="filter.clientName" type="text" class="form-control" />
                        
                        <hr>
                        <h5>Reservation Type</h5>
                        <input v-model="showReservation.res" class="form-check-input m-2" type="checkbox" id="id1" checked>
                        <label class="form-check-label m-1" for="id1"> Reservation</label>
                        <br>
                        <input v-model="showReservation.fastRes" class="form-check-input m-2" type="checkbox" id="id2" checked>
                        <label class="form-check-label m-1" for="id2"> Fast Reservation</label>
                        <br>
                        <hr>
                        <h5>Time</h5>
                        <input v-model="showReservation.past" class="form-check-input m-2" type="checkbox" id="id3" checked>
                        <label class="form-check-label m-1" for="id3"> Past</label>
                        <br>
                        <input v-model="showReservation.now" class="form-check-input m-2" type="checkbox" id="id4" checked>
                        <label class="form-check-label m-1" for="id4"> Now</label>
                        <br>
                        <input v-model="showReservation.future" class="form-check-input m-2" type="checkbox" id="id5" checked>
                        <label class="form-check-label m-1" for="id5"> Future</label>
                        <br>
                    
                        
   
                    </div>
                </div>
            </div>
            <div class="col-sm-9">
                <div v-for="reservation in reservations" :key="reservation">
                    
                        <div v-if="showIf(reservation)">
                            <BoatReservationViewComponent v-bind:reservation="reservation" />
                            <br> <br>
                        </div>
                    
                </div>
            </div>
        </div>
    </div>

</template>

<script>
import axios from "axios";
import ClientViewComponent from '../components/ClientViewComponent.vue'
// import ReservationViewComponent from '../instructor/ReservationViewComponent.vue'
import BoatReservationViewComponent from '../boat_owner/BoatReservationViewComponent.vue'
export default {
  name: 'BoatReservation',
  components: {
    ClientViewComponent,
    BoatReservationViewComponent
  },
  data: function(){
    return {
        reservations: [],
        sortType: 'NameAZ',
        filter: {
            boatName: '',
            clientName: '',
        },
        showReservation: {
            res: true,
            fastRes: true,
            past: true,
            now: true,
            future: true,
        }
    }
  },
  mounted: function(){
     this.loadData();
  },
  methods: {
    loadData: function(){
        axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
        axios.get('http://localhost:8180/api/boat-terms/reservation').then(resp => {
            this.reservations = resp.data;
            this.sortBoats();
            console.log(resp.data);
        });
    },
    sortBoats: function () {
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
        return this.showByBoatName(reservation) && this.showByReservationType(reservation) && this.showByClientName(reservation) && this.showByTime(reservation)
    },
    showByBoatName: function(reservation){
        return reservation.adventureName.toLowerCase().includes(this.filter.adventureName.toLowerCase())
    },
    showByClientName: function(reservation){
        if(this.filter.clientName === '') return true; //mini optimizacija xD 

        if(reservation.termType === 'RESERVATION'){
            return reservation.client.firstName.toLowerCase().includes(this.filter.clientName.toLowerCase())
        }else if(reservation.termType === 'FAST_RESERVATION'){
            for(const c of reservation.insFastResHistories){
                if(c.client.firstName.toLowerCase().includes(this.filter.clientName.toLowerCase())){
                    return true;
                }
            }
        }
        return false;
    },
    showByReservationType: function(reservation){
        if(this.showReservation.res == true && reservation.termType === 'RESERVATION') return true;
        if(this.showReservation.fastRes == true && reservation.termType === 'FAST_RESERVATION') return true;

        return false;
    },
    showByTime: function(reservation){
        
        if(this.showReservation.past &&  new Date(reservation.endTime) < new Date() ) return true;
        if(this.showReservation.now &&  new Date(reservation.startTime) < new Date() && new Date(reservation.endTime) > new Date()) return true;
        if(this.showReservation.future &&  new Date(reservation.startTime) > new Date() ) return true;
        
        return false;
    },
  }
}
</script>


<style scoped>

</style>
