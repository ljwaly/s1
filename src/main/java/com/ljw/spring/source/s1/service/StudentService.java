package com.ljw.spring.source.s1.service;

import org.springframework.stereotype.Component;

/**
 * 通过scopetargetsource包内部的3个类的操作，
 * 将StudentService处理成为一个代理的 懒加载的 多例的对象
 * 每次方法调用，都触发懒加载的getBean操作
 *
 */
@Component
public class StudentService {

    public String doLeaning() {
        System.out.println("======StudentService.doLeaning");

        return "Student leaning";
    }
}
