<template>
  <div>
    <el-row type="flex" class="form" justify="center">
      <el-col :span="8">
        <div>
          <el-form :model="informationForm" ref="informationForm" label-width="200px">
            <el-form-item>
                <h1>Your profile</h1>
            </el-form-item>

            <el-form-item label="First Name">
                <span v-text="informationForm.firstName"></span>
            </el-form-item>
            
            <el-form-item label="Last Name">
                <span v-text="informationForm.lastName"></span>
            </el-form-item>

            <el-form-item label="Gender">
                <span v-text="informationForm.gender"></span>
            </el-form-item>

            <el-form-item label="Phone">
                <span v-text="informationForm.phone"></span>
            </el-form-item>

            <el-form-item label="Date of Birth">
                <span v-text="informationForm.dateOfBirth"></span>
            </el-form-item>

            <el-form-item label="School Name">
                <span v-text="informationForm.schoolName"></span>
            </el-form-item>

            <el-form-item label="Major">
                <span v-text="informationForm.major"></span>
            </el-form-item>

            <el-form-item label="Educational Background">
                <span v-text="informationForm.educationalBackground"></span>
            </el-form-item>

            <el-form-item label="skll">
                <span v-text="informationForm.skill"></span>
            </el-form-item>

            <el-form-item label="statement">
                <span v-text="informationForm.statement"></span>
            </el-form-item>

            <el-form-item label="location">
                <span v-text="informationForm.location"></span>
            </el-form-item>

            <el-form-item label="Wanted Industry">
                <span v-text="informationForm.wantedIndustry"></span>
            </el-form-item>

            <el-form-item label="Job Type">
                <span v-text="informationForm.jobType"></span>
            </el-form-item>

            <el-form-item label="Expected Salary">
                <span v-text="informationForm.expectedSalary"></span>
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
          seekerId: null,
          firstName: '',
          lastName: '',
          gender: '',
          phone: null,
          dateOfBirth: '',
          schoolName: '',
          major: '',
          educationalBackground: '',
          skill: '',
          location: '',
          statement: '',
          wantedIndustry: '',
          jobType: '',
          expectedSalary: null,
          accountId: null,
        };
    },
      created() {
        this.getResume();
      },
    methods: {
      getResume(){
        // this.$axios.get('/api/seeker/resume').then(res => {
        //   // this.signupForm.token = res.data.data.token
        //   this.firstName = res.data.dat.firstName
        //   this.lastName = res.data.dat.lastName
        //   this.gender = res.data.dat.gender
        //   this.phone = res.data.dat.phone
        //   this.dateOfBirth = res.data.dat.dateOfBirth
        //   this.schoolName = res.data.dat.schoolName
        //   this.major = res.data.dat.major
        //   this.educationalBackground = res.data.dat.educationalBackground
        //   this.location = res.data.dat.location
        //   this.statement = res.data.dat.statement
        //   this.wantedIndustry = res.data.dat.wantedIndustry
        //   this.jobType = res.data.dat.jobType
        //   this.expectedSalary = res.data.dat.expectedSalary
        // });
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