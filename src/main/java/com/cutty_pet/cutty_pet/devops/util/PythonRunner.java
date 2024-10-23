package com.cutty_pet.cutty_pet.devops.util;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class PythonRunner {
    public static String scriptPathroot ="/Users/wanghao/Documents/2.python_project/testmodel1/";
    //OCR识别 返回识别到的文字
    public static JSONObject OCRRunFun (String taskdir) throws IOException, InterruptedException {
        JSONObject  resjson=new JSONObject();
        // 设置要执行的Python脚本
        String scriptName = "main.py";
        String scriptPath =scriptPathroot+scriptName;
        List<String> commandList = new ArrayList<>();
        commandList.add("/Users/wanghao/.virtualenvs/p3t2env/bin/python");
        commandList.add(scriptPath);
        commandList.add("-i");
        commandList.add(taskdir);

        String command = String.join(" ", commandList);
        System.out.println(command);
        // 创建 ProcessBuilder 对象并设置工作目录
        String workerName="";
        ProcessBuilder builder = new ProcessBuilder(commandList);
        File workingDir = new File(scriptPathroot+workerName);
        builder.directory(workingDir);

        // 启动进程并等待完成
        Process process = builder.start();
        int exitCode = process.waitFor();



        // 输出进程退出码
        System.out.println("Exit code: " + exitCode);

        // 读取输出结果
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        System.out.println("success output: " + stringBuilder.toString());
        //如果报错，则捕获报错 的输出
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream(), StandardCharsets.UTF_8));
        String errorLine;
        StringBuilder errorBuilder = new StringBuilder();
        while ((errorLine = errorReader.readLine()) != null) {
            errorBuilder.append(errorLine).append("\n");
        }
        System.out.println("Error output: " + errorBuilder.toString());

        // 将输出结果解析为 JSON 对象
        /**
         * json输出格式为
         *
             *  {"data": [{"name":"A","value":"xxxx","Num":"1"},{"name":"B","value":"xxxx","Num":"2"},{"name":"A","value":"xxxx","Num":"3"}]}
         */

        try {
            String resstr=stringBuilder.toString();
            resjson =  JSON.parseObject(resstr);
        } catch (JSONException e) {
            System.err.println("Failed to parse output as JSON: " + e.getMessage());
        }
        return  resjson;
    }
    //修改图片 输出到指定目录
    public static JSONObject modifiedImageFun (String taskdir) throws IOException, InterruptedException {
        JSONObject resJSon=new JSONObject();
        // 设置要执行的Python脚本
        String scriptName = "modified_image_module1.py";
        String scriptPath =scriptPathroot+scriptName;
        List<String> commandList = new ArrayList<>();
        commandList.add("/Users/wanghao/.virtualenvs/p3t2env/bin/python");
        commandList.add(scriptPath);
        commandList.add("-i");
        commandList.add(taskdir);
        String command = String.join(" ", commandList);
        System.out.println(command);
        // 创建 ProcessBuilder 对象并设置工作目录
        String workerName="";//无 直接是项目路径
        ProcessBuilder builder = new ProcessBuilder(commandList);
        File workingDir = new File(scriptPathroot+workerName);
        builder.directory(workingDir);

        // 启动进程并等待完成
        Process process = builder.start();
        int exitCode = process.waitFor();

        // 读取输出结果
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        System.out.println("success output: " + stringBuilder.toString());
        //如果报错，则捕获报错 的输出
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream(), StandardCharsets.UTF_8));
        String errorLine;
        StringBuilder errorBuilder = new StringBuilder();
        while ((errorLine = errorReader.readLine()) != null) {
            errorBuilder.append(errorLine).append("\n");
        }
        System.out.println("Error output: " + errorBuilder.toString());

        // 输出进程退出码
        System.out.println("Exit code: " + exitCode);

        try {
            String resstr=stringBuilder.toString();
            resJSon =  JSON.parseObject(resstr);
        } catch (JSONException e) {
            System.err.println("Failed to parse output as JSON: " + e.getMessage());
        }

        return resJSon;
    }
    //跑 hello world ！代码测试
    public static String runPythonScript() throws IOException, InterruptedException {
        String scriptPath = "/Users/wanghao/Documents/2.python_project/testmodel1/test1.py"; // 替换为实际的脚本路径
        // 创建 ProcessBuilder 对象并设置工作目录
        ProcessBuilder builder = new ProcessBuilder("python3", scriptPath);
        builder.redirectErrorStream(true); // 合并标准输出和标准错误流

        // 启动进程并等待完成
        Process process = builder.start();
        int exitCode = process.waitFor();

        // 输出进程退出码
        System.out.println("Exit code: " + exitCode);

        // 读取输出结果
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        String output = stringBuilder.toString().trim();

        return output;
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        String output =  runPythonScript();
        System.out.println("Python script output:");
        System.out.println(output);
    }


}
