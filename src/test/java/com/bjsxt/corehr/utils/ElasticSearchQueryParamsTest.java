package com.bjsxt.corehr.utils;

import com.bjsxt.corehr.constant.EsConstant;
import com.bjsxt.corehr.pojo.po.elasticSearch.OrgEsIndexData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

/**
 * @className: ElasticSearchQueryParamsTest
 * @description: ES动态传参工具类
 * @author: RenDeYou
 * @date: 2021/4/19 15:37
 */
@Slf4j
@SpringBootTest
class ElasticSearchQueryParamsTest {

//    @Test
    public void elasticSearchQueryParamsTest() {
        //组织ES返回字段
        String[] includeFields = new String[]{"id", "parentId", "name", "startDate", "environment"};
        /**
         * 创建对象
         * <OrgEsIndexData>builder()中<OrgEsIndexData>代表创建对象的对象类型
         */
        ElasticSearchQueryParams<OrgEsIndexData> params = ElasticSearchQueryParams.<OrgEsIndexData>builder()
                .indexNames(EsConstant.Org.ORG_INDEX)
                .typeNames(EsConstant.Org.ORG_TYPE)
                .clazz(OrgEsIndexData.class)
//                .includeFields(includeFields) //一般不用
//                .excludeFields() //一般不用
                .build();
        log.info("组织ES索引名称：{}", params.getIndexNames());
        log.info("组织ES类型名称：{}", params.getTypeNames());
        log.info("组织ES返回值类型：{}", params.getClazz());
        log.info("组织ES返回字段：{}", Arrays.toString(params.getIncludeFields()));
        log.info("组织ES不返回字段：{}", Arrays.toString(params.getExcludeFields()));
    }
}