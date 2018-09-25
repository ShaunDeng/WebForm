package com.shaunz.webform.web.navigationbar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shaunz.framework.web.base.BaseService;
import com.shaunz.webform.web.navigationbar.dao.NavigationBarMapper;
import com.shaunz.webform.web.navigationbar.entity.NavigationBar;

@Service
public class NavigationBarService extends BaseService{
	@Autowired
	NavigationBarMapper navigationBarMapper;
	
	public List<NavigationBar> queryAllNavigationBar(){
		return navigationBarMapper.queryAllNavigationBar();
	}
	
	public List<NavigationBar> queryList(){
		return navigationBarMapper.queryList();
	}

	public NavigationBar selectByPrimaryKey(String id) {
		return navigationBarMapper.selectByPrimaryKey(id);
	}

	public boolean insertSelective(NavigationBar navigationBar) {
		return navigationBarMapper.insertSelective(navigationBar) == 1;
	}

	public boolean updateByPrimaryKeySelective(NavigationBar navigationBar) {
		return navigationBarMapper.updateByPrimaryKeySelective(navigationBar) == 1;
	}

	public boolean deleteByPrimaryKey(String id) {
		return navigationBarMapper.deleteByPrimaryKey(id) == 1;
	}
	
	public boolean closeAuthor(NavigationBar navigationBar){
		navigationBar.setCloseFlg("Y");
		return updateByPrimaryKeySelective(navigationBar);
	}
}
