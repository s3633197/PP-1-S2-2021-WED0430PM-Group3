import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token: '',
    email: '',
    roleId: null
  },
  mutations: {
    // SET_EMAIL: (state, email) => {
    //   state.email = email
    // },
    // SET_PASSWORD: (state, password) => {
    //   this.password = password
    //   // localStorage.setItem("password",password)
    // },
    SET_ROLEID: (state, roleId) => {
      state.roleId = roleId
      localStorage.setItem("roleId",roleId)
      // localStorage.setItem("password",password)
    },
    SET_TOKEN: (state,token) => {
      state.token = token
      localStorage.setItem("token",token)
    },
    // SET_TOKEN_NULL: () => {
    //   // this.state.token = ''
    //   localStorage.setItem("token",null)
    // }
  },
  // getters: {
  //   getemail: state => {
  //     return state.todos.filter(todo => todo.done)
  //   }
  // },
  actions: {
  },
  modules: {
  }
})
