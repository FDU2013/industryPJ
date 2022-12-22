<template>
  <el-dropdown>
    <el-button type="primary">
      你好,{{this.username}} !
      <el-icon class="el-icon--right"><arrow-down /></el-icon>
    </el-button>
    <template #dropdown>
      <el-dropdown-menu>
        <el-dropdown-item @click = "userCenter">个人中心</el-dropdown-item>
        <el-dropdown-item @click = "changePassword">修改密码</el-dropdown-item>
        <el-dropdown-item divided>您的余额为：{{this.money}}</el-dropdown-item>
      </el-dropdown-menu>
    </template>
  </el-dropdown>

  <div>
    <el-dialog v-model="dialogVisible" title="修改密码" width="30%">
      <el-form :model="changePass" label-width="120px">

        <el-form-item label="旧密码">
          <el-input v-model="changePass.oldPassword" />
        </el-form-item>

        <el-form-item label="新密码">
          <el-input v-model="changePass.newPassword" />
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
  name: "UserInfos",
  data(){
    return{
      username:'',
      money:'',
      dialogVisible:false,
      changePass:{
        oldPassword:'',
        newPassword:''
      }
    }
  },
  mounted() {
    this.getMoney()
    this.username = JSON.parse(sessionStorage.getItem("user")).username
  },
  methods:{
    getMoney:function (){
      request.post("/user/getMoney",).then(res=>{
        this.money = res.data.data
      })
    },
    userCenter:function (){
      this.$router.replace('/user/usercenter')
    },
    changePassword:function (){
      this.dialogVisible = true
    },
    save:function (){
      request.post("/user/changePassword",this.changePass).then(res=>{
        if (res.data.code === 200) {
          this.$message({
            type: "success",
            message: res.data.msg
          })
          this.dialogVisible = false
        }
        else {
          this.$message({
            type: "error",
            message: res.data.msg
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>