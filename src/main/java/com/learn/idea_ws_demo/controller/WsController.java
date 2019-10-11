package com.learn.idea_ws_demo.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

/**
 * @author Administrator
 */
@Controller
public class WsController {

    @MessageMapping("/welcome")
    @SendTo("/topic/getting_01")
    public String handler_01(String msg){
        System.out.println("handler_01收到的信息: " + msg);
        return "这里是handler_01收到的信息: " + msg;
    }

    @MessageMapping("/welcome1")
    @SendToUser("/topic/getting_02")
    public String handler_02(String msg){
        System.out.println("handler_02收到的信息: " + msg);
        return "这是handler_02收到的信息: " + msg;
    }

    public String handler_03(){
        return "";
    }
}
