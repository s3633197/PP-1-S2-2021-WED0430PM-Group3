<template>
  <div>
    <br>
        <div class="form">
          <h1>Create your profile</h1>
          <el-form :model="informationForm" :rules="rules" ref="informationForm" label-position="left">
            <el-form-item label="First Name" prop="firstName">
                <el-input v-model="informationForm.firstName" style="width:100%;"></el-input>
            </el-form-item>
            <el-form-item label="Last Name" prop="lastName" >
                <el-input v-model="informationForm.lastName" style="width:100%;"></el-input>
            </el-form-item>
            <el-form-item  label="Gender" prop="gender">
              <el-select v-model="informationForm.gender" style="width:100%;">
                <el-option
                  v-for="item in genderOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="Phone" prop="phone" type="number">
                <el-input v-model="informationForm.phone" onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')" style="width:100%;"></el-input>
            </el-form-item>
            <el-form-item label="Date of Birth" prop="dateOfBirth" >
                <el-date-picker
                    style="width: 100%;"
                    v-model="informationForm.dateOfBirth"
                    type="date"
                    placeholder="select a date">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="School Name" prop="schoolName" >
                <el-input v-model="informationForm.schoolName" style="width:100%;"></el-input>
            </el-form-item>
            <el-form-item label="Major" prop="major" >
                <el-input v-model="informationForm.major" style="width:100%;"></el-input>
            </el-form-item>
            <el-form-item label="Educational Background" prop="educationalBackground" >
                <el-select v-model="informationForm.educationalBackground" style="width: 100%;" placeholder="Please select">
                    <el-option
                    style="width:100%;"
                        v-for="item in bsckgroundOptions"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="Skill" prop="skill" >
                <el-input v-model="informationForm.skill" style="width:100%;"></el-input>
            </el-form-item>
            <el-form-item label="Location" prop="location" >
                <el-input v-model="informationForm.location" style="width:100%;"></el-input>
            </el-form-item>
            <el-form-item label="Statement" prop="statement" >
                <el-input v-model="informationForm.statement" style="width:100%;"></el-input>
            </el-form-item>
            <el-form-item label="Wanted Industry" prop="wantedIndustry" >
                <el-input v-model="informationForm.wantedIndustry" style="width:100%;"></el-input>
            </el-form-item>
            <el-form-item label="JobType" prop="jobType" >
                <el-select v-model="informationForm.jobType" style="width: 100%;" placeholder="Please select">
                    <el-option style="width:100%;"
                        v-for="item in jobTypeOptions"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="Expected Salary" prop="expectedSalary" >
                <el-input v-model="informationForm.expectedSalary" onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')" style="width:100%;"></el-input>
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
// @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue'
import qs from 'qs'
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
                // phone:[
                //     { type:'number', message: 'Only accept number!' },
                // ],
                // expectedSalary:[
                //     { type: 'number', message: 'Only accept number!' },
                // ],
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
    //  created() {
    //    this.getemail();
    //  },
    methods: {
      submitForm(formName) {
        
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$axios.post('/api/seeker/create',this.informationForm).then(res => {
              
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
    }
}
</script>

<style scoped>

.form{
  width: 30%;
  margin-left:30.5%;
  margin-top: 5%;
  margin-bottom:5%;
  box-shadow: 5px 10px 5px #888888;
  background-color: rgb(240, 245, 250);
  border-radius: 15px;
  padding: 5%;
  padding-top: 3%;
  padding-bottom: 3%;
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
@media only screen and (max-width: 900px) {
  .form{
    width: 80%;
    margin-bottom:5%;
    margin-top: 10%;
    margin-left:6%;
  }
}
</style>