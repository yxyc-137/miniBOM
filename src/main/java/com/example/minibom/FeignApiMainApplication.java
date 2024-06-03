package com.example.minibom;

/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2024-2024. All rights reserved.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动类
 *
 * @since 2024-04-10
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.huawei.innovation", "com.example.minibom"})
@EnableFeignClients
public class FeignApiMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeignApiMainApplication.class, args);
    }
}

