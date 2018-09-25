package com.shaunz.webform.web.marketinfo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shaunz.webform.web.marketinfo.entity.MarketInfo;

@Repository
public interface MarketInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(MarketInfo record);

    int insertSelective(MarketInfo record);

    MarketInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MarketInfo record);

    int updateByPrimaryKey(MarketInfo record);
    
    List<MarketInfo> quaryAll();
    
    List<MarketInfo> queryList();
}