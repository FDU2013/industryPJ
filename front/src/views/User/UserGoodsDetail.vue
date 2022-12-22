<template>
  <div style="width: 70%;height:100%;margin: 0 auto;display: flex;flex-direction: column">
    <div style="width: 100%;height: 60%;display: flex;flex-direction: row">

      <div style="width: 40%">
        <el-image
            style=""
            :src="this.imageUrl"
            :zoom-rate="1.2"
            :preview-src-list="[this.imageUrl]"
            hide-on-click-modal="true"
            fit="cover"
            preview-teleported
        />
      </div>

      <div style="width: 50%;margin: 0 0 0 5%;">
        <el-card class="box-card" style="border-radius: 12px;text-align: center">
          <div style="font-size: 180%;font-weight: bold;margin-bottom: 10px">
            商品名：{{this.name}}
          </div>
          <div>
            商品Id:{{this.goodsId}}
          </div>
          <div>
            作者:{{this.author}}
          </div>
          <div>
            出版社:{{this.publisher}}
          </div>
          <div>
            媒介:{{this.medium}}
          </div>
          <div>
            标签:{{this.tag}}
          </div>
          <div>
            商品介绍:{{this.details}}
          </div>
          <div style="font-size: 150%;font-weight: bold;margin-top: 10px">
            价格:{{this.price}}元
          </div>
          <div style="margin-top: 10px">
            数量:  <el-input-number v-model="num" :min="1" :max="10" size="small" />
          </div>
          <div style="margin-top: 10px">
            <el-button type="primary" size="large" @click = handleAddList>加入购物车</el-button>
            <el-button type="success" size="large" @click = handleBuy>立即下单</el-button>
          </div>
        </el-card>
      </div>

    </div>

    <el-divider/>
    <div>
      <div style="font-size: 200%;font-weight: bold;color:rgb(85,158,255);margin-bottom: 5px">商品评价</div>
      <div>
        <el-table :data="tableData" style="width: 100%;margin:0 auto" border stripe>
          <el-table-column prop="userID" label="用户id" min-width="20%"/>
          <el-table-column prop="content" label="评价内容" min-width="60%"/>
          <el-table-column prop="stars" label="评价星级" min-width="10%" />
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
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "UserGoodsDetail",
  data(){
    return{
      goodsId:"",
      name:"",
      author:"",
      publisher:"",
      medium:"",
      tag:"",
      imageUrl:"",
      details:"",
      price:"",
      num:0,
      tableData:[],
      total:0,
      pageSize:10,
      currentPage:1,
    }
  },
  mounted() {
    this.load()
  },
  methods:{
    load:function (){
      this.goodsId = this.$route.query.goodsId
      this.name = this.$route.query.name
      this.author = this.$route.query.author
      this.publisher = this.$route.query.publisher
      this.medium = this.$route.query.medium
      this.tag = this.$route.query.tag
      this.imageUrl = this.$route.query.imageUrl
      this.details = this.$route.query.details
      this.price = this.$route.query.price
      setTimeout(() => {
        request.post("shop/getGoodComment",{
              goodID:this.goodsId,
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
    handleAddList:function (){
      request.post("/user/addToShoplist",{
        goodID:this.goodsId,
        num:this.num
      }).then(res=>{
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
    handleBuy:function (){
      request.post("/user/buy",{
        goodID:this.goodsId,
        num:this.num
      }).then(res=>{
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
    handleCurrentChange:function (pageNum){
      this.currentPage = pageNum
      this.load()
    }
  }
}
</script>

<style scoped>

</style>