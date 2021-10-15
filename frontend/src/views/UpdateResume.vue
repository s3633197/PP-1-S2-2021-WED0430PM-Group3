<template>
<!-- html -->
  <div>
    <br>
        <div class="form">
          <h1>Update your profile</h1>
          <el-form :inline="true" :model="informationForm" :rules="rules" ref="informationForm" label-width="165px" >
            <el-form-item label="First Name" prop="firstName">
                <el-input v-model="informationForm.firstName" class="shortInput"></el-input>
            </el-form-item>
            <el-form-item label="Last Name" prop="lastName" >
                <el-input v-model="informationForm.lastName" class="shortInput"></el-input>
            </el-form-item>
            <el-form-item  label="Gender" prop="gender">
              <el-select v-model="informationForm.gender" class="shortInput">
                <el-option
                  v-for="item in genderOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="Phone" prop="phone" type="number">
                <el-input v-model="informationForm.phone" onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')" class="shortInput"></el-input>
            </el-form-item>
            <el-form-item label="Educational Background" prop="educationalBackground" >
                <el-select v-model="informationForm.educationalBackground" style="width: 100%;" class="shortInput" placeholder="Please select">
                    <el-option
                        v-for="item in bsckgroundOptions"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="Date of Birth" prop="dateOfBirth" >
                <el-date-picker
                    class="shortInput"
                    v-model="informationForm.dateOfBirth"
                    type="date"
                    placeholder="select a date">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="School Name" prop="schoolName" >
                <el-input v-model="informationForm.schoolName" class="longInput"></el-input>
            </el-form-item>
            <el-form-item label="Major" prop="major" >
                <el-input v-model="informationForm.major" class="longInput"></el-input>
            </el-form-item>
            <el-form-item label="Skill" prop="skill" >
                <el-input v-model="informationForm.skill" class="longInput"></el-input>
            </el-form-item>
            <el-form-item label="Location" prop="location" >
                <el-input v-model="informationForm.location" class="longInput"></el-input>
            </el-form-item>
            
            <el-form-item label="Expected Salary" prop="expectedSalary" >
                <el-input v-model="informationForm.expectedSalary" onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')" class="shortInput"></el-input>
            </el-form-item>
            
            <el-form-item label="JobType" prop="jobType" >
                <el-select v-model="informationForm.jobType" placeholder="Please select">
                    <el-option class="shortInput"
                        v-for="item in jobTypeOptions"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="Wanted Industry" prop="wantedIndustry" >
                <el-input v-model="informationForm.wantedIndustry" class="longInput"></el-input>
            </el-form-item>
            <el-form-item label="Statement" prop="statement" >
                <textarea v-model="informationForm.statement" class="longInput" rows="10" cols="50"></textarea>
            </el-form-item>
            <el-form-item>
              <el-button class="submit" type="primary" @click="submitForm('informationForm')">submit</el-button>
              <el-button @click="resetForm('informationForm')">replace</el-button>
            </el-form-item>
          </el-form>
        </div>
        <br>
  </div>
</template>


<script>
export default {
  data() {
        return {
            informationForm: {
              firstName: '',
              lastName: '',
              gender: 'male',
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
              expectedSalary: 0,
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
     created() {
       //get the old info for easy to update
       this.informationForm = this.$route.query.informationForm
     },
    methods: { 
      // contact to backend to update the info
      submitForm(formName) {
        
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$axios.put('/api/seeker/update',this.informationForm).then(res => {
                this.$router.push("/resume")
                this.$router.go(0)
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
    }
}




</script>

<style scoped>
.form{
  width: 810px;
  margin-left:20.5%;
  margin-top: 5%;
  margin-bottom:5%;
  box-shadow: 5px 10px 5px #888888;
  background-color: rgb(255, 255, 255);
  border-radius: 15px;
  text-align: center;
  padding: 20px;
}
.submit{
  background-color: #54c685;
}
.submit:focus{
  background-color: #54c685;
}
.submit:hover{
  background-color: #3ea56a;
}
.shortInput{
  width: 220px;
}
.longInput{
  width: 620px;
  resize: none;
}
/*css only for small size screen like mobile phone*/
@media only screen and (max-width: 900px) {
  .form{
    width: 80%;
    margin-bottom:5%;
    margin-top: 10%;
    margin-left:6%;
  }
  .longInput{
    width: 219px;
    resize: none;
  }
}
</style>