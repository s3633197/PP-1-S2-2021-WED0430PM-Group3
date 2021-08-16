<template>
  <el-container
    :direction="info.direction"
    :class="info.class"
    :style="info.style"
    v-if="info.type == 'container'"
  >
    <ja-container-item
      v-for="child in info.children"
      :key="child"
      :info="child"
    />
  </el-container>
  <ja-header
    v-else-if="info.type == 'header'"
    :info="info.children[0]"
  ></ja-header>
  <ja-aside
    v-else-if="info.type == 'aside'"
    :info="info.children[0]"
  ></ja-aside>
  <ja-main v-else-if="info.type == 'main'" :info="info.children[0]"></ja-main>
  <ja-footer
    v-else-if="info.type == 'footer'"
    :info="info.children[0]"
  ></ja-footer>
</template>

<script>
import JaAside from "./JaAside.vue";
import JaFooter from "./JaFooter.vue";
import JaHeader from "./JaHeader.vue";
import JaMain from "./JaMain.vue";
export default {
  components: { JaAside, JaMain, JaHeader, JaFooter },
  name: "JaContainerItem",
  props: {
    info: Object,
  },
};
</script>