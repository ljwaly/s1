package com.ljw.spring.source.s1.service;

import org.springframework.stereotype.Component;

@Component
public class StudentService {

    public String doLeaning() {
        System.out.println("======StudentService.doLeaning");

        return "Student leaning";
    }
}
