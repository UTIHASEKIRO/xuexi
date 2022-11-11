package com.zkzl.module.pro.service.partners;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.zkzl.framework.test.core.ut.BaseDbUnitTest;

import com.zkzl.module.pro.controller.admin.partners.vo.*;
import com.zkzl.module.pro.dal.dataobject.partners.PartnersDO;
import com.zkzl.module.pro.dal.mysql.partners.PartnersMapper;
import com.zkzl.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;

import static cn.hutool.core.util.RandomUtil.*;
import static com.zkzl.module.pro.enums.ErrorCodeConstants.*;
import static com.zkzl.framework.test.core.util.AssertUtils.*;
import static com.zkzl.framework.test.core.util.RandomUtils.*;
import static com.zkzl.framework.common.util.object.ObjectUtils.*;
import static com.zkzl.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
* {@link PartnersServiceImpl} 的单元测试类
*
* @author 芋道源码
*/
@Import(PartnersServiceImpl.class)
public class PartnersServiceImplTest extends BaseDbUnitTest {

    @Resource
    private PartnersServiceImpl partnersService;

    @Resource
    private PartnersMapper partnersMapper;

    @Test
    public void testCreatePartners_success() {
        // 准备参数
        PartnersCreateReqVO reqVO = randomPojo(PartnersCreateReqVO.class);

        // 调用
        Long partnersId = partnersService.createPartners(reqVO);
        // 断言
        assertNotNull(partnersId);
        // 校验记录的属性是否正确
        PartnersDO partners = partnersMapper.selectById(partnersId);
        assertPojoEquals(reqVO, partners);
    }

    @Test
    public void testUpdatePartners_success() {
        // mock 数据
        PartnersDO dbPartners = randomPojo(PartnersDO.class);
        partnersMapper.insert(dbPartners);// @Sql: 先插入出一条存在的数据
        // 准备参数
        PartnersUpdateReqVO reqVO = randomPojo(PartnersUpdateReqVO.class, o -> {
            o.setId(dbPartners.getId()); // 设置更新的 ID
        });

        // 调用
        partnersService.updatePartners(reqVO);
        // 校验是否更新正确
        PartnersDO partners = partnersMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, partners);
    }

    @Test
    public void testUpdatePartners_notExists() {
        // 准备参数
        PartnersUpdateReqVO reqVO = randomPojo(PartnersUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> partnersService.updatePartners(reqVO), PARTNERS_NOT_EXISTS);
    }

    @Test
    public void testDeletePartners_success() {
        // mock 数据
        PartnersDO dbPartners = randomPojo(PartnersDO.class);
        partnersMapper.insert(dbPartners);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbPartners.getId();

        // 调用
        partnersService.deletePartners(id);
       // 校验数据不存在了
       assertNull(partnersMapper.selectById(id));
    }

    @Test
    public void testDeletePartners_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> partnersService.deletePartners(id), PARTNERS_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetPartnersPage() {
       // mock 数据
       PartnersDO dbPartners = randomPojo(PartnersDO.class, o -> { // 等会查询到
           o.setPicUrl(null);
           o.setName(null);
           o.setIntroduce(null);
           o.setCreateTime(null);
       });
       partnersMapper.insert(dbPartners);
       // 测试 picUrl 不匹配
       partnersMapper.insert(cloneIgnoreId(dbPartners, o -> o.setPicUrl(null)));
       // 测试 name 不匹配
       partnersMapper.insert(cloneIgnoreId(dbPartners, o -> o.setName(null)));
       // 测试 introduce 不匹配
       partnersMapper.insert(cloneIgnoreId(dbPartners, o -> o.setIntroduce(null)));
       // 测试 createTime 不匹配
       partnersMapper.insert(cloneIgnoreId(dbPartners, o -> o.setCreateTime(null)));
       // 准备参数
       PartnersPageReqVO reqVO = new PartnersPageReqVO();
       reqVO.setPicUrl(null);
       reqVO.setName(null);
       reqVO.setIntroduce(null);
       reqVO.setCreateTime((new Date[]{}));

       // 调用
       PageResult<PartnersDO> pageResult = partnersService.getPartnersPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbPartners, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetPartnersList() {
       // mock 数据
       PartnersDO dbPartners = randomPojo(PartnersDO.class, o -> { // 等会查询到
           o.setPicUrl(null);
           o.setName(null);
           o.setIntroduce(null);
           o.setCreateTime(null);
       });
       partnersMapper.insert(dbPartners);
       // 测试 picUrl 不匹配
       partnersMapper.insert(cloneIgnoreId(dbPartners, o -> o.setPicUrl(null)));
       // 测试 name 不匹配
       partnersMapper.insert(cloneIgnoreId(dbPartners, o -> o.setName(null)));
       // 测试 introduce 不匹配
       partnersMapper.insert(cloneIgnoreId(dbPartners, o -> o.setIntroduce(null)));
       // 测试 createTime 不匹配
       partnersMapper.insert(cloneIgnoreId(dbPartners, o -> o.setCreateTime(null)));
       // 准备参数
       PartnersExportReqVO reqVO = new PartnersExportReqVO();
       reqVO.setPicUrl(null);
       reqVO.setName(null);
       reqVO.setIntroduce(null);
       reqVO.setCreateTime((new Date[]{}));

       // 调用
       List<PartnersDO> list = partnersService.getPartnersList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbPartners, list.get(0));
    }

}
