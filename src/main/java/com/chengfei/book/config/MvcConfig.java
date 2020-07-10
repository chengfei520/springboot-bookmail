package com.chengfei.book.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login.html").setViewName("user/login");
        registry.addViewController("/login_success.html").setViewName("user/login_success");
        registry.addViewController("/regist.html").setViewName("user/regist");
        registry.addViewController("/regist_success.html").setViewName("user/regist_success");
        registry.addViewController("/manager.html").setViewName("manager/manager");

    }
}
