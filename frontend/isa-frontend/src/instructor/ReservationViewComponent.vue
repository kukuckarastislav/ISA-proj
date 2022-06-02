<template>

<div class="card" style="background-color: whitesmoke;">
    <div class="card-body">
        <div class="row">
            <div class="col-4">
                <img class="img-fluid" v-bind:src="getImg(reservation)">
            </div>
            <!-- Info -->
            <div class="col-3">
                <h5 class="card-title text-start"><a v-on:click="moreDetails(reservation.adventureName)" href="">{{reservation.adventureName}}</a></h5>
                <br>
                <h6 class="card-title text-start">Country: {{reservation.address.country}}</h6>
                <h6 class="card-title text-start">City: {{reservation.address.city}}</h6>
                <h6 class="card-title text-start">Street: {{reservation.address.street}} {{reservation.address.number}}</h6>
                <br>
            </div>
            <div class="col-5">
                <br>
                <h5 class="card-title text-start m-1" style="font-weight: bold;">{{reservation.termType}}</h5>
                <div v-if="reservation.termType === 'FAST_RESERVATION'">
                    <h5 v-if="reservation.taken" class="card-title text-start m-1" style="font-weight: bold;">TAKEN</h5>
                </div>
                <div v-else>
                    <h5 class="card-title text-start m-1" style="font-weight: bold;">{{reservation.statusOfReservation}}</h5>
                </div>
                <h5 class="card-title text-start">Start: {{convertDate(reservation.startTime)}}</h5>
                <h5 class="card-title text-start">End: {{convertDate(reservation.endTime)}}</h5>
                <br>
                <h5 class="card-title text-start">Price: {{reservation.price}}</h5>
            </div>
        </div>
            <!-- Additional services -->
        <div class="row" style="margin-top:20px;">
            <div class="col-12">
                <div class="list-group text-start overflow-auto card" style="height: 200px">
                <div class="row m-1" style="font-weight: bold;">
                    <div class="col-3">Name</div>
                    <div class="col-6">Description</div>
                    <div class="col-2">Price</div>
                </div>
                <label v-for="itPrice in reservation.additionalServices" :key="itPrice" class="list-group-item">
                    <div class="row m-1">
                        <div class="col-3">{{itPrice.name}}</div>
                        <div class="col-6">{{itPrice.description}}</div>
                        <div class="col-2">{{itPrice.price}}</div>
                    </div>
                </label>
                </div>
            </div>
        </div>
        <div class="row" style="margin-top:30px;">
            <!-- reservation.client -->
            <div class="col-8">
                <div v-if="reservation.clientView">
                    <ClientViewComponent v-bind:client="reservation.clientView"/>
                </div>
            </div>
            <div class="col-4">
                <div v-if="reservation.termType === 'FAST_RESERVATION'">
                    <div class="list-group text-start overflow-auto card" style="height: 220px">
                        <label v-for="frh in reservation.insFastResHistories" :key="frh" class="list-group-item">
                            <div v-on:click="selectClientView(reservation, frh)" class="row">
                                <div class="col">{{frh.client.firstName}}</div>
                                <div class="col">{{frh.client.lastName}}</div>
                                <div class="col">{{frh.statusOfFastReservation}}</div>
                            </div>
                        </label>
                    </div>
                </div>
            </div>
        </div>

        <div class="row" style="margin-top:30px;">
            <div class="col">
                <div class="btn-group" >
                    <button v-on:click="showForm(1)" class="btn btn-outline-primary">Report</button>
                    <button v-on:click="showForm(2)" class="btn btn-outline-primary">Complaint</button>
                    <button v-on:click="showForm(3)" class="btn btn-outline-primary">Reservate again</button>
                    <button v-on:click="showForm(4)" class="btn btn-secondary">Hide</button>
                </div>
            </div>
        </div>

        <!-- Report -->
        <div v-if="visibleForm.report" class="row" style="margin-top:30px;">
            <h4>Report</h4>
            <div class="col">
                TODO REPORT
            </div>
        </div>

        <!-- Complaint -->
        <div v-if="visibleForm.complaint" class="row" style="margin-top:30px;">
            <h4>Complaint</h4>
            <div class="col" v-if="!reservation.instructorComplaint">
                <textarea v-model="complaintText" style="width: 100%;" name="complaint" id="" rows="4"></textarea>
            </div>
            <div v-if="!reservation.instructorComplaint" class="col-3">
                <button v-on:click="closeComplaintForm()" class="btn btn-primary m-1">Cancel</button>
                <button v-on:click="sendComplaint()" class="btn btn-danger m-1" :disabled="complaintText===''">Send</button>
            </div>
            <div class="col" v-if="reservation.instructorComplaint">
                <div class="card">
                    <div class="card-body">
                        <p class="card-text text-start">For client: {{reservation.instructorComplaint.userEmail}} At: {{convertDate(reservation.instructorComplaint.createdAt)}}</p>
                        <p class="card-text text-start">Your Complaint: {{reservation.instructorComplaint.comment}}</p>
                        <div v-if="reservation.instructorComplaint.statusOfComplaint === 'ANSWERED'">
                            <hr>
                            <p class="card-text text-start"><b>ANSWERED At: {{convertDate(reservation.instructorComplaint.adminResponsDate)}}</b> By: {{reservation.instructorComplaint.adminEmail}}</p>
                            <p class="card-text text-start">{{reservation.instructorComplaint.adminResponse}}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Reservation Again -->
        <div v-if="visibleForm.reservationAgain" class="row" style="margin-top:30px;">
            <div class="col">
                TODO RESERVATION AGAIN
            </div>
        </div>


    </div>         
