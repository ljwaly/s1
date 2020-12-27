package com.ljw.spring.source.s1.test;

import com.ljw.spring.source.s1.beans.Star2;
import com.ljw.spring.source.s1.constructor.AutowiredConstructorBean;
import com.ljw.spring.source.s1.factorymethod.vo.FactoryMethodLjw;
import com.ljw.spring.source.s1.factorymethod.vo.FactoryMethodLjw2;
import com.ljw.spring.source.s1.listener.LjwApplicationListener;
import com.ljw.spring.source.s1.listener.event.LjwEvent;
import com.ljw.spring.source.s1.listener.event.LjwEvent1;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.ljw.spring.source.s1.beans.Student;

public class SpringTest {

    public static void main(String[] args) {
        String s2 =  new String("张") + new String("三");
        String s3 = s2.intern();
        String s1 = "张三";

        System.out.println(s2 == s1);
        System.out.println(s2 == s3);
        System.out.println(s3 == s1);

    }


    /**
     * xml式启动ApplicationContext
     */
    @Test
    public void test1() {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Student bean = applicationContext.getBean(Student.class);
        System.out.println(bean.getName());

        FactoryMethodLjw factoryMethodLjw = applicationContext.getBean(FactoryMethodLjw.class);
        System.out.println(factoryMethodLjw.getName());

        FactoryMethodLjw2 fctoryMethodLjw2 = applicationContext.getBean(FactoryMethodLjw2.class);
        System.out.println(fctoryMethodLjw2.getName());




    }




    /**
     * 注解式启动ApplicationContext
     */
    @Test
    public void test2() {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestBean.class);
        Star2 bean = applicationContext.getBean(Star2.class);
        System.out.println(bean.getName());

    }

    /**
     * xml式启动ApplicationContext
     * 通过ApplicationContext直接加入listener
     */
    @Test
    public void test3() {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        applicationContext.addApplicationListener(new LjwApplicationListener());
        applicationContext.publishEvent(new LjwEvent("ljw", "ljw.com.spring.source"));
        applicationContext.start();

    }

    /**
     * xml式启动ApplicationContext
     * 通过ApplicationContext直接加入listener
     */
    @Test
    public void test4() {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

        /**
         * 代码在实现的时候，相当于是一个广播模式
         */
        applicationContext.publishEvent(new LjwEvent1("1", "ljw.com.spring.source"));


    }


    /**
     * 验证bug使坏的
     * xml式启动ApplicationContext
     */
    @Test
    public void test5() {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        AutowiredConstructorBean bean = applicationContext.getBean(AutowiredConstructorBean.class);
        System.out.println("使坏：sc = " + bean.getSc());


    }


    @Test
    public void test6() {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");


        applicationContext.getBeanFactory().destroySingletons();


    }



}
