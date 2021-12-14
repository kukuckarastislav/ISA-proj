<template>
    <div>

        <div style="height: 80px;"></div>

        <section class="text-light p-5">
            <div class="container">
                <div class="row">
                    <div class="d-md-flex align-items-center justify-content-center">
                        <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
                            <input type="radio" class="btn-check" name="btnradio" id="btnradio1" autocomplete="off" checked>
                            <label class="btn btn-outline-primary" for="btnradio1">Cotages</label>

                            <input type="radio" class="btn-check" name="btnradio" id="btnradio2" autocomplete="off">
                            <label class="btn btn-outline-primary" for="btnradio2">Boats</label>

                            <input type="radio" class="btn-check" name="btnradio" id="btnradio3" autocomplete="off">
                            <label class="btn btn-outline-primary" for="btnradio3">Instructors</label>
                        </div>
                    </div>
                </div>
            </div>
        </section>



        <section class="text-light p-5">
            <div class="container ">
                    <h3 class="mb-3 mb-md-0 isa-text-color-darkBlue">Neki parametri za pretgragu???</h3>
                
                <div class="d-md-flex align-items-center">

                    <div class="input-group news-input">
                        <input type="text" class="form-control" placeholder="parametar pretrage 1" />
                        <input type="text" class="form-control" placeholder="parametar pretrage 2" />
                        <input type="text" class="form-control" placeholder="parametar pretrage 3" />
                        <button class="btn btn-dark btn-lg" type="button">Search</button>
                    </div>
                </div>
                <div class="form-check form-switch">
                    <label class="form-check-label isa-text-color-darkBlue" for="flexSwitchCheckDefault">Default switch checkbox input</label>
                    <input class="form-check-input" type="checkbox" id="flexSwitchCheckDefault">
                </div>
            </div>
        </section>



    <section id="cardss" class="p-5 isa-color-standard">
        <div class="container">
            <div class="row g-4">

               
                <div  v-for="n in 10" :key="n" class="col-md-6 col-lg-4">
                    <div class="card bg-light">
                        <div class="card-body text-center">
                            <img src="../assets/apartman.jpg" class="imgCard" alt=""/>
                            <h3 class="card-title mb-3">Vikendica</h3>
                            <p class="card-text">
                            Lorem ipsum dolor sit amet consectetur adipisicing elit.
                            Assumenda accusamus nobis sed cupiditate iusto? Quibusdam.</p>
                            <button class="btn isa-btn-more-detail btn-sm" type="button">More details</button>
                        </div>
                    </div>
                </div>




            </div>
        </div>
    </section>



    <div class="btn-toolbar justify-content-center isa-color-standard " role="toolbar" aria-label="Toolbar with button groups">
        <div class="btn-group" role="group" aria-label="First group">
            <button type="button" class="btn btn-outline-secondary">1</button>
            <button type="button" class="btn btn-outline-secondary">2</button>
            <button type="button" class="btn btn-outline-secondary">3</button>
            <button type="button" class="btn btn-outline-secondary">4</button>
        </div>
    </div>
    <div style="height:80px;" class="isa-color-standard"></div>



    </div>
</template>

<script>
//import axios from "axios";
export default {
  name: 'ViewCBI',
  data: function(){
    return {
      msgFromBackend: ''
    }
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
.imgCard{
    width: 380px;
    height: 250px;
}

</style>
