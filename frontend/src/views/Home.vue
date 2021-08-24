<template>
  <div id="app">
    <div id="logoo">
      <img alt="logo" src="../assets/Logo.svg">
    </div>
    <div id="nav">
      <div id="nav1">
        <router-link to="/Index">Home</router-link>
      </div>
      <div id="nav2" >
        <router-link to="/SignUp" v-if="logout">Sign Up | </router-link> 
        <router-link to="/SignIn" v-if="logout">Sign In</router-link> 
        <el-button id="logout" type="primary" @click="logoutButton()" v-if="login">Log out</el-button>
      </div>
      
    </div>
    
    <router-view/>
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  height: 100%;
}
#logoo {
  width: 240px;
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
  padding: 30px;

  width: 80%;
  display: inline-block;
  background-color: rgb(240, 240, 240);
}
#nav1 {

  display: inline;
}
#nav2 {
  float: right;
  display: inline;
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
.img {
  height: 80%;
  width: 100%;
  margin: 0;
}
#logout{
  background-color: rgb(240, 240, 240);
  color: black;
  border-color: rgb(240, 240, 240);
  margin: 0;
  padding: 0;
  font-weight:bold;
  font-size: 16px;
}
</style>

<script>
// @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue'

export default {
   created() {
      this.loginOrnot();
      console.log("home")
    },
  data() {
    return {
      login:false,
      logout:true,
    }
  },
  methods: {
    logoutButton() {
      this.$axios.post('/logout').then(res => {
        localStorage.removeItem('token')
        this.$router.push("/index")
      });
       
    },
    loginOrnot(){
      
      if(localStorage.getItem('token')==null){
        console.log("null")
        this.logout = true
        this.login = false
      }
      if(localStorage.getItem('token')!=null){
        console.log("value")
        this.login = true
        this.logout = false
      }
     
    },
    // personalOrCompany(){
      
    //   if(localStorage.getItem('roleId')==1){
    //     console.log("null")
    //     this.logout = true
    //     this.login = false
    //   }
    //   if(localStorage.getItem('token')!=null){
    //     console.log("value")
    //     this.login = true
    //     this.logout = false
    //   }
     
    // }
  },
}
</script>