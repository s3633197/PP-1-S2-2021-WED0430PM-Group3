<template>
    <div>
      <br> <br>
      <h1 style="margin:0;">Easy to find a job, there's no better way.</h1>
        <img src="../assets/img.jpg">
        <img src="../assets/img.jpg">
        <div v-if="company">
          <h1>All JobSeekers</h1> 
            <el-card class="cardseeker" v-for="item in array" :key="item.seekerId">
              <table>
                <tr>
                  <td style="color:#42b983;"><b>Name: </b>{{ item.firstName }} {{ item.lastName }}</td>
                  <td><b>EducationalBackground:  </b>{{ item.educationalBackground }}</td>
                  <td rowspan="2"><el-avatar :src="require('../assets/a.png')" :size="80"></el-avatar></td>
                </tr>
                <tr>
                  <td style="color:rgba(201, 70, 70, 0.856)"><b>Expected Salary: </b>{{ item.expectedSalary }} / hour</td>
                  <td><b>Skill: </b>{{ item.skill }}</td>
                </tr>
                <tr>
                   <td><b>Phone: </b>{{ item.phone }}</td>
                   <td></td>
                      <td>
                        <el-button type="primary"  class="submit" >
                            <router-link :to="{path:'/resume',query:{seekerId:item.seekerId}}" style="color: #fff;text-decoration: none;">
                              Details
                            </router-link>
                          </el-button>
                      </td>
                </tr>
              </table>
            </el-card>
             <br>
        </div>
        <div v-if="personal">
          <h1>Suggest Posts</h1>
                <el-card v-for="item in recommendPosts" :key="item.title" class="cardpost">
                  <el-table>
                    <tr>
                      <td style="color:#42b983;"><b>Title: </b>{{ item.title }}</td>
                      <td style="color:#42b983;"><b>Location: </b>{{ item.address || 'empty' }}</td>
                      <td style="color:#42b983;"><b>CompanyName: </b>{{ item.companyName }}</td>
                    </tr>
                    <tr>
                      <td style="color:rgba(201, 70, 70, 0.856)"><b>Salary: </b>{{ item.minSalary }}-{{ item.maxSalary }} / month</td>
                      <td><b>JobType: </b>{{ item.position }}</td>
                      <td><b>Industry: </b>{{ item.industry }} / Start Up Date : {{ item.startUpDate }}</td>
                    </tr>
                    <tr>
                      <td><b>EducationalBackground: </b>{{ item.educationalBackground }}</td>
                      <td></td>
                      <td>
                        <el-button type="primary"  class="submit" >
                            <router-link :to="{path:'/Post-Detail',query:{postId:item.postId}}" style="color: #fff;text-decoration: none;">
                              Details
                            </router-link>
                          </el-button>
                      </td>
                    </tr>
                  </el-table>
                </el-card>
                <br>
        </div>
        <div v-if="defaultt">
      <br>
      <h1>All Posts</h1><br>
      <el-card class="cardpost" v-for="item in array" :key="item.postid">
        <table>
          <tr>
            <td style="color:#42b983;"><b>Title: </b>{{ item.title }}</td>
            <td style="color:#42b983;"><b>Address: </b>{{ item.location || 'empty' }}</td>
            <td style="color:#42b983;"><b>Company Name: </b>{{ item.companyName }}</td>
          </tr>
          <tr>
            <td style="color:rgba(201, 70, 70, 0.856)"><b>Salary: </b>{{ item.minSalary }}-{{ item.maxSalary }}/hour</td>
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
         </table>
      </el-card>
        <br>
    </div>
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
  background-color: rgb(240, 245, 250)!important;
}
.cardseeker td{
  text-align: left;
  width: 44.5%;
  padding-left: 8%;
}
.cardpost{
  width:65%;
  margin-left:17%;
  margin-bottom: 2%;
  border-radius: 15px !important;
  background-color: rgb(240, 245, 250)!important;
}
.cardpost td{
  text-align: left;
  width: 20%;
  padding: 1.5%;
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
        recommendPosts:null,
        // recommendSeekers:null,
        array: null,
        personal:false,
        company:false,
        defaultt:true,
        login:false,
      }
    },
    
    created() {
      this.personalOrCompany();
      this.loginOrnot();
      if(this.login){
        if(this.personal==true){
          this.getRecommendPost();
        }else if(this.company==true){
           this.getAllSeekers();
        }
      }else{
          this.getAllPost();
      }
    },
    methods: {
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
        this.$axios.get('/api/post/all').then(res => {
          this.array = res.data.data
        });
      },
      getAllSeekers(){
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
          this.defaultt = true
        }
        if(localStorage.getItem('token')!=null){
          this.login = true
          this.defaultt = false
        }
      },
    }
  }
</script>