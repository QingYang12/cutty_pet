package com.cutty_pet.cutty_pet.common;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OrderCreateNumber {


    public  static String createNumber(){
        Date date=new Date();
        long time=date.getTime();
        Random ran =new Random();
        /**
         * 生成 [m,n] 的数字
         * int i1 = random.nextInt() * (n-m+1)+m;
         * */
        long randomNum=ran.nextInt()* (1-10000+1)+10000;
        String num=String.valueOf(time).toString()+String.valueOf(randomNum).toString();

        return num;
    }



    @Test
    public   void test(){
        String str=createNumber();
        System.out.println(str);
    }
}
