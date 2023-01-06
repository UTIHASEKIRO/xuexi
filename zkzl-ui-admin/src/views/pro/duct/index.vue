<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <!-- <el-form-item label="产品id" prop="productId">
        <el-input v-model="queryParams.productId" placeholder="请输入产品id" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="产品类别id" prop="typeId">
        <el-input v-model="queryParams.typeId" placeholder="请输入产品类别id" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item> -->
      <el-form-item label="产品名称" prop="productName">
        <el-input v-model="queryParams.productName" placeholder="请输入产品名称" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <!-- <el-form-item label="创建时间" prop="createTime">
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
                   v-hasPermi="['pro:duct:create']">新增</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" :loading="exportLoading"
                   v-hasPermi="['pro:duct:export']">导出</el-button>
      </el-col> -->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list" border>
      <el-table-column label="序号" align="center" prop="id" />
      <el-table-column label="产品名称" align="center" prop="productNameCn" />
      <el-table-column label="产品图片" align="center" prop="ductImgs" >
        <template  slot-scope="scope">
          <image-preview :src="scope.row.ductImgs" :width="'100px'"/>
        </template>
      </el-table-column>
      <el-table-column label="HS编号" align="center" prop="hsNo" />
      <el-table-column label="产品型号" align="center" prop="productModel" />
      <el-table-column label="用途分类" align="center" prop="useClassificationCn" />
      <el-table-column label="颜色" align="center" prop="colourCn" />
      <el-table-column label="包装方式" align="center" prop="packagingMethodCn" />
      <el-table-column label="交货方式" align="center" prop="deliveryMethodCn" />
      <el-table-column label="状态" align="center" prop="shipped" >
         <template slot-scope="scope" >
          <dict-tag :type="DICT_TYPE.SHIPPED" :value="scope.row.shipped"/>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['pro:duct:update']">修改</el-button>

          <el-button size="mini" type="text" icon="el-icon-delete" @click="updateShipped(scope.row)"
                     v-hasPermi="['pro:duct:update']">{{ scope.row.shipped=== '0'?'上架':'下架'}}</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="800px" v-dialogDrag append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

        <el-form-item label="产品名称"  prop="productNameCn">
          <el-input v-model="form.productNameCn" placeholder="请输入产品中文名称" />
        </el-form-item>
        <el-form-item  prop="productNameEn">
          <el-input v-model="form.productNameEn" placeholder="请输入产品英文名称" />
        </el-form-item>
        <el-form-item label="产品类型" prop="typeId">
          <!-- <el-input v-model="form.typeId" placeholder="请输入产品类型" /> -->
