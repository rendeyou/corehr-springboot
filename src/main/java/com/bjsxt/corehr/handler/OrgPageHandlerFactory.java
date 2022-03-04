package com.bjsxt.corehr.handler;

import com.bjsxt.corehr.constant.enums.OrgPageEnum;
import com.google.common.collect.Maps;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @className: OrgPageHandlerFactory
 * @description: 组织信息集处理器集合的工厂（注意不能定义为abstract class OrgPageHandlerFactory）
 * @author: RenDeYou
 * @date: 2021/4/15 18:55
 */
@Component
public class OrgPageHandlerFactory implements ApplicationContextAware {

    //声明orgPageHandlerMap集合
    private static Map<OrgPageEnum, OrgPageHandler> orgPageHandlerMap = Maps.newHashMap();

    //从SpringIOC容器获取，放入orgPageHandlerMap集合
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, OrgPageHandler> map = applicationContext.getBeansOfType(OrgPageHandler.class);
        map.forEach((key, val) -> {
            orgPageHandlerMap.put(val.getOrgPageEnum(), val);
        });
    }

    //从orgPageHandlerMap集合获取
    public static <T extends OrgPageHandler> T getOrgPageHandler(OrgPageEnum orgPageEnum) {
        return (T) orgPageHandlerMap.get(orgPageEnum);
    }
}
