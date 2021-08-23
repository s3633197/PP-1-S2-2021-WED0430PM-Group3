import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    email:'',
    password:'',
    roleId:null
  },
  mutations: {
    SET_EMAIL: (state, email) => {
      state.email = email
      // localStorage.setItem("email",email)
    },
    SET_PASSWORD: (state, password) => {
      state.password = password
      // localStorage.setItem("password",password)
    },
    SET_ROLEID: (state, roleId) => {
      state.roleId = roleId
      // localStorage.setItem("password",password)
    }
  },
  actions: {
  },
  modules: {
  }
})
