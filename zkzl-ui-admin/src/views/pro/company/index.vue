<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->

    <!-- 操作工具栏 -->

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="电话" align="center" prop="mobile" />
      <el-table-column label="邮箱" align="center" prop="email" />
      <el-table-column label="公司名称-中" align="center" prop="nameCn" />
      <el-table-column label="公司名称-英" align="center" prop="nameEn" />
      <el-table-column label="公司地址-中" align="center" prop="addressCn" />
      <el-table-column label="公司地址-英" align="center" prop="addressEn" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['pro:company:update']">修改</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" v-dialogDrag append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="图片">
          <imageUpload v-model="form.picUrl"/>
        </el-form-item>
        <el-form-item label="电话" prop="mobile">
          <el-input v-model="form.mobile" placeholder="请输入电话" />
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="form.email" placeholder="请输入邮箱" />
        </el-form-item>
        <el-form-item label="公司名称-中" prop="nameCn">
          <el-input v-model="form.nameCn" placeholder="请输入公司名称-中" />
        </el-form-item>
        <el-form-item label="公司名称-英" prop="nameEn">
          <el-input v-model="form.nameEn" placeholder="请输入公司名称-英" />
        </el-form-item>
        <el-form-item label="公司介绍-中">
          <editor v-model="form.introduceCn" :min-height="192"/>
        </el-form-item>
        <el-form-item label="公司介绍-英">
          <editor v-model="form.introduceEn" :min-height="192"/>
        </el-form-item>
        <el-form-item label="业务介绍-中">
          <editor v-model="form.businessIntroduceCn" :min-height="192"/>
        </el-form-item>
        <el-form-item label="业务介绍-英">
          <editor v-model="form.businessIntroduceEn" :min-height="192"/>
        </el-form-item>
        <el-form-item label="公司地址-中" prop="addressCn">
          <el-input v-model="form.addressCn" placeholder="请输入公司地址-中" />
        </el-form-item>
        <el-form-item label="公司地址-英" prop="addressEn">
          <el-input v-model="form.addressEn" placeholder="请输入公司地址-英" />
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
import { createCompany, updateCompany, deleteCompany, getCompany, getCompanyPage, exportCompanyExcel } from "@/api/pro/company";
import ImageUpload from '@/components/ImageUpload';
import Editor from '@/components/Editor';

export default {
  name: "Company",
  components: {
    ImageUpload,
    Editor,
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
      // 公司信息维护列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        picUrl: [{ required: true, message: "图片不能为空", trigger: "blur" }],
        mobile: [{ required: true, message: "电话不能为空", trigger: "blur" }],
        email: [{ required: true, message: "邮箱不能为空", trigger: "blur" }],
        nameCn: [{ required: true, message: "公司名称-中不能为空", trigger: "blur" }],
        nameEn: [{ required: true, message: "公司名称-英不能为空", trigger: "blur" }],
        introduceCn: [{ required: true, message: "公司介绍-中不能为空", trigger: "blur" }],
        introduceEn: [{ required: true, message: "公司介绍-英不能为空", trigger: "blur" }],
        businessIntroduceCn: [{ required: true, message: "业务介绍-中不能为空", trigger: "blur" }],
        businessIntroduceEn: [{ required: true, message: "业务介绍-英不能为空", trigger: "blur" }],
        addressCn: [{ required: true, message: "公司地址-中不能为空", trigger: "blur" }],
        addressEn: [{ required: true, message: "公司地址-英不能为空", trigger: "blur" }],
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
      getCompanyPage(this.queryParams).then(response => {
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
        picUrl: undefined,
        mobile: undefined,
        email: undefined,
        nameCn: undefined,
        nameEn: undefined,
        introduceCn: undefined,
        introduceEn: undefined,
        businessIntroduceCn: undefined,
        businessIntroduceEn: undefined,
        addressCn: undefined,
        addressEn: undefined,
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
      this.title = "添加公司信息维护";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getCompany(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改公司信息维护";
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
          updateCompany(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createCompany(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除公司信息维护编号为"' + id + '"的数据项?').then(function() {
          return deleteCompany(id);
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
      this.$modal.confirm('是否确认导出所有公司信息维护数据项?').then(() => {
          this.exportLoading = true;
          return exportCompanyExcel(params);
        }).then(response => {
          this.$download.excel(response, '公司信息维护.xls');
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
