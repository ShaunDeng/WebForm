package com.shaunz.webform.web.image.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shaunz.webform.web.image.entity.Image;

@Repository
public interface ImageMapper {
    int deleteByPrimaryKey(String id);

    int insert(Image record);

    int insertSelective(Image record);

    Image selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Image record);

    int updateByPrimaryKey(Image record);
    
    List<Image> queryList();
}