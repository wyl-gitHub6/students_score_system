package com.example.utils;

/**
 * 结果
 * 封装返回JSON数据的工具类
 * T通用泛型
 * code 0 成功 -1 失败
 *
 * @author Wangyl
 * @date 2022/08/27
 */
public class Result<T> {
    private int code;
    private String message;
    private T data;
    private String username;
    private String id;

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

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    public void setId(String id){ this.id = id; }

    public String getId(){ return id; }

    public Result() {
    }

    /**
     * 封装静态方法  -- 成功！--无数据返回
     *
     * @param message 消息
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> success(String message){
        Result<T> result = new Result<>();
        result.setCode(0);
        result.setMessage(message);
        return result;
    }

    /**
     * 封装静态方法  -- 成功！--有数据返回
     *
     * @param data    数据
     * @param message 消息
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> success(T data,String message){
        Result<T> result = new Result<>();
        result.setCode(0);
        result.setData(data);
        result.setMessage(message);
        return result;
    }

    /**
     * 群聊天使用
     *
     * @param data     数据
     * @param message  消息
     * @param id       id
     * @param username 用户名
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> success(T data,String message,String id, String username){
        Result<T> result = new Result<>();
        result.setCode(0);
        result.setData(data);
        result.setMessage(message);
        result.setUsername(username);
        result.setId(id);
        return result;
    }

    /**
     * 封装静态方法  -- 失败
     *
     * @param message 消息
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> error(String message){
        Result<T> result = new Result<>();
        result.setCode(-1);
        result.setMessage(message);
        return result;
    }

}
