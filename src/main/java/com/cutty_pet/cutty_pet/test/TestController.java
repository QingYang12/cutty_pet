package com.cutty_pet.cutty_pet.test;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
public class TestController {
    @RequestMapping(value = "/page")
    public ModelAndView home(){
        return new ModelAndView("test");
    }

    @RequestMapping(value = "/servletTest1")
    @ResponseBody
    public void test01(HttpServletRequest request){
        String str=readAsChars(request);
        System.out.println(str);
        JSONObject jsonstr= JSON.parseObject(str) ;
        String op_time=jsonstr.getString("op_time");
        String op_source=jsonstr.getString("op_source");
        String poi_info=jsonstr.getString("poi_info");
        String appPoiCode="";
        String poiName="";
        if(poi_info!=null){
            JSONObject poi_infoJson= JSON.parseObject(poi_info) ;
            appPoiCode=poi_infoJson.getString("appPoiCode");
            poiName=poi_infoJson.getString("poiName");
        }
        System.out.println(1);


    }
    @RequestMapping(value = "/servletTest2")
    @ResponseBody
    public void test02(@RequestBody TestRequest testRequest ){

        JSONObject s=(JSONObject)JSONObject.toJSON(testRequest);
        System.out.println( s.toJSONString());
    }
    public static String readAsChars(HttpServletRequest request)
    {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder("");
        try
        {
            br = request.getReader();
            String str;
            while ((str = br.readLine()) != null)
            {
                sb.append(str);
            }
            br.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (null != br)
            {
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
