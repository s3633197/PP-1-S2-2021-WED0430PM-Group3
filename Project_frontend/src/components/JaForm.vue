<template>
  <el-form :label-width="info.labelWidth" :size="info.size">
    <el-row v-for="row in info.children" :key="row">
      <el-col
        v-for="col in row"
        :key="col"
        :span="col.span"
        :offset="col.offset"
        :style="col.style"
        :class="col.class"
      >
        <el-form-item :label="col.label" :rules="col.rules">
          <ja-form-item :info="col.formitem" :edit="info.edit" />
        </el-form-item>
      </el-col>
    </el-row>
  </el-form>
</template>

<script>
import JaFormItem from "./JaFormItem.vue";

export default {
  name: "JaForm",
  components: {
    JaFormItem,
  },
  props: {
    info: {
      type: Object,
      default: () => {
        return {};
      },
    },
  },
  beforeMount() {
    let f = {};
    for (let i = 0; i < this.info.children.length; i++) {
      for (let j = 0; j < this.info.children[i].length; j++) {
        if (!f[this.info.children[i][j].formitem.formName]) {
          f[this.info.children[i][j].formitem.formName] = {};
        }
        if (this.info.children[i][j].formitem.formType == "Array") {
          f[this.info.children[i][j].formitem.formName][
            this.info.children[i][j].formitem.name
          ] = [""];
        } else if (this.info.children[i][j].formitem.formType == "Object") {
          let target = f[this.info.children[i][j].formitem.formName];
          let k = 0;
          for (
            k = 0;
            k < this.info.children[i][j].formitem.name.length - 1;
            k++
          ) {
            if (!target) {
              target = {};
            }
            if(!target[this.info.children[i][j].formitem.name[k]]){
              target[this.info.children[i][j].formitem.name[k]] = {};
            }
            target = target[this.info.children[i][j].formitem.name[k]];
          }
          target[this.info.children[i][j].formitem.name[k]] = "";
        } else {
          if (this.info.children[i][j].formitem.type == "checkbox") {
            f[this.info.children[i][j].formitem.formName][
              this.info.children[i][j].formitem.name
            ] = this.info.children[i][j].formitem.default;
          } else {
            f[this.info.children[i][j].formitem.formName][
              this.info.children[i][j].formitem.name
            ] = "";
          }
        }
      }
    }
    if (this.info.handledom) {
      //console.log(this.$route.params)
      this.$store.dispatch("changeinfo", {
        path: this.info.path,
        handle: this.info.handledom,
        data: this.$route.params.pk,
      });
    }
    //this.$store.dispatch("changeinfo",{path:this.info.path,handle:this.info.handle})
    this.$store.dispatch("setForm", f);
    if (this.info.handle) {
      this.$store.dispatch("formSetup", {
        apis: this.info.apis,
        path: this.info.path,
        handle: this.info.handle,
      });
    }
  },
};
</script>

<style scoped>
</style>