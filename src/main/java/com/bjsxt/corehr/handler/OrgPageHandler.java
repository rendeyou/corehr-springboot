package com.bjsxt.corehr.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bjsxt.corehr.constant.enums.OrgPageEnum;
import com.bjsxt.corehr.constant.enums.StringEnum;
import com.bjsxt.corehr.pojo.req.org.OrgUpdateReq;
import com.bjsxt.corehr.pojo.vo.ResponseVO;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @className: OrgPageHandler
 * @description: 组织信息集处理器
 * @author: RenDeYou
 * @date: 2021/4/15 19:00
 */
@Slf4j
public abstract class OrgPageHandler {
    /**
     * 组织信息集处理器类型
     * orgPageAbstractHandlerMap.put(val.getOrgPageEnum(), val);
     */
    public abstract OrgPageEnum getOrgPageEnum();

    /**
     * 插入和更新组织信息
     */
    public abstract ResponseVO editSaveOrgInfo(OrgUpdateReq orgUpdateInfo);

    /**
     * 插入组织其他信息
     */
    public abstract ResponseVO addOrgOtherInfo(OrgUpdateReq orgUpdateInfo);

    /**
     * 更新组织其他信息
     */
    public abstract ResponseVO updateOrgOtherInfo(OrgUpdateReq orgUpdateInfo);

    /**
     * 删除组织其他信息
     */
    public abstract ResponseVO deleteOrgOtherInfo(OrgUpdateReq orgUpdateInfo);


    /**
     * @param t     bean
     * @param clazz bean的类型
     * @param map   map
     * @return T bean的泛型
     * @description: 数据转换，map转bean
     * @author: RenDeYou
     * @date: 2021/4/18 20:00
     */
    public <T> T convertMapToBean(T t, Class<T> clazz, Map<String, Object> map) {
        //Object转String
        String jsonString = JSON.toJSONString(t);
        //String转JSONObject
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        if (null != map && !map.isEmpty()) {
            map.forEach((k, v) -> jsonObject.put(k, v));
        }
        //String转Object
        t = JSONObject.parseObject(jsonObject.toJSONString(), clazz);
        return t;
    }

    /**
     * @param t     bean
     * @param clazz bean的类型
     * @param map   map
     * @return T bean的泛型
     * @description: 数据转换，map转bean
     * @author: RenDeYou
     * @date: 2021/4/18 20:00
     */
    public <T> T convertMapToBean(T t, Class<T> clazz, Map<String, Object> map, Boolean needPerm) {
        //Object转String
        String jsonString = JSON.toJSONString(t);
        //String转JSONObject
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        //权限处理bean
        if (needPerm && null != map && !map.isEmpty()) {
            //处理数字类型
            List<String> numberTypes = Lists.newArrayList("java.lang.Long", "java.lang.Integer", "java.lang.Short", "java.lang.Byte", "long", "int", "short", "byte");
            //处理时间类型
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date defaultDate = null;
            try {
                defaultDate = sdf.parse("9999-01-01 00:00:00");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //处理bean
            List<Field> fields = this.getReflectFields(t);
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                String fieldTypeName = field.getType().getName();
                log.info("字段：{}", fieldName);
                log.info("字段类型：{}", fieldTypeName);
                if (map.containsKey(fieldName) && numberTypes.contains(fieldTypeName)) {
                    Object obj = map.get(fieldName);
                    if (null != obj && "*****".equals(obj.toString())) {
                        map.put(fieldName, -128);
                    }
                }
                if (map.containsKey(fieldName) && "java.util.Date".equals(fieldTypeName)) {
                    Object obj = map.get(fieldName);
                    if (null != obj && "*****".equals(obj.toString())) {
                        map.put(fieldName, defaultDate);
                    }
                }
            }
        }
        if (null != map && !map.isEmpty()) {
            map.forEach((k, v) -> jsonObject.put(k, v));
        }
        //String转Object
        t = JSONObject.parseObject(jsonObject.toJSONString(), clazz);
        return t;
    }

    /**
     * clazz.getDeclaredFields();
     * 获取某个类所有的字段,即包括public、protected、private,但是不包括父类的字段。
     */
    private List<Field> getReflectFields(Object obj) {
        List<Field> fieldList = Lists.newArrayList();
        Class<?> clazz = obj.getClass();
        while (null != clazz) {
            Field[] declaredFields = clazz.getDeclaredFields();
            List<Field> fields = Lists.newArrayList(declaredFields);
            fieldList.addAll(fields);
            clazz = clazz.getSuperclass();
        }
        return fieldList;
    }

    /**
     * Object公共判空方法
     */
    protected Map<String, Object> ObjectNotNull(Object obj, String desc) {
        Map<String, Object> map = Maps.newHashMap();
        //校验通过标识：true通过 false未通过
        Boolean flag = true;

        if (null == obj) {
            flag = false;
            map.put(StringEnum.DESC.getStringName(), desc + "不能为空");
        }

        map.put(StringEnum.FLAG.getStringName(), flag);
        return map;
    }

    /**
     * String公共判空方法
     */
    protected Map<String, Object> StringNotNull(String str, String desc) {
        Map<String, Object> map = Maps.newHashMap();
        //校验通过标识：true通过 false未通过
        Boolean flag = true;

        if (StringUtils.isBlank(str)) {
            flag = false;
            map.put(StringEnum.DESC.getStringName(), desc + "不能为空");
        }

        map.put(StringEnum.FLAG.getStringName(), flag);
        return map;
    }


}
