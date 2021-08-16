<template>
  <el-table-column
    v-if="info.type == 'column'"
    :prop="info.name"
    :label="info.label"
    :width="info.width"
    :fixed="info.fixed"
  >
    <template #default="scope">
      <ja-form-item
        v-if="scope.row.edit"
        :info="info.makeFormItem(info.name, scope.row, scope.$index)"
        :edit="scope.row.edit"
      ></ja-form-item>
      <span v-else>{{ val(scope.row) }}</span>
    </template>
  </el-table-column>

  <el-table-column
    v-else-if="info.type == 'columns'"
    :label="info.label"
    :width="info.width"
    :fixed="info.fixed"
  >
    <ja-table-column
      v-for="child in info.children"
      :key="child"
      :info="child"
    />
  </el-table-column>

  <el-table-column
    v-else-if="info.type == 'tag'"
    :label="info.label"
    :prop="info.name"
    :width="info.width"
    :fixed="info.fixed"
  >
    <template #default="scope">
      <el-tag :type="info.tagtype(scope.row[info.name])" disable-transitions>
        {{ scope.row[info.name] }}
      </el-tag>
    </template>
  </el-table-column>

  <el-table-column
    v-else-if="
      info.type == 'popover-tag' ||
      info.type == 'popover' ||
      info.type == 'popover-table' ||
      info.type == 'popver-table-tag'
    "
    :label="info.label"
    :fixed="info.fixed"
    :width="info.width"
  >
    <template #default="scope">
      <el-popover
        :effect="info.effect"
        :trigger="info.trigger"
        :placement="info.placement"
      >
        <template
          #default
          v-if="info.type == 'popover-tag' || info.type == 'popover'"
        >
          <div v-html="scope.row.popoverhtml"></div>
          <div
            style="text-align: right; margin: 0"
            v-if="scope.row.popoverOperation"
          >
            <el-button
              v-for="item in scope.row.popoverOperation"
              :key="item"
              :type="item.type"
              @click="handelClick(item, scope.$index, scope.row)"
              >{{ item.content }}</el-button
            >
          </div>
        </template>
        <template
          #default
          v-else-if="
            info.type == 'popover-table' || info.type == 'popover-tab-tag'
          "
        >
          <ja-table :info="scope.row.popoverTable" />
        </template>
        <template #reference>
          <div class="name-wrapper">
            <el-tag
              v-if="
                info.type == 'popover-tag' || info.type == 'popover-table-tag'
              "
              :type="info.tagtype(scope.row[info.name])"
              disable-transitions
            >
              {{ scope.row[info.name] }}
            </el-tag>
            <span v-else>{{ scope.row[info.name] }}</span>
          </div>
        </template>
      </el-popover>
    </template>
  </el-table-column>

  <el-table-column
    v-else-if="info.type == 'operation'"
    :label="info.label"
    :fixed="info.fixed"
    :width="info.width"
  >
    <template #default="scope">
      <el-button
        v-for="child in info.children"
        :key="child"
        @click="handelClick(child, scope.$index, scope.row)"
        :type="child.type"
        :size="child.size"
        >{{ child.label }}</el-button
      >
    </template>
  </el-table-column>
</template>

<script>
import JaFormItem from "./JaFormItem.vue";
import JaTable from "./JaTable.vue";
export default {
  components: { JaTable, JaFormItem },
  name: "JaTableColumn",
  props: {
    info: {
      type: Object,
      default: () => {
        return {};
      },
    },
  },
  computed: {
    // val:() => (data)=>{
    //   //console.log(data);
    //   console.log(this.info);
    //   return data;
    // },
    val: {
      get: function () {
        return function (data) {
          if (data == {}) {
            return null;
          }
          if (this.info.name.indexOf(".") != -1) {
            let names = this.info.name.split(".");
            let target = data;
            for (let i = 0; i < names.length; i++) {
              target = target[names[i]];
              if (!target) {
                return null;
              }
            }
            if(Array.isArray(target)) {
              target = target.join(",");
            }
            return target;
          } else {
            // if(data[this.info.name] && data[this.info.name].length && typeof data[this.info.name] == 'object'){
            //   return data[this.info.name].join(",");
            // }
            if(Array.isArray(data[this.info.name])){
              return data[this.info.name].join(",");
            }
            return data[this.info.name];
          }
        };
      },
    },
  },
  methods: {
    handelClick(obj, index, row) {
      obj.f.data.row = row;
      obj.f.data.index = index;
      if (obj.apihandle) {
        obj.f.api = obj.apihandle(obj.f.api, index, row);
      }
      if (obj.f.api) {
        //obj.f.api = obj.apihandle(obj.f.api,index,row);
        if (obj.formNameHandle) {
          obj.f.formName = obj.formNameHandle(obj.f.formName, index, row);
        }
        this.$store.dispatch("apiConn", obj.f);
      } else {
        if (obj.targethandle){
          let target = obj.targethandle(index,row);
          this.$router.push(target);
        }else{
          this.$store.dispatch("changeinfo", obj.f);
        }
      }
    },
  },
};
</script>

<style scoped>
</style>