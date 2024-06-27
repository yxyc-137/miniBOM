package com.example.minibom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.huawei.innovation", "com.example.minibom"})
@EnableFeignClients
public class MiniBomApplication {
    public static void main(String[] args) {
        SpringApplication.run(MiniBomApplication.class, args);
    }

}
