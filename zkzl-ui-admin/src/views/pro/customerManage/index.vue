<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px" >

      <el-form-item label="重要等级" prop="importantLevel" >
        <el-select v-model="queryParams.importantLevel" placeholder="请选择重要等级" clearable size="small" style="width: 240px">
          <el-option v-for="dict in importantLevelDict" :key="parseInt(dict.value)" :label="dict.label" :value="parseInt(dict.value)"/>
        </el-select>
      </el-form-item>

      <el-form-item label="客户跟进结果" prop="customerResult" label-width="100px">

        <el-select v-model="queryParams.customerResult" placeholder="请选择客户跟进结果" clearable size="small" style="width: 240px">
          <el-option v-for="dict in customerResultDict" :key="parseInt(dict.value)" :label="dict.label" :value="parseInt(dict.value)"/>
        </el-select>
      </el-form-item>

      <el-form-item label="跟进销售员" prop="followUser" label-width="100px">
        <el-input v-model="queryParams.followUser" placeholder="请输入跟进销售员" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作工具栏 -->
    <el-row :gutter="10" class="mb8">
      <!-- <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                   v-hasPermi="['pro:customer-manage:create']">新增</el-button>
      </el-col> -->
      <!-- <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" :loading="exportLoading"
                   v-hasPermi="['pro:customer-manage:export']">导出</el-button>
      </el-col> -->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list" border>
      <el-table-column label="序号id" align="center" prop="id" />
      <el-table-column label="客户" align="center" prop="customerName" />
      <el-table-column label="联系方式" align="center" prop="mobile" />
      <el-table-column label="联系地址" align="center" prop="address" />
      <el-table-column label="重要等级" align="center" prop="importantLevel" >
         <template slot-scope="scope">
          <dict-tag :type="DICT_TYPE.IMPORTANT_LEVEL" :value="scope.row.importantLevel"/>
        </template>

      </el-table-column>
      <!-- <el-table-column label="客户意向" align="center" prop="customerIntention" >
        <template slot-scope="scope">
          <dict-tag :type="DICT_TYPE.CUSTOMER_INTENTION" :value="scope.row.status"/>
        </template>
      </el-table-column> -->

      <el-table-column label="跟进销售员" align="center" prop="followUser" />
      <el-table-column label="客户跟进结果" align="center" prop="customerResult" >
         <template slot-scope="scope">
          <dict-tag :type="DICT_TYPE.CUSTOMER_RESULT" :value="scope.row.customerResult"/>
        </template>
      </el-table-column>

      <el-table-column label="跟进次数" align="center" prop="followNum" />
      <el-table-column label="客户需求" align="center" prop="customerDemand" />
      <el-table-column label="备注" align="center" prop="remark" />
      <!-- <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column> -->
       <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['pro:customer-manage:update']">修改</el-button>
<!--          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['pro:customer-manage:delete']">删除</el-button>-->
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" v-dialogDrag append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="客户" prop="customerName">
          <el-input v-model="form.customerName" placeholder="请输入客户" disabled = 'true' />
        </el-form-item>
        <el-form-item label="联系方式" prop="mobile">
          <el-input v-model="form.mobile" placeholder="请输入联系方式" disabled = 'true'  />
        </el-form-item>
        <el-form-item label="联系地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入联系地址" />
        </el-form-item>
        <el-form-item label="重要等级" prop="importantLevel">
          <el-select v-model="form.importantLevel" placeholder="请选择重要等级">
            <el-option v-for="dict in this.getDictDatas(DICT_TYPE.IMPORTANT_LEVEL)"
                       :key="dict.value" :label="dict.label" :value="dict.value" />
          </el-select>
        </el-form-item>
        <el-form-item label="客户意向" prop="customerIntention">
          <el-select v-model="form.customerIntention" placeholder="请选择客户意向">
            <el-option v-for="dict in this.getDictDatas(DICT_TYPE.CUSTOMER_INTENTION)"
                       :key="dict.value" :label="dict.label" :value="dict.value" />
          </el-select>
        </el-form-item>
        <el-form-item label="客户需求" prop="customerDemand">
          <el-input v-model="form.customerDemand" placeholder="请输入客户需求，引号;分隔开" />
        </el-form-item>
        <el-form-item label="客户跟进结果" prop="customerResult">
          <el-select v-model="form.customerResult" placeholder="请选择客户跟进结果">
            <el-option v-for="dict in this.getDictDatas(DICT_TYPE.CUSTOMER_RESULT)"
                       :key="dict.value" :label="dict.label" :value="dict.value" />
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="跟进次数" prop="followNum">
          <el-input v-model="form.followNum" placeholder="请输入跟进次数" />
        </el-form-item>
        <el-form-item label="跟进销售员" prop="followUser">
          <el-input v-model="form.followUser" placeholder="请输入跟进销售员" />
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
import { createCustomerManage, updateCustomerManage, deleteCustomerManage, getCustomerManage, getCustomerManagePage, exportCustomerManageExcel } from "@/api/pro/customerManage";
import {DICT_TYPE, getDictDatas} from "@/utils/dict";
export default {
  name: "CustomerManage",
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
      // 客户管理跟进列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        customerName: null,
        mobile: null,
        address: null,
        importantLevel: null,
        customerIntention: null,
        customerDemand: null,
        customerResult: null,
        remark: null,
        followNum: null,
        followUser: null,
        createTime: [],
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        customerName: [{ required: true, message: "客户不能为空", trigger: "blur" }],
        mobile: [{ required: true, message: "联系方式不能为空", trigger: "blur" }],
        importantLevel: [{ required: true, message: "重要等级不能为空", trigger: "blur" }],
        followUser: [{ required: true, message: "跟进销售员不能为空", trigger: "blur" }],
      },
      importantLevelDict: getDictDatas(DICT_TYPE.IMPORTANT_LEVEL),
      customerResultDict:getDictDatas(DICT_TYPE.CUSTOMER_RESULT),
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
      getCustomerManagePage(this.queryParams).then(response => {
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
        customerName: undefined,
        mobile: undefined,
        address: undefined,
        importantLevel: undefined,
        customerIntention: undefined,
        customerDemand: undefined,
        customerResult: undefined,
        remark: undefined,
        followNum: undefined,
        followUser: undefined,
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
      this.title = "添加客户管理跟进";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getCustomerManage(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改客户管理跟进";
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
          updateCustomerManage(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createCustomerManage(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除客户管理跟进编号为"' + id + '"的数据项?').then(function() {
          return deleteCustomerManage(id);
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
      this.$modal.confirm('是否确认导出所有客户管理跟进数据项?').then(() => {
          this.exportLoading = true;
          return exportCustomerManageExcel(params);
        }).then(response => {
          this.$download.excel(response, '客户管理跟进.xls');
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
