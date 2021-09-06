package com.cutty_pet.cutty_pet.customer.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cutty_pet.cutty_pet.adopt.entity.AdoptRecordHistory;
import com.cutty_pet.cutty_pet.common.PageParam;
import com.cutty_pet.cutty_pet.common.UUIDUtil;
import com.cutty_pet.cutty_pet.customer.entity.Customer;
import com.cutty_pet.cutty_pet.customer.dao.CustomerDao;
import com.cutty_pet.cutty_pet.customer.service.CustomerService;
import com.cutty_pet.cutty_pet.dic.entity.Dictionary;
import com.cutty_pet.cutty_pet.login.service.LoginService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 顾客表(Customer)表服务实现类
 *
 * @author makejava
 * @since 2021-04-03 18:31:17
 */
@Service("customerServiceImpl")
public class CustomerServiceImpl implements CustomerService {
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
    public Customer queryById(String id) {
        return this.customerDao.queryById(id);
    }
    @Override
    public List<Customer> queryByPage(PageParam pageParam) {
        JSONObject json = (JSONObject) JSON.toJSON(pageParam.getParam());
        Customer param = (Customer) JSON.toJavaObject(json, Customer.class);
        int pageNum=pageParam.getPageNum();
        int pageSize=pageParam.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        return this.customerDao.queryAll(param);
    }
    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Customer> queryAllByLimit(int offset, int limit) {
        return this.customerDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param customer 实例对象
     * @return 实例对象
     */
    @Override
    public Customer insert(Customer customer) throws Exception{
        if(!loginServiceImpl.verifiedUser(customer.getUsername())){
            customer.setId(UUIDUtil.getuuid());
            this.customerDao.insert(customer);
        }else{
            throw  new Exception();
        }


        return customer;
    }

    /**
     * 修改数据
     *
     * @param customer 实例对象
     * @return 实例对象
     */
    @Override
    public Customer update(Customer customer) {
        this.customerDao.update(customer);
        return this.queryById(customer.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.customerDao.deleteById(id) > 0;
    }
}
