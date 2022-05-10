package com.ikyxxs.adsearch;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class AdsearchTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void test() throws Exception {
        // 1. 准备中文分词器
        IKAnalyzer analyzer = new IKAnalyzer();

        // 2. 索引
        List<Advert> ads = new ArrayList<>();
        ads.add(new Advert(1L, 1, "android"));
        ads.add(new Advert(2L, 1, "ios"));
        ads.add(new Advert(3L, 1, "other"));
        ads.add(new Advert(4L, 2, "android"));
        ads.add(new Advert(5L, 2, "ios"));
        ads.add(new Advert(6L, 2, "other"));
        ads.add(new Advert(7L, 3, "android"));
        ads.add(new Advert(8L, 3, "ios"));
        ads.add(new Advert(9L, 3, "other"));

        Directory index = createIndex(analyzer, ads);

        // 3. 查询器
        Query typeQuery = new TermQuery(new Term("type", "3"));
        Query osQuery = new TermQuery(new Term("os", "ios"));
        Query os2Query = new TermQuery(new Term("os", "android"));

        BooleanQuery.Builder builder = new BooleanQuery.Builder();
        builder.add(typeQuery, BooleanClause.Occur.MUST);
        builder.add(osQuery, BooleanClause.Occur.SHOULD);
        builder.add(os2Query, BooleanClause.Occur.SHOULD);

        // 4. 搜索
        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        int numberPerPage = 1000;
        System.out.printf("当前一共有%d条数据%n", ads.size());
        System.out.printf("查询关键字是：\"%s\"%n", "type:3,os:ios");

        ScoreDoc[] hits = searcher.search(builder.build(), numberPerPage).scoreDocs;

        // 5. 显示查询结果
        showSearchResults(searcher, hits, builder.build(), analyzer);

        // 6. 关闭查询
        reader.close();
    }

    private static void showSearchResults(IndexSearcher searcher, ScoreDoc[] hits, Query query, IKAnalyzer analyzer) throws Exception {

        System.out.println("找到 " + hits.length + " 个命中.");
        System.out.println("序号\t匹配度得分\t结果");

        for (int i = 0; i < hits.length; ++i) {
            ScoreDoc scoreDoc= hits[i];
            int docId = scoreDoc.doc;
            Document d = searcher.doc(docId);

            List<IndexableField> fields = d.getFields();

            System.out.print((i + 1));

            System.out.print("\t" + scoreDoc.score);

            for (IndexableField f : fields) {
                System.out.print("\t" + d.get(f.name()));
            }

            System.out.println();
        }
    }

    private static Directory createIndex(IKAnalyzer analyzer, List<Advert> ads) throws IOException {

        Directory index = new RAMDirectory();

        IndexWriterConfig config = new IndexWriterConfig(analyzer);

        IndexWriter writer = new IndexWriter(index, config);

        for (Advert ad : ads) {
            addDoc(writer, ad);
        }

        writer.close();
        return index;
    }

    private static void addDoc(IndexWriter w, Advert ad) throws IOException {
        Document doc = new Document();
        doc.add(new StringField("id", ad.id + "", Field.Store.YES));
        doc.add(new StringField("type", ad.type + "", Field.Store.YES));
        doc.add(new StringField("os", ad.os, Field.Store.YES));
        w.addDocument(doc);
    }

    static class Advert {
        Long id;
        Integer type;
        String os;

        Advert() {}

        Advert(Long id, Integer type, String os) {
            this.id = id;
            this.type = type;
            this.os = os;
        }
    }
}
