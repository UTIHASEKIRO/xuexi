<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="供货商" prop="supplyCompany">
        <el-input v-model="queryParams.supplyCompany" placeholder="请输入供货商" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <!-- <el-form-item label="产品编号" prop="productSerial">
        <el-input v-model="queryParams.productSerial" placeholder="请输入产品编号" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="HS编号" prop="hsSerial">
        <el-input v-model="queryParams.hsSerial" placeholder="请输入HS编号" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="产品-尺寸" prop="productSize">
        <el-input v-model="queryParams.productSize" placeholder="请输入产品-尺寸" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="产品-颜色" prop="productColor">
        <el-input v-model="queryParams.productColor" placeholder="请输入产品-颜色" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="产品-克重" prop="productG">
        <el-input v-model="queryParams.productG" placeholder="请输入产品-克重" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="箱规-长" prop="boxLength">
        <el-input v-model="queryParams.boxLength" placeholder="请输入箱规-长" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="箱规-宽" prop="boxWide">
        <el-input v-model="queryParams.boxWide" placeholder="请输入箱规-宽" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="箱规-高" prop="boxHeight">
        <el-input v-model="queryParams.boxHeight" placeholder="请输入箱规-高" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="单价" prop="unitPrice">
        <el-input v-model="queryParams.unitPrice" placeholder="请输入单价" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="毛重" prop="grossWeight">
        <el-input v-model="queryParams.grossWeight" placeholder="请输入毛重" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="净重" prop="netWeight">
        <el-input v-model="queryParams.netWeight" placeholder="请输入净重" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker v-model="queryParams.createTime" style="width: 240px" value-format="yyyy-MM-dd HH:mm:ss" type="daterange"
                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" :default-time="['00:00:00', '23:59:59']" />
      </el-form-item> -->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作工具栏 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                   v-hasPermi="['pro:purchase-history:create']">新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
                   :loading="exportLoading"
                   v-hasPermi="['pro:purchase-history:export']">导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list" border>
      <el-table-column label="序号id" align="center" prop="id"/>
      <el-table-column label="供货单号" align="center" prop="purchaseHistoryId"/>
      <el-table-column label="供货商" align="center" prop="supplyCompany"/>

      <el-table-column label="产品编号" align="center" prop="productSerial"/>
      <el-table-column label="HS编号" align="center" prop="hsSerial"/>
      <el-table-column label="尺寸" align="center" prop="productSize"/>
      <el-table-column label="颜色" align="center" prop="productColor"/>
      <el-table-column label="克重" align="center" prop="productG"/>
      <el-table-column label="包装方式" align="center" prop="packingWay"/>
      <el-table-column label="长" align="center" prop="boxLength"/>
      <el-table-column label="宽" align="center" prop="boxWide"/>
      <el-table-column label="高" align="center" prop="boxHeight"/>

      <el-table-column label="数量" align="center" prop="mount"/>
      <el-table-column label="单价" align="center" prop="unitPrice"/>

      <el-table-column label="合计" align="center" prop="price"/>
      <el-table-column label="毛重" align="center" prop="grossWeight"/>
      <el-table-column label="净重" align="center" prop="netWeight"/>
      <!--      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
              <template slot-scope="scope">
                <span>{{ parseTime(scope.row.createTime) }}</span>
              </template>
            </el-table-column>-->
      <el-table-column label="备注" align="center" prop="remark"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['pro:purchase-history:update']">修改
          </el-button>
          <!-- <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['pro:purchase-history:delete']">删除</el-button> -->
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" v-dialogDrag append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="产品类别：" prop="typeId">
          <el-select v-model="form.typeId" placeholder="请选择产品类别" clearable size="small" style="width: 240px"
                     @change="getProductList">
            <el-option v-for="dict in duckTypeList" :key="dict.typeId" :label="dict.typeNameCn" :value="dict.typeId"/>
          </el-select>
        </el-form-item>
        <el-form-item label="产品" prop="product">
          <el-select v-model="form.productId" placeholder="请选择产品" clearable size="small" style="width: 240px" @change="getSupplyInfoPage(null,$event)">
            <el-option v-for="dict in productList" :key="dict.productId" :label="dict.productNameCn" :value="dict.productId"/>
          </el-select>
        </el-form-item>
        <el-form-item label="供货商" prop="supplyInfoId">
          <el-select v-model="form.supplyInfoId" placeholder="请选择供货商" clearable size="small" style="width: 240px" @change="setSupplyInfoValue">
            <el-option v-for="dict in supplyList" :key="parseInt(dict.id)" :label="dict.name" :value="dict.id"/>
          </el-select>
        </el-form-item>
        <el-form-item label="包装方式" prop="packingWay">
          <el-input v-model="form.packingWay" placeholder="请输入包装方式"/>
        </el-form-item>
        <el-form-item label="箱规-长" prop="boxLength">
          <el-input v-model="form.boxLength" placeholder="请输入箱规-长"/>
        </el-form-item>
        <el-form-item label="箱规-宽" prop="boxWide">
          <el-input v-model="form.boxWide" placeholder="请输入箱规-宽"/>
        </el-form-item>
        <el-form-item label="箱规-高" prop="boxHeight">
          <el-input v-model="form.boxHeight" placeholder="请输入箱规-高"/>
        </el-form-item>
        <el-form-item label="单价" prop="unitPrice">
          <el-input v-model="form.unitPrice" placeholder="请输入单价"/>
        </el-form-item>
        <el-form-item label="数量" prop="mount">
          <el-input v-model="form.mount" placeholder="请输入数量"/>
        </el-form-item>
        <el-form-item label="总价" prop="price">
          <el-input v-model="form.price" placeholder="请输入总价"/>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  createPurchaseHistory,
  deletePurchaseHistory,
  exportPurchaseHistoryExcel,
  getPurchaseHistory,
  getPurchaseHistoryPage,
  updatePurchaseHistory
} from "@/api/pro/purchaseHistory";
import {getSupplyInfoPage} from "@/api/pro/supplyInfo";
import {ductTypeList} from "@/api/pro/ductType";
import {getductList} from "@/api/pro/duct";

