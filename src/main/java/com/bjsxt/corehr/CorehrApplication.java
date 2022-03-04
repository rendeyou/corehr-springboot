package com.bjsxt.corehr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//操作数据库
@MapperScan("com.bjsxt.corehr.mapper")
public class CorehrApplication {

    public static void main(String[] args) {
        SpringApplication.run(CorehrApplication.class, args);
    }

}
