package com.cutty_pet.cutty_pet.user.vo;

import com.cutty_pet.cutty_pet.user.entity.User;

import java.io.Serializable;
import java.util.Date;

/**
 * 员工管理员表(User)实体类
 *
 * @author makejava
 * @since 2021-04-03 18:36:29
 */
public class UserDto extends User implements Serializable {
    String token ;
    String name ;
    String avatar ;
    String introduction ;

    String[] roles;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }
}
