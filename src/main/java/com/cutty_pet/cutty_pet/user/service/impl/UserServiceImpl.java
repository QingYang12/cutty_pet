package com.cutty_pet.cutty_pet.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cutty_pet.cutty_pet.common.Base64PWDUtil;
import com.cutty_pet.cutty_pet.common.BeanCopierUtils;
import com.cutty_pet.cutty_pet.common.PageParam;
import com.cutty_pet.cutty_pet.common.UUIDUtil;
import com.cutty_pet.cutty_pet.customer.dao.CustomerDao;
import com.cutty_pet.cutty_pet.customer.entity.Customer;
import com.cutty_pet.cutty_pet.login.service.LoginService;
import com.cutty_pet.cutty_pet.user.entity.User;
import com.cutty_pet.cutty_pet.user.dao.UserDao;
import com.cutty_pet.cutty_pet.user.service.UserService;
import com.cutty_pet.cutty_pet.user.vo.UserDto;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 员工管理员表(User)表服务实现类
 *
 * @author makejava
 * @since 2021-04-03 18:36:29
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Resource
    private CustomerDao customerDao;
    @Resource
    private LoginService loginServiceImpl;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(String id) {
        return this.userDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userDao.queryAllByLimit(offset, limit);
    }
    @Override
    public List<User> queryByPage(PageParam pageParam) {
        JSONObject json = (JSONObject) JSON.toJSON(pageParam.getParam());
        User param = (User) JSON.toJavaObject(json, User.class);
        int pageNum=pageParam.getPageNum();
        int pageSize=pageParam.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        return this.userDao.queryAll(param);
    }
    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) throws Exception{
        if(!loginServiceImpl.verifiedUser(user.getUsername())){
            user.setId(UUIDUtil.getuuid());
            this.userDao.insert(user);
        }else{
            throw  new Exception();
        }

        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.userDao.deleteById(id) > 0;
    }


    @Override
    public UserDto tokenGetUserinfo(String token) throws Exception{
        byte[] byteArray=Base64PWDUtil.decryptBASE64(token);
        String username=new String(byteArray);
        User user=new User();
        user.setUsername(username);
        List<User> userlist=this.userDao.queryAll(user);

        Customer customer=new Customer();
        customer.setUsername(username);
        List<Customer> customerlist=this.customerDao.queryAll(customer);
        UserDto userRes=null;
        if(userlist.size()>0){
            String[] roles= new String[] {"admin"};
            UserDto userDtoItem=new UserDto();
            User userItem=userlist.get(0);
            BeanCopierUtils.copy(userItem,userDtoItem);
            userRes=userDtoItem;
            userRes.setRoles(roles);
        }else{
            String[] roles= new String[] {"editor"};
            UserDto userDtoItem=new UserDto();
            Customer customerItem=customerlist.get(0);
            BeanCopierUtils.copy(customerItem,userDtoItem);
            userRes=userDtoItem;
            userRes.setRoles(roles);
        }

        return userRes;
    }
}
