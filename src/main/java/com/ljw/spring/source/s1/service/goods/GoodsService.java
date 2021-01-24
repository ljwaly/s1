package com.ljw.spring.source.s1.service.goods;



import com.ljw.spring.source.s1.poji.ZgGoods;

import java.util.List;

public interface GoodsService {

    void addGoods(ZgGoods zgGoods);

    List<ZgGoods> queryAll();
}
