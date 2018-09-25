package com.shaunz.webform.web.navigationbar.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shaunz.webform.web.navigationbar.entity.NavigationBar;

@Repository
public interface NavigationBarMapper {
	public int deleteByPrimaryKey(String id);

	public int insert(NavigationBar record);

	public int insertSelective(NavigationBar record);

	public NavigationBar selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(NavigationBar record);

	public int updateByPrimaryKey(NavigationBar record);
    
	public List<NavigationBar> queryAllNavigationBar();
	
	public List<NavigationBar> queryList();
}