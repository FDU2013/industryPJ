<template>
  <div class="main">
    <div>
      <div class="add" style="margin: 10px 0">
        <div style="margin-bottom: 10px">
          <el-button size="large" @click="add" type="primary">新增</el-button>
        </div>
      </div>
      <div>
        <el-table :data="tableData" style="width: 100%" border stripe>
          <el-table-column prop="tagName" label="标签" min-width="80%"/>
          <el-table-column fixed="right" label="操作" min-width="20%">
            <template #default="scope">
              <el-popconfirm title="确认删除?" @confirm="handleDelete(scope.row.tagName)">
                <template #reference>
                  <el-button type="text">删除</el-button>
                </template>
              </el-popconfirm>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <div>
      <el-dialog v-model="dialogVisible" title="添加新标签" width="30%">
        <el-form :model="addMedium" label-width="120px">
          <el-form-item label="媒介名">
            <el-input v-model="addMedium.mediumName" />
          </el-form-item>
          <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="save">确认</el-button>
      </span>
        </el-form>
        <template #footer>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "AdminGoodsTag",
  data(){
    return{
      total:0,
      pageSize:10,
      currentPage:1,
      search:'',
      dialogVisible:false,
      oldName:'',
      newName:'',
      introduction:'',
      addMedium :{
        mediumName:''
      },
      newSchool:{},
      tableData:[]
    }
  },
  mounted() {
    this.load()
  },
  methods:{
    load(){
      setTimeout(() => {
        request.post("/shop/getAllGoodsTag",{}).then(res=>{
          if(res.data.code===200){
            this.tableData=res.data.data
            console.log(this.tableData)
          }
          else{
            this.$message({
              type:"error",
              message: res.data.msg
            })
          }
        })
      }, 500)
    },
    add:function (){
      this.dialogVisible=true
      this.addMedium={}
    },
    save:function (){
      request.post("/shop/addGoodsTag", this.addMedium.mediumName).then(res => {
        if(res.data.code!==200) {
          this.$message({
            type:"error",
            message: res.data.msg
          })
        }
        this.load() // 刷新表格的数据
        this.dialogVisible = false  // 关闭弹窗
      })
    },
    handleDelete(mediumName) {
      request.post("/shop/deleteGoodsTag",mediumName).then(res => {
        if(res.data.code!==200) {
          this.$message({
            type:"error",
            message: res.data.msg
          })
        }
        this.load()  // 删除之后重新加载表格的数据
      })
    }
  }
}
</script>

<style scoped>
.main{
  margin-right: 10%;
}
.add{
  text-align: left;
}
</style>