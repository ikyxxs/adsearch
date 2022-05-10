package com.ikyxxs.adsearch;

import cn.hutool.core.date.DateUtil;
import com.ikyxxs.adsearch.controller.TestController;
import com.ikyxxs.adsearch.model.Advert;
import com.ikyxxs.adsearch.model.SearchParam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class AdsearchApplicationTests {

    @Autowired
    private TestController testController;

    @Test
    void contextLoads() {
    }

    @Test
    public void test() throws Exception {
        System.out.println("-------------添加广告-------------");

        Long yesterday = DateUtil.yesterday().getTime();
        Long tomorrow = DateUtil.tomorrow().getTime();

        List<Advert> ads = new ArrayList<>();
        ads.add(Advert.builder().advertId(1L).advertName("广告1").os(Arrays.asList("ios", "other")).activityType(Arrays.asList(1, 2, 3)).bannedAppFlowType(Arrays.asList("1.1", "2.1", "2.2")).bannedTags(Arrays.asList("3.3.3.3", "4.4.4.4")).promoteUrl("https://ikyxxs.com/2").regionIds(Arrays.asList("1301", "1302", "1303")).beginTime(yesterday).endTime(tomorrow).weight(1L).status(1).build());
        ads.add(Advert.builder().advertId(2L).advertName("广告2").os(Arrays.asList("ios", "other")).activityType(Arrays.asList(1, 2, 3)).bannedAppFlowType(Arrays.asList("1.1", "1.2", "2.1", "2.2")).bannedTags(Arrays.asList("2.2.2.2")).promoteUrl("https://ikyxxs.com/1").regionIds(Arrays.asList("1301", "1302")).beginTime(yesterday).endTime(tomorrow).weight(1L).status(1).build());
        ads.add(Advert.builder().advertId(3L).advertName("广告3").os(Arrays.asList("android", "other")).activityType(Arrays.asList(1, 2)).bannedAppFlowType(Arrays.asList("1.1", "1.2", "2.1", "2.2")).bannedTags(Arrays.asList("3.3.3.3")).promoteUrl("https://ikyxxs.com/1").regionIds(Arrays.asList("1301", "1302")).beginTime(yesterday).endTime(tomorrow).weight(1L).status(1).build());
        ads.add(Advert.builder().advertId(4L).advertName("广告4").os(Arrays.asList("ios", "other")).activityType(Arrays.asList(1, 3)).bannedAppFlowType(Arrays.asList("1.1", "1.2", "2.1", "2.2")).bannedTags(Arrays.asList("1.1.1.1")).promoteUrl("https://ikyxxs.com/1").regionIds(Arrays.asList("1301", "1302")).beginTime(yesterday).endTime(tomorrow).weight(1L).status(1).build());
        ads.add(Advert.builder().advertId(5L).advertName("广告5").os(Arrays.asList("ios", "other")).activityType(Arrays.asList(1, 2, 3)).bannedAppFlowType(Arrays.asList("1.1", "1.2", "2.1", "2.2")).bannedTags(Arrays.asList("1.1.1.1")).promoteUrl("https://ikyxxs.com/1").regionIds(Arrays.asList("1301", "1302")).beginTime(yesterday).endTime(tomorrow).weight(1L).status(1).build());
        ads.add(Advert.builder().advertId(6L).advertName("广告6").os(Arrays.asList("ios", "other")).activityType(Arrays.asList(1, 2, 3)).bannedAppFlowType(Arrays.asList("1.1", "2.1", "2.2")).bannedTags(Arrays.asList("1.1.1.1", "3.3.3.3")).promoteUrl("https://ikyxxs.com/1").regionIds(Arrays.asList("1301", "1302")).beginTime(yesterday).endTime(tomorrow).weight(1L).status(1).build());
        ads.add(Advert.builder().advertId(7L).advertName("广告7").os(Arrays.asList("ios", "other")).activityType(Arrays.asList(1, 2, 3)).bannedAppFlowType(Arrays.asList("1.1", "2.1", "2.2")).bannedTags(Arrays.asList("3.3.3.3", "4.4.4.4")).promoteUrl("https://ikyxxs.com/1").regionIds(Arrays.asList("1301", "1302")).beginTime(yesterday).endTime(tomorrow).weight(1L).status(1).build());
        ads.add(Advert.builder().advertId(8L).advertName("广告8").os(Arrays.asList("ios", "other")).activityType(Arrays.asList(1, 2, 3)).bannedAppFlowType(Arrays.asList("1.1", "2.1", "2.2")).bannedTags(Arrays.asList("3.3.3.3", "4.4.4.4")).promoteUrl("https://ikyxxs.com/2").regionIds(Arrays.asList("1301", "1302")).beginTime(yesterday).endTime(tomorrow).weight(1L).status(1).build());
        ads.add(Advert.builder().advertId(9L).advertName("广告9").os(Arrays.asList("ios", "other")).activityType(Arrays.asList(1, 2, 3)).bannedAppFlowType(Arrays.asList("1.1", "2.1", "2.2")).bannedTags(Arrays.asList("3.3.3.3", "4.4.4.4")).promoteUrl("https://ikyxxs.com/2").regionIds(Arrays.asList("1301", "1302", "1303")).beginTime(yesterday).endTime(tomorrow).weight(1L).status(1).build());
        ads.add(Advert.builder().advertId(10L).advertName("广告10").os(Arrays.asList("ios", "other")).activityType(Arrays.asList(1, 2, 3)).bannedAppFlowType(Arrays.asList("1.1", "2.1", "2.2")).bannedTags(Arrays.asList("3.3.3.3", "4.4.4.4")).promoteUrl("https://ikyxxs.com/2").regionIds(Arrays.asList("1301", "1302", "1303")).beginTime(yesterday).endTime(tomorrow).weight(2L).status(1).build());
        ads.add(Advert.builder().advertId(11L).advertName("广告11").os(Arrays.asList("ios", "other")).activityType(Arrays.asList(1, 2, 3)).bannedAppFlowType(Arrays.asList("1.1", "2.1", "2.2")).bannedTags(Arrays.asList("3.3.3.3", "4.4.4.4")).promoteUrl("https://ikyxxs.com/2").regionIds(Arrays.asList("1301", "1302", "1303")).beginTime(yesterday).endTime(tomorrow).weight(1L).status(0).build());

        ads.forEach(ad -> {
            try {
                System.out.println(testController.index(ad));
            } catch (IOException ignored) {
                System.out.println(ad.getAdvertName() + " index error");
            }
        });

        System.out.println("\n-------------检索广告-------------");
        SearchParam param = SearchParam.builder()
                .receiveIds(Arrays.asList(1L, 5L))
                .os("ios")
                .activityType(2)
                .appFlowType("1")
                .bannedTags(Arrays.asList("1.1.1.1", "2.2.2.2"))
                .bannedUrls(Collections.singletonList("https://ikyxxs.com/1"))
                .regionId("1303")
                .build();
        System.out.println(testController.search(param));
    }
}
