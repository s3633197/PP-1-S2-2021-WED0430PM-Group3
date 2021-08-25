<template>
  <div>
    <el-row type="flex" class="form" justify="center">
      <el-col :span="8">
        <div>
          <el-form :model="informationForm" :rules="rules" ref="informationForm" label-width="200px">
            <el-form-item>
                <h1>Create company</h1>
            </el-form-item>
            <el-form-item label="Company Name" prop="companyName">
                <el-input v-model="informationForm.companyName"></el-input>
            </el-form-item>
            
            
            <el-form-item label="Start Up Date" prop="startUpDate" >
                <el-date-picker
                    style="width: 272px;"
                    v-model="informationForm.startUpDate"
                    type="date"
                    placeholder="select a date">
                </el-date-picker>
            </el-form-item>

            <el-form-item label="Owner" prop="owner" >
                <el-input v-model="informationForm.owner"></el-input>
            </el-form-item>

            <el-form-item label="Industry" prop="industry" >
                <el-input v-model="informationForm.industry"></el-input>
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
export default {
  data() {
        return {
            informationForm: {
              companyName: '',
              startUpDate: '',
              owner: '',
              industry: '',
            },

            rules: {
                companyName: [
                    { required: true, message: 'Please enter company name!', trigger: 'blur' },
                ],
                startUpDate: [
                    { required: true, message: 'Please enter start up date!', trigger: 'blur' },
                ],
                owner: [
                    { required: true, message: 'Please enter the owner!', trigger: 'blur' },
                ],
                industry: [
                    { required: true, message: 'Please enter industry type!', trigger: 'blur' },
                ],
            },
            
            pickerOptions: {
                disabledDate(time) {
                    return time.getTime() > Date.now();
                }
            },
        };
      
    },

    methods: {
      submitForm(formName) {
        
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$axios.post('/api/company/create',this.informationForm).then(res => {
              
              // this.$router.push("/resume")
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