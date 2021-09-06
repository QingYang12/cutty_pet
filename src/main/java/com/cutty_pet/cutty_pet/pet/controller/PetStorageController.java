package com.cutty_pet.cutty_pet.pet.controller;

import com.cutty_pet.cutty_pet.common.PageParam;
import com.cutty_pet.cutty_pet.common.PageResult;
import com.cutty_pet.cutty_pet.common.ResponseDto;
import com.cutty_pet.cutty_pet.dic.entity.Dictionary;
import com.cutty_pet.cutty_pet.pet.dto.PetStorageDto;
import com.cutty_pet.cutty_pet.pet.entity.PetStorage;
import com.cutty_pet.cutty_pet.pet.service.PetStorageService;
import com.cutty_pet.cutty_pet.pet.vo.PetStorageVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 宠物库存表
 * 库中现有宠物(PetStorage)表控制层
 *
 * @author makejava
 * @since 2021-04-03 18:35:57
 */
@RestController
@RequestMapping("petStorage")
public class PetStorageController {
    /**
     * 服务对象
     */
    @Resource
    private PetStorageService petStorageServiceImpl;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public PetStorage selectOne(String id) {
        return this.petStorageServiceImpl.queryById(id);
    }



    /**
     * 主页搜索宠物接口 API001
     *
     * @param
     * @return
     */
    @RequestMapping("searchPet")
    @ResponseBody
    public PageResult searchPet(@RequestBody PageParam vo) throws  Exception{
        ResponseDto result=null;
        int code =200;
        String msg="success";
        try{
            List<PetStorageDto> data=petStorageServiceImpl.searchPet(vo);
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
    @RequestMapping("queryAll")
    @ResponseBody
    public PageResult queryAll(@RequestBody PageParam vo) throws  Exception{
        ResponseDto result=null;
        int code =200;
        String msg="success";
        try{
            List<PetStorageDto> data=petStorageServiceImpl.queryAll(vo);
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
