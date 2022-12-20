<template>
  <div>
    <div>
      <div class="add" style="margin: 10px 0">
        <div>
          <el-button size="large" @click="add" type="primary">新增</el-button>
        </div>
      </div>
      <div>
        <el-table :data="tableData" style="width: 80%" border stripe>
          <el-table-column prop="name" label="出版社名" min-width="30%"/>
          <el-table-column prop="details" label="简介" min-width="50%" />
          <el-table-column fixed="right" label="操作" min-width="20%">
            <template #default="scope">
              <el-button type="text" size="small" @click="handleEdit(scope.row.name, scope.row.details)">编辑</el-button>
              <el-popconfirm title="确认删除?" @confirm="handleDelete(scope.row.name)">
                <template #reference>
                  <el-button type="text">删除</el-button>
                </template>
              </el-popconfirm>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div style="margin: 10px 0">
        <el-pagination
            v-model:currentPage="currentPage"
            v-model:page-size="pageSize"
            layout="total, prev, pager, next, jumper"
            :total="total"
            @current-change="handleCurrentChange"
        />
      </div>
    </div>
    <div>
      <el-dialog v-model="dialogVisible" title="添加新出版社" width="30%">
        <el-form :model="addPublisher" label-width="120px">
          <el-form-item label="出版社名">
            <el-input v-model="addPublisher.name" />
          </el-form-item>
          <el-form-item label="具体介绍">
            <el-input v-model="addPublisher.details"/>
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
    <div>
      <el-dialog v-model="dialogVisible2" title="编辑出版社信息" width="30%">
        <el-form :model="newPublisher" label-width="120px">
          <el-form-item label="院系">
            <el-input v-model="newName" />
          </el-form-item>
          <el-form-item label="介绍">
            <el-input v-model="introduction"/>
          </el-form-item>
          <span class="dialog-footer">
        <el-button @click="dialogVisible2 = false">取消</el-button>
        <el-button type="primary" @click="saveEdit">确认</el-button>
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
  name: "AdminGoodsPublisher",
  data(){
    return{
      total:0,
      pageSize:10,
      currentPage:1,
      search:'',
      dialogVisible:false,
      dialogVisible2:false,
      oldName:'',
      newName:'',
      introduction:'',
      addPublisher:{
        name:'',
        details:''
      },
      newPublisher:{},
      tableData:[]
    }
  },
  mounted() {
    this.load()
  },
  methods:{
    load(){
      setTimeout(() => {
        request.post("/shop/searchPublisherPage",{
              pageNum: this.currentPage,
              pageSize: this.pageSize,
              search: this.search
            }
        ).then(res=>{

          if(res.data.code===200){
            this.tableData=res.data.data.records
            this.total=res.data.data.total
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
      this.addPublisher={}
    },
    save:function (){
      request.post("/shop/addPublisher", this.addPublisher).then(res => {
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
    saveEdit(){

      request.post("/admin/updateSchoolInfo", {
        oldName:this.oldName,
        newName:this.newName,
        introduction:this.introduction
      }).then(res=>{
        if(res.data.code!==200) {
          this.$message({
            type:"error",
            message: res.data.msg
          })
        }
        this.load()
        this.dialogVisible2=false
      })
    },
    handleEdit(schoolName, introduction){

      this.oldName=schoolName
      this.newName=schoolName
      this.introduction=introduction

      this.dialogVisible2 = true
      this.newPublisher={}
    },
    handleDelete(name) {
      request.post("/shop/deletePublisher",name).then(res => {
        if(res.data.code!==200) {
          this.$message({
            type:"error",
            message: res.data.msg
          })
        }
        this.load()  // 删除之后重新加载表格的数据
      })
    },
    handleCurrentChange:function (pageNum){
      this.currentPage = pageNum
      this.load()
    }
  }
}
</script>

<style scoped>
.add{
  text-align: left;
}
</style>