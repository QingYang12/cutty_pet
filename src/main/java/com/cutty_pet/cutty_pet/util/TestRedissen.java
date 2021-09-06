package com.cutty_pet.cutty_pet.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cutty_pet.cutty_pet.CuttyPetApplication;
import com.cutty_pet.cutty_pet.customer.dao.CustomerDao;
import com.cutty_pet.cutty_pet.customer.entity.Customer;
import com.github.pagehelper.PageHelper;
import org.junit.runner.RunWith;
import org.redisson.api.*;
import org.redisson.client.codec.StringCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CuttyPetApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestRedissen {
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    CustomerDao customerDao;

    @org.junit.Test
    public  void testResdissionsetHash(){
        RBucket<TTT> bucket = redissonClient.getBucket("w4");
        TTT t=new TTT();
        t.id="1";
        t.name="1";
        t.kkk="1";
        bucket.set(t);

    }


    @org.junit.Test
    public  void testResdissiongetOne(){
        RBucket<TTT> bucket =redissonClient.getBucket("w4");
        TTT value =  bucket.get();
        if(value!=null){
            System.out.println(JSONObject.toJSON(value).toString());
        }

    }
    @org.junit.Test
    public  void testResdission(){


        //future列表
        LinkedList<RFuture<Object>> futures = new LinkedList<>();
        //结果集
        LinkedList<Object> result = new LinkedList<>();
        RBatch batch = redissonClient.createBatch();
        for (int i =0;i<7;i++) {
            RBucketAsync<Object> bucket = batch.getBucket("w"+i,StringCodec.INSTANCE);
            RFuture<Object> async = bucket.getAsync();
            futures.add(async);
        }
        //批量执行
        BatchResult<?> execute = batch.execute();
        while (futures.size()>0){
            RFuture<Object> first = futures.removeFirst();
            //获取当前值，未完成时值为null，使用isDone方法区分value不存在还是任务未完成
            Object o = first.getNow();
            if (o!=null){
                result.add(o);
            }else {
                if (!first.isDone()){
                    //还没完成
                    futures.addLast(first);
                }else{
                    //已完成
                    result.add(null);
                }
            }
        }
        for(Object item:result){
            if(item==null){
                System.out.println("NULL");
            }else{
                System.out.println(JSONObject.toJSON(item).toString());
            }

        }
    }
    @org.junit.Test
   public void testTostr(){
        String str="{\"createPin\":\"aaa\",\"createTime\":1553855256000,\"employeeNo\":\"0001\",\"id\":133,\"orgCode\":\"111209\",\"parentPin\":\"admin209\",\"phone\":\"13621177370\",\"specialFlag\":1,\"sysVersion\":1,\"updatePin\":\"aaa\",\"updateTime\":1553855256000,\"userName\":\"小陈\",\"userPassword\":\"198BA88D05916B7194E4C2353D3CDBA5\",\"userPin\":\"ccccc\",\"yn\":false}";

        UserInfo u=JSON.parseObject(String.valueOf(str), UserInfo.class);
        System.out.println(u);
    }
//////////////////////////////---------------wh-------------/////////////////////////////////////

    @org.junit.Test//插入测试数据
    public void testINto(){
        for(int i=0;i<=1000;i++){
            Customer customer=new Customer();
            customer.setId(""+i);
            customer.setOld(""+i);
            customer.setPassword(""+i);
            customer.setTelephone(""+i);
            customer.setRole(""+i);
            customer.setUsername(""+i);
            customer.setCreateTime(new Date());
            customer.setUpdateTime(new Date());
            customerDao.insert(customer);
        }
    }
    @org.junit.Test//修改测试数据
    public void updataT1(){
            int i=1;
            Customer customer=new Customer();
            customer.setId(""+i);
            customer.setOld(""+5);
            customer.setPassword(""+i);
            customer.setTelephone(""+i);
            customer.setRole(""+i);
            customer.setUsername(""+i);
            customer.setCreateTime(new Date());
            customer.setUpdateTime(new Date());
            redissonUtils_setUserInfo(customer);
    }
    @org.junit.Test//查询测试数据
    public void selectT1(){
        RBucket<Object> bucket =redissonClient.getBucket("user_"+1);
        Object value =  bucket.get();
        if(value!=null){
            System.out.println(JSONObject.toJSON(value).toString());
        }
    }
//////////////////批量刷新缓存
    @org.junit.Test
    public void testrefreshUserCache(){
        List orgarr=new ArrayList<>();
        orgarr.add("1");
        //orgarr
        String token ="123123123";
        refreshUserCache(orgarr,token);
    }


////////////////////////

public void refreshUserCache(List<String> orgCodeList, String token) {
    try {
        // 校验token有效
        String tokenkeys="123123123";
        if(!tokenkeys.equals(token)){
            System.out.println("token 校验失敗,token="+ token);
            throw new Exception();
        }
        if (CollectionUtils.isEmpty(orgCodeList)) {
            //   分页查所有的用户，200
            batchAllUserInfoSelectIntoRedis();
        } else {
            // 查指定orgCodeList 的
            batchOrgeCodeUserInfoSelectIntoRedis(orgCodeList);

        }

    }catch (Throwable e) {
        System.out.println("批量刷用户信息redis 失敗,e="+e);
    }
    //return null;

}

    public void batchAllUserInfoSelectIntoRedis(){
        //分页批量查询
        long alltotal=0;
        int pageStart = 1;
        int pageCount = 200;
        System.out.println("全量刷用户的信息写入缓存开始");
        while (true){
            Customer page = new Customer();
            //page.set(pageStart);
            //page.setQryCount(pageCount);
            PageHelper.startPage(pageStart, pageCount);
            List<Customer> resUserInfoList=customerDao.queryAll(page);

            for(Customer item :resUserInfoList){
                // 查到的用户的信息写入缓存
                redissonUtils_setUserInfo(item);
            }
            pageStart++;
            alltotal+=200;

            System.out.println("redis更新用户范围已至："+alltotal);
            if(CollectionUtils.isEmpty(resUserInfoList)){
                break;
            }
        }
        System.out.println("全量刷用户的信息写入缓存结束");
        System.out.println("count="+alltotal);
    }
    public void batchOrgeCodeUserInfoSelectIntoRedis(List<String> orgCodeList){
        for(String orgcode:orgCodeList){
            Customer page = new Customer();
            page.setId(orgcode);
            List<Customer> resUserInfoList=customerDao.queryAll(page);
            for(Customer item :resUserInfoList){
                // 查到的用户的信息写入缓存
                System.out.println("orgCode查到的用户的信息写入缓存"+orgcode+" "+JSONObject.toJSON(item).toString());
                redissonUtils_setUserInfo(item);
            }
        }

    }

    public void redissonUtils_setUserInfo(Object entity){
        String prefix="user_";
        JSONObject josn=(JSONObject)JSONObject.toJSON(entity);
        String key=josn.getString("id");
        String value = JSON.toJSONString(entity);
        RBucket<String> bucket = redissonClient.getBucket(prefix + key);
        bucket.set(value);
    }


}
