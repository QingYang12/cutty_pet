package com.cutty_pet.cutty_pet.customer.service;

import com.cutty_pet.cutty_pet.common.PageParam;
import com.cutty_pet.cutty_pet.customer.entity.Customer;
import com.cutty_pet.cutty_pet.dic.entity.Dictionary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 顾客表(Customer)表服务接口
 *
 * @author makejava
 * @since 2021-04-03 18:31:17
 */

public interface CustomerService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Customer queryById(String id);
    List<Customer> queryByPage(PageParam pageParam);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Customer> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param customer 实例对象
     * @return 实例对象
     */
    Customer insert(Customer customer) throws Exception;

    /**
     * 修改数据
     *
     * @param customer 实例对象
     * @return 实例对象
     */
    Customer update(Customer customer);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
