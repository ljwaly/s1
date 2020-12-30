package com.ljw.spring.source.s1.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("refreshScope")
public class CustomScopeBean {
    private String username = "self-define-scope-test";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
