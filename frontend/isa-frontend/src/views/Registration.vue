<template>


    <div class="container">
      <div class="row align-items-center justify-content-between">
        <div class="col p-5 align-items-center">
          <h2>We are glad for your Registration</h2>
            <br>
            <label class="form-label">First name</label>
            <input v-model="registration.firstName" type="text" placeholder="" class="form-control">
            <br>
            <label class="form-label">Last name</label>
            <input v-model="registration.lastName" type="text" placeholder="" class="form-control">
            <br>
            <label class="form-label">Phone number</label>
            <input v-model="registration.phoneNumber" type="text" placeholder="" class="form-control">
            <br>
            <label for="exampleInputEmail1" class="form-label">Email address</label>
            <input v-model="registration.email" type="email" placeholder="youremail@gmail.com" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
            <br><br>

            <label class="form-label">Address</label>
            <div class="row justify-content-between">
              <div class="col">
                <input type="text" v-model="registration.address.country" class="form-control" placeholder="Country">
                <br>
                <input type="text" v-model="registration.address.city" class="form-control" placeholder="City">
                <br>
                <input type="text" v-model="registration.address.street" class="form-control" placeholder="street">
              </div>
              <div class="col">
                <input type="text" v-model="registration.address.number" class="form-control" placeholder="number">
                <br>
                <input type="number" v-model="registration.address.latitude" class="form-control" placeholder="latitude">
                <br>
                <input type="number" v-model="registration.address.longitude" class="form-control" placeholder="longitude">
              </div>
            </div> <br>

            <select class="form-select" v-model="registration.typeOfUser">
              <option value="ROLE_CUSTOMER">Customer</option>
              <option value="ROLE_COTTAGE_OWNER">Cottage Owner</option>
              <option value="ROLE_BOAT_OWNER">Boat Owner</option>
              <option value="ROLE_INSTRUCTOR">Instructor</option>
              <!-- <option value="ROLE_ADMIN">Admin</option> -->
            </select>
            
            <div v-if="registration.typeOfUser != 'ROLE_CUSTOMER'">
              <br>
              <label for="exampleFormControlTextarea1" class="form-label">Tell us your Motivation</label>
              <textarea class="form-control" v-model="registration.registrationMotivation" id="exampleFormControlTextarea1" rows="3"></textarea>
            </div>


            <br>   
            <label for="exampleInputPassword1" class="form-label">Password</label>
            <input type="password" v-model="registration.password" class="form-control" id="exampleInputPassword1">
            <br>  
            <label for="exampleInputPassword2" class="form-label">Confirm Password</label>
            <input type="password" v-bind:class="{ 'is-invalid' : registration.password != passwordConfirm }" v-model="passwordConfirm" class="form-control" id="exampleInputPassword2">

            <br><br><br>
            <button type="button" v-on:click="sendRegistration" class="btn btn-primary">Register</button>
            

        </div>
        <div class="col">
         <img src="../assets/undraw_secure_login_pdn4.svg" class="img-fluid" alt="" /> 
        </div>
      </div>
    </div>


</template>

<script>
import axios from "axios";
export default {
  name: 'Registration',
  components: {
    
  },
  data: function(){
    return {
      registration: {
        email: '',
        password: '',
        firstName: '',
        lastName: '',
        phoneNumber: '',
        address: {
          country: '',
          city: '',
          street: '',
          number: '',
          latitude: 0,
          longitude: 0
        },
        typeOfUser: 'ROLE_CUSTOMER',
        registrationMotivation: ''
      },

      passwordConfirm: ''
    }
  },
  methods: {
    validate: function(){
      if (this.registration.email === '') return false;
      if (this.registration.password === '') return false;
      if (this.registration.firstName === '') return false;
      if (this.registration.lastName === '') return false;
      if (this.registration.phoneNumber === '') return false;
      if (this.registration.address.country === '') return false;
      if (this.registration.address.city === '') return false;
      if (this.registration.address.street === '') return false;
      if (this.registration.address.latitude === '') return false;
      if (this.registration.address.longitude === '') return false;
      
      return true;
    },
    sendRegistration: function(){
      if(!this.validate()){
        alert('Niste popunili sva polja');
        return;
      }
     axios
          .post('http://localhost:8180/auth/signup',this.registration)
          .then(response => {
            alert("You've successfully registred.")
            this.$router.push('/');
          }).catch(err => {
              alert('DOSLO JE DO GRESKE')
          });
    }
  }
}
</script>


<style scoped>

.mapaTODO{
  
  width: 100%;
  height: 200px;
  background-color: dimgrey;
  border-radius: 10px;
}


</style>
