package com.shaunz.webform.web.author.entity;

import com.shaunz.framework.core.BaseEntity;

public class Author extends BaseEntity{
    private String name;

    private String pwd;

    private String aliasNm;

    private String gender;

    private String email;

    private String closeFlg;

    private String lockUp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getAliasNm() {
        return aliasNm;
    }

    public void setAliasNm(String aliasNm) {
        this.aliasNm = aliasNm == null ? null : aliasNm.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getCloseFlg() {
        return closeFlg;
    }

    public void setCloseFlg(String closeFlg) {
        this.closeFlg = closeFlg == null ? null : closeFlg.trim();
    }

    public String getLockUp() {
        return lockUp;
    }

    public void setLockUp(String lockUp) {
        this.lockUp = lockUp == null ? null : lockUp.trim();
    }
}