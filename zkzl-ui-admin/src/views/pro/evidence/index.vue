<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="凭证id" prop="evidenceId">
        <el-input v-model="queryParams.evidenceId" placeholder="请输入凭证id" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="询价单id" prop="priceInquryId">
        <el-input v-model="queryParams.priceInquryId" placeholder="请输入询价单id" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="订单id" prop="orderId">
        <el-input v-model="queryParams.orderId" placeholder="请输入订单id" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="定金截图" prop="depositPic">
        <el-input v-model="queryParams.depositPic" placeholder="请输入定金截图" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="尾款截图" prop="balancePic">
        <el-input v-model="queryParams.balancePic" placeholder="请输入尾款截图" clearable @keyup.enter.native="handleQuery"/>
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
                   v-hasPermi="['pro:evidence:create']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" :loading="exportLoading"
                   v-hasPermi="['pro:evidence:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="凭证id" align="center" prop="evidenceId" />
      <el-table-column label="询价单id" align="center" prop="priceInquryId" />
      <el-table-column label="订单id" align="center" prop="orderId" />
      <el-table-column label="定金截图" align="center" prop="depositPic" >
        <template slot-scope="scope">
          <ImagePreview  :src="scope.row.depositPic"
                         :width="'100px'"></ImagePreview>
        </template>
      </el-table-column>
      <el-table-column label="尾款截图" align="center" prop="balancePic" >
        <template slot-scope="scope">
          <ImagePreview  :src="scope.row.balancePic"
                         :width="'100px'"></ImagePreview>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['pro:evidence:update']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['pro:evidence:delete']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" v-dialogDrag append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="凭证id" prop="evidenceId">
          <el-input v-model="form.evidenceId" placeholder="请输入凭证id" />
        </el-form-item>
        <el-form-item label="询价单id" prop="priceInquryId">
          <el-input v-model="form.priceInquryId" placeholder="请输入询价单id" />
        </el-form-item>
        <el-form-item label="订单id" prop="orderId">
          <el-input v-model="form.orderId" placeholder="请输入订单id" />
        </el-form-item>
        <el-form-item label="定金截图" prop="depositPic">
          <el-input v-model="form.depositPic" placeholder="请输入定金截图" />
        </el-form-item>
        <el-form-item label="尾款截图" prop="balancePic">
          <el-input v-model="form.balancePic" placeholder="请输入尾款截图" />
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
import { createEvidence, updateEvidence, deleteEvidence, getEvidence, getEvidencePage, exportEvidenceExcel } from "@/api/pro/evidence";
import ImagePreview from "@/components/ImagePreview";
export default {
  name: "Evidence",
  components: {
    ImagePreview
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
      // 订单凭证信息列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        evidenceId: null,
        priceInquryId: null,
        orderId: null,
        depositPic: null,
        balancePic: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        evidenceId: [{ required: true, message: "凭证id不能为空", trigger: "blur" }],
        priceInquryId: [{ required: true, message: "询价单id不能为空", trigger: "blur" }],
        orderId: [{ required: true, message: "订单id不能为空", trigger: "blur" }],
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
      getEvidencePage(this.queryParams).then(response => {
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
        evidenceId: undefined,
        priceInquryId: undefined,
        orderId: undefined,
        depositPic: undefined,
        balancePic: undefined,
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
      this.title = "添加订单凭证信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getEvidence(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改订单凭证信息";
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
          updateEvidence(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createEvidence(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除订单凭证信息编号为"' + id + '"的数据项?').then(function() {
          return deleteEvidence(id);
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
      this.$modal.confirm('是否确认导出所有订单凭证信息数据项?').then(() => {
          this.exportLoading = true;
          return exportEvidenceExcel(params);
        }).then(response => {
          this.$download.excel(response, '订单凭证信息.xls');
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
