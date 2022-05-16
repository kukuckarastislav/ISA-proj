<template>
    <div style="height: 60px;"></div>
    <div class="container">
        <div class="row">
            <div class="col-sm-3">
                <div class="card align-items-start">
                    <div class="card-body justify-content-start text-start">
                        
                        <h5>Status of Revision</h5>
                    
                        <input v-model="showRevisions.pending" class="form-check-input m-2" type="checkbox" id="idPending" checked>
                        <label class="form-check-label m-1" for="idPending"> Pending</label>
                        <br>
                        <input v-model="showRevisions.rejected" class="form-check-input m-2" type="checkbox" id="idRejected" checked>
                        <label class="form-check-label m-1" for="idRejected"> Rejected</label>
                        <br>
                        <input v-model="showRevisions.approved" class="form-check-input m-2" type="checkbox" id="idApproved" checked>
                        <label class="form-check-label m-1" for="idApproved"> Approved</label>
                        <br>

                        <hr>
                        <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
                            <input v-on:click="loadAndShowEntity()" type="radio" class="btn-check" name="btnradio" id="btnradio1" autocomplete="off" checked>
                            <label class="btn btn-outline-primary" for="btnradio1">Reservation Entity</label>

                            <input v-on:click="loadAndShowOwners()" type="radio" class="btn-check" name="btnradio" id="btnradio2" autocomplete="off">
                            <label class="btn btn-outline-primary" for="btnradio2">Owners</label>
                        </div>
                      
                        <div v-if="showRevisions.showOwners">
                            <input v-model="showRevisions.instructor" class="form-check-input m-2" type="checkbox" id="idinstructor">
                            <label class="form-check-label m-1" for="idinstructor"> Instructors</label>
                            <br>
                            <input v-model="showRevisions.cottage_owner" class="form-check-input m-2" type="checkbox" id="idcottage_owner">
                            <label class="form-check-label m-1" for="idcottage_owner"> Cottage Owners</label>
                            <br>
                            <input v-model="showRevisions.boat_owner" class="form-check-input m-2" type="checkbox" id="idboat_owner">
                            <label class="form-check-label m-1" for="idboat_owner"> Boat Owners</label>
                            <br> 
                        </div>

                        <div v-if="showRevisions.showEntity">
                            <input v-model="showRevisions.instructor" class="form-check-input m-2" type="checkbox" id="idadventures" checked>
                            <label class="form-check-label m-1" for="idadventures"> Adventures</label>
                            <br>
                            <input v-model="showRevisions.cottage_owner" class="form-check-input m-2" type="checkbox" id="idcottages" checked>
                            <label class="form-check-label m-1" for="idcottages"> Cottages</label>
                            <br>
                            <input v-model="showRevisions.boat_owner" class="form-check-input m-2" type="checkbox" id="idboats" checked>
                            <label class="form-check-label m-1" for="idboats"> Boats</label>
                            <br>
                        </div>

                        <hr>
                        <input v-model="showRevisions.onlyMyResponse" class="form-check-input m-2" type="checkbox" id="idCCottage" checked>
                        <label class="form-check-label m-1" for="idCCottage"> Only my response</label>
  
   
                    </div>
                </div>
            </div>
            <div class="col-sm-9">
                
                <div v-for="revision in revisions" :key="revision">
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
                                        <p class="card-text text-start">{{revision.comment}} Lorem ipsum dolor sit amet consectetur adipisicing elit. Sequi repellat voluptates aliquam eius dolore tenetur ea mollitia possimus, doloremque nostrum veritatis earum quo quia aspernatur asperiores? Unde voluptatum odio consectetur!</p>
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
  name: 'RevisionPage',
  components: {
    
  },
  data: function(){
    return {
        showRevisions:{
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
        revisions: [],
    }
  },
  mounted: function(){
     this.loadData();
  },
  methods: {
    loadData: function(){
        if(this.showRevisions.showEntity){
            this.loadAndShowEntity();
        }else{
            this.loadAndShowOwners();
        }
        
    },
    sendAdminResponse: function(revision, status){
        alert('SLANJE RESPONSA');
        axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
        axios
          .post('http://localhost:8180/api/revision/respons', {
              adminUsername: 'isaprojectftn+admin@gmail.com', //TODO: da ne bude hardkodirano
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
       
        if( revision.statusOfRevision === 'PENDING' && this.showRevisions.pending) return true;
        if( revision.statusOfRevision === 'APPROVED' && this.showRevisions.approved) return true;
        if( revision.statusOfRevision === 'REJECTED' && this.showRevisions.rejected) return true;
       
        return false;
    },
    showOnlyMyRespons: function(revision){
        
        if(this.showRevisions.onlyMyResponse){
            if(revision.adminUsername === 'isaprojectftn+admin@gmail.com'){     //TODO: namestiti za bilo kog admina
                return true;
            }else{
                return false;
            }
        }
        
        return true;
    },
    showByrevisionType: function(revision){
       
        if( revision.ownerType === 'INSTRUCTOR' && this.showRevisions.instructor) return true;
        if( revision.ownerType === 'BOAT_OWNER' && this.showRevisions.boat_owner) return true;
        if( revision.ownerType === 'COTTAGE_OWNER' && this.showRevisions.cottage_owner) return true;
        
        return false;
    },
    convertDate: function(date){
        return new Date(date).toLocaleString();
    },

    loadAndShowOwners: function(){
        this.showRevisions.showEntity = false;
        this.showRevisions.showOwners = true;

        axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
        axios.get('http://localhost:8180/api/revision/owners').then(resp => {
            this.revisions = resp.data;
            console.log(resp.data);
        });
    },
    loadAndShowEntity: function(){
        this.showRevisions.showEntity = true;
        this.showRevisions.showOwners = false;

        axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
        axios.get('http://localhost:8180/api/revision/entity').then(resp => {
            this.revisions = resp.data;
            console.log(resp.data);
        });
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
