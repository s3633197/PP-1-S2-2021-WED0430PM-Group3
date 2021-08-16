<template>
  <ja-table v-if="info.type == 'table'" :info="info.children[0]" />
  <ja-form v-else-if="info.type == 'form'" :info="info.children[0]" />
  <ja-excel :style="jaexcelstyle"  v-else-if="info.type == 'jaexcel'" :info="info.children[0]" />
  <ja-card v-else-if="info.type == 'card'" :info="info.children[0]" />
  <div v-else>default</div>
</template>

<script>
import JaForm from "./JaForm.vue";
import JaTable from "./JaTable.vue";
import JaExcel from './JaExcel.vue';
import JaCard from "./JaCard.vue";

export default {
  name: "JaMainElement",
  components: { JaTable, JaForm,JaExcel, JaCard },
  props: {
    info: Object,
  },
  methods: {},
  data() {
    return {};
  },
  computed: {
    jaexcelstyle(){
      let h = (document.documentElement.clientHeight || document.body.clientHeight)-60;
      return `text-align:center; margin-top:${h/3}px;`;
    }
  }
};
</script>
