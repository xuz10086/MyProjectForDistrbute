package com.xuz.myproject.spcmemberservice;

import com.xuz.myproject.spcbasedata.config.DBConfig1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.xuz.myproject")
@EnableEurekaClient
//// 加载两个配置实体类
@EnableConfigurationProperties(value = {DBConfig1.class})
public class SpcMemberServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpcMemberServiceApplication.class, args);
    }

}
