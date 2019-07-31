package com.xuz.myproject.spcbasicbusiness;

import com.xuz.myproject.spcbasedata.config.DBConfig1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//// 加载两个配置实体类
@EnableConfigurationProperties(value = {DBConfig1.class})
@SpringBootApplication(scanBasePackages = {"com.xuz.myproject"})
@EnableEurekaClient
public class SpcBasicBusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpcBasicBusinessApplication.class, args);
    }

}
