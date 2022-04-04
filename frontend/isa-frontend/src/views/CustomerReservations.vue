<template>
<div v-if="!isFetching">
  <div>
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
  </div>
  <table  style="border: 1px solid gray; width: 50%; margin-left:25%; margin-top: 5%;" class="table">
    <tbody>
      <tr v-for="a in adventures"
            :key="a.startTime">
        <td>Image</td>
        <td>{{a.adventureName}}</td>
        <td></td>
        <td>Cancel</td>
      </tr>
    </tbody>
  </table>
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
      isFetching: true
    }
  },
  mounted() {
    axios.defaults.headers.common["Authorization"] =
                "Bearer " + window.sessionStorage.getItem("jwt");  
    axios.get('http://localhost:8180/api/client/getAdventures')
			.then(response => {this.adventures = response.data
            axios.get('http://localhost:8180/api/person/cottages')
			.then(response => {this.cottages = response.data
            axios.get('http://localhost:8180/api/person/boats')
			.then(response => {this.boats = response.data
            this.isFetching = false}).catch(err => (alert(err)));
            }).catch(err => (alert(err)));
            }).catch(err => (alert(err)));
    
     
      
  }
}
</script>


<style scoped>
td {
  text-align: left;
}


</style>