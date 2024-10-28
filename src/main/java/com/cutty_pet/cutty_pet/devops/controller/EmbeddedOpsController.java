package com.cutty_pet.cutty_pet.devops.controller;

import com.cutty_pet.cutty_pet.devops.entity.EmbeddedEntity;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;


@RestController
public class EmbeddedOpsController {
    @Autowired
    private KafkaProperties kafkaProperties; // Kafka 属性
    @Autowired
    private ConsumerFactory<String, String> consumerFactory; // Kafka 消费者工厂

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @GetMapping("embedded/hand")
    public DeferredResult<String> hand(@RequestParam("topic") String topic, @RequestParam(value = "group", required = false) String group) throws UnsupportedEncodingException {
        // 创建异步请求对象
        DeferredResult<String> deferredResult = new DeferredResult<>(30000L);
        EmbeddedEntity devOPSEntity = new EmbeddedEntity();
        // topic 是产品的 品类+ 唯一ID
        // group 分组  控制设备集群使用，一般不用
        // 在此处编写业务逻辑...
        topic=URLDecoder.decode(topic, "UTF-8");
        topic= topic.trim();
        if(group==null){
            group=topic;
        }else{
            group=URLDecoder.decode(group, "UTF-8");
        }
        // 创建 Kafka 消费者配置
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, group);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        // 创建 Kafka 消费者实例
        Consumer<String, String> consumer = consumerFactory.createConsumer(group, "", String.valueOf(props));

        // 订阅指定的 topic
        consumer.subscribe(Collections.singleton(topic));
        // 使用异步方式处理请求
        CompletableFuture.runAsync(() -> {
            int key =2;
            // 循环拉取消息
            while (key>=0) {
                // 拉取一批消息
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                if (!records.isEmpty()) {
                    ConsumerRecord<String, String> record = records.iterator().next();
                    // 处理消息...
                    System.out.printf("Received message: key = %s, value = %s%n", record.key(), record.value());
                    // 将结果返回给客户端
                    deferredResult.setResult(record.value());
                    break;
                } else {
                    // 等待一段时间再继续拉取
                    try {
                        Thread.sleep(100);
                        key--;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (key < 0) {
                // 如果循环2次还未拉取到消息，则将结果设置为空字符串     此时已等待0.2秒钟。
                deferredResult.setResult("");
                System.out.printf("Received message:  null");
            }
            // 关闭消费者
            consumer.close();
        });

        return deferredResult;// 返回的是单片机应该作出的指令
    }
    @GetMapping("embedded/handpush")
    public String handpush(@RequestParam("topic") String topic,  @RequestParam(value = "group", required = false) String group,  @RequestParam(value = "message", required = false) String message) throws UnsupportedEncodingException {
        String code ="200";
        // topic 是产品的 品类+ 唯一ID
        // group 分组  控制设备集群使用，一般不用
        // 在此处编写业务逻辑...
        topic=URLDecoder.decode(topic, "UTF-8");
        topic= topic.trim();
        if(group==null){
            group=topic;
        }
        try{
            if(message!=null&&!message.equals("")){

                message=URLDecoder.decode(message, "UTF-8");
                message= message.trim();
                System.out.println("message send: "+message);
                // 发送消息到 Kafka

                kafkaTemplate.send(topic, message);
            }else{
                System.out.println("无效消息");
            }
        }catch (Exception e){
            e.printStackTrace();
            code="500";
        }
        return code;
    }
}
