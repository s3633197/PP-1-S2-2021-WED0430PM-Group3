<template>
  <div>
    <el-row type="flex" class="form" justify="center">
      <el-col :span="8">
        <div>
          <el-form :model="postpostInformationForm" ref="postInformationForm" label-width="200px">
            <el-form-item>
                <h1>Your post</h1>
            </el-form-item>

            <el-form-item label="Title">
                <span v-text="postInformationForm.title"></span>
            </el-form-item>

            <el-form-item label="Description">
                <span v-text="postInformationForm.description"></span>
            </el-form-item>

            <el-form-item label="Industry">
                <span v-text="postInformationForm.industry"></span>
            </el-form-item>

            <el-form-item label="position">
                <span v-text="postInformationForm.position"></span>
            </el-form-item>

            <el-form-item label="Address">
                <span v-text="postInformationForm.address"></span>
            </el-form-item>

            <el-form-item label="Employment Type">
                <span v-text="postInformationForm.employmentType"></span>
            </el-form-item>

            <el-form-item label="Educational Background">
                <span v-text="postInformationForm.educationalBackground"></span>
            </el-form-item>

            <el-form-item label="Min Salary">
                <span v-text="postInformationForm.minSalary"></span>
            </el-form-item>

            <el-form-item label="Max Salary">
                <span v-text="postInformationForm.maxSalary"></span>
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
          description:'',
          industry: '',
          position:'',
          address:'',
          employmentType: '',
          educationalBackground:'',
          minSalary: null,
          maxSalary: null,
          companyId: null,
        };
    },
      created() {
        this.getAllPost();
      },
    methods: {
      getAllPost(){
        this.$axios.get('/api/post/getAll').then(res => {
          // this.signupForm.token = res.data.data.token
          this.postId = res.data.dat.postId
          this.title = res.data.dat.title
          this.description = res.data.dat.description
          this.industry = res.data.dat.industry
          this.position = res.data.dat.position
          this.address = res.data.dat.address
          this.employmentType = res.data.dat.employmentType
          this.educationalBackground = res.data.dat.educationalBackground
          this.minSalary = res.data.dat.minSalary
          this.maxSalary = res.data.dat.maxSalary
          this.companyId = res.data.dat.companyId
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