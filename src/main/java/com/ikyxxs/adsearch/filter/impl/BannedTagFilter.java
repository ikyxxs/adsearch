package com.ikyxxs.adsearch.filter.impl;

import com.ikyxxs.adsearch.enums.FilterType;
import com.ikyxxs.adsearch.filter.AdvertFilter;
import com.ikyxxs.adsearch.model.SearchParam;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.springframework.stereotype.Component;

@Component
public class BannedTagFilter implements AdvertFilter {

    @Override
    public boolean validate(SearchParam param) {
        return CollectionUtils.isNotEmpty(param.getBannedTags());
    }

    @Override
    public void filter(BooleanQuery.Builder builder, SearchParam param) {
        param.getBannedTags().forEach(tag -> {
            Query query = new TermQuery(new Term("bannedTags", String.valueOf(tag)));
            builder.add(query, BooleanClause.Occur.MUST_NOT);
        });
    }

    @Override
    public FilterType getType() {
        return FilterType.BANNED_TAG;
    }
}
