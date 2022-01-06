<template>

    <div class="container">
      <div class="row align-items-center justify-content-between">
        <div class="col p-5 align-items-center">
          <h2>Profile</h2>
            <br>
            <label v-if="!isEditingPassword" class="form-label">First name:</label>
            <label v-if="isEditingPassword" class="form-label">Current password:</label>
            <input style="text-align:center" v-if="isEditing && !isEditingPassword" v-model="profile.firstName" type="text" placeholder="" class="form-control">
            <span style = "cursor: default;" v-if="isViewing && !isEditingPassword" class="form-control">{{profile.firstName}}</span>
            <input style="text-align:center" v-if="isEditingPassword" v-model="passwordData.oldPassword" type="password" placeholder="" class="form-control">
            <br>
            <label v-if="!isEditingPassword" class="form-label">Last name</label>
            <label v-if="isEditingPassword" class="form-label">New password:</label>
            <input style="text-align:center" v-if="isEditing  && !isEditingPassword" v-model="profile.lastName" type="text" placeholder="" class="form-control">
            <span style = "cursor: default;" v-if="isViewing  && !isEditingPassword" class="form-control">{{profile.lastName}}</span>
            <input style="text-align:center" v-if="isEditingPassword" v-model="passwordData.newPassword" type="password" placeholder="" class="form-control">
            <br>
            <label v-if="!isEditingPassword" class="form-label">Phone number</label>
            <label v-if="isEditingPassword" class="form-label">New password repeated:</label>
            <input style="text-align:center" v-if="isEditing  && !isEditingPassword" v-model="profile.phoneNumber" type="text" placeholder="" class="form-control">
            <span style = "cursor: default;" v-if="isViewing  && !isEditingPassword" class="form-control">{{profile.phoneNumber}}</span>
             <input style="text-align:center" v-if="isEditingPassword" v-model="passwordData.newPasswordRepeated" type="password" placeholder="" class="form-control">
            <br>
            <label v-if="!isEditingPassword" for="exampleInputEmail1" class="form-label">Email address</label>
            <span v-if="!isEditingPassword" style = "cursor: default;"  class="form-control">{{profile.email}}</span>
            <br><br>

            <label v-if="!isEditingPassword" class="form-label">Address</label>
            <div class="row justify-content-between">
              <div class="col">
                 <label v-if="!isEditingPassword" class="form-label">Country</label>
                <input style="text-align:center" type="text" v-if="isEditing  && !isEditingPassword" v-model="profile.address.country" class="form-control" placeholder="Country">
                <span style = "cursor: default;" v-if="isViewing  && !isEditingPassword" class="form-control">{{profile.address.country}}</span>
                <br>
                <label v-if="!isEditingPassword" class="form-label">City</label>
                <input style="text-align:center" type="text" v-if="isEditing  && !isEditingPassword" v-model="profile.address.city" class="form-control" placeholder="City">
                <span style = "cursor: default;" v-if="isViewing  && !isEditingPassword" class="form-control">{{profile.address.city}}</span>
                <br>
                <label v-if="!isEditingPassword" class="form-label">Street</label>
                <input style="text-align:center" type="text" v-if="isEditing  && !isEditingPassword" v-model="profile.address.street" class="form-control" placeholder="street">
                <span style = "cursor: default;" v-if="isViewing  && !isEditingPassword" class="form-control">{{profile.address.street}}</span>
              </div>
              <div class="col">
                <label v-if="!isEditingPassword" class="form-label">Number</label>
                <input style="text-align:center" type="text" v-if="isEditing  && !isEditingPassword" v-model="profile.address.number" class="form-control" placeholder="number">
                <span style = "cursor: default;" v-if="isViewing  && !isEditingPassword" class="form-control">{{profile.address.number}}</span>
                <br>
                <label v-if="!isEditingPassword" class="form-label">Latitude</label>
                <input style="text-align:center" type="number" v-if="isEditing  && !isEditingPassword" v-model="profile.address.latitude" class="form-control" placeholder="latitude">
                <span style = "cursor: default;" v-if="isViewing  && !isEditingPassword" class="form-control">{{profile.address.latitude}}</span>
                <br>
                <label v-if="!isEditingPassword" class="form-label">Longitude</label>
                <input style="text-align:center" type="number" v-if="isEditing  && !isEditingPassword" v-model="profile.address.longitude" class="form-control" placeholder="longitude">
                <span style = "cursor: default;" v-if="isViewing  && !isEditingPassword" class="form-control">{{profile.address.longitude}}</span>
              </div>
            </div>

           

          
            
            

          

            <br><br><br>
            <button v-if="isViewing && !isEditingPassword" type="button" v-on:click="changeProfile" class="btn btn-primary">Change profile</button>
            <button v-if="isEditing && !isEditingPassword" type="button" v-on:click="saveChanges" class="btn btn-primary">Save changes</button>
            <button v-if="isEditingPassword" type="button" v-on:click="savePasswordChanges" class="btn btn-primary">Save changes</button>
            <br><br><br>
            <button v-if="isViewing && !isEditingPassword" type="button" v-on:click="changePassword" class="btn btn-primary">Change password</button>
            <button v-if="isEditing && !isEditingPassword" type="button" v-on:click="discardChanges" class="btn btn-primary">Discard changes</button>
            <button v-if="isEditingPassword" type="button" v-on:click="discardPasswordChanges" class="btn btn-primary">Discard changes</button>
            

        </div>
        
      </div>
    </div>

