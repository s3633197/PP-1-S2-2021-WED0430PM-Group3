<template>
    <el-form ref="form" :model="form" label-width="80px">
        <el-row>
            <el-col
                :span="8"
                :offset="1"
            >
                <el-form-item label="Title">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
            </el-col>
            <el-col
                :span="8"
                :offset="1"
            >
                <el-form-item label="Enrollment">
                    <el-input v-model="form.enrollment"></el-input>
                </el-form-item>
            </el-col>
        </el-row>
        <el-row>
            <el-col
                :span="8"
                :offset="1"
            >
                <el-form-item label="Description">
                    <el-input v-model="form.description"></el-input>
                </el-form-item>
            </el-col>
            <el-col
                :span="8"
                :offset="1"
            >
                <el-form-item label="Educational">
                    <el-input v-model="form.educational"></el-input>
                </el-form-item>
            </el-col>
        </el-row>
        <el-row>
            <el-col
                :span="8"
                :offset="1"
            >
                <el-form-item label="Industry">
                    <el-input v-model="form.industry"></el-input>
                </el-form-item>
            </el-col>
            <el-col
                :span="8"
                :offset="1"
            >
                <el-form-item label="Statement">
                    <el-input v-model="form.statement"></el-input>
                </el-form-item>
            </el-col>
        </el-row>
        <el-row>
            <el-col
                :span="8"
                :offset="1"
            >
                <el-form-item label="Job Function">
                    <el-input v-model="form.jobFunction"></el-input>
                </el-form-item>
            </el-col>
            <el-col
                :span="8"
                :offset="1"
            >
                <el-form-item label="Seniority">
                    <el-input v-model="form.Seniority"></el-input>
                </el-form-item>
            </el-col>
        </el-row>
        <el-row>
            <el-col
                :span="8"
                :offset="1"
            >
                <el-form-item>
                    <el-button type="primary">Submit</el-button>
                    <el-button>Skip</el-button>
                </el-form-item>
            </el-col>
        </el-row>
    </el-form>
</template>

<script>
export default {
    name: "Register",
    data(){
        return{
            form:{
                title:"",
                enrollment: "",
                description: "",
                educational: "",
                industry: "",
                statement: "",
                jobFunction: "",
                seniority: ""
            }
        }
    },
    methods:{

    }
}
</script>

<style scoped>


</style>