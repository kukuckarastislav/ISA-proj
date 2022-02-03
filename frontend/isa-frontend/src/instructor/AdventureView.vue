<template>
    <div style="height: 60px;"></div>
    <div class="container">
        <div class="row">
            <div class="col-sm-6">

            <CarouselView v-bind:images="adventure.images"></CarouselView>

            </div>
            <div class="col-sm-6">
                 Detaljnije opis
            </div>
        </div>
    </div>

</template>

<script>
import axios from "axios";
import CarouselView from '@/components/CarouselView.vue'
export default {
  name: 'AdventureView',
  components: {
    CarouselView
  },
  data: function(){
    return {
        adventureName: "",
        adventure: {}
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
            this.adventure = resp.data;
            console.log(resp.data);
        });
    },
    setImg: function(image){
      return 'http://localhost:8180/'+image.path;
    },
  }
}
</script>


<style scoped>



</style>
