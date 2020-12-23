package com.ljw.spring.source.s1.factory;

import com.ljw.spring.source.s1.factory.vo.LjwFactoryVo;

/**
 * 要求方法是非静态的
 */
public class FactoryBean1 {

    /**
     * 创建一个factory对象
     * @return
     */
    public LjwFactoryVo getNewLjw(){

        return new LjwFactoryVo();
    }

}
