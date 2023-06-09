package com.zkzl.module.product.controller.admin.spu.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import com.zkzl.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static com.zkzl.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 商品spu Excel 导出 Request VO", description = "参数和 SpuPageReqVO 是一致的")
@Data
public class SpuExportReqVO {

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "卖点")
    private String sellPoint;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "分类id")
    private Long categoryId;

    @ApiModelProperty(value = "商品主图地址,* 数组，以逗号分隔,最多上传15张")
    private List<String> picUrls;

    @ApiModelProperty(value = "排序字段")
    private Integer sort;

    @ApiModelProperty(value = "点赞初始人数")
    private Integer likeCount;

    @ApiModelProperty(value = "价格 单位使用：分")
    private Integer price;

    @ApiModelProperty(value = "库存数量")
    private Integer quantity;

    @ApiModelProperty(value = "上下架状态： 0 上架（开启） 1 下架（禁用）")
    private Boolean status;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "创建时间")
    private Date[] createTime;

}
