<template>
  <div>
    <div>
      <div>
        <el-table :data="tableData" style="width: 80%;margin:0 auto" border stripe>
          <el-table-column prop="orderID" label="订单id" min-width="20%"/>
          <el-table-column prop="purchaseID" label="购买id" min-width="20%" v-if="false"/>
          <el-table-column prop="goodID" label="商品id" min-width="20%" />
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
          <el-table-column prop="content" label="内容" min-width="20%" v-if="false"/>
          <el-table-column prop="stars" label="星级" min-width="20%" v-if="false"/>
          <el-table-column fixed="right" label="操作" min-width="20%">
            <template #default="scope">
              <el-button type="text" size="small" @click="handleComment(scope.row.purchaseID)">评价</el-button>
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
      <el-dialog v-model="dialogVisible" title="评价商品" width="50%">
        <el-form :model="commentInfo" label-width="">

          <el-form-item label="选择星级" prop="">
            <el-select v-model="commentInfo.stars" class="m-2" placeholder="请选择" size="large" style="width: 100%">
              <el-option
                  v-for="item in starOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
              />
            </el-select>
          </el-form-item>

          <el-form-item label="评价商品">
            <el-input
                v-model="commentInfo.content"
                :rows="5"
                type="textarea"
                placeholder="Please input"
            />
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

  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "UserNeedComment",
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
      starOptions:[{value:'1',label:'1'},{value:'2',label:'2'},{value:'3',label:'3'},{value:'4',label:'4'},{value:'5',label:'5'}],
    }
  },
  mounted() {
    this.load()
  },
  methods:{
    load(){
      setTimeout(() => {
        request.post("/shop/getNeedComment",{
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
    save:function (){
      request.post("/shop/toComment", this.commentInfo).then(res => {
        if(res.data.code!==200) {
          this.$message({
            type:"error",
            message: res.data.msg
          })
        }
        if(res.data.code===200) {
          this.$message({
            type:"success",
            message: res.data.msg
          })
        }
        this.load() // 刷新表格的数据
        this.dialogVisible = false  // 关闭弹窗
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
.add{
  text-align: left;
}
</style>