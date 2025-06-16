package com.cutty_pet.cutty_pet.devops.controller;

import com.alibaba.fastjson.JSONObject;
import com.cutty_pet.cutty_pet.common.cache.GuavaCacheManager;
import com.cutty_pet.cutty_pet.customer.service.CustomerService;
import com.cutty_pet.cutty_pet.devops.entity.DEVOPSEntity;
import com.cutty_pet.cutty_pet.devops.entity.EmbeddedEntity;
import com.cutty_pet.cutty_pet.devops.service.EmbeddedService;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.CompletableFuture;


@RestController
public class EmbeddedOpsController {
    @Autowired
    private KafkaProperties kafkaProperties; // Kafka 属性
    @Autowired
    private ConsumerFactory<String, String> consumerFactory; // Kafka 消费者工厂

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Resource
    private EmbeddedService embeddedServiceImpl;

    @GetMapping("embedded/hand")
    public DeferredResult<String> hand(@RequestParam("topic") String topic, @RequestParam(value = "group", required = false) String group, @RequestParam(value = "type", required = false) String type) throws UnsupportedEncodingException {
        // 创建异步请求对象
        DeferredResult<String> deferredResult = new DeferredResult<>(30L); //kafka 换成 30000L
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

        if(type!=null&&"c".equals(type)){ // c  k
            // 获取缓存
            LinkedList<String> cachedList = GuavaCacheManager.getInstance().get("hand", LinkedList.class);
            if(cachedList!=null){
                String json=cachedList.poll();
                JSONObject jsonObject= (JSONObject) JSONObject.parse(json);
                if(jsonObject!=null){
                    String message=jsonObject.getString("message");
                    deferredResult.setResult(message);
                }else{
                    deferredResult.setResult("9");
                }

            }else{
                deferredResult.setResult("9");
                System.out.printf(">> ");
            }
        }else{
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
                        System.out.println();
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
                    deferredResult.setResult("9");
                    System.out.printf(">> ");
                }
                // 关闭消费者
                consumer.close();
            });
        }


        return deferredResult;// 返回的是单片机应该作出的指令
    }
    @GetMapping("embedded/handpush")
    public String handpush(@RequestParam("topic") String topic,  @RequestParam(value = "group", required = false) String group,  @RequestParam(value = "message", required = false) String message,  @RequestParam(value = "type", required = false) String type) throws UnsupportedEncodingException {
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
                if(type!=null&&type.equals("c")){  //  c      k
                    LinkedList<String> cachedList = GuavaCacheManager.getInstance().get("hand", LinkedList.class);
                    JSONObject json=new JSONObject();
                    json.put("topic",topic);
                    json.put("group",group);
                    json.put("message",message);
                    json.put("type",type);
                    if(cachedList!=null){
                        cachedList.add(json.toString());
                    }else{
                        LinkedList<String> cachedListNew =new LinkedList<String>();
                        cachedListNew.add(json.toString());
                        GuavaCacheManager.getInstance().put("hand",cachedListNew);
                    }

                }else{
                    kafkaTemplate.send(topic, message);
                }

            }else{
                System.out.println("无效消息");
            }
        }catch (Exception e){
            e.printStackTrace();
            code="500";
        }
        return code;
    }
    @RequestMapping(value = "/voiceUpload", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject voiceUpload(HttpServletRequest request, @RequestParam("file") MultipartFile[] files) {
        String uploadDirRoot = "/Users/wanghao/Documents/pyvicetemp/";
        long currentTimeMillis = System.currentTimeMillis();
        //String taskdir=""+currentTimeMillis;
        String taskdir=request.getParameter("taskdir");
        String uploadDir=uploadDirRoot+taskdir;
        JSONObject resjson= new JSONObject();
        resjson.put("taskdir",taskdir);
        try {
            for (MultipartFile file : files) {
                // 获取文件信息，例如文件名和大小等元数据
                String originalFilename = request.getParameter("fileName");
                String fileName = file.getOriginalFilename();
                long fileSize = file.getSize();
                String contentType = file.getContentType();
                // 执行文件上传操作，将文件保存到指定文件夹下
                File uploadDirf = new File(uploadDir);
                if(!uploadDirf.exists()){
                    boolean success = uploadDirf.mkdirs();
                }else{

                }
                fileName="input.wav";
                Path filePath = Paths.get(uploadDir, fileName);
                Files.copy(file.getInputStream(), filePath);
                Thread.sleep(2000);
                //执行python 脚本 OCR
                //执行 执行后返回 解析json
                resjson= embeddedServiceImpl.voiceUpload(taskdir,uploadDir,fileName);
                resjson.put("taskdir",taskdir);
                // 执行文件上传操作，例如将文件保存到磁盘或数据库中
                // 这里只是简单地打印出文件信息
                System.out.println("Received file: " + fileName + ", size=" + fileSize + ", type=" + contentType);
            }

            return resjson;
        } catch (Exception ex) {
            ex.printStackTrace();
            return resjson;
        }
    }

    /**
     *
     * @param param  taskdir
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    // 下载到本地
    @RequestMapping("dowloadVoice")
    @ResponseBody
    public ResponseEntity<byte[]> dowloadVoice(@RequestBody JSONObject param) throws IOException, InterruptedException {
        String uploadDirRoot = "/Users/wanghao/Documents/pyimgtemp";
        DEVOPSEntity devOPSEntity = new DEVOPSEntity();
        devOPSEntity.setId("1L");
        devOPSEntity.setCreateUser("ssss");
        String taskdir=param.getString("taskdir");
        String fileNameStr="output_audio.wav";
        //下载回传图片
        // 读取图片文件
        String imagePath = uploadDirRoot + "/"+taskdir+"/"+fileNameStr;
        File imageFile = new File(imagePath);
        InputStream inputStream = new FileInputStream(imageFile);

        // 读取数据到字节数组
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, length);
        }
        byte[] imageData = outputStream.toByteArray();

        // 构造响应对象，将图片数据写入响应体
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .contentLength(imageData.length)
                .body(imageData);
    }
    // 上传图片
    @RequestMapping("/embedded/uploadImage")
    public ResponseEntity<String> uploadImage(@RequestBody byte[] imageData){
        try{
            String uploadDirRoot = "/Users/wanghao/Documents/embeddedImage";
            String filename=uploadDirRoot+"/"+"received_"+new Date().getTime()+ ".jpg";
            try(FileOutputStream fos =new FileOutputStream(filename)){
                fos.write(imageData);
            }
            return ResponseEntity.ok("received image size: "+ imageData.length + " bytes saved as "+filename);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("Upload failed");
        }
    }
}
