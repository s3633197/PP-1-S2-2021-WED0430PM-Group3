<template>
  <el-input
    v-model="username"
    placeholder="E-mail"
    prefix-icon="el-icon-user"
  ></el-input>
  <el-input
    v-model="password"
    placeholder="password"
    show-password
    prefix-icon="el-icon-lock"
    style="top: 16px"
  ></el-input>
  <span id="forgetPassword">Forget password</span>
  <el-button
    style="
      background: #1d2084;
      border-radius: 6px;
      width: 100%;
      margin-top: 60px;
    "
    @click="login"
    ><span id="loginButton">Login</span></el-button
  >
</template>

<script>
import api from "../api";

export default {
  name: "LoginForm",
  data() {
    return {
      username: "",
      password: "",
    };
  },
  methods: {
    login: function () {
      //this.$router.replace('/manage')
      api
        .standardcurdPost("user-center/login/", {
          username: this.username,
          password: this.password,
        })
        .then((response) => {
          //console.log(response.data);
          localStorage.setItem("token",response.data.data.token);
          localStorage.setItem("userInfo",JSON.stringify(response.data.data.user));
          api.standardcurdGet("user-center/lab/",{
            page:1,
            page_size:1000
          }).then(
            (response) => {
              localStorage.setItem("labs",JSON.stringify(response.data.data));
            }
          )
          api.standardcurdGet("sample-center/sample_type/",{
            page:1,
            page_size:1000
          }).then(
            (response) => {
              localStorage.setItem("sample_type",JSON.stringify(response.data.data));
            }
          )
          this.$router.push('/root/samplelist');
        });
    },
  },
};
</script>

<style scoped>
#forgetPassword {
  position: absolute;
  padding-top: 64px;
  right: 8px;
  width: 135px;
  height: 16px;
  font-size: 16px;
  font-family: SourceHanSansCN-Regular, SourceHanSansCN;
  font-weight: 400;
  color: #1d2084;
  line-height: 24px;
}

#loginButton {
  font-size: 24px;
  font-family: SourceHanSansCN-Regular, SourceHanSansCN;
  font-weight: 400;
  color: #ffffff;
}
</style>