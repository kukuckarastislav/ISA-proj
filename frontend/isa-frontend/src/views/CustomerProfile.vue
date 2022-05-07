<template>
    <div style="height: 60px;"></div>
    <div class="container">
        
        <div class="container">
            <div class="row">
                <div class="col-sm-5">
                    
                    <div class="card">
                        <div class="card-body">
                            <label class="form-label">First name</label>
                            <input :disabled="!isEditingProfile" v-model="profile.firstName" type="text" placeholder="" class="form-control">
                            <br>
                            <label class="form-label">Last name</label>
                            <input :disabled="!isEditingProfile" v-model="profile.lastName" type="text" placeholder="" class="form-control">
                            <br>
                            <label class="form-label">Phone number</label>
                            <input :disabled="!isEditingProfile" v-model="profile.phoneNumber" type="text" placeholder="" class="form-control">
                            <br>
                            <label for="exampleInputEmail1" class="form-label">Email address</label>
                            <input disabled v-model="profile.email" type="email" placeholder="youremail@gmail.com" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
                            <br><br>

                            <label class="form-label">Address</label>
                            <div class="row justify-content-between">
                            <div class="col">
                                <input :disabled="!isEditingProfile" type="text" v-model="profile.address.country" class="form-control" placeholder="Country">
                                <br>
                                <input :disabled="!isEditingProfile" type="text" v-model="profile.address.city" class="form-control" placeholder="City">
                                <br>
                                <input :disabled="!isEditingProfile" type="text" v-model="profile.address.street" class="form-control" placeholder="street">
                            </div>
                            <div class="col">
                                <input :disabled="!isEditingProfile" type="text" v-model="profile.address.number" class="form-control" placeholder="number">
                                <br>
                                <input :disabled="!isEditingProfile" type="number" v-model="profile.address.latitude" class="form-control" placeholder="latitude">
                                <br>
                                <input :disabled="!isEditingProfile" type="number" v-model="profile.address.longitude" class="form-control" placeholder="longitude">
                            </div>
                            </div>

                            <br>
                            <div class="mapaTODO">OVDE CE BITI MAPA</div>
                            <br>

                            <div class="d-flex justify-content-center">
                                <button v-if="!isEditingProfile" v-on:click="editProfile()" class="btn btn-primary m-1">Edit Informations</button>
                                <button v-if="isEditingProfile" v-on:click="cancelEditProfile()" class="btn btn-danger m-1">Cancel</button>
                                <button v-if="isEditingProfile" v-on:click="saveEditProfile()" class="btn btn-primary m-1">Save</button>
                            </div>
                            

                        </div>
                    </div>
                    <br>

                    <!-- Izmena passworda -->
                    <div class="card">
                        <div class="card-body">
                            <div v-if="isEditingPassword">
                                <input v-model="passwordData.oldPassword" type="password" placeholder="Your old password" class="form-control"><br>
                                <input v-model="passwordData.newPassword" type="password" placeholder="Your new password" class="form-control"><br>
                                <input v-model="passwordData.newPasswordRepeated" type="password" placeholder="Repeat Your new password" class="form-control" v-bind:class="{ 'is-invalid': !newPassEqual() }"><br>
                            </div>
                    
                            <div class="d-flex justify-content-center">
                                <button v-if="!isEditingPassword" v-on:click="editPassword()" class="btn btn-primary m-1">Change password</button>
                                <button v-if="isEditingPassword" v-on:click="cancelEditPassword()" class="btn btn-danger m-1">Cancel</button>
                                <button v-if="isEditingPassword" v-on:click="saveEditPassword()" class="btn btn-primary m-1">Save</button>
                            </div>
                        </div>
                    </div>
                    <br> <br> <br>

                    <!-- Brisanje profila -->
                    <div class="card border border-danger">
                        <div class="card-body border border-danger">
                            <textarea v-if="isDeletingAcc" class="form-control mb-2" v-model="deleteRequestMessage" rows="4"></textarea>
                            <div class="d-flex justify-content-center">
                                <button v-if="!isDeletingAcc" v-on:click="deleteAcc()" class="btn btn-danger m-1">Delete My Account</button>
                                <button v-if="isDeletingAcc" v-on:click="cancelDeleteAcc()" class="btn btn-light m-1">Cancel</button>
                                <button v-if="isDeletingAcc" v-on:click="sendDeleteAccReq()" class="btn btn-danger m-1">Delete My Account</button>
                            </div>
                           
                        </div>
                    </div>
                    
                      



                </div>
                <div class="col-sm-2"></div>
                <div class="col-sm-5">
                    <!-- Velika slika tj ilustracija -->
                    <img src="../assets/undraw_certificate_re_yadi.svg" class="img-fluid" />
                    <br> <br> <br> 

                </div>
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
        isEditingProfile: false,
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
        copyProfile: {},

        
        isEditingPassword: false,
        passwordData:{
            oldPassword: '',
            newPassword: '',
            newPasswordRepeated: ''
        },

        isDeletingAcc: false,
        deleteRequestMessage: '',
    }
  },
  mounted: function(){
    
  },
  mounted: function(){
      this.loadInformation();
  },
  methods: {
      loadInformation: function(){
          axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
            axios.get('http://localhost:8180/api/client/profileInfo')
            .then(response => {this.profile=response.data;}).catch(err => {alert('DOSLO JE DO GRESKE')}); 
      },
      editProfile: function(){
          // Najednostavniji nacin da se uradi Deep-Copy
          this.copyProfile = JSON.parse(JSON.stringify(this.profile));
          this.isEditingProfile = true;
      },
      cancelEditProfile: function(){
          this.profile = this.copyProfile;
          this.isEditingProfile = false;
      },
      saveEditProfile: function(){
          this.isEditingProfile = false;
          axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
            axios.put('http://localhost:8180/api/client/updateProfile',this.profile).then(response => 
            {this.profile=response.data}).catch(err => {alert('DOSLO JE DO GRESKE')}); 
      },
      editPassword: function(){
          this.isEditingPassword = true;
      },
      cancelEditPassword: function(){
          this.passwordData.oldPassword = '';
          this.passwordData.newPassword = '';
          this.passwordData.newPasswordRepeated = '';
          this.isEditingPassword = false;
      },
      saveEditPassword: function(){
          this.isEditingPassword = false;
          axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
          axios.put('http://localhost:8180/api/client/updatePassword',this.passwordData)
          .then(response => {
                  if (response.data) {
                        window.sessionStorage.clear();
                        this.$router.push('/');
                        alert('Uspesno promenjena lozinka');
                  }
                  else 
                    alert('DOSLO JE DO GRESKE')
          }).catch(err => {
              alert('DOSLO JE DO GRESKE')
          }); 
      },
      newPassEqual: function(){
          return this.passwordData.newPassword === this.passwordData.newPasswordRepeated;
      },
      deleteAcc: function(){
          this.isDeletingAcc = true;
      },
      cancelDeleteAcc: function(){
          this.isDeletingAcc = false;
          this.deleteRequestMessage = '';
      },
      sendDeleteAccReq: function(){
          this.isDeletingAcc = false;
          axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
          axios.put('http://localhost:8180/api/client/deleteRequest',{reason: this.deleteRequestMessage, username: this.profile.email})
          .then(response => {
                  if (response) {
                        window.sessionStorage.clear();
                        this.$router.push('/');
                        alert('Uspesno poslat zahtev za brisanje');
                  }
                  else 
                    alert('DOSLO JE DO GRESKE')
          }).catch(err => {
              alert('DOSLO JE DO GRESKE')
          }); 
      }

  }
}
</script>


<style scoped>

.tabelaProfila{
    font-size: 24px;
}

.mapaTODO{
  
  width: 100%;
  height: 200px;
  background-color: dimgrey;
  border-radius: 10px;
}


</style>
