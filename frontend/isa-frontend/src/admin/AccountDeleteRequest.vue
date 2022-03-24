<template>
    <div style="height: 60px;"></div>
    <div class="container">
        <div class="row">
            <div class="col-sm-3">
                <div class="card align-items-start">
                    <div class="card-body justify-content-start text-start">

                        <input v-model="showRequests.pending" class="form-check-input m-2" type="checkbox" id="idPending" checked>
                        <label class="form-check-label m-1" for="idPending"> Pending</label>
                        <br>
                        <input v-model="showRequests.rejected" class="form-check-input m-2" type="checkbox" id="idRejected" checked>
                        <label class="form-check-label m-1" for="idRejected"> Rejected</label>
                        <br>
                        <input v-model="showRequests.approved" class="form-check-input m-2" type="checkbox" id="idApproved" checked>
                        <label class="form-check-label m-1" for="idApproved"> Approved</label>
                        <br> <br>
                      
                        <input v-model="showRequests.client" class="form-check-input m-2" type="checkbox" id="idclient" checked>
                        <label class="form-check-label m-1" for="idclient"> Clients</label>
                        <br>
                        <input v-model="showRequests.instructor" class="form-check-input m-2" type="checkbox" id="idinstructor" checked>
                        <label class="form-check-label m-1" for="idinstructor"> Instructors</label>
                        <br>
                        <input v-model="showRequests.cottage_owner" class="form-check-input m-2" type="checkbox" id="idcottage_owner" checked>
                        <label class="form-check-label m-1" for="idcottage_owner"> Cottage Owners</label>
                        <br>
                        <input v-model="showRequests.boat_owner" class="form-check-input m-2" type="checkbox" id="idboat_owner" checked>
                        <label class="form-check-label m-1" for="idboat_owner"> Boat Owners</label>
                        <br> <br>
                      
                        <input v-on:click="onlyMyResponseClickEvent()" v-model="showRequests.onlyMyResponse" class="form-check-input m-2" type="checkbox" id="idCCottage" checked>
                        <label class="form-check-label m-1" for="idCCottage"> Only my response</label>
  
   
                    </div>
                </div>
            </div>
            <div class="col-sm-9">
                
                <div v-for="user in users" :key="user">
                    <div v-if="showIfNeed(user)" class="card">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-4">
                                    <h5 class="card-title text-start">{{user.firstName}} {{user.lastName}}</h5> 
                                    <h5 class="card-title text-start">{{user.typeOfUser}}</h5> 
                                    <br>
                                    <h6 class="card-subtitle mb-2 text-muted text-start">Email: {{user.email}}</h6>
                                    <h6 class="card-title text-start">Country: {{user.address.country}}</h6>
                                    <h6 class="card-title text-start">City: {{user.address.city}}</h6>
                                    <h6 class="card-title text-start">Street: {{user.address.street}} {{user.address.number}}</h6>
                                    <h6 class="card-title text-start">phone: {{user.phoneNumber}}</h6>
                                </div>
                                <div class="col-sm-8">
                                    <div>
                                        <p class="card-text text-start">{{user.accountDeleteRequestDTO.reason}} Lorem ipsum dolor sit amet consectetur adipisicing elit.</p>
                                        <div v-if="user.accountDeleteRequestDTO.deleteRequestStatus === 'PENDING'">
                                            <button v-on:click="approve(user)" class="btn btn-success m-2">Approve</button>
                                            <button v-on:click="setModalData(user)" data-bs-target="#enroll" data-bs-toggle="modal" class="btn btn-danger m-2">Reject</button>
                                        </div>
                                        <div v-else>
                                            <div class="card">
                                                <div class="card-body">
                                                    
                                                    <div class="card-text text-start rejectedCss fw-bold" v-if="user.accountDeleteRequestDTO.deleteRequestStatus === 'REJECTED'">REJECTED</div>
                                                    <div class="card-text text-start approvedCss fw-bold" v-if="user.accountDeleteRequestDTO.deleteRequestStatus === 'APPROVED'">APPROVED</div>
                                                    <p class="card-text text-start">By: {{user.accountDeleteRequestDTO.adminUsername}}</p>
                                                    <p class="card-text text-start">{{user.accountDeleteRequestDTO.adminResponse}} </p>
                                                </div>
                                            </div>
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

    <!-- MODAL -->
    <div
      class="modal fade"
      id="enroll"
      tabindex="-1"
      aria-labelledby="enrollLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="enrollLabel">Rejecting user {{rejectingUser.name}}</h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body">
            <p class="lead">Write reason for rejecting user: {{rejectingUser.email}}</p>
               <div class="mb-3">
                <textarea class="form-control" v-model="rejectingUser.message" rows="3"></textarea>
              </div>
          </div>
          <div class="modal-footer">
            <button v-on:click="resetModal()"
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
            >
              Cancel
            </button>
            <button type="button" class="btn btn-danger" data-bs-dismiss="modal" v-on:click="reject()">Rejection</button>
          </div>
        </div>
      </div>
    </div>

