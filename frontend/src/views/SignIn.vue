<template>
      <div>
        <br>
        <div class="form">
          <h1 class="h1">Sign In</h1>
          <el-form :model="signinForm" :rules="rules" ref="signinForm">
            <el-form-item label="Email" prop="username">
                <el-input v-model="signinForm.username"></el-input>
            </el-form-item>
            <el-form-item label="Password" prop="password" >
                <el-input v-model="signinForm.password" type="password"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button class="submit" type="primary" @click="submitForm('signinForm')">submit</el-button>
              <el-button @click="forgotPassword('signinForm')">forgot password</el-button>
            </el-form-item>
          </el-form>
        </div>
         <br>
  </div>
</template>


<script>
import qs from 'qs'
export default {
  name: 'SignIn',
  data() {
      return {
        active: 0,
        signinForm: {
          username: '',
          password: null,
          
        },
        rules: {
          username: [
            { required: true, message: 'Please enter email number!', trigger: 'blur' },
            { min: 5, message: 'At lease 5 number or letters!', trigger: 'blur' },
            {
              validator: function(rule, value, callback) {
                if (/^\w{1,64}@[a-z0-9\-]{1,256}(\.[a-z]{2,6}){1,2}$/i.test(value) == false) {
                  callback(new Error("Wrong email format!"));
                } else {
                  callback();
                }
              },
              trigger: "blur"
            }
          ],
          password: [
            { required: true, message: 'Please enter password!', trigger: 'blur' },
            { min: 6, max: 20, message: 'At lease 6 numbers or letters!', trigger: 'blur' },
          ],
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
              this.$axios.post('/login?',qs.stringify(this.signinForm),{headers:{'Content-Type' : "application/x-www-form-urlencoded; application/json"}}).then(res => {
                const jwt = res.headers['authentication']
                const roleId = res.data.data.roleId
                this.$store.commit('SET_TOKEN',jwt)
                this.$store.commit('SET_ROLEID',roleId)
                this.$router.push("/index")
                this.$router.go(0)
              });
          }else {
            console.log("login fail")
            return false;
          }
        });
      },
      forgotPassword(formName) {
        // this.$axios.post('/forgot?',qs.stringify(this.signinForm),{headers:{'Content-Type' : "application/x-www-form-urlencoded; application/json"}}).then(res => {
        //       // this.$axios.post('/login',qs.stringify(this.signinForm)).then(res => {

        // });
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
.submit:hover{
  background-color: #3ea56a;
}
.submit:focus{
  background-color: #54c685;
}
@media only screen and (max-width: 800px) {
  .form{
    width: 80%;
    margin-bottom:5%;
    margin-top: 10%;
    margin-left:6%;
  }
}
</style>