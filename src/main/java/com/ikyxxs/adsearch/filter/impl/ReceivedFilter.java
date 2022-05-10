package com.ikyxxs.adsearch.filter.impl;

import com.ikyxxs.adsearch.enums.FilterType;
import com.ikyxxs.adsearch.filter.AdvertFilter;
import com.ikyxxs.adsearch.model.SearchParam;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.springframework.stereotype.Component;

@Component
public class ReceivedFilter implements AdvertFilter {

    @Override
    public boolean validate(SearchParam param) {
        return CollectionUtils.isNotEmpty(param.getReceiveIds());
    }

    @Override
    public void filter(BooleanQuery.Builder builder, SearchParam param) {
        Query query = LongPoint.newSetQuery("advertId", param.getReceiveIds());
        builder.add(query, BooleanClause.Occur.MUST_NOT);
    }

    @Override
    public FilterType getType() {
        return FilterType.RECEIVED;
    }
}
