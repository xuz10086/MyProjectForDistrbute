package com.xuz.myproject.spcbasedomain.db1.pojo.dao;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDO implements Serializable {
    private static final long serialVersionUID = 7854837466417999254L;

    private Integer lUserId;

    private String vcUserName;

    private String vcUserPassword;

    private Integer openId;

    private String email;
}