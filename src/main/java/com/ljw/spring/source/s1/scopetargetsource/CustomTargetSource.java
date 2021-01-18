package com.ljw.spring.source.s1.scopetargetsource;

import org.springframework.aop.target.AbstractBeanFactoryBasedTargetSource;


/**
 * 类似AOP的懒加载
 */
public class CustomTargetSource extends AbstractBeanFactoryBasedTargetSource {

    @Override
    public Object getTarget() throws Exception {
        /**
         * 这里返回的是一个不调用切面的其他容器的对象本身
         *
         * 这里的beanFactory是不一样的，并不是spring的beanFactory
         *
         * AbstractBeanFactoryBasedTargetSourceCreator.getTargetSource()方法中对BeanFactory进行了偷梁换柱
         *
         */
        return getBeanFactory().getBean(getTargetBeanName());
    }

}
