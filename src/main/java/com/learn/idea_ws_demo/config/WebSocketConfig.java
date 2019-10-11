package com.learn.idea_ws_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author Administrator
 */
@Configuration
public class WebSocketConfig {

    /**
     * 用于扫描含有@ServerEndpoint注解的类
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
