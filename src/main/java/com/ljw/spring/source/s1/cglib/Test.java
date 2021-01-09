package com.ljw.spring.source.s1.cglib;

public class Test {
    public static void main(String[] args) {

        UserService userService = (UserService) CgLibBeanFactoryDemo.getInstance();

        String ljw = userService.doSomething1("ljw");
        System.out.println(ljw);

    }
}
