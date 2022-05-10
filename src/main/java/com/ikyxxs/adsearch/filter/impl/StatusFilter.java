package com.ikyxxs.adsearch.filter.impl;

import com.ikyxxs.adsearch.enums.FilterType;
import com.ikyxxs.adsearch.filter.AdvertFilter;
import com.ikyxxs.adsearch.model.SearchParam;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.lucene.document.IntPoint;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.springframework.stereotype.Component;

import static com.ikyxxs.adsearch.enums.StatusEnum.VALID;

@Component
public class StatusFilter implements AdvertFilter {

    @Override
    public boolean validate(SearchParam param) {
        return CollectionUtils.isNotEmpty(param.getReceiveIds());
    }

    @Override
    public void filter(BooleanQuery.Builder builder, SearchParam param) {
        Query query = IntPoint.newSetQuery("status", VALID.getStatus());
        builder.add(query, BooleanClause.Occur.FILTER);
    }

    @Override
    public FilterType getType() {
        return FilterType.STATUS;
    }
}
