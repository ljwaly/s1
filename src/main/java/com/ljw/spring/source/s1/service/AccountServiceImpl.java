package com.ljw.spring.source.s1.service;

import org.springframework.stereotype.Service;

/**
 * @Classname AccountServiceImpl
 * @Description TODO
 * @Author Jack
 * Date 2020/12/3 17:21
 * Version 1.0
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Override
    public String queryAccount() {
        System.out.println("queryAccount");
        return null;
    }
}
