package com.light.springboot.config;

import com.light.springboot.domain.email.Email;
import com.light.springboot.intercepter.AuthIntercepter;
import com.light.springboot.util.log.LogUtil;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;
import javax.servlet.MultipartConfigElement;

/**
 * @author 李振振
 * @version 1.0
 * @date 2019/8/23 17:56
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 自定义拦截器
     */
    @Resource
    private AuthIntercepter intercepter ;

    @Resource
    private FileConfig fileConfig;

    /**
     * 1:创建自己的Interceptor
     * 2:继承WebMvcConfigurer,并重写addInterceptors方法
     * 3:实例化自己的Interceptor
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LogUtil.info("--拦截器启动--");
        // 注册拦截器
        InterceptorRegistration registration = registry.addInterceptor(intercepter);
        // 拦截配置(比如前端以api开头的请求)
        registration.addPathPatterns("/api/**");
        // 排除配置(比如前端以word开头的请求)
        registration.excludePathPatterns("/api/user/login");
    }

    /**
     * 设置静态文件匹配路径
     * @param resourceHandlerRegistry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry) {
        resourceHandlerRegistry.addResourceHandler(fileConfig.getUpload()).addResourceLocations("file:"+fileConfig.getProfile());
    }

    /**
     * 设置文件存放路径
     * @return
     */
    @Bean
    MultipartConfigElement multipartConfigElement (){
        MultipartConfigFactory factory  = new MultipartConfigFactory();
        factory.setLocation(fileConfig.getProfile());
        return factory.createMultipartConfig();
    }
}
