<template>
    <el-button @click="add">{{msg}}</el-button>
</template>

<script>
export default {
    name:"TestCompontents",
    props:{
        msg:String
    },
    methods:{
        add:function(){
            this.$store.state.count += 100;
        }
    }
}
</script>