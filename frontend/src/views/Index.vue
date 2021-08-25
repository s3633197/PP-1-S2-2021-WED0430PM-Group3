<template>
    <div class="img">
         <img src="../assets/img.jpg">
         <img src="../assets/img.jpg">
         <div>
            <router-link to="/profile">Profile | </router-link>
            <router-link to="/resume">Resume | </router-link>
            <router-link to="/create-Post">create-Post | </router-link>
            <router-link to="/create-company">create-company | </router-link>
            <router-link to="/company-info">company-info</router-link>
         </div>
         
    </div>
</template>

<style>
img{
    height: 300px;
    width: 500px;
    display: inline;
    margin: 50px;
    
}
.img{
    text-align: center;
    height: auto;
    background-color: aliceblue;
    padding: 50px;
    
}
.el-col{
    margin: 10px;
    padding: 10px;
}
</style>


<script>
    export default {
        created() {
        console.log("body")
        },

    }
</script>