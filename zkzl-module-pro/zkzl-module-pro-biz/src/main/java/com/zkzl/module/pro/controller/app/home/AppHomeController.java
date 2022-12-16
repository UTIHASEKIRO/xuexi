package com.zkzl.module.pro.controller.app.home;

import com.zkzl.framework.common.pojo.CommonResult;
import com.zkzl.module.pro.controller.admin.company.vo.CompanyRespVO;
import com.zkzl.module.pro.controller.admin.partners.vo.PartnersExportReqVO;
import com.zkzl.module.pro.controller.admin.partners.vo.PartnersRespVO;
import com.zkzl.module.pro.convert.partners.PartnersConvert;
import com.zkzl.module.pro.dal.dataobject.partners.PartnersDO;
import com.zkzl.module.pro.service.company.CompanyService;
import com.zkzl.module.pro.service.partners.PartnersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import java.util.List;

import static com.zkzl.framework.common.pojo.CommonResult.success;

@Api(tags = "客户端 - 首页")
@RestController
@RequestMapping("/pro/home")
@Validated
public class AppHomeController {

    @Resource
    private CompanyService companyService;

    @Resource
    private PartnersService partnersService;

    @GetMapping("/companyIntroduction")
    @ApiOperation("公司介绍(关于我们)")
    @PermitAll
    public CommonResult<CompanyRespVO> getCompanyIntroduction() {
        CompanyRespVO companyRespVO = companyService.getCompanyIntroduction();
        return success(companyRespVO);
    }

    @GetMapping("/partners")
    @ApiOperation("合作伙伴")
    @PermitAll
    public CommonResult<List<PartnersRespVO>> getPartners() {
        List<PartnersDO> list = partnersService.getPartnersList(new PartnersExportReqVO());
        return success(PartnersConvert.INSTANCE.convertList(list));
    }

}
