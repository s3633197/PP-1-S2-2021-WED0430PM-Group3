<template>
<!-- html -->
  <div style="text-align:left;">
    <div class="head">
      <div style="display:inline-block;">
        <el-avatar shape="square" :src="require('../assets/a.png')" class="avatar"></el-avatar>
        <b class="companyName">{{ informationForm.companyName }}</b><br>
        <b>{{ informationForm.owner }}</b><br>
        <b>{{ informationForm.startUpDate }}</b>
      </div>
      <div class="postsNum">
        <h1>{{allposts.length}}</h1><b class="num">Posts</b>
      </div>
    </div>
    <div class="nav">
      <router-link style="margin-left:50px;" to="/get-All-Company-Post" >My Posts</router-link>
      <router-link :to="{path:'/updateCompanyInfo',query:{informationForm:this.informationForm}}">
        Update
      </router-link>
    </div>
    <router-view class="back" :array="allposts" :informationForm="this.informationForm"/>
  </div>
</template>


<script>
export default {
  data() {
        return {
          //data to store all information from backend
          allposts:[],
          informationForm: {
              companyId: '',
              companyName: '',
              startUpDate: '',
              owner: '',
              industry: '',
              accountId: '',
            },
        };
    },
    //on created call method contact to backend and get all the information
      created() {
        this.getCompanyInfo();
        this.getAllPost();
      },
    methods: {
      //contact to backend and get the info
      getCompanyInfo(){
        this.$axios.get('/api/company/info').then(res => {
          this.informationForm.companyId = res.data.data.companyId
          this.informationForm.companyName = res.data.data.companyName
          this.informationForm.startUpDate = res.data.data.startUpDate
          this.informationForm.owner = res.data.data.owner
          this.informationForm.industry = res.data.data.industry
          this.informationForm.accountId = res.data.data.accountId
        });
      },
      getAllPost(){
        this.$axios.get('/api/post/getAll').then(res => {
          this.allposts = res.data.data
        });
      },
    }
}
</script>

<style scoped>
.avatar{
    width: 80px;
    height: 80px;
    float:left;
    margin-right:50px;
}
.head{
  background-color:#42b983;
  height:90px;
  padding:25px;
  color:white;
  font-size: 15px;
}
.companyName{
  font-size:30px;
}
.postsNum{
  float: right;
  height: 100%;
  text-align: center;
  margin-right: 50px;
}
.num{
  font-size:20px;
}
h1{
  margin:0;
  font-size:55px;
}
.nav{
  padding:15px;
  border-bottom: 1px solid lightgrey
}
a {
  font-weight: bold;
  color: #222;
  text-decoration: none;
  margin-right:20px;
  font-size: 18px;
}
a.router-link-exact-active {
  color: #42b983;
  text-decoration: none;
}

.cardseeker{
  width:65%;
  margin-left:17%;
  margin-bottom: 2%;
  border-radius: 15px !important;
}

.submit{
  background-color: #54c685;
}
.submit:focus{
  background-color: #54c685;
}
.submit:hover{
  background-color: #3ea56a;
}

/*css only for small size screen like mobile phone*/
@media only screen and (max-width: 500px) {
.head{
  height:35px;
  padding:10px;
  color:white;
  font-size: 8px;
}
.avatar{
    width: 30px;
    height: 30px;
    margin-right:20px;
}
.companyName{
  font-size:15px;
}
.postsNum{
  margin-right: 20px;
}
.num{
  font-size:10px;
}
h1{
  margin:0;
  font-size:25px;
}
.nav{
  padding:5px;
}
a {
  margin-right:0px!important;
  font-size: 8px;
  margin-left: 15px !important;
}
}
</style>