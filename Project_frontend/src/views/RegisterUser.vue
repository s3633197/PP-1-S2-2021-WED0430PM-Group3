<template>
    <el-form ref="form" :model="form" label-width="80px">
        <el-row>
            <el-col
                :span="8"
                :offset="1"
            >
                <el-form-item label="Phone">
                    <el-input v-model="form.phone"></el-input>
                </el-form-item>
            </el-col>
            <el-col
                :span="8"
                :offset="1"
            >
                <el-form-item label="School">
                    <el-input v-model="form.school"></el-input>
                </el-form-item>
            </el-col>
        </el-row>
        <el-row>
            <el-col
                :span="8"
                :offset="1"
            >
                <el-form-item label="Gender">
                    <el-select v-model="form.gender">
                        <el-option label="Male" value="Male"></el-option>
                        <el-option label="Female" value="Female"></el-option>
                    </el-select>
                </el-form-item>
            </el-col>
            <el-col
                :span="8"
                :offset="1"
            >
                <el-form-item label="Major">
                    <el-input v-model="form.major"></el-input>
                </el-form-item>
            </el-col>
        </el-row>
        <el-row>
            <el-col
                :span="8"
                :offset="1"
            >
                <el-form-item label="Birthday">
                    <el-date-picker v-model="form.birthday" type="date"></el-date-picker>
                </el-form-item>
            </el-col>
            <el-col
                :span="8"
                :offset="1"
            >
                <el-form-item label="Education">
                    <el-input v-model="form.education"></el-input>
                </el-form-item>
            </el-col>
        </el-row>
        <el-row>
            <el-col
                :span="8"
                :offset="1"
            >
                <el-form-item label="Location">
                    <el-input v-model="form.location"></el-input>
                </el-form-item>
            </el-col>
            <el-col
                :span="8"
                :offset="1"
            >
                <el-form-item label="Skill">
                    <el-input v-model="form.Skill"></el-input>
                </el-form-item>
            </el-col>
        </el-row>
        <el-row>
            <el-col
                :span="8"
                :offset="1"
            >
                <el-form-item label="Statement">
                    <el-input v-model="form.statement"></el-input>
                </el-form-item>
            </el-col>
            <el-col
                :span="8"
                :offset="1"
            >
                <el-form-item label="Expect Industry">
                    <el-input v-model="form.expect_Industry"></el-input>
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
                phone:"",
                school: "",
                gender: "",
                major: "",
                birthday: "",
                education: "",
                location: "",
                skill: "",
                statement: "",
                expect_Industry:""
            }
        }
    },
    methods:{

    }
}
</script>

<style scoped>


</style>