</template>

<script>
import axios from "axios";
export default {
  name: 'CustomerProfile',
  components: {
    
  },
  data: function(){
    return {
     profile: {
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
      },
      isEditing: false,
			isViewing: true,
      isEditingPassword: false,
      passwordData:{
          oldPassword: '',
          newPassword: '',
          newPasswordRepeated: ''
      },
      loginData: {
        username: "",
        password: "",
      }
     
    }
  },
  methods: {
     changeProfile: function(){
       this.isEditing = true;
       this.isViewing = false;
    },
    discardChanges: function(){
      axios.defaults.headers.common["Authorization"] =
                "Bearer " + window.sessionStorage.getItem("jwt");  
     axios
          .get('http://localhost:8180/api/client/profileInfo',this.myConfig)
          .then(response => {this.profile=response.data
                  this.isViewing = true;
                  this.isEditing = false;
          }).catch(err => {
              alert('DOSLO JE DO GRESKE')
          }); 
      
    },
    saveChanges: function(){
      axios.defaults.headers.common["Authorization"] =
                "Bearer " + window.sessionStorage.getItem("jwt");  
     axios
          .put('http://localhost:8180/api/client/updateProfile',this.profile,this.myConfig)
          .then(response => {this.profile=response.data
                  this.isViewing = true;
                  this.isEditing = false;
          }).catch(err => {
              alert('DOSLO JE DO GRESKE')
          }); 
      
    },
    changePassword : function(){
      this.isEditingPassword = true;
    },
    discardPasswordChanges: function(){
      this.passwordData.newPassword = '';
      this.passwordData.oldPassword = '';
      this.passwordData.newPasswordRepeated = '';
      this.isEditingPassword = false;
    },
    savePasswordChanges: function(){
      axios.defaults.headers.common["Authorization"] =
                "Bearer " + window.sessionStorage.getItem("jwt");  
     axios
          .put('http://localhost:8180/api/client/updatePassword',this.passwordData,this.myConfig)
          .then(response => {
                  if (response) {
                  /*this.loginData.username = this.profile.email;
                  this.loginData.password = this.passwordData.newPassword;
                  this.profile.password = this.passwordData.newPassword;
                    axios
                        .post("http://localhost:8180/auth/login", this.loginData)
                        .then((response) =>{
                          window.sessionStorage.setItem("jwt", response.data.accessToken)
                          window.sessionStorage.setItem("role", response.data.role)

                          alert('Uspesno promenjena lozinka')
                        });*/
                        window.sessionStorage.clear();
                        this.$router.push('/');
                  alert('Uspesno promenjena lozinka');
                  }
                  else alert('DOSLO JE DO GRESKE')
                  this.isEditingPassword = false;
          }).catch(err => {
              alert('DOSLO JE DO GRESKE')
          }); 
    }
  },
  mounted: function(){
      axios.defaults.headers.common["Authorization"] =
                "Bearer " + window.sessionStorage.getItem("jwt");  
     axios
          .get('http://localhost:8180/api/client/profileInfo',this.myConfig)
          .then(response => this.profile=response.data).catch(err => {
              alert('DOSLO JE DO GRESKE')
          });    
  }
}
</script>


<style scoped>


</style>