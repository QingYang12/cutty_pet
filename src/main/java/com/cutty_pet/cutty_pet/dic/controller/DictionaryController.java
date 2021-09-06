package com.cutty_pet.cutty_pet.dic.controller;

import com.cutty_pet.cutty_pet.common.PageParam;
import com.cutty_pet.cutty_pet.common.PageResult;
import com.cutty_pet.cutty_pet.common.ResponseDto;
import com.cutty_pet.cutty_pet.common.TokenUtil;
import com.cutty_pet.cutty_pet.dic.entity.Dictionary;
import com.cutty_pet.cutty_pet.dic.service.DictionaryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 系统字典表(Dictionary)表控制层
 *
 * @author makejava
 * @since 2021-04-03 18:35:31
 */
@RestController
@RequestMapping("dictionary")
public class DictionaryController {
    /**
     * 服务对象
     */
    @Resource
    private DictionaryService dictionaryServiceImpl;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Dictionary selectOne(String id) {
        return this.dictionaryServiceImpl.queryById(id);
    }
    /**查询宠物字典 API005
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
            List<Dictionary> data=dictionaryServiceImpl.queryByPage(vo);
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
    /**新增宠物字典     API002
     * 新增数据
     *
     * @param dictionary 实例对象
     * @return 实例对象
     */
    @RequestMapping("insert")
    @ResponseBody
    ResponseDto insert(@RequestBody Dictionary dictionary, HttpServletRequest ret){
        ResponseDto result=null;
        try{
            String xtoken=TokenUtil.requestGetXToken(ret);
            String userName=TokenUtil.tokenGetUsername(xtoken);
            dictionary.setCreateUser(userName);
            dictionary.setCreateTime(new Date());
            dictionaryServiceImpl.insert(dictionary);
            result=new ResponseDto(200,"success",null);
        }catch (Exception e){
            result=new ResponseDto(500,"error:"+e.getMessage(),null);
            e.printStackTrace();
        }
        return result;
    }
    /**修改宠物字典         API003
     * 修改数据
     *
     * @param dictionary 实例对象
     * @return 实例对象
     */
    @RequestMapping("update")
    @ResponseBody
    ResponseDto update(@RequestBody Dictionary dictionary){
        ResponseDto result=null;
        try{
            dictionaryServiceImpl.update(dictionary);
            result=new ResponseDto(200,"success",null);
        }catch (Exception e){
            result=new ResponseDto(500,"error:"+e.getMessage(),null);
            e.printStackTrace();
        }
        return result;
    }
    /**删除宠物字典           API004
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @RequestMapping("delete")
    @ResponseBody
    ResponseDto deleteById(@RequestBody String id){
        ResponseDto result=null;
        int code =200;
        String msg="success";
        try{
            dictionaryServiceImpl.deleteById(id);
            result=new ResponseDto(200,"success",null);
        }catch (Exception e){
            result=new ResponseDto(500,"error:"+e.getMessage(),null);
            e.printStackTrace();
        }
        return result;
    }
}
