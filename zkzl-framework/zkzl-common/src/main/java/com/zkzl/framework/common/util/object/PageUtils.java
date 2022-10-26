package com.zkzl.framework.common.util.object;

import com.zkzl.framework.common.pojo.PageParam;

/**
 * {@link com.zkzl.framework.common.pojo.PageParam} 工具类
 *
 * @author 芋道源码
 */
public class PageUtils {

    public static int getStart(PageParam pageParam) {
        return (pageParam.getPageNo() - 1) * pageParam.getPageSize();
    }

}
