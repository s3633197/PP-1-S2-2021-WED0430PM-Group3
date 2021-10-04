<template>
  <div>
    <div id="nav">
        <img alt="logo" src="../assets/logo.png">
        <div class="navleft">
          <router-link to="/Index">Home</router-link>
          <router-link to="/get-All-Post">Posts</router-link>
          <router-link to="/get-All-Company-Post" v-if="company">My Posts</router-link>
          <b v-if="personal" style="color:whitesmoke;font-size:18px;float:right;">Current login as <b style="color:#42b983;">personal</b> !</b>
          <b v-if="company" style="color:whitesmoke;font-size:18px;float:right;">Current login as  <b style="color:#42b983;">company</b> !</b>
        </div>
        <div class="navright1" v-if="logout">
          <router-link to="/SignUp">Sign Up</router-link> 
          <router-link to="/SignIn">Sign In</router-link>
        </div>
        <div class="navright2" v-if="login">
          <el-dropdown>
            <el-avatar :src="require('../assets/a.png')" class="avatar" width="10%"></el-avatar>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item v-if="personal"><router-link class="droplist" to="/profile">Profile</router-link></el-dropdown-item>
              <el-dropdown-item v-if="personal"><router-link class="droplist" to="/resume">Resume</router-link></el-dropdown-item>
              <el-dropdown-item v-if="company"><router-link class="droplist" to="/create-Post">create-Post</router-link></el-dropdown-item>
              <el-dropdown-item v-if="company"><router-link class="droplist" to="/create-company">create-company</router-link></el-dropdown-item>
              <el-dropdown-item v-if="company"><router-link class="droplist" to="/company-info">company-info</router-link></el-dropdown-item>
              <hr>
              <el-dropdown-item  id="logoutList"><el-button id="logout" type="primary" @click="logoutButton()" v-if="login">Log out</el-button></el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
        
    </div>
    <router-view class="back"/>
    <div style="text-align:center;background-color:#42b983;padding:20px;margin-top:0px;">
      <span>© 2021 project one, RMIT</span>
    </div>
  </div>
</template>

<style  scoped>
#nav{
  background-color: #222;
  width: 100%;
  height: 70px;
  margin: 0 !important;
  padding: 10px 0!important;
  box-shadow: #222;
}
#nav img{
  height: 100%;
  width: 20%;
  float: left;
  margin: 0px;
}
#nav a {
  font-weight: bold;
  color: white;
  text-decoration: none;
  margin-right:20px;
  font-size: 18px;
}
#nav a.router-link-exact-active {
  color: #42b983;
  text-decoration: none;
}
.navleft{
  width: 60%;
  display: inline-block;
  margin: 0 !important;
  padding-top: 25px !important;
}
.navright1{
  padding-top: 25px !important;
  margin-right: 50px;
  float: right;
}
.navright2{
  padding-top: 15px !important;
  margin-right: 50px;
  float: right;
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
.droplist {
text-decoration: none;
color: black;
}
@media only screen and (max-width: 1150px) {
  .navleft{
    width: 40%;
  }
  .navright1{
    margin-right: 20px;
  }
  .navright2{
    margin-right: 20px;
  }
  #nav a {
    margin-right:10px;
    font-size: 10px;
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