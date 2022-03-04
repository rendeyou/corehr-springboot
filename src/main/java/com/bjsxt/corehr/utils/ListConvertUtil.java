package com.bjsxt.corehr.utils;

import com.google.common.collect.Lists;
import org.apache.commons.beanutils.BeanUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @className: ListConvertUtil
 * @description: 集合类型转换工具类
 * @author: RenDeYou
 * @date: 2021/4/19 10:45
 */
public class ListConvertUtil<T> {

    private ListConvertUtil() {
    }

    public static <T> List<T> listConvert(List<?> sourceList, Class<T> clazz) {
        if (null == sourceList || sourceList.isEmpty()) {
            return Collections.emptyList();
        }
        //返回值
        List<T> targetList = Lists.newArrayList();
        targetList = sourceList.stream().map(source -> beanConvert(source, clazz)).collect(Collectors.toList());
        return targetList;
    }

    private static <T> T beanConvert(Object source, Class<T> clazz) {
        if (null == source) {
            return null;
        }
        //返回值
        T target = null;
        try {
            target = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            BeanUtils.copyProperties(target, source);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return target;
    }
}
