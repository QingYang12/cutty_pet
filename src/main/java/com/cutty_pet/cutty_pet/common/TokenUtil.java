package com.cutty_pet.cutty_pet.common;

import jdk.nashorn.internal.ir.RuntimeNode;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class TokenUtil {
     public  static  String  getToken(String userName) throws  Exception{
        /*Date date=new Date();
        Random random=new Random();
        int a=random.nextInt()*100000;
        String token=userName+date.getTime()+a;*/
        String token=Base64PWDUtil.encryptBASE64(userName.getBytes());
        return token;
    }
    public  static  String  tokenGetUsername(String token) throws  Exception{
        byte[] userName=Base64PWDUtil.decryptBASE64(token);
        return new String(userName);
    }


    public  static String requestGetXToken(HttpServletRequest ret){
        List<String> list=new ArrayList<>();
        Enumeration e = ret.getHeaderNames();
        while (e.hasMoreElements()) {
            String headerName = (String) e.nextElement();
            if("X-Token".equals(headerName)||"x-token".equals(headerName)){
                Enumeration<String> headerValues = ret.getHeaders(headerName);
                while (headerValues.hasMoreElements()) {
                    list.add(headerValues.nextElement());
                }
            }

        }

        if(list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }
}
