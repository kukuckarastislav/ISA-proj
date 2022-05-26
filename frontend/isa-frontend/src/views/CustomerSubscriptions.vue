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
</div>

</template>

<script>
// @ is an alias to /src
import axios from "axios";

export default {
  name: 'CustomerSubscriptions',
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
      isModalComplaintVisible: false
    }
  },
  mounted() {
    axios.defaults.headers.common["Authorization"] =
                "Bearer " + window.sessionStorage.getItem("jwt");  
    axios.get('http://localhost:8180/api/client/getAdventures')
			.then(response => {this.adventures = response.data
            axios.get('http://localhost:8180/api/client/getCottages')
			.then(response => {this.cottages = response.data
            axios.get('http://localhost:8180/api/client/getBoats')
			.then(response => {this.boats = response.data
            this.isFetching = false}).catch(err => (alert(err)));
            }).catch(err => (alert(err)));
            }).catch(err => (alert(err)));
    
     
      
  },
   methods: {
      
        
   }
}
</script>


<style scoped>



</style>