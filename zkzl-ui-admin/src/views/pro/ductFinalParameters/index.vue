<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
      <!-- <el-form-item label="固定参数id" prop="finalParametersId">
        <el-input v-model="queryParams.finalParametersId" placeholder="请输入固定参数id" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item> -->
      <el-form-item label="参数名-中文" prop="parameterCn" >
        <el-input v-model="queryParams.parameterCn" placeholder="请输入参数名-中文" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <!-- <el-form-item label="参数名-英文" prop="parameterEn">
        <el-input v-model="queryParams.parameterEn" placeholder="请输入参数名-英文" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="值-中文" prop="valueCn">
        <el-input v-model="queryParams.valueCn" placeholder="请输入值-中文" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="值-英文" prop="valueEn">
        <el-input v-model="queryParams.valueEn" placeholder="请输入值-英文" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item> -->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作工具栏 -->
    <el-row :gutter="10" class="mb8">
      <!-- <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                   v-hasPermi="['pro:duct-final-parameters:create']">新增</el-button>
      </el-col> -->
      <!-- <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" :loading="exportLoading"
                   v-hasPermi="['pro:duct-final-parameters:export']">导出</el-button>
      </el-col> -->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list" border>
      <el-table-column label="序号id" align="center" prop="id" />
      <!-- <el-table-column label="固定参数id" align="center" prop="finalParametersId" /> -->
      <el-table-column label="参数名-中文" align="center" prop="parameterCn" />
      <el-table-column label="参数名-英文" align="center" prop="parameterEn" />
      <!-- <el-table-column label="值-中文" align="center" prop="valueCn" />
      <el-table-column label="值-英文" align="center" prop="valueEn" /> -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['pro:duct-final-parameters:update']">修改</el-button>
          <!-- <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['pro:duct-final-parameters:delete']">删除</el-button> -->
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" v-dialogDrag append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

        <el-form-item label="参数名-中文" prop="parameterCn">
          <el-input v-model="form.parameterCn" placeholder="请输入参数名-中文" />
        </el-form-item>
        <el-form-item label="参数名-英文" prop="parameterEn">
          <el-input v-model="form.parameterEn" placeholder="请输入参数名-英文" />
        </el-form-item>
        <!-- <el-form-item label="值-中文" prop="valueCn">
          <el-input v-model="form.valueCn" placeholder="请输入值-中文" />
        </el-form-item>
        <el-form-item label="值-英文" prop="valueEn">
          <el-input v-model="form.valueEn" placeholder="请输入值-英文" />
        </el-form-item> -->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { createductFinalParameters, updateductFinalParameters, deleteductFinalParameters, getductFinalParameters, getductFinalParametersPage, exportductFinalParametersExcel } from "@/api/pro/ductFinalParameters";

export default {
  name: "ductFinalParameters",
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
      // 产品固定参数列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        finalParametersId: null,
        parameterCn: null,
        parameterEn: null,
        valueCn: null,
        valueEn: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        finalParametersId: [{ required: true, message: "固定参数id不能为空", trigger: "blur" }],
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
      getductFinalParametersPage(this.queryParams).then(response => {
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
        finalParametersId: undefined,
        parameterCn: undefined,
        parameterEn: undefined,
        valueCn: undefined,
        valueEn: undefined,
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
      this.title = "添加产品固定参数";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      // this.reset();

      // this.form = row;
      //   this.open = true;
      //   this.title = "修改产品固定参数";
        this.$router.push({path:'/pro/productFinalParametersValue',query:{cn:row.parameterCn,en:row.parameterEn}})

      // const id = row.id;
      // getductFinalParameters(id).then(response => {
      //   this.form = response.data;
      //   this.open = true;
      //   this.title = "修改产品固定参数";
      // });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (!valid) {
          return;
        }
        // 修改的提交
        if (this.form.id != null) {
          updateductFinalParameters(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createductFinalParameters(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除产品固定参数编号为"' + id + '"的数据项?').then(function() {
          return deleteductFinalParameters(id);
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
      this.$modal.confirm('是否确认导出所有产品固定参数数据项?').then(() => {
          this.exportLoading = true;
          return exportductFinalParametersExcel(params);
        }).then(response => {
          this.$download.excel(response, '产品固定参数.xls');
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
