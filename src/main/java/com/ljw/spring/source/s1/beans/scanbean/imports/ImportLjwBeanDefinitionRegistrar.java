package com.ljw.spring.source.s1.beans.scanbean.imports;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class ImportLjwBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     * 调用@Import注解方法时候，会执行这个方法
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {


    }



}
