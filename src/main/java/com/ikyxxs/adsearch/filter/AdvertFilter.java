package com.ikyxxs.adsearch.filter;

import com.ikyxxs.adsearch.enums.FilterType;
import com.ikyxxs.adsearch.model.SearchParam;
import org.apache.lucene.search.BooleanQuery;

public interface AdvertFilter {

    /**
     * 参数校验
     */
    boolean validate(SearchParam param);

    /**
     * 构建过滤条件
     */
    void filter(BooleanQuery.Builder builder, SearchParam param);

    /**
     * 过滤类型
     */
    FilterType getType();

    /**
     * 过滤顺序
     */
    default int getOrder() {
        return getType().getOrder();
    }
}
