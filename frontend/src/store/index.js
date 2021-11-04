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
    SET_ROLEID: (state, roleId) => {
      state.roleId = roleId
      localStorage.setItem("roleId",roleId)
    },
    SET_TOKEN: (state,token) => {
      state.token = token
      localStorage.setItem("token",token)
    },
  },
  actions: {
  },
  modules: {
  }
})
