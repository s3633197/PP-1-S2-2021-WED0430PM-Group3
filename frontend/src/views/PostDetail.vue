<template>
<!-- html -->
    <div>
      <br>
      <h1>Post Detail</h1><br>
      <el-card class="top">
        <b class="title">{{this.title}}</b><br><br><br>
        <b>{{this.location || 'empty'}}  |  {{this.educationalBackground}}</b><br><br>
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
        <div style="display:inline-block;">
          <h3>industry </h3>{{this.industry}} 
        </div>
        <div style="display:inline-block;">
          <h3>employmentType </h3>{{this.employmentType}}
        </div>
      </div>
      <br>
    </div>
</template>

<script>
export default {
    data() {
      return {
        title:null,
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
        industry:null,
        employmentType:null,
      }
    },
    created() {
      // get the id to display the detail of this id's post
      this.postId = this.$route.params.postId
      this.seeDetail(this.postId)
    },
    methods: {
      // contact to backend give the post id to see the detail
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

}
</style>