export default {
  name: "PurchaseHistory",
  components: {},
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
      // 供货商供应记录列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        purchaseHistoryId: null,
        productSerial: null,
        hsSerial: null,
        productSize: null,
        productColor: null,
        productG: null,
        boxLength: null,
        boxWide: null,
        boxHeight: null,
        unitPrice: null,
        grossWeight: null,
        netWeight: null,
        createTime: [],
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      duckTypeList:[],
      productList:[],
      supplyList: []
    };
  },
  created() {
    this.getList();
    this.getDuckTypePage()
    this.getProductList(null)
    this.getSupplyInfoPage(null, null)
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 执行查询
      getPurchaseHistoryPage(this.queryParams).then(response => {
        this.list = response.data.list;
        this.total = response.data.total;
        this.loading = false;
      });
    },
    /** 商品类别列表 */
    getDuckTypePage() {
      ductTypeList({typeName: ''}).then(result => {
        this.duckTypeList = result.data
      })
    },
    /** 商品列表 */
    getProductList(typeId) {
      getductList({typeId: typeId}).then(result => {
        this.productList = result.data
        this.form.productId = ''
      })
    },
    /**获取供货商 */
    getSupplyInfoPage(typeId, productId) {
     var temp =  this.productList.filter(ele=>ele.productId === productId);
      this.setProductValue(temp[0]);
      getSupplyInfoPage({typeId: typeId, productId: productId}).then(result => {
        this.supplyList = result.data.list
        this.form.supplyInfoId = undefined
        this.form.supplyCompany = undefined
      })
    },
    setProductValue(val){
      console.log("产品点击事件")
      console.log(val)
      if (val != null) {
        this.$set(this.form, "productSerial", val.productId);
        this.$set(this.form, "hsSerial", val.hsNo);
        this.$set(this.form, "productSize", val.size);
        this.$set(this.form, "productColor", val.colourCn);
        this.$set(this.form, "productG", val.gramWeight);
        this.$set(this.form, "grossWeight", val.grossWeight);
        this.$set(this.form, "netWeight", val.netWeight);
      }
    },
    setSupplyInfoValue(supplyInfoId){
      var val =  this.supplyList.filter(ele=>ele.id === supplyInfoId);
      if (val != null) {
        this.$set(this.form, "supplyInfoId", val[0].id);
        this.$set(this.form, "supplyCompany", val[0].name);
      }
    },
    /** 类别树选择 */
    handleNodeClick(val) {
      this.$set(this.form, "typeId", val.typeId);
      this.$set(this.form, "typeNameCn", val.typeNameCn);
      if (!val.productTypeDOS || val.productTypeDOS.length == 0) {
        this.$refs.selectReport.blur();
      }
      this.getProductList(val.typeId)
      this.getSupplyInfoPage(val.typeId, null)
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
        supplyInfoId: undefined,
        supplyCompany: undefined,
        purchaseHistoryId: undefined,
        productSerial: undefined,
        hsSerial: undefined,
        productSize: undefined,
        productColor: undefined,
        productG: undefined,
        boxLength: undefined,
        boxWide: undefined,
        boxHeight: undefined,
        unitPrice: undefined,
        grossWeight: undefined,
        netWeight: undefined,
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
      this.title = "添加供货商供应记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getPurchaseHistory(id).then(response => {
        response.data.supplyInfoId = parseInt(response.data.supplyInfoId)
        this.form = response.data;
        this.open = true;
        this.title = "修改供货商供应记录";
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
          updatePurchaseHistory(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createPurchaseHistory(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除供货商供应记录编号为"' + id + '"的数据项?').then(function () {
        return deletePurchaseHistory(id);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      // 处理查询参数
      let params = {...this.queryParams};
      params.pageNo = undefined;
      params.pageSize = undefined;
      this.$modal.confirm('是否确认导出所有供货商供应记录数据项?').then(() => {
        this.exportLoading = true;
        return exportPurchaseHistoryExcel(params);
      }).then(response => {
        this.$download.excel(response, '供货商供应记录.xls');
        this.exportLoading = false;
      }).catch(() => {
      });
    }
  },
  filters: {

  }
};
</script>
