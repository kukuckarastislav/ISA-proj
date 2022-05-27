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

                         
                        </div>
                    </div>
                </div>
            </div>
             <div>
                    <div style="max-width:33.5%; margin: 0 auto;" class="input-group news-input">
                        <input style="margin-right:10%;margin-left:10%" v-model="searchedName" type="text" class="form-control" placeholder="Name" />
                        <button class="btn btn-dark btn-lg" type="button" v-on:click="preSearch()">Search</button>
                    </div>
                
                </div>

  <div v-if="button1">
  <table v-for="a in cottages"
            :key="a.id" style="border: 1px solid gray; width: 40%; margin-left:30%; margin-top: 5%;text-align:center" class="table">
    <tbody>
      <tr>
        <td style="border:none;width:40%;" rowspan="3"><img style="width:100%;" height="205" v-bind:src="'http://localhost:8180/api/entityImage/' + a.images[0].path" alt=""/></td>
        <td style="border:none;width:40%;text-align:left"><b style="zoom:1.6">{{a.name}}  <star-rating style="zoom:0.3;position: relative; top:-1.2em;;display: inline-block; margin-left:1em" v-model:rating="a.averageGrade" v-bind:show-rating=false v-bind:read-only=true /> </b>
        </td>
        <td style="border:none;width:25%;text-align:right"><button type="button" style="min-width:5.8em" class="btn btn-danger" v-on:click="unsubscribeFrom(a,'cottage')">Unsubscribe</button></td>
      </tr>
      <tr>
        
        <td style="border:none;text-align:left">{{a.description}}
        </td>
        <td  style="border:none;"></td>
        </tr>

      <tr>
          
        <td style="border:none;text-align:left">{{a.address.country}}, {{a.address.city}}, {{a.address.street}} {{a.address.number}}
        </td>
        <td style="border:none;color:red"></td>
        </tr>

    </tbody>
  </table>
  </div>

   <div v-if="button2">
  <table v-for="a in boats"
            :key="a.id" style="border: 1px solid gray; width: 40%; margin-left:30%; margin-top: 5%;text-align:center" class="table">
    <tbody>
      <tr>
        <td style="border:none;width:40%;" rowspan="3"><img style="width:100%;" height="205" v-bind:src="'http://localhost:8180/api/entityImage/' + a.images[0].path" alt=""/></td>
        <td style="border:none;width:40%;text-align:left"><b style="zoom:1.6">{{a.name}}  <star-rating style="zoom:0.3;position: relative; top:-1.2em;;display: inline-block; margin-left:1em" v-model:rating="a.averageGrade" v-bind:show-rating=false v-bind:read-only=true /> </b>
        </td>
        <td style="border:none;width:25%;text-align:right"><button type="button" style="min-width:5.8em" class="btn btn-danger" v-on:click="unsubscribeFrom(a,'boat')">Unsubscribe</button></td>
      </tr>
      <tr>
        
        <td style="border:none;text-align:left">{{a.description}}
        </td>
        <td  style="border:none;"></td>
        </tr>

      <tr>
          
        <td style="border:none;text-align:left">{{a.address.country}}, {{a.address.city}}, {{a.address.street}} {{a.address.number}}
        </td>
        <td style="border:none;color:red"></td>
        </tr>

    </tbody>
  </table>
  </div>

   <div v-if="button3">
  <table v-for="a in adventures"
            :key="a.id" style="border: 1px solid gray; width: 40%; margin-left:30%; margin-top: 5%;text-align:center" class="table">
    <tbody>
      <tr>
        <td style="border:none;width:40%;" rowspan="3"><img style="width:100%;" height="205" v-bind:src="'http://localhost:8180/api/entityImage/' + a.images[0].path" alt=""/></td>
        <td style="border:none;width:40%;text-align:left"><b style="zoom:1.6">{{a.name}}  <star-rating style="zoom:0.3;position: relative; top:-1.2em;;display: inline-block; margin-left:1em" v-model:rating="a.averageGrade" v-bind:show-rating=false v-bind:read-only=true /> </b>
        </td>
        <td style="border:none;width:25%;text-align:right"><button type="button" style="min-width:5.8em" class="btn btn-danger" v-on:click="unsubscribeFrom(a,'adventure')">Unsubscribe</button></td>
      </tr>
      <tr>
        
        <td style="border:none;text-align:left">{{a.description}}
        </td>
        <td  style="border:none;"></td>
        </tr>

      <tr>
          
        <td style="border:none;text-align:left">{{a.address.country}}, {{a.address.city}}, {{a.address.street}} {{a.address.number}}
        </td>
        <td style="border:none;color:red"></td>
        </tr>

    </tbody>
  </table>
  </div>
</div>

</template>

<script>
// @ is an alias to /src
import axios from "axios";
import StarRating from 'vue-star-rating'
export default {
  name: 'CustomerSubscriptions',
  components: {
      StarRating
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
      checked: false,
      isModalVisible: false,
      selectedEntity: {},
      entityType: '',
      isModalComplaintVisible: false,
    }
  },
  mounted() {
    axios.defaults.headers.common["Authorization"] =
                "Bearer " + window.sessionStorage.getItem("jwt");  
    axios.get('http://localhost:8180/api/client/getSubscriptionsAdventure')
			.then(response => {this.adventures = response.data
            axios.get('http://localhost:8180/api/client/getSubscriptionsCottage')
			.then(response => {this.cottages = response.data
            axios.get('http://localhost:8180/api/client/getSubscriptionsBoat')
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
        preSearch: function(){
            axios.defaults.headers.common["Authorization"] =
                "Bearer " + window.sessionStorage.getItem("jwt");  
            axios.get('http://localhost:8180/api/client/getSubscriptionsAdventure')
			.then(response => {this.adventures = response.data
            axios.get('http://localhost:8180/api/client/getSubscriptionsCottage')
			.then(response => {this.cottages = response.data
            axios.get('http://localhost:8180/api/client/getSubscriptionsBoat')
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
    				if(this.searchedName && !this.cottages[i].name.toLowerCase().includes(this.searchedName.toLowerCase())){
								this.cottages.splice(i,1);
								control=0;
								break;	
							}                                                  
            } 
            }
            }
            else if(this.button2){

                    var control=0
			while(control===0){
				control=1;
			for (var i = 0; i < this.boats.length; i++) {
    				if(this.searchedName && !this.boats[i].name.toLowerCase().includes(this.searchedName.toLowerCase())){
								this.boats.splice(i,1);
								control=0;
								break;	
							 } 
              
            }
            }

            }
            else if(this.button3){
                var control=0
			while(control===0){
				control=1;
			for (var i = 0; i < this.adventures.length; i++) {
    				if(this.searchedName && !this.adventures[i].name.toLowerCase().includes(this.searchedName.toLowerCase())){
								this.adventures.splice(i,1);
								control=0;
								break;	
							}                                           
            } 
            }
            }
        },
        unsubscribeFrom:function(selEntity, typeEnt){
          axios.defaults.headers.common["Authorization"] =
                "Bearer " + window.sessionStorage.getItem("jwt");  
            axios
          .delete('http://localhost:8180/api/client/unsubscribe',{data: {type:typeEnt, id:selEntity.id}})
          .then(response => {
            alert('Uspesno ste odjavili pretplatu.')
            window.location.reload()
          }).catch(err => {
              alert('Doslo je do greske.')
          });
        }
        
   }
}
</script>


<style scoped>



</style>