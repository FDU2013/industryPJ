<template>
  <div>
    <div>
      <div>
        <el-table :data="tableData" style="width: 80%;margin: 0 auto" border stripe>
          <el-table-column prop="goodsId" label="商品id" min-width="10%"/>
          <el-table-column prop="name" label="商品名" min-width="10%"/>
          <el-table-column prop="author" label="作者" min-width="10%"/>
          <el-table-column prop="publisher" label="出版社" min-width="10%"/>
          <el-table-column prop="medium" label="媒介" min-width="10%"/>
          <el-table-column prop="tag" label="标签" min-width="10%"/>

          <el-table-column  label="商品图片" prop="imageUrl" min-width="15%">
            <template #default="scope">
              <div class="demo-image__preview">
                <el-image
                    style="width: 60px; height: 60px;"
                    :src="scope.row.imageUrl"
                    :zoom-rate="1.2"
                    :preview-src-list="[scope.row.imageUrl]"
                    hide-on-click-modal="true"
                    fit="cover"
                    preview-teleported
                    z-index="10000"
                />
              </div>
            </template>
          </el-table-column>


          <el-table-column prop="details" label="细节" min-width="10%"/>
          <el-table-column prop="price" label="单价" min-width="10%"/>
          <el-table-column prop="num" label="数量" min-width="15%">
            <template #default = "scope">
              <el-input-number v-model="scope.row.num" size="small" style="width: 70%" min="1" @change="handleChange(scope.row.goodsId,scope.row.num)" />
            </template>
          </el-table-column>

          <el-table-column fixed="right" label="操作" min-width="10%">
            <template #default="scope">
              <el-popconfirm title="确认删除?" @confirm="handleDelete(scope.row.goodsId)">
                <template #reference>
                  <el-button type="text">删除</el-button>
                </template>
              </el-popconfirm>
            </template>
          </el-table-column>

        </el-table>
      </div>

      <div style="display: flex;flex-direction: row;position: absolute;right: 15%;margin-top: 10px">
        <div style="font-size:large;font-weight: bold;margin: 0 20px 0 0 ">
          总价：{{this.totalPrice}}
        </div>
        <el-button type="primary" size="large" @click="buyShoplist()">结算</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "UserBuyList",
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
      tableData:[],
      totalPrice:''
    }
  },
  mounted() {
    this.load()
  },
  methods:{
    load(){
      setTimeout(() => {
        request.post("/user/getShoplist",).then(res=>{

          if(res.data.code===200){
            this.tableData=res.data.data.records
            this.totalPrice = res.data.data.totalPrice
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
    handleChange(goodId,num){
      request.post("/user/updateShoplist",{
        goodID:goodId,
        num:num
      }).then(this.load)
    },
    handleDelete(goodsId){
      request.post("/user/removeFromShoplist", goodsId).then(res=>{
        this.load()
        if(res.data.code === 200){
          this.$message({
            type:"success",
            message:res.data.message
          })
        }
        else{
          this.$message({
            type:"error",
            message:res.data.message
          })
        }
      })
    },
    buyShoplist(){
      request.post("/user/buyShoplist",).then(res=>{
        this.load()
        if(res.data.code===200||res.data.code === 310){
          this.$message({
            message: res.data.msg
          })
        }
        else if(res.data.code === 201){
          this.$message({
            message: res.data.msg + res.data.data
          })
        }
        else{
          this.$message({
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