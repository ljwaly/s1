package com.ljw.spring.source.s1.beans;

import com.ljw.spring.source.s1.aware.AwareBean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Component
@Import(AwareBean.class)
public class CQ {
    private static String flag = "cq";

}
