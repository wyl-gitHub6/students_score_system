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
    private final String IMAGE_URL = "D:\\image_upload\\";
    /**
     * 上传的服务器返回路径
     */
    private final String RETURN_URL = "http://localhost:8080/image_upload/";

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        //获取图片名称
        String fileName = file.getOriginalFilename();
        //获取后缀
        assert fileName != null;
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        //获取随机数
        String uuid = IdUtil.fastSimpleUUID();
        //拼接一下图片名称
        fileName = uuid + suffix;
        //上传 第一个参数文件流，第二个参数写到哪
        FileUtil.writeBytes(file.getBytes(),new File(IMAGE_URL+fileName));
        System.out.println(RETURN_URL+fileName);
        return Result.success(RETURN_URL+fileName);
    }

    @DeleteMapping("/deleteImage")
    public Result deleteImage(@RequestParam("deleteUrl") String deleteUrl){
        //获取文件名
        String url = deleteUrl.substring(deleteUrl.lastIndexOf("/"));
        //定义一个file对象
        File file = new File(IMAGE_URL+url);
        file.delete();
        return Result.success("删除图片"+url+"成功！");
    }
}
