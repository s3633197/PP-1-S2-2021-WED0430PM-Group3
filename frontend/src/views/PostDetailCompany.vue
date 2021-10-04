<template>
  <div>
    <!-- <br>
    <h1>Post Detail</h1><br>
    <el-card class="card" style="background-color:rgb(102, 216, 172);text-align:left;">
      <b style="font-size:40px;color:white;">Title: {{this.description}}</b><br><br><br>
      <p>Location: {{this.location || 'empty'}}  |  Educational Background: {{this.educationalBackground}}</p><br>
      <b style="font-size:25px;color:rgba(201, 70, 70, 0.856);">${{this.maxSalary}} / month</b><br><br>
    </el-card>
    <div class="card" style="text-align:left;height:400px;" >
      <h2>Description</h2>
      <p style="width:65%;display:inline-block;float:left;word-break: break-all;">{{this.description}}aasdasdasdasdasdasdasdasdasdasdsddddddddddddddddddddddasdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd</p>
      <el-card style="width:30%;float:right;height:200px;">
       <p style="color:rgb(102, 216, 172);">Company Information</p>
       <p>Company Name: {{this.companyName}}</p>
       <p>Owner: {{this.owner}}</p>
       <p>Start Up Date: {{this.startUpDate}}</p>
      </el-card>
    </div> -->
    <div >
    <div class="form">
      <h1>Post Detail</h1><br>
      <el-card style="background-color:#42b983;text-align:left;padding:2%;">
        <b style="font-size:40px;color:white;">Title: {{this.description}}</b><br><br><br>
        <p>Location: {{this.location || 'empty'}}  |  Educational Background: {{this.educationalBackground}}</p><br>
        <b style="font-size:25px;color:rgba(201, 70, 70, 0.856);">${{this.maxSalary}} / hour</b><br><br>
      </el-card>
      <div style="text-align:left;height:400px;background-color:white;padding:2%;" >
        <h2>Description</h2>
        <p style="width:65%;display:inline-block;float:left;word-break: break-all;">{{this.description}}aasdasdasdasdasdasdasdasdasdasdsddddddddddddddddddddddasdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd</p>
        <el-card style="width:30%;float:right;height:200px;">
        <b style="color:#42b983;">Company Information</b>
        <p><b>Company Name: </b>{{this.companyName}}
        <p><b>Owner: </b>{{this.owner}}</p>
        <p><b>Start Up Date: </b>{{this.startUpDate}}</p>
        </el-card>
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
                  <td style="color:#42b983;"><b>Name: </b>{{ item.firstName }} {{ item.lastName }}</td>
                  <td><b>EducationalBackground:  </b>{{ item.educationalBackground }}</td>
                  <td rowspan="2"><el-avatar :src="require('../assets/a.png')" :size="80"></el-avatar></td>
                </tr>
                <tr>
                  <td style="color:rgba(201, 70, 70, 0.856)"><b>Expected Salary: </b>{{ item.expectedSalary }} / hour</td>
                  <td><b>Skill: </b>{{ item.skill }}</td>
                </tr>
                <tr>
                   <td><b>Phone: </b>{{ item.phone }}</td>
                   <td></td>
                      <td>
                        <el-button type="primary"  class="submit" >
                            <router-link :to="{path:'/resume',query:{seekerId:item.seekerId}}" style="color: #fff;text-decoration: none;">
                              Details
                            </router-link>
                          </el-button>
                      </td>
                </tr>
              </table>
            </el-card>
        </div>
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
      }
    },
    created() {
      this.postId = this.$route.query.postId
      this.getRecommendJobSeeker(this.postId);
      this.seeDetail(this.postId)
      
    },
    methods: {
      seeDetail(postId){
        this.$axios.get('/api/post/select/'+postId).then(res => {
          this.position = res.data.data.position
          this.location = res.data.data.location
          this.educationalBackground = res.data.data.educationalBackground
          this.maxSalary = res.data.data.maxSalary
          this.description = res.data.data.description
          this.companyName = res.data.data.companyName
          this.owner = res.data.data.owner
          this.startUpDate = res.data.data.startUpDate
        });
      },
      getRecommendJobSeeker(postId){
        this.$axios.get('/api/post/recommend/all/'+postId).then(res => {
          this.recommendSeekers = res.data.data
          console.log("getRecommendJobSeeker: "+res.data.data)
        });
      },
    }
  }
</script>
<style scoped>
.form{
  width: 70%;
  margin-left:10%;

  padding: 5%;
  padding-top: 3%;
  padding-bottom: 3%;
}
.cardseeker{
  width:65%;
  margin-left:17%;
  margin-bottom: 2%;
  border-radius: 15px !important;
  background-color: rgb(240, 245, 250)!important;
}
.cardseeker td{
  text-align: left;
  width: 44.5%;
  padding-left: 8%;
}
@media only screen and (max-width: 900px) {
  .form{
    width: 80%;
    margin-left:6%;
  }
  .cardseeker{
    width:80%;
    margin-left:10%;
  }
}
</style>