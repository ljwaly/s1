package com.ljw.spring.source.s1.test;

import com.alibaba.fastjson.JSONObject;
import com.ljw.spring.source.s1.ScanBean;
import com.ljw.spring.source.s1.poji.ConsultConfigArea;
import com.ljw.spring.source.s1.service.AreaService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionTest {

    @Test
    public void test1() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ScanBean.class);
        AreaService bean = applicationContext.getBean(AreaService.class);
        Map param = new HashMap();
        param.put("areaCode","HB1");
        List<ConsultConfigArea> consultConfigAreas = bean.queryAreaFromDB(param);
        System.out.println(JSONObject.toJSONString(consultConfigAreas));
    }
}
