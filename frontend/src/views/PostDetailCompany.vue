<template>
<!-- html -->
  <div>
   <div>
      <br>
      <h1>Post Detail</h1><br>
      <el-card class="top">
        <b class="title">{{this.title || 'title'}}</b><br><br><br>
        <b>{{this.location || 'location'}}  |  {{this.educationalBackground}}</b><br><br>
        <b class="salary">${{this.maxSalary}} / hour</b><br><br>
      </el-card>
      
      <div class="bottom" >
        <h2>Description</h2>
        <p>{{this.description}}</p>
        <el-card class="card">
          <p><b style="color:#42b983;">Company Information</b></p><br>
          <p><b>Company Name: </b>{{this.companyName}}</p>
          <p><b>Owner: </b>{{this.owner}}</p>
          <p><b>Start Up Date: </b>{{this.startUpDate}}</p>
        </el-card>
        <div style="display:inline-block;margin:10px;">
          <h3>Industry </h3>{{this.industry}} 
        </div>
        <div style="display:inline-block;">
          <h3>Employment Type </h3>{{this.employmentType}}
        </div>
      </div>
      <br>
    </div>
    <div>
          <hr>
          <h1 style="text-align:center;">Suggest JobSeekers</h1>
            <el-card class="cardseeker" v-for="item in recommendSeekers" :key="item.seekerId">
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
        </div>
        <br>
  </div>
</template>

<script>
export default {
    data() {
      return {
        recommendSeekers:null,
        postId:null,
        position:null,
        location:null,
        educationalBackground:null,
        maxSalary:null,
        description:null,
        companyName:null,
        owner:null,
        startUpDate:null,
        industry:null,
        employmentType:null,
      }
    },
    created() {
      // get the post id
      this.postId = this.$route.params.postId
      // get all recommend jobseeker for company
      this.getRecommendJobSeeker(this.postId);
      // display the post detail
      this.seeDetail(this.postId)
      
    },
    methods: {
      // handle card click to display the jobseeker's info
      seekerCardClick(seekerId){
        this.$router.push({name: 'Resume', params: { seekerId: seekerId }})
      },
      //post detail
      seeDetail(postId){
        this.$axios.get('/api/post/select/'+postId).then(res => {
          this.title = res.data.data.title
          this.position = res.data.data.position
          this.location = res.data.data.location
          this.educationalBackground = res.data.data.educationalBackground
          this.maxSalary = res.data.data.maxSalary
          this.description = res.data.data.description
          this.companyName = res.data.data.companyName
          this.owner = res.data.data.owner
          this.startUpDate = res.data.data.startUpDate
          this.industry = res.data.data.industry
          this.employmentType = res.data.data.employmentType
        });
      },
      //contact to backend give the post id to get the recommend seeker
      getRecommendJobSeeker(postId){
        this.$axios.get('/api/post/recommend/all/'+postId).then(res => {
          this.recommendSeekers = res.data.data
        });
      },
    }
  }
</script>
<style scoped>
.div{
  width: 70%;
  margin-left:10%;  
  padding: 5%;
  
  padding-bottom: 3%;
}
.top{
  background-color:#42b983;
  text-align:left;
  padding:2%;
  width: 70%;
  margin-left: 13%;
  border-top-left-radius: 15px;
  border-top-right-radius: 15px;
}
.bottom{
  text-align:left;
  height:400px;
  background-color:rgb(255, 255, 255);
  padding:2%;
  width: 70%;
  margin-left: 13%;
  border-bottom-left-radius: 15px;
  border-bottom-right-radius: 15px;
  margin-bottom: 2%;
}
.card{
  width:30%;
  float:right;
  height:250px;
}
.card:hover{
    box-shadow: 5px 10px 5px #888888;
}
.title{
  font-size:40px;
  color:white;
}
.salary{
  font-size:25px;
  color:rgb(228, 39, 39);
}
p{
  width:65%;
  display:inline-block;
  word-break: break-all;
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
}
/*css only for small size screen like mobile phone*/
@media only screen and (max-width: 900px) {
.top{
  width: 90%;
  margin-left: 3%;
}
.bottom{
  width: 90%;
  margin-left: 3%;
  height:500px;
}
.title{
  font-size:30px;
}
.salary{
  font-size:20px;
}
p{
  width:100%;
  font-size: 15px;
}
.card{
  width:55%;
  font-size: 15px;
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
}
</style>