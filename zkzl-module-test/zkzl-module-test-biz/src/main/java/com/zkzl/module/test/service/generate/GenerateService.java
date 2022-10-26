package com.zkzl.module.test.service.generate;

import java.util.*;
import javax.validation.*;
import com.zkzl.module.test.controller.admin.generate.vo.*;
import com.zkzl.module.test.dal.dataobject.generate.GenerateDO;
import com.zkzl.framework.common.pojo.PageResult;

/**
 * 测试自动代码生成 Service 接口
 *
 * @author 芋道源码
 */
public interface GenerateService {

    /**
     * 创建测试自动代码生成
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createGenerate(@Valid GenerateCreateReqVO createReqVO);

    /**
     * 更新测试自动代码生成
     *
     * @param updateReqVO 更新信息
     */
    void updateGenerate(@Valid GenerateUpdateReqVO updateReqVO);

    /**
     * 删除测试自动代码生成
     *
     * @param id 编号
     */
    void deleteGenerate(Integer id);

    /**
     * 获得测试自动代码生成
     *
     * @param id 编号
     * @return 测试自动代码生成
     */
    GenerateDO getGenerate(Integer id);

    /**
     * 获得测试自动代码生成列表
     *
     * @param ids 编号
     * @return 测试自动代码生成列表
     */
    List<GenerateDO> getGenerateList(Collection<Integer> ids);

    /**
     * 获得测试自动代码生成分页
     *
     * @param pageReqVO 分页查询
     * @return 测试自动代码生成分页
     */
    PageResult<GenerateDO> getGeneratePage(GeneratePageReqVO pageReqVO);

    /**
     * 获得测试自动代码生成列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 测试自动代码生成列表
     */
    List<GenerateDO> getGenerateList(GenerateExportReqVO exportReqVO);

}
