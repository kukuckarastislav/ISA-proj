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
                <div v-if="reservation.termType === 'FAST_RESERVATION' && reservation.insFastResHistories.length > 0">
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
        <div v-if="visibleForm.report" style="margin-top:30px;">
            <h4>Report</h4>
            <div class="row" v-if="!reservation.report">
                <div class="col text-start">
                    <input v-model="report.notShowUp" class="form-check-input m-2" type="checkbox" v-bind:id="idr+'1'" checked>
                    <label class="form-check-label m-1" v-bind:for="idr+'1'"> The client did not show up</label>
                    <br>
                    <div v-if="!report.notShowUp">
                        <input v-model="report.negative" class="form-check-input m-2" type="checkbox" v-bind:id="idr+'2'" checked>
                        <label class="form-check-label m-1" v-bind:for="idr+'2'"> Negative experience</label>
                    </div>
                </div>
            </div>
            <div class="row" v-if="!reservation.report">
                <div class="col" v-if="!report.notShowUp">
                    <textarea v-model="report.comment" style="width: 100%;" name="complaint" id="" rows="4"></textarea>
                </div>
                <div v-if="true" class="col-3">
                    <button v-on:click="closeReportForm()" class="btn btn-primary m-1">Cancel</button>
                    <button v-on:click="sendReport()" class="btn btn-danger m-1" :disabled="report.comment==='' && !report.notShowUp">Send</button>
                </div>
            </div>
            <div class="row">
                <div class="col" v-if="reservation.report">
                    <div class="card">
                        <div class="card-body">
                            <p class="card-text text-start" v-if="!reservation.report.clientShowedUp"><b>The client did not show up</b></p>
                            <p class="card-text text-start" v-if="reservation.report.sanctionClient"><b>Negative experience</b></p>
                            <p class="card-text text-start">For client: {{reservation.report.clientEmail}} At: {{convertDate(reservation.report.createdAt)}}</p>
                            <p class="card-text text-start">Your comment: {{reservation.report.text}}</p>
                            <div v-if="reservation.report.statusOfReport !== 'PENDING'">
                                <hr>
                                <p class="card-text text-start" style="font-weight: bold; color: red;">{{reservation.report.statusOfReport}}</p>
                                <p class="card-text text-start"><b>ANSWERED At: {{convertDate(reservation.report.adminResponsDate)}}</b> By: {{reservation.report.adminUsername}}</p>
                                <p class="card-text text-start">{{reservation.report.adminResposne}}</p>
                            </div>
                        </div>
                    </div>
                </div>
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
          <h4>Reservate again</h4>
          <div class="card">
            <div class="card-body">
              <div class="row">
                <div class="col-5">
                  <div class="row justify-content-between">
                    <div class="col">
                        <input type="text" v-model="repeatReservation.address.country" class="form-control" placeholder="Country">
                        <br>
                        <input type="text" v-model="repeatReservation.address.city" class="form-control" placeholder="City">
                        <br>
                        <input type="text" v-model="repeatReservation.address.street" class="form-control" placeholder="street">
                    </div>
                    <div class="col">
                        <input type="text" v-model="repeatReservation.address.number" class="form-control" placeholder="number">
                        <br>
                        <input type="number" v-model="repeatReservation.address.latitude" class="form-control" placeholder="latitude">
                        <br>
                        <input type="number" v-model="repeatReservation.address.longitude" class="form-control" placeholder="longitude">
                    </div>
                    <Datepicker v-model="repeatReservation.date" range style="margin-top: 22px;"></Datepicker>
                  </div>
                </div>
                <div class="col-7">
                    <div class="list-group text-start overflow-auto card" style="height: 162px">
                    <label v-for="itPrice in allItemPrices" :key="itPrice" class="list-group-item">
                        <div class="row">
                            <div class="col-sm-1">
                                <input class="form-check-input me-1" type="checkbox" value="" v-on:click="addItemPriceFastReservation(itPrice)">
                            </div>
                            <div class="col-sm-3">{{itPrice.name}}</div>
                            <div class="col-sm-6">{{itPrice.description}}</div>
                            <div class="col-sm-2">{{itPrice.price}}</div>
                        </div>
                    </label>
                    </div>
                    <br>
                    <div class="row">
                    <div class="col-1"></div>
                      <div class="col-5 text-end">Max number of People</div>
                      <div class="col-2">
                        <input type="number" v-model="repeatReservation.maxNumberOfPeople" class="form-control">
                      </div>
                      <div class="col-2 text-end">Price</div>
                      <div class="col-2">
                        <input type="number" v-model="repeatReservation.price" class="form-control">
                      </div>
                    </div>
                </div>
                <div class="row" style="margin-top: 30px;">
                <hr>
                <div class="col">
                  <button class="btn btn-danger m-1" v-on:click="showForm(4)">Cancel</button>
                  <button class="btn btn-primary m-1" v-on:click="sendNewFastReservation()">Create</button>
                </div>
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
import ClientViewComponent from '../components/ClientViewComponent.vue'
import Datepicker from 'vue3-date-time-picker';
import 'vue3-date-time-picker/dist/main.css'
export default {
  name: 'ReservationViewComponent',
  props: ['reservation'],
  components: {
      ClientViewComponent,
      Datepicker
  },
  data: function(){
    return {
        idr: '',
        visibleForm: {
            report: false,
            complaint: false,
            reservationAgain: false,
        },

        report: {
            notShowUp: false,
            negative: false,
            comment: '',
        },

        complaintText: '',

        repeatReservation: {
          date: [],
          itemPrices: [],
          address: {
                country: '',
                city: '',
                street: '',
                number: '',
                latitude: 0,
                longitude: 0
          },
          maxNumberOfPeople: 0,
          price: 0,
        },

        allItemPrices: [],
        allAdditionalEquipment: [],
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
            this.idr = this.reservation.idReservation.toString() + this.reservation.termType;
            console.log("ID: ", this.idr)
        },
  },
    mounted: function () {
      this.loadAdditionaData();
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
        this.idr = this.reservation.idReservation.toString() + this.reservation.termType;
        console.log("ID: ", this.idr)
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
            this.initReservationAgainForm();
            this.visibleForm.reservationAgain = true;
        }
    },
    sendComplaint: function(){
        if(new Date(this.reservation.endTime) < new Date()){
            
            if(!this.reservation.clientView){
                alert('Error: Client Is not selected')
                return;
            }

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
    closeReportForm: function(){
        this.report.comment = '';
        this.report.notShowUp = false
        this.report.negative = false
        this.visibleForm.report = false;
    },
    sendReport: function(){
        if(new Date(this.reservation.endTime) < new Date()){
            
            if(!this.reservation.clientView){
                alert('Error: Client Is not selected')
                return;
            }

            if(this.report.notShowUp){
                this.report.negative = false;
                this.report.comment = '';
            }
            
            let instructorReport = {
                "reservationId" : this.reservation.idReservation,
                "reservationType" : 'INSTRUCTOR',
                "termType" : this.reservation.termType,
                "text" : this.report.comment,
                "sanctionClient" : this.report.negative,
                "clientShowedUp" : !this.report.notShowUp,
                "idClient" : this.reservation.clientView.id,
                "clientEmail" : this.reservation.clientView.email,
            }

            console.log("POSTbody", instructorReport)
            axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
            axios.post('http://localhost:8180/api/report/instructor',instructorReport).then(resp => {
                    console.log(resp.data);
                    if(!resp.data){
                        alert("Error")
                    }else{
                        alert("Successfully")
                        this.closeReportForm()
                    }
            });

        }else{
            alert('Error: Reservation is not finished')
            this.closeComplaintForm()
        }
    },
    addItemPriceFastReservation: function (itPrice) {  
       for(let i = 0; i < this.repeatReservation.itemPrices.length; i++) {
            if(this.repeatReservation.itemPrices[i].id == itPrice.id){
                //remove
                this.repeatReservation.itemPrices.splice(i, 1);
                return;
            }
        }
        this.repeatReservation.itemPrices.push(itPrice)
    },
    sendNewFastReservation: function(){

      const startTimeForBackend = new Date(Date.UTC(this.fastReservationForm.date[0].getFullYear(), this.fastReservationForm.date[0].getMonth(), this.fastReservationForm.date[0].getDate(), this.fastReservationForm.date[0].getHours(), this.fastReservationForm.date[0].getMinutes()))
      const endTimeForBackend = new Date(Date.UTC(this.fastReservationForm.date[1].getFullYear(), this.fastReservationForm.date[1].getMonth(), this.fastReservationForm.date[1].getDate(), this.fastReservationForm.date[1].getHours(), this.fastReservationForm.date[1].getMinutes()))


      let newFastReservationDTO = {
          "idAdventure" : this.adventure.id,
          "startTime" : startTimeForBackend,
          "endTime" : endTimeForBackend,
          "maxNumberOfPeople" : this.fastReservationForm.maxNumberOfPeople,
          "address" : this.fastReservationForm.address,
          "itemPrices" : this.fastReservationForm.itemPrices,
          "price" : this.fastReservationForm.price
      }


      axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
      axios.post('http://localhost:8180/api/instructorterms/fast-reservation', newFastReservationDTO).then(
        (resp) => {
          console.log(resp.data)
          if(resp.data.startsWith("error")){
            alert(resp.data)
          }else{
            this.openFastReservationForm();
            this.fastReservationForm.visible = false;
            this.loadData(false);
          }
        }, 
        (err)=>{
          alert(err)
      });
      },
    initReservationAgainForm: function () {
      this.repeatReservation.address = this.reservation.address
      this.repeatReservation.maxNumberOfPeople = 1
      this.repeatReservation.price = 0
      this.repeatReservation.itemPrices = []
      },
    loadAdditionaData: function () {
        axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
        axios.get('http://localhost:8180/api/additionalequipment/getadditionalequipment').then(resp => {
            this.allAdditionalEquipment = resp.data;
            console.log(resp.data);
        });
        axios.get('http://localhost:8180/api/itemprice/getitemprices').then(resp => {
            this.allItemPrices = resp.data;
            console.log(resp.data);
        });    
    },
  }
}
</script>


<style scoped>

</style>
