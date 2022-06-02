<template>
    <div style="height: 60px;"></div>
    <div class="container">
        <div class="row">
            <div class="col-sm-3">
                <div class="card align-items-start">
                    <div class="card-body justify-content-start text-start">
                        
                        <h5>Status of Report</h5>
                    
                        <input v-model="showreports.pending" class="form-check-input m-2" type="checkbox" id="idPending" checked>
                        <label class="form-check-label m-1" for="idPending"> Pending</label>
                        <br>
                        <input v-model="showreports.rejected" class="form-check-input m-2" type="checkbox" id="idRejected" checked>
                        <label class="form-check-label m-1" for="idRejected"> Rejected</label>
                        <br>
                        <input v-model="showreports.approved" class="form-check-input m-2" type="checkbox" id="idApproved" checked>
                        <label class="form-check-label m-1" for="idApproved"> Approved</label>
                        <br>

                        <hr>
                        <h5>Report from</h5>
                        <div>
                            <input v-model="showreports.instructor" class="form-check-input m-2" type="checkbox" id="idadventures" checked>
                            <label class="form-check-label m-1" for="idadventures"> Adventures</label>
                            <br>
                            <input v-model="showreports.cottage_owner" class="form-check-input m-2" type="checkbox" id="idcottages" checked>
                            <label class="form-check-label m-1" for="idcottages"> Cottages</label>
                            <br>
                            <input v-model="showreports.boat_owner" class="form-check-input m-2" type="checkbox" id="idboats" checked>
                            <label class="form-check-label m-1" for="idboats"> Boats</label>
                            <br>
                        </div>

                        <hr>
                        <input v-model="showreports.onlyMyResponse" class="form-check-input m-2" type="checkbox" id="idCCottage" checked>
                        <label class="form-check-label m-1" for="idCCottage"> Only my response</label>
  
   
                    </div>
                </div>
            </div>
            <div class="col-sm-9">
                
                <div v-for="report in reports" :key="report">
                    <div v-if="showIfNeed(report)">
                    <div class="card">
                        <div class="card-body">
                            <div class="row">
                                <div class="col">
                                    <h5 class="card-title text-start">{{report.entityName}}</h5> 
                                    <h6 class="card-title text-start">Owner: {{report.report.ownerEmail}}</h6> 
                                    <h6 class="card-title text-start">Client: {{report.report.clientEmail}}</h6> 
                                </div>
                            
                            </div>
                            <div class="row">
                                <div class="col">
                                    <div class="card">
                                    <div class="card-body">
                                        <p class="card-text text-start">At: {{convertDate(report.report.createdAt)}}</p>
                                        <p class="card-text text-start">{{report.report.text}}</p>
                                    </div>
                                </div>
                                </div>
                            </div>
                            <button v-if="!report.front_visibleForm && report.report.statusOfReport === 'PENDING'" v-on:click="report.front_visibleForm = true" class="btn btn-primary m-2">Respond</button>
                            <div v-if="report.report.statusOfReport === 'PENDING' && report.front_visibleForm" class="row" style="margin-top: 30px;">
                                <div class="col-9"> 
                                    <select v-model="report.front_status" class="form-select" style="width: 20%;" aria-label="Default select example">
                                    <option value="APPROVE">APPROVE</option>
                                    <option value="REJECT">REJECT</option>
                                    </select>
                                    <textarea v-model="report.front_comment" style="width: 100%; margin-top: 10px;" rows="3"></textarea>
                                </div>
                                <div class="col" style="margin-top: 45px;">
                                    <button class="btn btn-primary m-1" v-on:click="resetRespond(report)">Cancel</button>
                                    <button class="btn btn-danger m-1" v-on:click="sendAdminResponse(report)" :disabled="report.front_comment === ''">Send</button>
                                </div>
                            </div>
                            <div class="row mt-2" v-if="report.report.statusOfReport !== 'PENDING'">
                                <div class="col">
                                    <div class="card">
                                        <div class="card-body">
                                            <div class="card-text text-start rejectedCss fw-bold" v-if="report.report.statusOfReport === 'REJECTED'">REJECTED</div>
                                            <div class="card-text text-start approvedCss fw-bold" v-if="report.report.statusOfReport === 'APPROVED'">APPROVED</div>
                                            <p class="card-text text-start"><b>{{convertDate(report.report.adminResponsDate)}} By: {{report.report.adminUsername}}</b></p>
                                            <p class="card-text text-start">{{report.report.adminResposne}}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>         
                    </div>
                    <br>
                    </div>
                </div>

            </div>
        </div>
    </div>


</template>

<script>
import axios from "axios";
export default {
  name: 'ReportPage',
  components: {
    
  },
  data: function(){
    return {
        showreports:{
            showEntity: true,
            showOwners: false,
            pending: true,
            approved: true,
            rejected: true,
            onlyMyResponse: false,

            instructor: true,
            cottage_owner: true,
            boat_owner: true,

            client: true //del
        },
        adminResponse:{
            name: '',
            email: '',
            message: '',
            report: {},
            deleteRequestStatus: 'PENDING'
        },
        reports: [],
    }
  },
  mounted: function(){
     this.loadData();
  },
  methods: {
    loadData: function(){
        
        axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
        axios.get('http://localhost:8180/api/report/admin').then(resp => {
            this.reports = resp.data;
            for(let i = 0; i < this.reports.length; i++){
                this.reports[i].front_comment = ''
                this.reports[i].front_visibleForm = false
                this.reports[i].front_status = 'APPROVE'
            }
            console.log(resp.data);
        });
        
    },
    sendAdminResponse: function(report){
        
        let status = (report.front_status === 'APPROVE') ? 'APPROVED' :'REJECTED' ;
        
        let reportRespond = {
            "idReport" : report.report.id,
            "comment": report.front_comment,
            "statusOfReport": status
        }
        console.log("POSTbody", reportRespond)
        axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
        axios
          .post('http://localhost:8180/api/report/admin', reportRespond)
          .then(response => {
              console.log(response.data);
              if(response.data){
                  this.loadData();
              }else{
                  alert("Error");
              }

            }).catch(err => {
              alert('DOSLO JE DO GRESKE')
            });
    },
    showIfNeed: function(report){
        return true;
        return this.showByStatus(report) && this.showOnlyMyRespons(report) && this.showByreportType(report)
    },
    showByStatus: function(report){
       
        if( report.statusOfreport === 'PENDING' && this.showreports.pending) return true;
        if( report.statusOfreport === 'APPROVED' && this.showreports.approved) return true;
        if( report.statusOfreport === 'REJECTED' && this.showreports.rejected) return true;
       
        return false;
    },
    showOnlyMyRespons: function(report){
        
        if(this.showreports.onlyMyResponse){
            if(report.adminUsername === window.sessionStorage.getItem("email")){
                return true;
            }else{
                return false;
            }
        }
        
        return true;
    },
    showByreportType: function(report){
       
        if( report.ownerType === 'INSTRUCTOR' && this.showreports.instructor) return true;
        if( report.ownerType === 'BOAT_OWNER' && this.showreports.boat_owner) return true;
        if( report.ownerType === 'COTTAGE_OWNER' && this.showreports.cottage_owner) return true;
        
        return false;
    },
    convertDate: function(date){
        return new Date(date).toLocaleString();
    },
    resetRespond: function(report){
        report.front_comment = ''
        report.front_visibleForm = false;
    },
  }
}
</script>


<style scoped>

.rejectedCss{
    color: red;
}

.approvedCss{
    color: rgb(0, 255, 0);
}

.stars{
  color: coral;
}

</style>
