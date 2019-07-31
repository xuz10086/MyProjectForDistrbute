package com.xuz.myproject.spcbasicbusinessweb.hystrix;

import com.xuz.myproject.spcbasicbusinessweb.feign.UserServiceFeign;
import com.xuz.myproject.base.JsonResult;
import com.xuz.myproject.base.JsonResultFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class UserServiceFeignHystrix implements UserServiceFeign {

    @Override
    public JsonResult queryUser() {
        return JsonResultFactory.setResultError("服务降级提示");
    }

    @Override
    public JsonResult queryUserById(@RequestParam("lUserId") Long lUserId) {
        return JsonResultFactory.setResultError("服务降级提示");
    }
}
