<template>
  <div class="main">
    <div class="header">
      <Header/>
    </div>
    <div class="login-main">
<!--      输入账号和密码的地方-->
      <div class="login-input">
        <div class="login" @keyup.enter="login">
          <div style="margin: 0 0 10px 0;text-align: center;font-size: large;font-weight: bold;color:rgb(85,158,255)">
            登录
          </div>
          <el-form :model="login_data" size="normal" :rules="rules">
            <el-form-item prop="ID">
              <el-input prefix-icon="User" v-model="login_data.ID"/>
            </el-form-item>
            <el-form-item prop="password">
              <el-input show-password prefix-icon="Lock" v-model="login_data.password" />
            </el-form-item>
            <el-button style="width: 100%;" type="primary" @click="login">
              登录
            </el-button>
            <div style="font-size: 5px;margin-top: 5px;float: right;cursor: pointer" @click = "register">
              还没有账号？立即注册
            </div>
          </el-form>
        </div>
      </div>

    </div>

    <div class="footer">
      Copyright FDU@2013
    </div>
  </div>

</template>


<script>
import request from "@/utils/request";
import {admin_routes, user_routes} from "@/router";
import {student_routes} from "@/router";
import {teacher_routes} from "@/router";
import router from "@/router";
import axios from 'axios'
import Header from "@/components/Header";

function add_routes () {
  var cache = [];
  sessionStorage['routes'] = JSON.stringify(router.getRoutes(), function(key, value) {
      if (typeof value === 'object' && value !== null) {
          if (cache.indexOf(value) !== -1) {
              return;
          }
          cache.push(value);
      }
      return value;
  });
  cache = null;
}

export default {
  name: "Login",
  components: {Header},
  data() {
    return {
      login_data: {
        ID: '',
        password: ''
      },
      rules: {
        number: [
          { required: true, message: '请输入学/工号', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    register:function (){
      router.push('/register')
    },
    login: function (){
      request.post("/auth/login", this.login_data).then(res => {
        if (res.data.code === 200) {
          this.$message({
            type: "success",
            message: res.data.msg
          })
          sessionStorage.setItem("user", JSON.stringify(res.data.data))
          if (res.data.data.role === "admin") {
            router.addRoute(admin_routes)
            add_routes()
            router.push('/admin')
            setTimeout(() => {
              router.go(0)
            }, 10);

          }
          else if (res.data.data.role === "user") {
            router.addRoute(user_routes)
            add_routes()
            router.push('/user')
            setTimeout(() => {
              router.go(0)
            }, 10);
          }
          // else if (res.data.data.role === "student") {
          //   router.addRoute(student_routes)
          //   add_routes()
          //   router.push('/student')
          //   setTimeout(() => {
          //     router.go(0)
          //   }, 10);
          // }
          // else if (res.data.data.role === "teacher") {
          //   router.addRoute(teacher_routes)
          //   add_routes()
          //   router.push('/teacher')
          //   setTimeout(() => {
          //     router.go(0)
          //   }, 10);
          // }
        }
        else {
          this.$message({
            type: "error",
            message: res.data.msg
          })
        }

      })
    }
  },
}


</script>

<style scoped>
body{
  width: 100%;
  height: 100%;
}
.main{
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  position: fixed;
}
.header{
  height: 10%;
}
.login-main{
  background: url("../assets/img_3.png")
  0px 0px no-repeat;
  background-size: 100% 100%;
  background-color: #003A12;
  width: 100%;
  height: 85%;
  display: flex;
}
.login-input{
  background: white;
  margin:10% 0 0 60%;
  width: 30%;
  height: 60%;
  border-radius: 12px;
  display: flex;
}
.login {
  width: 60%;
  height:60%;
  float:right;
  margin: auto 20%;
}
.footer{
  height: 5%;
  margin: 10px auto;
  font-size: small;

}
</style>