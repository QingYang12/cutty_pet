package com.cutty_pet.cutty_pet.devops.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Comparator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OminiHandVsionModelShell {
    // 单例实例（私有静态常量）
    private static final OminiHandVsionModelShell INSTANCE = new OminiHandVsionModelShell();

    private static final String imagePath = "/Users/wanghao/Documents/embeddedImage/";
    private static final String API_KEY = "sk-5a839dbb64074a62a1a78e9cb6502bef";
    private static final String BASE_URL = "https://dashscope.aliyuncs.com/compatible-mode/v1";


    // 调度器和任务引用
    private ScheduledExecutorService scheduler;
    private ScheduledFuture<?> future;

    // 私有构造器，防止外部创建新实例
    private OminiHandVsionModelShell() {
        System.out.println("model shell");
        scheduler = Executors.newSingleThreadScheduledExecutor();
        System.out.println("model shell");
    }

    // 公共静态方法，提供全局访问点
    public static OminiHandVsionModelShell getInstance() {
        return INSTANCE;
    }

    // 实例方法替代原来的静态方法
    public void startModel() {
        System.out.println("模型 启动命令");
        if (future != null && !future.isDone()) {
            System.out.println("模型已经在运行中...");
            return;
        }

        System.out.println("模型已启动");
        try{
            future = scheduler.scheduleAtFixedRate(this::dispatcherModel, 0, 5, TimeUnit.SECONDS);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void stopModel() {
        System.out.println("模型 关闭命令");
        if (future != null) {
            future.cancel(false); // 不中断当前正在执行的任务
        }

        scheduler.shutdown(); // 关闭调度器
    }

    /**
     * 调用模型      此处 调用 Omini模型
     */
    public void dispatcherModel()  {
        try {

            // 获取目录下所有文件
            File dir = new File(imagePath);
            if (!dir.exists() || !dir.isDirectory()) {
                //return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 目录不存在或不是一个目录
                return;
            }
            // 找到最新的文件
            File latestFile = Files.list(dir.toPath())
                    .filter(Files::isRegularFile)
                    .max(Comparator.comparingLong(file -> {
                        try {
                            return Files.getLastModifiedTime(file).toMillis();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }))
                    .map(java.nio.file.Path::toFile)
                    .orElse(null);

            if (latestFile == null) {
                //return ResponseEntity.notFound().build(); // 没有找到任何文件
                return;
            }
            String fileName = latestFile.getName();
            String imageFilePath=imagePath+fileName;
            //测试代码
            imageFilePath=imagePath+"received_1750340543726.jpg";
            //imageFilePath="/Users/wanghao/Downloads/111.jpg";
            // 1. 读取并编码图像文件为 Base64
            String base64Image = encodeImageToBase64(imageFilePath);
            // 2. 构造请求体（JSON 格式）
            String requestBody = constructRequestBody1(base64Image);
            //System.out.println("返回 ：" +requestBody);
            //String requestBody = constructRequestBody2(base64Audio);
            // 3. 创建 Apache HttpClient 实例
            CloseableHttpClient httpClient = HttpClients.createDefault();
            // 4. 构造 HTTP POST 请求
            HttpPost post = new HttpPost(BASE_URL + "/chat/completions");
            post.setHeader("Content-Type", "application/json");
            post.setHeader("Authorization", "Bearer " + API_KEY);
            post.setEntity(new StringEntity(requestBody));

            // 5. 发送请求并处理响应
            try (CloseableHttpResponse response = httpClient.execute(post)) {
                if (response.getStatusLine().getStatusCode() == 200) {
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8)
                    );

                    ByteArrayOutputStream audioData = new ByteArrayOutputStream();
                    ObjectMapper objectMapper = new ObjectMapper();
                    String base64AudioDataAll="";
                    String textDataAll="";
                    String line;
                    int kindex=0;
                    while ((line = reader.readLine()) != null) {
                        if (line.startsWith("data: ")) {
                            String jsonData = line.substring(6); // 去掉 "data: " 前缀

                            if (jsonData.equals("[DONE]")) {
                                System.out.println("Stream ended.");
                                break;
                            }

                            // 解析 JSON 数据
                            JsonNode jsonNode = objectMapper.readTree(jsonData);
                            if(jsonNode.has("choices")&& jsonNode.get("choices").isArray()){
                                JsonNode choicesNode = jsonNode.get("choices");
                                if (!choicesNode.isEmpty()) {
                                    JsonNode audioNode = jsonNode.path("choices").get(0).path("delta").path("audio");

                                    if (audioNode.has("data")) {
                                        String base64AudioData = audioNode.get("data").asText();
                                        String base64AudioDataTrim=base64AudioData;//.replaceAll("[^A-Za-z0-9+/=]", "");;
                                        base64AudioDataAll+=base64AudioDataTrim;
                                    }
                                    if (audioNode.has("transcript")) {
                                        String textstr = audioNode.get("transcript").asText();
                                        textDataAll+=textstr;
                                    }
                                }
                            }

                        }else{
                            //debug 打开这里
                            //System.out.println("line else test"+line);
                        }
                    }
                    System.out.println("---------------------");
                    System.out.println(textDataAll);
                    System.out.println("---------------------");

                    // 正则表达式匹配 JSON 对象（以 { 开头，} 结尾）
                    Pattern pattern = Pattern.compile("\\{(?:[^{}]|(?![^{]*}))*\\}");
                    Matcher matcher = pattern.matcher(textDataAll);

                    if (matcher.find()) {
                        String jsonString = matcher.group(0);
                        System.out.println("提取到的 JSON 字符串为：");
                        System.out.println(jsonString);

                        // 使用 Jackson 解析 JSON
                        ObjectMapper mapper = new ObjectMapper();
                        JsonNode jsonNode = mapper.readTree(jsonString);
                        int code = jsonNode.get("code").asInt();
                        System.out.println("提取出的 code 字段值为：" + code);
                    } else {
                        System.out.println("未找到有效的 JSON 数据！");
                    }
                } else {
                    System.err.println("Error: " + response.getStatusLine().getStatusCode());
                    System.err.println(EntityUtils.toString(response.getEntity()));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // 构造 JSON 请求体（适用于 Java 8）
    private static String constructRequestBody1(String base64Image) {

        String jsonTemplate = "{\n" +
                "    \"model\": \"qwen-omni-turbo\",\n" +
                "    \"messages\": [\n" +
                "        {\n" +
                "            \"role\": \"user\",\n" +
                "            \"content\": [\n" +
                "                {\n" +
                "                    \"type\": \"image_url\",\n" +
                "                    \"image_url\": {\n" +
                "                        \"url\": \"data:image/png;base64,"+base64Image+"\"\n" + // 使用占位符
                "                    }\n" +
                "                },\n" +
                "{\n" +
                "    \"type\": \"text\",\n" +
                "    \"text\": \"你是一个视觉识别助手和机器人控制器。请根据图像内容完成以下任务：\\n\\n" +

                "任务描述:\\n" +
                "1. 识别图像中黑色托盘中的白色方块。\\n" +
                "2. 找出黄色托盘的位置。\\n" +
                "3. 如果存在蓝色托盘，请一并识别其位置（若不存在可忽略）。\\n" +
                "4. 规划机械臂将白色方块从黑色托盘移动到黄色托盘的路径。\\n\\n" +

                "动作控制说明:\\n" +
                "机械臂由4个舵机控制，分别如下：\\n" +
                "- 舵机1：夹子夹取(1) / 松开(2)\\n" +
                "- 舵机2：上臂左移(3) / 右移(4)\\n" +
                "- 舵机3：下臂左移(5) / 右移(6)\\n" +
                "- 舵机4：底座左转(7) / 右转(8)\\n\\n" +

                "输出格式:\\n" +
                "请以JSON格式返回以下字段：\\n" +
                "- white: 白色方块中心坐标 (x, y, z)\\n" +
                "- yellow: 黄色托盘中心坐标 (x, y, z)\\n" +
                "- blue: 蓝色托盘中心坐标 (x, y, z)，如果没有则省略\\n" +
                "- clip: 当前夹子位置 (x, y, z)\\n" +
                "- code: 控制指令编码（每次只返回一个命令，值为1~8）\\n\\n" +

                "输出规则:\\n" +
                "- 每次调用只需返回一个动作命令。\\n" +
                "- 若未检测到目标对象（如白方块、黄/蓝托盘），请返回空对象 {}。\\n" +
                "- 坐标系基于图像像素坐标系，单位为像素。\\n" +
                "- 不要添加任何额外解释，只返回JSON结果。\\n\\n" +

                "示例输出:\\n" +
                "{\\\"white\\\": [20, 30, 10], \\\"yellow\\\": [50, 30, 10], \\\"clip\\\": [20, 30, 20], \\\"code\\\": 1}\\n" +

                "\"" +
                "}\n"+
                "            ]\n" +
                "        }\n" +
                "    ],\n" +
                "    \"stream\": true,\n" +
                "    \"stream_options\": {\n" +
                "        \"include_usage\": true\n" +
                "    },\n" +
                "    \"modalities\": [\"text\"],\n" +   //, "audio"
                "    \"audio\": {\n" +
                "        \"voice\": \"Ethan\",\n" +
                "        \"format\": \"wav\"\n" +
                "    }\n" +
                "}";
        return jsonTemplate;
    }
    // 将图像文件编码为 Base64 字符串
    private static String encodeImageToBase64(String imagePath) throws IOException {
        File file = new File(imagePath);
        byte[] fileContent = Files.readAllBytes(file.toPath());
        return Base64.getEncoder().encodeToString(fileContent);
    }

    void send(String code){
        OkHttpClient client = new OkHttpClient();

        // 构建请求
        Request request = new Request.Builder()
                .url("https://test.wanghaonet.com/yushandevops/embedded/handpush?topic=testtopic&group=testtopicg&message="+code+"&type=c")
                .header("Accept", "application/json")
                .build();

        // 发起请求
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                System.out.println("状态码: " + response.code());
                System.out.println("响应内容: " + response.body().string());
            } else {
                System.out.println("请求失败: " + response.message());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // 可选：main 方法测试单例行为
    public static void main(String[] args) {
        /** 使用单例调用方法
        OminiHandVsionModelShell model = OminiHandVsionModelShell.getInstance();
        model.startModel();
        model.stopModel();*/
        OminiHandVsionModelShell.getInstance().dispatcherModel();
    }
}
