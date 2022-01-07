<template>
    <div style="height: 60px;"></div>
    <div class="container">
        <div class="row">
            <div class="col-sm-3">
                aa
            </div>
            <div class="col-sm-9">
                coool
            </div>
        </div>
    </div>

</template>

<script>
import axios from "axios";
export default {
  name: 'AdventureView',
  components: {
    
  },
  data: function(){
    return {
        adventureName: ""
    }
  },
  mounted: function(){
    this.adventureName = decodeURI(window.location.pathname.split('/')[2]);
    this.loadData();
  },
  methods: {
    loadData: function(){
       axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
        axios.get('http://localhost:8180/api/adventure/byinstructor/'+encodeURIComponent(this.adventureName)).then(resp => {
            this.adventures = resp.data;
               this.sortAdventures();
            console.log(resp.data);
        });
    }
  }
}
</script>


<style scoped>



</style>
