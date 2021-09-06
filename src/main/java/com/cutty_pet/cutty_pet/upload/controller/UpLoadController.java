package com.cutty_pet.cutty_pet.upload.controller;

import com.alibaba.fastjson.JSONObject;
import com.cutty_pet.cutty_pet.common.PageParam;
import com.cutty_pet.cutty_pet.common.PageResult;
import com.cutty_pet.cutty_pet.common.ResponseDto;
import com.cutty_pet.cutty_pet.upload.entity.FileDB;
import com.cutty_pet.cutty_pet.upload.service.UpLoadService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * 系统字典表(FileDB)表控制层
 *
 * @author makejava
 * @since 2021-04-03 18:35:31
 */
@RestController
@RequestMapping("upLoadFile")
public class UpLoadController {
    /**
     * 服务对象
     */
    @Resource
    private UpLoadService upLoadServiceImpl;

    /**文件查询     API0019
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public FileDB selectOne(String id) {
        return this.upLoadServiceImpl.queryById(id);
    }

    /**文件上传     API0018
     * 新增数据
     *
     * @param
     * @return 实例对象
     */
    @RequestMapping("upLoad")
    @ResponseBody
    ResponseDto upLoad(@RequestBody MultipartFile file){
        ResponseDto result=null;
        try{
            FileDB fileDB=upLoadServiceImpl.upLoad(file);
            result=new ResponseDto(200,"success",null);
            result.setData(fileDB);
        }catch (Exception e){
            result=new ResponseDto(500,"error:"+e.getMessage(),null);
            e.printStackTrace();
        }
        return result;
    }
    /**文件下载     API0021
     * 新增数据
     *
     * @param
     * @return 实例对象
     */
    @RequestMapping("downLoad")
    @ResponseBody
    ResponseDto downLoad(HttpServletRequest ret){//,@RequestBody JSONObject jsonObject
        ResponseDto result=null;
        try{
            //String id=jsonObject.getString("id");

            String id=ret.getParameter("id");
            FileDB fileDB=upLoadServiceImpl.downLoad(id);
//rep.setContentType("image/*");
//            ByteArrayInputStream bArray = new ByteArrayInputStream(fileDB.getFilebyte());
//            ServletOutputStream sos = rep.getOutputStream();
//            int len;
//            byte[] arr = new byte[1024];
//            while ((len = bArray.read(arr)) != -1) {
//                sos.write(arr, 0, len);
//                sos.flush();
//            }
//            sos.close();
//            bArray.close();
            fileDB.setFileSrc("data:image/jpg;base64,"+fileDB.getFileSrc());
            result=new ResponseDto(200,"success",null);
            result.setData(fileDB);
        }catch (Exception e){
            result=new ResponseDto(500,"error:"+e.getMessage(),null);
            e.printStackTrace();
        }
        return result;
    }
    /**删除文件          API0020
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
            upLoadServiceImpl.deleteById(id);
            result=new ResponseDto(200,"success",null);
        }catch (Exception e){
            result=new ResponseDto(500,"error:"+e.getMessage(),null);
            e.printStackTrace();
        }
        return result;
    }
}
