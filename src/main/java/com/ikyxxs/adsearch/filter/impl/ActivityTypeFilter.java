package com.ikyxxs.adsearch.filter.impl;

import com.ikyxxs.adsearch.enums.FilterType;
import com.ikyxxs.adsearch.filter.AdvertFilter;
import com.ikyxxs.adsearch.model.SearchParam;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.springframework.stereotype.Component;

@Component
public class ActivityTypeFilter implements AdvertFilter {

    @Override
    public boolean validate(SearchParam param) {
        return null != param.getActivityType();
    }

    @Override
    public void filter(BooleanQuery.Builder builder, SearchParam param) {
        Query query = new TermQuery(new Term("activityType", String.valueOf(param.getActivityType())));
        builder.add(query, BooleanClause.Occur.FILTER);
    }

    @Override
    public FilterType getType() {
        return FilterType.ACTIVITY_TYPE;
    }

    @Override
    public int getOrder() {
        return getType().getOrder();
    }
}
