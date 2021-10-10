package com.example.utils;

/**
 * 封装返回JSON数据的工具类
 * T通用泛型
 * code 0 成功 -1 失败
 * @Author: wyl
 * @date: 2021/8/27 11:37
 */
public class Result<T> {
    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result() {
    }

    /**
     * @Author wyl
     * @Description 封装静态方法  -- 成功！--无数据返回
     * @Date 18:10 2021/10/6
     * @Param [message]
     * @return com.example.utils.Result<T>
     **/
    public static <T> Result<T> success(String message){
        Result<T> result = new Result<>();
        result.setCode(0);
        result.setMessage(message);
        return result;
    }

    /**
     * @Author wyl
     * @Description 封装静态方法  -- 成功！--有数据返回
     * @Date 18:10 2021/10/6
     * @Param [data, message]
     * @return com.example.utils.Result<T>
     **/
    public static <T> Result<T> success(T data,String message){
        Result<T> result = new Result<>();
        result.setCode(0);
        result.setData(data);
        result.setMessage(message);
        return result;
    }

    /**
     * @Author wyl
     * @Description 封装静态方法  -- 失败
     * @Date 18:10 2021/10/6
     * @Param [message]
     * @return com.example.utils.Result<T>
     **/
    public static <T> Result<T> error(String message){
        Result<T> result = new Result<>();
        result.setCode(-1);
        result.setMessage(message);
        return result;
    }

}
