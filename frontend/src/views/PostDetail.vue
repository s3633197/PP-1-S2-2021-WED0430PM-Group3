<template>
  <div >
    <br>
    <div class="form">
      <h1>Post Detail</h1>
      <el-card style="background-color:#42b983;text-align:left;padding:2%;">
        <b style="font-size:40px;color:white;">Title: {{this.description}}</b><br><br><br>
        <p>Location: {{this.location || 'empty'}}  |  Educational Background: {{this.educationalBackground}}</p><br>
        <b style="font-size:25px;color:rgba(201, 70, 70, 0.856);">${{this.maxSalary}} / month</b><br><br>
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
</template>

<script>
export default {
    data() {
      return {
        recommendSeekers:null,
        company:false,
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
@media only screen and (max-width: 900px) {
  .form{
    width: 80%;
    margin-left:6%;
  }
}
</style>