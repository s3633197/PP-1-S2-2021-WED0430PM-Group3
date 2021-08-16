<template>
    <ja-container :info="info"/>    
</template>

<script>

import JaContainer from '../components/JaContainer.vue';

import config from '../config'

export default {
    name:"Root",
    components:{
        JaContainer
    },
    computed:{
        info(){
            return this.$store.state.info;
        }
    },
    data(){
        return{
            //info:config
        }
    },
    beforeMount(){
        this.$store.dispatch("setinfo",config);
        var main = require("../config/"+this.$route.params.page+".js");
        //console.log(main.default.main);
        //console.log(main);
        this.$store.dispatch("setMain",main);
        //this.info.children[0].children[1].children[1].children = main.default.main;
    },
    watch:{
        // eslint-disable-next-line no-unused-vars
        $route(to,from){
            if(this.$route.params.page || this.$route.params.pk){
                var main = require("../config/"+this.$route.params.page+".js");
                this.$store.dispatch("setMain",main);
            }
        }
    },
    methed:{},
}

</script>

<style>



</style>