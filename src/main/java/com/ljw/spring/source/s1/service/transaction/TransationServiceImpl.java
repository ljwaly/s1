package com.ljw.spring.source.s1.service.transaction;


import com.ljw.spring.source.s1.dao.CommonMapper;
import com.ljw.spring.source.s1.poji.ConsultConfigArea;
import com.ljw.spring.source.s1.poji.ZgGoods;
import com.ljw.spring.source.s1.poji.ZgTicket;
import com.ljw.spring.source.s1.service.AreaService;
import com.ljw.spring.source.s1.service.goods.GoodsService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransationServiceImpl implements TransationService {

    @Autowired
    AreaService areaService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    CommonMapper commonMapper;

    @Autowired
    TransationService transationService;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private DataSource dateSource;


    /**
     * 注解@Transactional的传播属性：默认（REQUIRED，REQUIRED_NEW，NESTED）
     *
     * REQUIRED:是同一个事务，同一个事务对象，同一个链接对象
     * 内部有异常，一旦内部进行了回滚，则设定ResourceHolderSupport的全局变量rollbackOnly=true，最终在最外层进行回滚
     *
     * REQUIRES_NEW:内层中有一个是这个，外层和其他内层都是REQUIRED
     *  REQUIRES_NEW的是会创建一个事务对象，其他的是一个事务属性
     *  （在REQUIRES_NEW运行时，老的事务对象挂起（解除绑定），而REQUIRES_NEW则自我进行回滚或者提交，在处理完之后，会进行原来的事务对象进行重新绑定）
     *
     *
     *
     */
    @Transactional
    @Override
    public void transation(ConsultConfigArea area, ZgGoods zgGoods) {

        /**
         * 获取连接对象
         */
        ConnectionHolder conHolder =
                (ConnectionHolder) TransactionSynchronizationManager.getResource(
                        //获取数据源对象，创建DataSourceTransactionManager时候，设置进去的
                        dateSource
                );
        Connection connection = conHolder.getConnection();
        System.out.println("------------connection = " + connection);


        /**
         * 多个嵌套方法都有事务属性的话，
         * 最外层做事务提交
         *
         * 默认传播属性的情况下
         * 即使外层嵌套了try--catch，也会造成全局的回滚
         *
         */
        areaService.addArea(area);
        goodsService.addGoods(zgGoods);
    }


    @Transactional
    @Override
    public int getTicket() {

        //1、获取锁
        List<ZgTicket> zgTickets = commonMapper.queryTicketById("12306");
        Map lockmap = new HashMap();
        lockmap.put("ticketId", "12306");
        lockmap.put("version", zgTickets.get(0).getVersion());
        int i = commonMapper.updateLock(lockmap);

        if (i > 0) {
            //抢票
            ZgTicket zgTicket = zgTickets.get(0);
            zgTicket.setTicketCount(2);
            int i1 = commonMapper.updateTicket(zgTicket);
        } else {
            //继续抢
            ((TransationService) AopContext.currentProxy()).getTicket();
        }

        return 0;
    }

    @Autowired
    PlatformTransactionManager platformTransactionManager;

    public void xxx() {
        DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
        defaultTransactionDefinition.setPropagationBehavior(0);
        TransactionStatus transaction = platformTransactionManager.getTransaction(defaultTransactionDefinition);

        try {
            System.out.println("业务代码");
        }catch (Exception e) {
            platformTransactionManager.rollback(transaction);
        }

        platformTransactionManager.commit(transaction);
    }




    @Override
    public int getTicketModeOne() {
        Integer execute = transactionTemplate.execute(status -> {
            //1、获取锁
            List<ZgTicket> zgTickets = commonMapper.queryTicketById("12306");
            Map lockmap = new HashMap();
            lockmap.put("ticketId", "12306");
            lockmap.put("version", zgTickets.get(0).getVersion());
            int i = commonMapper.updateLock(lockmap);

            if (i > 0) {
                //抢票
                ZgTicket zgTicket = zgTickets.get(0);
                zgTicket.setTicketCount(2);
                int i1 = commonMapper.updateTicket(zgTicket);
            }
            return i;
        });
        if (execute == 0) {
            //继续抢
            getTicketModeOne();
        }
        return 0;
    }
}
