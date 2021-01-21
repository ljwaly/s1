package com.ljw.spring.source.s1.transcation;


import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 判断是在一个事务之中，那么就是同一个链接对象
 *
 * 如果要使用多个DML语句，
 * 需要关闭自动提交
 *
 *
 *
 * 事务有效
 * 需要入口类
 * 就是注解@EnableTransactionManagement
 */
@EnableTransactionManagement
public class ConnectionDemo {
}
