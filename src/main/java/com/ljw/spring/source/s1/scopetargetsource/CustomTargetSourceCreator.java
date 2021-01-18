package com.ljw.spring.source.s1.scopetargetsource;

import com.ljw.spring.source.s1.service.StudentService;
import org.springframework.aop.framework.autoproxy.target.AbstractBeanFactoryBasedTargetSourceCreator;
import org.springframework.aop.target.AbstractBeanFactoryBasedTargetSource;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class CustomTargetSourceCreator extends AbstractBeanFactoryBasedTargetSourceCreator {

    @Override
    protected AbstractBeanFactoryBasedTargetSource createBeanFactoryBasedTargetSource(Class<?> beanClass, String beanName) {

        if (getBeanFactory() instanceof ConfigurableListableBeanFactory) {
            ConfigurableListableBeanFactory beanFactory = (ConfigurableListableBeanFactory) getBeanFactory();
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);

            if (beanClass.isAssignableFrom(StudentService.class)) {
                return new CustomTargetSource();
            }

        }

        return  null;
    }
}
