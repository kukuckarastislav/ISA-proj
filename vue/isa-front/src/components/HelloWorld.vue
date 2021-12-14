<template>
  <div class="hello">
    <h1>MSG from backend: {{msgFromBackend}}</h1>
  </div>
</template>

<script>
//import axios from "axios";
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
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

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
