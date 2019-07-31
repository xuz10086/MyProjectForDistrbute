package com.xuz.myproject.spcbasicbusinessweb.controller;

import com.xuz.myproject.spcbasicbusinessweb.feign.UserServiceFeign;
import com.xuz.myproject.base.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户前端控制层
 *
 * @author xuz
 * @date 2019/7/30 4:28 PM
 */
@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    private UserServiceFeign userServiceFeign;

    @RequestMapping("queryUser")
    public JsonResult queryUser() {
        JsonResult jsonResult = userServiceFeign.queryUser();
        return jsonResult;
    }

    @RequestMapping("queryUserById")
    public JsonResult queryUserById(@RequestParam("lUserId") Long lUserId) {
        JsonResult jsonResult = userServiceFeign.queryUserById(lUserId);
        return jsonResult;
    }

}
