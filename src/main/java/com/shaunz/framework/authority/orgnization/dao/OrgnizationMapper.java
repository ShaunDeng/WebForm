package com.shaunz.framework.authority.orgnization.dao;

import com.shaunz.framework.authority.orgnization.entity.Orgnization;

public interface OrgnizationMapper {
    int deleteByPrimaryKey(String id);

    int insert(Orgnization record);

    int insertSelective(Orgnization record);

    Orgnization selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Orgnization record);

    int updateByPrimaryKey(Orgnization record);
}