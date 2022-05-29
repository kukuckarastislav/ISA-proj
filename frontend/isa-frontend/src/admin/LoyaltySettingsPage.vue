<template>
    <div style="height: 60px;"></div>
    <div class="container">

        <div class="row">
            <div class="col">
                 <div class="card SettingsCss">
                    <div class="card-body">
                        <h5 class="card-title">Settings</h5>
                        <div class="row">
                            <div class="col-3"></div>
                            <div class="col-6">
                                <table style="text-align: left; width: 100%;">
                                    <tr>
                                        <td><p class="card-text">Client Score for Reservation</p></td>
                                        <td><input style="width: 50%;" :disabled="!isEditingData" type="number" v-model="loyaltySettings.clientScoreForReservation" class="form-control" placeholder="number" min="0"></td>
                                    </tr>
                                    <tr>
                                        <td><p class="card-text">Owner Score for Reservation</p></td>
                                        <td><input style="width: 50%;" :disabled="!isEditingData" type="number" v-model="loyaltySettings.ownerScoreForReservation" class="form-control" placeholder="number" min="0"></td>
                                    </tr>
                                    <tr>
                                        <td><p class="card-text">System Percentage</p></td>
                                        <td><input style="width: 50%;" :disabled="!isEditingData" type="number" v-model="loyaltySettings.systemPercentage" class="form-control" placeholder="number" min="0"></td>
                                    </tr>
                                </table>
                            </div>
                            <div class="col-3"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="row mt-4">
            <div class="col-6">
                <div class="card silverCss">
                    <div class="card-body">
                        <h5 class="card-title silverText">SILVER</h5>
                        
                        <table style="text-align: left; width: 100%;">
                            <tr>
                                <td><p class="card-text">Minimum Score for SILVER</p></td>
                                <td><input style="width: 50%;" :disabled="!isEditingData" type="number" v-model="loyaltySettings.minimumScoreForSILVER" class="form-control" placeholder="number" min="0"></td>
                            </tr>
                            <tr>
                                <td><p class="card-text">Client Discount Percentage for SILVER</p></td>
                                <td><input style="width: 50%;" :disabled="!isEditingData" type="number" v-model="loyaltySettings.clientDiscountPercentageSILVER" class="form-control" placeholder="number" min="0"></td>
                            </tr>
                            <tr>
                                <td><p class="card-text">Owner Discount Percentage for SILVER</p></td>
                                <td><input style="width: 50%;" :disabled="!isEditingData" type="number" v-model="loyaltySettings.ownerDiscountPercentageSILVER" class="form-control" placeholder="number" min="0" max="99"></td>
                            </tr>
                        </table>

                    </div>
                </div>
            </div>
            <div class="col-6">
                <div class="card goldCss">
                    <div class="card-body">
                        <h5 class="card-title goldText">GOLD</h5>
                        
                        <table style="text-align: left; width: 100%;">
                            <tr>
                                <td><p class="card-text">Minimum Score for GOLD</p></td>
                                <td><input style="width: 50%;" :disabled="!isEditingData" type="number" v-model="loyaltySettings.minimumScoreForGOLD" class="form-control" placeholder="number" min="0"></td>
                            </tr>
                            <tr>
                                <td><p class="card-text">Client Discount Percentage for GOLD</p></td>
                                <td><input style="width: 50%;" :disabled="!isEditingData" type="number" v-model="loyaltySettings.clientDiscountPercentageGOLD" class="form-control" placeholder="number" min="0"></td>
                            </tr>
                            <tr>
                                <td><p class="card-text">Owner Discount Percentage for GOLD</p></td>
                                <td><input style="width: 50%;" :disabled="!isEditingData" type="number" v-model="loyaltySettings.ownerDiscountPercentageGOLD" class="form-control" placeholder="number" min="0"></td>
                            </tr>
                        </table>

                    </div>
                </div>
            </div>
        </div>

        <div class="row my-5">
            <div class="col">
                <button v-on:click="editloyaltySettings()" v-if="!isEditingData" class="btn btn-primary mx-1">Edit</button>
                <button v-on:click="cancelEditloyaltySettings()" v-if="isEditingData" class="btn btn-danger mx-1">Cancel</button>
                <button v-on:click="saveEditloyaltySettings()" v-if="isEditingData" class="btn btn-success mx-1">Save</button>
            </div>
        </div>
        <p class="text-danger">{{errMsg}}</p>
        <p style="color: green;">{{msg}}</p>
        
        

    </div>
</template>

<script>
import axios from "axios";
export default {
  name: 'LoyaltySettingsPage',
  components: {
    
  },
  data: function(){
    return {
        isEditingData: false,
        loyaltySettings: {
            clientScoreForReservation : 0,  //1
            ownerScoreForReservation : 0,   //1
            minimumScoreForSILVER : 0,      //s
            minimumScoreForGOLD : 0,        //g
            clientDiscountPercentageSILVER : 0, //s
            clientDiscountPercentageGOLD : 0, //g
            ownerDiscountPercentageSILVER : 0, //s
            ownerDiscountPercentageGOLD : 0, //g
            systemPercentage : 0 //1
        },
        copyloyaltySettings: {},
        errMsg: '',
        msg: '',
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
            axios.get('http://localhost:8180/api/loyalty')
            .then(response => {this.loyaltySettings=response.data; console.log(response.data);}).catch(err => {alert('DOSLO JE DO GRESKE')}); 
      },
      editloyaltySettings: function(){
          // Najednostavniji nacin da se uradi Deep-Copy
          this.copyloyaltySettings = JSON.parse(JSON.stringify(this.loyaltySettings));
          this.isEditingData = true;
      },
      cancelEditloyaltySettings: function(){
          this.loyaltySettings = this.copyloyaltySettings;
          this.isEditingData = false;
      },
      saveEditloyaltySettings: function(){
          this.errMsg = ''
          this.isEditingData = false;
          axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
            axios.post('http://localhost:8180/api/loyalty',this.loyaltySettings).then(
                (response) => {
                    this.loyaltySettings=response.data
                    this.msg = 'successfully updated settings!'
                }).catch((err) => {
                    this.errMsg = 'Error, settings are not updated!'
                    alert('DOSLO JE DO GRESKE')
                }); 
      },
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
    background-image: url("../assets/blurry-gradient-silver.svg"); 
    color: white;
}

.silverText{
    font-weight: bold;
}

.goldCss{
    border-color: white;
    border-width: 2px;
    border-radius: 8px;
    background-image: url("../assets/blurry-gradient-gold.svg"); 
    color: black;
}

.SettingsCss{
    border-color: white;
    border-width: 2px;
    border-radius: 8px;
    background: rgb(2,0,36);
    background: linear-gradient(15deg, rgba(2,0,36,1) 0%, rgba(9,9,121,1) 23%, rgba(0,146,255,1) 100%);
    color: white;
}




.goldText{
    font-weight: bold;
}


</style>
