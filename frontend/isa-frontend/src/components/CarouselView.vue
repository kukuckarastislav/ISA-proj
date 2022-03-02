<template>

    <!-- 
        Ovu komponentu treba koristiti kada nam treba za slike, poziva se ovako
        <CarouselView v-bind:images="adventure.images"></CarouselView>
    -->




    <div class="size">
        <!-- 
            d-none - sakriva
            d-block - prikazuje
        -->
        <div class="card">
            <div>
                <div v-for="image in imagesDTO" :key="image">
                    <img v-bind:src="setImg(image)" class="card-img-top img-fluid fade" v-bind:class="{ 'd-none': !image.visible, 'd-block': image.visible }">
                </div>
            
                <button v-on:click="slectImage(-1)" class="carousel-control-prev" type="button">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>

                <button v-on:click="slectImage(1)" class="carousel-control-next" type="button">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>
            
            <div class="card-body">
                
                <div class="btn-group" role="group" aria-label="First group" v-for="(image, i) in imagesDTO" :key="(image, i)">
                    <button v-on:click="setImageByButton(image, i)" type="radio" class="btn" v-bind:class="{'btn-secondary':image.visible, 'btn-outline-secondary':!image.visible}">{{i+1}}</button>
                </div>
            
            </div>
            
        </div>
         
    </div>
</template>

<script>
export default {
  props: [
      'images',
      'server'
  ],
  name: 'CarouselView',
  data: function(){
    return {
       imagesDTO: [],
       itImg: 0,

    }
  },
    watch: { 
      	images: function(newVal, oldVal) { // watch it
            this.imagesDTO = [];
            let i = 0;
            console.log('hello');
            for (const img of newVal){
                if(i==0){
                    this.imagesDTO.push({'img':img, 'visible':true})    
                }else{
                    this.imagesDTO.push({'img':img, 'visible':false})
                }
                i++;
            }
        },
        server: function(newVal, oldVal){
            console.log('promena');
        }
    },
  mounted() {
   console.log('MOUNTED FROM IMAGESS');
  },
  methods: {
      setImg: function(image){
          if(this.server == true){
              console.log('i must call server');
              return 'http://localhost:8180/'+image.img.path;
          }else{
              console.log('local img on my pc');
              return image.img.path;
          }

      },
      slectImage: function(n){
          if(this.itImg + n < 0 || this.itImg + n >= this.imagesDTO.length) return

          for (const img of this.imagesDTO) { img.visible = false; }
          
          this.itImg = this.itImg + n;
          this.imagesDTO[this.itImg].visible = true;
      },
      setImageByButton: function(image, i){
          for (const img of this.imagesDTO)
            img.visible = false;
          
          this.itImg = i;
          image.visible = true;
      }
  }

}
</script>
<style scoped>

.size{
    width: 100%;
}

/* Fading animation */
.fade {
    opacity: 1;
  -webkit-animation-name: fade;
  -webkit-animation-duration: 0.6s;
  animation-name: fade;
  animation-duration: 0.6s;
}

@-webkit-keyframes fade {
  from {opacity: .4} 
  to {opacity: 1}
}

@keyframes fade {
  from {opacity: .4} 
  to {opacity: 1}
}

</style>
