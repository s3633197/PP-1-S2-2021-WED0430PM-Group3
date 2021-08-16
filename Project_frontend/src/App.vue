<template>
  <router-view />
</template>

<script>

export default {
  name: 'App',
  components: {
  },
  mounted(){
    // window.addEventListener('beforeunload',()=>{
    //   localStorage.removeItem('token');
    // })
  }
}
</script>

<style>
  @import "./css/main.css";
</style>
