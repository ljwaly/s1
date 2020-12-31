package com.ljw.spring.source.s1.beandefinitionpostprocessor;

import com.ljw.spring.source.s1.beans.BeanFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class BeanFactoryBeanDemo implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        /**
         * 自定义对象
         * 工厂注入
         */
        BeanFactoryBean beanFactoryBean = new BeanFactoryBean();
        beanFactory.registerSingleton("beanFactoryBean", beanFactoryBean);
    }
}
