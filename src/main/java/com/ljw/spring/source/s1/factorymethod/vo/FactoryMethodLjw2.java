package com.ljw.spring.source.s1.factorymethod.vo;

public class FactoryMethodLjw2 {
    private String name = "ljwFactoryMethodBean2";

    public static FactoryMethodLjw2 getFactoryMethodLjwBean() {
        return new FactoryMethodLjw2();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
