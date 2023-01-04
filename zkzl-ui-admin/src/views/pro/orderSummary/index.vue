<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" >

      <el-form-item label="状态" prop="status">
        <!-- <el-input v-model="queryParams.status" placeholder="请选择订单" clearable @keyup.enter.native="handleQuery"/> -->
         <el-select v-model="queryParams.status" placeholder="请选择状态" clearable size="small" >
          <el-option v-for="dict in orderStatusDict" :key="parseInt(dict.value)" :label="dict.label" :value="dict.value"/>
        </el-select>
      </el-form-item>

      <el-form-item label="包装确认日期" prop="signTime" label-width="108px">
        <el-date-picker v-model="queryParams.signTime" style="width: 240px" value-format="yyyy-MM-dd HH:mm:ss" type="daterange"
                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" :default-time="['00:00:00', '23:59:59']" />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作工具栏 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="3">
        <span class="summary-label">订单总数: {{orderSummary.allOrder}}</span>
      </el-col>
      <el-col :span="3">
         <span class="summary-label">已经完成: {{orderSummary.finishOrder}}</span>
      </el-col>
      <el-col :span="3">
         <span class="summary-label">正在进行: {{orderSummary.ingOrder}}</span>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list" border>
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="询价单号" align="center" prop="priceInquryId" />
      <el-table-column label="订单号" align="center" prop="orderId" />
      <el-table-column label="公司名称" align="center" prop="companyName" />
      <el-table-column label="联系人" align="center" prop="contectName" />
      <el-table-column label="联系电话" align="center" prop="mobile" />
      <el-table-column label="签约时间" align="center" prop="createTime" />
      <el-table-column label="合同金额" align="center" prop="contractAmount" />
      <el-table-column label="定金金额" align="center" prop="depositAmount" />
      <el-table-column label="尾款金额" align="center" prop="balanceAmount" />
      <el-table-column label="状态" align="center" prop="status" >
        <template slot-scope="scope">
          <dict-tag :type="DICT_TYPE.ORDER_STATUS" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="完成进度" align="center" prop="finishPercent" />
      <el-table-column label="业务员" align="center" prop="salesman" />

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button  v-if="scope.row.status !== '0'" size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['pro:order-summary:update']">修改</el-button>
          <!-- <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['pro:order-summary:delete']">删除</el-button> -->
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" v-dialogDrag append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="订单id" prop="orderId">
          <el-input v-model="form.orderId" disabled placeholder="请输入订单id" />
        </el-form-item>
        <el-form-item label="客户" prop="customer">
          <el-input v-model="form.customer" placeholder="请输入客户" />
        </el-form-item>
        <el-form-item label="订单状态" prop="status">
          <!-- <el-input v-model="form.status" placeholder="请输入客户" /> -->
          <el-select v-model="form.status" placeholder="请选择状态" clearable size="small" >
          <el-option v-for="dict in orderStatusDict" :key="dict.value" :label="dict.label" :value="dict.value"/>
        </el-select>
        </el-form-item>
        <el-form-item label="完成进度" prop="finishPercent">
          <!-- <el-input v-model="form.finishPercent" placeholder="请输入完成进度" /> -->
          <el-input-number class="input-number" v-model="form.finishPercent"  controls-position="right"  :min="0" :max='100' placeholder="请输入完成进度"></el-input-number>

        </el-form-item>
        <!-- <el-form-item label="业务员" prop="salesman">
          <el-input v-model="form.salesman" placeholder="请输入业务员" />
        </el-form-item> -->
        <el-form-item label="合同金额" prop="contractAmount">
          <el-input-number class="input-number" v-model="form.contractAmount"  controls-position="right"  :min="0"  :precision="2" placeholder="请输入合同金额"></el-input-number>

          <!-- <el-input v-model="form.contractAmount" placeholder="请输入合同金额" /> -->
        </el-form-item>
        <el-form-item label="定金金额" prop="depositAmount">
          <el-input-number class="input-number" v-model="form.depositAmount"  controls-position="right"  :min="0"  :precision="2" placeholder="请输入定金金额"></el-input-number>

          <!-- <el-input v-model="form.depositAmount" placeholder="请输入定金金额" /> -->
        </el-form-item>
        <el-form-item label="尾款金额" prop="balanceAmount">
          <el-input-number class="input-number" v-model="form.balanceAmount"  controls-position="right"  :min="0"  :precision="2" placeholder="请输入尾款金额"></el-input-number>
          <!-- <el-input v-model="form.balanceAmount" placeholder="请输入尾款金额" /> -->
        </el-form-item>
        <el-form-item label="生产日期" prop="produceDate">
          <el-date-picker clearable v-model="form.produceDate" type="date" value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择生产日期" />
        </el-form-item>
        <el-form-item label="印刷包装确认日期" prop="confirmDate">
          <el-date-picker clearable v-model="form.confirmDate" type="date" value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择印刷包装确认日期" />
        </el-form-item>
        <el-form-item label="结束日期" prop="endDate">
          <el-date-picker clearable v-model="form.endDate" type="date" value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择结束日期" />
        </el-form-item>
        <el-form-item label="产品测试结果汇报" prop="result">
          <el-input v-model="form.result" placeholder="请输入产品测试结果汇报" />
        </el-form-item>
        <el-form-item label="体积" prop="volume">
          <el-input v-model="form.volume" placeholder="请输入体积" />
        </el-form-item>
        <el-form-item label="装柜时间" prop="packTime">
          <el-date-picker clearable v-model="form.packTime" type="date" value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择装柜时间" />
        </el-form-item>
        <el-form-item label="发货时间" prop="deleveryTime">
          <el-date-picker clearable v-model="form.deleveryTime" type="date" value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择发货时间" />
        </el-form-item>
        <el-form-item label="结束时间" prop="endingTime">
          <el-date-picker clearable v-model="form.endingTime" type="date" value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择结束时间" />
        </el-form-item>
        <el-form-item label="备注" prop="remarks">
          <el-input v-model="form.remarks" placeholder="请输入备注" />
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
import { createOrderSummary, updateOrderSummary, deleteOrderSummary, getOrderSummary, getOrderSummaryPage, exportOrderSummaryExcel,pageOrderSummary,pageOrderCount } from "@/api/pro/orderSummary";
import {DICT_TYPE, getDictDatas} from "@/utils/dict";
export default {
  name: "OrderSummary",
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
      // 订单汇总列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        orderSummaryId: null,
        orderId: null,
        customer: null,
        finishPercent: null,
        salesman: null,
        contractAmount: null,
        depositAmount: null,
        balanceAmount: null,
        produceDate: [],
        confirmDate: [],
        endDate: [],
        result: null,
        volume: null,
        packTime: [],
        deleveryTime: [],
        endingTime: [],
        remarks: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        orderSummaryId: [{ required: true, message: "订单汇总表id不能为空", trigger: "blur" }],
      },
      orderSummary:{},
      orderStatusDict: getDictDatas(DICT_TYPE.ORDER_STATUS),
    };
  },
  created() {
    this.getList();
    this.pageOrderCount()
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 执行查询
      pageOrderSummary(this.queryParams).then(response => {
        this.list = response.data.list;
        this.total = response.data.total;
        this.loading = false;
      });
    },
    pageOrderCount(){
      pageOrderCount().then(result=>{
        this.orderSummary  = result.data
      })
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
        orderSummaryId: undefined,
        orderId: undefined,
        customer: undefined,
        finishPercent: undefined,
        salesman: undefined,
        contractAmount: undefined,
        depositAmount: undefined,
        balanceAmount: undefined,
        produceDate: undefined,
        confirmDate: undefined,
        endDate: undefined,
        result: undefined,
        volume: undefined,
        packTime: undefined,
        deleveryTime: undefined,
        endingTime: undefined,
        remarks: undefined,
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
      this.title = "添加订单汇总";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getOrderSummary(id).then(response => {
        // response.data.produceDate = response.data.produceDate.split(' ')[0]
        this.form = response.data;
        console.log('handleUpdate',this.form)
        this.open = true;
        this.title = "修改订单汇总";
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
          updateOrderSummary(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createOrderSummary(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除订单汇总编号为"' + id + '"的数据项?').then(function() {
          return deleteOrderSummary(id);
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
      this.$modal.confirm('是否确认导出所有订单汇总数据项?').then(() => {
          this.exportLoading = true;
          return exportOrderSummaryExcel(params);
        }).then(response => {
          this.$download.excel(response, '订单汇总.xls');
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
<style lang="scss" scoped>
.summary-label{
  font-size: 14px;
    color: #606266;
    font-weight: 700;
}
</style>
<style>
.input-number{
width: 180px !important;
}
</style>
