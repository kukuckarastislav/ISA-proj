<template>
<transition name="modal-fade">
  <div centered class="modal-backdrop">
    <div class="modal">
      <header class="modal-header">
        <slot name="header">
            Write complaint
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
            <table style = "color: #0d6efd;" class="table">
                <tbody>
                <tr>
                    <td style="text-transform:uppercase;zoom:1.5">ABOUT {{entityType}}</td>
                    <td style="zoom:1.5">ABOUT <span v-if="entityType=='adventure'" style="text-transform:uppercase"> Instructor </span> <span style="text-transform:uppercase" v-else> Owner </span> </td>
                </tr>
                <tr>
                    <td><textarea v-model = "commentEntity" style="margin-top:1em" class="form-control" placeholder="Leave comment" rows="8"></textarea></td>
                    <td><textarea v-model = "commentOwner" style="margin-top:1em" class="form-control" placeholder="Leave comment" rows="8"></textarea></td>
                </tr>
                </tbody>
            </table>
            <button type="button" style = "margin-top:2em; zoom:1.1" class="btn btn-primary" v-on:click="sendComplaint()">Send complaint</button>
        </slot>
       </section>


    </div>
  </div>
</transition>
</template>
<script>
import axios from "axios";
export default {
  name: "CustomerComplaintModal",
  props: ['entity', 'entityType'],
  data: function () {
    return {
      date: this.$store.state.date,
      totalPrice: 0,
      commentOwner: '',
      commentEntity: ''
    };
  },
  updated() {
    this.ratingEntity= 0;
    this.ratingOwner= 0;
    this.commentOwner ='',
    this.commentEntity = ''
  },
  methods: {
    close() {
        console.log(this.entity)
      this.$emit('close');
    },
    sendComplaint() {
      axios.defaults.headers.common["Authorization"] =
                "Bearer " + window.sessionStorage.getItem("jwt");
      const tempReview = new Object();
      tempReview.isFast = this.entity.isFast;
      tempReview.reservationId = this.entity.reservationId;
      tempReview.type = this.entityType;
      tempReview.entityComment = this.commentEntity;
      tempReview.overseerComment = this.commentOwner;

      if(this.entityType == 'cottage') tempReview.entityId = this.entity.cottageId;
      else if (this.entityType == 'boat') tempReview.entityId = this.entity.boatId;
      else tempReview.entityId = this.entity.adventureId;

      
      axios
          .post('http://localhost:8180/api/client/addComplaint', tempReview)
          .then(response => {alert('Uspesno ste ostavili zalbu.')
          this.entity.isComplainedOf = true;
          this.$emit('close');
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
    color: #0d6efd;
    justify-content: space-between;
    justify-content: center;
    align-items: center;
    font-size: 2.3em
   
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
    color: #0d6efd;
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

  table, th, td {
  border:none;
}
  
</style>