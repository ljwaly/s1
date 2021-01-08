package com.ljw.spring.source.s1.beans.scanbean.jconditional;

import com.ljw.spring.source.s1.beans.scanbean.imports.vo.AH;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@ConditionOnBean(AH.class)
public class DemoConditionBean {
    private static String flag = "demoConditionBeanTest";
}
