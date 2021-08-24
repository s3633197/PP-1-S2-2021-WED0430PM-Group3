<template>
  <div>
    <el-row type="flex" class="form" justify="center">
      <el-col :span="8">
        <div>
          <el-form :model="informationForm" :rules="rules" ref="informationForm" label-width="200px">
            <el-form-item>
                <h1>Create your profile!</h1>
            </el-form-item>
            <el-form-item label="First Name" prop="firstName">
                <el-input v-model="informationForm.firstName"></el-input>
            </el-form-item>
            <el-form-item label="Last Name" prop="lastName" >
                <el-input v-model="informationForm.lastName"></el-input>
            </el-form-item>
            <el-form-item  label="Gender" prop="gender">
              <el-select v-model="informationForm.gender" style="width: 272px;">
                <el-option
                  v-for="item in genderOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="Phone" prop="phone" >
                <el-input v-model="informationForm.phone"></el-input>
            </el-form-item>
            <el-form-item label="Date of Birth" prop="dateOfBirth" >
                <el-date-picker
                    style="width: 272px;"
                    v-model="informationForm.dateOfBirth"
                    type="date"
                    placeholder="select a date">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="Major" prop="major" >
                <el-input v-model="informationForm.major"></el-input>
            </el-form-item>
            <el-form-item label="Educational Background" prop="educationalBackground" >
                <el-select v-model="informationForm.educationalBackground" style="width: 272px;" placeholder="Please select">
                    <el-option
                        v-for="item in bsckgroundOptions"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="Skill" prop="skill" >
                <el-input v-model="informationForm.skill"></el-input>
            </el-form-item>
            <el-form-item label="Location" prop="location" >
                <el-input v-model="informationForm.location"></el-input>
            </el-form-item>
            <el-form-item label="Statement" prop="statement" >
                <el-input v-model="informationForm.statement"></el-input>
            </el-form-item>
            <el-form-item label="Wanted Industry" prop="wantedIndustry" >
                <el-input v-model="informationForm.wantedIndustry"></el-input>
            </el-form-item>
            <el-form-item label="JobType" prop="jobType" >
                <el-select v-model="informationForm.jobType" style="width: 272px;" placeholder="Please select">
                    <el-option
                        v-for="item in jobTypeOptions"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="Expected Salary" prop="expectedSalary" >
                <el-input v-model="informationForm.expectedSalary"></el-input>
            </el-form-item>

            
            <el-form-item>
              <el-button class="submit" type="primary" @click="submitForm('informationForm')">submit</el-button>
              <el-button @click="resetForm('informationForm')">replace</el-button>
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
import qs from 'qs'
export default {
  data() {
        return {
            informationForm: {
              firstName: '',
              lastName: '',
              gender: 'empty',
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
            },

            rules: {
                firstName: [
                    { required: true, message: 'Please enter your first name!', trigger: 'blur' },
                ],
                lastName: [
                    { required: true, message: 'Please enter your last name!', trigger: 'blur' },
                ],
                educationalBackground: [
                    { required: true, message: 'Please select your educational background!', trigger: 'blur' },
                ],
                jobType: [
                    { required: true, message: 'Please select your job type!', trigger: 'blur' },
                ],
            },

            genderOptions: [{
            value: 'male',
            label: 'male'
            }, {
            value: 'female',
            label: 'female'
            }, {
            value: 'empty',
            label: 'empty'
            }], 

            jobTypeOptions: [{
            value: 'Full Time',
            label: 'Full Time'
            }, {
            value: 'Part Time',
            label: 'Part Time'
            }, {
            value: 'Contract',
            label: 'Contract'
            }],

            bsckgroundOptions: [{
            value: 'Elementary School',
            label: 'Elementary School'
            }, {
            value: 'Secondary Education',
            label: 'Secondary Education'
            }, {
            value: 'Post Secondary Education',
            label: 'Post-Secondary Education'
            }, {
            value: 'High school',
            label: 'High school'
            }, {
            value: 'Diploma',
            label: 'Diploma'
            }, {
            value: 'Bachelor',
            label: 'Bachelor'
            }, {
            value: 'Master',
            label: 'Master'
            }, {
            value: 'Phd',
            label: 'Phd'
            }],

            pickerOptions: {
                disabledDate(time) {
                    return time.getTime() > Date.now();
                }
            },
        };
      
    },
    //  created() {
    //    this.getemail();
    //  },
    methods: {
      submitForm(formName) {
        
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$axios.post('/api/seeker/create?',qs.stringify(this.informationForm),{headers:{'Content-Type' : "application/x-www-form-urlencoded; application/json"}}).then(res => {
              
              this.$router.push("/resume")
            });
          }else {
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      next() {
        if (this.active++ > 2) this.active = 0;
      },
      // getemail(){
      //   this.$axios.get('/api/user/register').then(res => {
      //     // this.signupForm.token = res.data.data.token
      //     this.email = res.data.dat.email
      //     this.passwordexist = res.data.dat.password
      //     console.log(this.email)
      //     console.log(this.passwordexist)
      //   });
      // }
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