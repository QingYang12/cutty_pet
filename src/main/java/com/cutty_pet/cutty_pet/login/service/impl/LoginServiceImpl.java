package com.cutty_pet.cutty_pet.login.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cutty_pet.cutty_pet.common.PageParam;
import com.cutty_pet.cutty_pet.common.TokenUtil;
import com.cutty_pet.cutty_pet.common.UUIDUtil;
import com.cutty_pet.cutty_pet.customer.dao.CustomerDao;
import com.cutty_pet.cutty_pet.customer.entity.Customer;
import com.cutty_pet.cutty_pet.login.entity.UserToken;
import com.cutty_pet.cutty_pet.login.service.LoginService;
import com.cutty_pet.cutty_pet.user.dao.UserDao;
import com.cutty_pet.cutty_pet.user.entity.User;
import com.github.pagehelper.PageHelper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 员工管理员表(User)表服务实现类
 *
 * @author makejava
 * @since 2021-04-03 18:36:29
 */
@Service("loginServiceImpl")
public class LoginServiceImpl implements LoginService {
    @Resource
    private UserDao userDao;
    @Resource
    private CustomerDao customerDao;


    @Override
    public UserToken login(User user) throws Exception{
        UserToken userToken=new UserToken();
        List<User> userlist= this.userDao.queryAll(user);
        Customer customer=new Customer();
        customer.setUsername(user.getUsername());
        customer.setPassword(user.getPassword());
        List<Customer> customerlist=this.customerDao.queryAll(customer);
        if(userlist.size()>0){
            User userItem=userlist.get(0);
            String userName=userItem.getUsername();
            String password=userItem.getPassword();
            String token= TokenUtil.getToken(userName);
            userToken.setToken(token);
        }else if(customerlist.size()>0){
            Customer userItem=customerlist.get(0);
            String userName=userItem.getUsername();
            String password=userItem.getPassword();
            String token= TokenUtil.getToken(userName);
            userToken.setToken(token);
        }else{
            throw new Exception();
        }
        return  userToken;
    }
    @Override
    public void logout(HttpServletRequest ret) {
        //String xtoken=TokenUtil.requestGetXToken(ret);
        //String UserName=TokenUtil.tokenGetUsername(xtoken);
    }
    @Override
    public boolean verifiedUser(String username) {
        boolean  res=false;
        User user=new User();
        user.setUsername(username);
        List<User> userlist= this.userDao.queryAll(user);
        Customer customer=new Customer();
        customer.setUsername(username);
        List<Customer> customerlist=this.customerDao.queryAll(customer);
        if(userlist.size()>0||customerlist.size()>0){
            res=true;
        }
        return  res;
    }


}
