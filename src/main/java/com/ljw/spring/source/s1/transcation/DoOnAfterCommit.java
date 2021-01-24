package com.ljw.spring.source.s1.transcation;

import org.springframework.transaction.support.TransactionSynchronizationAdapter;

/**
 * @Classname DoOnAfterCommit
 * @Description TODO
 * @Author Jack
 * Date 2021/1/21 15:34
 * Version 1.0
 */
public class DoOnAfterCommit extends TransactionSynchronizationAdapter {
    @Override
    public void afterCommit() {
        super.afterCommit();
        System.out.println("=========事务提交后做事情==========");
    }
}
