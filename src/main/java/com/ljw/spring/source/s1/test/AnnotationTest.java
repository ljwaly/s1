package com.ljw.spring.source.s1.test;

import com.ljw.spring.source.s1.beans.*;
import com.ljw.spring.source.s1.beans.scanbean.ScanBean;
import com.ljw.spring.source.s1.beans.scanbean.imports.ImportLjwWithNothing;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * 注解启动：
 * ApplicationContext
 */
public class AnnotationTest {

    /**
     * 扫描包
     */
    @Test
    public void test1() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.ljw.spring.source.s1");
        Environment environment = applicationContext.getEnvironment();
        System.out.println("property.local=" + environment.getProperty("property.local"));
    }


    /**
     * 扫描类
     */
    @Test
    public void test2() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ScanBean.class);
        Environment environment = applicationContext.getEnvironment();
        System.out.println("property.local=" + environment.getProperty("property.local"));
    }

    /**
     * 对象加入spring容器-1
     * 自定义BeanDefinition对象模式,通过registry方式注册的
     */
    @Test
    public void test3() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ScanBean.class);
        RegistryBeanDefinitionBean registryBeanDefinitionBean = (RegistryBeanDefinitionBean) applicationContext.getBean("registryBeanDefinitionBean");
        System.out.println("registryBeanDefinitionBean=" + registryBeanDefinitionBean.getName());
    }

    /**
     * 对象加入spring容器-2
     * 自定义对象，通过BeanFactory加入spring容器
     */
    @Test
    public void test4() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ScanBean.class);
        BeanFactoryBean beanFactoryBean = (BeanFactoryBean) applicationContext.getBean("beanFactoryBean");
        System.out.println("beanFactoryBean=" + beanFactoryBean.getName());
    }

    /**
     * 对象加入spring容器-3
     * FactoryBean模式
     */
    @Test
    public void test5() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ScanBean.class);
        Student student = (Student) applicationContext.getBean("factoryBeanDemo");
        System.out.println("student=" + student.getName());
    }

    /**
     * 对象加入spring容器-4
     * 注解@Bean模式
     */
    @Test
    public void test6() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ScanBean.class);
        ImportLjwWithNothing annotationBeanBean =  applicationContext.getBean(ImportLjwWithNothing.class);
        System.out.println("annotationBeanBean=" + annotationBeanBean);
    }












}
