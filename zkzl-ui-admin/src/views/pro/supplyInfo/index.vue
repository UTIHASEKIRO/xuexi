<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="公司名称" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入公司名称" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="公司地址" prop="address">
        <el-input v-model="queryParams.address" placeholder="请输入公司地址" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="联系人" prop="contact">
        <el-input v-model="queryParams.contact" placeholder="请输入联系人" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="电话" prop="phone">
        <el-input v-model="queryParams.phone" placeholder="请输入电话" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="email" prop="email">
        <el-input v-model="queryParams.email" placeholder="请输入email" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker v-model="queryParams.createTime" style="width: 240px" value-format="yyyy-MM-dd HH:mm:ss" type="daterange"
                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" :default-time="['00:00:00', '23:59:59']" />
      </el-form-item>
      <el-form-item label="主要产品" prop="product">
        <el-input v-model="queryParams.product" placeholder="请输入主要产品" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="产品类别id" prop="sortId">
        <el-input v-model="queryParams.sortId" placeholder="请输入产品类别id" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="推荐理由-价格" prop="reasonPrice">
        <el-input v-model="queryParams.reasonPrice" placeholder="请输入推荐理由-价格" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="推荐理由-质量" prop="reasonQuality">
        <el-input v-model="queryParams.reasonQuality" placeholder="请输入推荐理由-质量" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="推荐理由-服务" prop="reasonService">
        <el-input v-model="queryParams.reasonService" placeholder="请输入推荐理由-服务" clearable @keyup.enter.native="handleQuery"/>
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
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" :loading="exportLoading"
                   v-hasPermi="['pro:supply-info:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="序号id" align="center" prop="id" />
      <el-table-column label="公司名称" align="center" prop="name" />
      <el-table-column label="公司地址" align="center" prop="address" />
      <el-table-column label="联系人" align="center" prop="contact" />
      <el-table-column label="电话" align="center" prop="phone" />
      <el-table-column label="email" align="center" prop="email" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="主要产品" align="center" prop="product" />
      <el-table-column label="产品类别id" align="center" prop="sortId" />
      <el-table-column label="推荐理由-价格" align="center" prop="reasonPrice" />
      <el-table-column label="推荐理由-质量" align="center" prop="reasonQuality" />
      <el-table-column label="推荐理由-服务" align="center" prop="reasonService" />
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
        <el-form-item label="公司名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入公司名称" />
        </el-form-item>
        <el-form-item label="公司地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入公司地址" />
        </el-form-item>
        <el-form-item label="联系人" prop="contact">
          <el-input v-model="form.contact" placeholder="请输入联系人" />
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入电话" />
        </el-form-item>
        <el-form-item label="email" prop="email">
          <el-input v-model="form.email" placeholder="请输入email" />
        </el-form-item>
        <el-form-item label="主要产品" prop="product">
          <el-input v-model="form.product" placeholder="请输入主要产品" />
        </el-form-item>
        <el-form-item label="产品类别id" prop="sortId">
          <el-input v-model="form.sortId" placeholder="请输入产品类别id" />
        </el-form-item>
        <el-form-item label="推荐理由-价格" prop="reasonPrice">
          <el-input v-model="form.reasonPrice" placeholder="请输入推荐理由-价格" />
        </el-form-item>
        <el-form-item label="推荐理由-质量" prop="reasonQuality">
          <el-input v-model="form.reasonQuality" placeholder="请输入推荐理由-质量" />
        </el-form-item>
        <el-form-item label="推荐理由-服务" prop="reasonService">
          <el-input v-model="form.reasonService" placeholder="请输入推荐理由-服务" />
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
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
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
