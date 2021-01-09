package com.ljw.spring.source.s1.beans.scanbean.imports;

/**
 * 被@Import注解注入类，如果不实现特殊的几个接口，最终会被初始化
 * 最终会被组装BeanDefinition，并在单利创建的时候，进行初始化
 */
public class ImportWithNothing {

    public ImportWithNothing(){
        System.out.println("ImportLjwWithNothing------abc");
    }
}
