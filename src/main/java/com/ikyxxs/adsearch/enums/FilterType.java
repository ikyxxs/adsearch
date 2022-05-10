package com.ikyxxs.adsearch.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 过滤类型枚举
 */
@Getter
@AllArgsConstructor
public enum FilterType {

    RECEIVED("已领取广告", 1),
    OS_PLATFORM("操作系统", 2),
    ACTIVITY_TYPE("活动类型", 3),
    BANNED_APP_FLOW_TYPE("屏蔽流量", 4),
    BANNED_TAG("屏蔽标签", 5),
    BANNED_URL("屏蔽落地页", 6),
    REGION("地域", 7),
    VALID_PERIOD("投放时段", 8),
    STATUS("广告状态", 9),
    ;

    private final String desc;
    private final int order;
}