</div>

</template>

<script>
import axios from "axios";
import ClientViewComponent from '../components/ClientViewComponent.vue'
export default {
  name: 'ReservationViewComponent',
  props: ['reservation'],
  components: {
    ClientViewComponent
  },
  data: function(){
    return {
        visibleForm: {
            report: false,
            complaint: false,
            reservationAgain: false,
        },

        complaintText: '',
    }
  },
  watch: { 
      	reservation: function(newVal, oldVal) { // watch it
             console.log("NOVA VREDNOST", newVal)
            this.reservation = newVal
            this.reservation.clientView = this.reservation.client
            if(this.reservation.termType === 'FAST_RESERVATION'){
                if(this.reservation.insFastResHistories.length > 0){
                    this.reservation.clientView = this.reservation.insFastResHistories[0].client
                }
            }
        },
  },
  mounted: function(){
      this.setupReservation();
  },
  methods: {
    setupReservation: function(){
        console.log("nice")
        this.reservation.clientView = this.reservation.client
        if(this.reservation.termType === 'FAST_RESERVATION'){
            if(this.reservation.insFastResHistories.length > 0){
                this.reservation.clientView = this.reservation.insFastResHistories[0].client
            }
        }
    },
    moreDetails: function(adventureName){
        this.$router.push({ path: '/adventure/'+encodeURIComponent(adventureName)});
    },
    getImg: function(reservation){
        var minNum = 10000;
        var imgPath = reservation.images[0].path;
        for(var i=0; i<reservation.images.length; i++){
            var num = Number(reservation.images[i].name.split(".")[0].substring(3));
            if(num < minNum){
                minNum = num;
                imgPath = reservation.images[i].path;
            }

        }
        return 'http://localhost:8180/api/entityImage/'+imgPath;
    },
    convertDate: function(date){
        return new Date(date).toLocaleString();
    },
    selectClientView: function(reservation, frh){
        if(reservation.termType === 'FAST_RESERVATION'){
            reservation.clientView = frh.client
        }
    },
    showForm: function(n){
        this.visibleForm.report = false;
        this.visibleForm.complaint = false;
        this.visibleForm.reservationAgain = false;
        if(n==1){
            this.visibleForm.report = true;
        }
        if(n==2){
            this.visibleForm.complaint = true;
        }
        if(n==3){
            this.visibleForm.reservationAgain = true;
        }
    },
    sendComplaint: function(){
        if(new Date(this.reservation.endTime) < new Date()){
            
            let instructorComplaint = {
                "clientEmail" : this.reservation.clientView.email,
                "comment": this.complaintText,
                "fastReservation" : false,
                "idReservation": this.reservation.idReservation
            }

            if(this.reservation.termType === 'FAST_RESERVATION') {
                instructorComplaint.fastReservation = true
            }
            console.log("RESERVATION", this.reservation)
            console.log("POSTbody", instructorComplaint)
            axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
            axios.post('http://localhost:8180/api/complaint/instructor',instructorComplaint).then(resp => {
                    console.log(resp.data);
                    if(!resp.data){
                        alert("Error")
                    }else{
                        alert("Successfully")
                        this.closeComplaintForm()
                    }
            });

        }else{
            alert('Error: Reservation is not finished')
            this.closeComplaintForm()
        }
    },
    closeComplaintForm: function(){
        this.visibleForm.complaint = false;
        this.complaintText = '';
    },
    convertDate: function(date){
        return new Date(date).toLocaleString();
    },
  }
}
</script>


<style scoped>

</style>
