package com.ljw.spring.source.s1.scopedproxy;


import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 *
 * proxyMode使用cglib决定是多例模式
 * 如果只声明value是多例的，实际在注入之后，并不起效，直接就是单例
 *
 * 在被注入的时候，是一个cglib代理对象，切面中有getBean,
 * 因为声明为多例，在getBean，每次都是不同的对象
 *
 * 类似xml属性autowire-candidate,
 * 如果声明为这个：
 * 这个bean不作为其他bean的自动装配的候选者，（内部不能有其他bean的自动装配属性）
 * 但是这个bean本身还是可以使用自动装配来注入其他bean
 *
 *
 * 在被其他Bean，依赖注入的时候，触发getBean()，
 * ScopedProxyBean采用FactoryBean方式初始化，
 *     首先创建factoryBean对象 {@link org.springframework.aop.scope.ScopedProxyFactoryBean}，放入spring一级缓存
 *     然后factoryBean对象进行getObject()方法，拿到ScopedProxyFactoryBean的成员变量proxy(ScopedProxyBean的代理对象CGLIB)，
 *     并放入{@link org.springframework.beans.factory.support.FactoryBeanRegistrySupport}的factoryBeanObjectCache缓存中，
 *     proxy并返回给外层的bean，用来被注入
 *
 *          这个FactoryBean对象是在扫描@Component注解的时候，创建的BeanDefinition，初始化进去的，
 *          初始化BeanDefinition，声明了这个BeanDefinition是多例的，
 *          这个FactoryBean对象实现了BeanFactoryAware接口，在初始化Aware接口setBeanFactory的时候，
 *          创建了一个成员变量proxy，是ScopedProxyBean的代理CGLIB代理对象，
 *          proxy还放入了一个切面{@link org.springframework.aop.support.DelegatingIntroductionInterceptor}
 *
 *
 * 当本代理对象proxy被调用的时候，触发代理的拦截器invoke方法，
 * invoke方法内部调用getTarget(),
 * 重新触发ScopedProxyBean的getBean操作，
 * 此时使用beanName是targetSource.scopedProxyBean，
 * 因为这个targetSource.scopedProxyBean是多例的，触发多例scope初始化bean模式，此时并不会缓存这个bean
 *
 * 每次调用，都如以上调用过程，创建新的多例对象，完成方法调用
 *
 *
 */
@Component
@Scope(value = DefaultListableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ScopedProxyBean {

    public void code () {
        System.out.println("ScopedProxyBean.code=" + this.hashCode());

    }
}
