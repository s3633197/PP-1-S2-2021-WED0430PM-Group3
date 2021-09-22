<template>
  <div>
    <br>
    <h1>Your profile</h1>
    <el-card class="cardseeker">
              <el-descriptions class="margin-top" :column="size"  border>
                      <el-descriptions-item>
                        <template slot="label"><i class="el-icon-user"></i>Company Name</template>{{ informationForm.companyName }}
                      </el-descriptions-item>
                      <el-descriptions-item>
                        <template slot="label"><i class="el-icon-user"></i>Start Up Date</template>{{ informationForm.startUpDate }}
                      </el-descriptions-item>
                      <el-descriptions-item>
                         <el-avatar :src="require('../assets/a.png')" :size="80"></el-avatar>
                      </el-descriptions-item>
                      <el-descriptions-item>
                        <template slot="label" ><i class="el-icon-office-building"></i>Owner</template>{{ informationForm.owner }}
                      </el-descriptions-item>
                      <el-descriptions-item>
                        <template slot="label"><i class="el-icon-user"></i>Industry</template>{{ informationForm.industry }}
                      </el-descriptions-item>
                      
                      <el-descriptions-item>
                        <template >
                          <el-button type="primary" class="submit">
                            <router-link style="color:white;text-decoration: none;" :to="{path:'/updateCompanyInfo',query:{informationForm:this.informationForm}}">
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
        <th>Company Name:</th>
        <th>{{ informationForm.companyName }}</th>
      </tr>
      <tr>
        <th>Start Up Date:</th>
        <td>{{ informationForm.startUpDate }}</td>
      </tr>
      <tr>
        <th>Owner:</th>
        <td>{{ informationForm.owner }}</td>
      </tr>
      <tr>
        <th>Industry: </th>
        <td>{{ informationForm.industry }}</td>
      </tr>        
    </table>
    <br><br>
     <el-button type="primary" class="submit">
        <router-link style="color:white;" :to="{path:'/updateCompanyInfo',query:{informationForm:this.informationForm}}">
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
          informationForm: {
              companyId: '',
              companyName: '',
              startUpDate: '',
              owner: '',
              industry: '',
              accountId: '',
            },
        };
    },
      created() {
        this.getCompanyInfo();
      },
    methods: {
      getCompanyInfo(){
        this.$axios.get('/api/company/info').then(res => {
          this.informationForm.companyId = res.data.data.companyId
          this.informationForm.companyName = res.data.data.companyName
          this.informationForm.startUpDate = res.data.data.startUpDate
          this.informationForm.owner = res.data.data.owner
          this.informationForm.industry = res.data.data.industry
          this.informationForm.accountId = res.data.data.accountId
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