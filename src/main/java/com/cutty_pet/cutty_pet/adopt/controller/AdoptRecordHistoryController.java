package com.cutty_pet.cutty_pet.adopt.controller;

import com.cutty_pet.cutty_pet.adopt.entity.AdoptRecordHistory;
import com.cutty_pet.cutty_pet.adopt.service.AdoptRecordHistoryService;
import com.cutty_pet.cutty_pet.common.PageParam;
import com.cutty_pet.cutty_pet.common.PageResult;
import com.cutty_pet.cutty_pet.common.ResponseDto;
import com.cutty_pet.cutty_pet.common.TokenUtil;
import com.cutty_pet.cutty_pet.dic.entity.Dictionary;
import com.cutty_pet.cutty_pet.pet.dto.PetStorageDto;
import com.cutty_pet.cutty_pet.pet.vo.PetStorageVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 客户收养宠物记录表(AdoptRecordHistory)表控制层
 *
 * @author makejava
 * @since 2021-04-03 19:17:23
 */
@RestController
@RequestMapping("adoptRecordHistory")
public class AdoptRecordHistoryController {
    /**
     * 服务对象
     */
    @Resource
    private AdoptRecordHistoryService adoptRecordHistoryServiceImpl;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public AdoptRecordHistory selectOne(String id) {
        return this.adoptRecordHistoryServiceImpl.queryById(id);
    }
    /**
     * 顾客收养宠物历史查询 API007
     *
     * @param
     * @return
     */
    @RequestMapping("queryByPage")
    @ResponseBody
    PageResult querybyPage(@RequestBody PageParam vo, HttpServletRequest ret)throws  Exception{
        ResponseDto result=null;
        int code =200;
        String msg="success";
        try{
            List<AdoptRecordHistory> data=adoptRecordHistoryServiceImpl.queryByPage(vo);
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

    /**
     * 顾客收养宠物接口 API006
     *
     * @param
     * @return
     */
    @RequestMapping("customerAdopt")
    @ResponseBody
    public ResponseDto customerAdopt(@RequestBody AdoptRecordHistory adoptRecordHistoryvo, HttpServletRequest ret) {
        ResponseDto result=null;
        try{
            String xtoken= TokenUtil.requestGetXToken(ret);
            String userName=TokenUtil.tokenGetUsername(xtoken);
            adoptRecordHistoryvo.setAdoptUsername(userName);
            adoptRecordHistoryvo.setCreateUser(userName);
            adoptRecordHistoryvo.setCreateTime(new Date());
            adoptRecordHistoryServiceImpl.customerAdopt(adoptRecordHistoryvo);
            result=new ResponseDto(200,"success",null);
        }catch (Exception e){
            result=new ResponseDto(500,"error:"+e.getMessage(),null);
            e.printStackTrace();
        }
        return result;
    }

}
