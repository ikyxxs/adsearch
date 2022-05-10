package com.ikyxxs.adsearch.filter.impl;

import cn.hutool.core.date.DateUtil;
import com.ikyxxs.adsearch.enums.FilterType;
import com.ikyxxs.adsearch.filter.AdvertFilter;
import com.ikyxxs.adsearch.model.SearchParam;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.springframework.stereotype.Component;

@Component
public class ValidPeriodFilter implements AdvertFilter {

    @Override
    public boolean validate(SearchParam param) {
        return true;
    }

    @Override
    public void filter(BooleanQuery.Builder builder, SearchParam param) {
        long now = DateUtil.current();
        builder.add(LongPoint.newRangeQuery("beginTime", 0, now), BooleanClause.Occur.FILTER);
        builder.add(LongPoint.newRangeQuery("endTime", now, Long.MAX_VALUE), BooleanClause.Occur.FILTER);
    }

    @Override
    public FilterType getType() {
        return FilterType.VALID_PERIOD;
    }

    @Override
    public int getOrder() {
        return getType().getOrder();
    }
}
