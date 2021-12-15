<template>
        <section
            class="bg-dark text-light p-5 p-lg-0 pt-lg-5 text-center text-sm-start imgBack Wellcom">
            <div class="container">
                <div class="d-sm-flex align-items-center justify-content-between">
                    <div>
                        <div style="height:200px;"></div>
                        <h1>Best place for finding fishing pleasure</h1>
                        <h1><span class="isa-text-color-green">We offer cotage boat and instructor</span></h1>
                        <button
                        class="btn btn-lg isa-bg-green"
                        data-bs-toggle="modal"
                        data-bs-target="#enroll"
                        >
                        Sign up
                        </button>
                    </div>
                
                </div>
            </div>
        </section>

        <ViewCBI/>


</template>

<script>
// @ is an alias to /src
import ViewCBI from '@/components/ViewCBI.vue'

export default {
  name: 'Home',
  components: {
    ViewCBI
  }
}
</script>


<style scoped>

body::before {
  display: block;
  content: '';
  height: 60px;
}

.Wellcom {
    height: 1500px;
}

.imgBack {
    background: linear-gradient(90deg, rgba(0,0,20,1) 0%, rgba(0, 153, 255, 0.1) 60%), url('../assets/fishing.jpg');  
    background-repeat: no-repeat;
    background-size: cover;
    height: 900px; 
    background-size: 100%;
    background-position: top right, 0px -300px;
}
</style>