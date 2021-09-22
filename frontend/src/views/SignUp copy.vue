<template>
  <div>
    <el-row type="flex" class="step" justify="center">
      <el-col :span="6"><div class="step2">
        <el-steps :active="active" finish-status="success">
          <el-step title="Register"></el-step>
          <el-step title="Profile"></el-step>
        </el-steps>
      </div></el-col>
    </el-row>

    <el-row type="flex" class="form" justify="center">
      <el-col :span="8">
        <div>
          <el-form :model="signupForm" :rules="rules" ref="signupForm"  label-position="left">
            <el-form-item label="email" prop="email" >
                <el-input v-model="signupForm.email"></el-input>
            </el-form-item>
            <el-form-item label="password" prop="password">
                <el-input v-model="signupForm.password" type="password"></el-input>
            </el-form-item>
            <el-form-item label="password confirm"  prop="password_confirm">
                <el-input v-model="signupForm.password_confirm" type="password"></el-input>
            </el-form-item>
            <el-form-item  label="account role" prop="roleId">
              <el-select v-model="signupForm.roleId" style="width: 320px;">
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="verify code" prop="verifyCode" >
                <el-input v-model="signupForm.verifyCode" style="display:inline-block; width:248px;"></el-input>
                <el-button type="primary" @click="sendVerifyCode('signupForm')" style="margin:0;">send</el-button>
            </el-form-item>
            <el-form-item>
              <el-button class="submit" type="primary" @click="submitForm('signupForm')">submit</el-button>
              <el-button @click="resetForm('signupForm')">replace</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-col>
    </el-row>
  </div>
</template>
 

<script>
import Element from "element-ui";
export default {
  name: 'SignUp',
  // components: {
  //   HelloWorld
  // }
  data() {
      return {
        
        password_confirm:"",
        active: 0,
        signupForm: {
          email: '',
          password: '',
          roleId: null,
          verifyCode:''
        },
        rules: {
          email: [
            { required: true, message: 'Please enter email number!', trigger: 'blur' },
            { min: 5, message: '5-10 number or letters!', trigger: 'blur', validator: checkEmail }
          ],
          password: [
            { required: true, message: 'Please enter password!', trigger: 'blur' },
            { min: 6, max: 20, message: 'At lease 6 numbers or letters!', trigger: 'blur' },
          ],
          password_confirm: [
            { required: true, message: 'Please enter password again!', trigger: 'blur' },
            { min: 6, max: 20, message: 'At lease 6 numbers or letters!', trigger: 'blur' },
            { }
          ],
          roleId: [
            { required: true, message: 'Please select account role!', trigger: 'blur' },
          ],
          verifyCode: [
            { required: true, message: 'Please enter verify code!', trigger: 'blur' },
          ]
        },

        options: [{
          value: 1,
          label: 'personal'
        }, {
          value: 2,
          label: 'company'
        }],

      };
      var checkEmail = (rule, value, cb) => {
            const regEmail = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
            if (regEmail.test(value)) {
                // 合法的邮箱
                return cb() 
            }
            cb(new Error('请输入合法的邮箱'))
        };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            if(this.signupForm.password == this.password_confirm){
              alert("The two passwords entered are inconsistent")
            }else{
              // this.$axios.post('/api/account/register?',qs.stringify(this.signupForm)).then(res => {
              this.$axios.post('/api/account/register?',this.signupForm).then(res => {
              // const jwt = res.headers['authorization']
              // this.$store.commit('SET_EMAIL',jwt)
              // this.$store.commit('SET_PASSWORD',jwt)
               this.$router.push("/signin")
              });
            }
          }else {
            return false;
          }
        });
      },
      sendVerifyCode(){
          if (this.signupForm.email!='') {
            console.log('send')
            this.$axios.post('/api/account/verify/email',this.signupForm.email).then(res => {
                  console.log(res.data.message)
                  console.log(res.data.statusCode)
                });
          }else {
             Element.Message.error('Please enter email first!')
          }

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
  text-align: left;
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
.submit:focus{
  background-color: #54c685;
}
button{
  margin-left: 20%;

}
</style>