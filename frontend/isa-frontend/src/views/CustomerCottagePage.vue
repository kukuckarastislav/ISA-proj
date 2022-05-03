<template>
    <div style="height: 60px;"></div>
    <div class="container">
        <div class="row">
            <div class="col-sm-6">

            <CarouselView v-bind:server="true" v-bind:images="cottage.images"></CarouselView>
            

            </div>
            <div v-if="cottage.name != undefined" class="col-sm-6">

                <h1 class="text-start">{{cottage.name}}</h1> <br>

                <h3 class="text-start stars">
                  <span v-for="index in Math.round(cottage.averageGrade)" :key="index">&#9733;</span> 
                  <span v-for="index in Math.round(5-cottage.averageGrade)" :key="index">&#9734;</span> 
                  {{cottage.averageGrade}}
                </h3>
                
                <h5 class="text-start">Description: {{cottage.description}} </h5>
                <br>
                
                <h5 class="text-start">Behaviour: {{cottage.behaviourRules}}</h5>
                <h5 class="text-start">Max number of people: {{cottage.capacity}}</h5>
                
                

                <br>
                <div style="margin-top:4.5em" class="row">
                  <div class="col-sm-3">
                      <div v-if="cottage.price != undefined" class="card">
                        <div class="card-body">
                          <div class="row">
                            <div class="col cenaCss">
                              <h2><b>${{cottage.price.price}}</b></h2>
                              <h6><b>{{cottage.price.priceType}}</b></h6>
                            </div>
                          </div>
                        </div>
                      </div>
                  </div>
                  <div class="col-sm-9">
                       <div class="card">
                        <div class="card-body">
                          <div class="row">
                            <div class="col-sm-2">
                              <img src="../assets/abstract-user.png" class="img-fluid rounded-start" >
                            </div>
                            <div class="col-sm-10">
                                <h5 class="card-title text-start">Owner: {{cottage.owner.firstName}} {{cottage.owner.lastName}}</h5>
                                <h6 class="card-title text-start">Tel: {{cottage.owner.phoneNumber}}
                                    
                                </h6>
                            </div>
                        </div>
                      </div>
                    </div>
                  </div>

                </div>
            </div>
        </div>


        <br> <br> <br>
        <div class="row">
          <div class="col">
            <div class="card">
              <div class="card-body">
                  <div style="height: 200px;" class="row">
                      <div class="col-sm-9">
                          <div style="background-color: gray; height:100%;"> PROSTOR ZA MAPU ?</div>
                      </div>
                      <div v-if="cottage.address != undefined" class="col-sm-3">
                          <h6 class="card-title text-start">Country: {{cottage.address.country}}</h6>
                          <h6 class="card-title text-start">City: {{cottage.address.city}}</h6>
                          <h6 class="card-title text-start">Street: {{cottage.address.street}} {{cottage.address.number}}</h6>
                      </div>
                  </div>
              </div>         
            </div>
          </div>
        </div>
        
        <!-- PriceList -->
        <br>
        <div v-if="role==='ROLE_CUSTOMER'" class="row">
          <div class="col">
            <h2 class="text-start">Price list</h2>
            <div style="text-align:left;color: coral;">Please select additional services you'd like to use:</div>
            <div class="row row-cols-auto">
              <div style="cursor: pointer;" v-on:click="selectPriceItem(index)" v-for="(priceItem,index) in cottage.additionalServices" :key="priceItem" :id="'priceItem'+index" class="priceItem">
                <span v-if="inItemList(index)" style="color:green"> Item added</span>
                <h2 style="font-weight: bold">{{priceItem.name}}</h2>
                <h5>{{priceItem.description}}</h5>
                <h2 style="font-weight: bold">${{priceItem.price}}</h2>
              </div>
            </div>
          </div>
        </div>


        <div v-else class="row">
          <div class="col">
            <h2 class="text-start">Price list</h2>
            <div class="row row-cols-auto">
              <div v-for="(priceItem,index) in cottage.additionalServices" :key="priceItem" :id="'priceItem'+index" class="priceItem">
                <h2 style="font-weight: bold">{{priceItem.name}}</h2>
                <h5>{{priceItem.description}}</h5>
                <h2 style="font-weight: bold">${{priceItem.price}}</h2>
              </div>
            </div>
          </div>
        </div>

        
        <br>
        <br>
        <br>
        <button v-if="role==='ROLE_CUSTOMER'" type="button" style="zoom:1.5;background-color:coral;border-color:coral" class="btn btn-primary" v-on:click="showModal()">RESERVE</button>
        <br>
        <br>
        <br>
        <br>
    
    <div>
       <h2 class="text-start">Actions</h2>
         <table v-for="a in actions"
            :key="a.startTime" style="border: 1px solid gray; width: 50%; margin-left:25%; margin-top: 5%;text-align:left" class="table">
    <tbody>
      <tr>
        <td style="border:none;" colspan="2">{{(new Date(a.startTime)).getDate()}}/{{(new Date(a.startTime)).getMonth()+1}}/{{(new Date(a.startTime)).getFullYear()}} {{(new Date(a.startTime)).getHours()}}:{{(new Date(a.startTime)).getMinutes()}}-{{(new Date(a.endTime)).getDate()}}/{{(new Date(a.endTime)).getMonth()+1}}/{{(new Date(a.endTime)).getFullYear()}} {{(new Date(a.endTime)).getHours()}}:{{(new Date(a.endTime)).getMinutes()}}</td>
        <td style="border:none;text-align:center">{{a.discount}}% OFF</td>
      </tr>
      <tr>
        
        <td style="border:none;width:26%;">Price:
        </td>
        <td style="border:none;width:24%;" >{{a.newPrice}}$ <span class="strikethrough">{{a.originalPrice}}$</span></td>
        <td style="border:none;width:50%;"></td>
        </tr>
      <tr>
       
        <td style="border:none;">People:
        </td>
        <td style="border:none;">up to {{a.maxPeople}}</td>
        <td style="border:none;padding-right:1em;text-align:right"><button v-if="role==='ROLE_CUSTOMER'" v-on:click="reserveAction(a)" style="background-color:coral;border-color:coral" class="btn btn-primary">RESERVE</button></td>
        </tr>
       <tr>
        
        <td style="border:none;">Additional services:
        </td>
        <td style="border:none;"><div style="display: inline-block; border: 1px solid #0d6efd;margin-right:1em;margin-bottom:0.5em" v-for="service in a.additionalServices" :key="service.id">
          {{service.name}}
          </div></td>
        <td style="border:none;"></td>
        </tr>
    </tbody>
  </table>
      </div>
    <ReserveModalCottage
      v-show="isModalVisible"
      @close="closeModal"
      v-bind:chosenServices="chosenServices"
      v-bind:cottage="cottage"
    />
    </div>
    

