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
          Total price: $
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
import Datepicker from 'vue3-date-time-picker';
    import 'vue3-date-time-picker/dist/main.css'
    import { ref, onMounted } from 'vue';
export default {
  name: "ReserveModal",
  props: ['chosenServices'],
  components: {Datepicker},
  data: function () {
    return {
      date: this.$store.state.date
    };
  },
     methods: {
      close() {
          console.log(this.chosenServices)
        this.$emit('close');
      },
      reserve() {

      }
    }
}
</script>
<style>
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