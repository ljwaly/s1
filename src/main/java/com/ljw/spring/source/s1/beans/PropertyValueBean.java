package com.ljw.spring.source.s1.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class PropertyValueBean {


    private String password;

    @Value("${property.aba}")
    private String aba;




    @Resource
    private TestBean1 t1;

    @Autowired
    private TestBean2 t2;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
