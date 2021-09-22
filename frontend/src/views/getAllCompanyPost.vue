<template>
    <div>
      <br>
      <h1>My Posts</h1><br>
      <el-card class="cardpost" v-for="item in array" :key="item.title">
        <el-descriptions class="margin-top" :column="size"  border>
                      <el-descriptions-item>
                        <template slot="label"><i class=""></i>Title</template>{{ item.title }}
                      </el-descriptions-item>
                      <el-descriptions-item>
                        <template slot="label"><i class="el-icon-office-building"></i>Location</template>{{ item.location || 'empty' }}
                      </el-descriptions-item>
                      <el-descriptions-item>
                         <el-avatar :src="require('../assets/a.png')" :size="80"></el-avatar>
                      </el-descriptions-item>
                      <el-descriptions-item>
                        <template slot="label"><i class="el-icon-office-building"></i>Industry</template>{{ item.industry }}
                      </el-descriptions-item>
                      <el-descriptions-item><template slot="label"><i class="el-icon-tickets"></i>Salary</template>{{ item.minSalary }}-{{ item.maxSalary }} / month
                      </el-descriptions-item>
                      <el-descriptions-item><template slot="label"><i class="el-icon-office-building"></i>JobType</template>{{ item.employmentType }}
                      </el-descriptions-item>
                      <el-descriptions-item >
                        <template slot="label"><i class="el-icon-office-building"></i>EducationalBackground</template>{{ item.educationalBackground }}
                      </el-descriptions-item>
                       <template slot="extra">
                          <el-button  class="submit">
                            <router-link :to="{path:'/UpdatePost',query:{item:item}}" style="color: #fff;text-decoration: none;">
                              Update
                            </router-link>
                          </el-button>
                          <el-button type="primary" >
                            <router-link :to="{path:'/Post-Detail-Company',query:{postId:item.postId}}" style="color: #fff;text-decoration: none;">
                              Details
                            </router-link>
                          </el-button>
                          <el-button type="danger" @click="handleDelete(item.postId)">
                              Delete
                          </el-button>
                       </template>

                    </el-descriptions>
        <!-- <table>
          <tr>
            <td ><b>Title: </b>{{ item.title }}</td>
            <td><b>Address: </b>{{ item.location || 'empty' }}</td>
            <td><b>Industry: </b>{{ item.industry }}</td>
          </tr>
          <tr>
            <td><b>Salary: </b>{{ item.minSalary }}-{{ item.maxSalary }}/month</td>
            <td><b>JobType: </b>{{ item.employmentType }}</td> 
          </tr>
          <tr>
             <td><b>Educational Background: </b>{{ item.educationalBackground }}</td>
             <td></td>
             <td>
               <el-button  class="submit">
                  <router-link :to="{path:'/UpdatePost',query:{item:item}}" style="color: #fff;text-decoration: none;">
                    Update
                  </router-link>
                </el-button>
                <el-button type="primary" >
                  <router-link :to="{path:'/Post-Detail-Company',query:{postId:item.postId}}" style="color: #fff;text-decoration: none;">
                    Details
                  </router-link>
                </el-button>
                <el-button type="danger" @click="handleDelete(item.postId)">Delete</el-button>
             </td>
          </tr>
         </table> -->
      </el-card>
      <br>
    </div>
    
</template>

<style scoped>
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
  width:70%;
  margin-left:17%;
  margin-bottom: 2%;
  border-radius: 15px !important;
  background-color: rgb(240, 245, 250)!important;
}
td{
  text-align: left;
  width: 20%;
  padding: 1.5%;
}
b{
  font-size: 18px;
}
@media only screen and (max-width: 500px) {
  .cardseeker{
    width:80%;
    margin-left:10%;
  }
}
</style>

<script>
  export default {
    
    data() {
      return {
        size:3,
        array:null,
      }
    },
    created() {
       window.addEventListener('resize', this.onResize)
      this.getAllPost();
    },
    methods: {
      onResize() {
        if (window.innerWidth <= 900) {
          this.size = 1
        }else {
          this.size = 3
        }
      },
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
      handleDelete(postId){
        this.$axios.delete('/api/post/delete/'+postId).then(res => {
          this.$router.push("/get-All-Company-Post")
          this.$router.go(0)
        });
      }
    }
  }
</script>