package com.ljw.spring.source.s1.beans.scanbean.imports.vo;

import org.springframework.stereotype.Component;

@Component
public class AH {
    private String flag = "anhui";

    public String getFlag() {
        return this.flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
