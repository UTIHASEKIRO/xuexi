<template>
  <div class="app-container">
 <el-row :gutter="20">
      <!--部门数据-->
      <el-col :span="4" :xs="24">
        <div class="head-container">
          <el-input v-model="typeName" placeholder="请输入部门名称" clearable size="small" prefix-icon="el-icon-search" style="margin-bottom: 20px"/>
        </div>
        <div class="head-container">
          <el-tree node-key="typeId" :data="deptOptions" :props="defaultProps" :expand-on-click-node="false" :filter-node-method="filterNode"
                   ref="tree" default-expand-all highlight-current @node-click="handleNodeClick"/>
        </div>
      </el-col>
      <!--用户数据-->
      <el-col :span="20" :xs="24">
    <!-- 搜索工作栏 -->
    <!-- <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="类别名称-中文" prop="typeName">
        <el-input v-model="queryParams.typeName" placeholder="请输入类别名称-中文" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
     
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form> -->

    <!-- 操作工具栏 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                   v-hasPermi="['pro:duct-type:create']">新增</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" :loading="exportLoading"
                   v-hasPermi="['pro:duct-type:export']">导出</el-button>
      </el-col> -->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list" border>
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="类别名称-中文" align="center" prop="typeNameCn" />
      <el-table-column label="类别名称-英文" align="center" prop="typeNameEn" />

     
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['pro:duct-type:update']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['pro:duct-type:delete']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <!-- <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/> -->
      </el-col>
    </el-row>
    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" v-dialogDrag append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="类别名称-中文" prop="typeNameCn">
          <el-input v-model="form.typeNameCn" placeholder="请输入类别名称-中文" />
        </el-form-item>
        <el-form-item label="类别名称-英文" prop="typeNameEn">
          <el-input v-model="form.typeNameEn" placeholder="请输入类别名称-英文" />
        </el-form-item>
        <el-form-item label="图片" prop="typePic">
          <!-- <el-input v-model="form.typeId" placeholder="请输入产品类别id" /> -->
          <imageUpload v-model="form.typePic" :limit="1" fileWh="320*265"/>
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
import { createductType, updateductType, deleteductType, getductType, getductTypePage, exportductTypeExcel,ductTypeList } from "@/api/pro/ductType";
import ImageUpload from '@/components/ImageUpload';

export default {
  name: "ductType",
  components: {
    ImageUpload
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
      // 商品类别列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        typeId:undefined,
        typeName: "",
        typeNameEn: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      defaultProps: {
        children: "productTypeDOS",
        label: "typeNameCn"
      },
      typeName:"",
      deptOptions:[],
      parentNode:{}
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
      ductTypeList(this.queryParams).then(response => {
        this.handleTree(response.data)
        this.list = response.data
        this.loading = false;
      });
    },
    handleTree(data){
      var parent = [{typeNameCn:'顶级',productTypeDOS:[...data]}]
      this.parentNode = parent
      
      console.log('handleTree',parent)
      this.deptOptions = parent
    },
     handleNodeClick(data) {
      console.log('handleNodeClick',data)
      this.parentNode = data
      this.form.typeId = this.parentNode.typeId
      // this.queryParams.deptId = data.id;
      // this.getList();
      this.list = data.productTypeDOS
    },
    filterNode(value, data) {
      if (!value) return true;
      return data.typeNameCn.indexOf(value) !== -1;
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
        parentId: undefined,
        typeId: undefined,
        typeNameCn: undefined,
        typeNameEn: undefined,
        sort: undefined,
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
      this.form.parentId = this.parentNode.typeId
      this.open = true;
      this.title = "添加商品类别";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      getductType({typeId:row.typeId}).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改商品类别";
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
          updateductType(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createductType(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除商品类别编号为"' + id + '"的数据项?').then(function() {
          return deleteductType({typeId:row.typeId});
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
      this.$modal.confirm('是否确认导出所有商品类别数据项?').then(() => {
          this.exportLoading = true;
          return exportductTypeExcel(params);
        }).then(response => {
          this.$download.excel(response, '商品类别.xls');
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
