package com.ljw.spring.source.s1.beans.scanbean.imports;

/**
 * 被@Import注解注入类，最终会被组装BeanDefinition，并在单利创建的时候，进行初始化
 */
public class ImportLjwWithNothing {

    public ImportLjwWithNothing(){
        System.out.println("ImportLjwWithNothing------abc");
    }
}
