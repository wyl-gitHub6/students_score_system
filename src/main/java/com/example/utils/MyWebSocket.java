package com.example.utils;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Wangyl
 * @date 13:08 2021/10/2
 */
@ServerEndpoint(value = "/websocket/{username}")
@Component
@Slf4j
public class MyWebSocket {

    private String username;

    private String id;

    /**
     * 用来存放每个客户端对应的MyWebSocket对象。
     */
    private static final CopyOnWriteArrayList<MyWebSocket> WEB_SOCKET_LIST = new CopyOnWriteArrayList<>();
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 连接建立成功调用的方法
     *
     * @param session  会话
     * @param username 用户名
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        this.session = session;
        String[] split = username.split(",");
        this.id=split[0];
        this.username=split[1];
        WEB_SOCKET_LIST.add(this);
        log.info("有人加入聊天室！当前在线人数为" + WEB_SOCKET_LIST.size());
        this.session.getAsyncRemote().sendText(JSONUtil.toJsonStr(Result.success(WEB_SOCKET_LIST.size(), "")));
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        /*从set中删除*/
        WEB_SOCKET_LIST.remove(this);
        log.info("有人退出！当前在线人数为" + WEB_SOCKET_LIST.size());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message  消息
     * @param username 用户名
     */
    @OnMessage
    public void onMessage(String message, @PathParam("username") String username) {
        log.info("来自客户端的消息-->:" + username + message);
        //群发消息
        String[] split = username.split(",");
        this.id=split[0];
        this.username=split[1];
        broadcast(message,this.id,this.username);
    }

    /**
     * 发生错误时调用
     *
     * @param error 错误
     */
    @OnError
    public void onError(Throwable error) {
        log.info("发生错误");
        error.printStackTrace();
    }

    /**
     * 群发自定义消息
     *
     * @param message  消息
     * @param id       id
     * @param username 用户名
     */
    public  void broadcast(String message,String id, String username){
        for (MyWebSocket item : WEB_SOCKET_LIST) {
            /*同步异步说明参考：http://blog.csdn.net/who_is_xiaoming/article/details/53287691
            this.session.getBasicRemote().sendText(message);*/
            /*异步发送消息.*/
            item.session.getAsyncRemote().sendText(JSONUtil.toJsonStr(Result.success(WEB_SOCKET_LIST.size(),message,id,username)));
        }
    }
}
