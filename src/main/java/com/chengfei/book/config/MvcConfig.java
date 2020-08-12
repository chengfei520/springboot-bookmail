package com.chengfei.book.config;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Properties;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("client/index");
        registry.addViewController("/login.html").setViewName("user/login");
        registry.addViewController("/login_success.html").setViewName("user/login_success");
        registry.addViewController("/regist.html").setViewName("user/regist");
        registry.addViewController("/regist_success.html").setViewName("user/regist_success");
        registry.addViewController("/order.html").setViewName("order/order");
        registry.addViewController("/manager.html").setViewName("manager/manager");
        registry.addViewController("/book_manager.html").setViewName("manager/book_manager");
        registry.addViewController("/book_edit.html").setViewName("manager/book_edit");
        registry.addViewController("/cart.html").setViewName("cart/cart");


    }

    /**
     * 验证码组件注册到容器
     * @return
     */
    @Bean
    public Producer kaptchaProducer() {
        Properties properties = new Properties();
        properties.setProperty("kaptcha.image.width", "100");
        properties.setProperty("kaptcha.image.height", "40");
        properties.setProperty("kaptcha.textproducer.font.size", "32");
        properties.setProperty("kaptcha.textproducer.font.color", "0,0,0");
        properties.setProperty("kaptcha.textproducer.char.string", "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYAZ");
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");

        DefaultKaptcha kaptcha = new DefaultKaptcha();
        Config config = new Config(properties);
        kaptcha.setConfig(config);
        return kaptcha;
    }
}
