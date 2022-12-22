<template>
 <div style="display: flex;flex-direction: row;">
   <div>
     <img src = "https://www.yishuzi.cn/image.png?fsize=30&font=wmwb.ttf&text=FDU2013&mirror=no&color=111&vcolor=111&bgcolor=fff&alpha=no&output=png&spacing=4&shadow=no&transparent=no&icon=no&iconic=&top_spacing=5&left_spacing=6&icon_size=48">
   </div>
   <div style="width: 100%">
     <el-menu
         :default-active="$route.path"
         class="el-menu-demo"
         mode="horizontal"
         @select="handleSelect"
         router
     >
       <el-menu-item index="/user/shop">商品首页</el-menu-item>
       <el-menu-item index="/user/address">地址管理</el-menu-item>
       <el-menu-item index="/user/buylist">购物车</el-menu-item>
       <el-sub-menu index="2">
         <template #title>查看订单</template>
         <el-menu-item index="/user/notpaidorder">查看待支付订单</el-menu-item>
         <el-menu-item index="/user/undeliverorder">查看待发货订单</el-menu-item>
         <el-menu-item index="/user/notreceiveorder">查看待收货订单</el-menu-item>
         <el-menu-item index="/user/finishedorder">查看已完成订单 </el-menu-item>
       </el-sub-menu>
       <el-sub-menu index="3">
         <template #title>评价信息</template>
         <el-menu-item index="/user/needcomment">查看未评价商品</el-menu-item>
         <el-menu-item index="/user/alreadycomment">查看已评价商品</el-menu-item>
       </el-sub-menu>
     </el-menu>
   </div>
   <div style="margin:20px 0 0px 45%">
     <UserInfos/>
   </div>
 </div>
</template>

<script>
import UserInfos from "@/components/UserInfos";
export default {
  name: "UserHeader",
  components: {UserInfos},
  props:['userinfo']
}
</script>

<style scoped>

</style>