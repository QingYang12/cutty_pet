package com.cutty_pet.cutty_pet.login.controller;

import com.cutty_pet.cutty_pet.common.PageParam;
import com.cutty_pet.cutty_pet.common.PageResult;
import com.cutty_pet.cutty_pet.common.ResponseDto;
import com.cutty_pet.cutty_pet.login.entity.UserToken;
import com.cutty_pet.cutty_pet.login.service.LoginService;
import com.cutty_pet.cutty_pet.user.entity.User;
import com.cutty_pet.cutty_pet.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * 员工管理员表(User)表控制层
 *
 * @author makejava
 * @since 2021-04-03 18:36:29
 */
@RestController
@RequestMapping("/user")
public class LoginController {
    /**
     * 服务对象
     */
    @Resource
    private LoginService loginServiceImpl;


    /**登录系统     API0022
     *
     *
     * @param User 实例对象
     * @return 实例对象
     */
    @RequestMapping("login")
    @ResponseBody
    ResponseDto login(@RequestBody User User){
        ResponseDto result=null;
        try{
            UserToken userToken =loginServiceImpl.login(User);
            result=new ResponseDto(200,"success",userToken);
        }catch (Exception e){
            result=new ResponseDto(500,"error:"+e.getMessage(),null);
            e.printStackTrace();
        }
        return result;
    }
    /**登出系统     API0023
     *
     *
     * @param
     * @return 实例对象
     */
    @RequestMapping("logout")
    @ResponseBody
    ResponseDto logout(HttpServletRequest ret){
        ResponseDto result=null;
        try{
            loginServiceImpl.logout(ret);
            result=new ResponseDto(200,"success",null);
        }catch (Exception e){
            result=new ResponseDto(500,"error:"+e.getMessage(),null);
            e.printStackTrace();
        }
        return result;
    }
}
