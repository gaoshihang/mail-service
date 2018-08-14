package com.gsh.mailservice.configuration;

import com.gsh.mailservice.controller.interceptor.APIInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class InterceptorConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器
        InterceptorRegistration ir = registry.addInterceptor(new APIInterceptor());
        //配置拦截路径
        ir.addPathPatterns("/alertService/**");
        ir.addPathPatterns("/alertRules/**");
    }


}
