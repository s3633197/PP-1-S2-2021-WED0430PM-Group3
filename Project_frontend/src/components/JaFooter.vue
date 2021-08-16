<template>
  <el-footer>footer</el-footer>
</template>

<script>
export default {
  name: "JaFooter",
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