package com.shaunz.framework.authority.authority.dao;

import java.util.List;

import com.shaunz.framework.authority.authority.entity.Authority;

public interface AuthorityMapper {
    int deleteByPrimaryKey(String id);

    int insert(Authority record);

    int insertSelective(Authority record);

    Authority selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Authority record);

    int updateByPrimaryKey(Authority record);
    
    List<String> findFunctionPermissionByUsrId(String usrId);
}