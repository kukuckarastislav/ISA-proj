<template>
    <div style="height: 60px;"></div>
    <div class="container" v-if="businessData != null">
        
        <div class="row">
            <div class="col-6">
                <h3 class="card-title text-start"><b>{{businessData.instructorDTO.firstName}} {{businessData.instructorDTO.lastName}}</b></h3> 
                <h4 class="card-title text-start">{{businessData.instructorDTO.email}} {{businessData.instructorDTO.lastName}}</h4>
                <div class="row" style="margin-top: 30px;">
                    <div class="col-5" style="margin-bottom: 30px;">
                        <h3 class="text-start stars">
                            <span v-for="index in Math.round(businessData.instructorDTO.averageGrade)" :key="index">&#9733;</span> 
                            <span v-for="index in Math.round(5-businessData.instructorDTO.averageGrade)" :key="index">&#9734;</span> 
                            {{businessData.instructorDTO.averageGrade}}
                        </h3>
                        <div class="card" v-bind:class="getCssForLoyalty()" style="width: 200px;" v-on:click="visibleLoyaltyInfo = !visibleLoyaltyInfo">
                            <div class="card-body">
                                <h5 class="card-title text-start">Loyalty</h5> 
                                <h6 class="card-title text-start"><b>{{businessData.instructorDTO.loyalty.loyaltyType}}</b></h6> 
                                <h6 class="card-title text-start">Score {{businessData.instructorDTO.loyalty.score}}</h6> 
                            </div>
                        </div>
                    </div>
                    <div class="col card" v-if="visibleLoyaltyInfo">
                        <div class="card-body">
                        <h5 class="card-title text-start">Score For Reservation: {{businessData.loyaltySettings.ownerScoreForReservation}}</h5> 
                        <h5 class="card-title text-start">minimum Score For SILVER: {{businessData.loyaltySettings.minimumScoreForSILVER}}</h5>
                        <h5 class="card-title text-start">minimum Score For GOLD: {{businessData.loyaltySettings.minimumScoreForGOLD}}</h5>
                        <h5 class="card-title text-start">Discount if SILVER: {{businessData.loyaltySettings.ownerDiscountPercentageSILVER}}%</h5>
                        <h5 class="card-title text-start">Discount if GOLD {{businessData.loyaltySettings.ownerDiscountPercentageGOLD}}%</h5>
                        <h5 class="card-title text-start">System percentage {{businessData.loyaltySettings.systemPercentage}}%</h5>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-2"></div>
            <div class="col-4">
                <div class="card" style="margin-top: 200px;">
                    <div class="card-body">
                        <Datepicker class="" v-model="date" range></Datepicker>
                        <button v-on:click="loadData()"  class="btn btn-primary mt-4" style="width: 100%;">Show Income in this period</button>
                    </div>
                </div>
            </div>
        </div>

        <hr style="margin-top: 10px;">
        <div class="row">
            <div class="col">
                    <div class="row text-start">
                        <div class="col-4">
                            <h5 class="card-title text-start text-bold">Name</h5>
                        </div>
                        <div class="col-3">
                            <h5 class="card-title text-start text-bold">Average Grade</h5>
                        </div>
                        <div class="col-1">
                            <h5 class="card-title text-start text-bold">#Year</h5>
                        </div>
                        <div class="col-1">
                            <h5 class="card-title text-start text-bold">#Month</h5>
                        </div>
                        <div class="col-1">
                            <h5 class="card-title text-start text-bold">#Week</h5>
                        </div>
                        <div class="col-2">
                            <h5 class="card-title text-start text-bold">Income in Period</h5>
                        </div>
                    </div>
            </div>
        </div>
        <hr>
        <div class="row">
            <div class="col">
                <div v-for="bs in businessData.businessStatistics" :key="bs">
                    <div class="row text-start">
                        <div class="col-4">
                            <h5 class="card-title text-start">{{bs.entityName}}</h5>
                        </div>
                        <div class="col-3">
                            <h3 class="text-start stars">
                            <span v-for="index in Math.round(bs.grade)" :key="index">&#9733;</span> 
                            <span v-for="index in Math.round(5-bs.grade)" :key="index">&#9734;</span> 
                            {{bs.grade}}
                            </h3>
                        </div>
                        <div class="col-1">
                            <h5 class="card-title text-start">{{bs.numOfResInYear}}</h5>
                        </div>
                        <div class="col-1">
                            <h5 class="card-title text-start">{{bs.numOfResInMonth}}</h5>
                        </div>
                        <div class="col-1">
                            <h5 class="card-title text-start">{{bs.numOfResInWeek}}</h5>
                        </div>
                        <div class="col-2">
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
                        <div class="col-3">
                            <h3 class="text-start stars">
                            <span v-for="index in Math.round(sumary.grade)" :key="index">&#9733;</span> 
                            <span v-for="index in Math.round(5-sumary.grade)" :key="index">&#9734;</span> 
                            {{sumary.grade}}
                            </h3>
                        </div>
                        <div class="col-1">
                            <h5 class="card-title text-start text-bold">{{sumary.year}}</h5>
                        </div>
                        <div class="col-1">
                            <h5 class="card-title text-start text-bold">{{sumary.month}}</h5>
                        </div>
                        <div class="col-1">
                            <h5 class="card-title text-start text-bold">{{sumary.week}}</h5>
                        </div>
                        <div class="col-2">
                            <h5 class="card-title text-start text-bold">${{sumary.income}}</h5>
                        </div>
                    </div>
            </div>
        </div>
        <br> <br>
    </div>
