<template>
    <div style="height: 60px;"></div>
    <div class="container">
        <div class="row">
            <div class="col-sm-3">
                <div class="card align-items-start">
                    <div class="card-body justify-content-start text-start">
                        
                        <h5>Status of Complaint</h5>
                    
                        <input v-model="showComplaints.answered" class="form-check-input m-2" type="checkbox" id="idAnswered" checked>
                        <label class="form-check-label m-1" for="idAnswered"> Answered</label>
                        <br>
                        <input v-model="showComplaints.unanswered" class="form-check-input m-2" type="checkbox" id="idUnanswered" checked>
                        <label class="form-check-label m-1" for="idUnanswered"> Unanswered</label>
                        <br>

                        <hr>
                        <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
                            <input v-on:click="ShowEntity()" type="radio" class="btn-check" name="btnradio" id="btnradio1" autocomplete="off" checked>
                            <label class="btn btn-outline-primary" for="btnradio1">Complaint Entity</label>

                            <input v-on:click="ShowOwners()" type="radio" class="btn-check" name="btnradio" id="btnradio2" autocomplete="off">
                            <label class="btn btn-outline-primary" for="btnradio2">Owners</label>
                        </div>
                      
                        <div v-if="showComplaints.showOwners">
                            <input v-model="showComplaints.instructor" class="form-check-input m-2" type="checkbox" id="idinstructor">
                            <label class="form-check-label m-1" for="idinstructor"> Instructors</label>
                            <br>
                            <input v-model="showComplaints.cottage_owner" class="form-check-input m-2" type="checkbox" id="idcottage_owner">
                            <label class="form-check-label m-1" for="idcottage_owner"> Cottage Owners</label>
                            <br>
                            <input v-model="showComplaints.boat_owner" class="form-check-input m-2" type="checkbox" id="idboat_owner">
                            <label class="form-check-label m-1" for="idboat_owner"> Boat Owners</label>
                            <br> 
                        </div>

                        <div v-if="showComplaints.showEntity">
                            <input v-model="showComplaints.instructor" class="form-check-input m-2" type="checkbox" id="idadventures" checked>
                            <label class="form-check-label m-1" for="idadventures"> Adventures</label>
                            <br>
                            <input v-model="showComplaints.cottage_owner" class="form-check-input m-2" type="checkbox" id="idcottages" checked>
                            <label class="form-check-label m-1" for="idcottages"> Cottages</label>
                            <br>
                            <input v-model="showComplaints.boat_owner" class="form-check-input m-2" type="checkbox" id="idboats" checked>
                            <label class="form-check-label m-1" for="idboats"> Boats</label>
                            <br>
                        </div>

                        <hr>
                        <input v-model="showComplaints.onlyMyResponse" class="form-check-input m-2" type="checkbox" id="idCCottage" checked>
                        <label class="form-check-label m-1" for="idCCottage"> Only my response</label>
  
   
                    </div>
                </div>
            </div>
            <div class="col-sm-9">
                
                <div v-for="complaint in complaints" :key="complaint">
                    <div v-if="showIfNeed(complaint)" class="card">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="card-text text-start" style="width: 40px;" v-if="complaint.revisionType==='OWNER'">
                                        <img src="../assets/abstract-user.png" class="img-fluid rounded-start" >
                                    </div>
                                    <h5 v-else class="card-title text-start">{{complaint.nameOfService}}</h5> 
                                    <h6 class="card-title text-start">Owner: {{complaint.providerEmail}}</h6> 
                                    <h6 class="card-title text-start">Client: {{complaint.userEmail}}</h6> 
                                </div>
                            </div>

                            <div class="row">
                                <div>
                                    <div class="card m-1">
                                        <div class="card-body">
                                            <p class="card-text text-start"><b>{{convertDate(complaint.createdAt)}}</b></p>
                                            <p class="card-text text-start">{{complaint.comment}}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div>                                        
                                    <div v-if="complaint.statusOfComplaint === 'UNANSWERED'">
                                        <button v-on:click="setModalData(complaint)" class="btn btn-primary m-2" data-bs-target="#enroll" data-bs-toggle="modal">Answer</button>
                                    </div>
                                    <div v-else>
                                        <div class="card m-1">
                                            <div class="card-body">
                                                <p class="card-text text-start"><b>{{convertDate(complaint.adminResponsDate)}}</b> By: {{complaint.adminEmail}}</p>
                                                <p class="card-text text-start">{{complaint.adminResponse}}</p>
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
            <h5 class="modal-title" id="enrollLabel">Write answer to complaint</h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
              v-on:click="resetModal()"
            ></button>
          </div>
          <div class="modal-body">
            <p class="lead">Message will be send to</p>
            <p class="">user: {{adminResponse.userEmail}}</p>
            <p class="">owner: {{adminResponse.providerEmail}}</p>
            <div class="card">
                <div class="card-body">
                    <h4>Comment</h4>
                    <p>{{adminResponse.comment}} Lorem ipsum dolor sit amet consectetur, adipisicing elit. Doloremque debitis libero voluptatum ipsum sequi adipisci expedita corrupti similique! Illum harum consequatur rerum quidem voluptatum laborum aut sed, corrupti non eum?</p>
                </div>
            </div>
               <div class="mb-3">
                <textarea class="form-control" v-model="adminResponse.message" rows="3"></textarea>
              </div>
          </div>
          <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-bs-dismiss="modal" v-on:click="AnswerOnComplaint()">Send</button>
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
  name: 'ComplaintPage',
  components: {
    
  },
  data: function(){
    return {
        showComplaints:{
            showEntity: true,
            showOwners: false,
            
            answered: true,
            unanswered: true,

            onlyMyResponse: false,

            instructor: true,
            cottage_owner: true,
            boat_owner: true,

        },
        adminResponse:{
            id: 0,
            userEmail: '',
            providerEmail: '',
            message: 'Your anser ...',
            comment: '',
        },
        complaints: [],
    }
  },
  mounted: function(){
     this.loadData();
  },
  methods: {
    AnswerOnComplaint: function(){

        axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
        axios
          .post('http://localhost:8180/api/complaint/respons', {
             "id": this.adminResponse.id,
             "response": this.adminResponse.message,
             "adminEmail": window.sessionStorage.getItem("email")
          })
          .then(response => {
              console.log(response)
              this.loadData();

            }).catch(err => {
              alert('DOSLO JE DO GRESKE')
            });
            
           this.resetModal()
    },
    showIfNeed: function(complaint){
        return this.showByStatus(complaint) && this.showOnlyMyRespons(complaint) && this.showBycomplaintType(complaint) && this.showByType(complaint)
    },
    showByStatus: function(complaint){
       
        if( complaint.statusOfComplaint === 'UNANSWERED' && this.showComplaints.unanswered) return true;
        if( complaint.statusOfComplaint === 'ANSWERED' && this.showComplaints.answered) return true;
       
        return false;
    },
    showOnlyMyRespons: function(complaint){
        
        if(this.showComplaints.onlyMyResponse){
            if(complaint.adminEmail === window.sessionStorage.getItem("email")){     
                return true;
            }else{
                return false;
            }
        }
        
        return true;
    },
    showBycomplaintType: function(complaint){
       
        if( complaint.providerType === 'INSTRUCTOR' && this.showComplaints.instructor) return true;
        if( complaint.providerType === 'BOAT_OWNER' && this.showComplaints.boat_owner) return true;
        if( complaint.providerType === 'COTTAGE_OWNER' && this.showComplaints.cottage_owner) return true;
        
        return false;
    },
    showByType: function(complaint){
        if(complaint.revisionType === 'ENTITY' && this.showComplaints.showEntity) return true;
        if(complaint.revisionType === 'OWNER' && this.showComplaints.showOwners) return true;

        return false;
    },
    ShowEntity: function(){
        this.showComplaints.showEntity = true;
        this.showComplaints.showOwners = false;
    },
    ShowOwners: function(){
        this.showComplaints.showEntity = false;
        this.showComplaints.showOwners = true;
    },
    convertDate: function(date){
        return new Date(date).toLocaleString();
    },
    setModalData: function(complaint){
        this.adminResponse.id = complaint.id
        this.adminResponse.userEmail = complaint.userEmail
        this.adminResponse.providerEmail = complaint.providerEmail
        this.adminResponse.message = 'Your anser ...'
        this.adminResponse.comment = complaint.comment
    },
    resetModal: function(){
        this.adminResponse.id = 0
        this.adminResponse.userEmail = ''
        this.adminResponse.providerEmail = ''
        this.adminResponse.message = ''
        this.adminResponse.comment = ''
    },
    loadData: function(){
        axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
        axios.get('http://localhost:8180/api/complaint').then(resp => {
            this.complaints = resp.data;
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
