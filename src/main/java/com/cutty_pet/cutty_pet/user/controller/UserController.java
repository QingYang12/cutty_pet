package com.cutty_pet.cutty_pet.user.controller;

import com.cutty_pet.cutty_pet.common.BeanCopierUtils;
import com.cutty_pet.cutty_pet.common.PageParam;
import com.cutty_pet.cutty_pet.common.PageResult;
import com.cutty_pet.cutty_pet.common.ResponseDto;
import com.cutty_pet.cutty_pet.user.entity.User;
import com.cutty_pet.cutty_pet.user.service.UserService;
import com.cutty_pet.cutty_pet.user.vo.UserDto;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * 员工管理员表(User)表控制层
 *
 * @author makejava
 * @since 2021-04-03 18:36:29
 */
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userServiceImpl;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public User selectOne(String id) {
        return this.userServiceImpl.queryById(id);
    }
    /**查询管理员 API0015
     * 查询多条数据
     *
     * @return 对象列表
     */
    @RequestMapping(value="queryByPage" ,method = POST)
    @ResponseBody
    PageResult querybyPage(@RequestBody PageParam vo)throws  Exception{
        ResponseDto result=null;
        int code =200;
        String msg="success";
        try{
            List<User> data=userServiceImpl.queryByPage(vo);
            result=new ResponseDto(code,msg,data);
        }catch (Exception e){

            code=500;
            msg="error:"+e.getMessage();
            result=new ResponseDto(500,msg,null);
            e.printStackTrace();
        }
        PageResult pageResult=new PageResult(vo,result,code,msg);
        return pageResult;
    }
    /**新增管理员     API0012
     * 新增数据
     *
     * @param User 实例对象
     * @return 实例对象
     */
    @RequestMapping("insert")
    @ResponseBody
    ResponseDto insert(@RequestBody User User){
        ResponseDto result=null;
        try{
            userServiceImpl.insert(User);
            result=new ResponseDto(200,"success",null);
        }catch (Exception e){
            result=new ResponseDto(500,"error:"+e.getMessage(),null);
            e.printStackTrace();
        }
        return result;
    }
    /**修改管理员         API0014
     * 修改数据
     *
     * @param User 实例对象
     * @return 实例对象
     */
    @RequestMapping("update")
    @ResponseBody
    ResponseDto update(@RequestBody User User){
        ResponseDto result=null;
        try{
            userServiceImpl.update(User);
            result=new ResponseDto(200,"success",null);
        }catch (Exception e){
            result=new ResponseDto(500,"error:"+e.getMessage(),null);
            e.printStackTrace();
        }
        return result;
    }
    /**删除管理员           API0013
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @RequestMapping("delete")
    @ResponseBody
    ResponseDto deleteById(@RequestBody String id){
        ResponseDto result=null;
        try{
            userServiceImpl.deleteById(id);
            result=new ResponseDto(200,"success",null);
        }catch (Exception e){
            result=new ResponseDto(500,"error:"+e.getMessage(),null);
            e.printStackTrace();
        }
        return result;
    }


    /**根据token拿到用户user信息         API0024
     * 修改数据
     *
     * @param
     * @return 实例对象
     */
    @RequestMapping("info")
    @ResponseBody
    ResponseDto tokenGetUserinfo(@RequestParam("token") String token){
        ResponseDto result=null;
        try{
            User user=userServiceImpl.tokenGetUserinfo(token);
            UserDto userDto=new UserDto();
            BeanCopierUtils.copy(user,userDto);
            userDto.setName(user.getUsername());
            userDto.setIntroduction("1");
            userDto.setAvatar("1");
            result=new ResponseDto(200,"success",userDto);
        }catch (Exception e){
            result=new ResponseDto(500,"error:"+e.getMessage(),null);
            e.printStackTrace();
        }
        return result;
    }
}
