import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Index from '../views/Index.vue'
import SignUp from '../views/SignUp.vue'
import SignIn from '../views/SignIn.vue'
import Profile from '../views/Profile.vue'
import Resume from '../views/Resume.vue'
import createPost from '../views/createPost.vue'
import getAllPost from '../views/getAllPost.vue'
import getAllCompanyPost from '../views/getAllCompanyPost.vue'
import createCompany from '../views/createCompany.vue'
import companyInfo from '../views/companyInfo.vue'
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
      },
      {
        path: '/Profile',
        name: 'Profile',
        component: Profile
      },
      {
        path: '/Resume',
        name: 'Resume',
        component: Resume
      },
      {
        path: '/create-Post',
        name: 'createPost',
        component: createPost
      },
      {
        path: '/get-All-Post',
        name: 'getAllPost',
        component: getAllPost
      },
      {
        path: '/get-All-Company-Post',
        name: 'getAllCompanyPost',
        component: getAllCompanyPost
      },
      {
        path: '/create-company',
        name: 'createCompany',
        component: createCompany
      },
      {
        path: '/company-info',
        name: 'companyInfo',
        component: companyInfo
      },
  ]
  },
  
  
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
