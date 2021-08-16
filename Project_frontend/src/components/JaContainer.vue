<template>
  <ja-container-item
    v-for="child in info.children"
    :key="child"
    :info="child"
  />
</template>

<script>
import JaContainerItem from "./JaContainerItem.vue";

export default {
  name: "JaContainer",
  components: {
    JaContainerItem,
  },
  props: {
    info: Object,
  },
  data() {
    return {};
  },
  methde: {},
  mounted() {},
};
</script>

<style scoped>
</style>