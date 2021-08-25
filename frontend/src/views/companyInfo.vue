<template>
  <div>
    <el-row type="flex" class="form" justify="center">
      <el-col :span="8">
        <div>
          <el-form :model="informationForm" ref="informationForm" label-width="200px">
            <el-form-item>
                <h1>Your profile</h1>
            </el-form-item>

            <el-form-item label="Company Name: ">
                <span v-text="informationForm.companyName"></span>
            </el-form-item>
            
            <el-form-item label="Start Up Date: ">
                <span v-text="informationForm.startUpDate"></span>
            </el-form-item>

            <el-form-item label="Owner: ">
                <span v-text="informationForm.owner"></span>
            </el-form-item>

            <el-form-item label="Industry: ">
                <span v-text="informationForm.industry"></span>
            </el-form-item>

          </el-form>
        </div>
      </el-col>
    </el-row>
  </div>
</template>


<script>
// @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue'
export default {
  data() {
        return {
          informationForm: {
              companyId: '',
              companyName: '',
              startUpDate: '',
              owner: '',
              industry: '',
              accountId: '',
            },
        };
    },
      created() {
        this.getCompanyInfo();
      },
    methods: {
      getCompanyInfo(){
        this.$axios.get('/api/company/info').then(res => {
          this.informationForm.companyId = res.data.data.companyId
          this.informationForm.companyName = res.data.data.companyName
          this.informationForm.startUpDate = res.data.data.startUpDate
          this.informationForm.owner = res.data.data.owner
          this.informationForm.industry = res.data.data.industry
          this.informationForm.accountId = res.data.data.accountId
        });
      }
    }
}




</script>

<style scoped>

.step{
  height: 200px;
  align-items: center;
  margin: 0ch;
}
.form{
  padding: 15px;
}
.step2{
  width: 500px;
}
.submit{
  background-color: #54c685;
  
}
span{
  margin-left: 20px;
  padding-left: 50px;
}

/* .submit:hover{
  background-color: #3ea56a;
}
.submit:visited{
  background-color: #54c685;
}
.submit:active{
  background-color: #54c685;
} */
.submit:focus{
  background-color: #54c685;
}
button{
  margin-left: 20%;
  /* background-color: #54c685; */
}
</style>