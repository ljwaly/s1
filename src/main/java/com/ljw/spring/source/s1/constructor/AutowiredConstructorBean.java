package com.ljw.spring.source.s1.constructor;

import com.ljw.spring.source.s1.beans.CQ;
import com.ljw.spring.source.s1.beans.SC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AutowiredConstructorBean {


    //侵入性强，spring的注解
    @Autowired
    private SC sc;

    //jdk注解，在
    @Resource
    private CQ cq;

    /**
     * 结论：
     * 1.如果只有一个有参数的构造方法，无@Autowired注释，也会自动装配
     * 2.如果有多个构造方法，优先@Autowired注解的
     * 3.如果有多个构造方法，多个@Autowired注解的,非必须加载的构造方法需要设定Required=false,并且参数越多的，优先级越高
     * 4.如果有多个构造方法，无@Autowired注解的，需添加无参数构造方法
     *
     * @param sc
     * @param cq
     */
    @Autowired(required = false)
    public AutowiredConstructorBean (SC sc, CQ cq) {
        System.out.println("cq = " + cq);
        System.out.println("sc = " + sc);


    }

    @Autowired(required = false)
    public AutowiredConstructorBean (SC sc) {

        System.out.println("sc = " + sc);


    }

    public SC getSc() {
        return sc;
    }

    public CQ getCq() {
        return cq;
    }
}
