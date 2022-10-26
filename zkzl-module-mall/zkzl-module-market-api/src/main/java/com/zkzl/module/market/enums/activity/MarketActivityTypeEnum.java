package com.zkzl.module.market.enums.activity;

import com.zkzl.framework.common.core.IntArrayValuable;

import java.util.Arrays;

/**
 * 促销活动类型枚举
 */
public enum MarketActivityTypeEnum implements IntArrayValuable {

    TIME_LIMITED_DISCOUNT(1, "限时折扣"),
    FULL_PRIVILEGE(2, "满减送"),
    ;

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(MarketActivityTypeEnum::getValue).toArray();

    /**
     * 类型值
     */
    private final Integer value;
    /**
     * 类型名
     */
    private final String name;

    MarketActivityTypeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    @Override
    public int[] array() {
        return ARRAYS;
    }
}
