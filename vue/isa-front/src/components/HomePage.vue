<template>
    <div>
        <nav class="navbar navbar-expand-lg navbar-dark py-3 fixed-top isa-color-standard">
            <div class="container">
                <a href="#" class="navbar-brand">Fishing Online</a>

                <button
                class="navbar-toggler"
                        type="button"
                data-bs-toggle="collapse"
                data-bs-target="#navmenu"
                >
                <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" d="navmenu">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item">
                        <a href="#learn" class="nav-link">What You'll Learn</a>
                        </li>
                        <li class="nav-item">
                        <a href="#questions" class="nav-link">Questions</a>
                        </li>
                        <li class="nav-item">
                        <a href="#instructors" class="nav-link">Instructors</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

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






            <!-- Footer -->
        <footer class="p-5 isa- text-white text-center position-relative isa-color-standard">
            <div class="container">
                <p class="lead">Copyright &copy; 2021 ISA project</p>

                <a href="#" class="position-absolute bottom-0 end-0 p-5">
                <i class="bi bi-arrow-up-circle h1"></i> Home
                </a>
            </div>
        </footer>


    </div>
</template>

<script>
//import axios from "axios";
import ViewCBI from './ViewCBI.vue'
export default {
  name: 'HomePage',
  data: function(){
    return {
      msgFromBackend: ''
    }
  },
  components: {
    ViewCBI
  },
  mounted() {
    /*
    axios.get('http://localhost:8180/helloTest')
			.then(response => (this.msgFromBackend = response.data)).catch(err => (alert(err)));
      */
  },
  methods: {

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
    /*background-image: linear-gradient(90deg, rgba(131,53,124,1), url('../assets/fishing.jpg'));    */
    background: linear-gradient(90deg, rgba(0,0,20,1) 0%, rgba(0, 153, 255, 0.1) 60%), url('../assets/fishing.jpg');  
    background-repeat: no-repeat;
    background-size: cover;
    height: 900px; 
    background-size: 100%;
    background-position: top right, 0px -300px;
}




</style>
