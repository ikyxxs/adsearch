package com.ikyxxs.adsearch.factory;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexableField;

public class IndexableFieldFactory {

    /**
     * 构建 Lucene 的索引字段
     */
    public static IndexableField buildIndexableField(java.lang.reflect.Field field, String value) {
        if (null != field.getAnnotation(com.ikyxxs.adsearch.annotation.TextField.class)) {
            return new TextField(field.getName(), value, Field.Store.YES);
        } else if (null != field.getAnnotation(com.ikyxxs.adsearch.annotation.IntField.class)) {
            if (StringUtils.isNumeric(value)) {
                return new IntPoint(field.getName(), Integer.parseInt(value));
            }
        } else if (null != field.getAnnotation(com.ikyxxs.adsearch.annotation.LongField.class)) {
            if (StringUtils.isNumeric(value)) {
                return new LongPoint(field.getName(), Long.parseLong(value));
            }
        } else if (null != field.getAnnotation(com.ikyxxs.adsearch.annotation.NumericDocValuesField.class)) {
            if (StringUtils.isNumeric(value)) {
                return new NumericDocValuesField(field.getName(), Long.valueOf(value));
            }
        } else {
            return new StringField(field.getName(), value, Field.Store.YES);
        }
        return null;
    }
}
