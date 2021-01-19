package com.ljw.spring.source.s1.test;

import com.ljw.s2.bean.Li;
import com.ljw.spring.source.s1.aop.special.TransactionDemoService;
import com.ljw.spring.source.s1.beans.*;
import com.ljw.spring.source.s1.beans.scanbean.ScanBean;
import com.ljw.spring.source.s1.beans.scanbean.imports.ImportBeanDefinitionRegistrarDemo;
import com.ljw.spring.source.s1.beans.scanbean.imports.ImportWithNothing;
import com.ljw.spring.source.s1.beans.scanbean.imports.vo.*;
import com.ljw.spring.source.s1.beans.scanbean.jconditional.DemoConditionBean;
import com.ljw.spring.source.s1.beans.scanbean.jconditional.DemoConditionProperty;
import com.ljw.spring.source.s1.beans.scanbean.metadata.AnnotationMetadataDemo;
import com.ljw.spring.source.s1.scanner.selector.SelfDefineImportSelectorScannerTest;
import com.ljw.spring.source.s1.scopedproxy.MyBean;
import com.ljw.spring.source.s1.service.StudentService;
import com.ljw.spring.source.s1.service.TeacherService;
import org.junit.Test;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
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
     *
     * getObject方法只有在@Bean注解中，才能使用切点方法
     */
    @Test
    public void test11() throws Exception {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ScanBean.class);
        Docker docker1 = applicationContext.getBean(Docker.class);
        System.out.println("docker1.hashCode()= " + docker1.hashCode());


        /**
         * lili是spring容器中的lili，
         * 普通对象，
         * 并不是@Configuration的@Bean方法中的增强代理类
         *
         */
        Lili lili = applicationContext.getBean(Lili.class);
        /**
         * 但是这里并没有调用切点方法，直接调用了Lili类内部的getObject,得到了一个新的docker
         * 所以这里是普通类，直接调用getObject()方法
         */
        Object object = lili.getObject();
        System.out.println("object.hashCode() = " + object.hashCode());


    }

    /**
     * 模拟
     */
    @Test
    public void test12() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ScanBean.class);
        DemoConditionProperty sh = applicationContext.getBean(DemoConditionProperty.class);
        System.out.println(sh);

    }


    /**
     * 创建自定义扫描注解ImportBeanDefinitionRegistrar
     * 全量扫描
     */
    @Test
    public void test13() {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ScanBean.class);
        Li bean = applicationContext.getBean(Li.class);
        System.out.println("bean = " + bean);

    }

    /**
     * 创建自定义扫描注解 + ImportSelector + BeanDefinitionRegistryPostProcessor
     * 全量扫描
     */
    @Test
    public void test14() {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SelfDefineImportSelectorScannerTest.class);
        Li bean = applicationContext.getBean(Li.class);
        System.out.println("bean = " + bean);

    }

    /**
     * 创建自定义依赖DI注入注解未完成
     * 全量扫描
     */
    @Test
    public void test15() throws Exception {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ScanBean.class);
        TeacherService teacherService = applicationContext.getBean(TeacherService.class);
        teacherService.doTeacherService();

    }

    /**
     * Aop
     *
     * test16会受到
     * Aop懒加载的影响，SetCustomTargetSourceCreator如果加上@Component，这个16的切面无效
     */
    @Test
    public void test16() throws Exception {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ScanBean.class);
        TeacherService teacherService = applicationContext.getBean(TeacherService.class);
        teacherService.doTeacherService();

    }


    /**
     * Aop
     * 注解@Transactional支持
     */
    @Test
    public void test17() {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ScanBean.class);
        TransactionDemoService transactionDemoService = applicationContext.getBean(TransactionDemoService.class);
        transactionDemoService.doTransNothing();
        transactionDemoService.doTransEffect();

    }

    /**
     * Aop-TargetSource
     * 相当于懒加载
     */
    @Test
    public void test18() throws Exception {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ScanBean.class);

        /**
         * spring容器中放置的是
         * StudentService的懒加载的代理类Proxy
         */
        StudentService studentService = applicationContext.getBean(StudentService.class);
        /**
         * 每次方法调用，都会触发代理类Proxy的invoke方法，
         * invoke方法执行targetSource.getTarget()获取另一个beanFactory工厂（无AOP）的多例StudentService对象
         * 代理类获取切面，进行AOP链路调用
         *
         * 每次方法调用，都是一个新的StudentService的多例对象，多例对象不存放beanFactory一级缓存容器
         */
        studentService.doLeaning();
        studentService.doLeaning();
    }


    /**
     * Aop；
     * ScopedProxy代理的多例对象
     *
     */
    @Test
    public void test19() throws Exception {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ScanBean.class);
        MyBean bean = applicationContext.getBean(MyBean.class);

        for (int i = 0; i < 10; i++) {
            bean.test();
        }
    }



}
