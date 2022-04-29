package com.example.config;

import com.example.utils.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常抓捕
 *
 * @author Wangyl
 * @date 2022/04/27
 */
@RestControllerAdvice
public class AllExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<String> exceptionHandler(Exception e){
        return Result.error(e.getMessage()+"-"+e.getClass());
    }
}
