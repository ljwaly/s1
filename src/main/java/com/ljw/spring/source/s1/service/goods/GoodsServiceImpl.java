package com.ljw.spring.source.s1.service.goods;


import com.ljw.spring.source.s1.dao.CommonMapper;
import com.ljw.spring.source.s1.poji.ZgGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    CommonMapper commonMapper;

    @Autowired
    DataSource dateSource;

    @Transactional
    @Override
    public void addGoods(ZgGoods zgGoods) {
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
        int i = commonMapper.addGood(zgGoods);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ZgGoods> queryAll() {
        return commonMapper.queryAll();
    }
}
