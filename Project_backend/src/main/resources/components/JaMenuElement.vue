<template>
  <el-submenu v-if="info.type == 'submenu'" :index="info.indextag">
    <template #title v-if="info.title && info.src">
      <span v-html="info.span"></span
      ><img :class="info.class" :src="info.src" alt="" /><span
        :class="info.textClass"
        v-html="info.title"
      ></span>
    </template>
    <template #tite v-else-if="info.title">
      <span v-html="info.span"></span
      ><span :class="info.textClass" v-html="info.title"></span>
    </template>
    <ja-menu-element
      v-for="child in info.children"
      :key="child"
      :info="child"
    />
  </el-submenu>
  <el-menu-item-group v-else-if="info.type == 'group'" :index="info.indextag">
    <template #title v-if="info.title && info.src">
      <span v-html="info.span"></span
      ><img :class="info.class" :src="info.src" alt="" /><span
        :class="info.textClass"
        v-html="info.title"
      ></span>
    </template>
    <template #tite v-else-if="info.title">
      <span v-html="info.span"></span
      ><span :class="info.textClass" v-html="info.title"></span>
    </template>
    <ja-menu-element
      v-for="child in info.children"
      :key="child"
      :info="child"
    />
  </el-menu-item-group>
  <el-menu-item v-else-if="info.type == 'item'" :index="info.indextag">
    <div v-if="info.src && info.title">
      <span v-html="info.span"></span
      ><img :class="info.class" :src="info.src" alt="" /><span
        :class="info.textClass"
        ><router-link :to="info.target" v-html="info.title"></router-link
      ></span>
    </div>
    <div v-else-if="info.title">
      <span v-html="info.span"></span
      ><span :class="info.textClass"
        ><router-link :to="info.target" v-html="info.title"></router-link
      ></span>
    </div>
  </el-menu-item>
</template>

<script>
export default {
  name: "JaMenuElement",
  props: {
    info: {
      type: Object,
      defaul: () => {
        return {};
      },
    },
  },
  methods: {},
  data() {
    return {};
  },
};
</script>

<style scoped>
</style>