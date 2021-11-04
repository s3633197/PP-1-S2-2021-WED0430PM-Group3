<template>
<!-- html -->
    <div>
      <br> <br>
      <h1 style="margin:0;">Easy to find a job, there's no better way.</h1>
        <img src="../assets/homeImg1.jpg">
        <img src="../assets/homeImg2.jpg">
        <div v-if="company" class="div">
          <h1>All JobSeekers</h1> 
            <el-card class="cardseeker" v-for="item in array" :key="item.seekerId" @click.native="seekerCardClick(item.seekerId)">
            
              <table>
                <tr>
                  <td class="importantInfo" colspan="2">{{ item.firstName }} {{ item.lastName }}</td>
                  <td colspan="3">{{ item.educationalBackground }}</td>
                  <td rowspan="3" style="text-align:center;"><el-avatar :src="require('../assets/a.png')" class="avatar"></el-avatar></td>
                </tr>
                <tr>
                  <td colspan="2" style="color:rgba(201, 70, 70, 0.856)">{{ item.expectedSalary }} / hour</td>
                  <td colspan="3" >{{ item.skill }}</td>
                </tr>
                <tr>
                   <td>{{ item.phone }}</td>
                   <td></td><td></td><td></td>
                </tr>
              </table>
            </el-card>
             <br>
        </div>
        <div v-if="personal">
          <h1>Suggest Posts</h1>
                <el-card v-for="item in recommendPosts" :key="item.postId" class="cardpost" @click.native="postCardClick(item.postId)">
                  <table>
                    <tr>
                      <td class="importantInfo" colspan="2">{{ item.title || 'title'}}<b style="color:black;font-size:20px;">[{{ item.location || 'location' }}]</b></td>
                      <td class="importantInfo" colspan="3">{{ item.companyName || 'companyName' }}</td>
                      <td rowspan="3" style="text-align:center;"><el-avatar shape="square" :src="require('../assets/a.png')" class="avatar"></el-avatar></td>
                    </tr>
                    <tr>
                      <td style="color:rgba(201, 70, 70, 0.856)">{{ item.minSalary }}-{{ item.maxSalary }} / hour</td>
                      <td></td><td></td><td></td>
                    </tr>
                    <tr>
                      <td colspan="2">{{ item.jobType || 'jobType'}} | {{ item.educationalBackground || 'educationalBackground'}}</td>
                      <td colspan="3">{{ item.industry || 'industry'}} / {{ item.startUpYear || 'startUpDate'}}</td>
                    </tr>
                  </table>
                </el-card>
                <br>
        </div>
        <div v-if="defaultt" class="div">
          <br>
          <h1>All Posts</h1><br>
          <el-card class="cardpost" v-for="item in array" :key="item.postid" @click.native="postCardClick(item.postId)">
            <table>
              <tr>
                <td class="importantInfo" colspan="2">{{ item.title }} [{{ item.location || 'empty' }}]</td>
                <td class="importantInfo" colspan="3">{{ item.companyName }}</td>
                <td rowspan="3" colspan="1" style="text-align:center;"><el-avatar shape="square" :src="require('../assets/a.png')" class="avatar"></el-avatar></td>
              </tr>
              <tr>
                <td colspan="2"  style="color:rgba(201, 70, 70, 0.856)">{{ item.minSalary }}-{{ item.maxSalary }}/hour</td>
                <td></td><td></td><td></td>
              </tr>
              <tr>
                <td colspan="2" >{{ item.jobType || 'empty'}} | {{ item.educationalBackground || 'empty'}}</td>
                <td colspan="3" >{{ item.industry || 'empty'}} {{ item.startUpYear|| 'empty' }}</td>
              </tr>
            </table>
          </el-card>
          <br>
        </div>
        
    </div>
</template>

