package com.ljw.spring.source.s1.beandefinitionpostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.stereotype.Component;
import com.ljw.spring.source.s1.beans.RegistryBeanDefinitionBean;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Component
public class PropertyBeanDefinitionPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        /**
         * 自定义BeanDefinition创建对象
         */
        GenericBeanDefinition bd = new GenericBeanDefinition();
        bd.setBeanClass(RegistryBeanDefinitionBean.class);
        MutablePropertyValues propertyValues = bd.getPropertyValues();
        propertyValues.add("name","Registry-create-BeanDefinition-inSpring-test");
        registry.registerBeanDefinition("registryBeanDefinitionBean", bd);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {


        Properties properties = new Properties();
        properties.put("property.aba", "ljw");
        PropertiesPropertySource propertiesPropertySource = new PropertiesPropertySource("propertyCustom", properties);


        StandardEnvironment env = (StandardEnvironment) beanFactory.getBean(Environment.class);
        MutablePropertySources propertySources = env.getPropertySources();

        propertySources.addLast(propertiesPropertySource);


//        //效果同上
//        Properties properties1 = new Properties();
//        propertySources.addLast(new PropertiesPropertySource("", properties1));
//        properties1.put("property.aba", "ljw");
    }
}
