<template>
    <ja-container />    
</template>

<script>

import JaContainer from '../components/JaContainer.vue';

export default {
    name:"Root",
    components:{
        JaContainer
    },
    data(){
        return{
        }
    },
    methed:{}
}

</script>

<style>
.default-container{
    position: absolute;
    left: 0px;
    top: 0px;
    width: 100%;
    height: 100%;
}

.default-header{
    background-color:rgba(29, 32, 132, 1)
}

.default-header-img{
    height:80%;
    padding-top:0.5%;
}

.default-header-img:hover{
    cursor: pointer;
}

.default-header-text{
    font-size: 16px;
    font-family: HelveticaNeue;
    color: #FFFFFF;
    padding-top:1%;
}

.default-header-text:hover{
    cursor: pointer;
}

.default-header-img-2{
    height: 10%;
    padding-top: 1%;
}

.default-header-img-2:hover{
    cursor: pointer;
}

.default-logo{
    width:1rem;
}

.default-text-1{
    font-size:0.8rem;
    color:#1D2084;
    font-family: SourceHanSansCN-Medium, SourceHanSansCN;
    font-weight: 500;
}

.default-text-2{
    font-size:0.8rem;
    font-family: SourceHanSansCN-Medium, SourceHanSansCN;
    color:#333333;
    font-weight:400;
}

</style>