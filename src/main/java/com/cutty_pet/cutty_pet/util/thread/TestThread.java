package com.cutty_pet.cutty_pet.util.thread;

import com.alibaba.fastjson.JSONObject;
import com.cutty_pet.cutty_pet.common.thread.TResponse;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class TestThread {
    /**
     * 线程池
     */
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;
    @Test
    public void test(){


        //System.out.println(1);
        //System.out.println(1);
        //taskExecutor.execute(()->{});
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程数
        executor.setCorePoolSize(3);
        // 最大线程数
        executor.setMaxPoolSize(6);
        // 任务队列大小
        executor.setQueueCapacity(6);
        // 线程前缀名
        executor.setThreadNamePrefix("threadPrefix");
        // 线程的空闲时间
        executor.setKeepAliveSeconds(200);
        // 拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 线程初始化
        executor.initialize();
        List<String> rpcparamList=new ArrayList<>();
        for(int i=0;i<=10;i++){
            rpcparamList.add(i+"");
        }
        List<TResponse> resList=new ArrayList<>();
        // 需要rpc调用的按钮
        List<Future<TResponse>> buttonFutures = new ArrayList<>();
        if (!CollectionUtils.isEmpty(rpcparamList)) {
            for (String param : rpcparamList) {
                Future<TResponse> buttonFuture = executor.submit(() ->
                        send(param));
                buttonFutures.add(buttonFuture);
            }
        }
        if (!CollectionUtils.isEmpty(buttonFutures)) {
            for (Future<TResponse> buttonFuture : buttonFutures) {
                try {
                    //TResponse buttonDTO = buttonFuture.get(200L, TimeUnit.MILLISECONDS);
                    TResponse buttonDTO = buttonFuture.get();
                    resList.add(buttonDTO);
                } catch (Exception e) {
                    System.out.println("获取进入问诊按钮信息异常, buttonFuture={}, exception"+ JSONObject.toJSONString(buttonFuture)+e);
                }
            }
        }
    }
    //模拟调RPC
    TResponse send(String param){
        TResponse res=new TResponse();
        try{
            Thread.sleep(5000);

        }catch (Exception e){
            e.printStackTrace();
        }
        if(param.equals("0")){
            throw new RuntimeException();
        }
        res.setMsg(param);

        return  res;
    }

}
