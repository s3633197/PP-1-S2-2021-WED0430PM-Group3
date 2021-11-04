<template>
<!-- html -->
  <div>
    <meta http-equiv="Content-Security-Policy" content="script-src 'self'">
    <br>
        <div class="form">
          <h1>Sign Up</h1>
          <el-form :model="signupForm" :rules="rules" ref="signupForm"  label-position="left">
            <el-form-item label="Email" prop="email">
                <el-input v-model="signupForm.email"></el-input>
            </el-form-item>
            <el-form-item label="Password" prop="password">
                <el-input v-model="signupForm.password" type="password"></el-input>
            </el-form-item>
            <el-form-item label="Password Confirm"  prop="password_confirm">
                <el-input v-model="signupForm.password_confirm" type="password"></el-input>
            </el-form-item>
            <el-form-item label="Account Role" prop="roleId" >
              <el-select v-model="signupForm.roleId" class="role">
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="Verify Code" prop="verifyCode">
              <br>
                <el-input v-model="signupForm.verifyCode" class="input"></el-input>
                <el-button type="primary"  class="submit" @click="sendVerifyCode('signupForm')">send</el-button>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" class="submit" @click="submitForm('signupForm')">submit</el-button>
              <el-button @click="resetForm('signupForm')">replace</el-button>
            </el-form-item>
          </el-form>
        </div>
        <br>
      </div>
</template>
 
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
.role{
  width: 100%;
}
.input{
  width: 78.5%;
  margin-right: 5%;
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
/*css only for small size screen like mobile phone*/
@media only screen and (max-width : 900px) {
  .form{
    width: 80%;
    margin-bottom:5%;
    margin-top: 10%;
    margin-left:6%;
  }
  .input{
    width: 70%;
  }
}
</style>



<script>
import Element from "element-ui";
export default {
  name: 'SignUp',
  data() {
      return {
        active: 0,
        signupForm: {
          email: '',
          password: '',
          password_confirm:'',
          roleId: null,
          verifyCode:''
        },
        // rules for sign up input
        rules: {
          email: [
            { required: true, message: 'Please enter email number!', trigger: 'blur' },
            { min: 5, message: 'At lease 5 number or letters!', trigger: 'blur',},
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
          password_confirm: [
            { required: true, message: 'Please enter password again!', trigger: 'blur' },
            { min: 6, max: 20, message: 'At lease 6 numbers or letters!', trigger: 'blur' },
          ],
          roleId: [
            { required: true, message: 'Please select account role!', trigger: 'blur' },
          ],
          verifyCode: [
            { required: true, message: 'Please enter verify code!', trigger: 'blur' },
          ]
        },
        //selections
        options: [{
          value: 1,
          label: 'personal'
        }, {
          value: 2,
          label: 'company'
        }],

      };
    },
    methods: {
      //post the info email, password and role to backend
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            if(this.signupForm.password != this.signupForm.password_confirm){
              alert("The two passwords entered are inconsistent")
            }else{
              this.$axios.post('/api/account/register?',this.signupForm).then(res => {
               this.$router.push("/signin")
              });
            }
          }else {
            return false;
          }
        });
      },
      //send the verify code to the email 
      sendVerifyCode(){
          if (this.signupForm.email!='') {
            this.$axios.post('/api/account/verify/email',this.signupForm.email).then(res => {

                });
          }else {
             Element.Message.error('Please enter email first!')
          }

      },
      //clear the info in the input
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
    }
    
}

</script>

