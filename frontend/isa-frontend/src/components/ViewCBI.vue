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
                        <input v-model="searchedName" type="text" class="form-control" placeholder="Name" />
                        <input v-model="searchedLocation" type="text" class="form-control" placeholder="Location" />
                        <input v-model="searchedAdvertiser" type="text" class="form-control" placeholder="Owner/Instructor" />
                        <button class="btn btn-dark btn-lg" type="button" v-on:click="preSearch()">Search</button>
                    </div>
                </div>
               <!-- <div class="form-check form-switch">
                    <label class="form-check-label isa-text-color-darkBlue" for="flexSwitchCheckDefault">Default switch checkbox input</label>
                    <input class="form-check-input" type="checkbox" id="flexSwitchCheckDefault">
                </div> -->
                <div style="text-align:left; background-color:white">
                <button style="background-color:white; border:none;" v-on:click="openFilters()"><img style="height:2em;"  src="../assets/filter.svg"></button>
                </div>
                <div v-if="showFilters" style="color:black; text-align:center">
                    <b>Sort by: </b>
                    <select class="form-select d-inline" aria-label="Default select example" style="width:7em; margin-left:10em" v-model="sortBy">
                    <option value="1">Name</option>
                    <option value="2">Address</option>
                    <option value="3">Grade</option>
                    </select>
                    <select class="form-select d-inline" aria-label="Default select example" style="width:9em; margin-left:10em" v-model="sortInOrder">
                    <option value="1">Ascending</option>
                    <option value="2">Descending</option>
                    </select>
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
      isFetching: true,
      showFilters: false,
      sortBy:1,
	  sortInOrder:1,
      searchedName: '',
      searchedLocation: '',
      searchedAdvertiser: ''
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
                this.preSearch();
        },
        button1Clicked : function(){
                this.button3 = false
                this.button1 = true
                this.button2 = false
                this.preSearch();
        },
        button2Clicked : function(){
                this.button3 = false
                this.button2 = true
                this.button1 = false
                this.preSearch();
        },
        showAdventure: function(IdNum){
            this.$router.push({ path: '/customerAdventurePage/'+IdNum});
        },
        showCottage: function(IdNum){
            this.$router.push({ path: '/customerCottagePage/'+IdNum});
        },
        showBoat: function(IdNum){
            this.$router.push({ path: '/customerBoatPage/'+IdNum});
        },
        openFilters: function(){
            this.showFilters = ! this.showFilters;
        },
        preSearch: function(){
            axios.get('http://localhost:8180/api/person/adventures')
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
        searchBy: function(){
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
				var loc=this.adventures[i].address.country+" "+this.adventures[i].address.city +" "+this.adventures[i].address.street +" "+this.adventures[i].address.number;
                var owr=this.adventures[i].instructor.firstName+" "+this.adventures[i].instructor.lastName 
    				if(this.searchedName && !this.adventures[i].name.toLowerCase().includes(this.searchedName.toLowerCase())){
								this.adventures.splice(i,1);
								control=0;
								break;	
									}
                    else if(this.searchedLocation && !loc.toLowerCase().includes(this.searchedLocation.toLowerCase().replace(',',''))){
								this.adventures.splice(i,1);
								control=0;
								break;	
									}
                    else if(this.searchedAdvertiser && !owr.toLowerCase().includes(this.searchedAdvertiser.toLowerCase())){
								this.adventures.splice(i,1);
								control=0;
								break;	
									}  
            }
            }


                     if (this.showFilters){

                    if(this.sortBy=="3" && this.sortInOrder=="1"){
                                        this.adventures.sort(function (a, b) {
                                              return a.averageGrade - b.averageGrade;
                                                });
                                    } else if(this.showFilters && this.sortBy=="3" && this.sortInOrder=="2"){
                                        this.adventures.sort(function (a, b) {
                                              return b.averageGrade - a.averageGrade;
                                                });
                                    }  else if(this.showFilters && this.sortBy==1 && this.sortInOrder==1){
                                        this.adventures.sort(function (a, b) {
                                              return a.name.replace(/\s+/g, '').localeCompare(b.name.replace(/\s+/g, ''))
                                                });
                                    } else if(this.showFilters && this.sortBy=="1" && this.sortInOrder=="2"){
                                        this.adventures.sort(function (a, b) {
                                              return b.name.replace(/\s+/g, '').localeCompare(a.name.replace(/\s+/g, ''))
                                                });
                                    }  else if(this.showFilters && this.sortBy==2 && this.sortInOrder==1){
                                        this.adventures.sort(function (a, b) {
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
                                        this.adventures.sort(function (a, b) {
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
