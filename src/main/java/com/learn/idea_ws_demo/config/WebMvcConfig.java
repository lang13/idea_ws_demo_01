package com.learn.idea_ws_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Administrator
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //跳转到ws.html
        registry.addViewController("/ws").setViewName("/ws");
        registry.addViewController("/ws1").setViewName("/ws1");
        registry.addViewController("/cuan").setViewName("/cuan");
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(interceptConfig())
//                .addPathPatterns("/**")
//                //放行的请求
//                .excludePathPatterns("/login")
//                .excludePathPatterns("/cuan");
//    }

    @Bean
    public InterceptConfig interceptConfig(){
        return new InterceptConfig();
    }
}
