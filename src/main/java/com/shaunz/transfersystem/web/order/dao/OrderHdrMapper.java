package com.shaunz.transfersystem.web.order.dao;

import com.shaunz.transfersystem.web.order.entity.OrderHdr;

public interface OrderHdrMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrderHdr record);

    int insertSelective(OrderHdr record);

    OrderHdr selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderHdr record);

    int updateByPrimaryKey(OrderHdr record);
}