package com.learn.idea_ws_demo.config;

import com.learn.idea_ws_demo.util.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆验证的拦截器
 * @author Administrator
 */
public class InterceptConfig implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //解决后台获取前端数据中文乱码问题
        httpServletResponse.setCharacterEncoding("utf-8");
        //从请求头中会去token
        //前端需要设置token的唯一标识
        String token = httpServletRequest.getHeader("token");

        //放行的请求
        //登陆放行,在WebMvcConfig里面配置

        //验证token是否合法
        if( token != null){
            boolean isSuccess = JwtUtil.verify(token);
            if(isSuccess){
                return true;
            }
        }

        System.out.println("方法被拦截...");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
