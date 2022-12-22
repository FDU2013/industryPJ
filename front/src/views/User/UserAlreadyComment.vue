<template>
  <div>
    <div>
      <div>
        <el-table :data="tableData" style="width: 80%;margin:0 auto" border stripe>
          <el-table-column prop="orderID" label="订单id" min-width="10%"/>
          <el-table-column prop="purchaseID" label="购买id" min-width="10%" v-if="false"/>
          <el-table-column prop="goodID" label="商品id" min-width="10%" />
          <el-table-column prop="goodName" label="商品名" min-width="20%" />
          <el-table-column prop="url" label="图片" min-width="20%">
            <template #default="scope">
              <div class="demo-image__preview">
                <el-image
                    style="width: 60px; height: 60px;"
                    :src="scope.row.url"
                    :zoom-rate="1.2"
                    :preview-src-list="[scope.row.url]"
                    hide-on-click-modal="true"
                    fit="cover"
                    preview-teleported
                    z-index="10000"
                />
              </div>
            </template>
          </el-table-column>
          <el-table-column prop="content" label="内容" min-width="30%"/>
          <el-table-column prop="stars" label="星级" min-width="20%"/>
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
  name: "UserAlreadyComment",
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
      commentInfo:{
        purchaseID:'',
        stars:'',
        content:''
      },
      addressOptions:[],
      tableData2:[],
    }
  },
  mounted() {
    this.load()
  },
  methods:{
    load(){
      setTimeout(() => {
        request.post("/shop/getHaveCommented",{
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
    handleComment:function(purchaseID){
      this.dialogVisible = true
      this.commentInfo = {}
      this.commentInfo.purchaseID = purchaseID
    },
    handleCurrentChange:function (pageNum){
      this.currentPage = pageNum
      this.load()
    }
  }
}
</script>

<style scoped>
.add{
  text-align: left;
}
</style>