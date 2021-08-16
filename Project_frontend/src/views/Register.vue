<template>
    <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="Name">
            <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="Email">
            <el-input v-model="form.email"></el-input>
        </el-form-item>
        <el-form-item label="password">
            <el-input v-model="form.password"></el-input>
        </el-form-item>
        <el-form-item label="role">
            <el-select v-model="form.role"></el-select>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" >Register</el-button>
        </el-form-item>
    </el-form>
</template>

<script>
export default {
    name: "Register",
    data(){
        return{
            form:{
                name:"",
                email: "",
                password: "",
                role: ""
            }
        }
    },
    methods:{

    }
}
</script>

<style scoped>


</style>