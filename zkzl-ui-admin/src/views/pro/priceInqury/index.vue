<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">

      <el-form-item label="公司名称" prop="buyerCompanyName">
        <el-input v-model="queryParams.buyerCompanyName" placeholder="请输入公司名称" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
     
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable size="small">
         <el-option v-for="dict in inqueryStatusDict" :key="parseInt(dict.value)" :label="dict.label" :value="dict.value"/>
        
        </el-select>
      </el-form-item>
     
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作工具栏 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                   v-hasPermi="['pro:price-inqury:create']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" :loading="exportLoading"
                   v-hasPermi="['pro:price-inqury:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list" border>
      <el-table-column label="序号" align="center" prop="id" />
      <el-table-column label="询价单号" align="center" prop="priceInquryId" />
      <el-table-column label="公司名称" align="center" prop="companyName" />
      <!-- <el-table-column label="卖方联系地址" align="center" prop="sellerCompanyAddress" /> -->
      <el-table-column label="联系人" align="center" prop="contactName" />
      <el-table-column label="联系电话" align="center" prop="mobile" />
      <el-table-column label="询价时间" align="center" prop="createTime" />
<!-- 
      <el-table-column label="客户id" align="center" prop="buyerCompanyId" />
      <el-table-column label="总价格" align="center" prop="price" />
      <el-table-column label="折扣" align="center" prop="discount" />
      <el-table-column label="合计" align="center" prop="total" />
      <el-table-column label="报价日期" align="center" prop="priceDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.priceDate) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="报价有效日期" align="center" prop="effectiveDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.effectiveDate) }}</span>
        </template>
      </el-table-column> -->
      <el-table-column label="状态" align="center" prop="status" >
         <template slot-scope="scope">
          <dict-tag :type="DICT_TYPE.PRICE_INQUERY_STATUS" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <!-- <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column> -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['pro:price-inqury:update']">修改</el-button>
          <!-- <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['pro:price-inqury:delete']">删除</el-button> -->
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="1200px" v-dialogDrag append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

        <el-row >
          <el-col :span="8">
            <el-form-item label="询价单号"  prop="priceInquryId">
           <span>{{form.priceInquryId}}</span>
          </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="报价日期"  prop="priceDate">
          <span>{{form.priceDate}}</span>
        </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="报价有效日期" prop="effectiveDate"  label-width="120px">
          <el-date-picker clearable v-model="form.effectiveDate" type="date" value-format="yyyy-MM-dd" placeholder="选择报价有效日期" />
        </el-form-item>
          </el-col>
          
          
         
        </el-row>
        <el-row >
          <el-col :span="8">
            <div class="sub-title">卖方信息</div>
             <el-form-item label="公司名称" prop="sellerCompanyName">
              <span>{{form.sellerCompanyName}}</span>
              <!-- <el-input v-model="form.sellerCompanyName" placeholder="请输入卖方公司名称" /> -->
            </el-form-item>
            <el-form-item label="联系地址" prop="sellerCompanyAddress">
              <span>{{form.sellerCompanyAddress}}</span>
              <!-- <el-input v-model="form.sellerCompanyAddress" placeholder="请输入卖方联系地址" /> -->
            </el-form-item>
            <el-form-item label="联系人" prop="sellerContact">
              <span>{{form.sellerContact}}</span>
              <!-- <el-input v-model="form.sellerContact" placeholder="请输入卖方联系人" /> -->
            </el-form-item>
            <el-form-item label="联系电话" prop="sellerTel">
              <span>{{form.sellerTel}}</span>
              <!-- <el-input v-model="form.sellerTel" placeholder="请输入卖方联系电话" /> -->
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <div class="sub-title">买方信息</div>
             <el-form-item label="公司名称" prop="buyerCompanyName">
              <span>{{form.buyerCompanyName}}</span>
              <!-- <el-input v-model="form.sellerCompanyName" placeholder="请输入卖方公司名称" /> -->
            </el-form-item>
            <el-form-item label="联系地址" prop="buyerAddress">
              <span>{{form.buyerAddress}}</span>
              <!-- <el-input v-model="form.sellerCompanyAddress" placeholder="请输入卖方联系地址" /> -->
            </el-form-item>
            <el-form-item label="联系人" prop="buyerContactName">
              <span>{{form.buyerContactName}}</span>
              <!-- <el-input v-model="form.sellerContact" placeholder="请输入卖方联系人" /> -->
            </el-form-item>
            <el-form-item label="联系电话" prop="buyerMobile">
              <span>{{form.buyerMobile}}</span>
              <!-- <el-input v-model="form.sellerTel" placeholder="请输入卖方联系电话" /> -->
            </el-form-item>
          </el-col>
        </el-row >
        <el-table  :data="form.childs">
      <el-table-column label="编号" align="center" prop="productId" />
      <el-table-column label="HS编号" align="center" prop="hsSerial" />
      <el-table-column label="货描" align="center" prop="desc" />
      <el-table-column label="尺寸" align="center" prop="productSize" />
      <el-table-column label="颜色" align="center" prop="productColor" />
      <el-table-column label="克重" align="center" prop="productG" />
      <el-table-column label="包装方式" align="center" prop="packageWay" />
      <el-table-column label="长" align="center" prop="boxLength" />
      <el-table-column label="宽" align="center" prop="boxWide" />
      <el-table-column label="高" align="center" prop="boxHeight" />
      <el-table-column label="数量" align="center" prop="mount" />
      <el-table-column label="单价" align="center" prop="unitPrice " >
        <template slot-scope="scope">
          <el-input v-model="scope.row.unitPrice " type="number" @input="priceInput(scope.row)" placeholder="请输入价格" />
        </template>
      </el-table-column>
      <el-table-column label="供应商" align="center" prop="supplyName" />
      <el-table-column label="小计" align="center" prop="price" />
      <el-table-column label="体积" align="center" prop="volume" />
      <el-table-column label="毛重" align="center" prop="grossWeight" />
      <el-table-column label="净重" align="center" prop="netWeight" />
      <!-- <el-table-column label="交货方式" align="center" prop="deliveryMethodCn" /> -->

    </el-table>


        <el-form-item label="总价格" prop="price">
          <span>{{form.price}}</span>
          <!-- <el-input v-model="form.price" placeholder="请输入总价格" /> -->
        </el-form-item>
        <el-form-item label="折扣" prop="discount">
          <el-input v-model="form.discount" type="number" @input="calcDiscountPrice" placeholder="请输入折扣" />
        </el-form-item>
        <el-form-item label="合计" prop="total">
          <span>{{form.total}}</span>
          <!-- <el-input v-model="form.total" placeholder="请输入合计" /> -->
        </el-form-item>
        <el-form-item label="买方理想报价" prop="buyerIdealPrice">
          <span>{{form.buyerIdealPrice}}</span>
          <!-- <el-date-picker clearable v-model="form.priceDate" type="date" value-format="timestamp" placeholder="选择报价日期" /> -->
        </el-form-item>
       
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="danger" @click="reject">拒 绝</el-button>
        <el-button type="success" @click="success">成 交</el-button>
        <el-button type="primary" @click="confirm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

        <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="commonOpen" width="1200px" v-dialogDrag append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

        <el-row >
          <el-col :span="8">
            <el-form-item label="询价单号"  prop="priceInquryId">
           <span>{{form.priceInquryId}}</span>
          </el-form-item>
          </el-col>
          <el-col :span="8">
            <!-- <el-form-item label="报价日期"  prop="priceDate">
          <span>{{form.priceDate}}</span>
        </el-form-item> -->
          </el-col>
          <el-col :span="8">
            <!-- <el-form-item label="报价有效日期" prop="effectiveDate"  label-width="120px">
          <el-date-picker clearable v-model="form.effectiveDate" type="date" value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择报价有效日期" />
        </el-form-item> -->
          </el-col>
          
          
         
        </el-row>
        <el-row >
          <el-col :span="8">
            <div class="sub-title">卖方信息</div>
             <el-form-item label="公司名称" prop="sellerCompanyName">
              
              <el-input v-model="form.sellerCompanyName" placeholder="请输入卖方公司名称" />
            </el-form-item>
            <el-form-item label="联系地址" prop="sellerCompanyAddress">

              <el-input v-model="form.sellerCompanyAddress" placeholder="请输入卖方联系地址" />
            </el-form-item>
            <el-form-item label="联系人" prop="sellerContact">

              <el-input v-model="form.sellerContact" placeholder="请输入卖方联系人" />
            </el-form-item>
            <el-form-item label="联系电话" prop="sellerTel">

              <el-input v-model="form.sellerTel" placeholder="请输入卖方联系电话" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <div class="sub-title">买方信息</div>
             <el-form-item label="公司名称" prop="buyerCompanyName">
              <span>{{form.buyerCompanyName}}</span>
              <!-- <el-input v-model="form.sellerCompanyName" placeholder="请输入卖方公司名称" /> -->
            </el-form-item>
            <el-form-item label="联系地址" prop="buyerAddress">
              <span>{{form.buyerAddress}}</span>
              <!-- <el-input v-model="form.sellerCompanyAddress" placeholder="请输入卖方联系地址" /> -->
            </el-form-item>
            <el-form-item label="联系人" prop="buyerContactName">
              <span>{{form.buyerContactName}}</span>
              <!-- <el-input v-model="form.sellerContact" placeholder="请输入卖方联系人" /> -->
            </el-form-item>
            <el-form-item label="联系电话" prop="buyerMobile">
              <span>{{form.buyerMobile}}</span>
              <!-- <el-input v-model="form.sellerTel" placeholder="请输入卖方联系电话" /> -->
            </el-form-item>
          </el-col>
        </el-row >
        <el-table  :data="form.childs">
      <el-table-column label="编号" align="center" prop="productId" />
      <el-table-column label="HS编号" align="center" prop="hsSerial" />
      <el-table-column label="货描" align="center" prop="desc" />
      <el-table-column label="尺寸" align="center" prop="productSize" />
      <el-table-column label="颜色" align="center" prop="productColor" />
      <el-table-column label="克重" align="center" prop="productG" />
      <el-table-column label="包装方式" align="center" prop="packageWay" />
      <el-table-column label="长" align="center" prop="boxLength" />
      <el-table-column label="宽" align="center" prop="boxWide" />
      <el-table-column label="高" align="center" prop="boxHeight" />
      <el-table-column label="数量" align="center" prop="mount" />
      <el-table-column label="单价" align="center" prop="unitPrice " >
        <!-- <template slot-scope="scope">
          <el-input v-model="scope.row.unitPrice " type="number" @input="priceInput(scope.row)" placeholder="请输入价格" />
        </template> -->
      </el-table-column>
      <el-table-column label="供应商" align="center" prop="supplyName" />
      <el-table-column label="小计" align="center" prop="price" />
      <el-table-column label="体积" align="center" prop="volume" />
      <el-table-column label="毛重" align="center" prop="grossWeight" />
      <el-table-column label="净重" align="center" prop="netWeight" />
      <!-- <el-table-column label="交货方式" align="center" prop="deliveryMethodCn" /> -->

    </el-table>


       
      </el-form>
      <div slot="footer" class="dialog-footer">

        <el-button type="primary" @click="confirm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { createPriceInqury, updatePriceInqury, deletePriceInqury, getPriceInqury, getPriceInquryPage, exportPriceInquryExcel,pageManage,pageCommon,
