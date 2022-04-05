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
        <td style="border:none;padding-right:1em"><button type="button" class="btn btn-danger">Cancel</button></td>
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



</style>