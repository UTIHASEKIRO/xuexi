<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="产品类别" prop="typeId">
        <el-select v-model="queryParams.typeId" placeholder="请选择产品类别" clearable size="small" style="width: 240px">
          <el-option v-for="dict in duckTypeList" :key="parseInt(dict.id)" :label="dict.typeNameCn" :value="dict.typeId"/>
        </el-select>
        <!-- <el-input v-model="queryParams.sortId" placeholder="请选择产品类别" clearable @keyup.enter.native="handleQuery"/> -->
      </el-form-item>
      <el-form-item label="公司名称" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入公司名称" clearable @keyup.enter.native="handleQuery"/>
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
                   v-hasPermi="['pro:supply-info:create']">新增</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" :loading="exportLoading"
                   v-hasPermi="['pro:supply-info:export']">导出</el-button>
      </el-col> -->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="序号id" align="center" prop="id" />
      <el-table-column label="产品类别" align="center" prop="typeNameCn" />
      <el-table-column label="公司名称" align="center" prop="name" />
      <el-table-column label="联系人" align="center" prop="contact" />
      <el-table-column label="联系电话" align="center" prop="phone" />
      <el-table-column label="公司评级" align="center" prop="evaluation" />
      <el-table-column label="主要产品" align="center" prop="productNameCn" />
      <el-table-column label="推荐理由-价格" align="center" prop="reasonPrice" >
        <template slot-scope="scope">
          <dict-tag :type="DICT_TYPE.REASON_PRICE" :value="scope.row.reasonPrice"/>
        </template>
      </el-table-column>
      <el-table-column label="推荐理由-质量" align="center" prop="reasonQuality" >
        <template slot-scope="scope">
          <dict-tag :type="DICT_TYPE.REASON_QUALITY" :value="scope.row.reasonQuality"/>
        </template>
      </el-table-column>
      <el-table-column label="推荐理由-服务" align="center" prop="reasonService" >
        <template slot-scope="scope">
          <dict-tag :type="DICT_TYPE.REASON_SERVICE" :value="parseInt(scope.row.reasonService)"/>
        </template>
      </el-table-column>

      <!-- <el-table-column label="公司地址" align="center" prop="address" />


      <el-table-column label="email" align="center" prop="email" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column> -->



      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['pro:supply-info:update']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['pro:supply-info:delete']">删除</el-button>
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
          <el-select ref="selectReport" v-model="form.typeId" placeholder="请选择">
            <el-option :value="form.typeId" :label="form.typeNameCn" style="height: auto">
              <el-tree :data="duckTypeList" :props="{ value: 'typeId',  label: 'typeNameCn' ,children: 'productTypeDOS'}" @node-click="handleNodeClick"></el-tree>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="公司名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入公司名称" />
        </el-form-item>
        <el-form-item label="联系人" prop="contact">
          <el-input v-model="form.contact" placeholder="请输入联系人" />
        </el-form-item>
        <el-form-item label="联系电话" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入电话" />
        </el-form-item>
        <el-form-item label="公司评价" prop="evaluation">
          <el-input v-model="form.evaluation" placeholder="请输入公司评价" />
        </el-form-item>


        <el-form-item label="主要产品" prop="product">
          <el-select v-model="form.productId" placeholder="请选择主要产品" clearable size="small" style="width: 240px">
            <el-option v-for="dict in productList" :key="parseInt(dict.id)" :label="dict.productNameCn" :value="dict.productId"/>
          </el-select>
        </el-form-item>

        <el-form-item label="推荐理由-价格" prop="reasonPrice">
          <el-select v-model="form.reasonPrice" placeholder="请选择推荐理由" clearable size="small" style="width: 240px">
            <el-option v-for="dict in reasonServiceDict" :key="parseInt(dict.value)" :label="dict.label" :value="parseInt(dict.value)"/>
          </el-select>
        </el-form-item>
        <el-form-item label="推荐理由-质量" prop="reasonQuality">
           <el-select v-model="form.reasonQuality" placeholder="请选择推荐理由" clearable size="small" style="width: 240px">
            <el-option v-for="dict in reasonQualityDict" :key="parseInt(dict.value)" :label="dict.label" :value="parseInt(dict.value)"/>
          </el-select>
        </el-form-item>
        <el-form-item label="推荐理由-服务" prop="reasonService">
           <el-select v-model="form.reasonService" placeholder="请选择推荐理由" clearable size="small" style="width: 240px">
            <el-option v-for="dict in reasonPriceDict" :key="parseInt(dict.value)" :label="dict.label" :value="parseInt(dict.value)"/>
          </el-select>
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
import { createSupplyInfo, updateSupplyInfo, deleteSupplyInfo, getSupplyInfo, getSupplyInfoPage, exportSupplyInfoExcel } from "@/api/pro/supplyInfo";
import { getductTypePage,ductTypeList } from "@/api/pro/ductType";
import { getductList } from "@/api/pro/duct";
import {DICT_TYPE, getDictDatas} from "@/utils/dict";
export default {
  name: "SupplyInfo",
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
      // 供货商信息列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        name: null,
        address: null,
        contact: null,
        phone: null,
        email: null,
        createTime: [],
        product: null,
        sortId: null,
        reasonPrice: null,
        reasonQuality: null,
        reasonService: null,
      },
      // 表单参数
      form: {},
      addForm: {},
      // 表单校验
      rules: {
         typeId: [
          { required: true, message: "产品类别不能为空", trigger: "blur" }
        ],
        name: [
          { required: true, message: "公司名称不能为空", trigger: "blur" }
        ],
        contact: [
          { required: true, message: "联系人不能为空", trigger: "blur" }
        ],
        phone: [
          {
            pattern: /^(?:(?:\+|00)86)?1(?:(?:3[\d])|(?:4[5-79])|(?:5[0-35-9])|(?:6[5-7])|(?:7[0-8])|(?:8[\d])|(?:9[189]))\d{8}$/,
            message: "请输入正确的联系电话",
            trigger: "blur"
          }
        ],
        evaluation: [
          { required: true, message: "公司评价不能为空", trigger: "blur" }
        ],
        productId: [
          { required: true, message: "主要产品不能为空", trigger: "blur" }
        ],
        reasonPrice: [
          { required: true, message: "推荐理由不能为空", trigger: "blur" }
        ],
        reasonQuality: [
          { required: true, message: "推荐理由不能为空", trigger: "blur" }
        ],
         reasonService: [
          { required: true, message: "推荐理由不能为空", trigger: "blur" }
        ],
      },
      duckTypeList:[],
      productList:[],
      reasonServiceDict: getDictDatas(DICT_TYPE.REASON_SERVICE),
      reasonQualityDict: getDictDatas(DICT_TYPE.REASON_QUALITY),
      reasonPriceDict: getDictDatas(DICT_TYPE.REASON_PRICE),
    };
  },
  created() {
    this.getList();
    this.getDuckTypePage();
    this.getProductList(null)
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 执行查询
      getSupplyInfoPage(this.queryParams).then(response => {
        this.list = response.data.list;
        this.total = response.data.total;
        this.loading = false;
      });
    },
    /** 商品类别列表 */
    getDuckTypePage(){
      ductTypeList({typeName:''}).then(result=>{
        this.duckTypeList = result.data
      })
    },
    /** 商品列表 */
    getProductList(typeId){
      getductList({typeId: typeId }).then(result=>{
        this.productList = result.data
        this.form.productId = ''
      })
    },
    /** 类别树选择 */
    handleNodeClick(val) {
      this.$set(this.form, "typeId", val.typeId);
      this.$set(this.form, "typeNameCn", val.typeNameCn);
      if (!val.productTypeDOS || val.productTypeDOS.length == 0) {
        this.$refs.selectReport.blur();
      }
      this.getProductList(val.typeId)
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
        name: undefined,
        address: undefined,
        contact: undefined,
        phone: undefined,
        email: undefined,
        product: undefined,
        sortId: undefined,
        reasonPrice: undefined,
        reasonQuality: undefined,
        reasonService: undefined,
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
      this.title = "添加供货商信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getSupplyInfo(id).then(response => {
        response.data.typeId = response.data.typeId
        response.data.reasonPrice = parseInt(response.data.reasonPrice)
        response.data.reasonQuality = parseInt(response.data.reasonQuality)
        response.data.reasonService = parseInt(response.data.reasonService)
        this.form = response.data;
        this.open = true;
        this.title = "修改供货商信息";
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
          updateSupplyInfo(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createSupplyInfo(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除供货商信息编号为"' + id + '"的数据项?').then(function() {
          return deleteSupplyInfo(id);
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
      this.$modal.confirm('是否确认导出所有供货商信息数据项?').then(() => {
          this.exportLoading = true;
          return exportSupplyInfoExcel(params);
        }).then(response => {
          this.$download.excel(response, '供货商信息.xls');
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
