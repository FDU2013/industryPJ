<template>
  <div>
    <div>
      <div class="add" style="margin: 10px 0 10px 15%">
            <el-button size="large" @click="add" type="primary">新增</el-button>
      </div>
      <div>
        <el-table :data="tableData" style="width: 70%;margin: 0 auto" border stripe>
          <el-table-column prop="addressID" label="地址ID" min-width="10%" v-if="false"/>
          <el-table-column prop="name" label="收货人姓名" min-width="10%"/>
          <el-table-column prop="phone" label="收货人电话" min-width="10%"/>
          <el-table-column prop="details" label="具体收货地址" min-width="60%"/>
          <el-table-column prop="description" label="描述" min-width="10%"/>
          <el-table-column fixed="right" label="操作" min-width="10%">
            <template #default="scope">
<!--              <el-button type="text" size="small" @click="handleEdit(-->
<!--                  scope.row.name,-->
<!--                  scope.row.phone,-->
<!--                  scope.row.details,-->
<!--                  scope.row.description-->
<!--                  )">编辑</el-button>-->
              <el-popconfirm title="确认删除?" @confirm="handleDelete(scope.row.addressID)">
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
      <el-dialog v-model="dialogVisible" title="添加地址" width="30%">
        <el-form :model="addAddress" label-width="120px">
          <el-form-item label="收货人姓名">
            <el-input v-model="addAddress.name" />
          </el-form-item>
          <el-form-item label="收货人电话">
            <el-input v-model="addAddress.phone" />
          </el-form-item>
          <el-form-item label="详细地址">
            <el-input v-model="addAddress.details" />
          </el-form-item>
          <el-form-item label="描述">
            <el-input v-model="addAddress.description" />
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
      <el-dialog v-model="dialogVisible2" title="编辑商品信息" width="30%">
        <el-form :model="newAddress" label-width="120px">
          <el-form-item label="收货人姓名">
            <el-input v-model="newAddress.name" />
          </el-form-item>
          <el-form-item label="收货人电话">
            <el-input v-model="newAddress.phone" />
          </el-form-item>
          <el-form-item label="详细地址">
            <el-input v-model="newAddress.details" />
          </el-form-item>
          <el-form-item label="描述">
            <el-input v-model="newAddress.description" />
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
  name: "UserAddress",
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
      addAddress:{
        name:'',
        phone:'',
        details:'',
        description:''
      },
      newAddress:{
        name:'',
        phone:'',
        details:'',
        description: ''
      },
      tableData:[],
      mediumList:[],
      tagList:[]
    }
  },
  mounted() {
    this.load()
  },
  methods:{
    load(){
      setTimeout(() => {
        request.post("/user/allAddress",).then(res=>{

          if(res.data.code===200){
            this.tableData=res.data.data
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
      request.post("/user/addAddress", this.addAddress).then(res => {
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
    saveEdit(){
      request.post("/shop/updateGood", this.newGoods).then(res=>{
        if(res.data.code!==200) {
          this.$message({
            type:"error",
            message: res.data.msg
          })
        }
        else{
          this.$message({
            type:"success",
            message: res.data.msg
          })
        }
        this.load()
        this.dialogVisible2=false
      })
    },
    handleEdit(name,phone,details,description){
      this.dialogVisible2 = true
      this.newAddress.name = name
      this.newAddress.phone = phone
      this.newAddress.details = details
      this.newAddress.description = description
    },
    handleDelete(addressId) {
      request.post("/user/deleteAddress",addressId).then(res => {
        if(res.data.code ===200) {
          this.$message({
            type:"success",
            message: res.data.msg
          })
        }
        else if(res.data.code!==200) {
          this.$message({
            type:"error",
            message: res.data.msg
          })
        }
        this.load()
      })
    },
  }
}
</script>

<style scoped>
</style>