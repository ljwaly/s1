package com.ljw.spring.source.s1.test;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.ljw.spring.source.s1.vo.Student;

public class SpringTest {


    @Test
    public void test1() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Student bean = applicationContext.getBean(Student.class);
        System.out.println(bean.getName());
    }
}
