package com.zkzl.module.system.service.notice;

import com.zkzl.framework.common.exception.util.ServiceExceptionUtil;
import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.module.system.controller.admin.notice.vo.NoticeCreateReqVO;
import com.zkzl.module.system.controller.admin.notice.vo.NoticePageReqVO;
import com.zkzl.module.system.controller.admin.notice.vo.NoticeUpdateReqVO;
import com.zkzl.module.system.convert.notice.NoticeConvert;
import com.zkzl.module.system.dal.mysql.notice.NoticeMapper;
import com.zkzl.module.system.dal.dataobject.notice.NoticeDO;
import com.google.common.annotations.VisibleForTesting;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.zkzl.module.system.enums.ErrorCodeConstants.NOTICE_NOT_FOUND;

/**
 * 通知公告 Service 实现类
 *
 * @author 芋道源码
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Resource
    private NoticeMapper noticeMapper;

    @Override
    public Long createNotice(NoticeCreateReqVO reqVO) {
        NoticeDO notice = NoticeConvert.INSTANCE.convert(reqVO);
        noticeMapper.insert(notice);
        return notice.getId();
    }

    @Override
    public void updateNotice(NoticeUpdateReqVO reqVO) {
        // 校验是否存在
        this.checkNoticeExists(reqVO.getId());
        // 更新通知公告
        NoticeDO updateObj = NoticeConvert.INSTANCE.convert(reqVO);
        noticeMapper.updateById(updateObj);
    }

    @Override
    public void deleteNotice(Long id) {
        // 校验是否存在
        this.checkNoticeExists(id);
        // 删除通知公告
        noticeMapper.deleteById(id);
    }

    @Override
    public PageResult<NoticeDO> pageNotices(NoticePageReqVO reqVO) {
        return noticeMapper.selectPage(reqVO);
    }

    @Override
    public NoticeDO getNotice(Long id) {
        return noticeMapper.selectById(id);
    }

    @VisibleForTesting
    public void checkNoticeExists(Long id) {
        if (id == null) {
            return;
        }
        NoticeDO notice = noticeMapper.selectById(id);
        if (notice == null) {
            throw ServiceExceptionUtil.exception(NOTICE_NOT_FOUND);
        }
    }

}
