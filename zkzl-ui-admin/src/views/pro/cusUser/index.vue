<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="状态" prop="status">
         <el-select v-model="queryParams.status" placeholder="请选择状态" clearable size="small" style="width: 240px">
          <el-option v-for="dict in userStatusDict" :key="parseInt(dict.value)" :label="dict.label" :value="parseInt(dict.value)"/>
        </el-select>
      </el-form-item>
      <el-form-item label="公司名称" prop="companyName">
        <el-input v-model="queryParams.companyName" placeholder="请输入公司名称" clearable @keyup.enter.native="handleQuery"/>
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
                   v-hasPermi="['pro:cus-user:create']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" :loading="exportLoading"
                   v-hasPermi="['pro:cus-user:export']">导出</el-button>
      </el-col> -->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list" border>
      <el-table-column label="用户ID" align="center" prop="userId" />
      <el-table-column label="公司名称" align="center" prop="companyName" />
      <el-table-column label="联系人" align="center" prop="contectName" />
      <el-table-column label="联系电话" align="center" prop="mobile" />
      <el-table-column label="联系地址" align="center" prop="address" />
      <el-table-column label="注册时间" align="center" prop="createTime" />
      <el-table-column label="状态" align="center" prop="status" >
        <template slot-scope="scope">
          <dict-tag :type="DICT_TYPE.USER_STATUS" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text"  @click="handleUpdateUserPassword(scope.row)"
                     >重置密码</el-button>
                     <el-button size="mini" type="text"  @click="handleUpdateUserStatus(scope.row)"
                     >{{scope.row.status === 0?"禁用":"启用"}}</el-button>
          <!-- <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['pro:cus-user:update']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['pro:cus-user:delete']">删除</el-button> -->
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" v-dialogDrag append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="联系地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入联系地址" />
        </el-form-item>
        <el-form-item label="公司名称" prop="companyName">
          <el-input v-model="form.companyName" placeholder="请输入公司名称" />
        </el-form-item>
        <el-form-item label="联系人" prop="contectName">
          <el-input v-model="form.contectName" placeholder="请输入联系人" />
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
import { createCusUser, updateCusUser, deleteCusUser, getCusUser, getCusUserPage, exportCusUserExcel,updateUserStatus,updateUserPassword } from "@/api/pro/cusUser";
import {DICT_TYPE, getDictDatas} from "@/utils/dict";
export default {
  name: "CusUser",
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
      // 客户公司信息列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        address: null,
        companyName: null,
        contectName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        address: [{ required: true, message: "联系地址不能为空", trigger: "blur" }],
        companyName: [{ required: true, message: "公司名称不能为空", trigger: "blur" }],
        contectName: [{ required: true, message: "联系人不能为空", trigger: "blur" }],
      },
      userStatusDict: getDictDatas(DICT_TYPE.USER_STATUS), 
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
      getCusUserPage(this.queryParams).then(response => {
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
        status: undefined,
        companyName: undefined,
      };
      this.resetForm("form");
    },
    // 重置密码
    handleUpdateUserPassword(row){
       var payload = {
        id:row.userId,
        password:"A2c1_3"
      }
    this.$modal.confirm('确认要重置'  + '"' + row.contectName + '"用户密码吗?').then(function() {
          return  updateUserPassword(payload);
        }).then(() => {
          // this.getList()
          this.$modal.msgSuccess("重置成功");
        }).catch(function() {
          // row.status = row.status === CommonStatusEnum.ENABLE ? CommonStatusEnum.DISABLE
          //     : CommonStatusEnum.ENABLE;
        });
    },
    /** 禁用/启用 用户登录前端页面 */
    handleUpdateUserStatus(row) {
      console.log("updateUserStatus",row)
      var label = row.status=== 0?"禁用":"启用"
      var payload = {
        id:row.userId,
        status:row.status===0?1:0
      }
      this.$modal.confirm('确认要' + label + '"' + row.contectName + '"用户吗?').then(function() {
          return  updateUserStatus(payload);
        }).then(() => {
          this.getList()
          this.$modal.msgSuccess(label + "成功");
        }).catch(function() {
          // row.status = row.status === CommonStatusEnum.ENABLE ? CommonStatusEnum.DISABLE
          //     : CommonStatusEnum.ENABLE;
        });
      
     
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
      this.title = "添加客户公司信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const userId = row.userId;
      getCusUser(userId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改客户公司信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (!valid) {
          return;
        }
        // 修改的提交
        if (this.form.userId != null) {
          updateCusUser(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createCusUser(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const userId = row.userId;
      this.$modal.confirm('是否确认删除客户公司信息编号为"' + userId + '"的数据项?').then(function() {
          return deleteCusUser(userId);
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
      this.$modal.confirm('是否确认导出所有客户公司信息数据项?').then(() => {
          this.exportLoading = true;
          return exportCusUserExcel(params);
        }).then(response => {
          this.$download.excel(response, '客户公司信息.xls');
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
