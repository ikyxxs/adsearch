package com.ikyxxs.adsearch.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 广告状态枚举枚举
 */
@Getter
@AllArgsConstructor
public enum StatusEnum {

    INVALID(0, "无效"),
    VALID(1, "有效");

    private final Integer status;
    private final String desc;
}
