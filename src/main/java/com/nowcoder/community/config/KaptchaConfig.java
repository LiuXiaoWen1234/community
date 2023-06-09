package com.nowcoder.community.config;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

// 注解告诉spring这不是普通类，这是配置类
@Configuration
public class KaptchaConfig {
    @Bean
    public Producer kaptchaProducer(){
        Properties  properties = new Properties();
        properties.setProperty("kaptcha.image.width", "100");
        properties.setProperty("kaptcha.image.height", "40");
        properties.setProperty("kaptcha.textproducer.font.size", "32");
        properties.setProperty("kaptcha.textproducer.font.color", "0,0,0");
        properties.setProperty("kaptcha.textproducer.font.string", "0123456789ABCDEFGHIGKLMNOPQRSTUVWXYZ");
        properties.setProperty("kaptcha.textproducer.font.length", "4");
        properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");

        Config config = new Config(properties);
        DefaultKaptcha kaptcha = new DefaultKaptcha();
        kaptcha.setConfig(config);
        return kaptcha;

    }

}
