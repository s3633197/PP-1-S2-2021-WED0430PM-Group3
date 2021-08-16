import { createStore } from 'vuex'
import api from '../api'

export default createStore({
  //strict: process.env.NODE_ENV !== 'production',
  state: {
    //count: 0,
    form: {},
    info: {},
    tableData: {}
  },
  mutations: {
    INCREMENT(state) {
      state.count++;
    },
    DECREMENT(state) {
      state.count--;
    },
    update(state, message) {
      state.count = message;
    },
    SETINFO(state, info) {
      state.info = info;
    },
    CHANGEINFO(state, f) {
      let path = f.path;
      let target = state.info;
      for (let i = 0; i < path.length; i++) {
        //console.log(target.name);
        target = target.children[path[i]];
      }
      //console.log(target);
      f.handle(target,f.data,state);
    },
    SETMAIN(state, main) {
      state.info.children[0].children[1].children[1].children = main.default.main;
    },
    UPDATEFORM(state, f) {
      if (f.index) {
        state.form[f.formName][f.name][f.index] = f.newVal;
      } else if (f.formType == 'Object') {
        let target = state.form[f.formName];
        let i = 0;
        for (i = 0; i < f.name.length - 1; i++) {
          target = target[f.name[i]];
        }
        target[f.name[i]] = f.newVal;
      } else {
        state.form[f.formName][f.name] = f.newVal;
      }
    },
    SETFORM(state, f) {
      //state.form = Object.assign(state.form,f);
      for(let k in f){
        if(!state.form[k]){
          state.form[k] = f[k];
        }
      }
    },
    SETTABLEDATA(state, f) {
      console.log("hear");
      if(f.name == 'excel'){
        state.tableData[f.tableName] = state.tableData.excel;
      }else{
        api.standardcurdGet(f.name, f.data).then((response) => {
          state.tableData[f.tableName] = response.data.data;
        })
      }
    },
    SETTABLEDEFAULT(state, tableName) {
      state.tableData[tableName] = { results: [], page: 0, page_size: 20, total: 0 };
    },
    FORMSETUP(state, f) {
      let path = f.path;
      let target = state.info;
      for (let i = 0; i < path.length; i++) {
        target = target.children[path[i]];
      }
      let apiarray = [];
      for (let i = 0; i < f.apis.length; i++) {
        if (f.apis[i].type == 'get') {
          apiarray.push(api.standardcurdGet(f.apis[i].name, f.apis[i].data));
        }else{
          apiarray.push(api.standardcurdPost(f.apis[i].name, f.apis[i].data));
        }
      }
      if(f.handle){
        f.handle(target, apiarray, state);
      }
    },
    APICONN(state,f){
      let path = f.path;
      let target = state.info;
      for(let i = 0; i < path.length; i++){
        target = target.children[path[i]];
      }
      let apiSendData;
      if(!state.form[f.formName] && f.formName){
        state.form[f.formName] = {};
        apiSendData = {};
      }else if(!f.formName){
        apiSendData = {};
      }else{
        apiSendData = state.form[f.formName];
      }
      if(f.preHandle){
        apiSendData = f.preHandle(apiSendData, f.data);
      }
      if(f.type == 'get' || f.type == "GET" || f.type == "Get"){
        api.standardcurdGet(f.api,apiSendData).then(
          (response) => {
            f.handle(target,response.data,state,f);
          }
        )
      }else if(f.type == 'post' || f.type == "POST" || f.type == "Post"){
        api.standardcurdPost(f.api,apiSendData).then(
          (response) => {
            f.handle(target,response.data,state,f);
          }
        )
      }
    },
    SETTABLEDATABYEXCEL(state,f){
      console.log("SETTABLEDATABYEXCEL");
      state.tableData.excel = f;
    }
  },
  actions: {
    increment(context) {
      context.commit("INCREMENT");
    },
    decrement(context) {
      context.commit("DECREMENT");
    },
    update(context, message) {
      context.commit("update", message);
    },
    setinfo(context, info) {
      context.commit("SETINFO", info);
    },
    setMain(context, main) {
      context.commit("SETMAIN", main);
    },
    changeinfo(context, f) {
      context.commit("CHANGEINFO", f);
    },
    updateForm(context, f) {
      context.commit("UPDATEFORM", f);
    },
    setForm(context, f) {
      context.commit("SETFORM", f);
    },
    setTableData(context, f) {
      context.commit("SETTABLEDATA", f);
    },
    setTableDefault(context, tableName) {
      context.commit("SETTABLEDEFAULT", tableName);
    },
    formSetup(context, f) {
      context.commit("FORMSETUP", f);
    },
    apiConn(context, f){
      context.commit("APICONN",f);
    },
    setTableDataByExcel(context, f){
      context.commit("SETTABLEDATABYEXCEL",f);
    }
  },
  modules: {
  }
})
