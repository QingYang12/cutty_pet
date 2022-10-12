package com.cutty_pet.cutty_pet.util;

import com.alibaba.fastjson.JSONObject;
import com.cutty_pet.cutty_pet.CuttyPetApplication;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.runner.RunWith;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CuttyPetApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Component
public class TestKafkaConsumer {
    @KafkaListener(topics = {"test_msg_topic"},groupId = "test_msg_group")
    public void listener(ConsumerRecord<String,String> record){
        //获取消息
        String message = record.value();
        //消息偏移量
        long offset = record.offset();
        System.out.println("读取的消息："+message+"\n当前偏移量："+offset);
    }
}
