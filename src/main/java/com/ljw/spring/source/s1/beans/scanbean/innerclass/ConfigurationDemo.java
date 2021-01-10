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
 * 注解@Configuration在spring容器中，创建出来的对象ConfigurationDemo是动态代理类对象，
 *     在调用此代理对象方法的时候，会调用filter的accept方法并转向代理方法（进行前后置处理）
 * 注解@Component在spring容器中，创建粗来的对象就是ConfigurationDemo自己类的对象
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


        /**
         * spring管理的lili对象，在@Configuration的回调方法中，getBean过程中放到spring容器了
         * 放进spring容器之后，返回的bean，由这个bean创建了一个增强代理Lili对象，对getObject有切点的对象
         * 这个对象并不会放入spring容器中，返回到这里
         *
         */
        Lili lili = getLili();

        /**
         * 在@Bean方法内部的方法调用，都会触发创建增强代理类，形成切点方法
         * 而在其他环境中，不能触发切点方法（从spring中拿出来的Lili是一个普通类）
         * 在这里，lili是一个增强代理对象，这个并不是spring容器管理的
         *
         */
        Object object = lili.getObject();
        System.out.println("object.hashCode() = " + object.hashCode());


        return  lisonFactory;
    }

    @Bean
    public Lili getLili(){
        /**
         * 在创建这个对象的时候，
         * 因为是@Bean注解
         * 会在@Configuration的代理增强类的回调方法中，创建Lili的对象，
         * 而Lili本身是FactoryBean，会创建Lili的增强代理类，
         * 然后在Lili的代理类内部设定回调方法，
         * 当getLili()方法被调用的时候，激活getObject方法
         *
         *
         */
        return new Lili();
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
