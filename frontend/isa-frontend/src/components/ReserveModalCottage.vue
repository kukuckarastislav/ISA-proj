<template>
<transition name="modal-fade">
  <div centered class="modal-backdrop">
    <div class="modal">
      <header class="modal-header">
        <slot name="header">
            Reservation
        </slot>
        <button
          type="button"
          class="btn-close"
          v-on:click="close()"
        >
          x
        </button>
      </header>

      <section class="modal-body">
        <slot name="body">
        Selected date:<br><br>
        <Datepicker style="display: inline-block;zoom: 1.15;position: relative; top:0.25em; margin-right:2%; width:25em" v-model="date" range></Datepicker>
        <br>
         <br>
        <div style="max-width:50%; text-align: center;margin: auto; margin-top:2% ">
    <div style="margin-bottom:5%">Selected services:</div>
        <table class="table">
  <thead>
    <tr>
      <th scope="col">Service</th>
      <th scope="col">Price</th>
    </tr>
  </thead>
   <tbody>
   <tr v-for="s in chosenServices" :key="s.id">
      <td>{{s.name}}</td>
      <td>{{s.price}}$</td>
     </tr>
  </tbody>
  </table><br>
  </div>
          Total price: {{totalPrice}}$
          <br>
          <br>
          <br>
          <button type="button" style="zoom:1.2;background-color:coral;border-color:coral" class="btn btn-primary" v-on:click="reserve()">RESERVE</button>
        </slot>
       </section>


    </div>
  </div>
</transition>
</template>
<script>
import axios from "axios";
import Datepicker from 'vue3-date-time-picker';
    import 'vue3-date-time-picker/dist/main.css'
    import { ref, onMounted } from 'vue';
export default {
  name: "ReserveModalCottage",
  props: ['chosenServices','cottage'],
  components: {Datepicker},
  data: function () {
    return {
      date: this.$store.state.date,
      totalPrice: 0
    };
  },
  watch:{
      date(value, oldValue){
        this.calculatePrice();
      }
  },
  updated() {
    this.calculatePrice();
  },
  methods: {
    close() {
      this.$emit('close');
    },
    calculatePrice(){
        if(!this.date) return;
        this.totalPrice = 0;
        var numOfDays = Math.floor((this.date[1].getTime()-this.date[0].getTime())/ (1000 * 3600* 24));
        for(let i=0;i<this.chosenServices.length;i++){
          this.totalPrice += this.chosenServices[i].price
        }
        this.totalPrice += numOfDays * this.cottage.price.price;
    },
    reserve() {
      if(!this.date){
        alert("Morate uneti datum.")
        return;
      }
      axios.defaults.headers.common["Authorization"] =
                "Bearer " + window.sessionStorage.getItem("jwt");  
     axios
          .post('http://localhost:8180/api/client/reserveCottage',{"startTime":new Date(Date.UTC(this.date[0].getFullYear(), this.date[0].getMonth(), this.date[0].getDate(), this.date[0].getHours(), this.date[0].getMinutes())), "endTime":new Date(Date.UTC(this.date[1].getFullYear(), this.date[1].getMonth(), this.date[1].getDate(), this.date[1].getHours(), this.date[1].getMinutes())), "cottage":this.cottage, "additionalServices":this.chosenServices})
          .then(response => {
            if(response.data) alert('Uspesno ste rezervisali vikendicu.')
            else alert('Trenutno vam je onemoguceno da pravite rezervacije zbog previlikog broja penala.')
          }).catch(err => {
              alert('DOSLO JE DO GRESKE')
          }); 
    }
  }
}
</script>
<style scoped>




  .modal-backdrop {
      
    position: fixed;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    background-color: rgba(0, 0, 0, 0.3);
    display: flex;
    justify-content: center;
    align-items: center;
    
  }

  .modal {
    background: #FFFFFF;
    box-shadow: 2px 2px 20px 1px;
    overflow-x: auto;
    display: flex;
    flex-direction: column;
    height: 60%;
    width: 50%;
     position: relative;
  top: 25%;
  transform: translateY(-50%);
  
    
    
  }

  .modal-header,
  .modal-footer {
    padding: 15px;
    display: flex;
  }

  .modal-header {
    position: relative;
    border-bottom: 1px solid #eeeeee;
    color: coral;
    justify-content: space-between;
    justify-content: center;
    align-items: center;
   
  }

  .modal-footer {
    border-top: 1px solid #eeeeee;
    flex-direction: column;
    justify-content: flex-end;
  }

  .modal-body {
    position: relative;
    padding: 20px 10px;
  }

  .btn-close {
    position: absolute;
    top: 0;
    right: 0;
    border: none;
    font-size: 20px;
    padding: 10px;
    cursor: pointer;
    font-weight: bold;
    color: coral;
    background: transparent;
  }

  .btn-green {
    color: white;
    background: #4AAE9B;
    border: 1px solid #4AAE9B;
    border-radius: 2px;
  }

  .modal-fade-enter {
    opacity: 0;
  }
  .modal-fade-leave-to {
    opacity: 0;
  }

  .modal-fade-enter-active .modal-fade-leave-active {
    transition: opacity .5s ease;
  }
  .modal-fade-leave-active {
    transition: opacity .5s ease;
  }
  
</style>