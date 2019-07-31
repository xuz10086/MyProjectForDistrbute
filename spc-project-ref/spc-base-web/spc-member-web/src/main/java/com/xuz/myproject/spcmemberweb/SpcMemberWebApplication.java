package com.xuz.myproject.spcmemberweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.xuz.myproject.spcmemberweb.feign")
@SpringBootApplication(scanBasePackages = "com.xuz.myproject")
public class SpcMemberWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpcMemberWebApplication.class, args);
    }

}
