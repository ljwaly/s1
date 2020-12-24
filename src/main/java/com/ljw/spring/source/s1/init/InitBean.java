package com.ljw.spring.source.s1.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class InitBean implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        //bean初始化完成，参数初始化之后
        System.out.println("InitBean.afterPropertiesSet-----");
    }
}
