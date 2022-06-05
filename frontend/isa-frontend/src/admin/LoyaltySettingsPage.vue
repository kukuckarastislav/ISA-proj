<template>
    <div style="height: 60px;"></div>
    <div class="container">

        <div class="row">
            <div class="col">
                <h1>Loyalty Settings</h1>
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
        <hr>
        
        <!-- BUSINESS -->
        <div class="row" style="margin-top: 100px;">
            <div class="col">
                <h1 style="margin-top: 40px;">Business</h1>
            </div>
            <div class="col-4">
                <div class="card">
                    <div class="card-body">
                        <Datepicker class="" v-model="date" range></Datepicker>
                        <button v-on:click="loadData()"  class="btn btn-primary mt-4" style="width: 100%;">Show Income in this period</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="row" style="margin-bottom: 40px;">
        <hr style="margin-top: 10px;">
        <div class="row">
            <div class="col">
                    <div class="row text-start">
                        <div class="col-4">
                            <h5 class="card-title text-start text-bold">Name</h5>
                        </div>
                        <div class="col-2">
                            <h5 class="card-title text-start text-bold">Average Grade</h5>
                        </div>
                        <div class="col-3">
                            <h5 class="card-title text-start text-bold">Loyalty</h5>
                        </div>
                        <div class="col-3">
                            <h5 class="card-title text-start text-bold">System Income in Period</h5>
                        </div>
                    </div>
            </div>
        </div>
        <hr>
        <div class="row">
            <div class="col">
                
                <div v-for="bs in businessData" :key="bs">
                    <div class="row text-start mt-1">
                        <div class="col-4">
                            <h5 class="card-title text-start text-bold">{{bs.fullName}}</h5>
                            <h6 class="card-title text-start">{{bs.email}}</h6>
                            <h5 class="card-title text-start">{{bs.userTypeISA}}</h5>
                        </div>
                        <div class="col-2">
                            <h3 class="text-start stars">
                            <span v-for="index in Math.round(bs.grade)" :key="index">&#9733;</span> 
                            <span v-for="index in Math.round(5-bs.grade)" :key="index">&#9734;</span> 
                            {{bs.grade}}
                            </h3>
                        </div>
                        <div class="col-3">
                            <div class="card" v-bind:class="getCssForLoyalty(bs)" style="width: 200px;">
                                <div class="card-body">
                                    <h5 class="card-title text-start">Loyalty</h5> 
                                    <h6 class="card-title text-start"><b>{{bs.loyalty.loyaltyType}}</b></h6> 
                                    <h6 class="card-title text-start">Score {{bs.loyalty.score}}</h6> 
                                </div>
                            </div>
                        </div>
                        <div class="col-3">
                            <h5 class="card-title text-start">${{bs.income}}</h5>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <hr>
        <div class="row">
            <div class="col">
                    <div class="row text-start">
                        <div class="col-4">
                            <h5 class="card-title text-start text-bold">&#8721;</h5>
                        </div>
                        <div class="col-5"></div>
                        <div class="col-2">
                            <h5 class="card-title text-start text-bold">${{systemIncome}}</h5>
                        </div>
                    </div>
            </div>
        </div>
        </div>

    </div>
</template>

<script>
import axios from "axios";

import Datepicker from 'vue3-date-time-picker';
import 'vue3-date-time-picker/dist/main.css'

export default {
  name: 'LoyaltySettingsPage',
  components: {
    Datepicker
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
        
        systemIncome: 0,
        businessData : [],
        date: [],
    }
  },
  mounted: function () {
    this.date.push(new Date(new Date()-1000*3600*24*365))
    this.date.push(new Date())
    this.loadInformation();
    this.loadData();
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
          this.msg = ''
          this.isEditingData = false;
          axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
            axios.post('http://localhost:8180/api/loyalty',this.loyaltySettings).then(
                (response) => {
                    this.loyaltySettings=response.data
                    this.msg = 'successfully updated settings!'
                    this.loadData()
                }).catch((err) => {
                    this.errMsg = 'Error, settings are not updated!'
                    alert('DOSLO JE DO GRESKE')
                }); 
      },
    getCssForLoyalty: function(user){
        if(user.loyalty.loyaltyType === 'GOLD') return 'goldCss2';
        if(user.loyalty.loyaltyType === 'SILVER') return 'silverCss2';
        if(user.loyalty.loyaltyType === 'REGULAR') return 'SettingsCss2';
        return '';
    },
    loadData: function () {
        let timeStamptBackend = {
            "startTime": new Date(Date.UTC(this.date[0].getFullYear(), this.date[0].getMonth(), this.date[0].getDate(), this.date[0].getHours(), this.date[0].getMinutes())),
            "endTime" : new Date(Date.UTC(this.date[1].getFullYear(), this.date[1].getMonth(), this.date[1].getDate(), this.date[1].getHours(), this.date[1].getMinutes()))    
        }
        
        axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
        axios.post('http://localhost:8180/api/admin/business', timeStamptBackend).then(resp => {
            console.log(resp.data);
            this.businessData = resp.data;
            this.systemIncome = 0
            for (let user of this.businessData) {
                this.systemIncome += user.income
            }
        });
    },
  }
}
</script>


<style scoped>

.tabelaProfila{
    font-size: 24px;
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

.stars{
  color: coral;
}

.silverCss2{
    border-color: white;
    border-width: 2px;
    border-radius: 8px;
    background: rgb(81, 81, 81);
    background: linear-gradient(15deg, rgb(82, 82, 82) 0%, rgb(139, 139, 139) 23%, rgb(222, 222, 222) 100%);
    color: white;
}

.goldCss2{
    border-color: white;
    border-width: 2px;
    border-radius: 8px;
    background: rgb(168, 129, 4);
    background: linear-gradient(15deg, rgb(190, 126, 0) 0%, rgb(202, 158, 10) 23%, rgb(238, 255, 0) 100%);
    color: black;
}

.SettingsCss2{
    border-color: white;
    border-width: 2px;
    border-radius: 8px;
    background: rgb(36, 23, 0);
    background: linear-gradient(15deg, rgba(2,0,36,1) 0%, rgba(9,9,121,1) 23%, rgba(0,146,255,1) 100%);
    color: white;
}

.text-bold{
    font-weight: bold;
}


</style>
