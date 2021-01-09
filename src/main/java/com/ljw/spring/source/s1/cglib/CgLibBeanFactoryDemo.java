package com.ljw.spring.source.s1.cglib;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.CallbackFilter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;

public class CgLibBeanFactoryDemo {

    public static Object getInstance(){

        /**
         * 子类生成代理
         */
        Enhancer enhancer = new Enhancer();
        /**
         * 对谁代理，就把谁设置为父类
         */
        enhancer.setSuperclass(UserServiceImpl.class);

        CallbackFilter callbackFilter = new CglibCallBackFilterDemo();

        /**
         * 初始化策略filter
         * 这个会进行拦截，
         */
        enhancer.setCallbackFilter(callbackFilter);


        /**
         * 创建各种Callback对象
         */
        Callback callback1 = new DosomethingIntercepter1();
        Callback callback2 = new DosomethingIntercepter2();
        Callback callback3 = new DosomethingIntercepter3();
        //这个NoOp表示no operator, 即什么也不操作，有点像null操作
        Callback noop = NoOp.INSTANCE;
        //
        Callback fixedValueCallBack = new FixedValueIntercepter();

        /**
         * 有序数组，0,1,2,3,4
         */
        Callback[] callbacks = {callback1, callback2, callback3, noop, fixedValueCallBack};

        //初始化callBack策略
        enhancer.setCallbacks(callbacks);

        return enhancer.create();


    }

}