<style scoped>
  .div{
    background-color: rgb(219, 217, 217);
    font-size: 15px;
  }
  img{
      width: 30%;
      margin: 3%;
  }
  .importantInfo{
    font-size: 25px;
    color:#42b983;
  }
  .avatar{
    width: 80px;
    height: 80px;
  }
  .cardseeker{
    width:65%;
    margin-left:17%;
    margin-bottom: 2%;
    border-radius: 15px !important;
    background-color: rgb(255, 255, 255)!important;
    text-align: left;
  }
  .cardseeker:hover{
       box-shadow: 5px 10px 5px #888888;
       cursor:pointer
  }
.cardseeker table{
  margin-left:5%;
  width: 90%;
}
.cardseeker td{
  text-align: left;
  width: 78px;
  /* border: 1px solid black; */
}
.cardpost{
  width:60%;
  margin-left:20%;
  margin-bottom: 2%;
  border-radius: 15px !important;
  background-color: rgb(255, 255, 255)!important;
}
.cardpost:hover{
  box-shadow: 5px 10px 5px #888888;
  cursor:pointer
}
.cardpost table{
  margin-left:5%;
  width: 90%;
}
.cardpost td{
  text-align: left;
  width: 78px;
  /* border: 1px solid black; */

}
.avatar{
    width: 80px;
    height: 80px;
}
  .submit{
    background-color: #54c685 !important;
  }
  .submit:hover{
    background-color: #3ea56a !important;
  }
  .submit:focus{
    background-color: #54c685 !important;
  }
  /*css only for small size screen like mobile phone*/
@media only screen and (max-width: 1150px) {
  .div{
    font-size: 10px;
  }
  img{
      width: 75%;
      margin: 3%;
  }
  .importantInfo{
    font-size: 15px !important;
    color:#42b983;
  }
  .avatar{
    width: 30px;
    height: 30px;
    background: #3ea56a;
  }
  .cardseeker{
    width:100%;
    margin-left:0%;
    border-radius: 0 !important;
  }
  .cardseeker table{
    margin-left:0%;
    width: 100%;
  }
  .cardseeker td{
    width: 20%;
    padding: 0%;
  }
  .cardpost{
    width:100%;
    margin-left:0%;
    border-radius: 0 !important;
  }
  .cardpost table{
    margin-left:0%;
    width: 100%;
  }
  .cardpost td{
    width: 70%;
    padding: 0%;
  }
  .submit{
    background-color: #54c685 !important;

  }
}
</style>

<script>
  export default {
    data() {
      return {
        recommendPosts:null,
        array: null,
        personal:false,
        company:false,
        defaultt:true,
        login:false,
        dataNum: 0,
        currentPage: 1,
      }
    },
    
    created() {
      this.personalOrCompany();
      this.loginOrnot();
      if(this.login){
        if(this.personal==true){
          this.getRecommendPost();
        }else{
           this.getAllSeekers();
        }
      }else{
          this.getAllPost();
      }
    },
    methods: {
      //handle card click for company
      seekerCardClick(seekerId){
        this.$router.push({name: 'ResumeCompany', params: { seekerId: seekerId }})
      },
      //handle card click for personal account
      postCardClick(postId){
        this.$router.push({name: 'PostDetail', params: { postId: postId }})
      },
      //get Recommend Post for jobseeker
      getRecommendPost(){
        this.$axios.get('/api/seeker/recommend/all').then(res => {
          this.recommendPosts = res.data.data
          this.dataNum = res.data.data.length
        });
      },
      getAllPost(){
        this.$axios.get('/api/post/all').then(res => {
          this.array = res.data.data
          this.dataNum = res.data.data.length
        });
      },
       //get All Seekers for company
      getAllSeekers(){
        this.$axios.get('/api/seeker/all').then(res => {
          this.array = res.data.data
          this.dataNum = res.data.data.length
        });
      },
      //check the role of current login account
      personalOrCompany(){
        if(localStorage.getItem('roleId')==1){
          this.personal = true
          this.company = false
        }
        if(localStorage.getItem('roleId')==2){
          this.company = true
          this.personal = false
        }
      },
      //check login or not
      loginOrnot(){
        if(localStorage.getItem('token')==null){
          this.login = false
          this.defaultt = true
        }
        if(localStorage.getItem('token')!=null){
          this.login = true
          this.defaultt = false
        }
      },
    }
  }
</script>