<template>
<!-- html -->
  <div>
    <br>
      <h1>Your Profile</h1>
      <el-card class="cardseeker">
            <table class="table">
              <tr><th colspan="4">Personal Information</th></tr>
              <tr>
                <td colspan="2">Name: {{ informationForm.firstName }} {{ informationForm.lastName }}</td>
                <td>Gender: {{ informationForm.gender }}</td>
                <td rowspan="2"><el-avatar :src="require('../assets/a.png')" class="avatar" ></el-avatar></td>
              </tr>
              <tr>
                <td colspan="2">DOB: {{ informationForm.dateOfBirth }}</td>
                <td colspan="1">Phone: {{ informationForm.phone }}</td>
              </tr>
              
              <tr><th colspan="4">EducationalBackground</th></tr>
              <tr>
                <td colspan="2">SchoolName: {{ informationForm.schoolName }}</td>
                <td colspan="2">Major: {{ informationForm.major }}</td>
              </tr>     
              <tr>
                <td colspan="4">Education: {{ informationForm.educationalBackground }}</td>
              </tr>
              <tr><th colspan="4">Others</th></tr>
              <tr>
                <td colspan="2">Skill: {{ informationForm.skill }}</td>
                <td colspan="2">Wanted Industry: {{ informationForm.wantedIndustry }}</td>
              </tr> 
              <tr>
                <td colspan="2">Job Type: {{ informationForm.jobType }}</td>
                <td colspan="2">Expected Salary: {{ informationForm.expectedSalary }}</td>
              </tr>
              <tr>
                <td colspan="4">Location: {{ informationForm.location }}</td>
              </tr>
              <tr>
                <td colspan="3">Statement:{{ informationForm.statement }}</td>
                <td>
                  <el-button type="primary" size="mini" class="submit">
                      <router-link style="color:white;text-decoration: none;" :to="{path:'/updateResume',query:{informationForm:this.informationForm}}">
                        Update
                      </router-link>
                  </el-button>
                </td>
              </tr>
            </table>
      </el-card>
      <br>
  </div>
</template>


<script>
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
        this.getResume();
      },
    methods: {
      //get the resume info from backend
      getResume(){
        this.$axios.get('/api/seeker/resume').then(res => {
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
  margin-left:15%;
  margin-bottom: 2%;
  border-radius: 15px !important;
  padding: 30px;
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
table{
  text-align: left;
  width: 100%;
  border-collapse: collapse;
}
th{
  font-size: 25px;
  background-color: #3ea56a;
  color: white;
  padding-left: 30px;
}
td{
  padding: 10px;
}
.avatar{
    width: 80px;
    height: 80px;
}
/*css only for small size screen like mobile phone*/
@media only screen and (max-width: 500px) {
.cardseeker{
  width: 90%;
  margin-left:5%;
  margin-bottom: 2%;
  border-radius: 15px !important;
  padding: 0px;
}
th{
  font-size: 15px;
  background-color: #3ea56a;
  color: white;
  padding-left: 30px;
}
td{
  padding: 10px;
  border:1px solid black;
  font-size: 10px;
}
.avatar{
    width: 50px;
    height: 50px;
}
}
</style>