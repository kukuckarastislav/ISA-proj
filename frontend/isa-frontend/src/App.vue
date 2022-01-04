<template>
  <!-- NAV -->
  <div class="fixed-top">
  <nav class="navbar navbar-expand-lg navbar-dark py-3 isa-color-standard">
            <div class="container">
                <a href="/" class="navbar-brand">Fishing Online</a>

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
                       <li v-if="role===''" class="nav-item">
                        <a href="/login" class="nav-link">login</a>
                        </li>
                        <li v-if="role===''" class="nav-item">
                        <a href="/registration" class="nav-link">registration</a>
                        </li>
                        <li v-if="role!==''" class="nav-item">
                        <a v-on:click="logout" class="nav-link">logout</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
  
         
    
    <div v-if="role === 'ROLE_ADMIN'">
      <AdminLinks></AdminLinks>
    </div>
    <div v-if="role === 'ROLE_CUSTOMER'">
      <CustomerLinks></CustomerLinks>
    </div>
    <!-- 
    <div v-if="role == 'ROLE_COTTAGE_OWNER'">
      <CottageOwnerLinks></CottageOwnerLinks>
    </div>
    -->

  </div>
  <div style="height: 72px;"></div>
  <div v-if="role !== ''" style="height: 40px;"></div>

  <router-view :key="$route.fullPath"/>

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
import CustomerLinks from './views/Customer.vue'

export default {
  name: 'App',
  components: {
    AdminLinks,
    CustomerLinks
  },
  mounted: function(){
      
      this.role = window.sessionStorage.getItem("role")
      if (this.role == null) this.role = ""
      console.log(this.role)
      //this.role = userInformation.role
  },
  data: function(){
    return {
      role: ''
    }
  },
  methods: {
    logout: function(){
      window.sessionStorage.clear()
      this.role = ''
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
