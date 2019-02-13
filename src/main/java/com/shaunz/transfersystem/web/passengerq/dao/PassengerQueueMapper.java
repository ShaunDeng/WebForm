package com.shaunz.transfersystem.web.passengerq.dao;

import java.util.List;

import com.shaunz.transfersystem.web.passengerq.entity.PassengerQueue;

public interface PassengerQueueMapper {
    int deleteByPrimaryKey(String id);

    int insert(PassengerQueue record);

    int insertSelective(PassengerQueue record);

    PassengerQueue selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PassengerQueue record);

    int updateByPrimaryKey(PassengerQueue record);
    
    List<PassengerQueue> queryList();
}