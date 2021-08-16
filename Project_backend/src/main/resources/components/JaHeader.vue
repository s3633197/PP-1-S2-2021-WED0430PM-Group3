<template>
  <el-header :class="info.class">
    <el-row
      v-for="child in info.children"
      :key="child"
      :class="child.class"
      :style="child.style"
    >
      <el-col
        v-for="col in child.children"
        :key="col"
        :span="col.span"
        :offset="col.offset"
        :class="col.class"
        :style="col.style"
      >
        <img :src="col.src" @click="handle(col)" v-if="col.type == 'img'" />
        <span @click="handle(col)" v-else-if="col.type == 'text'">{{
          col.value
        }}</span>
      </el-col>
    </el-row>
  </el-header>
</template>

<script>
export default {
  name: "JaHeader",
  components: {},
  props: {
    info: Object,
  },
  data() {
    return {};
  },
  methods: {
    handle: function (data) {
      this.$router.replace(data.target);
    },
  },
  mounted() {},
};
</script>

<style scoped>
</style>