package com.ljw.spring.source.s1.service.transaction;


import com.ljw.spring.source.s1.poji.ConsultConfigArea;
import com.ljw.spring.source.s1.poji.ZgGoods;

public interface TransationService {

    void transation(ConsultConfigArea area, ZgGoods zgGoods);

    int getTicket();

    int getTicketModeOne();
}
