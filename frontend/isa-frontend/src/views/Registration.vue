<template>
    <div style="height: 100px;"></div>


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
            </div>

            <br>
            <div class="mapaTODO">OVDE CE BITI MAPA</div>
            <br>

            <select class="form-select" v-model="registration.typeOfUser">
              <option value="Customer">Customer</option>
              <option value="CottageOwner">Cottage Owner</option>
              <option value="BoatOwner">Boat Owner</option>
              <option value="Instructor">Instructor</option>
              <option value="Admin">Admin</option>
            </select>
            
            <div v-if="registration.typeOfUser != 'Customer'">
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
        phoneNumber: '011-111-111',
        address: {
          country: '',
          city: '',
          street: '',
          number: '',
          latitude: 0,
          longitude: 0
        },
        typeOfUser: 'Customer',
        registrationMotivation: ''
      },

      passwordConfirm: ''
    }
  },
  methods: {
    sendRegistration: function(){
      axios
          .post('http://localhost:8180/api/auth/registration',this.registration)
          .then(response => console.log(response))
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