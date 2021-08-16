
<template>
  <div class="login">
    <img id='background' alt="Matridx background" src="../assets/loginimg/background.png">
    <span id="loginText">XXXXX系统</span>
    <div class="formbox">
      <div class="forminer">
        <login-form/>
      </div>
    </div>
  </div>
</template>

<script>

import LoginForm from '../components/LoginForm.vue'

export default {
  name: 'login',
  components:{
    LoginForm
  },
  data(){
    return{
    }
  },
  mounted(){
    document.querySelector('body').setAttribute('style', 'background-color:rgb(29,32,132);')
  },
  beforeUnmount(){
    document.querySelector('body').removeAttribute('style')
  },
  methods:{
  }
}
</script>

<style scoped>
#logo{
  position:absolute;
  z-index: 17;
  left: 10rem;
  top: 4rem;
  width: 8rem;
  height: 4rem;
}
#background{
  position:absolute;
  z-index: 3;
  width:67%;
  height:88.5%;
  left:14%;
  top:11.5%;
}
.formbox{
  position:absolute;
  z-index: 5;
  width:35%;
  height:40%;
  top:35%;
  left:32.5%;
  background-color:rgba(255, 255, 255, 70%);
  border-radius: 6px;
}
.forminer{
  position:absolute;
  width:80%;
  top:10%;
  left:10%;
}
#loginText{
  position:absolute;
  z-index: 55;
  width: 43%;
  display: block;
  overflow-wrap: break-word;
  color: rgba(254, 254, 254, 1);
  font-size: 200%;
  letter-spacing: 5px;
  font-family: SourceHanSansCN-Medium;
  line-height: 60px;
  left:32.5%;
  top:22%;
}
</style>