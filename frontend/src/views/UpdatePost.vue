<template>
<!-- html -->
  <div>
    <br>
        <div class="form">
          <h1 class="h1">Update Post</h1>
          <el-form class="elform" :inline="true" :model="postInformation" :rules="rules" ref="postInformation" label-width="130px" :label-position="left">
            <el-form-item label="Title" prop="title">
                <el-input v-model="postInformation.title" class="shortInput"></el-input>
            </el-form-item>

            <el-form-item label="Position" prop="Position" >
                <el-input v-model="postInformation.position" class="shortInput"></el-input>
            </el-form-item>

           <el-form-item label="Education" prop="educationalBackground" >
                <el-select class="shortInput" v-model="postInformation.educationalBackground" placeholder="Please select">
                    <el-option
                        v-for="item in backgroundOptions"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="Employment Type" prop="employmentType" >
                <el-select v-model="postInformation.employmentType" placeholder="Please select" class="shortInput">
                    <el-option
                        v-for="item in employmentTypeOptions"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                    </el-option>
                </el-select>
            </el-form-item>
            
            <el-form-item label="Address" prop="address" >
                <el-input v-model="postInformation.address" class="longInput"></el-input>
            </el-form-item>
           
            
            <el-form-item label="Industry" prop="industry" >
                <el-input v-model="postInformation.industry" class="longInput"></el-input>
            </el-form-item>
            

            <el-form-item label="Min Salary" prop="minSalary" >
                <el-input class="shortInput" v-model="postInformation.minSalary" onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')"></el-input>
            </el-form-item>

             <el-form-item label="Max Salary" prop="maxSalary" >
                <el-input class="shortInput" v-model="postInformation.maxSalary" onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')"></el-input>
            </el-form-item>

            <el-form-item label="Description" prop="description" >
                <textarea v-model="postInformation.description" rows="10" cols="50" class="longInput"></textarea>
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
                postId:null,
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
    created() {
       this.postInformation = this.$route.query.item
       this.postInformation.position = this.$route.query.item.location
       this.postInformation.employmentType = this.$route.query.item.jobType
       this.postInformation.address = this.$route.query.item.location
     },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
             this.$axios.put('/api/post/update/'+this.postInformation.postId,this.postInformation).then(res => {
               this.$router.push({name: 'PostDetailCompany', params: { postId: this.postInformation.postId }})
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
  width: 580px;
  resize: none;
}
/*css only for small size screen like mobile phone*/
@media only screen and (max-width: 900px) {
  .form{
    width: 80%;
    margin-bottom:5%;
    margin-top: 10%;
    margin-left:6%;
    padding: 0px;
  }
  .shortInput{
    width: 500px;
  }
  .longInput{
    width: 605px;
    resize: none;
  }
}

</style>