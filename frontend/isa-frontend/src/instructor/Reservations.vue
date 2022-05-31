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
                    
                        <div v-if="showIf(reservation)" class="card">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-4">
                                        <img class="img-fluid" v-bind:src="getImg(reservation)">
                                    </div>
                                    <!-- Info -->
                                    <div class="col-3">
                                        <h5 class="card-title text-start"><a v-on:click="moreDetails(reservation.adventureName)" href="">{{reservation.adventureName}}</a></h5>
                                        <br>
                                        <h6 class="card-title text-start">Country: {{reservation.address.country}}</h6>
                                        <h6 class="card-title text-start">City: {{reservation.address.city}}</h6>
                                        <h6 class="card-title text-start">Street: {{reservation.address.street}} {{reservation.address.number}}</h6>
                                        <br>
                                    </div>
                                    <div class="col-5">
                                        <br>
                                        <h5 class="card-title text-start m-1" style="font-weight: bold;">{{reservation.termType}}</h5>
                                        <h5 class="card-title text-start">Start: {{convertDate(reservation.startTime)}}</h5>
                                        <h5 class="card-title text-start">End: {{convertDate(reservation.endTime)}}</h5>
                                        <br>
                                        <h5 class="card-title text-start">Price: {{reservation.price}}</h5>
                                    </div>
                                </div>
                                 <!-- Additional services -->
                                <div class="row" style="margin-top:20px;">
                                    <div class="col-12">
                                        <div class="list-group text-start overflow-auto card" style="height: 200px">
                                        <div class="row m-1" style="font-weight: bold;">
                                            <div class="col-3">Name</div>
                                            <div class="col-6">Description</div>
                                            <div class="col-2">Price</div>
                                        </div>
                                        <label v-for="itPrice in reservation.additionalServices" :key="itPrice" class="list-group-item">
                                            <div class="row m-1">
                                                <div class="col-3">{{itPrice.name}}</div>
                                                <div class="col-6">{{itPrice.description}}</div>
                                                <div class="col-2">{{itPrice.price}}</div>
                                            </div>
                                        </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="row" style="margin-top:30px;">
                                    <!-- reservation.client -->
                                    <div class="col-8">
                                        <div v-if="reservation.clientView">
                                            <ClientViewComponent v-bind:client="reservation.clientView"/>
                                        </div>
                                    </div>
                                    <div class="col-4">
                                        <div v-if="reservation.termType === 'FAST_RESERVATION'">
                                            <div class="list-group text-start overflow-auto card" style="height: 220px">
                                                <label v-for="frh in reservation.insFastResHistories" :key="frh" class="list-group-item">
                                                    <div v-on:click="selectClientView(reservation, frh)" class="row">
                                                        <div class="col">{{frh.client.firstName}}</div>
                                                        <div class="col">{{frh.client.lastName}}</div>
                                                        <div class="col">{{frh.statusOfFastReservation}}</div>
                                                    </div>
                                                </label>
                                            </div>
                                        </div>
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
import ClientViewComponent from '../components/ClientViewComponent.vue'
export default {
  name: 'Reservation',
  components: {
    ClientViewComponent
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
            for(let i = 0; i < this.reservations.length; i++){
                this.reservations[i].clientView = this.reservations[i].client
                if(this.reservations[i].termType === 'FAST_RESERVATION'){
                    if(this.reservations[i].insFastResHistories.length > 0){
                        this.reservations[i].clientView = this.reservations[i].insFastResHistories[0].client
                    }
                }
            }
            this.sortAdventures();
            console.log(resp.data);
        });
    },
    moreDetails: function(adventureName){
        this.$router.push({ path: '/adventure/'+encodeURIComponent(adventureName)});
    },
    getImg: function(reservation){
        var minNum = 10000;
        var imgPath = reservation.images[0].path;
        for(var i=0; i<reservation.images.length; i++){
            var num = Number(reservation.images[i].name.split(".")[0].substring(3));
            if(num < minNum){
                minNum = num;
                imgPath = reservation.images[i].path;
            }

        }
        return 'http://localhost:8180/api/entityImage/'+imgPath;
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
    convertDate: function(date){
        return new Date(date).toLocaleString();
    },
    getCssForLoyalty: function(user){
        if(user.loyalty.loyaltyType === 'GOLD') return 'goldCss';
        if(user.loyalty.loyaltyType === 'SILVER') return 'silverCss';
        if(user.loyalty.loyaltyType === 'REGULAR') return 'SettingsCss';
        return '';
    },
    selectClientView: function(reservation, frh){
        if(reservation.termType === 'FAST_RESERVATION'){
            reservation.clientView = frh.client
        }
    },
  }
}
</script>


<style scoped>
.silverCss{
    border-color: white;
    border-width: 2px;
    border-radius: 8px;
    background: rgb(81, 81, 81);
    background: linear-gradient(15deg, rgb(82, 82, 82) 0%, rgb(139, 139, 139) 23%, rgb(222, 222, 222) 100%);
    color: white;
}

.goldCss{
    border-color: white;
    border-width: 2px;
    border-radius: 8px;
    background: rgb(168, 129, 4);
    background: linear-gradient(15deg, rgb(190, 126, 0) 0%, rgb(202, 158, 10) 23%, rgb(238, 255, 0) 100%);
    color: black;
}

.SettingsCss{
    border-color: white;
    border-width: 2px;
    border-radius: 8px;
    background: rgb(36, 23, 0);
    background: linear-gradient(15deg, rgba(2,0,36,1) 0%, rgba(9,9,121,1) 23%, rgba(0,146,255,1) 100%);
    color: white;
}
</style>
