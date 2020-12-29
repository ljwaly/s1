package com.ljw.spring.source.s1.test;

import com.ljw.spring.source.s1.beans.Star2;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnoTest {

    /**
     * 注解式启动ApplicationContext
     */
    @Test
    public void test1() {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestBean.class);
        Star2 bean = applicationContext.getBean(Star2.class);
        System.out.println(bean.getName());

    }
}
