<template>
  <el-aside :style="height">
    <el-scrollbar>
      <el-menu :default-openeds="info.openeds">
        <ja-menu-element
          v-for="child in info.children"
          :key="child"
          :info="child"
        />
      </el-menu>
    </el-scrollbar>
  </el-aside>
</template>

<script>
import JaMenuElement from "./JaMenuElement.vue";

export default {
  name: "JaAside",
  components: {
    JaMenuElement,
  },
  props: {
    info: Object,
  },
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