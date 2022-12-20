<template>
<div>
  <Header/>
  <div style="width: 30%;margin: 10px auto">
    <div style="margin: 0 0 10px 0;text-align: center;font-size: large;font-weight: bold;color:rgb(85,158,255)">
      注册新用户
    </div>
    <el-form :label-position="labelPosition" label-width="80px" :model="newUser" size="medium" >
      <el-form-item label="请输入ID">
        <el-input v-model="newUser.ID"></el-input>
      </el-form-item>
      <el-form-item label="请输入密码">
        <el-input v-model="newUser.password"></el-input>
      </el-form-item>
      <el-form-item label="请输入名字">
        <el-input v-model="newUser.name"></el-input>
      </el-form-item>
      <el-form-item label="请输入邮箱">
        <el-input v-model="newUser.email"></el-input>
      </el-form-item>
      <el-form-item label="请输入电话">
        <el-input v-model="newUser.phone"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitForm('newUser')">提交</el-button>
        <el-button @click="resetForm('newUser')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</div>
</template>

<script>
import Header from "@/components/Header";
import request from "@/utils/request";
export default {
  name: "Register",
  components: {Header},
  data() {
    return {
      labelPosition: 'top',
      newUser: {
        ID: '',
        password: '',
        name: '',
        email:' ',
        phone : ''
      }
    }
  },
  methods: {
    submitForm(formName) {
      request.post("/auth/register", this.newUser).then(res => {
        if (res.data.code === 200) {
          this.$message({
            type: "success",
            message: res.data.msg
          })
        } else {
          this.$message({
            type: "error",
            message: res.data.msg
          })
          this.$router.replace('/login')
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>

<style scoped>

</style>