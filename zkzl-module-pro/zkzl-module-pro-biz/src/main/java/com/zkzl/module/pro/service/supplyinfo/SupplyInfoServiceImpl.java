package com.zkzl.module.pro.service.supplyinfo;

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
        // 插入
        SupplyInfoDO supplyInfo = SupplyInfoConvert.INSTANCE.convert(createReqVO);
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
        return supplyInfoMapper.selectById(id);
    }

    @Override
    public List<SupplyInfoDO> getSupplyInfoList(Collection<Long> ids) {
        return supplyInfoMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<SupplyInfoDO> getSupplyInfoPage(SupplyInfoPageReqVO pageReqVO) {
        return supplyInfoMapper.selectPage(pageReqVO);
    }

    @Override
    public List<SupplyInfoDO> getSupplyInfoList(SupplyInfoExportReqVO exportReqVO) {
        return supplyInfoMapper.selectList(exportReqVO);
    }

}
