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
                        <br>
                        <input v-model="showUsers.client" class="form-check-input" type="checkbox" id="idClient" checked>
                        <label class="form-check-label" for="idClient"> Client</label>
                        
   
                    </div>
                </div>
            </div>
            <div class="col-sm-9">
                
                <div v-for="user in users" :key="user">
                <div v-if="showIfNeed(user)">
                    <div class="card">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-5">
                                    <h5 class="card-title text-start">{{user.firstName}} {{user.lastName}}</h5> 
                                    <br>
                                    <h6 class="card-subtitle mb-2 text-muted text-start">Email: {{user.email}}</h6>
                                    <h6 class="card-title text-start">Country: {{user.address.country}}</h6>
                                    <h6 class="card-title text-start">City: {{user.address.city}}</h6>
                                    <h6 class="card-title text-start">Street: {{user.address.street}} {{user.address.number}}</h6>
                                    <h6 class="card-title text-start">phone: {{user.phoneNumber}}</h6>
                                </div>
                                <div class="col-sm-3">
                                    <div>
                                        <h5 class="card-title text-start">{{user.userTypeISA}}</h5> 
                                        <div v-if="user.userTypeISA === 'CLIENT'">
                                            <h6 class="card-title text-start">Number of penalty now: {{user.numOfPenaltyNow}}</h6>
                                        </div>
                                        <div v-else>
                                            <h3 class="text-start stars">
                                                <span v-for="index in Math.round(user.averageGrade)" :key="index">&#9733;</span> 
                                                <span v-for="index in Math.round(5-user.averageGrade)" :key="index">&#9734;</span> 
                                                {{user.averageGrade}}
                                            </h3>
                                            <h6 v-if="user.userTypeISA === 'INSTRUCTOR'" class="card-title text-start">Biography: {{user.biography}}</h6>
                                        </div>
                                        <div>
                                            <div class="card" v-bind:class="getCssForLoyalty(user)">
                                                <div class="card-body">
                                                    <h5 class="card-title text-start">Loyalty</h5> 
                                                    <h6 class="card-title text-start"><b>{{user.loyalty.loyaltyType}}</b></h6> 
                                                    <h6 class="card-title text-start">Score {{user.loyalty.score}}</h6> 
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-1"></div>
                                <div class="col-3">
                                    <div class="card">
                                        <div class="card-body">
                                            <h5 class="card-title">Admin panel</h5>
                                            <hr>
                                            <button v-on:click="deleteAction(user)" class="btn btn-danger" style="width: 100%;">Delete</button>
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
  name: 'UsersPage',
  components: {
    
  },
  data: function(){
    return {
        showUsers:{
            instructors: true,
            boatOwners: true,
            cottageOwners: true,
            client: true,
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
         axios.get('http://localhost:8180/api/admin/user').then(resp => {
            this.users = resp.data;
            console.log(resp.data);
        });
    },
    showIfNeed: function(user){
        if( user.userTypeISA === 'COTTAGE_OWNER' && this.showUsers.cottageOwners) return true;
        if( user.userTypeISA === 'INSTRUCTOR' && this.showUsers.instructors) return true;
        if( user.userTypeISA === 'BOAT_OWNER' && this.showUsers.boatOwners) return true;
        if( user.userTypeISA === 'CLIENT' && this.showUsers.client) return true;

        return false;
    },
    getCssForLoyalty: function(user){
        if(user.loyalty.loyaltyType === 'GOLD') return 'goldCss';
        if(user.loyalty.loyaltyType === 'SILVER') return 'silverCss';
        if(user.loyalty.loyaltyType === 'REGULAR') return 'SettingsCss';
        return '';
    },
    deleteAction: function(user){
        let delUser = {
            "idUser" : user.idUser, 
            "username" :user.email,
            "userTypeISA": user.userTypeISA
        }
        console.log(delUser)
         axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
         axios.post('http://localhost:8180/api/admin/user',delUser).then(resp => {
            if(!resp.data){
                alert("Error (False)")
            }else{
                this.loadData();
            }
        }, (err) => {alert("Error")});
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

</style>
