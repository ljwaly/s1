package com.ljw.spring.source.s1.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class PropertyValueBean {

    @Value("${property.aba}")
    private String aba;

    @Resource
    private Star1 s1;

    @Autowired
    private Star2 s2;

    public PropertyValueBean (){
        System.out.println("----PropertyValueBean");

    }

    @PostConstruct
    public void init(){
        System.out.println("----PropertyValueBean.init");
    }

    public String getAba() {
        return aba;
    }

    public void setAba(String aba) {
        this.aba = aba;
    }
}