<!--          <el-cascader
            v-model="form.typeId"
            :options="ductTypeList"
            placeholder="请输入产品类型"
            clearable size="small" style="width: 240px"
            :props="{children:'productTypeDOS',value:'typeId',label:'typeNameCn'}"
            >
          </el-cascader>-->
           <el-select v-model="form.typeId" placeholder="请输入产品类型" clearable size="small" style="width: 240px">
            <el-option v-for="dict in ductTypeList" :key="parseInt(dict.typeId)" :label="dict.typeNameCn" :value="dict.typeId"/>
          </el-select>
        </el-form-item>
        <el-form-item label="产品型号" prop="productModel">
          <el-input v-model="form.productModel" placeholder="请输入产品型号" />
        </el-form-item>
        <el-form-item label="HS编号" prop="hsNo">
          <el-input v-model="form.hsNo" placeholder="请输入HS编号" />
        </el-form-item>
        <el-form-item label="包装方式" prop="packagingMethodCn">
          <!-- <el-input v-model="form.packagingMethodCn" placeholder="请输入包装方式" /> -->
          <el-select v-model="form.packagingMethodCn" placeholder="请输入包装方式" clearable size="small" style="width: 240px">
            <el-option v-for="dict in packageList" :key="parseInt(dict.id)" :label="dict.valueCn" :value="dict.valueCn"/>
          </el-select>
        </el-form-item>
        <el-form-item label="颜色" prop="colourCn">
          <!-- <el-input v-model="form.colourCn" placeholder="请输入颜色" /> -->
          <el-select v-model="form.colourCn" placeholder="请输入颜色" clearable size="small" style="width: 240px">
            <el-option v-for="dict in colorList" :key="parseInt(dict.id)" :label="dict.valueCn" :value="dict.valueCn"/>
          </el-select>
        </el-form-item>
        <el-form-item label="交货方式" prop="deliveryMethodCn">
          <!-- <el-input v-model="form.deliveryMethodCn" placeholder="请输入交货方式" /> -->
           <el-select v-model="form.deliveryMethodCn" placeholder="请输入交货方式" clearable size="small" style="width: 240px">
            <el-option v-for="dict in deliveryList" :key="parseInt(dict.id)" :label="dict.valueCn" :value="dict.valueCn"/>
          </el-select>
        </el-form-item>
        <el-form-item label="用途分类" prop="useClassificationCn">
          <!-- <el-input v-model="form.useClassificationCn" placeholder="请输入用途分类" /> -->
          <el-select v-model="form.useClassificationCn" placeholder="请输入用途分类" clearable size="small" style="width: 240px">
            <el-option v-for="dict in useList" :key="parseInt(dict.id)" :label="dict.valueCn" :value="dict.valueCn"/>
          </el-select>
        </el-form-item>
        <el-form-item label="克重" prop="gramWeight">
          <el-input v-model="form.gramWeight" placeholder="请输入克重" />
        </el-form-item>
        <el-form-item label="尺寸" prop="size">
          <el-input v-model="form.size" placeholder="请输入尺寸" />
        </el-form-item>
        <el-form-item label="箱规" prop="boxGauge">
          <el-input v-model="form.boxGauge" placeholder="请输入箱规" />
        </el-form-item>
        <el-form-item label="体积" prop="volume">
          <el-input v-model="form.volume" placeholder="请输入体积" />
        </el-form-item>
       <el-form-item label="毛重" prop="grossWeight">
          <el-input v-model="form.grossWeight" placeholder="请输入毛重" />
        </el-form-item>
        <el-form-item label="净重" prop="netWeight">
          <el-input v-model="form.netWeight" placeholder="请输入净重" />
        </el-form-item>
        <el-form-item label="图片" prop="picDOS">
          <!-- <el-input v-model="form.typeId" placeholder="请输入产品类别id" /> -->
          <imageUpload v-model="form.picDOSTemp" :limit="8" fileWh="380*380"/>
        </el-form-item>
        <el-form-item label="证书" prop="productCertificateDOS">
          <!-- <el-input v-model="form.productCertificateDOS" placeholder="请输入产品类别id" /> -->
          <imageUpload v-model="form.productCertificateDOSTemp" :limit="5"/>
        </el-form-item>
        <el-form-item label="其他参数" >
         <el-button type="primary" @click="openDyanicDialog">添 加</el-button>
         <div class="dynamic-item" v-for="(item,index) in form.productParametersDOS" :key="item.parameterEn">
          <el-input v-model="item.parameterCn" disabled />
          <el-input v-model="item.valueCn" disabled />
          <el-input v-model="item.parameterEn" disabled />
          <el-input v-model="item.valueCn" disabled />
          <el-button type="danger" @click="deleteDynamic(index)">删除</el-button>

         </div>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
     <el-dialog title="添加参数" :visible.sync="dynamicParamDialogFlag" width="500px"  append-to-body>
      <el-form ref="dynamicform" :model="dynamicParamForm" :rules="dynamicParamRules" label-width="100px">
        <el-form-item label="名称-中文" prop="parameterCn">
          <el-input v-model="dynamicParamForm.parameterCn" placeholder="请输入名称-中文" />
        </el-form-item>
         <el-form-item label="名称-英文" prop="parameterEn">
          <el-input v-model="dynamicParamForm.parameterEn" placeholder="请输入名称-英文" />
        </el-form-item>
         <el-form-item label="值-中文" prop="valueCn">
          <el-input v-model="dynamicParamForm.valueCn" placeholder="请输入值-中文" />
        </el-form-item>
         <el-form-item label="值-英文" prop="valueEn">
          <el-input v-model="dynamicParamForm.valueEn" placeholder="请输入值-英文" />
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitDynamicForm">确 定</el-button>
        <el-button @click="cancelDynamic">取 消</el-button>
      </div>
     </el-dialog>
  </div>
</template>

