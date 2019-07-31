package com.xuz.myproject.spcmemberweb.hystrix;

import com.xuz.myproject.base.JsonResult;
import com.xuz.myproject.base.JsonResultFactory;
import com.xuz.myproject.spcbasedomain.db1.pojo.dao.UserDO;
import com.xuz.myproject.spcmemberweb.feign.MemberServiceFeign;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class MemberServiceFeignHystrix implements MemberServiceFeign {
    @Override
    public JsonResult regist(@RequestBody UserDO userDO) {
        return JsonResultFactory.setResultError("服务降级提示");
    }

    @Override
    public JsonResult doLogin(@RequestBody UserDO userDO) {
        return JsonResultFactory.setResultError("服务降级提示");
    }
}
