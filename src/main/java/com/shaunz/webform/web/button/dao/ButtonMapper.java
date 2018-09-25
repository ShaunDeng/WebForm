package com.shaunz.webform.web.button.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shaunz.webform.web.button.entity.Button;

@Repository
public interface ButtonMapper {
    int deleteByPrimaryKey(String id);

    int insert(Button record);

    int insertSelective(Button record);

    Button selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Button record);

    int updateByPrimaryKey(Button record);
    
    List<Button> queryList();
}