update } from "@/api/pro/priceInqury";
import { mapGetters } from 'vuex'
import {DICT_TYPE, getDictDatas} from "@/utils/dict";
export default {
  name: "PriceInqury",
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 询价列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      commonOpen:false,
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        priceInquryId: null,
        sellerCompanyName: null,
        sellerCompanyAddress: null,
        sellerContact: null,
        sellerTel: null,
        buyerCompanyId: null,
        price: null,
        discount: null,
        total: null,
        priceDate: [],
        effectiveDate: [],
        status: null,
        createTime: [],
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        // priceInquryId: [{ required: true, message: "询价表id不能为空", trigger: "blur" }],
        // sellerCompanyName: [{ required: true, message: "卖方公司名称不能为空", trigger: "blur" }],
        // sellerCompanyAddress: [{ required: true, message: "卖方联系地址不能为空", trigger: "blur" }],
        // sellerContact: [{ required: true, message: "卖方联系人不能为空", trigger: "blur" }],
        // sellerTel: [{ required: true, message: "卖方联系电话不能为空", trigger: "blur" }],
        // buyerCompanyId: [{ required: true, message: "客户id不能为空", trigger: "blur" }],
        // priceDate: [{ required: true, message: "报价日期不能为空", trigger: "blur" }],
        // effectiveDate: [{ required: true, message: "报价有效日期不能为空", trigger: "blur" }],
        unitPrice: [{ required: true, message: "单价不能为空", trigger: "blur" }],
      },
      isAdmin:false,
      inqueryStatusDict: getDictDatas(DICT_TYPE.PRICE_INQUERY_STATUS),
    };
  },
  computed: {
    ...mapGetters([
      'roles',
    ]),
  },
  created() {
  this.isAdmin = this.roles.findIndex(ele=>ele === 'super_admin')!=-1

    this.getList();
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 执行查询
      if(this.isAdmin){
        pageManage(this.queryParams).then(response => {
                this.list = response.data.list;
                this.total = response.data.total;
                this.loading = false;
              });
      }else{
        pageCommon(this.queryParams).then(response => {
                this.list = response.data.list;
                this.total = response.data.total;
                this.loading = false;
              });
      }
      
    },
    priceInput(row){
      console.log("priceInput",row)
      if(row.unitPrice){
        row.price = (parseFloat(row.unitPrice)*parseFloat(row.mount)).toFixed(2)
      }else{
        row.price = 0
      }
     this.form.price =  this.sumChildPrice()
      this.calcDiscountPrice()
    },
    sumChildPrice(){
      var price = 0
      this.form.childs.forEach(element => {
        if(element.unitPrice){
        price = (parseFloat(price)+ parseFloat(element.unitPrice)*parseFloat(element.mount)).toFixed(2)
      }else{
        price = price+ 0
      }
      });
      return price
    },
    calcDiscountPrice(){
      if(this.form.discount && this.form.price){
        this.form.total = (parseFloat(this.form.price)*parseFloat(this.form.discount) / parseFloat(100)).toFixed(2)
      }else{
        this.form.total = this.form.price
      }
    },
    confirm(){
      console.log('confir,',this.isAdmin)
      if(this.isAdmin){
        if(!this.form.effectiveDate){
          this.$modal.msgError("报价有效日期不能为空");
          return
        }
        this.form.priceInquryChilds = this.form.childs
        this.form.status = '2'
        console.log('update',this.form)
        update( this.form).then(result=>{
        this.open = false
        this.getList()
        })
      }else{
        this.form.status = '1'
         update( this.form).then(result=>{
        this.commonOpen = false
        this.getList()
        })
      }
    },
    success(){
      if(!this.form.effectiveDate){
          this.$modal.msgError("报价有效日期不能为空");
          return
        }
        this.form.priceInquryChilds = this.form.childs
        this.form.status = '3'
        console.log('update',this.form)
        update( this.form).then(result=>{
        this.open = false
        
        this.getList()
        })
    },
    reject(){
      update( {id:this.form.id,status:'4'}).then(result=>{
        this.open = false
        this.getList()
        })
    },
    /** 取消按钮 */
    cancel() {
      this.open = false;
      this.reset();
    },
    /** 表单重置 */
    reset() {
      this.form = {
        id: undefined,
        priceInquryId: undefined,
        sellerCompanyName: undefined,
        sellerCompanyAddress: undefined,
        sellerContact: undefined,
        sellerTel: undefined,
        buyerCompanyId: undefined,
        price: undefined,
        discount: undefined,
        total: undefined,
        priceDate: undefined,
        effectiveDate: undefined,
        status: undefined,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNo = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加询价";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getPriceInqury({id}).then(response => {
        this.form = response.data;
        if(this.isAdmin){
          this.open = true;
        }else{
          this.commonOpen = true
        }
        
        this.title = "修改询价";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (!valid) {
          return;
        }
        // 修改的提交
        if (this.form.id != null) {
          updatePriceInqury(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createPriceInqury(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除询价编号为"' + id + '"的数据项?').then(function() {
          return deletePriceInqury(id);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      // 处理查询参数
      let params = {...this.queryParams};
      params.pageNo = undefined;
      params.pageSize = undefined;
      this.$modal.confirm('是否确认导出所有询价数据项?').then(() => {
          this.exportLoading = true;
          return exportPriceInquryExcel(params);
        }).then(response => {
          this.$download.excel(response, '询价.xls');
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
<style lang="scss" scoped>
.hor{
  display: flex;
  justify-content: space-between;
}
.sub-title{
      font-size: 18px;
    color: #303133;
    margin-bottom: 22px;
}
</style>
