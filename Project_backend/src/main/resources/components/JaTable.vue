<template>
  <el-scrollbar>
    <el-table
      :data="tableData.results"
      :style="info.style"
      :stripe="info.strip"
      :border="info.border"
      :row-class-name="info.tableRowClassName"
    >
      <ja-table-column
        v-for="child in info.children"
        :key="child"
        :info="child"
      />
    </el-table>
  </el-scrollbar>
  <br /><br />
  <el-pagination
    v-if="info.pagination"
    @size-change="handleSizeChange"
    @current-change="handleCurrentChange"
    background
    :current-page="tableData.page"
    :page-sizes="[10, 20, 30, 40]"
    :page-size="tableData.page_size"
    layout="total, sizes, prev, pager, next, jumper"
    :total="tableData.total"
    style="float: right; right: 85px; position: relative"
  >
  </el-pagination>
</template>

<script>
import JaTableColumn from "./JaTableColumn.vue";
export default {
  components: { JaTableColumn },
  name: "JaTable",
  props: {
    info: Object,
  },
  computed: {
    tableData() {
      //console.log(this.$store.state.tableData[this.info.tableName]);
      return this.$store.state.tableData[this.info.tableName];
    },
  },
  beforeCreate: function () {
    this.$store.dispatch("setTableDefault", this.info.tableName);
  },
  mounted: function () {
    if (this.info.api) {
      this.$store.dispatch("setTableData", {
        name: this.info.api,
        data: {},
        tableName: this.info.tableName,
      });
    }else if(this.info.origin == 'excel'){
      this.$store.dispatch("setTableData",{
        name: 'excel',
        tableName: this.info.tableName
      })
    }
  },
  methods:{
    handleSizeChange(val){
      console.log("hear"+val);
      if (this.info.api){
        let data = this.$store.state.form[this.info.formName] || {};
        data.page = 1;
        data.page_size = val;
        this.$store.dispatch("setTableData", {
          name: this.info.api,
          data: data,
          tableName: this.info.tableName
        })
      }
    },
    handleCurrentChange(val){
      console.log("hear"+val);
      if(this.info.api){
        let data = this.$store.state.form[this.info.tableName] || {};
        data.page = val;
        data.page_size = this.$store.state.tableData[this.info.tableName].page_size;
        this.$store.dispatch("setTableData", {
          name: this.info.api,
          data: data,
          tableName: this.info.tableName
        })
      }
    }
  }
};
</script>

<style scoped>
</style>