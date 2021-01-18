package com.ljw.spring.source.s1.beans;

import org.springframework.stereotype.Component;

@Component
public class Teacher {

    public void teaching() throws Exception{
        System.out.println("Teacher.teaching");
        if (!Teacher.class.isAssignableFrom(Object.class)) {
            throw new Exception();
        }
    }
}
