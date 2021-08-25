<template>
  <el-row id ="row" class="row">
    <el-col v-for="(o, index) in array" :key="o" :offset="index > 0 ? 2 : 0" id ="col">
      <el-card id="card" key="o.postId">
        <div class="div">
          <span v-text="o.title" class="span"></span>
          <span v-text="o.location" class="span"></span>
          <span v-text="o.companyName" class="span"></span>
        </div>
        <div class="div">
          <span v-text="o.Salary" class="span"></span>
          <span v-text="o.postInformationForm.jobType" class="span"></span>
          <span v-text="o.industryDate" class="span"></span>
        </div>
        <div class="div">
          <span v-text="o.educationalBackground"></span>
        </div>
        <!-- <div style="padding: 14px; ">
          <div class="bottom clearfix">
            <el-button type="text" class="button">操作按钮</el-button>
          </div>
        </div> -->
      </el-card>
    </el-col>
  </el-row>
</template>

<style>
#col{
  width: 740px;
  margin: 5px;
  padding: 0;
}
#row{
  margin: 0;
  padding: 0;
}
#card{
  margin: 0;
  padding: 0;
}
.span{
  margin: 0;
  margin-left: 120px;
}
.div{
  padding: 20px;
  margin: 0;
}

</style>

<script>
// @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue'
export default {
  data() {
        return {
          array:null,
          postId: null,
          title:'',
          minSalary: null,
          maxSalary: null,
          educationalBackground: '',
          location: '',
          industry: '',
          jobType: '',
          startUpYear: '',
          companyName: '',
          companyId: null,
          Salary: minSalary+" - "+maxSalary,
          industryDate: industry+" | "+startUpYear
        };
    },
      created() {
        this.getAllPost();
      },
    methods: {
      getAllPost(){
        this.$axios.get('/api/post/all').then(res => {
          this.array = res.data
          this.postId = res.data.data.postId
          this.title = res.data.data.title
          this.minSalary = res.data.data.minSalary
          this.maxSalary = res.data.data.maxSalary
          this.educationalBackground = res.data.data.educationalBackground
          this.location = res.data.data.location
          this.industry = res.data.data.industry
          this.jobType = res.data.data.jobType
          this.startUpYear = res.data.dat.startUpYear
          this.companyName = res.data.data.companyName
          this.companyId = res.data.data.companyId
        });
      }
    }
}




</script>