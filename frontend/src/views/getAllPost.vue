<template>
<!-- html -->
    <div class="div">
      <br>
      <h1>All Posts</h1><br>
      <el-card class="cardpost" v-for="item in array" :key="item.postid" @click.native="postCardClick(item.postId)">
        <table >
          <tr>
            <td class="importantInfo" colspan="2">{{ item.title }} [{{ item.location || 'empty' }}]</td>
            <td class="importantInfo" colspan="3">{{ item.companyName }}</td>
            <td rowspan="3" colspan="1" style="text-align:center;"><el-avatar shape="square" :src="require('../assets/a.png')" class="avatar"></el-avatar></td>
          </tr>
          <tr>
            <td colspan="2"  style="color:rgba(201, 70, 70, 0.856)">{{ item.minSalary }}-{{ item.maxSalary }}/hour</td>
            <td></td><td></td><td></td>
          </tr>
          <tr>
            <td colspan="2" >{{ item.jobType }} | {{ item.educationalBackground }}</td>
            <td colspan="3" >{{ item.industry }} {{ item.startUpYear }}</td>
          </tr>
         </table>
      </el-card>
        <br>
    </div>
    
</template>


<script >
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
      // click the card to display the detail of this post
      postCardClick(postId){
        this.$router.push({name: 'PostDetail', params: { postId: postId }})
      },
      // get all post from backend
      getAllPost(){
        this.$axios.get('/api/post/all').then(res => {
          this.array = res.data.data
        });
      },
    }
  }
</script>

<style  scoped>
/*  css */
  .importantInfo{
    font-size: 25px;
    color:#42b983;
  }
  .submit{
    background-color: #54c685 !important;
  }
  .submit:hover{
    background-color: #3ea56a !important;
  }
  .submit:focus{
    background-color: #54c685 !important;
  }
  .cardpost{
    width:60%;
    margin-left:20%;
    margin-bottom: 2%;
    border-radius: 15px !important;
    background-color: rgb(255, 255, 255)!important;
    font-size: 15px;
  }
  .cardpost:hover{
      box-shadow: 5px 10px 5px #888888;
      cursor:pointer
  }
  table{
    margin-left:5%;
    width: 90%;
  }
  td{
    text-align: left;
    width: 78px;
  }
  .avatar{
      width: 80px;
      height: 80px;
  }
  /*css only for small size screen like mobile phone*/
  @media only screen and (max-width: 1150px) {
    .importantInfo{
      font-size: 15px !important;
      color:#42b983;
    }
    .avatar{
      width: 30px;
      height: 30px;
    }
    .cardpost{
      width:100%;
      margin-left:0%;
      border-radius: 0 !important;
    }
    table{
      margin-left:0%;
      width: 100%;
    }
    td{
      width: 70%;
      padding: 0%;
    }
    .submit{
      background-color: #54c685 !important;
    }
  }
</style>