import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Index from '../views/Index.vue'
import SignUp from '../views/SignUp.vue'
import SignIn from '../views/SignIn.vue'
import axios from 'axios'




Vue.use(VueRouter)


const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
    children:[
      {
        path: 'Index',
        name: 'Index',
        component: Index
      },
      {
        path: '/SignIn',
        name: 'SignIn',
        component: SignIn
      },
      {
        path: '/SignUp',
        name: 'SignUp',
        component: SignUp
      }
  ]
  },
  
  
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
