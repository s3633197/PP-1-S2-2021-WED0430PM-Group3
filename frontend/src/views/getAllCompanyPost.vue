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
                        <span style="margin:10px; ">{{ item.address || 'empty' }}</span><br><br><br>
                        <span > JobType: {{ item.jobType }}</span>
                        
                        
                    </div>
                    <div style="float:right;margin:14px;width:298px;">
                        <span>{{ item.companyName }}</span><br><br><br>
                        <span>{{ item.industry }}/{{ item.startUpYear }}</span><br><br>
                        <el-button size="mini" @click="handleEdit()">Edit</el-button>
                        <el-button size="mini" type="danger" @click="handleDelete(item.postId)">Delete</el-button>
                    </div>
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
        this.$axios.get('/api/post/getAll').then(res => {
          this.array = res.data.data
        });
      },
      handleEdit(){
        this.$axios.get('/api/post/getAll').then(res => {
          this.array = res.data.data
        });
      },
      handleDelete(postId){
        this.$axios.get('/api/post/delete/{postId}').then(res => {
          this.array = res.data.data
        });
      }
    }
  }
</script>