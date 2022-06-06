<template>
    <div style="height: 60px;"></div>
    <div class="container">
        <div class="row">
            <div class="col-sm-3">
                <div class="card align-items-start">
                    <div class="card-body justify-content-start text-start">

                        <input v-model="showEntitys.instructors" class="form-check-input m-2" type="checkbox" id="idCInstructors" checked>
                        <label class="form-check-label m-1" for="idCInstructors"> Adventures</label>
                        <br>
                        <input v-model="showEntitys.boatOwners" class="form-check-input m-2" type="checkbox" id="idCBoat" checked>
                        <label class="form-check-label m-1" for="idCBoat"> Boats</label>
                        <br>
                        <input v-model="showEntitys.cottageOwners" class="form-check-input m-2" type="checkbox" id="idCCottage" checked>
                        <label class="form-check-label m-1" for="idCCottage"> Cottages</label>
                      
                        
   
                    </div>
                </div>
            </div>
            <div class="col-sm-9">
                
                <div v-for="entity in entitys" :key="entity">
                    <div v-if="showIfNeed(entity)">
                    <div class="card">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-4">
                                        <img class="img-fluid" v-bind:src="getImg(entity)">
                                        <p class="card-title text-center">{{entity.description}}</p>
                                    </div>
                                <div class="col-sm-4" >
                                    <h5 class="card-title text-start">{{entity.entityName}}</h5> 
                                    <h5 class="card-title text-start">{{entity.ownerName}}</h5> 
                                    <br>
                                    <h6 class="card-subtitle mb-2 text-muted text-start">Email: {{entity.ownerEmail}}</h6>
                                    <h6 class="card-title text-start">Country: {{entity.address.country}}</h6>
                                    <h6 class="card-title text-start">City: {{entity.address.city}}</h6>
                                    <h6 class="card-title text-start">Street: {{entity.address.street}} {{entity.address.number}}</h6>
                                </div>
                                
                                <div class="col-sm-2">
                                    <div>
                                        <h5 class="card-title text-start">{{entity.isaEntityType}}</h5>                                         
                                        <h4 class="text-start stars">
                                            <span v-for="index in Math.round(entity.averageGrade)" :key="index">&#9733;</span> 
                                            <span v-for="index in Math.round(5-entity.averageGrade)" :key="index">&#9734;</span> 
                                            {{entity.averageGrade}}
                                        </h4>
                                        <div v-if="entity.price != undefined" class="card" style="width: 130px;">
                                            <div class="card-body">
                                            <div class="row">
                                                <div class="col cenaCss">
                                                <h3><b>${{entity.price.price}}</b></h3>
                                                <h6><b>{{entity.price.priceType}}</b></h6>
                                                </div>
                                            </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                
                                
                                <div class="col-2">
                                    <div class="card">
                                        <div class="card-body">
                                            <h6 class="card-title">Admin panel</h6>
                                            <hr>
                                            <button :disabled="entity.deleted" v-on:click="deleteAction(entity)" class="btn btn-danger" style="width: 100%;">Delete</button>
                                            <p v-if="entity.deleted" class="m-1" style="color: red; font-weight: bold;">DELETED</p>
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
  name: 'EntityPage',
  components: {
    
  },
  data: function(){
    return {
        showEntitys:{
            instructors: true,
            boatOwners: true,
            cottageOwners: true,
        },
        entitys: [],
    }
  },
  mounted: function(){
     this.loadData();
  },
  methods: {
    loadData: function(){
        axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
         axios.get('http://localhost:8180/api/admin/entity').then(resp => {
            this.entitys = resp.data;
            console.log(resp.data);
        });
    },
    showIfNeed: function(entity){
        if( entity.ownerType === 'COTTAGE_OWNER' && this.showEntitys.cottageOwners) return true;
        if( entity.ownerType === 'INSTRUCTOR' && this.showEntitys.instructors) return true;
        if( entity.ownerType === 'BOAT_OWNER' && this.showEntitys.boatOwners) return true;

        return false;
    },
    getImg: function(adventure){
        var minNum = 10000;
        var imgPath = adventure.images[0].path;
        for(var i=0; i<adventure.images.length; i++){
            var num = Number(adventure.images[i].name.split(".")[0].substring(3));
            if(num < minNum){
                minNum = num;
                imgPath = adventure.images[i].path;
            }

        }
        return 'http://localhost:8180/api/entityImage/'+imgPath;
    },
      deleteAction: function (entity) {
        let confirmAction = confirm("Are you sure? Delete " + entity.name);
        if (!confirmAction) {
            return
        }
        
        let delEntity = {
            "isaEntityType" : entity.isaEntityType, 
            "idEntity" :entity.idEntity
        }
        console.log(delEntity)
         axios.defaults.headers.common["Authorization"] = "Bearer " + window.sessionStorage.getItem("jwt");  
         axios.post('http://localhost:8180/api/admin/entity',delEntity).then(resp => {
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
.cenaCss{
  color: coral;
  font-size: small;
}

</style>
