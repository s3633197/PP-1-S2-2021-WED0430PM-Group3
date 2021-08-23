<template>
  <div>
    <el-row type="flex" class="form" justify="center">
      <el-col :span="8">
        <div>
          <el-form :model="signinForm" :rules="rules" ref="signinForm" label-width="200px">
            <el-form-item label="email" prop="email">
                <el-input v-model="signinForm.email"></el-input>
            </el-form-item>
            <el-form-item label="password" prop="password" >
                <el-input v-model="signinForm.password" type="password"></el-input>
            </el-form-item>
            
            <el-form-item>
              <el-button class="submit" type="primary" @click="submitForm('signinForm')">submit</el-button>
              <el-button @click="resetForm('signinForm')">replace</el-button>
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
  name: 'SignIn',
  // components: {
  //   HelloWorld
  // }
  data() {
      return {
        active: 0,
        signinForm: {
          email: '',
          password: '',
          
        },
        rules: {
          email: [
            { required: true, message: 'Please enter email number!', trigger: 'blur' },
            { min: 5, max: 20, message: '5-10 number or letters!', trigger: 'blur' }
          ],
          password: [
            { required: true, message: 'Please enter password!', trigger: 'blur' },
            { min: 6, max: 20, message: 'At lease 6 numbers or letters!', trigger: 'blur' },
          ],
        }
      };
    },
    //  created() {
    //    this.getemail();
    //  },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
              this.$axios.post('/login?',qs.stringify(this.signinForm)).then(res => {
                console.log("code = " + res.data.data.statusCode)
                const jwt = res.headers['Authentication']
                this.$store.commit('SET_TOKEN',jwt)
                this.$store.commit('SET_EMAIL',jwt)
                this.$router.push("/index")
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



import qs from 'qs'
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
.submit:hover{
  background-color: #3ea56a;
  
}
.submit:focus{
  background-color: #54c685;
}
button{
  margin-left: 20%;
  /* background-color: #54c685; */
}
</style>