import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    name: 'home',
    component: () => import(/*webpackChunkName: "login"*/ '../views/Login.vue')
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/root',
    name: 'Root',
    component: () => import(/* webpackChunkName: "root"*/'../views/RootBack.vue')
  },
  {
    path: '/root/:page/:pk?',
    name: 'Root',
    component: () => import(/* webpackChunkName: "test2"*/'../views/Root.vue')
  },
  {
    path: '/apitest/',
    name: 'ApiTest',
    component: () => import(/*webpackChunkName: "apitest"*/'../views/ApiTest.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import(/*webpackChunkName: "login"*/ '../views/Login.vue')
  },
  {
    path: '/register',
    name: "Register",
    component: () => import(/*webpackChunkName: "Register"*/ '../views/Register.vue')
  },
  {
    path: '/registerpost',
    name: "RegisterPost",
    component: () => import(/*webpackChunkName: "RegisterPost"*/ '../views/RegisterPost.vue')
  },
  {
    path: '/registeruser',
    name: "RegisterUser",
    component: () => import(/*webpackChunkName: "RegisterUser"*/ '../views/RegisterUser.vue')
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
