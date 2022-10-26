package com.zkzl.module.test.service.generate;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.zkzl.module.test.controller.admin.generate.vo.*;
import com.zkzl.module.test.dal.dataobject.generate.GenerateDO;
import com.zkzl.framework.common.pojo.PageResult;

import com.zkzl.module.test.convert.generate.GenerateConvert;
import com.zkzl.module.test.dal.mysql.generate.GenerateMapper;

import static com.zkzl.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.zkzl.module.test.enums.ErrorCodeConstants.*;

/**
 * 测试自动代码生成 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class GenerateServiceImpl implements GenerateService {

    @Resource
    private GenerateMapper generateMapper;

    @Override
    public Integer createGenerate(GenerateCreateReqVO createReqVO) {
        // 插入
        GenerateDO generate = GenerateConvert.INSTANCE.convert(createReqVO);
        generateMapper.insert(generate);
        // 返回
        return generate.getId();
    }

    @Override
    public void updateGenerate(GenerateUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateGenerateExists(updateReqVO.getId());
        // 更新
        GenerateDO updateObj = GenerateConvert.INSTANCE.convert(updateReqVO);
        generateMapper.updateById(updateObj);
    }

    @Override
    public void deleteGenerate(Integer id) {
        // 校验存在
        this.validateGenerateExists(id);
        // 删除
        generateMapper.deleteById(id);
    }

    private void validateGenerateExists(Integer id) {
        if (generateMapper.selectById(id) == null) {
            throw exception(GENERATE_NOT_EXISTS);
        }
    }

    @Override
    public GenerateDO getGenerate(Integer id) {
        return generateMapper.selectById(id);
    }

    @Override
    public List<GenerateDO> getGenerateList(Collection<Integer> ids) {
        return generateMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<GenerateDO> getGeneratePage(GeneratePageReqVO pageReqVO) {
        return generateMapper.selectPage(pageReqVO);
    }

    @Override
    public List<GenerateDO> getGenerateList(GenerateExportReqVO exportReqVO) {
        return generateMapper.selectList(exportReqVO);
    }

}
