package com.ljw.spring.source.s1.beans;

/**
 * 使用@Bean创建对象
 */
public class AnnotationBeanBean {
    private String name = "Annotation-Bean-create-inSpring";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
