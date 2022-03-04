package com.bjsxt.corehr.constant;

/**
 * @className: RedisConstant
 * @description: RedisConstant
 * @author: RenDeYou
 * @date: 2021/4/19 13:13
 */
public class RedisConstant {

    /**
     * 组织相关redis key
     */
    public static class Org {
        /**
         * 添加组织Redis锁
         */
        public final static String ORG_ADD_LOCK_KEY = "corehr:org:add:lock";

        /**
         * 组织限制条件
         */
        public final static String ORG_CONDITION_MAP_KEY = "corehr:org:condition:map:%s";
    }

    /**
     * 单位相关redis key
     */
    public static class Position {
        /**
         * 添加单位Redis锁
         */
        public final static String POS_ADD_LOCK_KEY = "corehr:pos:add:lock";

        /**
         * 单位限制条件
         */
        public final static String POS_CONDITION_MAP_KEY = "corehr:pos:condition:map:%s";
    }

    //私有构造方法，公有获取方法
    private RedisConstant() {
    }

    public static String getRedisKey(String prefix, Object obj) {
        return String.format(prefix, obj);
    }

}
