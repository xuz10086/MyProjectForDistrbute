package com.xuz.myproject.spcbaseapi.service;

import com.xuz.myproject.base.JsonResult;
import org.springframework.web.bind.annotation.*;

/**
 * 用户接口
 *
 * @author xuz
 * @date 2019/7/30 4:23 PM
 */
public interface UserService {

    @RequestMapping("queryUser")
    JsonResult queryUser();

    @RequestMapping("queryUserById")
    JsonResult queryUserById(@RequestParam("lUserId") Long lUserId);
}
