package com.ljw.spring.source.s1.beans;

import org.springframework.stereotype.Component;

@Component
public class Star1 {
    public String name = "star1";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
