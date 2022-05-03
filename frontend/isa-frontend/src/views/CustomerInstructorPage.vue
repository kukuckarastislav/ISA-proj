<template>

    <div v-if="!isFetching">
        <br>
       <h1> {{instructor.firstName}} {{instructor.lastName}} </h1>
        Biography: {{instructor.adventures[0].biography}} <br>
        Address: {{address.country}}, {{address.city}}, {{address.street}} {{address.number}}<br>
        Phone number: {{instructor.phoneNumber}}<br>
        Grade:
           <span v-if="instructor.averageGrade===0">Not yet rated</span>  
           <span v-else>{{instructor.averageGrade}}</span><br>
        <br><br>
        <h2>Adventures</h2><br>
        <div style="margin-bottom:5%" v-for="a in instructor.adventures" :key="a">
            <h4>{{a.name}}</h4>
            <br>
            <span style="margin-left:5%" v-for="i in a.images" :key="i">
                <img  width="250" height = "150" v-bind:src="'http://localhost:8180/api/entityImage/' + i.path">
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
           Reservation cancellation conditions: {{a.reservationCancellationConditions}}   <br>
           
        </div> 

    </div>

</template>

<script>
import axios from "axios";
export default {
  name: "CustomerInstructorPage",
  components: {},
  data: function () {
    return {
      instructor: {},
      address: {},
      instructorId: 0,
      isFetching: true
    };
  },
  mounted: function () {
    this.instructorId = decodeURI(window.location.pathname.split("/")[2]);
    
    axios
      .get("http://localhost:8180/api/person/instructors/" + this.instructorId)
      .then((response) => {
        this.isFetching = false
        this.instructor = response.data;
        this.address = this.instructor.address;
      })
      .catch((err) => alert(err));
  },
  methods: {
    setImg: function (image) {
      return "http://localhost:8180/api/entityImage/" + image.path;
    },
  },
};
</script>


<style scoped>
</style>