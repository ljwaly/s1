package com.ljw.spring.source.s1.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 多例模式
 */
@Component
@Scope("prototype")
public class PrototypeBeanDemo {
    private String username = "prototypeBeanDemoName";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
