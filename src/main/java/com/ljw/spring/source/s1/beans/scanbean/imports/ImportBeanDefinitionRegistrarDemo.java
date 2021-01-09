package com.ljw.spring.source.s1.beans.scanbean.imports;

import com.ljw.spring.source.s1.beans.scanbean.imports.vo.RegistryBeanDefinitionBeanDemo;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;


/**
 * 实现了ImportBeanDefinitionRegistrar的类ImportSelectorDemo，本身并不会实例化
 */
public class ImportBeanDefinitionRegistrarDemo implements ImportBeanDefinitionRegistrar {

    /**
     * 调用@Import注解方法时候，会执行这个方法
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //TODO:自己可以自定义BeanDefinition，然后装配入spring容器


        GenericBeanDefinition bd = new GenericBeanDefinition();
        bd.setBeanClass(RegistryBeanDefinitionBeanDemo.class);
        MutablePropertyValues propertyValues = bd.getPropertyValues();
        propertyValues.add("name","Registry-create-BeanDefinitionDemo-inSpring-test");
        registry.registerBeanDefinition("registryBeanDefinitionBeanDemo", bd);


    }



}
