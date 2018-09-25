package com.shaunz.webform.web.blogmap.dao;

import java.util.List;

import com.shaunz.webform.web.blogmap.entity.BlogMap;

public interface BlogMapMapper {
    int insert(BlogMap record);

    int insertSelective(BlogMap record);
    
    List<BlogMap> queryList(BlogMap record);
    
    int delete(BlogMap record);
    
    int updateOrderByPageNBlog(BlogMap record);
}