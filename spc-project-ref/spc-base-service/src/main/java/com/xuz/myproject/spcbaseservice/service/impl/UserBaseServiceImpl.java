package com.xuz.myproject.spcbaseservice.service.impl;

import com.xuz.myproject.spcbasedata.mapper.db1.UserMapper;
import com.xuz.myproject.spcbasedata.persist.AbstractPersistBaseService;
import com.xuz.myproject.spcbasedata.service.AbstractBaseService;
import com.xuz.myproject.spcbasedata.service.DefualtPersistBaseService;
import com.xuz.myproject.spcbasedomain.db1.pojo.dao.UserDO;
import com.xuz.myproject.spcbaseservice.service.api.UserBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据库原子操作
 *
 * @author xuz
 * @date 2019/7/30 8:24 AM
 */
@Service
public class UserBaseServiceImpl extends AbstractBaseService<UserDO, Long> implements UserBaseService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DefualtPersistBaseService defualtPersistBaseService;

    @Override
    protected UserMapper getDao() {
        return userMapper;
    }

    @Override
    protected AbstractPersistBaseService getDataBaseService() {
        return defualtPersistBaseService;
    }

    /**
     * 查询用户列表
     *
     * @param
     * @return java.util.List<com.xuz.myproject.spcbasedomain.db1.pojo.dao.UserDO>
     * @author xuz
     * @date 2019/7/29 5:06 PM
     */
    @Override
    public List<UserDO> queryUser() {
        return super.query(null);
    }

    /**
     * 根据条件查询单个用户
     *
     * @param userDO
     * @return com.xuz.myproject.spcbasedomain.db1.pojo.dao.UserDO
     * @author xuz
     * @date 2019/7/30 9:16 AM
     */
    @Override
    public UserDO queryUserOne(UserDO userDO) {
        // 参数校验 。。。
        return super.queryOne(userDO);
    }

    /**
     * 根据用户ID查询用户
     *
     * @param lUserId
     * @return com.xuz.myproject.spcbasedomain.db1.pojo.dao.UserDO
     * @author xuz
     * @date 2019/7/29 5:06 PM
     */
    @Override
    public UserDO queryUserById(Long lUserId) {
        // 参数校验 。。。
        return super.load(lUserId);
    }

    /**
     * 新增用户
     *
     * @param userDO
     * @return void
     * @author xuz
     * @date 2019/7/30 11:12 AM
     */
    @Override
    public void insertUser(UserDO userDO) {
        // 参数校验 。。。
        super.insert(userDO);
    }


}
