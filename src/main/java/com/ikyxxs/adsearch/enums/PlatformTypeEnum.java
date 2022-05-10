package com.ikyxxs.adsearch.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 操作系统枚举
 */
@Getter
@AllArgsConstructor
public enum PlatformTypeEnum {

    ANDROID(1, "android", "安卓"),
    IOS(2, "ios", "苹果"),
    UNKNOW(3, "unknow", "其它");

    private final Integer code;
    private final String desc;
    private final String cName;

    public static PlatformTypeEnum getByDesc(String desc) {
        for (PlatformTypeEnum e : PlatformTypeEnum.values()) {
            if (Objects.equals(e.getDesc(), desc)) {
                return e;
            }
        }
        return UNKNOW;
    }
}
