<template>
  <div>
    <div>
      <div class="add" style="margin: 10px 0">
        <div style="display: flex;flex-direction: row;width: 100%">

          <div>
            <el-button size="large" @click="add" type="primary">新增</el-button>
          </div>

          <div style="width: 100%">

            <div style="margin-left: 10%">
              <el-checkbox-group v-model="mediumList">
                <el-checkbox v-for="item in mediumOptions" :label="item.label"></el-checkbox>
              </el-checkbox-group>
            </div>

            <div style="margin-left: 10%">
              <el-checkbox-group v-model="tagList">
                <el-checkbox v-for="item in tagOptions" :label="item.label"></el-checkbox>
              </el-checkbox-group>
            </div>

            <div>
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
              <el-button type="text" size="small" @click="handleEdit(
                  scope.row.goodsId,
                  scope.row.name,
                  scope.row.author,
                  scope.row.imageUrl,
                  scope.row.details,
                  scope.row.price
                  )">编辑</el-button>
              <!--              <el-popconfirm title="确认删除?" @confirm="handleDelete(scope.row.name)">-->
              <!--                <template #reference>-->
              <!--                  <el-button type="text">删除</el-button>-->
              <!--                </template>-->
              <!--              </el-popconfirm>-->
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div style="margin: 10px 0">
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
      <el-dialog v-model="dialogVisible" title="添加新商品" width="30%">
        <el-form :model="addGoods" label-width="120px">
          <el-form-item label="商品id">
            <el-input v-model="addGoods.goodsId" />
          </el-form-item>
          <el-form-item label="商品名称">
            <el-input v-model="addGoods.name" />
          </el-form-item>
          <el-form-item label="作者">
            <el-input v-model="addGoods.author" />
          </el-form-item>

          <el-form-item label="出版社选择" prop="">
            <el-select v-model="addGoods.publisher" class="m-2" placeholder="请选择" size="small">
              <el-option
                  v-for="item in publisherOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
              />
            </el-select>
          </el-form-item>

          <el-form-item label="媒介选择" prop="">
            <el-select v-model="addGoods.medium" class="m-2" placeholder="请选择" size="small">
              <el-option
                  v-for="item in mediumOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
              />
            </el-select>
          </el-form-item>

          <el-form-item label="标签选择" prop="">
            <el-select v-model="addGoods.tag" class="m-2" placeholder="请选择" size="small">
              <el-option
                  v-for="item in tagOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
              />
            </el-select>
          </el-form-item>

          <el-form-item label="图片">
            <el-input v-model="addGoods.imageUrl" />
          </el-form-item>
          <el-form-item label="细节">
            <el-input v-model="addGoods.details" />
          </el-form-item>
          <el-form-item label="价格">
            <el-input v-model="addGoods.price" />
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
      <el-dialog v-model="dialogVisible2" title="编辑商品信息" width="30%">
        <el-form :model="newGoods" label-width="120px">
          <el-form-item label="商品id">
            <el-input v-model="newGoods.goodsId" disabled/>
          </el-form-item>
          <el-form-item label="商品名称">
            <el-input v-model="newGoods.name" />
          </el-form-item>
          <el-form-item label="作者">
            <el-input v-model="newGoods.author" />
          </el-form-item>

          <el-form-item label="出版社选择" prop="">
            <el-select v-model="newGoods.publisher" class="m-2" placeholder="请选择" size="small">
              <el-option
                  v-for="item in publisherOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
              />
            </el-select>
          </el-form-item>

          <el-form-item label="媒介选择" prop="">
            <el-select v-model="newGoods.medium" class="m-2" placeholder="请选择" size="small">
              <el-option
                  v-for="item in mediumOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
              />
            </el-select>
          </el-form-item>

          <el-form-item label="标签选择" prop="">
            <el-select v-model="newGoods.tag" class="m-2" placeholder="请选择" size="small">
              <el-option
                  v-for="item in tagOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
              />
            </el-select>
          </el-form-item>

          <el-form-item label="图片">
            <el-input v-model="newGoods.imageUrl" />
          </el-form-item>
          <el-form-item label="细节">
            <el-input v-model="newGoods.details" />
          </el-form-item>
          <el-form-item label="价格">
            <el-input v-model="newGoods.price" />
          </el-form-item>

          <el-form-item label="状态" prop="">
            <el-select v-model="newGoods.status" class="m-2" placeholder="请选择" size="small">
              <el-option
                  v-for="item in statusOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
              />
            </el-select>
          </el-form-item>

          <span class="dialog-footer">
        <el-button @click="dialogVisible2 = false">取消</el-button>
        <el-button type="primary" @click="saveEdit">确认</el-button>
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
  name: "AdminGoodsSoldOut",
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
      addGoods:{
        goodsId:'',
        name:'',
        author:'',
        publisher:'',
        medium:'',
        tag:'',
        imageUrl:'',
        details:'',
        price:''
      },
      newGoods:{
        goodsId:'',
        name:'',
        author:'',
        publisher:'',
        medium:'',
        tag:'',
        imageUrl:'',
        details:'',
        price:'',
        status:''
      },
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
    this.getPublisherOption()
    this.getMediumOption()
    this.getTagOption()
  },
  methods:{
    getPublisherOption: function () {
      request.post("/shop/getAllPublisherName").then(res => {
        console.log(res.data.data)
        let that = this
        if (!res.data) return
        res.data.data.forEach (function (item) {
          let option = {value: item, label: item}
          that.publisherOptions.push(option)
        })
      })
    },
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
    load(){
      setTimeout(() => {
        request.post("/shop/adminSearchSoldOutGood",{
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
    add:function (){
      this.dialogVisible=true
      this.addPublisher={}
    },
    save:function (){
      request.post("/shop/addGood", this.addGoods).then(res => {
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
    saveEdit(){

      request.post("/shop/updateGood", this.newGoods).then(res=>{
        if(res.data.code!==200) {
          this.$message({
            type:"error",
            message: res.data.msg
          })
        }
        else{
          this.$message({
            type:"success",
            message: res.data.msg
          })
        }
        this.load()
        this.dialogVisible2=false
      })
    },
    handleEdit(goodsId,name,author,imageUrl,details,price){
      this.dialogVisible2 = true
      this.newGoods.goodsId = goodsId
      this.newGoods.name = name
      this.newGoods.author = author
      this.newGoods.imageUrl = imageUrl
      this.newGoods.details = details
      this.newGoods.price = price
    },
    handleDelete(name) {
      request.post("/shop/deletePublisher",name).then(res => {
        if(res.data.code!==200) {
          this.$message({
            type:"error",
            message: res.data.msg
          })
        }
        this.load()  // 删除之后重新加载表格的数据
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