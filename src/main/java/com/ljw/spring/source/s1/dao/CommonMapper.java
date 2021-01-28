package com.ljw.spring.source.s1.dao;


import com.ljw.spring.source.s1.poji.ConsultConfigArea;
import com.ljw.spring.source.s1.poji.ZgGoods;
import com.ljw.spring.source.s1.poji.ZgTicket;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface CommonMapper {

    @Select("select * from consult_configarea where areaCode=#{areaCode}")
    List<ConsultConfigArea> queryAreaByAreaCode(Map param);

    @Select("select * from consult_configarea where areaCode=#{areaCode}")
    List<ConsultConfigArea> queryAreaById(String areaCode);

    @Insert("insert into consult_configarea(AREACODE,AREANAME,STATE) values(#{areaCode},#{areaName},#{state})")
    int addArea(ConsultConfigArea area);

    @Insert("insert into zg_goods (goodCode, goodName, count\n" +
            "\t\t)\n" +
            "\t\tvalues (#{goodCode,jdbcType=VARCHAR}, #{goodName,jdbcType=VARCHAR},\n" +
            "\t\t#{count,jdbcType=INTEGER}\n" +
            "\t\t)")
    int addGood(ZgGoods zgGoods);

    @Select("select * from zg_goods where goodCode=#{goodCode}")
    List<ZgGoods> queryGoodsByGoodCode(String goodCode);

    @Select("select * from zg_goods")
    List<ZgGoods> queryAll();

    @Update("update zg_ticket set version=version+1 where ticketId = #{ticketId} and version = #{version}")
    int updateLock(Map map);

    @Select("select * from zg_ticket where ticketId = #{ticketId}")
    List<ZgTicket> queryTicketById(String ticketId);

    @Update("update zg_ticket set ticketCount=ticketCount-#{ticketCount} where ticketId = #{ticketId} and ticketCount >= #{ticketCount}")
    int updateTicket(ZgTicket zgTicket);
}
