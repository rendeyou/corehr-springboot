package com.bjsxt.corehr.utils;

import lombok.Data;
import org.apache.commons.lang3.ArrayUtils;

import java.io.Serializable;
import java.util.Objects;

/**
 * @className: ElasticSearchQueryParams
 * @description: ES动态传参工具类
 * @author: RenDeYou
 * @date: 2021/4/19 14:48
 */
@Data
public class ElasticSearchQueryParams<T> implements Serializable {
    private static final long serialVersionUID = -8527712150465776770L;
    /**
     * indexNames ES索引名称
     * typeNames ES类型名称
     * clazz ES返回值类型
     * includeFields ES返回字段
     * excludeFields 不返回字段
     */
    private String[] indexNames;
    private String[] typeNames;
    private Class<T> clazz;
    private String[] includeFields;
    private String[] excludeFields;

    //属性获取方法
    public String[] getIndexNames() {
        Objects.requireNonNull(indexNames);
        return indexNames;
    }

    public String[] getTypeNames() {
        Objects.requireNonNull(typeNames);
        return typeNames;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public String[] getIncludeFields() {
        return includeFields == null ? ArrayUtils.EMPTY_STRING_ARRAY : includeFields;
    }

    public String[] getExcludeFields() {
        return excludeFields == null ? ArrayUtils.EMPTY_STRING_ARRAY : excludeFields;
    }

    //私有构造方法，公有获取方法
    private ElasticSearchQueryParams() {
    }

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    /**
     * 静态内部类
     */
    public static class Builder<T> {
        private ElasticSearchQueryParams<T> params;

        //私有构造方法，公有获取方法
        private Builder() {
            params = new ElasticSearchQueryParams<>();
        }

        public ElasticSearchQueryParams<T> build() {
            return params;
        }

        //属性赋值方法
        public Builder<T> indexNames(String... indexNames) {
            params.indexNames = indexNames;
            return this;
        }

        public Builder<T> typeNames(String... typeNames) {
            params.typeNames = typeNames;
            return this;
        }

        public Builder<T> clazz(Class<T> clazz) {
            params.clazz = clazz;
            return this;
        }

        public Builder<T> includeFields(String... includeFields) {
            params.includeFields = includeFields;
            return this;
        }

        public Builder<T> excludeFields(String... excludeFields) {
            params.excludeFields = excludeFields;
            return this;
        }
    }
}
