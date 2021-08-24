<template>
  <div>
    <el-row type="flex" class="form" justify="center">
      <el-col :span="8">
        <div>
          <el-form :model="postInformation" :rules="rules" ref="postInformation" label-width="200px">
            <el-form-item>
                <h1>Create posts</h1>
            </el-form-item>
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
            <el-form-item label="Location" prop="location" >
                <el-input v-model="postInformation.location"></el-input>
            </el-form-item>
           
            <el-form-item label="Employment Type" prop="employmentType" >
                <el-select v-model="postInformation.employmentType" style="width: 272px;" placeholder="Please select">
                    <el-option
                        v-for="item in employmentTypeOptions"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                    </el-option>
                </el-select>
            </el-form-item>

            <el-form-item label="Educational Background" prop="educationalBackground" >
                <el-select v-model="postInformation.educationalBackground" style="width: 272px;" placeholder="Please select">
                    <el-option
                        v-for="item in backgroundOptions"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                    </el-option>
                </el-select>
            </el-form-item>

            <el-form-item label="Min Salary" prop="minSalary" >
                <el-input v-model="postInformation.minSalary"></el-input>
            </el-form-item>

             <el-form-item label="Max Salary" prop="maxSalary" >
                <el-input v-model="postInformation.maxSalary"></el-input>
            </el-form-item>

            
            <el-form-item>
              <el-button class="submit" type="primary" @click="submitForm('postInformation')">submit</el-button>
              <el-button @click="resetForm('postInformation')">replace</el-button>
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
            postInformation: {
                title:'',
                description: '',
                industry: '',
                position: '',
                location: '',
                employmentType: '',
                educationalBackground: '',
                minSalary: null,
                maxSalary: null,
            },

            rules: {
                title: [
                    { required: true, message: 'Please enter your first name!', trigger: 'blur' },
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
            value: 'Full Time',
            label: 'Full Time'
            }, {
            value: 'Part Time',
            label: 'Part Time'
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
               console.log("post"+res.data)
            //   this.$router.push("/index")
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