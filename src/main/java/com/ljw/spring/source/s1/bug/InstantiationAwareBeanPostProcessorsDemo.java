package com.ljw.spring.source.s1.bug;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;


@Component
public class InstantiationAwareBeanPostProcessorsDemo implements InstantiationAwareBeanPostProcessor {


    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if ("autowiredConstructorBean".equals(beanName)) {
            return false;
        }

        return true;
    }
}
