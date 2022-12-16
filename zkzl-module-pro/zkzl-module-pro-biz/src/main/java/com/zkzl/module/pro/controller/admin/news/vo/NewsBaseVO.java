package com.zkzl.module.pro.controller.admin.news.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 新闻 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class NewsBaseVO {

    @ApiModelProperty(value = "图片")
    private String picUrl;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "概要")
    private String summary;

    @ApiModelProperty(value = "1公司新闻2行业新闻3通知通告", required = true)
    @NotNull(message = "1公司新闻2行业新闻3通知通告不能为空")
    private Integer newsType;

    @ApiModelProperty(value = "是否上架 1上架0下架")
    private String shelves;

}
