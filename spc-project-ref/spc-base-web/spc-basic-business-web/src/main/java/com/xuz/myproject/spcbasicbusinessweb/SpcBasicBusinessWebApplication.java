package com.xuz.myproject.spcbasicbusinessweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.xuz.myproject.spcbasicbusinessweb.feign")
@SpringBootApplication(scanBasePackages = "com.xuz.myproject")
public class SpcBasicBusinessWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpcBasicBusinessWebApplication.class, args);
    }

}
