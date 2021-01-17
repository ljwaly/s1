package com.ljw.spring.source.s1.proxy;

/**
 * 被代理对象
 */
public class Xiaoming implements People{

    //小明找到对象之前，需要有小明的父母帮助小明找对象

    @Override
    public void findMM(String beau) {
        System.out.println("我是小明，没时间找对象，我需要找个女朋友结婚:" + beau);
    }


    //帮助小明去结婚，后带小孩
}
