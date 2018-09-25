package com.shaunz.framework.authority.user.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.shaunz.framework.authority.user.entity.User;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User selectByUserNm(String name);
    
    User selectByEmail(String email);
    
    List<User> queryAll();
    
    List<User> queryLst(User user);
    
    List<Map<String, Object>> getUsrRoleMapBy(String userId);
    
    int insertUsrRoleMapSelective(Map<String, Object> usrRoleMap);
    
    int deleteUsrRoleMapBy(String userId);
}