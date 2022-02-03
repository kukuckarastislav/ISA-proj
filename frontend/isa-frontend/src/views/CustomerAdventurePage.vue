<template>

    <div v-if="!isFetching">
        <br>      
       <h1>{{a.name}}</h1>
            <br>
            <span style="margin-left:5%" v-for="i in a.images" :key="i">
                <img  width="250" height = "150" v-bind:src="'http://localhost:8180/' + i.path">
                </span>
                <br><br><br>
           Description: {{a.description}}<br>
           Price: {{a.price.price}}$ {{a.price.priceType}} <br>
           Address : {{a.address.country}}, {{a.address.city}}, {{a.address.street}} {{a.address.number}}<br>
           Max number of people: {{a.maxNumberOfPeople}}<br>
           Behaviour rules: {{a.behaviourRules}} <br>
           Additional services: <br>
           <div v-for="prl in a.pricelist" :key="prl">
                {{prl.name}} {{prl.price}}$ - {{prl.description}} 
            </div>
            Additional equipments: <br>
           <div v-for="ae in a.additionalEquipments" :key="ae">
                {{ae.name}}  
            </div>
           Reservation cancellation conditions: {{a.reservationCancellationConditions}}   <br><br>
        <h2> {{instructor.firstName}} {{instructor.lastName}} </h2>
        Biography: {{a.biography}} <br>
        Phone number: {{instructor.phoneNumber}}<br>
        <br><br> 

    </div>

</template>

<script>
import axios from "axios";
export default {
  name: "CustomerAdventurePage",
  components: {},
  data: function () {
    return {
      a: {},
      instructor: null,
      adventureId: 0,
      isFetching: true
    };
  },
  mounted: function () {
    this.adventureId = decodeURI(window.location.pathname.split("/")[2]);
    
    axios
      .get("http://localhost:8180/api/person/adventures/" + this.adventureId)
      .then((response) => {
        this.isFetching = false
        this.a = response.data;
        this.instructor = response.data.instructor;
      })
      .catch((err) => alert(err));
  },
  methods: {
    setImg: function (image) {
      return "http://localhost:8180/" + image.path;
    },
  },
};
</script>


<style scoped>
</style>