package com.ljw.spring.source.s1.beans.scanbean.innerclass;

import com.ljw.spring.source.s1.beans.scanbean.imports.vo.XJ;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    /**
     * 注解@Bean会走FactoryBean方式创建bean
     * factoryBean=BeanConfig
     * factoryMethod=getXj
     *
     *
     * @return
     */
    @Bean
    public XJ getXj(){

        return  new XJ();
    }


}
