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
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CuttyPetApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestKafkaProduct {
    @Autowired
    private KafkaTemplate kafkaTemplate;


    @org.junit.Test
    public  void testsend(){
        //消息发送s
        String topic="test_msg_topic";
        String msg=" msg  msg msg msg msg msg msg msg ";
        kafkaTemplate.send(topic,msg);
    }
}
