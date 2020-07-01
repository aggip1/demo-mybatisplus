package com.ag;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ag.repository")
public class DemoMybatisplusApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoMybatisplusApplication.class, args);
        System.out.println("代码修改中......");

    }

}
