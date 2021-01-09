package com.ljw.spring.source.s1.beans.scanbean.innerclass;

import com.ljw.spring.source.s1.beans.scanbean.imports.vo.Lili;
import com.ljw.spring.source.s1.beans.scanbean.imports.vo.Lison;
import com.ljw.spring.source.s1.beans.scanbean.imports.vo.LisonFactory;
import com.ljw.spring.source.s1.beans.scanbean.imports.vo.XJ;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注解@Component（调用2次，违背单例）和@Configuration（调用一次，有代理对象生成）有区别
 *
 * 注解@Configuration需要每次方法调用，需要从缓存中获取到Bena
 *
 */
@Configuration
public class ConfigurationDemo {



    /**
     * 注解@Bean会走FactoryBean方式创建BeanDefinition
     *
     * BeanDefinition对象
     * factoryBeanName = ConfigurationDemo
     * factoryMethodName = getLison
     *
     * spring装配调用一次
     * 在下面一个getLisonFactory()方法中，又调用一次
     *
     * 这两次调用情况下，
     * 注解@Component（调用2次，违背单例）和@Configuration（调用一次，有代理对象生成）有区别
     *
     *
     * @return
     */
    @Bean
    public Lison getLison(){
        return  new Lison();
    }


    /**
     * BeanDefinition对象
     * factoryBeanName = ConfigurationDemo
     * factoryMethodName = getLisonFactory
     *
     * @return
     */
    @Bean
    public LisonFactory getLisonFactory() throws Exception {
        LisonFactory lisonFactory = new LisonFactory();

        /**
         * 这里是方法调用
         */
        lisonFactory.setLison(getLison());


        Lili lili = getLili();
        Object object = lili.getObject();
        System.out.println("object.hashCode() = " + object.hashCode());


        return  lisonFactory;
    }

    @Bean
    public Lili getLili(){

        return  new Lili();
    }


    /**
     * MethodMetadata方法上的注解
     *
     * 一个@Bean的方法就对应一个BeanDefinition对象
     *
     * 注解@Bean会走FactoryBean方式创建BeanDefinition
     * factoryBeanName = ConfigurationDemo
     * factoryMethodName = getXj
     *
     *
     * @return
     */
    @Bean
    public XJ getXj(){

        return  new XJ();
    }


}
