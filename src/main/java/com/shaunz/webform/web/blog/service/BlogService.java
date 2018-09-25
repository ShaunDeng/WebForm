package com.shaunz.webform.web.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shaunz.framework.web.base.BaseService;
import com.shaunz.webform.web.blog.dao.BlogMapper;
import com.shaunz.webform.web.blog.entity.Blog;

@Service
public class BlogService extends BaseService{

	@Autowired
	BlogMapper blogMapper;
	
	public List<Blog> queryAll(){
		return blogMapper.queryAll();
	}
	
	public List<Blog> queryList(){
		return blogMapper.queryList();
	}

	public Blog selectByPrimaryKey(String id) {
		return blogMapper.selectByPrimaryKey(id);
	}

	public boolean insertSelective(Blog blog) {
		return blogMapper.insertSelective(blog) == 1;
	}

	public boolean updateByPrimaryKeySelective(Blog blog) {
		return blogMapper.updateByPrimaryKeySelective(blog) == 1;
	}

	public boolean deleteByPrimaryKey(String id) {
		return blogMapper.deleteByPrimaryKey(id) == 1;
	}
	
	public boolean close(Blog blog){
		blog.setCloseFlg("Y");
		return updateByPrimaryKeySelective(blog);
	}


}
