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
                      
                        <input v-model="showRequests.onlyMyResponse" class="form-check-input m-2" type="checkbox" id="idCCottage" checked>
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
                                    <h6 class="card-title text-start">Date: {{convertDate(user.accountDeleteRequestDTO.requestDate)}}</h6>
                                    <h6 class="card-title text-start">Country: {{user.address.country}}</h6>
                                    <h6 class="card-title text-start">City: {{user.address.city}}</h6>
                                    <h6 class="card-title text-start">Street: {{user.address.street}} {{user.address.number}}</h6>
                                    <h6 class="card-title text-start">phone: {{user.phoneNumber}}</h6>
                                </div>
                                <div class="col-sm-8">
                                    <div>
                                        <p class="card-text text-start">{{user.accountDeleteRequestDTO.reason}}</p>
                                        <div v-if="user.accountDeleteRequestDTO.deleteRequestStatus === 'PENDING'">
                                            <button v-on:click="setModalData(user)" data-bs-target="#enroll" data-bs-toggle="modal" class="btn btn-primary m-2">Respond</button>
                                        </div>
                                        <div v-else>
                                            <div class="card">
                                                <div class="card-body">
                                                    
                                                    <div class="card-text text-start rejectedCss fw-bold" v-if="user.accountDeleteRequestDTO.deleteRequestStatus === 'REJECTED'">REJECTED</div>
                                                    <div class="card-text text-start approvedCss fw-bold" v-if="user.accountDeleteRequestDTO.deleteRequestStatus === 'APPROVED'">APPROVED</div>
                                                    <p class="card-text text-start"><b>{{convertDate(user.accountDeleteRequestDTO.adminResponsDate)}}</b></p>
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
            <h5 class="modal-title" id="enrollLabel">Write to {{adminResponse.name}}</h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
              v-on:click="resetModal()"
            ></button>
          </div>
          <div class="modal-body">
            <p class="lead">Write message to user: {{adminResponse.email}}</p>
               <div class="mb-3">
                <textarea class="form-control" v-model="adminResponse.message" rows="3"></textarea>
              </div>
          </div>
          <div class="modal-footer">
               <button type="button" class="btn btn-danger" data-bs-dismiss="modal" v-on:click="reject()">Reject</button>
            <button type="button" class="btn btn-success" data-bs-dismiss="modal" v-on:click="approve()">Approve</button>
            <button v-on:click="resetModal()"
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
            >
              Cancel
            </button>
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
            onlyMyResponse: false,
            instructor: true,
            cottage_owner: true,
            boat_owner: true,
            client: true
        },
        adminResponse:{
            name: '',
            email: '',
            message: '',
            user: {},
            deleteRequestStatus: 'PENDING'
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
        this.resetModal();
    },
    sendAdminResponse: function(status){
        alert('SLANJE RESPONSA');
        axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
        axios
          .post('http://localhost:8180/api/deleteRequest/admin-response', {
              idRequest: this.adminResponse.user.accountDeleteRequestDTO.id,
              username: this.adminResponse.user.email,
              adminResponse: this.adminResponse.message,
              deleteRequestStatus: status
          })
          .then(response => {
              console.log(response)
              this.loadData();

            }).catch(err => {
              alert('DOSLO JE DO GRESKE')
            });
    },
    approve: function(){
        this.sendAdminResponse('APPROVED');
    },
    reject: function(){
        this.sendAdminResponse('REJECTED');
    },
    setModalData: function(user){
        this.adminResponse.name = user.firstName + ' ' + user.lastName;
        this.adminResponse.email = user.email;
        this.adminResponse.message = '';
        this.adminResponse.user = user;
    },
    resetModal: function(){
        this.adminResponse.name = '';
        this.adminResponse.email = '';
        this.adminResponse.message = '';
        this.adminResponse.user = {};
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
        
        if(this.showRequests.onlyMyResponse){
            if(accDelRequest.accountDeleteRequestDTO.adminUsername !== null &&
                accDelRequest.accountDeleteRequestDTO.adminUsername === 'isaprojectftn+admin@gmail.com'){
                return true;
            }else{
                return false;
            }
        }
        
        return true;
    },
    showByUserType: function(accDelRequest){
        if( accDelRequest.accountDeleteRequestDTO.userTypeISA === 'INSTRUCTOR' && this.showRequests.instructor) return true;
        if( accDelRequest.accountDeleteRequestDTO.userTypeISA === 'BOAT_OWNER' && this.showRequests.boat_owner) return true;
        if( accDelRequest.accountDeleteRequestDTO.userTypeISA === 'COTTAGE_OWNER' && this.showRequests.cottage_owner) return true;
        if( accDelRequest.accountDeleteRequestDTO.userTypeISA === 'CLIENT' && this.showRequests.client) return true;

        return false;
    },
    convertDate: function(date){
        return new Date(date).toLocaleString();
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
