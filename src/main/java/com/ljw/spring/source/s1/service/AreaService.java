package com.ljw.spring.source.s1.service;


import com.ljw.spring.source.s1.poji.ConsultConfigArea;

import java.util.List;
import java.util.Map;

public interface AreaService {
    List<ConsultConfigArea> queryAreaFromDB(Map param);

    String queryAreaFromRedisOne(Map param);

    String queryAreaFromRedisTow(Map param);

    int addArea(ConsultConfigArea area);
}
