package com.baizhi;

import com.baizhi.iterceptors.AdminIterceptors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

/**
 * Created by Administrator on 2018/6/11.
 */
@Configuration
public class WebMvcConfigurerAdapter extends org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter{
    @Autowired
    private AdminIterceptors adminIterceptors;
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(adminIterceptors).addPathPatterns("/**");
        registry.addInterceptor(adminIterceptors).excludePathPatterns("/login*");
        registry.addInterceptor(adminIterceptors).excludePathPatterns("/show");
        registry.addInterceptor(adminIterceptors).excludePathPatterns("/code");
        registry.addInterceptor(adminIterceptors).excludePathPatterns("/first_page");
        registry.addInterceptor(adminIterceptors).excludePathPatterns("/wen");
        registry.addInterceptor(adminIterceptors).excludePathPatterns("/regist");
        registry.addInterceptor(adminIterceptors).excludePathPatterns("/modify");
        registry.addInterceptor(adminIterceptors).excludePathPatterns("/obtain");
        registry.addInterceptor(adminIterceptors).excludePathPatterns("/check");
        registry.addInterceptor(adminIterceptors).excludePathPatterns("/member");
    }
}
