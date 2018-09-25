package com.shaunz.framework.authority.function.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.shaunz.framework.authority.function.entity.Function;

public interface FunctionMapper {
    int deleteByPrimaryKey(String id);

    int insert(Function record);

    int insertSelective(Function record);

    Function selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Function record);

    int updateByPrimaryKey(Function record);
    
    List<Function> queryAll();
    
    List<Function> queryAllAuthorizedFunctionByUsrId(String usrId);
    
    Map<String, Object> findObjBy(@Param("tableNm")String tableNm,String objId);
    
    List<Map<String, Object>> queryObjLstBy(@Param("tableNm")String tableNm);
}