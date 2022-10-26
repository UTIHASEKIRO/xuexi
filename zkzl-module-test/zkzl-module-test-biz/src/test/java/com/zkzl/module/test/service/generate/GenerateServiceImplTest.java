package com.zkzl.module.test.service.generate;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.zkzl.framework.test.core.ut.BaseDbUnitTest;

import com.zkzl.module.test.controller.admin.generate.vo.*;
import com.zkzl.module.test.dal.dataobject.generate.GenerateDO;
import com.zkzl.module.test.dal.mysql.generate.GenerateMapper;
import com.zkzl.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;

import static cn.hutool.core.util.RandomUtil.*;
import static com.zkzl.module.test.enums.ErrorCodeConstants.*;
import static com.zkzl.framework.test.core.util.AssertUtils.*;
import static com.zkzl.framework.test.core.util.RandomUtils.*;
import static com.zkzl.framework.common.util.object.ObjectUtils.*;
import static com.zkzl.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
* {@link GenerateServiceImpl} 的单元测试类
*
* @author 芋道源码
*/
@Import(GenerateServiceImpl.class)
public class GenerateServiceImplTest extends BaseDbUnitTest {

    @Resource
    private GenerateServiceImpl generateService;

    @Resource
    private GenerateMapper generateMapper;

    @Test
    public void testCreateGenerate_success() {
        // 准备参数
        GenerateCreateReqVO reqVO = randomPojo(GenerateCreateReqVO.class);

        // 调用
        Integer generateId = generateService.createGenerate(reqVO);
        // 断言
        assertNotNull(generateId);
        // 校验记录的属性是否正确
        GenerateDO generate = generateMapper.selectById(generateId);
        assertPojoEquals(reqVO, generate);
    }

    @Test
    public void testUpdateGenerate_success() {
        // mock 数据
        GenerateDO dbGenerate = randomPojo(GenerateDO.class);
        generateMapper.insert(dbGenerate);// @Sql: 先插入出一条存在的数据
        // 准备参数
        GenerateUpdateReqVO reqVO = randomPojo(GenerateUpdateReqVO.class, o -> {
            o.setId(dbGenerate.getId()); // 设置更新的 ID
        });

        // 调用
        generateService.updateGenerate(reqVO);
        // 校验是否更新正确
        GenerateDO generate = generateMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, generate);
    }

    @Test
    public void testUpdateGenerate_notExists() {
        // 准备参数
        GenerateUpdateReqVO reqVO = randomPojo(GenerateUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> generateService.updateGenerate(reqVO), GENERATE_NOT_EXISTS);
    }

    @Test
    public void testDeleteGenerate_success() {
        // mock 数据
        GenerateDO dbGenerate = randomPojo(GenerateDO.class);
        generateMapper.insert(dbGenerate);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Integer id = dbGenerate.getId();

        // 调用
        generateService.deleteGenerate(id);
       // 校验数据不存在了
       assertNull(generateMapper.selectById(id));
    }

//    @Test
//    public void testDeleteGenerate_notExists() {
//        // 准备参数
//        Integer id = randomIntegerId();
//
//        // 调用, 并断言异常
//        assertServiceException(() -> generateService.deleteGenerate(id), GENERATE_NOT_EXISTS);
//    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetGeneratePage() {
       // mock 数据
       GenerateDO dbGenerate = randomPojo(GenerateDO.class, o -> { // 等会查询到
           o.setCreateTime(null);
       });
       generateMapper.insert(dbGenerate);
       // 测试 createTime 不匹配
       generateMapper.insert(cloneIgnoreId(dbGenerate, o -> o.setCreateTime(null)));
       // 准备参数
       GeneratePageReqVO reqVO = new GeneratePageReqVO();
       reqVO.setCreateTime((new Date[]{}));

       // 调用
       PageResult<GenerateDO> pageResult = generateService.getGeneratePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbGenerate, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetGenerateList() {
       // mock 数据
       GenerateDO dbGenerate = randomPojo(GenerateDO.class, o -> { // 等会查询到
           o.setCreateTime(null);
       });
       generateMapper.insert(dbGenerate);
       // 测试 createTime 不匹配
       generateMapper.insert(cloneIgnoreId(dbGenerate, o -> o.setCreateTime(null)));
       // 准备参数
       GenerateExportReqVO reqVO = new GenerateExportReqVO();
       reqVO.setCreateTime((new Date[]{}));

       // 调用
       List<GenerateDO> list = generateService.getGenerateList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbGenerate, list.get(0));
    }

}
