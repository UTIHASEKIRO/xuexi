package com.zkzl.module.pro.service.priceinqury;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.zkzl.module.pro.controller.admin.priceinqury.vo.*;
import com.zkzl.module.pro.dal.dataobject.priceinqury.PriceInquryDO;
import com.zkzl.framework.common.pojo.PageResult;

import com.zkzl.module.pro.convert.priceinqury.PriceInquryConvert;
import com.zkzl.module.pro.dal.mysql.priceinqury.PriceInquryMapper;

import static com.zkzl.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.zkzl.module.system.enums.ErrorCodeConstants.PRICE_INQURY_NOT_EXISTS;

/**
 * 询价 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class PriceInquryServiceImpl implements PriceInquryService {

    @Resource
    private PriceInquryMapper priceInquryMapper;

    @Override
    public Long createPriceInqury(PriceInquryCreateReqVO createReqVO) {
        // 插入
        PriceInquryDO priceInqury = PriceInquryConvert.INSTANCE.convert(createReqVO);
        priceInquryMapper.insert(priceInqury);
        // 返回
        return priceInqury.getId();
    }

    @Override
    public void updatePriceInqury(PriceInquryUpdateReqVO updateReqVO) {
        // 校验存在
        this.validatePriceInquryExists(updateReqVO.getId());
        // 更新
        PriceInquryDO updateObj = PriceInquryConvert.INSTANCE.convert(updateReqVO);
        priceInquryMapper.updateById(updateObj);
    }

    @Override
    public void deletePriceInqury(Long id) {
        // 校验存在
        this.validatePriceInquryExists(id);
        // 删除
        priceInquryMapper.deleteById(id);
    }

    private void validatePriceInquryExists(Long id) {
        if (priceInquryMapper.selectById(id) == null) {
            throw exception(PRICE_INQURY_NOT_EXISTS);
        }
    }

    @Override
    public PriceInquryDO getPriceInqury(Long id) {
        return priceInquryMapper.selectById(id);
    }

    @Override
    public List<PriceInquryDO> getPriceInquryList(Collection<Long> ids) {
        return priceInquryMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<PriceInquryDO> getPriceInquryPage(PriceInquryPageReqVO pageReqVO) {
        return priceInquryMapper.selectPage(pageReqVO);
    }

    @Override
    public List<PriceInquryDO> getPriceInquryList(PriceInquryExportReqVO exportReqVO) {
        return priceInquryMapper.selectList(exportReqVO);
    }

}
