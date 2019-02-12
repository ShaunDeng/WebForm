package com.shaunz.transfersystem.web.passenger.dao;

import com.shaunz.transfersystem.web.passenger.entity.Passenger;

public interface PassengerMapper {
    int deleteByPrimaryKey(String id);

    int insert(Passenger record);

    int insertSelective(Passenger record);

    Passenger selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Passenger record);

    int updateByPrimaryKey(Passenger record);
}