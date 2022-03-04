package com.bjsxt.corehr.constant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @className: RedisConstantTest
 * @description: RedisConstantTest
 * @author: RenDeYou
 * @date: 2021/4/19 13:31
 */
@Slf4j
@SpringBootTest
class RedisConstantTest {

    //    @Test
    public void redisConstantTest() {
        String redisKey = RedisConstant.getRedisKey(RedisConstant.Org.ORG_ADD_LOCK_KEY, null);
        log.info("获取redisKey={}", redisKey);
        String redisKey2 = RedisConstant.getRedisKey(RedisConstant.Org.ORG_CONDITION_MAP_KEY, "append");
        log.info("动态获取redisKey2={}", redisKey2);
    }

}