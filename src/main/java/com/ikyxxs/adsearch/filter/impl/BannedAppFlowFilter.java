package com.ikyxxs.adsearch.filter.impl;

import com.ikyxxs.adsearch.enums.FilterType;
import com.ikyxxs.adsearch.filter.AdvertFilter;
import com.ikyxxs.adsearch.model.SearchParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.springframework.stereotype.Component;

@Component
public class BannedAppFlowFilter implements AdvertFilter {

    @Override
    public boolean validate(SearchParam param) {
        return StringUtils.isNotBlank(param.getAppFlowType());
    }

    @Override
    public void filter(BooleanQuery.Builder builder, SearchParam param) {
        Query query = new TermQuery(new Term("bannedAppFlowType", param.getAppFlowType()));
        builder.add(query, BooleanClause.Occur.MUST_NOT);
    }

    @Override
    public FilterType getType() {
        return FilterType.BANNED_APP_FLOW_TYPE;
    }
}
