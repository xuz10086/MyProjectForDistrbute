package com.xuz.myproject.spcbaseapi.service;

import com.xuz.myproject.base.JsonResult;
import com.xuz.myproject.spcbasedomain.db1.pojo.dao.UserDO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 会员服务暴露接口
 *  
 * @author xuz
 * @date 2019/7/30 4:27 PM
 */
public interface MemberService {

    @RequestMapping(value = "regist", method = RequestMethod.POST)
    JsonResult regist(@RequestBody UserDO userDO);

    @RequestMapping(value = "doLogin", method = RequestMethod.POST)
    JsonResult doLogin(@RequestBody UserDO userDO);
}
