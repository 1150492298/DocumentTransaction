import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import store from './store'
import '@/assets/global.css'
import AppIntroHeader from "@/components/AppIntroHeader";
import SlideVerify from 'vue-monoplasty-slide-verify';

Vue.config.productionTip = false

Vue.use(SlideVerify);

Vue.use(ElementUI,{size:'small'})

Vue.component('AppIntroHeader',AppIntroHeader)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
