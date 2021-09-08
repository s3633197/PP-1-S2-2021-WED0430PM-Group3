<template>
    <div>
        <el-row>
            <h1 style="text-align:center;">All Posts</h1>
            <el-col :span="16" v-for="item in array" :key="item.title" id="card">
                <el-card :body-style="{ padding: '0px' }">
                    <div style="float:left;margin:14px;width:298px;">
                        <span style="font-size:30px;">{{ item.title }}</span><br><br>
                        <span>{{ item.minSalary }}-{{ item.maxSalary }}/month</span><br><br>
                        <span >{{ item.educationalBackground }}</span>
                    </div>
                    <div style="float:left;margin:14px;width:298px;">
                        <span style="margin:10px; ">{{ item.location || 'empty' }}</span><br><br><br>
                        <span > JobType: {{ item.jobType }}</span>
                    </div>
                    <div style="float:right;margin:14px;width:298px;">
                        <span>{{ item.companyName }}</span><br><br><br>
                        <span>{{ item.industry }}/{{ item.startUpYear }}</span>
                    </div>
                    <!-- <div style="padding: 14px;padding-bottom: 0; background-color:grey; height:50px;">
                        <span style="float:left; font-size:30px; margin-right:280px; ">{{ item.title }}</span>
                        <span>{{ item.location || 'empty' }}</span>
                        <span style="float:right;font-size:30px;margin-right:14px;">{{ item.companyName }}</span>
                    </div>
                     <div style="padding-left: 14px;padding-top: 14px;background-color:green; height:30px;">
                        <span style="float:left; margin-right:280px; "> Salary: {{ item.minSalary }}-{{ item.maxSalary }}</span>
                        <span > JobType: {{ item.jobType }}</span>
                        <span style="float:right;margin-right:14px;">{{ item.industry }}/{{ item.startUpYear }}</span>
                    </div>
                    <div style="padding-left: 14px;padding-bottom: 14px;padding-top: 14px;">
                        <span > EducationalBackground: {{ item.educationalBackground }}</span>
                    </div> -->
                </el-card>
            </el-col>
        </el-row>
    </div>
    
</template>

<style>
#card{
    margin-left: 260px;
}
</style>

<script>
  export default {
    
    data() {
      return {
        array:null,
      }
    },
    created() {
      this.getAllPost();
    },
    methods: {
      handleEdit(index, row) {
        console.log(index, row);
      },
      handleDelete(index, row) {
        console.log(index, row);
      },
       getAllPost(){
        this.$axios.get('/api/post/all').then(res => {
          this.array = res.data.data
        });
      }
    }
  }
</script>