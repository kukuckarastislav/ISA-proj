<template>  
    <div class="card">
        <div class="card-body">
            <div class="row">
                <div class="col-8">
                    <h5 class="card-title text-start">{{client.firstName}} {{client.lastName}}</h5> 
                    <br>
                    <h6 class="card-subtitle mb-2 text-muted text-start">Email: {{client.email}}</h6>
                    <h6 class="card-title text-start">Country: {{client.address.country}}</h6>
                    <h6 class="card-title text-start">City: {{client.address.city}}</h6>
                    <h6 class="card-title text-start">Street: {{client.address.street}} {{client.address.number}}</h6>
                    <h6 class="card-title text-start">phone: {{client.phoneNumber}}</h6>
                </div>
                <div class="col-4">
                    <div class="card" v-bind:class="getCssForLoyalty(client)">
                        <div class="card-body">
                            <h5 class="card-title text-start">Loyalty</h5> 
                            <h6 class="card-title text-start"><b>{{client.loyalty.loyaltyType}}</b></h6> 
                            <h6 class="card-title text-start">Score {{client.loyalty.score}}</h6> 
                        </div>
                    </div>
                </div>
            </div>
        </div>         
    </div>
</template>

<script>
export default {
  name: 'ClientViewComponent',
  components: {
  },
  props:[
    'client'
  ],
  data: function(){
    return {
       
    }
  },
  mounted: function(){

  },
  methods: {
    getCssForLoyalty: function(user){
        if(user.loyalty.loyaltyType === 'GOLD') return 'goldCss';
        if(user.loyalty.loyaltyType === 'SILVER') return 'silverCss';
        if(user.loyalty.loyaltyType === 'REGULAR') return 'SettingsCss';
        return '';
    },
  }
}
</script>


<style scoped>
.silverCss{
    border-color: white;
    border-width: 2px;
    border-radius: 8px;
    background: rgb(81, 81, 81);
    background: linear-gradient(15deg, rgb(82, 82, 82) 0%, rgb(139, 139, 139) 23%, rgb(222, 222, 222) 100%);
    color: white;
}

.goldCss{
    border-color: white;
    border-width: 2px;
    border-radius: 8px;
    background: rgb(168, 129, 4);
    background: linear-gradient(15deg, rgb(190, 126, 0) 0%, rgb(202, 158, 10) 23%, rgb(238, 255, 0) 100%);
    color: black;
}

.SettingsCss{
    border-color: white;
    border-width: 2px;
    border-radius: 8px;
    background: rgb(36, 23, 0);
    background: linear-gradient(15deg, rgba(2,0,36,1) 0%, rgba(9,9,121,1) 23%, rgba(0,146,255,1) 100%);
    color: white;
}
</style>
