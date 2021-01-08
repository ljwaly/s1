package com.ljw.spring.source.s1.beans;


public class PropertiesBean {

    private String password;

    private String str;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "PropertiesBean{" +
                "password='" + password + '\'' +
                ", str='" + str + '\'' +
                '}';
    }
}
