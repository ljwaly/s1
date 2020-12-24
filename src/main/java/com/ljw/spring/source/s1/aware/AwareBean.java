package com.ljw.spring.source.s1.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

/**
 * 当AwareBean实例化完成之后
 */
//@Component
public class AwareBean implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, EnvironmentAware, ImportAware {

    /**
     *  拿到bean的名称
     * @param name
     */
    @Override
    public void setBeanName(String name) {
        System.out.println("name = " + name);
    }

    /**
     * 可以拿到beanFactory的对象
     *
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("beanFactory = " + beanFactory);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("applicationContext = " + applicationContext);
    }

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("environment = " + environment);
    }

    /**
     * 拿到importMetadata对象
     * @param importMetadata
     */
    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        /**
         * 可以拿到使用了这个类的@Import(AwareBean.class)
         * 的对象(例如CQ)上的所有的注解
         */

        System.out.println("importMetadata = " + importMetadata);
        MergedAnnotations annotations = importMetadata.getAnnotations();

    }
}
