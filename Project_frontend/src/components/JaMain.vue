<template>
  <!-- <el-main><el-button @click="addbutton" v-for="btn in info.btn" :key="btn">{{btn}}</el-button></el-main> -->
  <el-main style="height: 540px" :style="height">
    <el-scrollbar>
      <ja-main-element
        v-for="child in info.children"
        :key="child"
        :info="child"
      />
    </el-scrollbar>
  </el-main>
</template>

<script>
import JaMainElement from "./JaMainElement.vue";
export default {
  components: { JaMainElement },
  name: "JaMain",
  props: {
    info: Object,
  },
  methods: {
    // addbutton:function(){
    //     this.$store.dispatch("changeinfo",this.info.f);
    // }
  },
  // beforeCreate(){
  //   console.log(this.info);
  // },
  computed:{
    height(){
      let h = (document.documentElement.clientHeight || document.body.clientHeight)-60;
      return `height:${h}px`;
    }
  }
};
</script>

<style scoped>
</style>