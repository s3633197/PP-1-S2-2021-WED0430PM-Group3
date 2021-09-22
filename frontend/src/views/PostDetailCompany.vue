<template>
  <div>
    <br>
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
    </div>
    <div>
          <hr>
          <h1 style="text-align:center;">Suggest JobSeekers</h1>
            <el-card class="card" :span="16" v-for="item in recommendSeekers" :key="item.seekerId">
              <table>
                <tr>
                  <th>Name: {{ item.firstName }} {{ item.lastName }}</th>
                  <td>Gender: {{ item.gender }}</td>
                  <td>Birthday: {{ item.dateOfBirth }}</td>
                </tr>
                <tr>
                  <td>Expected Salary: {{ item.expectedSalary }} / month</td>
                  <td>Skill: {{ item.skill }}</td>
                  <td>Phone: {{ item.phone }}</td>
                </tr>
                <tr>
                   <td>EducationalBackground: {{ item.educationalBackground }}</td>
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