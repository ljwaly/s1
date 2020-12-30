package com.ljw.spring.source.s1.test;

import com.ljw.spring.source.s1.beans.Star2;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;


public class AnnotationTest {

    /**
     * 注解式启动ApplicationContext
     */
    @Test
    public void test1() {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.ljw.spring.source.s1");
        Environment environment = applicationContext.getEnvironment();

        System.out.println("property.local=" + environment.getProperty("property.local"));

    }
}
