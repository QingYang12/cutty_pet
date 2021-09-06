package com.cutty_pet.cutty_pet.succour.controller;

import com.cutty_pet.cutty_pet.adopt.entity.AdoptRecordHistory;
import com.cutty_pet.cutty_pet.common.PageParam;
import com.cutty_pet.cutty_pet.common.PageResult;
import com.cutty_pet.cutty_pet.common.ResponseDto;
import com.cutty_pet.cutty_pet.common.TokenUtil;
import com.cutty_pet.cutty_pet.succour.entity.SuccourRecordHistory;
import com.cutty_pet.cutty_pet.succour.service.SuccourRecordHistoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 站点救助宠物记录表(SuccourRecordHistory)表控制层
 *
 * @author makejava
 * @since 2021-04-03 19:17:37
 */
@RestController
@RequestMapping("succourRecordHistory")
public class SuccourRecordHistoryController {
    /**
     * 服务对象
     */
    @Resource
    private SuccourRecordHistoryService succourRecordHistoryServiceImpl;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SuccourRecordHistory selectOne(String id) {
        return this.succourRecordHistoryServiceImpl.queryById(id);
    }

    /**
     * 站点救助宠物接口 API008
     *
     * @param
     * @return
     */
    @RequestMapping("sitSuccour")
    @ResponseBody
    public ResponseDto sitSuccour(@RequestBody  SuccourRecordHistory succourRecordHistory, HttpServletRequest ret) {
        ResponseDto result=null;
        try{
            String xtoken= TokenUtil.requestGetXToken(ret);
            String userName=TokenUtil.tokenGetUsername(xtoken);
            succourRecordHistory.setCreateUser(userName);
            succourRecordHistory.setCreateTime(new Date());
            succourRecordHistoryServiceImpl.sitSuccour(succourRecordHistory);
            result=new ResponseDto(200,"success",null);
        }catch (Exception e){
            result=new ResponseDto(500,"error:"+e.getMessage(),null);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 站点救助宠物历史查询 API009
     *
     * @param
     * @return
     */
    @RequestMapping("queryByPage")
    @ResponseBody
    PageResult querybyPage(@RequestBody PageParam vo)throws  Exception{
        ResponseDto result=null;
        int code =200;
        String msg="success";
        try{
            List<SuccourRecordHistory> data=succourRecordHistoryServiceImpl.queryByPage(vo);
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

}
