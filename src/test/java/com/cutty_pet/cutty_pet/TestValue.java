package com.cutty_pet.cutty_pet;

import com.alibaba.fastjson.JSONObject;
import com.cutty_pet.cutty_pet.common.thread.TResponse;
import com.cutty_pet.cutty_pet.util.TTT2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)//MockitoJUnitRunner
@SpringBootTest(classes = CuttyPetApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestValue {

    @Test
    public  void testBoolean(){
        TTT2 t2=new TTT2();//@Value("${some.key:true}")
        System.out.println(JSONObject.toJSON(t2));

    }

    @Test
    public  void test2(){
        String str="12121";
        Boolean listsrc=true;//为空
        Boolean key= Boolean.valueOf("false");
        if(listsrc&&Boolean.valueOf("false")){
            System.out.println(1);
        }

        System.out.println(2);
    }


    @Test
    public  void test3(){
        List arr=new ArrayList<>();
        for(int i=0;i<=10;i++){
            TResponse t=new TResponse();
            arr.add(t);
        }
    }
}
