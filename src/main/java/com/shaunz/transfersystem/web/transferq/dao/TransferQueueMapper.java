package com.shaunz.transfersystem.web.transferq.dao;

import com.shaunz.transfersystem.web.transferq.entity.TransferQueue;

public interface TransferQueueMapper {
    int deleteByPrimaryKey(String id);

    int insert(TransferQueue record);

    int insertSelective(TransferQueue record);

    TransferQueue selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TransferQueue record);

    int updateByPrimaryKey(TransferQueue record);
}