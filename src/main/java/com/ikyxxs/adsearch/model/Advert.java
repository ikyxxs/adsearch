package com.ikyxxs.adsearch.model;

import com.ikyxxs.adsearch.annotation.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Advert {

    /**
     * 广告id
     */
    @LongField
    private Long advertId;

    /**
     * 广告名称
     */
    @StringField
    private String advertName;

    /**
     * 配置Id
     */
    @StringField
    private Long orientId;

    /**
     * 投放的操作系统(ios, android, other)
     */
    @TextField
    private List<String> os;

    /**
     * 投放的活动类型
     */
    @TextField
    private List<Integer> activityType;

    /**
     * 屏蔽的流量类型
     * e.g. 1.1:微信-android,1.2:微信-ios;2.1:QQ-android,2.2:QQ-ios;3.1:支付宝-android,3.2:支付宝-ios
     */
    @TextField
    private List<String> bannedAppFlowType;

    /**
     * 屏蔽的标签(a.b.c.d)
     */
    @TextField
    private List<String> bannedTags;

    /**
     * 落地页链接
     */
    @StringField
    private String promoteUrl;

    /**
     * 地域
     */
    @TextField
    private List<String> regionIds;

    /**
     * 投放起始时间
     */
    @LongField
    private Long beginTime;

    /**
     * 投放结束时间
     */
    @LongField
    private Long endTime;

    /**
     * 权重(用于排序)
     */
    @NumericDocValuesField
    private Long weight;

    /**
     * 广告状态
     * @see com.ikyxxs.adsearch.enums.StatusEnum
     */
    @IntField
    private Integer status;
}
