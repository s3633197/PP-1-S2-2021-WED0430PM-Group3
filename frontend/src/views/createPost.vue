<template>
  <div>
    <br>
        <div class="form">
          <h1 class="h1">Create posts</h1>
          <el-form :model="postInformation" :rules="rules" ref="postInformation" label-position="left">
            <el-form-item label="Title" prop="title">
                <el-input v-model="postInformation.title"></el-input>
            </el-form-item>

            <el-form-item label="Description" prop="description" >
                <el-input v-model="postInformation.description"></el-input>
            </el-form-item>

            <el-form-item label="Industry" prop="industry" >
                <el-input v-model="postInformation.industry"></el-input>
            </el-form-item>
            
            <el-form-item label="Position" prop="Position" >
                <el-input v-model="postInformation.position"></el-input>
            </el-form-item>
            <el-form-item label="Address" prop="address" >
                <el-input v-model="postInformation.address"></el-input>
            </el-form-item>
           
            <el-form-item label="Employment Type" prop="employmentType" >
                <el-select class="role" v-model="postInformation.employmentType" placeholder="Please select">
                    <el-option
                        v-for="item in employmentTypeOptions"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                    </el-option>
                </el-select>
            </el-form-item>

            <el-form-item label="Educational Background" prop="educationalBackground" >
                <el-select class="role" v-model="postInformation.educationalBackground" placeholder="Please select">
                    <el-option
                        v-for="item in backgroundOptions"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                    </el-option>
                </el-select>
            </el-form-item>

            <el-form-item label="Min Salary" prop="minSalary" >
                <el-input v-model="postInformation.minSalary" onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')"></el-input>
            </el-form-item>

             <el-form-item label="Max Salary" prop="maxSalary" >
                <el-input v-model="postInformation.maxSalary" onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')"></el-input>
            </el-form-item>

            
            <el-form-item>
              <el-button class="submit" type="primary" @click="submitForm('postInformation')">submit</el-button>
              <el-button @click="resetForm('postInformation')">replace</el-button>
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
            postInformation: {
                title:'',
                description: '',
                industry: '',
                position: '',
                address: '',
                employmentType: '',
                educationalBackground: '',
                minSalary: null,
                maxSalary: null,
            },

            rules: {
                title: [
                    { required: true, message: 'Please enter your first name!', trigger: 'blur' },
                    { min: 30, message: '30 letters or less!', trigger: 'blur' }
                ],
                description: [
                    { required: true, message: 'Please enter your last name!', trigger: 'blur' },
                ],
                educationalBackground: [
                    { required: true, message: 'Please select your educational background!', trigger: 'blur' },
                ],
                employmentType: [
                    { required: true, message: 'Please select your employment type!', trigger: 'blur' },
                ],
            },

            employmentTypeOptions: [{
            value: 'Full-time',
            label: 'Full-Time'
            }, {
            value: 'Part-time',
            label: 'Part-Time'
            }, {
            value: 'Contract',
            label: 'Contract'
            }],

            backgroundOptions: [{
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
        };
      
    },
    methods: {
      submitForm(formName) {
        console.log(qs.stringify(this.postInformation))
        this.$refs[formName].validate((valid) => {
          if (valid) {
             this.$axios.post('/api/post/create',this.postInformation).then(res => {
               this.$router.push("/get-All-Company-Post")
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
.role{
  width: 100%;
}
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