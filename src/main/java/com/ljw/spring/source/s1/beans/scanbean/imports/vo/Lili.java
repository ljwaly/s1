package com.ljw.spring.source.s1.beans.scanbean.imports.vo;

import org.springframework.beans.factory.FactoryBean;

public class Lili implements FactoryBean {

    @Override
    public Object getObject() throws Exception {
        return new Docker();
    }

    @Override
    public Class<?> getObjectType() {
        return Docker.class;
    }
}
