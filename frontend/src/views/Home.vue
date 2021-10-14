<template>
<!-- html -->
  <div>
    <header id="header">
        <img alt="logo" src="../assets/logo.png">
        <div class="navleft">
          <router-link to="/Index" >Home</router-link>
          <router-link to="/get-All-Post">Posts</router-link>
        </div>
        <div class="navright1" v-if="logout">
          <router-link to="/SignUp">Sign Up</router-link> 
          <router-link to="/SignIn">Sign In</router-link>
        </div>
        <el-button v-if="personal" class="submit" @click="UploadCV()"><b class="submitText">Upload CV</b></el-button>
        <el-button v-if="company" class="submit" @click="Companyinfo()"><b class="submitText">Company info</b></el-button>
        <div class="navright2" v-if="login">
          <el-dropdown >
            <el-avatar :shape='avatar' :src="require('../assets/a.png')" class="avatar"></el-avatar>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item v-if="personal"></el-dropdown-item>
              <el-dropdown-item v-if="company"><router-link class="droplist" to="/create-Post">Create Post</router-link></el-dropdown-item>
              <el-dropdown-item v-if="company"></el-dropdown-item>
              <hr>
              <el-dropdown-item  id="logoutList"><el-button id="logout" type="primary" @click="logoutButton()" v-if="login">Log out</el-button></el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
        
    </header>
    <router-view class="back"/>
    <footer style="text-align:center;background-color:#42b983;padding:20px;margin-top:0px;">
      <span>© 2021 project one, RMIT</span>
    </footer>
  </div>
</template>

<style  scoped>
#header{
  background-color: #222;
  margin: 0 !important;
  height: 70px;
  width: 100%;
  align-content: center;
}
#header img{
  height: 100%;
  float: left;
  margin: 0px!important;
}
#header a {
  font-weight: bold;
  color: white;
  text-decoration: none;
  margin-right:20px;
  font-size: 18px;
}
#header a.router-link-exact-active {
  color: #42b983;
  text-decoration: none;
}
.navleft{
  width: 70%;
  display: inline-block;
  margin-left:20px !important;
  padding-top: 25px;
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
.status{
  color:whitesmoke;
  font-size:18px;
  margin-right:30px;
  float: right;
}
.submit{
  background-color: #54c685 !important;
  border: 0ch;
}
.submitText{
  margin-right:0px !important;
  font-size: 15px !important;
  font-weight:normal !important;
  color:rgb(255, 255, 255);
}
.submit:hover{
  background-color: #3ea56a !important;
}
.submit:focus{
  background-color: #54c685 !important;
}

.submit a.router-link-exact-active {
  color: white !important;
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
  background-color: #ecf5ff;
}
.droplist {
text-decoration: none;
color: black;
}

/*css only for small size screen like mobile phone*/
@media only screen and (max-width: 1150px) {
  #header{
    height: 50px;
  }
  #header a {
    margin-right:10px;
    font-size: 10px;
  }
  .navleft{
    padding-top: 15px;
    margin-left:0px !important;
    width: 30%;
  }
  .navright1{
    padding-top: 15px !important;
    margin-right: 20px !important;
  }
  .navright2{
    padding-top: 10px!important;
    margin-right: 20px !important;
  }
  .status{
    font-size: 10px !important;  
    margin-right:15px;
    margin-left: 0px;
  }
  .submit{
    padding: 2px 7px 6px 7px!important;
  }
  .submitText{
    font-size: 8px!important;
  }
  .avatar{
    width: 30px;
    height: 30px;
    background: #3ea56a;
  }

}
</style>

<script>
export default {
  created() {
    //check account status
      this.loginOrnot();
    //checkt account role to display different ui
      this.personalOrCompany();
    //different role have different avatar
      this.avatarHandler()
    },
  data() {
    return {
      login:false,
      logout:true,
      personal:false,
      company:false,
      avatar:null,
    }
  },
  methods: {
    avatarHandler() {
        if (this.company === true) {
          this.avatar = "square"
        }else {
          this.avatar = "circle"
        }
      },
    //if CV exits display the CV otherwise link to create CV page
    UploadCV(){
      this.$axios.get('/api/seeker/resume').then(res => {
        console.log("statusCode"+res.data.statusCode)
          if(res.data.statusCode!=200){
            this.$router.push("/Profile")
          }else{
            this.$router.push("/resume")
          }
        });
    },
    //if company info exits display the company info otherwise link to create company page
    Companyinfo(){
        this.$axios.get('/api/company/info').then(res => {
          if(res.data.statusCode!==200){
            this.$router.push("/create-company")
          }else{
            this.$router.push("/company-info")
          }
        });
    },
    //logout
    logoutButton() {
      this.$axios.post('/logout').then(res => {
        localStorage.removeItem('token')
        localStorage.removeItem('roleId')
        this.$router.push("/index")
        this.$router.go(0)
      });
       
    },
    //check account status
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
    //check account role
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