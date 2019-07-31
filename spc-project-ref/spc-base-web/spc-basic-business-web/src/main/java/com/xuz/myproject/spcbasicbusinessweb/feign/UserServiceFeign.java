package com.xuz.myproject.spcbasicbusinessweb.feign;

import com.xuz.myproject.spcbasicbusinessweb.hystrix.UserServiceFeignHystrix;
import com.xuz.myproject.spcbaseapi.service.UserService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@FeignClient(value = "basic-business-service", fallback = UserServiceFeignHystrix.class) // 服务提供者的服务名，容错保护
@Component
public interface UserServiceFeign extends UserService {
}
