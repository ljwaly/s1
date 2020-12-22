package com.ljw.spring.source.s1.vo;

/**
 * @Auth lijinwu
 * @Date 2020年12月9日 下午4:18:01
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