</template>

<script>
import axios from "axios";
export default {
  name: 'AccountDeleteRequest',
  components: {
    
  },
  data: function(){
    return {
        showRequests:{
            pending: true,
            approved: true,
            rejected: true,
            onlyMyResponse: true,
            instructor: true,
            cottage_owner: true,
            boat_owner: true,
            client: true
        },
        rejectingUser:{
            name: '',
            email: '',
            message: '',
            user: {}
        },
        users: [],
    }
  },
  mounted: function(){
     this.loadData();
  },
  methods: {
    loadData: function(){
        axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
        axios.get('http://localhost:8180/api/deleteRequest/allRequestsDetail').then(resp => {
            this.users = resp.data;
            console.log(resp.data);
        });
    },
    approve: function(user){

        axios
          .post('http://localhost:8180/auth/setapproved', {
              "email": user.email,
              "message": '',
              "enabled": true,
          })
          .then(response => {
              console.log(response)
              this.loadData();

            }).catch(err => {
              alert('DOSLO JE DO GRESKE')
            });
    },
    reject: function(){
          axios
          .post('http://localhost:8180/auth/setapproved', {
              "email": this.rejectingUser.user.email,
              "message": this.rejectingUser.message,
              "enabled": false
          })
          .then(response => {
              console.log(response)
              this.loadData();

            }).catch(err => {
              alert('DOSLO JE DO GRESKE')
            });
    },
    setModalData: function(user){
        this.rejectingUser.name = user.firstName + ' ' + user.lastName;
        this.rejectingUser.email = user.email;
        this.rejectingUser.message = '';
        this.rejectingUser.user = user;
    },
    resetModal: function(){
        this.rejectingUser.name = '';
        this.rejectingUser.email = '';
        this.rejectingUser.message = '';
        this.rejectingUser.user = {};
    },
    showIfNeed: function(accDelRequest){
        return this.showByStatus(accDelRequest) && this.showOnlyMyRespons(accDelRequest) && this.showByUserType(accDelRequest)
    },
    showByStatus: function(accDelRequest){
        if( accDelRequest.accountDeleteRequestDTO.deleteRequestStatus === 'PENDING' && this.showRequests.pending) return true;
        if( accDelRequest.accountDeleteRequestDTO.deleteRequestStatus === 'APPROVED' && this.showRequests.approved) return true;
        if( accDelRequest.accountDeleteRequestDTO.deleteRequestStatus === 'REJECTED' && this.showRequests.rejected) return true;
        
        return false;
    },
    showOnlyMyRespons: function(accDelRequest){
        /*
        TODO
        if(this.showRequests.onlyMyResponse){
            if(accDelRequest.accountDeleteRequestDTO.adminUsername === 'isaprojectftn+admin@gmail.com')
        }
        */
        return true
    },
    showByUserType: function(accDelRequest){
        if( accDelRequest.accountDeleteRequestDTO.userTypeISA === 'INSTRUCTOR' && this.showRequests.instructor) return true;
        if( accDelRequest.accountDeleteRequestDTO.userTypeISA === 'BOAT_OWNER' && this.showRequests.boat_owner) return true;
        if( accDelRequest.accountDeleteRequestDTO.userTypeISA === 'COTTAGE_OWNER' && this.showRequests.cottage_owner) return true;
        if( accDelRequest.accountDeleteRequestDTO.userTypeISA === 'CLIENT' && this.showRequests.client) return true;

        return false;
    },
    onlyMyResponseClickEvent: function(){
        //alert('' + this.showRequests.onlyMyResponse)
    }
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

</style>
