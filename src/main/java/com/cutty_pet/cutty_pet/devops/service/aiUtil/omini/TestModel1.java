package com.cutty_pet.cutty_pet.devops.service.aiUtil.omini;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class TestModel1 {

    // 替换为你的 API Key
    private static final String API_KEY = "sk-5a839dbb64074a62a1a78e9cb6502bef";
    private static final String BASE_URL = "https://dashscope.aliyuncs.com/compatible-mode/v1";
    private static final String outputPath = "/Users/wanghao/Downloads/output_audio.wav";
    private static final String outputPathtxt = "/Users/wanghao/Downloads/output_audio.txt";
    private static final String imagePath = "/Users/wanghao/Downloads/111.jpg";
    public static void main(String[] args) throws IOException {
        // 1. 读取并编码图像文件为 Base64
        String base64Image = encodeImageToBase64(imagePath);
        //String base64Audio = encodeImageToBase64(outputPath);
        // 2. 构造请求体（JSON 格式）
        String requestBody = constructRequestBody1(base64Image);
        //String requestBody = constructRequestBody2(base64Audio);

        // 3. 创建 Apache HttpClient 实例
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
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
                            System.out.println("line else test"+line);
                        }
                    }


                    convertBase64ToWav(base64AudioDataAll.toString(), outputPath, 24000);

                    Files.write(Paths.get(outputPathtxt), base64AudioDataAll.getBytes(StandardCharsets.UTF_8));
                    System.out.println("音频文件已保存为 output_audio.wav");
                    System.out.println(textDataAll);
                } else {
                    System.err.println("Error: " + response.getStatusLine().getStatusCode());
                    System.err.println(EntityUtils.toString(response.getEntity()));
                }
            }
        }
    }

    // 将图像文件编码为 Base64 字符串
    private static String encodeImageToBase64(String imagePath) throws IOException {
        File file = new File(imagePath);
        byte[] fileContent = Files.readAllBytes(file.toPath());
        return Base64.getEncoder().encodeToString(fileContent);
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
                "                {\n" +
                "                    \"type\": \"text\",\n" +
                "                    \"text\": \"图片中描绘的是什么景象？\"\n" +     //图片中描绘的是什么景象？
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ],\n" +
                "    \"stream\": true,\n" +
                "    \"stream_options\": {\n" +
                "        \"include_usage\": true\n" +
                "    },\n" +
                "    \"modalities\": [\"text\",\"audio\"],\n" +   //, "audio"
                "    \"audio\": {\n" +
                "        \"voice\": \"Ethan\",\n" +
                "        \"format\": \"wav\"\n" +
                "    }\n" +
                "}";
        return jsonTemplate;
    }
    private static String constructRequestBody2(String base64Audio) {

        String jsonTemplate = "{\n" +
                "    \"model\": \"qwen-omni-turbo\",\n" +
                "    \"messages\": [\n" +
                "        {\n" +
                "            \"role\": \"user\",\n" +
                "            \"content\": [\n" +
                "                {\n" +
                "                    \"type\": \"text\",\n" +
                "                    \"text\": \"音频中描绘的是什么景象？\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": \"input_audio\",\n" +
                "                    \"input_audio\": {\n" +
                "                        \"data\": \"data:audio/wav;base64,"+base64Audio+"\"\n" + // 使用占位符
                "                    }\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ],\n" +
                "    \"stream\": true,\n" +
                "    \"stream_options\": {\n" +
                "        \"include_usage\": true\n" +
                "    },\n" +
                "    \"modalities\": [\"text\", \"audio\"],\n" +
                "    \"audio\": {\n" +
                "        \"voice\": \"Ethan\",\n" +
                "        \"format\": \"wav\"\n" +
                "    }\n" +
                "}";
        return jsonTemplate;
    }
    /**
     * 将 Base64 编码的音频数据转换为 WAV 文件
     *
     * @param base64AudioData Base64 编码的音频数据
     * @param outputPath      输出 WAV 文件路径
     * @param sampleRate      采样率 (Hz)
     * @throws IOException 如果文件操作失败
     */
    public static void convertBase64ToWav(String base64AudioData, String outputPath, int sampleRate) throws IOException {
        // 解码 Base64 数据为字节数组
        byte[] audioBytes = Base64.getDecoder().decode(base64AudioData);

        // 将字节数组保存为 WAV 文件
        saveAsWav(audioBytes, outputPath, sampleRate);
    }

    /**
     * 将 PCM 数据保存为 WAV 文件
     *
     * @param pcmData    PCM 音频数据
     * @param outputPath 输出 WAV 文件路径
     * @param sampleRate 采样率 (Hz)
     * @throws IOException 如果文件操作失败
     */
    private static void saveAsWav(byte[] pcmData, String outputPath, int sampleRate) throws IOException {
        // 创建 WAV 文件头
        byte[] wavHeader = createWavHeader(pcmData.length, sampleRate, 1, 16);

        // 合并 WAV 文件头和 PCM 数据
        ByteArrayOutputStream fullWavData = new ByteArrayOutputStream();
        fullWavData.write(wavHeader);
        fullWavData.write(pcmData);

        // 写入文件
        Files.write(Paths.get(outputPath), fullWavData.toByteArray());

        System.out.println("WAV 文件已保存到: " + outputPath);
    }

    /**
     * 创建 WAV 文件头
     *
     * @param totalAudioLen 总音频数据长度（字节数）
     * @param sampleRate    采样率 (Hz)
     * @param channels      声道数 (1: 单声道, 2: 双声道)
     * @param bitsPerSample 每个样本的位数 (通常为 16)
     * @return WAV 文件头的字节数组
     */
    private static byte[] createWavHeader(int totalAudioLen, int sampleRate, int channels, int bitsPerSample) {
        byte[] header = new byte[44];

        // RIFF/WAVE header
        header[0] = 'R';
        header[1] = 'I';
        header[2] = 'F';
        header[3] = 'F';
        long fileSize = totalAudioLen + 36; // 文件总大小（不包括前 8 字节）
        header[4] = (byte) (fileSize & 0xff);
        header[5] = (byte) ((fileSize >> 8) & 0xff);
        header[6] = (byte) ((fileSize >> 16) & 0xff);
        header[7] = (byte) ((fileSize >> 24) & 0xff);
        header[8] = 'W';
        header[9] = 'A';
        header[10] = 'V';
        header[11] = 'E';

        // fmt subchunk
        header[12] = 'f';
        header[13] = 'm';
        header[14] = 't';
        header[15] = ' ';
        header[16] = 16; // Subchunk1Size (16 for PCM)
        header[17] = 0;
        header[18] = 0;
        header[19] = 0;
        header[20] = 1; // AudioFormat (1 for PCM)
        header[21] = 0;
        header[22] = (byte) channels; // Number of channels
        header[23] = 0;
        long byteRate = sampleRate * channels * bitsPerSample / 8;
        header[24] = (byte) (sampleRate & 0xff);
        header[25] = (byte) ((sampleRate >> 8) & 0xff);
        header[26] = (byte) ((sampleRate >> 16) & 0xff);
        header[27] = (byte) ((sampleRate >> 24) & 0xff);
        header[28] = (byte) (byteRate & 0xff);
        header[29] = (byte) ((byteRate >> 8) & 0xff);
        header[30] = (byte) ((byteRate >> 16) & 0xff);
        header[31] = (byte) ((byteRate >> 24) & 0xff);
        header[32] = (byte) (channels * bitsPerSample / 8); // BlockAlign
        header[33] = 0;
        header[34] = (byte) bitsPerSample; // BitsPerSample
        header[35] = 0;

        // data subchunk
        header[36] = 'd';
        header[37] = 'a';
        header[38] = 't';
        header[39] = 'a';
        header[40] = (byte) (totalAudioLen & 0xff);
        header[41] = (byte) ((totalAudioLen >> 8) & 0xff);
        header[42] = (byte) ((totalAudioLen >> 16) & 0xff);
        header[43] = (byte) ((totalAudioLen >> 24) & 0xff);

        return header;
    }

}