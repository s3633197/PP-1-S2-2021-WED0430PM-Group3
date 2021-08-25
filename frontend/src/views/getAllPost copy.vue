<template>
  <div>
    <el-row type="flex" class="form" justify="center">
      <el-col :span="8">
        <div>
          <el-form :model="postpostInformationForm" ref="postInformationForm" label-width="200px">
            <el-form-item>
                <h1>All post</h1>
            </el-form-item>

            <el-form-item label="Title">
                <span v-text="postInformationForm.title"></span>
            </el-form-item>

            <el-form-item label="Min Salary">
                <span v-text="postInformationForm.minSalary"></span>
            </el-form-item>

            <el-form-item label="Max Salary">
                <span v-text="postInformationForm.maxSalary"></span>
            </el-form-item>

            <el-form-item label="Educational Background">
                <span v-text="postInformationForm.educationalBackground"></span>
            </el-form-item>

            <el-form-item label="Location">
                <span v-text="postInformationForm.location"></span>
            </el-form-item>

            <el-form-item label="Industry">
                <span v-text="postInformationForm.industry"></span>
            </el-form-item>

            <el-form-item label="Job Type">
                <span v-text="postInformationForm.jobType"></span>
            </el-form-item>

            <el-form-item label="Start Up Year">
                <span v-text="postInformationForm.startUpYear"></span>
            </el-form-item>

            <el-form-item label="Company Name">
                <span v-text="postInformationForm.companyName"></span>
            </el-form-item>

          </el-form>
        </div>
      </el-col>
    </el-row>
  </div>
</template>


<script>
// @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue'
export default {
  data() {
        return {
          postId: null,
          title:'',
          minSalary: null,
          maxSalary: null,
          educationalBackground: '',
          location: '',
          industry: '',
          jobType: '',
          startUpYear: '',
          companyName: '',
          companyId: null,
        };
    },
      created() {
        this.getAllPost();
      },
    methods: {
      getAllPost(){
        this.$axios.get('/api/post/all').then(res => {
          const array = res.data.data
          this.postId = res.data.data.postId
          this.title = res.data.data.title
          this.minSalary = res.data.data.minSalary
          this.maxSalary = res.data.data.maxSalary
          this.educationalBackground = res.data.data.educationalBackground
          this.location = res.data.data.location
          this.industry = res.data.data.industry
          this.jobType = res.data.data.jobType
          this.startUpYear = res.data.dat.startUpYear
          this.companyName = res.data.data.companyName
          this.companyId = res.data.data.companyId
        });
      }
    }
}




</script>

<style scoped>

.step{
  height: 200px;
  align-items: center;
  margin: 0ch;
}
.form{
  padding: 15px;
}
.step2{
  width: 500px;
}
.submit{
  background-color: #54c685;
  
}
/* .submit:hover{
  background-color: #3ea56a;
}
.submit:visited{
  background-color: #54c685;
}
.submit:active{
  background-color: #54c685;
} */
.submit:focus{
  background-color: #54c685;
}
button{
  margin-left: 20%;
  /* background-color: #54c685; */
}
</style>