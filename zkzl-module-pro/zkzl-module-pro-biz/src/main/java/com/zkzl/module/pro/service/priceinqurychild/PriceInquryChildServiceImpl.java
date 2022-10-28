package com.zkzl.module.pro.service.priceinqurychild;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.zkzl.module.pro.controller.admin.priceinqurychild.vo.*;
import com.zkzl.module.pro.dal.dataobject.priceinqurychild.PriceInquryChildDO;
import com.zkzl.framework.common.pojo.PageResult;

import com.zkzl.module.pro.convert.priceinqurychild.PriceInquryChildConvert;
import com.zkzl.module.pro.dal.mysql.priceinqurychild.PriceInquryChildMapper;

import static com.zkzl.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.zkzl.module.system.enums.ErrorCodeConstants.PRICE_INQURY_CHILD_NOT_EXISTS;

/**
 * 询价表子 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class PriceInquryChildServiceImpl implements PriceInquryChildService {

    @Resource
    private PriceInquryChildMapper priceInquryChildMapper;

    @Override
    public Long createPriceInquryChild(PriceInquryChildCreateReqVO createReqVO) {
        // 插入
        PriceInquryChildDO priceInquryChild = PriceInquryChildConvert.INSTANCE.convert(createReqVO);
        priceInquryChildMapper.insert(priceInquryChild);
        // 返回
        return priceInquryChild.getId();
    }

    @Override
    public void updatePriceInquryChild(PriceInquryChildUpdateReqVO updateReqVO) {
        // 校验存在
        this.validatePriceInquryChildExists(updateReqVO.getId());
        // 更新
        PriceInquryChildDO updateObj = PriceInquryChildConvert.INSTANCE.convert(updateReqVO);
        priceInquryChildMapper.updateById(updateObj);
    }

    @Override
    public void deletePriceInquryChild(Long id) {
        // 校验存在
        this.validatePriceInquryChildExists(id);
        // 删除
        priceInquryChildMapper.deleteById(id);
    }

    private void validatePriceInquryChildExists(Long id) {
        if (priceInquryChildMapper.selectById(id) == null) {
            throw exception(PRICE_INQURY_CHILD_NOT_EXISTS);
        }
    }

    @Override
    public PriceInquryChildDO getPriceInquryChild(Long id) {
        return priceInquryChildMapper.selectById(id);
    }

    @Override
    public List<PriceInquryChildDO> getPriceInquryChildList(Collection<Long> ids) {
        return priceInquryChildMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<PriceInquryChildDO> getPriceInquryChildPage(PriceInquryChildPageReqVO pageReqVO) {
        return priceInquryChildMapper.selectPage(pageReqVO);
    }

    @Override
    public List<PriceInquryChildDO> getPriceInquryChildList(PriceInquryChildExportReqVO exportReqVO) {
        return priceInquryChildMapper.selectList(exportReqVO);
    }

}
