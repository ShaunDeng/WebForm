package com.shaunz.webform.web.blog.dao;

import java.util.List;
import java.util.Map;

import com.shaunz.webform.web.blog.entity.Blog;

public interface BlogMapper {
    int deleteByPrimaryKey(String id);

    int insert(Blog record);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);
    
    List<Blog> selectByPageId(Map<String, String> map);
    
    List<Blog> queryList();
    
    List<Blog> queryAll();
}