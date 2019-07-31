package com.xuz.myproject.spcmemberweb.controller;

import com.xuz.myproject.base.JsonResult;
import com.xuz.myproject.spcbasedomain.db1.pojo.dao.UserDO;
import com.xuz.myproject.spcmemberweb.feign.MemberServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会员服务前端控制层
 *  
 * @author xuz
 * @date 2019/7/30 4:37 PM
 */
@RestController
@RequestMapping("member")
public class MemberController {

    @Autowired
    private MemberServiceFeign memberServiceFeign;

    @RequestMapping("doLogin")
    public JsonResult doLogin(@RequestBody UserDO userDO) {
        JsonResult jsonResult = memberServiceFeign.doLogin(userDO);
        return jsonResult;
    }

    @RequestMapping("regist")
    public JsonResult regist(@RequestBody UserDO userDO) {
        return memberServiceFeign.regist(userDO);
    }
}
