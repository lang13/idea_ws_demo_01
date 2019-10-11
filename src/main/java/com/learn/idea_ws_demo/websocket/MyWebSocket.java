package com.learn.idea_ws_demo.websocket;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * ws的接入点
 * @author Administrator
 */
@ServerEndpoint("/WebSocket")
@Component
public class MyWebSocket {
    /**记录当前的连接数*/
    private static int onlineCount = 0;

    /**创建线程安全的set集合来存放MyWebSocket*/
    public static CopyOnWriteArraySet<MyWebSocket> webSockets = new CopyOnWriteArraySet<>();

    /**于某个客户端的连接会话,需要这session来给客户端发送信息*/
    private Session session;

    private static Logger logger = Logger.getLogger(MyWebSocket.class);
    /**
     * 建立ws连接后调用的方法
     * @param session
     */
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        /**加入到set中*/
        webSockets.add(this);
        addOnLineCount();
        logger.info("有新的连接加入!当前在线的人数为: " + getOnlineCount());
    }

    /**
     * 连接关闭时调用的方法
     */
    @OnClose
    public void onClose(){
        /**从set中移除*/
        webSockets.remove(this);
        subOnlineCount();
        logger.info("有一连接关闭!当前在线的人数为: " + getOnlineCount());
    }

    /**
     * 收到客户端信息后调用的方法
     * @param msg
     * @param session
     */
    @OnMessage
    public void onMessage(String msg, Session session){
        this.session = session;
        logger.info("这是来自服务器的信息: " + msg);
        try {
            //发送信息
            sendAll(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送信息需要注意方法的加锁,避免阻塞报错
     * @param msg
     */
    public synchronized void sendMessage(String msg) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(msg);
        this.session.getAsyncRemote().sendText(json);
    }

    /**
     * 自定义群发信息
     * @param msg
     * @throws IOException
     */
    public static void sendAll(String msg) throws IOException{
        for (MyWebSocket webSocket : webSockets){
            webSocket.sendMessage(msg);
        }
    }

    public static synchronized void addOnLineCount(){
        MyWebSocket.onlineCount++;
    }

    public static synchronized int getOnlineCount(){
        return onlineCount;
    }

    public static synchronized void subOnlineCount(){
        MyWebSocket.onlineCount--;
    }
}
