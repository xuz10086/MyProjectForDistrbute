package com.xuz.myproject.spcbasedata.mapper.db1;

import com.xuz.myproject.spcbasedata.persist.IPersistDataAccess;
import com.xuz.myproject.spcbasedomain.db1.pojo.dao.UserDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends IPersistDataAccess<UserDO, Long> {

}