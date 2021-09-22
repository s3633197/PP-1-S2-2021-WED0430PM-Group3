<template>
    <div >
      <br>
      <h1>All Posts</h1><br>
      <el-card class="cardpost" v-for="item in array" :key="item.postid">
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
                      <el-descriptions-item><template slot="label"><i class="el-icon-user"></i>CompanyName</template>{{ item.companyName }}
                      </el-descriptions-item>
                      <el-descriptions-item><template slot="label"><i class="el-icon-tickets"></i>Salary</template>{{ item.minSalary }}-{{ item.maxSalary }} / month
                      </el-descriptions-item>
                      <el-descriptions-item><template slot="label"><i class="el-icon-office-building"></i>JobType</template>{{ item.jobType }}
                      </el-descriptions-item>
                      <el-descriptions-item>
                        <template slot="label"><i class="el-icon-office-building"></i>Industry</template>{{ item.industry }}
                      </el-descriptions-item>
                      <el-descriptions-item >
                        <template slot="label"><i class="el-icon-office-building"></i>EducationalBackground</template>{{ item.educationalBackground }}
                      </el-descriptions-item>
                      <el-descriptions-item>
                       <template>
                          <el-button type="primary" class="submit" >
                            <router-link :to="{path:'/Post-Detail',query:{postId:item.postId}}" style="color: #fff;text-decoration: none;">
                              Details
                            </router-link>
                          </el-button>
                       </template>
                      </el-descriptions-item>
                    </el-descriptions>
        <!-- <table>
          <tr>
            <td style="color:#42b983;"><b>Title: </b>{{ item.title }}</td>
            <td style="color:#42b983;"><b>Address: </b>{{ item.location || 'empty' }}</td>
            <td style="color:#42b983;"><b>Company Name: </b>{{ item.companyName }}</td>
          </tr>
          <tr>
            <td style="color:rgba(201, 70, 70, 0.856)"><b>Salary: </b>{{ item.minSalary }}-{{ item.maxSalary }}/month</td>
            <td><b>JobType: </b>{{ item.jobType }}</td>
            <td><b>Industry: </b>{{ item.industry }} / Start Up Year: {{ item.startUpYear }}</td>
          </tr>
          <tr>
             <td><b>Educational Background: </b>{{ item.educationalBackground }}</td>
             <td></td>
             <td>
               <el-button type="primary"  class="submit" >
                  <router-link :to="{path:'/Post-Detail',query:{postId:item.postId}}" style="color: #fff;text-decoration: none;">
                    Details
                  </router-link>
                </el-button>
             </td>
          </tr>
         </table> -->
      </el-card>
        <br>
    </div>
    
</template>


<script >
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
        this.$axios.get('/api/post/all').then(res => {
          this.array = res.data.data
        });
      },
    }
  }
</script>

<style  scoped>

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
  width:65%;
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