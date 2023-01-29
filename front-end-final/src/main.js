import Vue from 'vue'
import App from './App.vue'
import { BootstrapVue } from 'bootstrap-vue'


// bootstrap 5.x npm import
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap/dist/js/bootstrap.bundle.js'

import router from './router'
import store from './store'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'



Vue.use(BootstrapVue)

// 공용함수
import { auth } from './store/auth.module';
import Vuex from 'vuex';
Vue.use(Vuex)
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

export default new Vuex.Store({
  modules: {
    auth
  },
});