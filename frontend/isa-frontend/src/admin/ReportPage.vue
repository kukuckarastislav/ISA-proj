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
                        <div v-if="showreports.showOwners">
                            <input v-model="showreports.instructor" class="form-check-input m-2" type="checkbox" id="idinstructor">
                            <label class="form-check-label m-1" for="idinstructor"> Instructors</label>
                            <br>
                            <input v-model="showreports.cottage_owner" class="form-check-input m-2" type="checkbox" id="idcottage_owner">
                            <label class="form-check-label m-1" for="idcottage_owner"> Cottage Owners</label>
                            <br>
                            <input v-model="showreports.boat_owner" class="form-check-input m-2" type="checkbox" id="idboat_owner">
                            <label class="form-check-label m-1" for="idboat_owner"> Boat Owners</label>
                            <br> 
                        </div>

                        <div v-if="showreports.showEntity">
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
                
                <div v-for="revision in reports" :key="revision">
                    <div v-if="showIfNeed(revision)" class="card">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="card-text text-start" style="width: 40px;" v-if="revision.revisionType==='OWNER'">
                                        <img src="../assets/abstract-user.png" class="img-fluid rounded-start" >
                                    </div>
                                    <h5 v-else class="card-title text-start">{{revision.entityName}}</h5> 
                                    <h6 class="card-title text-start">Owner: {{revision.ownerUsername}}</h6> 
                                    <h6 class="card-title text-start">Client: {{revision.clientUsername}}</h6> 
                                </div>
                                
                                <div class="col-sm-6">
                                    <div>                                        
                                        <div v-if="revision.statusOfRevision === 'PENDING'">
                                            <button v-on:click="sendAdminResponse(revision, true)" class="btn btn-primary m-2">Approve</button>
                                            <button v-on:click="sendAdminResponse(revision, false)" class="btn btn-danger m-2">Reject</button>
                                        </div>
                                        <div v-else>
                                            <div class="card m-1">
                                                <div class="card-body">
                                                    <div class="card-text text-start rejectedCss fw-bold" v-if="revision.statusOfRevision === 'REJECTED'">REJECTED</div>
                                                    <div class="card-text text-start approvedCss fw-bold" v-if="revision.statusOfRevision === 'APPROVED'">APPROVED</div>
                                                    <p class="card-text text-start"><b>{{convertDate(revision.adminResponsDate)}}</b></p>
                                                    <p class="card-text text-start">By: {{revision.adminUsername}}</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row m-1">
                                <div class="card">
                                    <div class="card-body">

                                        <h3 class="text-start stars">
                                        <span v-for="index in Math.round(revision.grade)" :key="index">&#9733;</span> 
                                        <span v-for="index in Math.round(5-revision.grade)" :key="index">&#9734;</span> 
                                        {{revision.grade}}
                                        </h3>
                                        <p class="card-text text-start">{{revision.grade}} {{convertDate(revision.revisionCreatedAt)}}</p>
                                        <p class="card-text text-start">{{revision.comment}}</p>
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
            revision: {},
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
        axios.get('http://localhost:8180/api/revision/owners').then(resp => {
            this.reports = resp.data;
            console.log(resp.data);
        });
        
    },
    sendAdminResponse: function(revision, status){
        alert('SLANJE RESPONSA');
        axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
        axios
          .post('http://localhost:8180/api/revision/respons', {
              adminUsername: window.sessionStorage.getItem("email"),
              approve: status,
              idRevision: revision.idRevision,
              ownerType: revision.ownerType,
              revisionType: revision.revisionType
          })
          .then(response => {
              console.log(response)
              this.loadData();

            }).catch(err => {
              alert('DOSLO JE DO GRESKE')
            });
    },
    showIfNeed: function(revision){
        return this.showByStatus(revision) && this.showOnlyMyRespons(revision) && this.showByrevisionType(revision)
    },
    showByStatus: function(revision){
       
        if( revision.statusOfRevision === 'PENDING' && this.showreports.pending) return true;
        if( revision.statusOfRevision === 'APPROVED' && this.showreports.approved) return true;
        if( revision.statusOfRevision === 'REJECTED' && this.showreports.rejected) return true;
       
        return false;
    },
    showOnlyMyRespons: function(revision){
        
        if(this.showreports.onlyMyResponse){
            if(revision.adminUsername === window.sessionStorage.getItem("email")){
                return true;
            }else{
                return false;
            }
        }
        
        return true;
    },
    showByrevisionType: function(revision){
       
        if( revision.ownerType === 'INSTRUCTOR' && this.showreports.instructor) return true;
        if( revision.ownerType === 'BOAT_OWNER' && this.showreports.boat_owner) return true;
        if( revision.ownerType === 'COTTAGE_OWNER' && this.showreports.cottage_owner) return true;
        
        return false;
    },
    convertDate: function(date){
        return new Date(date).toLocaleString();
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
