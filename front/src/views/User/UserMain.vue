<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <UserHeader/>
      </el-header>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </div>

</template>

<script>
import UserHeader from "@/components/UserHeader";
export default {
  name: "UserMain",
  components: {UserHeader}
}
</script>

<style scoped>

</style>