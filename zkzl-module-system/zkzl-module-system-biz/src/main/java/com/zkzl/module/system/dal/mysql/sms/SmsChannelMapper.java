package com.zkzl.module.system.dal.mysql.sms;

import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.mybatis.core.mapper.BaseMapperX;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.module.system.controller.admin.sms.vo.channel.SmsChannelPageReqVO;
import com.zkzl.module.system.dal.dataobject.sms.SmsChannelDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

@Mapper
public interface SmsChannelMapper extends BaseMapperX<SmsChannelDO> {

    default PageResult<SmsChannelDO> selectPage(SmsChannelPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SmsChannelDO>()
                .likeIfPresent(SmsChannelDO::getSignature, reqVO.getSignature())
                .eqIfPresent(SmsChannelDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(SmsChannelDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SmsChannelDO::getId));
    }

    @Select("SELECT COUNT(*) FROM system_sms_channel WHERE update_time > #{maxUpdateTime}")
    Long selectCountByUpdateTimeGt(Date maxUpdateTime);

}
