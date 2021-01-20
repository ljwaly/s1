package com.ljw.spring.source.s1.methodfind;


/**
 * 编译器不能识别泛型
 * 会擦除泛型T，强制改为Object
 * @param <T>
 */
public interface SuperClass<T> {

    T getResult(T param);
}
