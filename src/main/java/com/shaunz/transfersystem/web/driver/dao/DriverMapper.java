package com.shaunz.transfersystem.web.driver.dao;

import java.util.List;

import com.shaunz.transfersystem.web.driver.entity.Driver;

public interface DriverMapper {
    int deleteByPrimaryKey(String id);

    int insert(Driver record);

    int insertSelective(Driver record);

    Driver selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Driver record);

    int updateByPrimaryKey(Driver record);
    
    List<Driver> queryList();
}