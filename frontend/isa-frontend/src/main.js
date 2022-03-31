import { createApp } from 'vue'
import { createStore } from 'vuex'
import App from './App.vue'
import router from './router'

import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap"
import './assets/globalStyle.css';

const store = createStore({
    state () {
      return {
        date: ''
      }
    },
    mutations: {
      increment (state, searchedDate) {
        state.date=searchedDate
      }
    }
  })

createApp(App).use(router).use(store).mount('#app')
