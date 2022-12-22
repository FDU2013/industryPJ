<template>
<div>
  <el-card class="box-card" style="width: 40%;margin: 0 auto;border-radius: 12px">
    <div slot="header" class="clearfix">
      <span>个人信息</span>
      <el-button style="float: right; padding: 3px 0" type="text" @click = "changeInfo(this.phone,this.name,this.email)">修改个人信息</el-button>
    </div>
    <el-divider/>
    <div style="margin: 10px 0">
      用户ID：{{this.userID}}
    </div>
    <div style="margin: 10px 0">
      用户名：{{this.username}}
    </div>
    <div style="margin: 10px 0">
      电 话：{{this.phone}}
    </div>
    <div style="margin: 10px 0">
      姓 名:{{this.name}}
    </div>
    <div style="margin: 10px 0">
      邮 箱：{{this.email}}
    </div>
  </el-card>
</div>

  <div>
    <el-dialog v-model="dialogVisible" title="修改个人信息" width="50%">
      <el-form :model="newInfo" label-width="">

        <el-form-item label="电 话">
          <el-input v-model="newInfo.phone" />
        </el-form-item>

        <el-form-item label="姓 名">
          <el-input v-model="newInfo.name" />
        </el-form-item>

        <el-form-item label="邮 箱">
          <el-input v-model="newInfo.email" />
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
</template>

<script>
import request from "@/utils/request";

export default {
  name: "UserCenter",
  data(){
    return{
      userID:'',
      username:'',
      phone:'',
      name:'',
      email:'',
      dialogVisible:false,
      newInfo:{
        phone:'',
        name:'',
        email:''
      }
    }
  },
  mounted() {
    this.load()
    this.username = JSON.parse(sessionStorage.getItem("user")).username
  },
  methods:{
    load:function (){
      request.post("/user/info",).then(res=>{
        this.phone = res.data.data.phone
        this.name = res.data.data.name
        this.userID = res.data.data.userID
        this.email = res.data.data.email
        if(res.data.code===200){
          this.$message({
            type:"success",
            message: res.data.msg
          })
        }
        else{
          this.$message({
            type:"error",
            message: res.data.msg
          })
        }
      })
    },
    changeInfo:function (phone,name,email){
      this.dialogVisible = true
      this.newInfo.phone = phone
      this.newInfo.name = name
      this.newInfo.email = email
    },
    save:function (){
      request.post("/user/updateInfo",this.newInfo).then(res=>{
        if(res.data.code===200){
          this.$message({
            type:"success",
            message: res.data.msg
          })
        }
        else{
          this.$message({
            type:"error",
            message: res.data.msg
          })
        }
        this.dialogVisible = false
        this.load()
      })
    }
  }
}
</script>

<style scoped>

</style>