package com.ljw.spring.source.s1.destory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;


@Component
public class DestoryBeanDemo implements DisposableBean {

    @Override
    public void destroy() throws Exception {
        System.out.println("======DestoryBeanDemo.destroy");
    }
}
