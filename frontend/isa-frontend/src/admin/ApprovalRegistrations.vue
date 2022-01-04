<template>
    <div style="height: 60px;"></div>
    <div class="container">
        <div class="row">
            <div class="col-sm-3">
                <div class="card align-items-start">
                    <div class="card-body justify-content-start text-start">

                        <input v-model="showUsers.instructors" class="form-check-input" type="checkbox" id="idCInstructors" checked>
                        <label class="form-check-label" for="idCInstructors"> Instructors</label>
                        <br>
                        <input v-model="showUsers.boatOwners" class="form-check-input" type="checkbox" id="idCBoat" checked>
                        <label class="form-check-label" for="idCBoat"> Boat Owners</label>
                        <br>
                        <input v-model="showUsers.cottageOwners" class="form-check-input" type="checkbox" id="idCCottage" checked>
                        <label class="form-check-label" for="idCCottage"> Cottage Owners</label>
  
   
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
                                        <p class="card-text text-start">{{user.registrationMotivation}}</p>
                                        <div>
                                            <button v-on:click="approve(user)" class="btn btn-success m-2">Approve</button>
                                            <button v-on:click="setModalData(user)" data-bs-target="#enroll" data-bs-toggle="modal" class="btn btn-danger m-2">Reject</button>
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
  name: 'ApprovalRegistrations',
  components: {
    
  },
  data: function(){
    return {
        showUsers:{
            instructors: true,
            boatOwners: true,
            cottageOwners: true,
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
         axios.get('http://localhost:8180/auth/noenabled').then(resp => {
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
    showIfNeed: function(user){
        if( user.typeOfUser === 'ROLE_COTTAGE_OWNER' && this.showUsers.cottageOwners) return true;
        if( user.typeOfUser === 'ROLE_INSTRUCTOR' && this.showUsers.instructors) return true;
        if( user.typeOfUser === 'ROLE_BOAT_OWNER' && this.showUsers.boatOwners) return true;

        return false;
    }
  }
}
</script>


<style scoped>



</style>
