package com.cutty_pet.cutty_pet.embedded.controller;

import com.cutty_pet.cutty_pet.common.ResponseDto;
import com.cutty_pet.cutty_pet.embedded.entity.Embedded;
import com.cutty_pet.cutty_pet.embedded.service.EmbeddedService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 *
 *
 * @author makejava
 * @since 2021-04-03 19:17:23
 */
@RestController
@RequestMapping("/embedded")
public class EmbeddedController {
    /**
     * 服务对象
     */
    @Resource
    private EmbeddedService embeddedServiceImpl;



    @RequestMapping("/hand")
    @ResponseBody
    public ResponseDto handreceive( HttpServletRequest ret) {
        ResponseDto result=null;
        try{
            Map<String, String[]> map= ret.getParameterMap();
            Embedded embeddedvo=new Embedded();
            embeddedvo.setParameterMap(map);
            embeddedServiceImpl.handreceive(embeddedvo);
            result=new ResponseDto(200,"success",null);
        }catch (Exception e){
            result=new ResponseDto(500,"error:"+e.getMessage(),null);
            e.printStackTrace();
        }
        return result;
    }

}
