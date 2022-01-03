<template>
  <!-- NAV -->
  <div class="fixed-top">
          <nav class="navbar navbar-expand-lg navbar-dark py-3 isa-color-standard">
              <div class="container">
                  <a href="/" class="navbar-brand">Fishing Online</a>

                  <div class="collapse navbar-collapse" d="navmenu">
                      <ul class="navbar-nav ms-auto">
                          <li class="nav-item">
                          <a href="/registration" class="nav-link">registration</a>
                          </li>
                      </ul>
                  </div>
              </div>
          </nav>
    
    <div v-if="typeOfUser == 'Admin'">
      <AdminLinks></AdminLinks>
    </div>
    <!-- 
    <div v-if="typeOfUser == 'CottageOwner'">
      <CottageOwnerLinks></CottageOwnerLinks>
    </div>
    -->

  </div>
  <div style="height: 72px;"></div>
  <div v-if="typeOfUser != 'None'" style="height: 40px;"></div>

  <router-view/>

  <!-- Footer -->
  <footer class="p-5 isa- text-white text-center position-relative isa-color-standard">
      <div class="container">
          <p class="lead">Copyright &copy; 2021 ISA project</p>

          <a href="#" class="position-absolute bottom-0 end-0 p-5">
          <i class="bi bi-arrow-up-circle h1"></i> Home
          </a>
      </div>
  </footer>

</template>

<script>
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap"
import AdminLinks from './views/Admin.vue'

export default {
  name: 'App',
  components: {
    AdminLinks

  },
  data: function(){
    return {
      typeOfUser: 'Admin'
    }
  }
}
</script>


<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

</style>
