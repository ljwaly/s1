package com.ljw.spring.source.s1.beans;

/**
 * 使用FactoryBean模式创建对象
 */
public class Student {

    private String name = "ljw";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + "]";
    }


}
