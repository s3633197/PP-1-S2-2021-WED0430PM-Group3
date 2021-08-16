<template>
    <el-button @click="btnclick">点击测试</el-button>
    <div>{{data}}</div>
</template>

<script>

import api from '../api'

export default {
    name:"ApiTest",
    components:{},
    data(){
        return{
            data:""
        }
    },
    methods:{
        btnclick:function(){
            // api.userlogin({
            //     user:"test",
            //     password:"test"
            // })
            let res = api.standardcurdGet('app',{});
            
            res.then((response)=>{
                this.data = response.data;
            })
        }
    }
}
</script>

<style scoped>

</style>