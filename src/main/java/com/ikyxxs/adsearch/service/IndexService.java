package com.ikyxxs.adsearch.service;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.ikyxxs.adsearch.factory.IndexableFieldFactory;
import com.ikyxxs.adsearch.model.Advert;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class IndexService {

    private static final Logger logger = LoggerFactory.getLogger(IndexService.class);

    public static Directory index = new RAMDirectory();

    /**
     * 添加索引
     */
    public void index(Advert advert) throws IOException {
        IndexWriterConfig config = new IndexWriterConfig();
        IndexWriter writer = new IndexWriter(index, config);
        addDoc(writer, advert);
        writer.close();
    }

    /**
     * 添加文档
     */
    private void addDoc(IndexWriter w, Advert ad) throws IOException {
        Document doc = new Document();

        Class cls = ad.getClass();
        java.lang.reflect.Field[] fields = cls.getDeclaredFields();
        for (java.lang.reflect.Field field : fields) {
            try {
                field.setAccessible(true);

                String value;
                if (null == field.get(ad)) {
                    value = StringUtils.EMPTY;
                } else if (field.getType() == List.class) {
                    value = Joiner.on(" ").join((List) field.get(ad)).toLowerCase();
                } else {
                    value = String.valueOf(field.get(ad));
                }

                IndexableField indexableField = IndexableFieldFactory.buildIndexableField(field, value);
                Optional.ofNullable(indexableField).ifPresent(doc::add);
            } catch (IllegalAccessException e) {
                logger.error("addDoc error, advert={}", JSON.toJSONString(ad), e);
            }
        }

        w.addDocument(doc);
    }
}
