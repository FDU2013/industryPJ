<template>
  <el-container>
    <el-header>
      <Header/>
    </el-header>

    <el-container>

      <el-aside width="20%" style="margin-top: 10px;">
        <AdminIndex/>
      </el-aside>

      <el-main>
        <router-view></router-view>
      </el-main>

    </el-container>
  </el-container>
</template>

<script>


import Header from "@/components/Header";
import AdminIndex from "@/components/AdminIndex";
export default {
  name: "AdminMain",
  components: {AdminIndex, Header},
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    }
  }

}
</script>

<style scoped>

</style>