<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" >
      <el-form-item label="状态" prop="status">
       <el-select v-model="queryParams.status" placeholder="请选择状态" clearable size="small" >
          <el-option v-for="dict in summaryStatusDict" :key="parseInt(dict.value)" :label="dict.label" :value="dict.value"/>
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime" label-width="68px">
        <el-date-picker v-model="queryParams.createTime" style="width: 240px" value-format="yyyy-MM-dd" type="daterange"
                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期"  />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作工具栏 -->
    <!-- <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                   v-hasPermi="['pro:curement-summary:create']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" :loading="exportLoading"
                   v-hasPermi="['pro:curement-summary:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row> -->
 <el-row :gutter="10" class="mb8">
      <el-col :span="3">
        <span class="summary-label">采购单总数: {{orderSummary.totalCount}}</span>
      </el-col>
      <el-col :span="3">
         <span class="summary-label">已经完成: {{orderSummary.finishCount}}</span>
      </el-col>
      <el-col :span="3">
         <span class="summary-label">正在进行: {{orderSummary.afootCount}}</span>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list" border>
      <el-table-column label="序号" align="center" prop="id" />
      <el-table-column label="订单编号" align="center" prop="orderId" />
      <el-table-column label="供货单号" align="center" prop="procurementSummaryId" />
      <el-table-column label="产品名称" align="center" prop="productName" />
      <el-table-column label="产品数量" align="center" prop="mount" />
      <el-table-column label="供货商" align="center" prop="supplyCompany" />
      <el-table-column label="采购金额" align="center" prop="procurementAmount" />
      <el-table-column label="定金" align="center" prop="depositAmount" />
      <el-table-column label="尾款" align="center" prop="balanceAmount" />
      <el-table-column label="状态" align="center" prop="supplyCompany" >
         <template slot-scope="scope">
          <dict-tag :type="DICT_TYPE.PROCUREMENT_SUMMARY_STATUS" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="完成进度" align="center" prop="finishPercent" />
      <el-table-column label="操作人" align="center" prop="buyer" />

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['pro:curement-summary:update']">修改</el-button>
<!--          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['pro:curement-summary:delete']">删除</el-button>-->
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="800px" v-dialogDrag append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="160px">
        <el-form-item label="订单号" prop="orderId">
          <span>{{ form.orderId }}</span>
        </el-form-item>
        <el-form-item label="供货商" prop="orderId">
          <span>{{ form.supplyCompany }}</span>
        </el-form-item>
        <el-form-item label="产品名称" prop="orderId">
          <span>{{ form.productName }}</span>
        </el-form-item>
        <el-form-item label="订单状态" prop="status">
          <!-- <el-input v-model="form.status" placeholder="请输入客户" /> -->
          <el-select v-model="form.status" placeholder="请选择状态" clearable size="small" >
            <el-option v-for="dict in summaryStatusDict" :key="parseInt(dict.value)" :label="dict.label" :value="parseInt(dict.value)"/>
          </el-select>
        </el-form-item>
        <el-form-item label="完成进度" prop="finishPercent">
          <el-input v-model="form.finishPercent" placeholder="请输入完成进度" />
        </el-form-item>
        <el-form-item label="采购金额" prop="procurementAmount">
          <el-input v-model="form.procurementAmount" placeholder="请输入采购金额" />
        </el-form-item>
        <el-form-item label="定金金额" prop="depositAmount">
          <el-input v-model="form.depositAmount" placeholder="请输入定金金额" />
        </el-form-item>
        <el-form-item label="尾款金额" prop="balanceAmount">
          <el-input v-model="form.balanceAmount" placeholder="请输入尾款金额" />
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

        <el-form-item label="运费" prop="freight">
          <el-input v-model="form.freight" placeholder="请输入运费" />
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
import { createcurementSummary, updatecurementSummary, deletecurementSummary, getcurementSummary, getcurementSummaryPage, exportcurementSummaryExcel,statistics } from "@/api/pro/curementSummary";
import {DICT_TYPE, getDictDatas} from "@/utils/dict";
export default {
  name: "curementSummary",
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
      // 采购汇总列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        status:undefined,
        createTime:undefined

      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        procurementSummaryId: [{ required: true, message: "采购汇总表id不能为空", trigger: "blur" }],
      },
      summaryStatusDict: getDictDatas(DICT_TYPE.PROCUREMENT_SUMMARY_STATUS),
      orderSummary:{}
    };
  },
  created() {
    this.getList();
    this.statistics()
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 执行查询
      console.log('getList',this.queryParams)
      getcurementSummaryPage(this.queryParams).then(response => {
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
    statistics(){
      statistics().then(result=>{
        console.log('statistics',result)
        this.orderSummary.afootCount = result.data.afootCount
        this.orderSummary.finishCount = result.data.finishCount
        this.orderSummary.totalCount = result.data.totalCount
      })
    },
    /** 表单重置 */
    reset() {
      this.form = {
        id: undefined,
        procurementSummaryId: undefined,
        orderId: undefined,
        orderChildId: undefined,
        supplyCompany: undefined,
        finishPercent: undefined,
        buyer: undefined,
        procurementAmount: undefined,
        depositAmount: undefined,
        balanceAmount: undefined,
        produceDate: undefined,
        confirmDate: undefined,
        endDate: undefined,
        result: undefined,
        volume: undefined,
        freight: undefined,
        deleveryTime: undefined,
        packTime: undefined,
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
      this.queryParams.createTime = undefined
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加采购汇总";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getcurementSummary({procurementSummaryId:row.procurementSummaryId}).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改采购汇总";
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
          updatecurementSummary(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createcurementSummary(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除采购汇总编号为"' + id + '"的数据项?').then(function() {
          return deletecurementSummary(id);
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
      this.$modal.confirm('是否确认导出所有采购汇总数据项?').then(() => {
          this.exportLoading = true;
          return exportcurementSummaryExcel(params);
        }).then(response => {
          this.$download.excel(response, '采购汇总.xls');
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
