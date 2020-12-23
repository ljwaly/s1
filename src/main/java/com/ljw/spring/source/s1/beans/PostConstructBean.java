package com.ljw.spring.source.s1.beans;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class PostConstructBean {

    @PostConstruct
    public void init() {
        System.out.println("---PostConstructBean--init...");
    }

    /**
     * 涉及一些别的东西
     */
    @PreDestroy
    public void desy(){

        System.out.println("---PostConstructBean--desy...");

    }

}
