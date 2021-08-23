<template>
  <div id="app">
    <div id="logoo">
      <img alt="logo" src="../assets/Logo.svg">
    </div>
    <div id="nav">
      <router-link to="/Index">Home</router-link> |
      <router-link to="/SignUp">Sign Up</router-link> |
      <router-link to="/SignIn">Sign In</router-link>
    </div>
    <router-view/>
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  height: 100%;
}
#logoo {
  width: 240px;
  height: 78px;
  float: left;
  background-color: rgb(240, 240, 240);
}
#logoo img{
  width: 100%;
  height: 80%;
  margin: 0;
  padding-top: 3%;
}
#nav {
  padding: 30px;
  text-align: right;
  width: 80%;
  display: inline-block;
  background-color: rgb(240, 240, 240);
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}
#nav a.router-link-exact-active {
  color: #42b983;
}
.img {
  height: 80%;
  width: 100%;
  margin: 0;
}

</style>
