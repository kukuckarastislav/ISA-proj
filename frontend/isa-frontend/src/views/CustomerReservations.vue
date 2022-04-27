<template>
<div v-if="!isFetching">
     
            <div style = "margin-bottom:1.4%;margin-top:1.5%" class="container">
                <div class="row">
                    <div class="d-md-flex align-items-center justify-content-center">
                        <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
                            <input type="radio" class="btn-check" name="btnradio" id="btnradio1" autocomplete="off" checked v-on:click="button1Clicked">
                            <label class="btn btn-outline-primary" for="btnradio1">Cotages</label>

                            <input type="radio" class="btn-check" name="btnradio" id="btnradio2" autocomplete="off" v-on:click="button2Clicked">
                            <label class="btn btn-outline-primary" for="btnradio2">Boats</label>

                            <input type="radio" class="btn-check" name="btnradio" id="btnradio3" autocomplete="off" v-on:click="button3Clicked">
                            <label class="btn btn-outline-primary" for="btnradio3">Adventures</label>

                            <input style="margin-left:4.2em;zoom:1.6" class="form-check-input" type="checkbox" v-model="checked" id="flexCheckChecked">
                            <label class="form-check-label" for="flexCheckChecked" style="margin-top:0.1em;margin-left:1em;color:#0d6efd;zoom:1.4">NOT PASSED ONLY</label>
                        </div>
                    </div>
                </div>
            </div>
       
  
                    <div>
                    <div style="max-width:33.5%; margin: 0 auto;" class="input-group news-input">
                      <button style="background-color:white; border:none;text-align:left" v-on:click="openFilters()"><img style="height:2em;"  src="../assets/filter.svg"></button>
                        <input style="margin-right:10%;margin-left:10%" v-model="searchedName" type="text" class="form-control" placeholder="Name" />
                        <button class="btn btn-dark btn-lg" type="button" v-on:click="preSearch()">Search</button>
                    </div>
                
                </div>
  <div v-if="showFilters" style="color:black; text-align:center;margin-top:1.4%">
                    <b>Sort by: </b>
                    <select class="form-select d-inline" aria-label="Default select example" style="width:7em; margin-left:10em" v-model="sortBy">
                    <option value="1">Date</option>
                    <option value="2">Price</option>
                    <option value="3">Duration</option>
                    </select>
                    <select class="form-select d-inline" aria-label="Default select example" style="width:9em; margin-left:10em" v-model="sortInOrder">
                    <option value="1">Ascending</option>
                    <option value="2">Descending</option>
                    </select>
                    </div>   
       
  <div v-if="button3">
  <table v-for="a in adventures"
            :key="a.startTime" style="border: 1px solid gray; width: 50%; margin-left:25%; margin-top: 5%;text-align:left" class="table">
    <tbody>
      <tr>
        <td style="border:none;width:30%;" rowspan="5"><img style="width:100%;" height="205" v-bind:src="'http://localhost:8180/' + a.imagePath" alt=""/></td>
        <td style="border:none;width:25%"><b>{{a.adventureName}}</b>
        </td>
        <td style="border:none;"></td>
        <td style="border:none;text-align:right;width:50%"></td>
      </tr>
      <tr>
        
        <td style="border:none;">Start date:
        </td>
        <td style="border:none;"></td>
        {{a.startTime}}
        <td style="border:none;"></td>
        </tr>
      <tr>
       
        <td style="border:none;">End date:
        </td>
        <td style="border:none;"></td>
        {{a.endTime}}        
        <td v-if="a.status==='CANCELLED'" style="border:none;padding-right:1em;color:red">CANCELLED</td>
        <td v-else-if="LessThanthreeDays(a)" style="border:none;padding-right:1em;color:red"></td>
        <td  v-else style="border:none;padding-right:1em"><button type="button" class="btn btn-danger" v-on:click="cancelAdventure(a)">Cancel</button></td>
        </tr>
       <tr>
        
        <td style="border:none;">Total price:
        </td>
        <td style="border:none;"></td>
        {{a.price}}$
        <td style="border:none;"></td>
        </tr>
        <tr>
       
        <td style="">Additional services:
        </td>
        <td style=""></td>
        <div style="display: inline-block; border: 1px solid #0d6efd;margin-right:1em;margin-bottom:0.5em" v-for="service in a.additionalServices" :key="service.id">
          {{service.name}}
          </div>
        <td style=""></td>
        </tr>
    </tbody>
  </table>
  </div>

  <div v-if="button1">
  <table v-for="a in cottages"
            :key="a.startTime" style="border: 1px solid gray; width: 50%; margin-left:25%; margin-top: 5%;text-align:left" class="table">
    <tbody>
      <tr>
        <td style="border:none;width:30%;" rowspan="5"><img style="width:100%;" height="205" v-bind:src="'http://localhost:8180/' + a.imagePath" alt=""/></td>
        <td style="border:none;width:25%"><b>{{a.cottageName}}</b>
        </td>
        <td style="border:none;"></td>
        <td style="border:none;text-align:right;width:50%"></td>
      </tr>
      <tr>
        
        <td style="border:none;">Start date:
        </td>
        <td style="border:none;"></td>
        {{a.startTime}}
        <td style="border:none;"></td>
        </tr>
      <tr>
       
        <td style="border:none;">End date:
        </td>
        <td style="border:none;"></td>
        {{a.endTime}}        
        <td v-if="a.status==='CANCELLED'" style="border:none;padding-right:1em;color:red">CANCELLED</td>
        <td v-else-if="LessThanthreeDays(a)" style="border:none;padding-right:1em;color:red"></td>
        <td  v-else style="border:none;padding-right:1em"><button type="button" class="btn btn-danger" v-on:click="cancelAdventure(a)">Cancel</button></td>
        </tr>
       <tr>
        
        <td style="border:none;">Total price:
        </td>
        <td style="border:none;"></td>
        {{a.price}}$
        <td style="border:none;"></td>
        </tr>
        <tr>
       
        <td style="">Additional services:
        </td>
        <td style=""></td>
        <div style="display: inline-block; border: 1px solid #0d6efd;margin-right:1em;margin-bottom:0.5em" v-for="service in a.additionalServices" :key="service.id">
          {{service.name}}
          </div>
        <td style=""></td>
        </tr>
    </tbody>
  </table>
  </div>
