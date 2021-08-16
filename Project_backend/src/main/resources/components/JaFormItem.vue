<template>
  <el-input
    v-model="value"
    v-if="info.type == 'input' && edit"
    :placeholder="info.placeholder"
    :suffix-icon="info.suffixIcon"
    :prefix-icon="info.prefixIcon"
    :size="info.size"
    :style="info.style"
    :disabled="info.disabled"
    :show-password="info.showPassword"
  >
    <template #prepend v-if="info.prepend">{{ info.prepend }}</template>
    <template #append v-if="info.append">{{ info.append }}</template>
  </el-input>
  <el-input
    v-model.number="value"
    v-else-if="info.type == 'number' && edit"
    :placeholder="info.placeholder"
    :suffix-icon="info.suffixIcon"
    :prefix-icon="info.prefixIcon"
    :size="info.size"
    :style="info.style"
    :disabled="info.disabled"
  >
    <template #prepend v-if="info.prepend">{{ info.prepend }}</template>
    <template #append v-if="info.append">{{ info.append }}</template>
  </el-input>
  <el-date-picker
    v-model="value"
    :placeholder="info.placeholder"
    v-else-if="(info.type == 'date' || info.type == 'datetime') && edit"
    :type="info.type"
    value-format="YYYY-MM-DD HH:mm:ss"
    :style="info.style"
    :size="info.size"
    :disabled="info.disabled"
  ></el-date-picker>
  <el-select
    v-model="value"
    :placeholder="info.placeholder"
    v-else-if="info.type == 'select' && edit"
    :style="info.style"
    :size="info.size"
    :multiple="info.multiple"
    :clearable="info.clearable"
    :disabled="info.disabled"
  >
    <el-option
      v-for="option in info.options"
      :key="option"
      :label="option.label"
      :value="option.value"
      :disabled="option.disabled"
      v-html="option.html"
    ></el-option>
  </el-select>
  <el-input
    v-model="value"
    :placeholder="info.placeholder"
    v-else-if="info.type == 'textarea' && edit"
    :type="info.type"
    show-word-limit
    :maxlength="info.maxlength"
    :autosize="info.autosize"
    :style="info.style"
    :size="info.size"
    :disabled="info.disabled"
  ></el-input>
  <el-checkbox
    v-model="value"
    v-else-if="info.type == 'checkbox' && edit"
    :disabled="info.disabled"
    :style="info.style"
    :size="info.size"
    >{{ info.label }}</el-checkbox
  >
  <el-button @click="handle" v-else-if="info.type == 'button'">{{
    info.label
  }}</el-button>
  <span v-else>{{ Data }}</span>
</template>

<script>
export default {
  name: "JaFormItem",
  props: {
    edit: {
      type: Boolean,
      default: () => {
        return true;
      },
    },
    info: {
      type: Object,
      default: () => {
        return {};
      },
    },
  },
  computed: {
    Data() {
      if (this.info.showPassword) {
        return "******";
      }
      if (this.info.formType == "Array") {
        //this.data = this.$store.state.form[this.info.formName][this.info.name][this.info.indextag];
        return this.$store.state.form[this.info.formName][this.info.name][
          this.info.indextag
        ];
      } else if (this.info.formType == "Object") {
        let target = this.$store.state.form[this.info.formName];
        for (let i = 0; i < this.info.name.length; i++) {
          //console.log(this.info.name);
          target = target[this.info.name[i]];
        }
        if(Array.isArray(target)){
          return target.join(",");
        }
        return target;
      } else if (this.info.formType == "default" && this.info.multiple) {
        if (this.$store.state.form[this.info.formName][this.info.name]) {
          return this.$store.state.form[this.info.formName][
            this.info.name
          ].join(",");
        } else {
          return "";
        }
      } else if (this.info.options) {
        if (typeof this.info.options[0] == "object") {
          return this.$store.state.form[this.info.formName][this.info.name]
            .name;
        }
      }
      if (Array.isArray(this.$store.state.form[this.info.formName][this.info.name])){
        return this.$store.state.form[this.info.formName][this.info.name].join(",")
      }
      //this.data = this.$store.state.form[this.info.formName][this.info.name];
      //console.log("here");
      return this.$store.state.form[this.info.formName][this.info.name];
    },
    value: {
      get() {
        let target;
        if (this.info.type == "checkbox") {
          let val = (target =
            this.$store.state.form[this.info.formName][this.info.name]);
          if (val == undefined || val == "" || val == null) {
            return false;
          } else {
            return true;
          }
        }
        if (this.info.formType == "Array") {
          target =
            this.$store.state.form[this.info.formName][this.info.name][
              this.info.indextag
            ];
        } else if (this.info.formType == "Object") {
          target = this.$store.state.form[this.info.formName];
          for (let i = 0; i < this.info.name.length; i++) {
            //console.log(target);
            target = target[this.info.name[i]];
          }
        } else if (this.info.formType == "default" && this.info.multiple) {
          if (this.$store.state.form[this.info.formName][this.info.name]) {
            target = this.$store.state.form[this.info.formName][this.info.name];
          } else {
            target = [];
          }
        } else if (this.info.options) {
          // if (typeof this.info.options[0] == "object") {
          //   target = {
          //     value:
          //       this.$store.state.form[this.info.formName][this.info.name].name,
          //     realValue: {
          //       name: this.$store.state.form[this.info.formName][this.info.name]
          //         .name,
          //       code: this.$store.state.form[this.info.formName][this.info.name]
          //         .code,
          //     },
          //   };
          // } else {
          //   target = this.$store.state.form[this.info.formName][this.info.name];
          // }
          target = this.info.optionget(
            this.$store.state.form[this.info.formName][this.info.name]
          );
        } else {
          target = this.$store.state.form[this.info.formName][this.info.name];
        }
        //this.data = target;
        //console.log(this.data);
        return target;
      },
      set(value) {
        let f = {};
        if (value.realValue) {
          value = value.realValue;
        }
        if (this.info.formType == "Array") {
          f = {
            formName: this.info.formName,
            name: this.info.name,
            index: this.info.indextag,
            newVal: value,
            formType: "Array",
          };
        } else if (this.info.formType == "Object") {
          f = {
            formName: this.info.formName,
            name: this.info.name,
            newVal: value,
            formType: "Object",
          };
        } else {
          f = {
            formName: this.info.formName,
            name: this.info.name,
            newVal: value,
          };
        }
        this.$store.dispatch("updateForm", f);
      },
    },
  },
  //data() {},
  methods: {
    handle: async function () {
      if (this.info.optype == "dom") {
        this.$store.dispatch("changeinfo", this.info.f);
      } else if (this.info.optype == "api") {
        this.$store.dispatch("apiConn", this.info.f);
      } else if (this.info.optype == "excel") {
        let tableData = this.$store.state.tableData[this.info.tableName].results;
        for(let i = 0; i < tableData.length; i++) {
          let f = this.info.f;
          f.data = tableData[i];
          await this.$store.dispatch("apiConn", f);
        }
      }
      if (this.info.target) {
        this.$router.push(this.info.target);
      }
    },
  },
  mounted() {
    if (this.info.multiple) {
      this.$store.dispatch("updateForm", {
        formName: this.info.formName,
        name: this.info.name,
        newVal: [],
      });
    }
  },
};
</script>

<style scoped>
</style>