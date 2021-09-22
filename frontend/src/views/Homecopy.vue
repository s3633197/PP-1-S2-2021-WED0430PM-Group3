<template>
  <div id="app">
    <div id="logoo">
      <img alt="logo" src="../assets/Logo.svg">
    </div>
    <div id="nav">
      <div id="nav1">
        <router-link to="/Index" style="margin-right:20px;">Home</router-link>
        <router-link to="/get-All-Post" style="margin-right:20px;">Posts</router-link>
        <router-link to="/get-All-Company-Post" v-if="company" style="margin-right:20px;">My Posts</router-link>
      </div>
      <div id="nav2">
        <router-link to="/SignUp" v-if="logout" class="nav22">Sign Up | </router-link> 
        <router-link to="/SignIn" v-if="logout" class="nav22">Sign In</router-link>
      </div>
      <div id="nav22">
        <el-dropdown v-if="login">
          <el-avatar :src="require('../assets/a.png')" class="avatar" :size="50"></el-avatar>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item v-if="personal"><router-link class="router-link-active" to="/profile">Profile</router-link></el-dropdown-item>
            <el-dropdown-item v-if="personal"><router-link class="router-link-active" to="/resume">Resume</router-link></el-dropdown-item>
            <el-dropdown-item v-if="company"><router-link class="router-link-active" to="/create-Post">create-Post</router-link></el-dropdown-item>
            <el-dropdown-item v-if="company"><router-link class="router-link-active" to="/create-company">create-company</router-link></el-dropdown-item>
            <el-dropdown-item v-if="company"><router-link class="router-link-active" to="/company-info">company-info</router-link></el-dropdown-item>
            <hr>
            <el-dropdown-item  id="logoutList"><el-button id="logout" type="primary" @click="logoutButton()" v-if="login">Log out</el-button></el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </div>
    <router-view/>
    <div>
      <hr>
      <span>© 2021 project one, RMIT</span>
    </div>
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  height: 100%;
  width: 100%;
  text-align: center;
}
#logoo {
  width: 20%;
  height: 78px;
  float: left;
  background-color: rgb(240, 240, 240);
  
}
#logoo img{
  width: 100%;
  height: 80%;
  margin: 0;
  padding-top: 3%;
}
#nav {
  padding: 0px;
  height: 78px;
  width: 80%;
  display: inline-block;
  background-color: rgb(240, 240, 240);
  text-align: left;
}
#nav1 {
  height: auto;
  display: inline-block;
  margin-top: 32px;
}
#nav2 {
  float: right;
  margin-right: 20px;
  margin-top: 30px;
  height: auto;
  width: auto;
}
#nav22 {
  float: right;
  margin-right: 20px;
  margin-top: 15px;
  height: auto;
  width: auto;
}
#nav a {
  font-weight: bold;
  color: #2c3e50;
  text-decoration: none;
}
#nav a.router-link-exact-active {
  color: #42b983;
  text-decoration: none;
}

#logout{
  background-color: white;
  color: black;
  border-color: rgb(240, 240, 240);
  margin: 0;
  padding: 0;
  font-weight:bold;
  font-size: 16px;
  border: none;
  width: 100%;
  height: 43px;
}
#logoutList{
  padding: 0;
}
#logout:hover {
  background-color: #ecf5ff; /* Green */

}
a {
text-decoration: none;
}
.droplist {
text-decoration: none;
color: black;
}
.avatar{
  width: 600px;
}
@media only screen and (max-width: 500px) {
#logoo img{
  height: 100%;
}
}
</style>

<script>
// @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue'
export default {
  created() {
      this.loginOrnot();
      this.personalOrCompany();
    },
  data() {
    return {
      login:false,
      logout:true,
      personal:false,
      company:false,
      
    }
  },
  methods: {
    logoutButton() {
      this.$axios.post('/logout').then(res => {
        localStorage.removeItem('token')
        localStorage.removeItem('roleId')
        this.$router.push("/index")
        this.$router.go(0)
      });
       
    },
    loginOrnot(){
      
      if(localStorage.getItem('token')==null){
        this.logout = true
        this.login = false
      }
      if(localStorage.getItem('token')!=null){
        this.login = true
        this.logout = false
      }
     
    },
    personalOrCompany(){
      
      if(localStorage.getItem('roleId')==1){
        this.personal = true
        this.company = false
      }
      if(localStorage.getItem('roleId')==2){
        this.company = true
        this.personal = false
      }
     
    }
  },
}
</script>