<template>
    <el-card :class="info.class">
        <template #header v-if="info.header">
            <div :class="info.header.class">
                <span v-if="info.header.title">{{info.header.title}}</span>
                <ja-form-item v-if="info.header.op" :info="info.header.op"/>
            </div>
        </template>
        <ja-form v-if="info.form" :info="info.form" />
        <ja-table v-if="info.table" :info="info.table" />
    </el-card>
</template>

<script>
import JaForm from './JaForm.vue'
import JaFormItem from './JaFormItem.vue'
import JaTable from './JaTable.vue'

export default {
  components: { JaFormItem, JaForm, JaTable },
    props:{
        info: Object
    },
}

</script>

<style scoped>

</style>