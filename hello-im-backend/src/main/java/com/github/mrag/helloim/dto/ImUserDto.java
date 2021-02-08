package com.github.mrag.helloim.dto;

import com.github.mrag.helloim.common.Consts;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class ImUserDto implements Serializable {
    private Integer id;

    @Pattern(regexp = Consts.RegExp.USERNAME)
    private String username;

    @Pattern(regexp = Consts.RegExp.PASSWORD)
    private String password;

    @Pattern(regexp = Consts.RegExp.PHONE)
    private String bindPhone;

    @Email
    private String bindEmail;

    @Pattern(regexp = Consts.RegExp.URL)
    private String headPortraitUrl;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBindPhone() {
        return bindPhone;
    }

    public void setBindPhone(String bindPhone) {
        this.bindPhone = bindPhone;
    }

    public String getBindEmail() {
        return bindEmail;
    }

    public void setBindEmail(String bindEmail) {
        this.bindEmail = bindEmail;
    }

    public String getHeadPortraitUrl() {
        return headPortraitUrl;
    }

    public void setHeadPortraitUrl(String headPortraitUrl) {
        this.headPortraitUrl = headPortraitUrl;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", username=" + username +
                ", password=" + password +
                ", bindPhone=" + bindPhone +
                ", bindEmail=" + bindEmail +
                ", headPortraitUrl=" + headPortraitUrl +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}