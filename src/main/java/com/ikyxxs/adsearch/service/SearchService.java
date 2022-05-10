package com.ikyxxs.adsearch.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ikyxxs.adsearch.enums.PlatformTypeEnum;
import com.ikyxxs.adsearch.filter.AdvertFilter;
import com.ikyxxs.adsearch.model.SearchParam;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.search.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@Service
public class SearchService {

    private static final Integer NUMBER_PER_PAGE = 1000;

    private final List<AdvertFilter> advertFilters;

    @Autowired
    public SearchService(List<AdvertFilter> advertFilters) {
        this.advertFilters = advertFilters;
        this.advertFilters.sort(Comparator.comparingInt(AdvertFilter::getOrder));
    }

    /**
     * 检索广告
     */
    public JSONArray search(SearchParam param) throws Exception {
        // 参数处理
        preSearch(param);

        // 3. 查询器
        BooleanQuery.Builder builder = new BooleanQuery.Builder();
        buildQuery(builder, param);

        // 4. 搜索
        IndexReader reader = DirectoryReader.open(IndexService.index);
        IndexSearcher searcher = new IndexSearcher(reader);
        ScoreDoc[] hits = searcher.search(builder.build(), NUMBER_PER_PAGE, new Sort(SortField.FIELD_SCORE,new SortField("weight", SortField.Type.LONG,true))).scoreDocs;

        // 5. 构造查询结果
        JSONArray result = buildResult(searcher, hits);

        // 6. 关闭查询
        reader.close();
        return result;
    }

    /**
     * 构造查询
     */
    private void buildQuery(BooleanQuery.Builder builder, SearchParam param) {
        advertFilters.stream().filter(advertFilter -> advertFilter.validate(param))
                .forEach(advertFilter -> advertFilter.filter(builder, param));
    }

    /**
     * 构造查询结果
     */
    private JSONArray buildResult(IndexSearcher searcher, ScoreDoc[] hits) throws IOException {
        JSONArray arr = new JSONArray();
        for (ScoreDoc scoreDoc : hits) {
            Document d = searcher.doc(scoreDoc.doc);

            List<IndexableField> fields = d.getFields();

            JSONObject jo = new JSONObject();
            jo.put("score", scoreDoc.score);
            for (IndexableField f : fields) {
                jo.put(f.name(), d.get(f.name()));
            }
            arr.add(jo);
        }
        return arr;
    }

    /**
     * 预处理
     */
    private void preSearch(SearchParam param) {
        param.setAppFlowType(param.getAppFlowType() + "." + PlatformTypeEnum.getByDesc(param.getOs()).getCode());
    }
}
