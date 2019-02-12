package com.shaunz.transfersystem.web.vehicle.dao;

import com.shaunz.transfersystem.web.vehicle.entity.Vehicle;

public interface VehicleMapper {
    int deleteByPrimaryKey(String id);

    int insert(Vehicle record);

    int insertSelective(Vehicle record);

    Vehicle selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Vehicle record);

    int updateByPrimaryKey(Vehicle record);
}