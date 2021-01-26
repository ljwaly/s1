package com.ljw.spring.source.s1.transcation.transactioneffect;

import org.springframework.transaction.support.TransactionSynchronizationAdapter;

public class MyTransactionEffter extends TransactionSynchronizationAdapter {

    @Override
    public void afterCommit() {
        super.afterCommit();
        System.out.println("=================MyTransactionEffter.test = afterCommit");
    }
}
