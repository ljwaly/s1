package com.ljw.spring.source.s1.service;

import com.ljw.spring.source.s1.dao.CommonMapper;
import com.ljw.spring.source.s1.poji.ConsultConfigArea;
import com.ljw.spring.source.s1.service.goods.GoodsService;
import com.ljw.spring.source.s1.transcation.DoOnAfterCommit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class AreaServiceImpl implements AreaService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AccountService accountService;

    @Autowired
    private CommonMapper commonMapper;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    AreaService areaService;

    @Autowired
    DataSource dateSource;

    /**
     *
     * 注解@Transactional后面的属性，设置隔离级别，是否只读，传播属性
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = true, rollbackFor = RuntimeException.class)
    @Override
    public List<ConsultConfigArea> queryAreaFromDB(Map param) {
        TransactionSynchronizationManager.registerSynchronization(new DoOnAfterCommit());
        logger.info("================从mysql里面查询数据 事务1========================");
        List<ConsultConfigArea> areas = commonMapper.queryAreaByAreaCode(param);
        return areas;
    }

    @Transactional
    @Override
    public String queryAreaFromRedisOne(Map param) {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("================从mysql里面查询数据 事务2========================");
        return "OK";
    }

    @Transactional
    @Override
    public String queryAreaFromRedisTow(Map param) {
        return "OK";
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public int addArea(ConsultConfigArea area) {
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
        int i = commonMapper.addArea(area);
        return i;
    }
}
