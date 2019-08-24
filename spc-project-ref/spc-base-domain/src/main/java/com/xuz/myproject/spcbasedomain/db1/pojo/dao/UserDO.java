package com.xuz.myproject.spcbasedomain.db1.pojo.dao;

import java.io.Serializable;

public class UserDO implements Serializable {
    private static final long serialVersionUID = 7854837466417999254L;

    private Integer lUserId;

    private String vcUserName;

    private String vcUserPassword;

    private Integer openId;

    private String email;

    public Integer getlUserId() {
        return lUserId;
    }

    public void setlUserId(Integer lUserId) {
        this.lUserId = lUserId;
    }

    public String getVcUserName() {
        return vcUserName;
    }

    public void setVcUserName(String vcUserName) {
        this.vcUserName = vcUserName;
    }

    public String getVcUserPassword() {
        return vcUserPassword;
    }

    public void setVcUserPassword(String vcUserPassword) {
        this.vcUserPassword = vcUserPassword;
    }

    public Integer getOpenId() {
        return openId;
    }

    public void setOpenId(Integer openId) {
        this.openId = openId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}