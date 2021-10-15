//import all file
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
import PostDetail from '../views/PostDetail.vue'
import PostDetailCompany from '../views/PostDetailCompany.vue'
import UpdateResume from '../views/UpdateResume.vue'
import UpdateCompanyInfo from '../views/UpdateCompanyInfo.vue'
import UpdatePost from '../views/UpdatePost.vue'
import ResumeCompany from '../views/ResumeCompany.vue'
Vue.use(VueRouter)

//routers
const routes = [
  {
    path: '/',
    name: 'Home',
    redirect: 'Index',
    component: Home,
    children:[
      {
        path: 'Index',
        name: 'Index',
        component: Index,

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
        path: '/ResumeCompany',
        name: 'ResumeCompany',
        component: ResumeCompany
      },
      {
        path: '/updateResume',
        name: 'UpdateResume',
        component: UpdateResume
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
        path: '/updatePost',
        name: 'UpdatePost',
        component: UpdatePost
      },
      {
        path: '/Post-Detail',
        name: 'PostDetail',
        component: PostDetail
      },
      {
        path: '/Post-Detail-Company',
        name: 'PostDetailCompany',
        component: PostDetailCompany
      },
      
      {
        path: '/create-company',
        name: 'createCompany',
        component: createCompany
      },
      {
        path: '/company-info',
        name: 'companyInfo',
        redirect: '/get-All-Company-Post',
        component: companyInfo,
        children:[
          {
            path: '/updateCompanyInfo',
            name: 'UpdateCompanyInfo',
            component: UpdateCompanyInfo
          },
          {
            path: '/get-All-Company-Post',
            name: 'getAllCompanyPost',
            component: getAllCompanyPost
          },
        ]
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