<script>
import { createduct, updateduct, deleteduct, getduct, getductPage, exportductExcel,updateShipped } from "@/api/pro/duct";
import {getductParametersPage} from "@/api/pro/ductParameters";
import { ductTypeList} from "@/api/pro/ductType"
import {  getductFinalParametersPage } from "@/api/pro/ductFinalParameters";
import ImageUpload from '@/components/ImageUpload';
import ImagePreview from "@/components/ImagePreview";
import {DICT_TYPE, getDictDatas} from "@/utils/dict";
export default {
  name: "duct",
  components: {
    ImageUpload,ImagePreview
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
      // 产品列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        productId: null,
        typeId: null,
        productName: null,
        createTime: [],
      },
      // 表单参数
      form: {
        productParametersDOS:[]
      },
      dynamicParamDialogFlag:false,
      dynamicParamForm:{
        parameterCn:undefined,
        parameterEn:undefined,
        valueCn:undefined,
        valueEn:undefined,
      },
      dynamicParamRules:{
        parameterCn: [{ required: true, message: "名称-中文不能为空", trigger: "blur" }],
        parameterEn: [{ required: true, message: "名称-英文不能为空", trigger: "blur" }],
        valueCn: [{ required: true, message: "值-中文不能为空", trigger: "blur" }],
        valueEn: [{ required: true, message: "值-英文不能为空", trigger: "blur" }],
      },
      // 表单校验
      rules: {
        productNameCn: [{ required: true, message: "产品名称不能为空", trigger: "blur" }],
        productNameEn: [{ required: true, message: "产品名称不能为空", trigger: "blur" }],
        typeId: [{ required: true, message: "产品类型不能为空", trigger: "blur" }],
        productModel: [{ required: true, message: "产品型号不能为空", trigger: "blur" }],
        hsNO: [{ required: true, message: "HS编号不能为空", trigger: "blur" }],
        packagingMethodCn: [{ required: true, message: "包装方式不能为空", trigger: "blur" }],
        colourCn: [{ required: true, message: "颜色不能为空", trigger: "blur" }],
        deliveryMethodCn: [{ required: true, message: "交货方式不能为空", trigger: "blur" }],
        useClassificationCn: [{ required: true, message: "用途分类不能为空", trigger: "blur" }],
        gramWeight: [{ required: true, message: "克重不能为空", trigger: "blur" }],
        size: [{ required: true, message: "尺寸不能为空", trigger: "blur" }],
        boxGauge: [{ required: true, message: "箱规不能为空", trigger: "blur" }],
        volume: [{ required: true, message: "体积不能为空", trigger: "blur" }],
        grossWeight: [{ required: true, message: "毛重不能为空", trigger: "blur" }],
        netWeight: [{ required: true, message: "净重不能为空", trigger: "blur" }],
      },
      ductTypeList:[],
      colorList:[],//颜色列表
      useList:[],//用途分类列表
      deliveryList:[],//交货方式列表
      packageList:[],//包装方式列表
    };
  },
  created() {
    this.getList();
    this.getductPage();
    this.getDuctType();
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 执行查询
      getductPage(this.queryParams).then(response => {
        response.data.list.forEach(ele=>{
          ele.ductImgs = ele.picDOS.map(pic=>pic.url).join(',')
        })
        this.list = response.data.list;
        this.total = response.data.total;
        this.loading = false;
      });
    },
    updateShipped(row){
      console.log('updateShipped',row)
      var status = row.shipped=== '0'?'上架':'下架'
      this.$modal.confirm('是否确认'+status+'产品名称为"' + row.productNameCn + '"的数据项?').then(function() {
          return updateShipped({productId:row.productId});
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess(status+"成功");
        }).catch(() => {});

    },
    /** 取消按钮 */
    cancel() {
      this.open = false;
      this.reset();
    },
    cancelDynamic(){
      this.resetDynamicParamsForm()
      this.dynamicParamDialogFlag = false
    },
    submitDynamicForm(){
       this.$refs["dynamicform"].validate(valid => {
        if (!valid) {
          return;
        }
        if(!this.form.productParametersDOS){
          this.form.productParametersDOS = []
        }
        this.form.productParametersDOS.push(this.dynamicParamForm)
        this.resetDynamicParamsForm()
        this.dynamicParamDialogFlag = false

      });
    },
    deleteDynamic(index){
      // this.$delete(this.form.productParametersDOS,index)
      this.form.productParametersDOS.splice(index,1)
      // this.form.productParametersDOS = this.form.productParametersDOS
      console.log('deleteDynamic',this.form.productParametersDOS)
    },
    resetDynamicParamsForm(){
      this.dynamicParamForm = {}
    },
    /** 表单重置 */
    reset() {
      this.form = {
        id: undefined,
        productId: undefined,
        typeId: undefined,
        productName: undefined,
        productParametersDOS:[]
      };
      this.resetForm("form");
    },
    openDyanicDialog(){
      this.resetDynamicParamsForm()
      this.dynamicParamDialogFlag = true
    },
    //获取产品类别列表
    getDuctType(){
      ductTypeList({typeName:''}).then(result=>{
        this.ductTypeList = result.data
      })
    },
    /**查询固定参数 */
    getductPage(){
      this.getCategoryList({parameterCn:'颜色',parameterEn:'colour'},'colorList')
      this.getCategoryList({parameterCn:'用途分类',parameterEn:'Use classification'},'useList')
      this.getCategoryList({parameterCn:'交货方式',parameterEn:'Delivery method'},'deliveryList')
      this.getCategoryList({parameterCn:'包装方式',parameterEn:'Packaging method'},'packageList')
    },
    getCategoryList(param,list){
      getductParametersPage(param).then(result=>{
        this[list] = result.data.list
        console.log('getCategoryList',this[list])
      })
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNo = 1;
      this.getList();
      console.log(this.colorList,this.useList,this.deliveryList)
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
      this.title = "添加产品";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.productId;
      getduct({productId:id}).then(response => {
        this.form = response.data;
        if(this.form.picDOS) {
          var pictemp = this.form.picDOS
          var picDosTemp = ''
          pictemp.forEach(ele => {
            picDosTemp = picDosTemp + ele.url + ","
          })
          this.form.picDOSTemp = picDosTemp
          this.form.picDOSTemp = this.form.picDOSTemp.substr(0,this.form.picDOSTemp.length-1);
        }
        if(this.form.productCertificateDOS) {
          var certTemp = this.form.productCertificateDOS
          var certDosTemp = ''
          certTemp.forEach(ele => {
            certDosTemp = certDosTemp + ele.url + ","
          })
          this.form.productCertificateDOSTemp = certDosTemp
          this.form.productCertificateDOSTemp = this.form.productCertificateDOSTemp.substr(0,this.form.productCertificateDOSTemp.length-1);
        }
        this.open = true;
        this.title = "修改产品";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (!valid) {
          return;
        }
        //处理中英文字段
        this.packageList.forEach(ele=>{
          if(ele.valueCn === this.form.packagingMethodCn){
            this.form.packagingMethodEn = ele.valueEn
          }
        })
         this.colorList.forEach(ele=>{
          if(ele.valueCn === this.form.colourCn){
            this.form.colourEn = ele.valueEn
          }
        })
        this.deliveryList.forEach(ele=>{
          if(ele.valueCn === this.form.deliveryMethodCn){
            this.form.deliveryMethodEn = ele.valueEn
          }
        })
        this.useList.forEach(ele=>{
          if(ele.valueCn === this.form.useClassificationCn){
            this.form.useClassificationEn = ele.valueEn
          }
        })
        //修改图片地址
        if(this.form.picDOSTemp){
        var temp =this.form.picDOSTemp
        this.form.picDOS = []
        temp = temp.split(',')
        temp.forEach(ele=>{
          this.form.picDOS.push({url:ele})
        })
        }else{
          this.form.picDOS = []
        }
       if(this.form.productCertificateDOSTemp){
        var temp =this.form.productCertificateDOSTemp
        this.form.productCertificateDOS = []
        temp = temp.split(',')
        temp.forEach(ele=>{
          this.form.productCertificateDOS.push({certificateUrl:ele})
        })
        }else{
          this.form.productCertificateDOS = []
        }

        // if(this.form.productCertificateDOS.indexOf(',')!=-1){
        //   this.form.productCertificateDOS = this.form.productCertificateDOS.split(',')
        // }
        // 修改的提交
        if (this.form.id != null) {
          updateduct(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createduct(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除产品编号为"' + id + '"的数据项?').then(function() {
          return deleteduct(id);
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
      this.$modal.confirm('是否确认导出所有产品数据项?').then(() => {
          this.exportLoading = true;
          return exportductExcel(params);
        }).then(response => {
          this.$download.excel(response, '产品.xls');
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
<style lang="scss" scoped>
.dynamic-item{
  display: grid;
  grid-template-columns: 1fr 1fr;
  margin-bottom: 20px;

}
</style>
