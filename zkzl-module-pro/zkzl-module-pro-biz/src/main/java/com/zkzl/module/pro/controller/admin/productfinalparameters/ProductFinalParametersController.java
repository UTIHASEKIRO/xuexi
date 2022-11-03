package com.zkzl.module.pro.controller.admin.productfinalparameters;

import com.zkzl.module.pro.controller.admin.productfinalparametersname.vo.ProductFinalParametersNamePageReqVO;
import com.zkzl.module.pro.controller.admin.productfinalparametersname.vo.ProductFinalParametersNameRespVO;
import com.zkzl.module.pro.convert.productfinalparametersname.ProductFinalParametersNameConvert;
import com.zkzl.module.pro.dal.dataobject.productfinalparametersname.ProductFinalParametersNameDO;
import com.zkzl.module.pro.service.productfinalparametersname.ProductFinalParametersNameService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.*;
import javax.validation.*;
import java.util.*;
import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.common.pojo.CommonResult;
import static com.zkzl.framework.common.pojo.CommonResult.success;
import com.zkzl.module.pro.controller.admin.productfinalparameters.vo.*;
import com.zkzl.module.pro.dal.dataobject.productfinalparameters.ProductFinalParametersDO;
import com.zkzl.module.pro.convert.productfinalparameters.ProductFinalParametersConvert;
import com.zkzl.module.pro.service.productfinalparameters.ProductFinalParametersService;

@Api(tags = "管理后台 - 产品固定参数")
@RestController
@RequestMapping("/pro/duct-final-parameters")
@Validated
public class ProductFinalParametersController {

    @Resource
    private ProductFinalParametersService ductFinalParametersService;
    @Resource
    private ProductFinalParametersNameService ductFinalParametersNameService;

    @GetMapping("/page")
    @ApiOperation("产品固定参数查询")
    @PreAuthorize("@ss.hasPermission('pro:duct-final-parameters:query')")
    public CommonResult<PageResult<ProductFinalParametersNameRespVO>> getProductFinalParametersNamePage(@Valid ProductFinalParametersNamePageReqVO pageVO) {
        PageResult<ProductFinalParametersNameDO> pageResult = ductFinalParametersNameService.getductFinalParametersNamePage(pageVO);
        return success(ProductFinalParametersNameConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/valuePage")
    @ApiOperation("产品固定参数值查询")
    @PreAuthorize("@ss.hasPermission('pro:duct-final-parameters:query')")
    public CommonResult<PageResult<ProductFinalParametersRespVO>> updateductFinalParameters(@Valid ProductFinalParametersPageReqVO pageVO) {
        PageResult<ProductFinalParametersDO> pageResult = ductFinalParametersService.getductFinalParametersPage(pageVO);
        return success(ProductFinalParametersConvert.INSTANCE.convertPage(pageResult));
    }

    @PostMapping("/create")
    @ApiOperation("创建产品固定参数")
    @PreAuthorize("@ss.hasPermission('pro:duct-final-parameters:create')")
    public CommonResult<Long> createductFinalParameters(@Valid @RequestBody ProductFinalParametersCreateReqVO createReqVO) {
        return success(ductFinalParametersService.createductFinalParameters(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新产品固定参数")
    @PreAuthorize("@ss.hasPermission('pro:duct-final-parameters:update')")
    public CommonResult<Boolean> updateProductFinalParameters(@Valid @RequestBody ProductFinalParametersUpdateReqVO updateReqVO) {
        ductFinalParametersService.updateductFinalParameters(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除产品固定参数")
    @PreAuthorize("@ss.hasPermission('pro:duct-final-parameters:delete')")
    public CommonResult<Boolean> deleteductFinalParameters(@RequestParam("id") Long id) {
        ductFinalParametersService.deleteductFinalParameters(id);
        return success(true);
    }
}
