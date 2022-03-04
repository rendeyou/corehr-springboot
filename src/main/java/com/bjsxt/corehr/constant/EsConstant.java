package com.bjsxt.corehr.constant;

/**
 * @className: EsConstant
 * @description: EsConstant
 * @author: RenDeYou
 * @date: 2021/4/19 15:53
 */
public class EsConstant {

    /**
     * 组织
     */
    public static class Org {
        public final static String ORG_INDEX = "index_org_search";
        public final static String ORG_TYPE = "type_org_search";
    }

    /**
     * 岗位
     */
    public static class Position {
        public final static String POSITION_INDEX = "index_position_search";
        public final static String POSITION_TYPE = "type_position_search";
    }

    //私有构造方法，公有获取方法
    private EsConstant() {
    }

    public static String getEsKey(String prefix, Object obj) {
        return String.format(prefix, obj);
    }
}
