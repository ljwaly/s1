package com.ljw.spring.source.s1.service.goods;


import com.ljw.spring.source.s1.dao.CommonMapper;
import com.ljw.spring.source.s1.poji.ZgGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    CommonMapper commonMapper;

    @Transactional
    @Override
    public void addGoods(ZgGoods zgGoods) {
        int i = commonMapper.addGood(zgGoods);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ZgGoods> queryAll() {
        return commonMapper.queryAll();
    }
}
