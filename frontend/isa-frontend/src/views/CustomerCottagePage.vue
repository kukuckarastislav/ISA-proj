<template>

    <div v-if="!isFetching">
        <br>
       <h1> {{cottage.name}} </h1>
       <span  v-for="i in cottage.images" :key="i">
                <img  width="350" height = "250" v-bind:src="'http://localhost:8180/' + i.path">
                </span>
                <br><br><br>
        
        Address: {{cottage.address.country}}, {{cottage.address.city}}, {{cottage.address.street}} {{cottage.address.number}}<br>
        
        Grade:
           <span v-if="cottage.averageGrade===0">Not yet rated</span>  
           <span v-else>{{cottage.averageGrade}}</span><br>
       
           Description: {{cottage.description}}<br>
           Price: {{cottage.price.price}}$ {{cottage.price.priceType}} <br>
         
           <!--Max number of people: {{a.maxNumberOfPeople}}<br> -->
           Behaviour rules: {{cottage.behaviourRules}} <br>
           Additional services: <br>
           <div v-for="prl in cottage.additionalServices" :key="prl">
                {{prl.name}} {{prl.price}}$ - {{prl.description}} 
            </div>
            
           
        
      
    </div>

</template>

<script>
import axios from "axios";
export default {
  name: "CustomerCottagePage",
  components: {},
  data: function () {
    return {
      cottage: {},
      cottageId: 0,
      isFetching: true
    };
  },
  mounted: function () {
    this.cottageId = decodeURI(window.location.pathname.split("/")[2]);
    
    axios
      .get("http://localhost:8180/api/person/cottages/" + this.cottageId)
      .then((response) => {
        this.isFetching = false
        this.cottage = response.data;
      })
      .catch((err) => alert(err));
  },
  methods: {
    
  },
};
</script>


<style scoped>
</style>