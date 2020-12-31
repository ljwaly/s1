package com.ljw.spring.source.s1.test;

import com.ljw.spring.source.s1.beandefinitionpostprocessor.FactoryBeanDemo;
import com.ljw.spring.source.s1.beans.PropertiesBean;
import com.ljw.spring.source.s1.beans.Star2;
import com.ljw.spring.source.s1.constructor.AutowiredConstructorBean;
import com.ljw.spring.source.s1.factorymethod.vo.FactoryMethodLjw;
import com.ljw.spring.source.s1.factorymethod.vo.FactoryMethodLjw2;
import com.ljw.spring.source.s1.listener.LjwApplicationListener;
import com.ljw.spring.source.s1.listener.event.LjwEvent;
import com.ljw.spring.source.s1.listener.event.LjwEvent1;
import com.ljw.spring.source.s1.scope.CustomScopeBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.ljw.spring.source.s1.beans.Student;
import org.springframework.core.env.Environment;

public class SpringXmlTest {

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


    /**
     * 实现删除方法
     */
    @Test
    public void test6() {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

        PropertiesBean propertiesBean = applicationContext.getBean(PropertiesBean.class);
        System.out.println("-----propertiesBean = " + propertiesBean);

        /**
         * 拿到容器对象，进行destroy的方法调用
         */
        applicationContext.getBeanFactory().destroySingletons();


    }


    /**
     * Environment的结构
     */
    @Test
    public void test7() {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

        Environment bean = applicationContext.getBean(Environment.class);

        System.out.println("bean = " + bean);



    }

    /**
     * FactoryBead的验证
     */
    @Test
    public void test8() {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

        Student bean = (Student) applicationContext.getBean("factoryBeanDemo");

        System.out.println("bean = " + bean.getName());

        FactoryBeanDemo bean1 = (FactoryBeanDemo) applicationContext.getBean("&factoryBeanDemo");

        System.out.println("bean = " + bean1);

    }


    /**
     * FactoryBead的验证
     */
    @Test
    public void test9() {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

        Student bean = applicationContext.getBean(Student.class);

        System.out.println("bean = " + bean.getName());


    }

    /**
     * 自定义Scope的验证
     */
    @Test
    public void test10() {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

        CustomScopeBean bean = applicationContext.getBean(CustomScopeBean.class);

        System.out.println("bean = " + bean.getUsername());


    }


}