</template>

<script>
import axios from "axios";

import Datepicker from 'vue3-date-time-picker';
import 'vue3-date-time-picker/dist/main.css'


export default {
  name: 'InstructorBusinessPage',
  components: {
    Datepicker
  },
  data: function(){
    return {
        businessData: null,
        date: [],
        sumary: {
            grade: 0,
            year: 0,
            month: 0,
            week: 0,
            income: 0,
        },
        visibleLoyaltyInfo: false,
    }
  },
  mounted: function () {
    this.date.push(new Date(new Date()-1000*3600*24*365))
    this.date.push(new Date())
    this.loadData();
  },
  methods: {
    loadData: function () {
        let timeStamptBackend = {
            "startTime": new Date(Date.UTC(this.date[0].getFullYear(), this.date[0].getMonth(), this.date[0].getDate(), this.date[0].getHours(), this.date[0].getMinutes())),
            "endTime" : new Date(Date.UTC(this.date[1].getFullYear(), this.date[1].getMonth(), this.date[1].getDate(), this.date[1].getHours(), this.date[1].getMinutes()))    
        }
        
        axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
        axios.post('http://localhost:8180/api/instructor/business', timeStamptBackend).then(resp => {
            this.businessData = resp.data;
            this.sumary = { grade: 0, year: 0, month: 0, week: 0, income: 0 }
            for (let bs of this.businessData.businessStatistics) {
                this.sumary.grade += bs.grade
                this.sumary.year += bs.numOfResInYear
                this.sumary.month += bs.numOfResInMonth
                this.sumary.week += bs.numOfResInWeek
                this.sumary.income += bs.income
            }
            if (this.businessData.businessStatistics.length > 0) {
                this.sumary.grade = this.sumary.grade / this.businessData.businessStatistics.length
            }
            console.log(this.businessData)
        });
    },
    getCssForLoyalty: function(){
        if(this.businessData.instructorDTO.loyalty.loyaltyType === 'GOLD') return 'goldCss';
        if(this.businessData.instructorDTO.loyalty.loyaltyType === 'SILVER') return 'silverCss';
        if(this.businessData.instructorDTO.loyalty.loyaltyType === 'REGULAR') return 'SettingsCss';
        return '';
    },
  }
}
</script>


<style scoped>


.stars{
  color: coral;
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

.text-bold{
    font-weight: bold;
}
</style>
