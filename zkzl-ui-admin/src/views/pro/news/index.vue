<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="标题" prop="titleCn">
        <el-input v-model="queryParams.titleCn" placeholder="请输入标题" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="概要" prop="summaryCn">
        <el-input v-model="queryParams.summaryCn" placeholder="请输入概要" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="新闻类别" prop="newsType">
        <el-select v-model="queryParams.newsType" placeholder="请选择新闻类别" clearable size="small">
          <el-option v-for="dict in this.getDictDatas(DICT_TYPE.NEWS_TYPE)"
                       :key="dict.value" :label="dict.label" :value="dict.value"/>
        </el-select>
      </el-form-item>
      <el-form-item label="是否上架" prop="shelves">
        <el-select v-model="queryParams.shelves" placeholder="请选择是否上架" clearable size="small">
          <el-option v-for="dict in this.getDictDatas(DICT_TYPE.IS_SHELF)"
                       :key="dict.value" :label="dict.label" :value="dict.value"/>
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker v-model="queryParams.createTime" style="width: 240px" value-format="yyyy-MM-dd HH:mm:ss" type="daterange"
                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" :default-time="['00:00:00', '23:59:59']" />
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
                   v-hasPermi="['pro:news:create']">新增</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="序号id" align="center" prop="id" />
      <el-table-column label="标题_中" align="center" prop="titleCn" />
      <el-table-column label="标题_英" align="center" prop="titleEn" />
      <el-table-column label="概要_中" align="center" prop="summaryCn" />
      <el-table-column label="概要_英" align="center" prop="summaryEn" />
      <el-table-column label="新闻类别" align="center" prop="newsType">
        <template slot-scope="scope">
          <dict-tag :type="DICT_TYPE.NEWS_TYPE" :value="scope.row.newsType" />
        </template>
      </el-table-column>
      <el-table-column label="是否上架" align="center" prop="shelves">
        <template slot-scope="scope">
          <dict-tag :type="DICT_TYPE.IS_SHELF" :value="scope.row.shelves" />
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['pro:news:update']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['pro:news:delete']">删除</el-button>
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
          <imageUpload v-model="form.picUrl" limit="3"/>
        </el-form-item>
        <el-form-item label="标题_中" prop="titleCn">
          <el-input v-model="form.titleCn" placeholder="请输入标题_中" />
        </el-form-item>
        <el-form-item label="标题_英" prop="titleEn">
          <el-input v-model="form.titleEn" placeholder="请输入标题_英" />
        </el-form-item>
        <el-form-item label="概要_中" prop="summaryCn">
          <el-input v-model="form.summaryCn" placeholder="请输入概要_中" />
        </el-form-item>
        <el-form-item label="概要_英" prop="summaryEn">
          <el-input v-model="form.summaryEn" placeholder="请输入概要_英" />
        </el-form-item>
        <el-form-item label="内容_中">
          <editor v-model="form.contentCn" :min-height="192"/>
        </el-form-item>
        <el-form-item label="内容_英">
          <editor v-model="form.contentEn" :min-height="192"/>
        </el-form-item>
        <el-form-item label="新闻类别" prop="newsType">
          <el-select v-model="form.newsType" placeholder="请选择新闻类别">
            <el-option v-for="dict in this.getDictDatas(DICT_TYPE.NEWS_TYPE)"
                       :key="dict.value" :label="dict.label" :value="parseInt(dict.value)" />
          </el-select>
        </el-form-item>
        <el-form-item label="是否上架" prop="shelves">
          <el-select v-model="form.shelves" placeholder="请选择是否上架">
            <el-option v-for="dict in this.getDictDatas(DICT_TYPE.IS_SHELF)"
                       :key="dict.value" :label="dict.label" :value="dict.value" />
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
import { createNews, updateNews, deleteNews, getNews, getNewsPage, exportNewsExcel } from "@/api/pro/news";
import ImageUpload from '@/components/ImageUpload';
import Editor from '@/components/Editor';

export default {
  name: "News",
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
      // 新闻列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        picUrl: null,
        titleCn: null,
        titleEn: null,
        summaryCn: null,
        summaryEn: null,
        contentCn: null,
        contentEn: null,
        newsType: null,
        shelves: null,
        createTime: [],
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        picUrl: [{ required: true, message: "图片不能为空", trigger: "blur" }],
        titleCn: [{ required: true, message: "标题_中不能为空", trigger: "blur" }],
        titleEn: [{ required: true, message: "标题_英不能为空", trigger: "blur" }],
        summaryCn: [{ required: true, message: "概要_中不能为空", trigger: "blur" }],
        summaryEn: [{ required: true, message: "概要_英不能为空", trigger: "blur" }],
        contentCn: [{ required: true, message: "内容_中不能为空", trigger: "blur" }],
        contentEn: [{ required: true, message: "内容_英不能为空", trigger: "blur" }],
        newsType: [{ required: true, message: "新闻类别不能为空", trigger: "change" }],
        shelves: [{ required: true, message: "是否上架不能为空", trigger: "change" }],
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
      getNewsPage(this.queryParams).then(response => {
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
        picUrl: undefined,
        titleCn: undefined,
        titleEn: undefined,
        summaryCn: undefined,
        summaryEn: undefined,
        contentCn: undefined,
        contentEn: undefined,
        newsType: undefined,
        shelves: undefined,
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
      this.title = "添加新闻";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getNews(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改新闻";
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
          updateNews(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createNews(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除新闻编号为"' + id + '"的数据项?').then(function() {
          return deleteNews(id);
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
      this.$modal.confirm('是否确认导出所有新闻数据项?').then(() => {
          this.exportLoading = true;
          return exportNewsExcel(params);
        }).then(response => {
          this.$download.excel(response, '新闻.xls');
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
