package com.ljw.spring.source.s1.beans;


public class PropertiesBean {

    private String password;

    private String name;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PropertiesBean{" +
                "password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
