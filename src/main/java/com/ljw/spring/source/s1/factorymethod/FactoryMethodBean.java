package com.ljw.spring.source.s1.factorymethod;

import com.ljw.spring.source.s1.factorymethod.vo.FactoryMethodLjw;

/**
 * 要求方法是非静态的
 */
public class FactoryMethodBean {

    /**
     * 创建一个factory对象
     * @return
     */
    public FactoryMethodLjw getFactoryMethodLjwBean(){

        return new FactoryMethodLjw();
    }

}
