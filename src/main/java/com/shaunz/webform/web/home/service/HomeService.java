package com.shaunz.webform.web.home.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shaunz.framework.web.base.BaseService;
import com.shaunz.webform.web.blog.dao.BlogMapper;
import com.shaunz.webform.web.blog.entity.Blog;

@Service
public class HomeService extends BaseService{
	@Resource
	private BlogMapper blogMapper;
	
	public List<Blog> selectByPageId(String pageType,String pageId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("pageType", pageType);
		map.put("pageId", pageId);
		return blogMapper.selectByPageId(map);
	}
}
