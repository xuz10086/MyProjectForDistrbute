package com.xuz.myproject.spcbasicbusiness.service.impl;

import com.xuz.myproject.base.JsonResult;
import com.xuz.myproject.base.JsonResultFactory;
import com.xuz.myproject.constants.Constants;
import com.xuz.myproject.spcbaseapi.service.UserService;
import com.xuz.myproject.spcbasedata.service.DefualtPersistBaseService;
import com.xuz.myproject.spcbasedomain.db1.pojo.dao.UserDO;
import com.xuz.myproject.spcbaseservice.service.api.UserBaseService;
import com.xuz.myproject.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserBaseService userBaseService;
    @Autowired
    private DefualtPersistBaseService defualtPersistBaseService;

    @Override
    public JsonResult queryUser() {
        List<UserDO> userList = userBaseService.queryUser();
        return JsonResultFactory.setResultSuccess(userList);
    }

    @Override
    public JsonResult queryUserById(@RequestParam("lUserId") Long lUserId) {
        // 参数校验 。。。

        String key = "lUserId_" + lUserId;
        // 如果redis缓存中存在，直接拿取
        if (RedisUtil.hasKey2(key)) {
            UserDO userRedis = RedisUtil.getObj2(key, UserDO.class);
            return JsonResultFactory.setResultSuccess(userRedis);
        }

        UserDO userDO = userBaseService.queryUserById(lUserId);
        if (null == userDO) {
            return JsonResultFactory.setResultSuccess();
        }
        // 插入缓存
        log.info("####用户信息存放在redis中... key为:{},value为:{}", key, userDO.toString());
        // 注意：缺少超时时间单位后会出现redis解析失败
        RedisUtil.setObj2(key, userDO, Constants.TOKEN_HOUR_TIME, TimeUnit.SECONDS);
        return JsonResultFactory.setResultSuccess(userDO);
    }
}
