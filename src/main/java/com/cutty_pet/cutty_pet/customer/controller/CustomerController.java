package com.cutty_pet.cutty_pet.customer.controller;

import com.cutty_pet.cutty_pet.common.PageParam;
import com.cutty_pet.cutty_pet.common.PageResult;
import com.cutty_pet.cutty_pet.common.ResponseDto;
import com.cutty_pet.cutty_pet.customer.entity.Customer;
import com.cutty_pet.cutty_pet.customer.service.CustomerService;
import com.cutty_pet.cutty_pet.dic.entity.Dictionary;
import com.cutty_pet.cutty_pet.user.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 顾客表(Customer)表控制层
 *
 * @author makejava
 * @since 2021-04-03 18:31:17
 */
@RestController
@RequestMapping("customer")
public class CustomerController {
    /**
     * 服务对象
     */
    @Resource
    private CustomerService customerServiceImpl;
    @Resource
    private ObjectMapper jacksonObjectMapper;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Customer selectOne(String id) {
        return this.customerServiceImpl.queryById(id);
    }

    /**顾客注册     API0010
     * 新增数据
     *
     * @param customer 实例对象
     * @return 实例对象
     */
    @RequestMapping("insert")
    @ResponseBody
    ResponseDto insert(@RequestBody Customer customer){
        ResponseDto result=null;
        try{
            customerServiceImpl.insert(customer);
            result=new ResponseDto(200,"success",null);
        }catch (Exception e){
            result=new ResponseDto(500,"error:"+e.getMessage(),null);
            e.printStackTrace();
        }
        return result;
    }

    /**顾客查询    API0011
     * 查询多条数据
     *
     * @return 对象列表
     */
    @RequestMapping("queryByPage")
    @ResponseBody
    PageResult querybyPage(@RequestBody PageParam vo)throws  Exception{
        ResponseDto result=null;
        int code =200;
        String msg="success";
        try{
            List<Customer> data=customerServiceImpl.queryByPage(vo);
            result=new ResponseDto(200,"success",data);
        }catch (Exception e){

            code=500;
            msg="error:"+e.getMessage();
            result=new ResponseDto(500,msg,null);
            e.printStackTrace();
        }
        PageResult pageResult=new PageResult(vo,result,code,msg);
        return pageResult;
    }

    /**修改顾客        API0016
     * 修改数据
     *
     * @param User 实例对象
     * @return 实例对象
     */
    @RequestMapping("update")
    @ResponseBody
    ResponseDto update(@RequestBody Customer User){
        ResponseDto result=null;
        try{
            customerServiceImpl.update(User);
            result=new ResponseDto(200,"success",null);
        }catch (Exception e){
            result=new ResponseDto(500,"error:"+e.getMessage(),null);
            e.printStackTrace();
        }
        return result;
    }
    /**删除顾客           API0017
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
            customerServiceImpl.deleteById(id);
            result=new ResponseDto(200,"success",null);
        }catch (Exception e){
            result=new ResponseDto(500,"error:"+e.getMessage(),null);
            e.printStackTrace();
        }
        return result;
    }

}
