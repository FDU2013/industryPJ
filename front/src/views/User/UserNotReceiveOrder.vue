<template>
  <div>
    <div>
      <div>
        <el-table :data="tableData" style="width: 80%;margin:0 auto" border stripe>
          <el-table-column prop="id" label="订单id" min-width="10%"/>
          <el-table-column prop="buyerId" label="购买人id" min-width="10%" v-if="false"/>
          <el-table-column prop="totalPrice" label="总价" min-width="10%" />
          <el-table-column prop="status" label="状态" min-width="10%" />
          <el-table-column prop="address" label="地址" min-width="10%"/>
          <el-table-column prop="notes" label="备注" min-width="10%"/>
          <el-table-column prop="waybillNum" label="运单号" min-width="10%" />
          <el-table-column fixed="right" label="操作" min-width="10%">
            <template #default="scope">
              <el-popconfirm title="确认收货?" @confirm="receive(scope.row.id)">
                <template #reference>
                  <el-button type="text">收货</el-button>
                </template>
              </el-popconfirm>
              <el-button type="text" size="small" @click="viewDetails(scope.row.id)">查看订单详情</el-button>
            </template>
          </el-table-column>
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
    <div>
      <el-dialog v-model="dialogVisible" title="确认支付信息" width="50%">
        <el-form :model="payInfo" label-width="">
          <el-form-item label="备注">
            <el-input v-model="payInfo.notes" />
          </el-form-item>

          <el-form-item label="地址选择" prop="">
            <el-select v-model="payInfo.address" class="m-2" placeholder="请选择" size="large" style="width: 100%">
              <el-option
                  v-for="item in addressOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
              />
            </el-select>
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

    <div>
      <el-dialog v-model="dialogVisible2" title="查看订单详情" width="50%">
        <el-table :data="tableData2" style="width: 100%" border stripe>
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
          <el-table-column prop="price" label="总价格" min-width="10%"/>
          <el-table-column prop="status" label="状态" min-width="10%" v-if="false"/>
          <el-table-column prop="num" label="数量" min-width="10%"/>
        </el-table>
      </el-dialog>
    </div>

  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "UserNotReceiveOrder",
  data(){
    return{
      total:0,
      pageSize:10,
      currentPage:1,
      search:'',
      dialogVisible:false,
      dialogVisible2:false,
      introduction:'',
      tableData:[],
      tableData2:[]
    }
  },
  mounted() {
    this.load()
  },
  methods:{
    load(){
      setTimeout(() => {
        request.post("user/getNotReceivedOrder",{
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
    handleCurrentChange:function (pageNum){
      this.currentPage = pageNum
      this.load()
    },
    viewDetails:function (id){
      request.post("user/orderDetail",id).then(res=>{
        this.tableData2 = res.data.data
      })
      this.dialogVisible2 = true
    },
    receive:function (id){
      request.post("user/confirmReceive",id).then(res=>{
        if(res.data.code === 200){
          this.$message({
            type:"success",
            message:res.data.msg
          })
        }
        else{
          this.$message({
            type:"error",
            message:res.data.msg
          })
        }
      })
    }
  }
}
</script>

<style scoped>
.add{
  text-align: left;
}
</style>