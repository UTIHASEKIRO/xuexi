package com.zkzl.module.pro.service.supplyinfo;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.lang.generator.SnowflakeGenerator;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zkzl.framework.mybatis.core.util.MyBatisUtils;
import com.zkzl.module.pro.dal.dataobject.producttype.ProductTypeDO;
import me.ahoo.cosid.snowflake.SnowflakeFriendlyId;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.zkzl.module.pro.controller.admin.supplyinfo.vo.*;
import com.zkzl.module.pro.dal.dataobject.supplyinfo.SupplyInfoDO;
import com.zkzl.framework.common.pojo.PageResult;

import com.zkzl.module.pro.convert.supplyinfo.SupplyInfoConvert;
import com.zkzl.module.pro.dal.mysql.supplyinfo.SupplyInfoMapper;

import static com.zkzl.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.zkzl.module.system.enums.ErrorCodeConstants.SUPPLY_INFO_NOT_EXISTS;

/**
 * 供货商信息 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class SupplyInfoServiceImpl implements SupplyInfoService {

    @Resource
    private SupplyInfoMapper supplyInfoMapper;

    @Override
    public Long createSupplyInfo(SupplyInfoCreateReqVO createReqVO) {
        createReqVO.setSupplyInfoId(IdUtil.getSnowflakeNextIdStr());
        // 插入
        SupplyInfoDO supplyInfo = SupplyInfoConvert.INSTANCE.convert(createReqVO);
//        supplyInfo.setTypeId(IdUtil.getSnowflakeNextIdStr());
        supplyInfoMapper.insert(supplyInfo);
        // 返回
        return supplyInfo.getId();
    }

    @Override
    public void updateSupplyInfo(SupplyInfoUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateSupplyInfoExists(updateReqVO.getId());
        // 更新
        SupplyInfoDO updateObj = SupplyInfoConvert.INSTANCE.convert(updateReqVO);
        supplyInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteSupplyInfo(Long id) {
        // 校验存在
        this.validateSupplyInfoExists(id);
        // 删除
        supplyInfoMapper.deleteById(id);
    }

    private void validateSupplyInfoExists(Long id) {
        if (supplyInfoMapper.selectById(id) == null) {
            throw exception(SUPPLY_INFO_NOT_EXISTS);
        }
    }

    @Override
    public SupplyInfoDO getSupplyInfo(Long id) {
        return supplyInfoMapper.selectSupplyInfoById(id);
    }

    @Override
    public List<SupplyInfoDO> getSupplyInfoList(Collection<Long> ids) {
        return supplyInfoMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<SupplyInfoPageVO> getSupplyInfoPage(SupplyInfoPageReqVO pageReqVO) {
        IPage<SupplyInfoPageVO> mPage = MyBatisUtils.buildPage(pageReqVO);
        supplyInfoMapper.pageSupplyInfo(mPage,pageReqVO);
        return new PageResult<>(mPage.getRecords(), mPage.getTotal());
    }

    @Override
    public List<SupplyInfoDO> getSupplyInfoList(SupplyInfoExportReqVO exportReqVO) {
        return supplyInfoMapper.selectList(exportReqVO);
    }

    @Override
    public List<ProductTypeDO> getProductType() {
        return supplyInfoMapper.getProductType();
    }

    @Override
    public PageResult<SupplyInfoNameVO> getSupplyInfoName(SupplyInfoPageReqVO pageVO) {
        IPage<SupplyInfoNameVO> mPage = MyBatisUtils.buildPage(pageVO);
        supplyInfoMapper.getSupplyInfoName(mPage,pageVO);
        return new PageResult<>(mPage.getRecords(), mPage.getTotal());
    }

}
