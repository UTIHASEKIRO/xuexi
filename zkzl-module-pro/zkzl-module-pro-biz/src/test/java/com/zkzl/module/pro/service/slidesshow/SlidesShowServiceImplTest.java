package com.zkzl.module.pro.service.slidesshow;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.zkzl.framework.test.core.ut.BaseDbUnitTest;

import com.zkzl.module.pro.controller.admin.slidesshow.vo.*;
import com.zkzl.module.pro.dal.dataobject.slidesshow.SlidesShowDO;
import com.zkzl.module.pro.dal.mysql.slidesshow.SlidesShowMapper;
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
* {@link SlidesShowServiceImpl} 的单元测试类
*
* @author 芋道源码
*/
@Import(SlidesShowServiceImpl.class)
public class SlidesShowServiceImplTest extends BaseDbUnitTest {

    @Resource
    private SlidesShowServiceImpl slidesShowService;

    @Resource
    private SlidesShowMapper slidesShowMapper;

    @Test
    public void testCreateSlidesShow_success() {
        // 准备参数
        SlidesShowCreateReqVO reqVO = randomPojo(SlidesShowCreateReqVO.class);

        // 调用
        Long slidesShowId = slidesShowService.createSlidesShow(reqVO);
        // 断言
        assertNotNull(slidesShowId);
        // 校验记录的属性是否正确
        SlidesShowDO slidesShow = slidesShowMapper.selectById(slidesShowId);
        assertPojoEquals(reqVO, slidesShow);
    }

    @Test
    public void testUpdateSlidesShow_success() {
        // mock 数据
        SlidesShowDO dbSlidesShow = randomPojo(SlidesShowDO.class);
        slidesShowMapper.insert(dbSlidesShow);// @Sql: 先插入出一条存在的数据
        // 准备参数
        SlidesShowUpdateReqVO reqVO = randomPojo(SlidesShowUpdateReqVO.class, o -> {
            o.setId(dbSlidesShow.getId()); // 设置更新的 ID
        });

        // 调用
        slidesShowService.updateSlidesShow(reqVO);
        // 校验是否更新正确
        SlidesShowDO slidesShow = slidesShowMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, slidesShow);
    }

    @Test
    public void testUpdateSlidesShow_notExists() {
        // 准备参数
        SlidesShowUpdateReqVO reqVO = randomPojo(SlidesShowUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> slidesShowService.updateSlidesShow(reqVO), SLIDES_SHOW_NOT_EXISTS);
    }

    @Test
    public void testDeleteSlidesShow_success() {
        // mock 数据
        SlidesShowDO dbSlidesShow = randomPojo(SlidesShowDO.class);
        slidesShowMapper.insert(dbSlidesShow);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbSlidesShow.getId();

        // 调用
        slidesShowService.deleteSlidesShow(id);
       // 校验数据不存在了
       assertNull(slidesShowMapper.selectById(id));
    }

    @Test
    public void testDeleteSlidesShow_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> slidesShowService.deleteSlidesShow(id), SLIDES_SHOW_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetSlidesShowPage() {
       // mock 数据
       SlidesShowDO dbSlidesShow = randomPojo(SlidesShowDO.class, o -> { // 等会查询到
           o.setPicUrl(null);
           o.setCreateTime(null);
       });
       slidesShowMapper.insert(dbSlidesShow);
       // 测试 picUrl 不匹配
       slidesShowMapper.insert(cloneIgnoreId(dbSlidesShow, o -> o.setPicUrl(null)));
       // 测试 createTime 不匹配
       slidesShowMapper.insert(cloneIgnoreId(dbSlidesShow, o -> o.setCreateTime(null)));
       // 准备参数
       SlidesShowPageReqVO reqVO = new SlidesShowPageReqVO();
       reqVO.setPicUrl(null);
       reqVO.setCreateTime((new Date[]{}));

       // 调用
       PageResult<SlidesShowDO> pageResult = slidesShowService.getSlidesShowPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbSlidesShow, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetSlidesShowList() {
       // mock 数据
       SlidesShowDO dbSlidesShow = randomPojo(SlidesShowDO.class, o -> { // 等会查询到
           o.setPicUrl(null);
           o.setCreateTime(null);
       });
       slidesShowMapper.insert(dbSlidesShow);
       // 测试 picUrl 不匹配
       slidesShowMapper.insert(cloneIgnoreId(dbSlidesShow, o -> o.setPicUrl(null)));
       // 测试 createTime 不匹配
       slidesShowMapper.insert(cloneIgnoreId(dbSlidesShow, o -> o.setCreateTime(null)));
       // 准备参数
       SlidesShowExportReqVO reqVO = new SlidesShowExportReqVO();
       reqVO.setPicUrl(null);
       reqVO.setCreateTime((new Date[]{}));

       // 调用
       List<SlidesShowDO> list = slidesShowService.getSlidesShowList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbSlidesShow, list.get(0));
    }

}
