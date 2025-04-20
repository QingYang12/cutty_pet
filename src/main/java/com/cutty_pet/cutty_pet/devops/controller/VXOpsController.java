package com.cutty_pet.cutty_pet.devops.controller;

import com.alibaba.fastjson.JSONObject;
import com.cutty_pet.cutty_pet.devops.entity.DEVOPSEntity;
import com.cutty_pet.cutty_pet.devops.util.PythonRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@RequestMapping("vxapi")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST}, allowedHeaders = {"Content-Type"})
public class VXOpsController {

    private final PythonRunner pythonRunner;

    @Autowired
    public VXOpsController(PythonRunner pythonRunner) {
        this.pythonRunner = pythonRunner;
    }

    @RequestMapping("test")
    @ResponseBody
    public DEVOPSEntity selectOne(@RequestBody JSONObject param) {
        DEVOPSEntity devOPSEntity = new DEVOPSEntity();
        devOPSEntity.setId("1L");
        devOPSEntity.setCreateUser("ssss");
        return devOPSEntity;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject handleFileUpload(HttpServletRequest request, @RequestParam("file") MultipartFile[] files) {
        String uploadDirRoot = "/Users/wanghao/Documents/pyimgtemp/";
        long currentTimeMillis = System.currentTimeMillis();
        String taskdir=""+currentTimeMillis;
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
                fileName="input.png";
                Path filePath = Paths.get(uploadDir, fileName);
                Files.copy(file.getInputStream(), filePath);
                Thread.sleep(2000);
                //执行python 脚本 OCR
                //执行 执行后返回 解析json
                resjson= pythonRunner.OCRRunFun(taskdir);
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


    // 生成新图片
    @RequestMapping("createNewImg")
    @ResponseBody
    public JSONObject createNewImg(@RequestBody JSONObject param) throws IOException, InterruptedException {
        String uploadDirRoot = "/Users/wanghao/Documents/pyimgtemp";
        DEVOPSEntity devOPSEntity = new DEVOPSEntity();
        devOPSEntity.setId("1L");
        devOPSEntity.setCreateUser("ssss");
        String taskdir=param.getString("taskdir");
        String fileNameStr="input.png";
        JSONObject resjson= new JSONObject();
        //读取json
        //解析json
        //调用模型生成新图片 到执行路径
        pythonRunner.modifiedImageFun(taskdir);
        return resjson;
    }


    // 下载到本地
    @RequestMapping("dowloadNewImg")
    @ResponseBody
    public ResponseEntity<byte[]> dowloadNewImg(@RequestBody JSONObject param) throws IOException, InterruptedException {
        String uploadDirRoot = "/Users/wanghao/Documents/pyimgtemp";
        DEVOPSEntity devOPSEntity = new DEVOPSEntity();
        devOPSEntity.setId("1L");
        devOPSEntity.setCreateUser("ssss");
        String taskdir=param.getString("taskdir");
        String fileNameStr="out.png";
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

}
