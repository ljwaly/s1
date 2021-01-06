package com.ljw.spring.source.s1.beans.scanbean.innerclass;


import com.ljw.spring.source.s1.beans.Student1Vo;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


/**
 * 每一个对象，创建成一个ConfigurationClass信息收集的对象
 *
 * 在ConfigurationClassPostProcessor扫描的时候，进行内部类递归扫描
 * 创建BeanDefinition
 */
@Component
public class InnerClassDemo {

    @Component
    public class SpringSource {

        @Bean
        public Student1Vo getStudent1Vo(){
            return new Student1Vo();
        }

    }

    @Component
    public class MybatisSource {

    }
}
