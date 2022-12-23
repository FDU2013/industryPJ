<template>
  <div style="width: 70%;margin: 0 auto">
    <div>
      <div class="add" style="margin: 10px 0">
        <div style="display: flex;flex-direction: row;width: 100%">

          <div style="width: 100%;margin: 0 auto">

            <div style="margin-left: 10%">
              媒介选择：
              <el-checkbox-group v-model="mediumList">
                <el-checkbox v-for="item in mediumOptions" :label="item.label"></el-checkbox>
              </el-checkbox-group>
            </div>

            <div style="margin-left: 10%">
              标签选择：
              <el-checkbox-group v-model="tagList">
                <el-checkbox v-for="item in tagOptions" :label="item.label"></el-checkbox>
              </el-checkbox-group>
            </div>

            <div style="width: 120%;">
              <el-input clearable v-model="search" placeholder="请输入关键字" style="width:50%;margin-left: 100px"></el-input>
              <el-button type="primary" style="margin-left: 5px" @click="load">搜索</el-button>
            </div>

          </div>
        </div>

      </div>
      <div>
        <el-table :data="tableData" style="width: 100%" border stripe>
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
          <el-table-column prop="price" label="价格" min-width="10%"/>
          <el-table-column fixed="right" label="操作" min-width="10%">
            <template #default="scope">
              <el-button type="text" size="small" @click="goodsDetail(
                  scope.row.goodsId,
                  scope.row.name,
                  scope.row.author,
                  scope.row.publisher,
                  scope.row.medium,
                  scope.row.tag,
                  scope.row.imageUrl,
                  scope.row.details,
                  scope.row.price
                  )">查看详情</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div style="margin: 10px auto">
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
  name: "UserShop",
  data(){
    return{
      total:0,
      pageSize:10,
      currentPage:1,
      search:'',
      oldName:'',
      newName:'',
      introduction:'',
      tableData:[],
      publisherOptions:[],
      tagOptions:[],
      mediumOptions:[],
      statusOptions:[{value:'ForSale',label:'ForSale'},{value:'SoldOut',label:'SoldOut'}],
      mediumList:[],
      tagList:[]
    }
  },
  mounted() {
    this.load()
    this.getTagOption()
    this.getMediumOption()
  },
  methods:{
    getTagOption: function () {
      request.post("/shop/getAllGoodsTagName").then(res => {
        let that = this
        if (!res.data) return
        res.data.data.forEach (function (item) {
          let option = {value: item, label: item}
          that.tagOptions.push(option)
        })
      })
    },
    getMediumOption: function () {
      request.post("/shop/getAllMediumName").then(res => {
        let that = this
        if (!res.data) return
        res.data.data.forEach (function (item) {
          let option = {value: item, label: item}
          that.mediumOptions.push(option)
        })
      })
    },
    goodsDetail:function (goodsId, name, author, publisher, medium, tag, imageUrl, details, price){
      let pathInfo = this.$router.resolve({
        path:'/user/goodsdetail',
        query:{
          goodsId:goodsId,
          name:name,
          author:author,
          publisher:publisher,
          medium:medium,
          tag:tag,
          imageUrl:imageUrl,
          details:details,
          price:price
        }
      })
      window.open(pathInfo.href, '_blank');

    },
    load(){
      setTimeout(() => {
        request.post("/shop/adminSearchForSaleGood",{
              medium: this.mediumList,
              tag : this.tagList,
              pageNum: this.currentPage,
              pageSize: this.pageSize,
              search: this.search
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
    }
  }
}
</script>

<style scoped>
.add{
  text-align: left;
}
.demo-image__error .image-slot {
  font-size: 30px;
}
.demo-image__error .image-slot .el-icon {
  font-size: 30px;
}
.demo-image__error .el-image {
  width: 100%;
  height: 200px;
}
</style>