</template>

<script>
import axios from "axios";
import CarouselView from '@/components/CarouselView.vue'
import ReserveModalCottage from '@/components/ReserveModalCottage.vue';
export default {
  name: "CustomerCottagePage",
  components: {CarouselView,ReserveModalCottage},
  data: function () {
    return {
      cottage: {},
      cottageId: 0,
      isFetching: true,
      role: '',
      indexList: [],
      isModalVisible: false,
      chosenServices: [],
      actions: []
    };
  },
  mounted: function () {
    this.cottageId = decodeURI(window.location.pathname.split("/")[2]);
    
    axios
      .get("http://localhost:8180/api/person/cottages/" + this.cottageId)
      .then((response) => {
        this.isFetching = false
        this.cottage = response.data;
        this.role = window.sessionStorage.getItem("role")
        if (this.role == null) this.role = ""
        axios
          .get('http://localhost:8180/api/person/cottages/' + this.cottageId+'/fastReservations')
          .then(response => {
              this.actions = response.data
          }).catch(err => {
              alert('DOSLO JE DO GRESKE')
          });
      })
      .catch((err) => alert(err));
  },
  methods: {
    setImg: function (image) {
      return "http://localhost:8180/api/entityImage/" + image.path;
    },
    selectPriceItem: function(index){
      if(this.indexList.includes(index)){
        this.indexList.splice(this.indexList.indexOf(index), 1);
      }
      else this.indexList.push(index)
    },
    inItemList: function(index){
      if(this.indexList.includes(index)){
        return true;
      }
      return false;
    },
    showModal() {
      this.chosenServices=[]
        for(let k=0;k<this.indexList.length;k++){
          this.chosenServices.push(this.cottage.additionalServices[this.indexList[k]])
        }
        this.isModalVisible = true;
      },
      closeModal() {
        this.isModalVisible = false;
      },
      reserveAction(ac){
         axios.defaults.headers.common["Authorization"] =
                "Bearer " + window.sessionStorage.getItem("jwt");  
     axios
          .post('http://localhost:8180/api/client/reserveCottageAction',ac)
          .then(response => {alert('Uspesno ste rezervisali vikendicu.')
          }).catch(err => {
              alert('Već ste otkazali ovu akciju.')
          });
      }
  },
};
</script>


<style scoped>
.priceItem{
  font-weight: bold;
  padding: 20px 0px 0px 0px;
  margin: 15px;
  width: 176px;
  height: 180px;

  background: #E7E7E7;
  box-shadow: 0px 16px 16px rgba(0, 0, 0, 0.25);
  border-radius: 28px;
}

.stars{
  color: coral;
}

.cenaCss{
  color: coral;
}

.strikethrough {
  position: relative;
}
.strikethrough:before {
  position: absolute;
  content: "";
  left: 0;
  top: 50%;
  right: 0;
  border-top: 1px solid;
  border-color: inherit;
  
  -webkit-transform:rotate(-15deg);
  -moz-transform:rotate(-15deg);
  -ms-transform:rotate(-15deg);
  -o-transform:rotate(-15deg);
  transform:rotate(-15deg);
}
</style>