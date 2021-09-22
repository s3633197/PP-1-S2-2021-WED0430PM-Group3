<template>
  <div>
    <br>
      <h1>Your Profile</h1>
      <el-card class="cardseeker">
              <el-descriptions class="margin-top" :column="size"  border>
                      <el-descriptions-item>
                        <template slot="label"><i class="el-icon-user"></i>Name</template>{{ informationForm.firstName }} {{ informationForm.lastName }}
                      </el-descriptions-item>
                      <el-descriptions-item>
                        <template slot="label"><i class="el-icon-user"></i>Gender</template>{{ informationForm.gender }}
                      </el-descriptions-item>
                      <el-descriptions-item>
                         <el-avatar :src="require('../assets/a.png')" :size="80"></el-avatar>
                      </el-descriptions-item>
                      <el-descriptions-item>
                        <template slot="label" ><i class="el-icon-office-building"></i>Phone</template>{{ informationForm.phone }}
                      </el-descriptions-item>
                      <el-descriptions-item>
                        <template slot="label"><i class="el-icon-user"></i>Birthday</template>{{ informationForm.dateOfBirth }}
                      </el-descriptions-item>
                      <el-descriptions-item>
                        <template slot="label"><i class="el-icon-user"></i>SchoolName</template>{{ informationForm.schoolName }}
                      </el-descriptions-item>
                      <el-descriptions-item>
                        <template slot="label"><i class="el-icon-user"></i>Major</template>{{ informationForm.major }}
                      </el-descriptions-item>
                      <el-descriptions-item>
                        <template slot="label"><i class="el-icon-office-building"></i>EducationalBackground</template>{{ informationForm.educationalBackground }}
                      </el-descriptions-item>
                      <el-descriptions-item>
                        <template slot="label"><i class="el-icon-tickets"></i>Skill</template>{{ informationForm.skill }}
                      </el-descriptions-item>
                      <el-descriptions-item>
                        <template slot="label" ><i class="el-icon-office-building"></i>Location</template>{{ informationForm.location }}
                      </el-descriptions-item>
                       <el-descriptions-item>
                        <template slot="label" ><i class="el-icon-office-building"></i>Statement</template>{{ informationForm.statement }}
                      </el-descriptions-item>
                      <el-descriptions-item>
                        <template slot="label" ><i class="el-icon-office-building"></i>Wanted Industry</template>{{ informationForm.wantedIndustry }}
                      </el-descriptions-item>
                      <el-descriptions-item>
                        <template slot="label"><i class=""></i>Job Type</template>{{ informationForm.jobType }}
                      </el-descriptions-item>
                      <el-descriptions-item>
                        <template slot="label"><i class=""></i>Expected Salary</template>{{ informationForm.expectedSalary }} / month
                      </el-descriptions-item>
                      <el-descriptions-item>
                        <template >
                          <el-button type="primary" class="submit">
                            <router-link style="color:white;text-decoration: none;" :to="{path:'/updateResume',query:{informationForm:this.informationForm}}">
                              Update
                            </router-link>
                          </el-button>
                       </template>
                      </el-descriptions-item>
                    </el-descriptions>
            </el-card>
            <br>
    <!-- <table class="table">
      <tr>
        <th>First Name:</th>
        <th>{{ informationForm.firstName }}</th>
      </tr>
      <tr>
        <th>Last Name:</th>
        <td>{{ informationForm.lastName }}</td>
      </tr>
      <tr>
        <th>Gender:</th>
        <td>{{ informationForm.gender }}</td>
      </tr>
      <tr>
        <th>Phone:</th>
        <td>{{ informationForm.phone }}</td>
      </tr>  
      <tr>
        <th>DateOfBirth:</th>
        <td>{{ informationForm.dateOfBirth }}</td>
      </tr> 
      <tr>
        <th>SchoolName:</th>
        <td>{{ informationForm.schoolName }}</td>
      </tr>     
      <tr>
        <th>Major:</th>
        <td>{{ informationForm.major }}</td>
      </tr> 
      <tr>
        <th>EducationalBackground:</th>
        <td>{{ informationForm.educationalBackground }}</td>
      </tr> 
      <tr>
        <th>Skill:</th>
        <td>{{ informationForm.skill }}</td>
      </tr> 
      <tr>
        <th>Location:</th>
        <td>{{ informationForm.location }}</td>
      </tr> 
      <tr>
        <th>Statement:</th>
        <td>{{ informationForm.statement }}</td>
      </tr>
      <tr>
        <th>Wanted Industry:</th>
        <td>{{ informationForm.wantedIndustry }}</td>
      </tr>
      <tr>
        <th>Job Type:</th>
        <td>{{ informationForm.jobType }}</td>
      </tr>
      <tr>
        <th>Expected Salary:</th>
        <td>{{ informationForm.expectedSalary }}</td>
      </tr>
    </table> -->
    <!-- <br><br>
     <el-button type="primary" class="submit">
        <router-link style="color:white;" :to="{path:'/updateResume',query:{informationForm:this.informationForm}}">
          Update
        </router-link>
      </el-button>
     <br><br><br><br> -->
  </div>
</template>


<script>
// @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue'
export default {
  data() {
        return {
          size:3,
          informationForm:{
              seekerId: null,
              firstName: '',
              lastName: '',
              gender: '',
              phone: null,
              dateOfBirth: '',
              schoolName: '',
              major: '',
              educationalBackground: '',
              skill: '',
              location: '',
              statement: '',
              wantedIndustry: '',
              jobType: '',
              expectedSalary: null,
              accountId: null,
          },
          
        };
    },
      created() {
        window.addEventListener('resize', this.onResize)
        this.getResume();
      },
    methods: {
      onResize() {
        if (window.innerWidth <= 900) {
          this.size = 1
        }else {
          this.size = 3
        }
      },
      getResume(){
        this.$axios.get('/api/seeker/resume').then(res => {
          // this.signupForm.token = res.data.data.token
          this.informationForm.firstName = res.data.data.firstName
          this.informationForm.lastName = res.data.data.lastName
          this.informationForm.gender = res.data.data.gender
          this.informationForm.phone = res.data.data.phone
          this.informationForm.dateOfBirth = res.data.data.dateOfBirth
          this.informationForm.schoolName = res.data.data.schoolName
          this.informationForm.major = res.data.data.major
          this.informationForm.educationalBackground = res.data.data.educationalBackground
          this.informationForm.skill = res.data.data.skill
          this.informationForm.location = res.data.data.location
          this.informationForm.statement = res.data.data.statement
          this.informationForm.wantedIndustry = res.data.data.wantedIndustry
          this.informationForm.jobType = res.data.data.jobType
          this.informationForm.expectedSalary = res.data.data.expectedSalary
        });
      }
    }
}




</script>

<style scoped>

.cardseeker{
  width:65%;
  margin-left:17%;
  margin-bottom: 2%;
  border-radius: 15px !important;
}

.submit{
  background-color: #54c685;
}
.submit:focus{
  background-color: #54c685;
}
.submit:hover{
  background-color: #3ea56a;
}

@media only screen and (max-width: 500px) {
  .cardseeker{
    width:80%;
    margin-left:10%;
  }
}
</style>