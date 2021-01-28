package com.ljw.spring.source.s1.test;

import com.alibaba.fastjson.JSONObject;
import com.ljw.spring.source.s1.ScanBean;
import com.ljw.spring.source.s1.poji.ConsultConfigArea;
import com.ljw.spring.source.s1.poji.ZgGoods;
import com.ljw.spring.source.s1.service.AccountService;
import com.ljw.spring.source.s1.service.AccountServiceImpl;
import com.ljw.spring.source.s1.service.AreaService;
import com.ljw.spring.source.s1.service.goods.GoodsService;
import com.ljw.spring.source.s1.service.goods.GoodsServiceImpl;
import com.ljw.spring.source.s1.service.transaction.TransationService;
import com.ljw.spring.source.s1.service.transaction.TransationServiceImpl;
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

    /**
     * 事务传播属性
     */
    @Test
    public void test2() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ScanBean.class);
        TransationService bean = (TransationService) applicationContext.getBean("transationServiceImpl");

        ConsultConfigArea ca = new ConsultConfigArea();
        ca.setAreaCode("HN1");
        ZgGoods zg = new ZgGoods();
        zg.setGoodName("Apple1");

        bean.transation(ca, zg);
    }

    @Test
    public void test3() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ScanBean.class);
        GoodsService bean = applicationContext.getBean(GoodsService.class);


        ZgGoods zg = new ZgGoods();
        zg.setGoodName("Apple1");

        bean.addGoods(zg);
    }

    @Test
    public void test4() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ScanBean.class);
        AccountService bean = (AccountService) applicationContext.getBean("accountServiceImpl");

        bean.queryAccount();

    }


    /**
     * 编程式事务
     */
    @Test
    public void test5() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ScanBean.class);
        TransationService bean = (TransationService) applicationContext.getBean("transationServiceImpl");



        bean.getTicketModeOne();
    }
}
