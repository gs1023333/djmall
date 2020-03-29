package com.dj.mall.pro.auth;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Written by : GSSS
 * @创建时间 Date : 2020-3-24 下午 05:49
 */
@SpringBootApplication
@EnableDubbo
@MapperScan("com.dj.mall.mapper")
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
