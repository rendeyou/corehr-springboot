package com.bjsxt.corehr.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @className: NotInSwagger
 * @description: 自定义注解，不显示在Swagger中
 * @author: RenDeYou
 * @date: 2021/4/15 17:40
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotInSwagger {
}
