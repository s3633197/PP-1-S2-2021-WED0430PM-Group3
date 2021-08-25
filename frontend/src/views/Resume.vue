<template>
  <div>
    <el-row type="flex" class="form" justify="center">
      <el-col :span="8">
        <div>
          <el-form :model="informationForm" ref="informationForm" label-width="200px">
            <el-form-item>
                <h1>Your profile</h1>
            </el-form-item>

            <el-form-item label="First Name: ">
                <span v-text="informationForm.firstName"></span>
            </el-form-item>
            
            <el-form-item label="Last Name: ">
                <span v-text="informationForm.lastName"></span>
            </el-form-item>

            <el-form-item label="Gender: ">
                <span v-text="informationForm.gender"></span>
            </el-form-item>

            <el-form-item label="Phone: ">
                <span v-text="informationForm.phone"></span>
            </el-form-item>

            <el-form-item label="Date of Birth: ">
                <span v-text="informationForm.dateOfBirth"></span>
            </el-form-item>

            <el-form-item label="School Name: ">
                <span v-text="informationForm.schoolName"></span>
            </el-form-item>

            <el-form-item label="Major: ">
                <span v-text="informationForm.major"></span>
            </el-form-item>

            <el-form-item label="Educational Background: ">
                <span v-text="informationForm.educationalBackground"></span>
            </el-form-item>

            <el-form-item label="skill: ">
                <span v-text="informationForm.skill"></span>
            </el-form-item>

            <el-form-item label="statement: ">
                <span v-text="informationForm.statement"></span>
            </el-form-item>

            <el-form-item label="location: ">
                <span v-text="informationForm.location"></span>
            </el-form-item>

            <el-form-item label="Wanted Industry: ">
                <span v-text="informationForm.wantedIndustry"></span>
            </el-form-item>

            <el-form-item label="Job Type: ">
                <span v-text="informationForm.jobType"></span>
            </el-form-item>

            <el-form-item label="Expected Salary: ">
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
          informationForm:{
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
          },
          
        };
    },
      created() {
        this.getResume();
      },
    methods: {
      getResume(){
        this.$axios.get('/api/seeker/resume').then(res => {
          // this.signupForm.token = res.data.data.token
          this.informationForm.firstName = res.data.data.firstName
          this.informationForm.lastName = res.data.data.lastName
          this.informationForm.gender = res.data.data.gender
          this.informationForm.phone = res.data.data.phone
          this.informationForm.dateOfBirth = res.data.data.dateOfBirth
          this.informationForm.schoolName = res.data.data.schoolName
          this.informationForm.major = res.data.data.major
          this.informationForm.educationalBackground = res.data.data.educationalBackground
          this.informationForm.skill = res.data.data.skill
          this.informationForm.location = res.data.data.location
          this.informationForm.statement = res.data.data.statement
          this.informationForm.wantedIndustry = res.data.data.wantedIndustry
          this.informationForm.jobType = res.data.data.jobType
          this.informationForm.expectedSalary = res.data.data.expectedSalary
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
span{
  margin-left: 20px;
  padding-left: 50px;
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