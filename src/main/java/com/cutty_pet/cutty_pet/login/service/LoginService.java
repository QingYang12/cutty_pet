package com.cutty_pet.cutty_pet.login.service;

import com.cutty_pet.cutty_pet.common.PageParam;
import com.cutty_pet.cutty_pet.login.entity.UserToken;
import com.cutty_pet.cutty_pet.user.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 员工管理员表(User)表服务接口
 *
 * @author makejava
 * @since 2021-04-03 18:36:29
 */

public interface LoginService {


    UserToken login(User user) throws Exception;
    void logout(HttpServletRequest ret);
    //校验方法  所有用户中是否包含username
    boolean verifiedUser(String username);

}
