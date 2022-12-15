package com.zkzl.module.pro.service.slidesshow;

import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.zkzl.module.pro.controller.admin.slidesshow.vo.*;
import com.zkzl.module.pro.dal.dataobject.slidesshow.SlidesShowDO;
import com.zkzl.framework.common.pojo.PageResult;

import com.zkzl.module.pro.convert.slidesshow.SlidesShowConvert;
import com.zkzl.module.pro.dal.mysql.slidesshow.SlidesShowMapper;

import static com.zkzl.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.zkzl.module.system.enums.ErrorCodeConstants.SLIDES_SHOW_NOT_EXISTS;

/**
 * 轮播图 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class SlidesShowServiceImpl implements SlidesShowService {

    @Resource
    private SlidesShowMapper slidesShowMapper;

    @Override
    public Long createSlidesShow(SlidesShowCreateReqVO createReqVO) {
        // 插入
        SlidesShowDO slidesShow = SlidesShowConvert.INSTANCE.convert(createReqVO);
        slidesShowMapper.insert(slidesShow);
        // 返回
        return slidesShow.getId();
    }

    @Override
    public void updateSlidesShow(SlidesShowUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateSlidesShowExists(updateReqVO.getId());
        // 更新
        SlidesShowDO updateObj = SlidesShowConvert.INSTANCE.convert(updateReqVO);
        slidesShowMapper.updateById(updateObj);
    }

    @Override
    public void deleteSlidesShow(Long id) {
        // 校验存在
        this.validateSlidesShowExists(id);
        // 删除
        slidesShowMapper.deleteById(id);
    }

    private void validateSlidesShowExists(Long id) {
        if (slidesShowMapper.selectById(id) == null) {
            throw exception(SLIDES_SHOW_NOT_EXISTS);
        }
    }

    @Override
    public SlidesShowDO getSlidesShow(Long id) {
        return slidesShowMapper.selectById(id);
    }

    @Override
    public List<SlidesShowDO> getSlidesShowList(Collection<Long> ids) {
        return slidesShowMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<SlidesShowDO> getSlidesShowPage(SlidesShowPageReqVO pageReqVO) {
        return slidesShowMapper.selectPage(pageReqVO);
    }

    @Override
    public List<SlidesShowDO> getSlidesShowList(SlidesShowExportReqVO exportReqVO) {
        return slidesShowMapper.selectList(exportReqVO);
    }

    @Override
    public List<SlidesShowDO> getSlidesList() {
        return slidesShowMapper.selectList(new LambdaQueryWrapperX<SlidesShowDO>()
                .eq(SlidesShowDO::getIsShelf,"1")
                .orderByAsc(SlidesShowDO::getSort));
    }

}
