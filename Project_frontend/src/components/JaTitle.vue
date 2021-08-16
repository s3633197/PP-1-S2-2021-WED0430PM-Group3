<template>
  <el-col :span="info.span" :offset="info.offset" v-if="info.type == 'title'">
    <span :class="info.class">{{ info.msg }}</span>
  </el-col>
</template>

<script>
export default {
  name: "JaTitle",
  props: {
    info: {
      type: Object,
      default: () => {
        return {};
      },
    },
  },
};
</script>

<style scoped>
</style>