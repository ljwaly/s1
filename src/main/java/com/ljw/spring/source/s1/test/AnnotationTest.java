package com.ljw.spring.source.s1.test;

import com.ljw.spring.source.s1.beans.*;
import com.ljw.spring.source.s1.beans.scanbean.ScanBean;
import com.ljw.spring.source.s1.beans.scanbean.imports.ImportBeanDefinitionRegistrarDemo;
import com.ljw.spring.source.s1.beans.scanbean.imports.ImportWithNothing;
import com.ljw.spring.source.s1.beans.scanbean.imports.vo.Docker;
import com.ljw.spring.source.s1.beans.scanbean.jconditional.DemoConditionBean;
import com.ljw.spring.source.s1.beans.scanbean.imports.vo.AH;
import com.ljw.spring.source.s1.beans.scanbean.imports.vo.HN;
import com.ljw.spring.source.s1.beans.scanbean.imports.vo.SH;
import com.ljw.spring.source.s1.beans.scanbean.jconditional.DemoConditionProperty;
import com.ljw.spring.source.s1.beans.scanbean.metadata.AnnotationMetadataDemo;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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
        ImportWithNothing bean =  applicationContext.getBean(ImportWithNothing.class);
        System.out.println("bean=" + bean);
    }


    /**
     * 获取AnnotationMetadata测试
     */
    @Test
    public void test7() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ScanBean.class);
        AnnotationMetadataDemo bean =  applicationContext.getBean(AnnotationMetadataDemo.class);
        bean.metaData();
    }



    /**
     * 模拟springboot的@Import注入类实现接口DeferredImportSelector方式，
     * 向spring容器添加实例
     */
    @Test
    public void test8() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ScanBean.class);
        SH sh =  applicationContext.getBean(SH.class);
        System.out.println(sh);

        HN hn =  applicationContext.getBean(HN.class);
        System.out.println(hn);
    }
    /**
     * 注解@Import注入类，实现特殊接口（例如：DeferredImportSelector）方式，
     * 类本身并不会被加入到spring容器
     */
    @Test
    public void test8_1() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ScanBean.class);
        ImportBeanDefinitionRegistrarDemo sh =  applicationContext.getBean(ImportBeanDefinitionRegistrarDemo.class);
        System.out.println(sh);
    }

    /**
     * 模拟@Conditon
     */
    @Test
    public void test9() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ScanBean.class);

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();

        AH ah =  applicationContext.getBean(AH.class);
        System.out.println(ah);

        DemoConditionBean sh = applicationContext.getBean(DemoConditionBean.class);
        System.out.println(sh);



    }

    /**
     * 模拟@ConditionOnProperty自定义条件注入bean
     */
    @Test
    public void test10() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ScanBean.class);
        DemoConditionProperty sh = applicationContext.getBean(DemoConditionProperty.class);
        System.out.println(sh);

    }


    /**
     * 模拟@Configuration注解和@Component注解差别
     */
    @Test
    public void test11() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ScanBean.class);
        Docker bean = applicationContext.getBean(Docker.class);
        System.out.println(bean.hashCode());

    }







}
