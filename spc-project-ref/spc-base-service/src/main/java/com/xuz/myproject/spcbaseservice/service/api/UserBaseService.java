package com.xuz.myproject.spcbaseservice.service.api;

import com.xuz.myproject.spcbasedomain.db1.pojo.dao.UserDO;

import java.util.List;

public interface UserBaseService {

    List<UserDO> queryUser();

    UserDO queryUserOne(UserDO userDO);

    UserDO queryUserById(Long lUserId);

    void insertUser(UserDO userDO);
}
