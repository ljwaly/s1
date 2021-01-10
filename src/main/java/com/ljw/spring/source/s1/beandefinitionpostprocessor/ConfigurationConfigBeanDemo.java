package com.ljw.spring.source.s1.beandefinitionpostprocessor;

import com.ljw.spring.source.s1.beans.AnnotationBeanBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationConfigBeanDemo {

    @Bean
    public AnnotationBeanBean getAnnotationBeanBean(){
        return new AnnotationBeanBean();
    }
}
