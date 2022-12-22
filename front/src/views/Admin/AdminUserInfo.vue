<template>
  <div>
    <div>
      <div>
        <el-table :data="tableData" style="width: 80%;margin:0 auto" border stripe>
          <el-table-column prop="userID" label="ID" min-width="20%"/>
          <el-table-column prop="name" label="名字" min-width="20%"/>
          <el-table-column prop="email" label="邮箱" min-width="20%" />
          <el-table-column prop="phone" label="电话" min-width="20%" />
          <el-table-column fixed="right" label="操作" min-width="20%">
            <template #default="scope">
              <el-button type="text" size="small" @click="addMoney(scope.row.userID)">充值</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div style="margin: 10px auto 0 35%;width: 50%;">
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
      <el-dialog v-model="dialogVisible" title="充值" width="50%">
        <el-form :model="addMoneyInfo" label-width="">

          <el-form-item label="充值金额">
            <el-input v-model="addMoneyInfo.money" />
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
  name: "AdminUserInfo",
  data(){
    return{
      total:0,
      pageSize:10,
      currentPage:1,
      search:'',
      dialogVisible:false,
      dialogVisible2:false,
      introduction:'',
      tableData:[],
      addMoneyInfo:{
        ID:'',
        money:''
      },
      tableData2:[],
    }
  },
  mounted() {
    this.load()
  },
  methods:{
    load(){
      setTimeout(() => {
        request.post("/admin/getUserPage",{
              pageNum: this.currentPage,
              pageSize: this.pageSize,
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
    addMoney:function(userID){
      this.dialogVisible = true
      this.addMoneyInfo={}
      this.addMoneyInfo.ID = userID
    },
    save:function (){
      request.post("/money/add", this.addMoneyInfo).then(res => {
        if(res.data.code!==200) {
          this.$message({
            type:"error",
            message: res.data.msg
          })
        }
        if(res.data.code===200) {
          this.$message({
            type:"success",
            message: res.data.msg
          })
        }
        this.load() // 刷新表格的数据
        this.dialogVisible = false  // 关闭弹窗
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