<template>
<div v-if="!isFetching">
    <h4 style="margin-top:2%;color:red">* If you have 3 or more penalties, you'll be prevented from making new reservations</h4>
     <div style="max-width:30%; text-align: center;margin: auto; margin-top:2% ">

 <div  style="margin-bottom:79.8%" v-if="penalties.length > 0">        
     <table class="table">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Reason</th>
    </tr>
  </thead>
   <tbody>
   <tr v-for="(p,ind) in penalties" :key="p.id">
       <td>{{ind+1}}</td>
      <td>{{p.text}}</td>
     </tr>
  </tbody>
  </table><br>
  </div>
<div style="margin-bottom:94.5%" v-else>
    <h2> You currently have no penalties. </h2>
</div>
  </div>
</div>

</template>

<script>
// @ is an alias to /src
import axios from "axios";
export default {
  name: 'CustomerPenalties',
   data: function(){
    return {
      penalties: [],
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
    axios.get('http://localhost:8180/api/client/getPenalties')
			.then(response => {this.isFetching = false;
                this.penalties = response.data;
            }).catch(err => (alert(err)));
    
     
      
  },
   methods: {
 
      

        
   }
}
</script>


<style scoped>



</style>