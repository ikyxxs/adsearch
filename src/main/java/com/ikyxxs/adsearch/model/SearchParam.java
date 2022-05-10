package com.ikyxxs.adsearch.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class SearchParam implements Serializable {
    private static final long serialVersionUID = 8934833028547016257L;

    /**
     * 已领取广告
     */
    private List<Long> receiveIds;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 活动类型
     */
    private Integer activityType;

    /**
     * User-Agent
     */
    private String userAgent;

    /**
     * 流量类型
     */
    private String appFlowType;

    /**
     * 屏蔽标签
     */
    private List<String> bannedTags;

    /**
     * 屏蔽落地页
     */
    private List<String> bannedUrls;

    /**
     * 地域
     */
    private String regionId;
}
