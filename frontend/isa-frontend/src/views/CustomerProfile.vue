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
                


                <div class="row" style="margin-top: 30px;">
                        <div class="col">
                            <div class="card" v-bind:class="getCssForLoyalty()">
                                <div class="card-body">
                                    <div v-if="loyaltyDiscount!=0" style="float:right;margin-top:2em">
                                        You have a discount of {{loyaltyDiscount}} percent.
                                    </div>
                                    <h5 class="card-title text-start">Loyalty</h5> 
                                    <h6  class="card-title text-start"><b>{{profile.loyalty.loyaltyType}}</b></h6> 
                                    <h6 class="card-title text-start">Score {{profile.loyalty.score}}</h6> 
                                </div>
                            </div>
                        </div>
                    </div>
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
            loyalty: {},
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
        loyaltyDiscount: 0
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
            .then(response => {this.profile=response.data;
                axios.get('http://localhost:8180/api/client/getLoyaltyDiscount')
            .then(response => {this.loyaltyDiscount=response.data;}).catch(err => {alert('DOSLO JE DO GRESKE')}); 
            }).catch(err => {alert('DOSLO JE DO GRESKE')}); 
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
      },
      getCssForLoyalty: function(){
        if(this.profile.loyalty.loyaltyType === 'GOLD') return 'goldCss';
        if(this.profile.loyalty.loyaltyType === 'SILVER') return 'silverCss';
        if(this.profile.loyalty.loyaltyType === 'REGULAR') return 'SettingsCss';
        return '';
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

.silverCss{
    border-color: white;
    border-width: 2px;
    border-radius: 8px;
    background: rgb(81, 81, 81);
    background: linear-gradient(15deg, rgb(82, 82, 82) 0%, rgb(139, 139, 139) 23%, rgb(222, 222, 222) 100%);
    color: white;
}

.goldCss{
    border-color: white;
    border-width: 2px;
    border-radius: 8px;
    background: rgb(168, 129, 4);
    background: linear-gradient(15deg, rgb(190, 126, 0) 0%, rgb(202, 158, 10) 23%, rgb(238, 255, 0) 100%);
    color: black;
}

.SettingsCss{
    border-color: white;
    border-width: 2px;
    border-radius: 8px;
    background: rgb(36, 23, 0);
    background: linear-gradient(15deg, rgba(2,0,36,1) 0%, rgba(9,9,121,1) 23%, rgba(0,146,255,1) 100%);
    color: white;
}


</style>
