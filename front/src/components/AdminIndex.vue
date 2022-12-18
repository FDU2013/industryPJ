<template>
  <el-row class="tac" style="width: 100%">
    <el-col :span="12">
      <el-menu default-active="2" class="el-menu-vertical-demo">
        <el-sub-menu index="1">
          <template #title>
            <span>商品管理</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="1-1">
              <router-link to="/admin/goodspublisher">出版社管理</router-link>
            </el-menu-item>
            <el-menu-item index="1-2">
              <router-link to="/admin/goodsmedium">商品媒介管理</router-link>
            </el-menu-item>
            <el-menu-item index="1-3">
              <router-link to="/admin/goodstag">商品标签管理</router-link>
            </el-menu-item>
            <el-menu-item index="1-4">
              <router-link to="/admin/goodsinfo">商品信息管理</router-link>
            </el-menu-item>
          </el-menu-item-group>
        </el-sub-menu>

        <el-sub-menu index="2">
          <template #title>
            <span>订单管理</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="1-1">
              <router-link to="/admin/ordersend">未发货订单管理</router-link>
            </el-menu-item>
            <el-menu-item index="1-2">
              <router-link to="/admin/orderreceive">待收货订单管理</router-link>
            </el-menu-item>
            <el-menu-item index="1-3">
              <router-link to="/admin/orderfinished">已完成订单管理</router-link>
            </el-menu-item>
          </el-menu-item-group>
        </el-sub-menu>

      </el-menu>
    </el-col>
  </el-row>
</template>

<script>
export default {
  name: "AdminIndex"
}
</script>

<style scoped>

</style>