package com.gsh.mailservice.controller.interceptor;

import com.gsh.mailservice.util.JedisUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class APIInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        String userId = request.getParameter("userId");

        if(token == null || token.equals("")){
            System.out.println("token为空");
            return false;
        }
        if(userId == null || userId.equals("")){
            System.out.println("userId为空");
            return false;
        }

        String tokenInRedis = JedisUtil.get(userId);
        if(tokenInRedis == null || tokenInRedis.equals("") || !tokenInRedis.equals(token)){
            System.out.println("权限校验不通过");
            return false;
        }
        return true;
    }

}
