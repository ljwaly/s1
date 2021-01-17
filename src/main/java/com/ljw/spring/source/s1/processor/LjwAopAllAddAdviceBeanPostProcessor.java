package com.ljw.spring.source.s1.processor;

import com.ljw.spring.source.s1.aop.MyDefineAopInterceptorDemo;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.aop.config.AopConfigUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

@Component
public class LjwAopAllAddAdviceBeanPostProcessor implements BeanPostProcessor, PriorityOrdered{
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals(AopConfigUtils.AUTO_PROXY_CREATOR_BEAN_NAME)) {
            AnnotationAwareAspectJAutoProxyCreator aopBean = (AnnotationAwareAspectJAutoProxyCreator) bean;

            aopBean.setInterceptorNames("myDefineAopInterceptorDemo");
            return aopBean;
        }

        return bean;
    }

    private ApplicationContext applicationContext;


    @Override
    public int getOrder() {
        return 69;
    }


}
