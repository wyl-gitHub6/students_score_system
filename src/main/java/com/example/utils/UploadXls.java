package com.example.utils;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author excel表格导入
 */
public class UploadXls {

    public static HSSFSheet uploadXls(MultipartFile file) throws IOException {
        // 1.通过流读取Excel文件
        InputStream inputStream = file.getInputStream();
        //2.通过poi解析流 HSSFWorkbook 处理流得到的对象中 就封装了Excel文件所有的数据
        try (HSSFWorkbook workbook = new HSSFWorkbook(inputStream)) {
            //3.从文件中获取表对象  getSheetAt通过下标获取
            HSSFSheet sheet = workbook.getSheetAt(0);
            return sheet;
        }
    }
}
