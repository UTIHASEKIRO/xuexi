<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <!-- <el-form-item label="订单成本表业务id" prop="orderCostId">
        <el-input v-model="queryParams.orderCostId" placeholder="请输入订单成本表业务id" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item> -->
      <el-form-item label="产品id" prop="productId">
        <el-input v-model="queryParams.productId" placeholder="请输入产品id" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <!-- <el-form-item label="单价" prop="unitPrice">
        <el-input v-model="queryParams.unitPrice" placeholder="请输入单价" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="数量" prop="mount">
        <el-input v-model="queryParams.mount" placeholder="请输入数量" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item> -->
      <!-- <el-form-item label="供货商id" prop="supplyInfoId">
        <el-input v-model="queryParams.supplyInfoId" placeholder="请输入供货商id" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="成本价" prop="costPrice">
        <el-input v-model="queryParams.costPrice" placeholder="请输入成本价" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item> -->
      <el-form-item label="询价时间" prop="priceDate">
        <el-date-picker v-model="queryParams.priceDate" style="width: 240px" value-format="yyyy-MM-dd HH:mm:ss" type="daterange"
                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" :default-time="['00:00:00', '23:59:59']" />
      </el-form-item>
      <!-- <el-form-item label="开始时间(订单生成时间)" prop="startTime">
        <el-date-picker v-model="queryParams.startTime" style="width: 240px" value-format="yyyy-MM-dd HH:mm:ss" type="daterange"
                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" :default-time="['00:00:00', '23:59:59']" />
      </el-form-item>
      <el-form-item label="结束时间" prop="endTime">
        <el-date-picker v-model="queryParams.endTime" style="width: 240px" value-format="yyyy-MM-dd HH:mm:ss" type="daterange"
                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" :default-time="['00:00:00', '23:59:59']" />
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
                   v-hasPermi="['pro:order-cost:create']">新增</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" :loading="exportLoading"
                   v-hasPermi="['pro:order-cost:export']">导出</el-button>
      </el-col> -->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <!-- <el-table-column label="id" align="center" prop="id" /> -->
      <el-table-column label="序号" align="center" prop="orderCostId" />
      <el-table-column label="产品" align="center" prop="productId" />
      <el-table-column label="单价" align="center" prop="unitPrice" />
      <el-table-column label="数量" align="center" prop="mount" />
      <el-table-column label="供货商" align="center" prop="supplyInfoId" />
      <el-table-column label="成本价" align="center" prop="costPrice" />
      <el-table-column label="询价时间" align="center" prop="priceDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.priceDate) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="订单成交时间  " align="center" prop="startTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="endTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endTime) }}</span>
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
                     v-hasPermi="['pro:order-cost:update']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['pro:order-cost:delete']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" v-dialogDrag append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="订单成本表业务id" prop="orderCostId">
          <el-input v-model="form.orderCostId" placeholder="请输入订单成本表业务id" />
        </el-form-item>
        <el-form-item label="产品id" prop="productId">
          <el-input v-model="form.productId" placeholder="请输入产品id" />
        </el-form-item>
        <el-form-item label="单价" prop="unitPrice">
          <el-input v-model="form.unitPrice" placeholder="请输入单价" />
        </el-form-item>
        <el-form-item label="数量" prop="mount">
          <el-input v-model="form.mount" placeholder="请输入数量" />
        </el-form-item>
        <el-form-item label="供货商id" prop="supplyInfoId">
          <el-input v-model="form.supplyInfoId" placeholder="请输入供货商id" />
        </el-form-item>
        <el-form-item label="成本价" prop="costPrice">
          <el-input v-model="form.costPrice" placeholder="请输入成本价" />
        </el-form-item>
        <el-form-item label="询价时间" prop="priceDate">
          <el-date-picker clearable v-model="form.priceDate" type="date" value-format="timestamp" placeholder="选择询价时间" />
        </el-form-item>
        <el-form-item label="开始时间(订单生成时间)" prop="startTime">
          <el-date-picker clearable v-model="form.startTime" type="date" value-format="timestamp" placeholder="选择开始时间(订单生成时间)" />
        </el-form-item>
        <el-form-item label="结束时间" prop="endTime">
          <el-date-picker clearable v-model="form.endTime" type="date" value-format="timestamp" placeholder="选择结束时间" />
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
import { createOrderCost, updateOrderCost, deleteOrderCost, getOrderCost, getOrderCostPage, exportOrderCostExcel } from "@/api/pro/orderCost";

export default {
  name: "OrderCost",
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
      // 订单成本列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        orderCostId: null,
        productId: null,
        unitPrice: null,
        mount: null,
        supplyInfoId: null,
        costPrice: null,
        priceDate: [],
        startTime: [],
        endTime: [],
        createTime: [],
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        orderCostId: [{ required: true, message: "订单成本表业务id不能为空", trigger: "blur" }],
        productId: [{ required: true, message: "产品id不能为空", trigger: "blur" }],
        supplyInfoId: [{ required: true, message: "供货商id不能为空", trigger: "blur" }],
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
      getOrderCostPage(this.queryParams).then(response => {
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
        orderCostId: undefined,
        productId: undefined,
        unitPrice: undefined,
        mount: undefined,
        supplyInfoId: undefined,
        costPrice: undefined,
        priceDate: undefined,
        startTime: undefined,
        endTime: undefined,
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
      this.title = "添加订单成本";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getOrderCost(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改订单成本";
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
          updateOrderCost(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createOrderCost(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除订单成本编号为"' + id + '"的数据项?').then(function() {
          return deleteOrderCost(id);
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
      this.$modal.confirm('是否确认导出所有订单成本数据项?').then(() => {
          this.exportLoading = true;
          return exportOrderCostExcel(params);
        }).then(response => {
          this.$download.excel(response, '订单成本.xls');
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