</div>

</template>

<script>
// @ is an alias to /src
import axios from "axios";
export default {
  name: 'CustomerReservations',
  components: {
    
  },
   data: function(){
    return {
      adventures: [],
      button1: true,
      button2: false,
      button3: false,
      cottages: [],
      boats: [],
      isFetching: true,
      showFilters: false,
      checked: false
    }
  },
  mounted() {
    axios.defaults.headers.common["Authorization"] =
                "Bearer " + window.sessionStorage.getItem("jwt");  
    axios.get('http://localhost:8180/api/client/getAdventures')
			.then(response => {this.adventures = response.data
            axios.get('http://localhost:8180/api/client/getCottages')
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
        openFilters: function(){
            this.showFilters = ! this.showFilters;
        },
                preSearch: function(){
            axios.defaults.headers.common["Authorization"] =
                "Bearer " + window.sessionStorage.getItem("jwt");  
            axios.get('http://localhost:8180/api/client/getAdventures')
			.then(response => {this.adventures = response.data
            axios.get('http://localhost:8180/api/person/cottages')
			.then(response => {this.cottages = response.data
            axios.get('http://localhost:8180/api/person/boats')
			.then(response => {this.boats = response.data
            this.isFetching = false
            this.searchBy()}).catch(err => (alert(err)));
            }).catch(err => (alert(err)));
            }).catch(err => (alert(err)));
        },
        searchBy:async function(){
            if(this.button1){

            var control=0
			while(control===0){
				control=1;
			for (var i = 0; i < this.cottages.length; i++) {
				var loc=this.cottages[i].address.country+" "+this.cottages[i].address.city +" "+this.cottages[i].address.street +" "+this.cottages[i].address.number;
                var owr=this.cottages[i].owner.firstName+" "+this.cottages[i].owner.lastName 
    				if(this.searchedName && !this.cottages[i].name.toLowerCase().includes(this.searchedName.toLowerCase())){
								this.cottages.splice(i,1);
								control=0;
								break;	
									}
                    else if(this.searchedLocation && !loc.toLowerCase().includes(this.searchedLocation.toLowerCase().replace(',',''))){
								this.cottages.splice(i,1);
								control=0;
								break;	
									}
                    else if(this.searchedAdvertiser && !owr.toLowerCase().includes(this.searchedAdvertiser.toLowerCase())){
								this.cottages.splice(i,1);
								control=0;
								break;	
									}  
            }
            }

                if (this.showFilters){

                    if(this.sortBy=="3" && this.sortInOrder=="1"){
										this.cottages.sort(function (a, b) {
											  return a.averageGrade - b.averageGrade;
												});
									} else if(this.showFilters && this.sortBy=="3" && this.sortInOrder=="2"){
										this.cottages.sort(function (a, b) {
											  return b.averageGrade - a.averageGrade;
												});
									}  else if(this.showFilters && this.sortBy==1 && this.sortInOrder==1){
										this.cottages.sort(function (a, b) {
											  return a.name.replace(/\s+/g, '').localeCompare(b.name.replace(/\s+/g, ''))
												});
									} else if(this.showFilters && this.sortBy=="1" && this.sortInOrder=="2"){
										this.cottages.sort(function (a, b) {
											  return b.name.replace(/\s+/g, '').localeCompare(a.name.replace(/\s+/g, ''))
												});
									}  else if(this.showFilters && this.sortBy==2 && this.sortInOrder==1){
										this.cottages.sort(function (a, b) {
                                            if(a.address.country.replace(/\s+/g, '').localeCompare(b.address.country.replace(/\s+/g, '')) == 0)
                                                {
                                                    if(a.address.city.replace(/\s+/g, '').localeCompare(b.address.city.replace(/\s+/g, ''))==0){
                                                        return a.address.street.replace(/\s+/g, '').localeCompare(b.address.street.replace(/\s+/g, ''))==0
                                                    }
                                                    return a.address.city.replace(/\s+/g, '').localeCompare(b.address.city.replace(/\s+/g, ''));
                                                }
											  return a.address.country.replace(/\s+/g, '').localeCompare(b.address.country.replace(/\s+/g, ''))
												});
									} else if(this.showFilters && this.sortBy=="2" && this.sortInOrder=="2"){
										this.cottages.sort(function (a, b) {
											if(b.address.country.replace(/\s+/g, '').localeCompare(a.address.country.replace(/\s+/g, '')) == 0)
                                                {
                                                    if(b.address.city.replace(/\s+/g, '').localeCompare(a.address.city.replace(/\s+/g, ''))==0){
                                                        return b.address.street.replace(/\s+/g, '').localeCompare(a.address.street.replace(/\s+/g, ''))==0
                                                    }
                                                    return b.address.city.replace(/\s+/g, '').localeCompare(a.address.city.replace(/\s+/g, ''));
                                                }
											  return b.address.country.replace(/\s+/g, '').localeCompare(a.address.country.replace(/\s+/g, ''))
												});
                                    }

                }
            }
            else if(this.button2){

                    var control=0
			while(control===0){
				control=1;
			for (var i = 0; i < this.boats.length; i++) {
				var loc=this.boats[i].address.country+" "+this.boats[i].address.city +" "+this.boats[i].address.street +" "+this.boats[i].address.number;
                var owr=this.boats[i].owner.firstName+" "+this.boats[i].owner.lastName 
    				if(this.searchedName && !this.boats[i].name.toLowerCase().includes(this.searchedName.toLowerCase())){
								this.boats.splice(i,1);
								control=0;
								break;	
									}
                    else if(this.searchedLocation && !loc.toLowerCase().includes(this.searchedLocation.toLowerCase().replace(',',''))){
								this.boats.splice(i,1);
								control=0;
								break;	
									}
                    else if(this.searchedAdvertiser && !owr.toLowerCase().includes(this.searchedAdvertiser.toLowerCase())){
								this.boats.splice(i,1);
								control=0;
								break;	
									}  
            }
            }


                     if (this.showFilters){

                    if(this.sortBy=="3" && this.sortInOrder=="1"){
                                        this.boats.sort(function (a, b) {
                                              return a.averageGrade - b.averageGrade;
                                                });
                                    } else if(this.showFilters && this.sortBy=="3" && this.sortInOrder=="2"){
                                        this.boats.sort(function (a, b) {
                                              return b.averageGrade - a.averageGrade;
                                                });
                                    }  else if(this.showFilters && this.sortBy==1 && this.sortInOrder==1){
                                        this.boats.sort(function (a, b) {
                                              return a.name.replace(/\s+/g, '').localeCompare(b.name.replace(/\s+/g, ''))
                                                });
                                    } else if(this.showFilters && this.sortBy=="1" && this.sortInOrder=="2"){
                                        this.boats.sort(function (a, b) {
                                              return b.name.replace(/\s+/g, '').localeCompare(a.name.replace(/\s+/g, ''))
                                                });
                                    }  else if(this.showFilters && this.sortBy==2 && this.sortInOrder==1){
                                        this.boats.sort(function (a, b) {
                                            if(a.address.country.replace(/\s+/g, '').localeCompare(b.address.country.replace(/\s+/g, '')) == 0)
                                                {
                                                    if(a.address.city.replace(/\s+/g, '').localeCompare(b.address.city.replace(/\s+/g, ''))==0){
                                                        return a.address.street.replace(/\s+/g, '').localeCompare(b.address.street.replace(/\s+/g, ''))==0
                                                    }
                                                    return a.address.city.replace(/\s+/g, '').localeCompare(b.address.city.replace(/\s+/g, ''));
                                                }
                                              return a.address.country.replace(/\s+/g, '').localeCompare(b.address.country.replace(/\s+/g, ''))
                                                });
                                    } else if(this.showFilters && this.sortBy=="2" && this.sortInOrder=="2"){
                                        this.boats.sort(function (a, b) {
                                            if(b.address.country.replace(/\s+/g, '').localeCompare(a.address.country.replace(/\s+/g, '')) == 0)
                                                {
                                                    if(b.address.city.replace(/\s+/g, '').localeCompare(a.address.city.replace(/\s+/g, ''))==0){
                                                        return b.address.street.replace(/\s+/g, '').localeCompare(a.address.street.replace(/\s+/g, ''))==0
                                                    }
                                                    return b.address.city.replace(/\s+/g, '').localeCompare(a.address.city.replace(/\s+/g, ''));
                                                }
                                              return b.address.country.replace(/\s+/g, '').localeCompare(a.address.country.replace(/\s+/g, ''))
                                                });
                                    }

                }
            }
            else if(this.button3){
                var control=0
			while(control===0){
				control=1;
			for (var i = 0; i < this.adventures.length; i++) {
    				if(this.searchedName && !this.adventures[i].adventureName.toLowerCase().includes(this.searchedName.toLowerCase())){
								this.adventures.splice(i,1);
								control=0;
								break;	
							} else if(this.checked && (this.adventures[i].status==='CANCELLED' || ((new Date(this.adventures[i].endTime)) - (new Date()) < 0))){
								this.adventures.splice(i,1);
								control=0;
								break;	
									}                                                    
            } 
            }


                     if (this.showFilters){

                    if(this.sortBy=="3" && this.sortInOrder=="1"){
                                        this.adventures.sort(function (a, b) {
                                              return Math.abs((new Date(a.endTime)) - (new Date(a.startTime))) - Math.abs((new Date(b.endTime)) - (new Date(b.startTime)));
                                                });
                                    } else if(this.showFilters && this.sortBy=="3" && this.sortInOrder=="2"){
                                        this.adventures.sort(function (a, b) {
                                              return Math.abs((new Date(b.endTime)) - (new Date(b.startTime))) - Math.abs((new Date(a.endTime)) - (new Date(a.startTime)));
                                                });
                                    }  else if(this.showFilters && this.sortBy==1 && this.sortInOrder==1){
                                        this.adventures.sort(function (a, b) {
                                            console.log(new Date(a.startTime))
                                              return (new Date(a.startTime))-(new Date(b.startTime));
                                                });
                                    } else if(this.showFilters && this.sortBy=="1" && this.sortInOrder=="2"){
                                        this.adventures.sort(function (a, b) {
                                              return -(new Date(a.startTime))-(new Date(b.startTime));
                                                });
                                    } else if(this.showFilters && this.sortBy=="2" && this.sortInOrder=="2"){
                                        this.adventures.sort(function (a, b) {
                                              return b.price - a.price;
                                                });
                                    }else if(this.showFilters && this.sortBy=="2" && this.sortInOrder=="1"){
                                        this.adventures.sort(function (a, b) {
                                              return a.price - b.price;
                                                });
                                    }

                }
            }
        },
        LessThanthreeDays:function (adv) {
            var difference = (new Date(adv.startTime))-new Date();
            var dayDiff = difference / (1000 * 60 * 60 * 24)
            console.log(dayDiff)
            if(dayDiff < 3) return true;
            return false;
        },
        cancelAdventure:function(adv){
          axios.defaults.headers.common["Authorization"] =
                "Bearer " + window.sessionStorage.getItem("jwt");  
     axios
          .put('http://localhost:8180/api/client/cancelAdventureReservation',adv)
          .then(response => {
            adv.status='CANCELLED';
            alert('Uspesno ste otkazali avanturu.')
          }).catch(err => {
              alert('DOSLO JE DO GRESKE')
          }); 
        }
   }
}
</script>


<style scoped>



</style>