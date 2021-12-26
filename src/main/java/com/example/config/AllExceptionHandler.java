package com.example.config;

import com.example.utils.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author wyl
 */
@RestControllerAdvice
public class AllExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result ExceptionHandler(Exception e){
        return Result.error(e.getMessage()+"-"+e.getClass());
    }
}