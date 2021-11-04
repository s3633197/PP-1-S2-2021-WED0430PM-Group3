<template>
<!-- html -->
    <div>
      <br>
      <h1>My Posts</h1><br>
       <el-card class="cardpost" v-for="item in array" :key="item.postid">
         <table>
              <tr>
                <td class="importantInfo" colspan="3">{{ item.title }} [{{ item.location || 'location' }}]</td>
                <td class="importantInfo" colspan="2">{{ item.companyName || 'companyName'}}</td>
                <td rowspan="3" colspan="1" style="text-align:center;"><el-avatar shape="square" :src="require('../assets/a.png')" class="avatar"></el-avatar></td>
              </tr>
              <tr>
                <td colspan="3"  style="color:rgba(201, 70, 70, 0.856)">{{ item.minSalary }}-{{ item.maxSalary }}/hour</td>
                <td colspan="2" >{{ item.industry || 'industry'}} {{ item.startUpYear || 'startUpYear'}}</td>
              </tr>
              <tr>
                <td colspan="2" >{{ item.jobType || 'jobType'}} | {{ item.educationalBackground || 'educationalBackground'}}</td>
                
              </tr>
              <tr>
                <td colspan="6" style="text-align:right;">
                  <el-button  class="submit">
                      <router-link :to="{path:'/UpdatePost',query:{item:item}}" style="color: #fff;text-decoration: none;">
                        Update
                      </router-link>
                    </el-button>
                  <el-button type="primary"  @click.native="postDetail(item.postId)">
                      Details
                  </el-button>
                  <el-button type="danger" @click="handleDelete(item.postId)">Delete</el-button>
                </td>
              </tr>
            </table>
      </el-card>
      <br>
    </div>
    
</template>

<style scoped>
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
.card:hover{
  background-color: #ffffff !important;
  box-shadow: 0 10px 10px rgb(63, 62, 62) !important;
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
}
.cardpost table{
  margin-left:5%;
  width: 90%;
}
.cardpost td{
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
    font-size: 15px;
  }
  .cardpost table{
    margin-left:0%;
    width: 100%;
  }
  .cardpost td{
    width: 60%;
  }
  button{
    font-size: 10x;
    padding:5px;
  }
}
</style>

<script>
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
      }
    },
    props:{
        array:{
          type:Array,
          default:""
        }
    },
    methods: {
      //see detail of the post with this id
      postDetail(postId){
        this.$router.push({name: 'PostDetailCompany', params: { postId: postId }})
      },
      //contact to backend and get all post info
      getAllPost(){
        this.$axios.get('/api/post/getAll').then(res => {
          this.array = res.data.data
        });
      },
      //contact to backend to delete this post with this id
      handleDelete(postId){
        this.$axios.delete('/api/post/delete/'+postId).then(res => {
          this.$router.push("/get-All-Company-Post")
          this.$router.go(0)
        });
      },
    }
  }
</script>