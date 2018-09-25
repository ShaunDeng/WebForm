package com.shaunz.framework.common.auditlogs.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shaunz.framework.common.auditlogs.entity.SystemLog;

@Repository
public interface SystemLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(SystemLog record);

    int insertSelective(SystemLog record);

    SystemLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SystemLog record);

    int updateByPrimaryKey(SystemLog record);
    
    List<SystemLog> queryAll();
}