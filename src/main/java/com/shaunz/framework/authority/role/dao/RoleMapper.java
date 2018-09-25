package com.shaunz.framework.authority.role.dao;

import java.util.List;
import java.util.Map;

import com.shaunz.framework.authority.role.entity.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    List<Role> selectByUserId(String usrId);
    
    List<Role> queryList(Role role);
    
    List<Map<String, Object>> getAuthorityBy(String roleId);
    
    int insertRoleFunctionAuthorityMapSelective(Map<String, Object> map);
    
    int deleteRoleFunctionAuthorityMapBy(String roleId);
}