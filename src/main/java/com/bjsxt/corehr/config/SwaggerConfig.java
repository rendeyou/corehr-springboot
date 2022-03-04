package com.bjsxt.corehr.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.not;
import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;

/**
 * @className: SwaggerConfig
 * @description: Swagger配置类
 * @author: RenDeYou
 * @date: 2021/4/15 17:15
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket getDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                //扫描包
                .apis(RequestHandlerSelectors.basePackage("com.bjsxt.corehr.controller"))
                //选择类
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                //排除类：自定义注解，不显示在Swagger中
                .apis(not(withClassAnnotation(NotInSwagger.class)))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(
                        new ApiInfoBuilder()
                                .version("1.0")
                                .title("corehr")
                                .description("核心人事")
                                .termsOfServiceUrl("http://localhost:9100/swagger-ui.html")
                                .contact(new Contact("RenDeYou", "http://www.rendeyou.com", "280732160@qq.com"))
                                .build()
                );
    }
}
