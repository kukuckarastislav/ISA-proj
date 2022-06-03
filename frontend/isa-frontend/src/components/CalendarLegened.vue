<template>

    <div class="card">
        <div class="card-body">
        <div class="row">
            <div class="col-1" ><div class="circleColor mt-1" style="background-color: #ecffe3;"></div></div>
            <div class="col text-start"> TERM AVAILABILE</div>
        </div>
        <div class="row">
            <div class="col-1"><div class="circleColor mt-1" style="background-color: #ffe3e3;"></div></div>
            <div class="col text-start"> TERM UNAVAILABLE</div>
        </div>
        <div class="row">
            <div class="col-1"><div class="circleColor mt-1" style="background-color: #a100ba;"></div></div>
            <div class="col text-start"> RESERVATION ACTIVE</div>
        </div>
        <div class="row">
            <div class="col-1"><div class="circleColor mt-1" style="background-color: #35013d;"></div></div>
            <div class="col text-start"> RESERVATION CANCELLED</div>
        </div>
            <div class="row">
            <div class="col-1"><div class="circleColor mt-1" style="background-color: #0057ba;"></div></div>
            <div class="col text-start"> FAST RESERVATION ACTIVE</div>
        </div>
        <div class="row">
            <div class="col-1"><div class="circleColor mt-1" style="background-color: #06ba00;"></div></div>
            <div class="col text-start"> FAST RESERVATION CANCELLED</div>
        </div>
        </div>
    </div>

</template>

<script>
export default {
  name: 'CalendarLegend',
  data: function(){
   
  },
  mounted() {
   
  },
  methods: {
  }

}
</script>
<style scoped>

.circleColor{
  border-radius: 50%;
  width: 15px;
  height: 15px;
}
</style>
