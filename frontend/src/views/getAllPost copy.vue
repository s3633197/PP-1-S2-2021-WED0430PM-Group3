<template>
  <el-table style="width: 100%">
    <el-table-column label="Title" width="180">
      <template>
        <span v-for="item in array" :key="item.title" style="margin-left: 10px">{{ item.title }}</span>
      </template>
    </el-table-column>
    <el-table-column label="minSalary" width="180">
      <template>
        <span v-for="item in array" :key="item.minSalary" style="margin-left: 10px">{{ item.minSalary }}</span>
      </template>
    </el-table-column>
     
    <el-table-column label="操作">
      <template slot-scope="scope">
        <el-button
          size="mini"
          @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
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
          this.startUpYear = res.data.data.startUpYear
          this.companyName = res.data.data.companyName
          this.companyId = res.data.data.companyId
        });
      }
    }
}




</script>