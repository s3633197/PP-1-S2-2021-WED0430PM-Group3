<template>
    <el-upload
        drag
        multiple
        id="upload-form"
        @change="importExcel"
        :auto-upload="false"
    >
        <i class="el-icon-upload"></i>
        <div class="el-upload_text">
            <span id="upload-text-1">将文件拖到此处，或<em>点击上传</em></span>
            <br/>
            <span id="upload-text-2">支持扩展名: xls/xlsx/csv</span>
            <br/>
        </div>
        <template #tip>
            <div class="el-upload_tip">
                只能上传 xls/xlsx/csv 文件，且不超过500kb
            </div>
        </template>
    </el-upload>
</template>

<script>

import XLSL from 'xlsx'

import { ElMessage } from 'element-plus'

export default {
    name: "JaExcel",
    props:{
        info: Object
    },
    methods: {
        file2Xce:function(file){
            let types = file.name.split('.');
            types = types[types.length-1];
            let fileType = ['xlsx','xls','xlm','xls','xlt','xlw','csv'].some(item => item === types);
            if(!fileType){
                //this.$message('格式错误! 请重新选择');
                ElMessage.error('格式错误！请重新选择');
                return;
            }
            // eslint-disable-next-line no-unused-vars
            return new Promise(function(resolve,reject){
                const reader = new FileReader();
                reader.onload = function(e){
                    const data = e.target.result;
                    this.wb = XLSL.read(data,{
                        type:'binary'
                    });
                    const result = [];
                    this.wb.SheetNames.forEach((sheetName)=>{
                        result.push({
                            sheetName:sheetName,
                            sheet:XLSL.utils.sheet_to_json(this.wb.Sheets[sheetName])
                        })
                    });
                    resolve(result);
                }
                reader.readAsBinaryString(file.raw);
                //console.log(reject);
            })
        },
        importExcel:function(file){
            // const types = file.name.split('.')[1];
            // const fileType = ['xlsx','xlc','xlm','xls','xlt','xlw','csv'].some(item => item === types);
            // if(!fileType){
            //     this.$message('格式错误！请重新选择');
            //     return;
            // }
            // let types = file.name.split('.');
            // types = types[types.length-1];
            // let fileType = ['xlsx','xls','xlm','xls','xlt','xlw','csv'].some(item => item === types);
            // if(!fileType){
            //     //this.$message('格式错误! 请重新选择');
            //     //ElMessage.error('格式错误！请重新选择');
            //     return;
            // }
            this.file2Xce(file).then(
                tableJson => {
                    if(tableJson && tableJson.length > 0){
                        let main = {};
                        main.default = {};
                        main.default = this.info.handle(tableJson);
                        let tableData = this.info.makeTableData(tableJson);
                        this.$store.dispatch("setTableDataByExcel",tableData);
                        //console.log(main);
                        this.$store.dispatch("setMain",main);
                        //console.log(tableData);
                        
                        //console.log('数据',this.xlsxJson);
                    }
                }
            )
        }
    }
}

</script>

<style>

#upload-text-1{
    width: 208px;
    height: 24px;
    font-size: 16px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: #333333;
    line-height: 24px;
}

#upload-text-2{
    width: 162px;
    height: 22px;
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: #999999;
    line-height: 22px;
}


</style>