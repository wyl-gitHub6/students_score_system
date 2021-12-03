package com.example.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.example.utils.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author wyl
 */
@RestController
@RequestMapping("/uploadController")
public class UploadController {
    /**
     * 上传的地址
     */
    private final String image_url = "D:\\image_upload\\";
    /**
     * 上传的服务器返回路径
     */
    private final String return_url = "http://localhost:8080/image_upload/";

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        //获取图片名称
        String fileName = file.getOriginalFilename();
        //获取后缀
        String suffix = fileName.substring(fileName.lastIndexOf("."),fileName.length());
        //获取随机数
        String uuid = IdUtil.fastSimpleUUID();
        //拼接一下图片名称
        fileName = uuid + suffix;
        //上传 第一个参数文件流，第二个参数写到哪
        FileUtil.writeBytes(file.getBytes(),new File(image_url+fileName));
        System.out.println(return_url+fileName);
        return Result.success(return_url+fileName);
    }

    @DeleteMapping("/deleteImage")
    public Result deleteImage(@RequestParam("deleteUrl") String deleteUrl){
        //获取文件名
        String url = deleteUrl.substring(deleteUrl.lastIndexOf("/"),deleteUrl.length());
        //定义一个file对象
        File file = new File(image_url+url);
        file.delete();
        return Result.success("删除图片"+url+"成功！");
    }
}
