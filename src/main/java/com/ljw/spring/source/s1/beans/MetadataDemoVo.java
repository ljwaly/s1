package com.ljw.spring.source.s1.beans;


import org.springframework.stereotype.Component;

@Component
public class MetadataDemoVo {

    private String name = "metadataDemoVoLjw";

    private String msg = "root";

    @Override
    public String toString() {
        return "MetadataDemoVo{" +
                "name='" + name + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
