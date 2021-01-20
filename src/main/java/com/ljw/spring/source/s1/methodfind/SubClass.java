package com.ljw.spring.source.s1.methodfind;


/**
 * 由于泛型擦除，无法识别
 * 在代码内部会重新生成一个新的方法,
 * 这个新方法就是：桥接方法
 *
 * 桥接接口与我们的实现方法
 */
public class SubClass implements SuperClass<String>{
    @Override
    public String getResult(String param) {
        return param;
    }


//    public Object getResult(Object param) {
//        return (String) param;
//    }
}
