package com.shaunz.webform.web.dropdownlist.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shaunz.webform.web.dropdownlist.entity.DropDownList;

@Repository
public interface DropDownListMapper {
    int deleteByPrimaryKey(String id);

    int insert(DropDownList record);

    int insertSelective(DropDownList record);

    DropDownList selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DropDownList record);

    int updateByPrimaryKey(DropDownList record);
    
    List<DropDownList> queryList();
    
    List<DropDownList> queryAll();
}