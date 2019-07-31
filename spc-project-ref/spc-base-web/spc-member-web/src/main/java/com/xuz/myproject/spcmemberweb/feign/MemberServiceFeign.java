package com.xuz.myproject.spcmemberweb.feign;

import com.xuz.myproject.spcbaseapi.service.MemberService;
import com.xuz.myproject.spcmemberweb.hystrix.MemberServiceFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@FeignClient(value = "member-service", fallback = MemberServiceFeignHystrix.class)
@Component
public interface MemberServiceFeign extends MemberService {
}
