package com.ljw.spring.source.s1.beans;

/**
 * 使用BeanFactory创建对象
 */
public class BeanFactoryBean {
    private String name = "BeanFactory-create-bean-inSpring";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
