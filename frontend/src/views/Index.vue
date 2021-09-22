<template>
    <div>
        <img src="../assets/img.jpg">
        <img src="../assets/img.jpg">
        <div v-if="company">
          <hr>
          <h1>All JobSeekers</h1> 
            <el-card class="cardseeker" v-for="item in array" :key="item.seekerId">
              <el-descriptions class="margin-top" :column="size"  border>
                      <el-descriptions-item>
                        <template slot="label"><i class="el-icon-user"></i>Name</template>{{ item.firstName }} {{ item.lastName }}
                      </el-descriptions-item>
                      <el-descriptions-item>
                        <template slot="label"><i class="el-icon-user"></i>Gender</template>{{ item.gender }}
                      </el-descriptions-item>
                      <el-descriptions-item>
                        <template slot="label">Seeker Avatar</template>
                         <el-avatar :src="require('../assets/a.png')" :size="80"></el-avatar>
                      </el-descriptions-item>
                      <el-descriptions-item>
                        <template slot="label"><i class="el-icon-user"></i>Birthday</template>{{ item.dateOfBirth }}
                      </el-descriptions-item>
                      <el-descriptions-item>
                        <template slot="label"><i class="el-icon-office-building"></i>EducationalBackground</template>{{ item.educationalBackground }}
                      </el-descriptions-item>
                      <el-descriptions-item>
                        <template slot="label"><i class=""></i>Expected Salary</template>{{ item.expectedSalary }} / month
                      </el-descriptions-item>
                      <el-descriptions-item>
                        <template slot="label"><i class="el-icon-tickets"></i>Skill</template>{{ item.skill }}
                      </el-descriptions-item>
                      <el-descriptions-item>
                        <template slot="label" ><i class="el-icon-office-building"></i>Phone</template>{{ item.phone }}
                      </el-descriptions-item>
                      <el-descriptions-item>
                        <template >
                          <el-button type="primary" class="submit" >
                            <router-link :to="{path:'/resume',query:{seekerId:item.seekerId}}" style="color: #fff;text-decoration: none;">
                              Details
                            </router-link>
                          </el-button>
                       </template>
                      </el-descriptions-item>
                    </el-descriptions>
            </el-card>
        </div>
        <div v-if="personal">
          <hr>
          <h1>Suggest Posts</h1>
                <el-card v-for="item in recommendPosts" :key="item.title" class="cardpost">
                    <el-descriptions class="margin-top" :column="size"  border>
                      <el-descriptions-item>
                        <template slot="label"><i class=""></i>Title</template>{{ item.title }}
                      </el-descriptions-item>
                      <el-descriptions-item>
                        <template slot="label"><i class="el-icon-office-building"></i>Location</template>{{ item.address || 'empty' }}
                      </el-descriptions-item>
                      <el-descriptions-item>
                        <template slot="label">Company Avatar</template>
                         <el-avatar :src="require('../assets/a.png')" :size="80"></el-avatar>
                      </el-descriptions-item>
                      <el-descriptions-item><template slot="label"><i class="el-icon-user"></i>CompanyName</template>{{ item.companyName }}
                      </el-descriptions-item>
                      <el-descriptions-item><template slot="label"><i class="el-icon-tickets"></i>Salary</template>{{ item.minSalary }}-{{ item.maxSalary }} / month
                      </el-descriptions-item>
                      <el-descriptions-item><template slot="label"><i class="el-icon-office-building"></i>Position</template>{{ item.position }}
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
                </el-card>
        </div>
        <hr>
        <h1>Slogan</h1>
        <img src="../assets/img.jpg">
        <img src="../assets/img.jpg">
    </div>
</template>

<style scoped>
b{
  font-size: 18px;
}
img{
    height: 25%;
    width: 30%;
    margin: 4%;
}

hr{
  border: 1px solid black;
}
.cardseeker{
  width:65%;
  margin-left:17%;
  margin-bottom: 2%;
  border-radius: 15px !important;
}
.cardpost{
  width:65%;
  margin-left:17%;
  margin-bottom: 2%;
  border-radius: 15px !important; 
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
@media only screen and (max-width: 500px) {
  img{
    width: 92%;
    display: block;
  }
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
        recommendPosts:null,
        // recommendSeekers:null,
        array: null,
        personal:false,
        company:false,
        login:false,
      }
    },
    
    created() {
      window.addEventListener('resize', this.onResize)
      this.personalOrCompany();
      this.loginOrnot();
      if(this.login){
        if(this.personal==true){
          this.getRecommendPost();
        }else{
          // this.getRecommendJobSeeker()
           this.getAllPost();
        }
      }
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
      getRecommendPost(){
        this.$axios.get('/api/seeker/recommend/all').then(res => {
          console.log(res.data.data)
          this.recommendPosts = res.data.data
        });
      },
      getAllPost(){
        this.$axios.get('/api/seeker/all').then(res => {
          this.array = res.data.data
        });
      },
      personalOrCompany(){
        if(localStorage.getItem('roleId')==1){
          this.personal = true
          this.company = false
        }
        if(localStorage.getItem('roleId')==2){
          this.company = true
          this.personal = false
        }
      },
      loginOrnot(){
        if(localStorage.getItem('token')==null){
          this.login = false
        }
        if(localStorage.getItem('token')!=null){
          this.login = true
        }
      },
    }
  }


</script>