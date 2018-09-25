package com.shaunz.webform.web.dropdownlist.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shaunz.framework.web.base.BaseService;
import com.shaunz.webform.web.dropdownlist.dao.DropDownListMapper;
import com.shaunz.webform.web.dropdownlist.entity.DropDownList;

@Service
public class DropDownListService extends BaseService{

	@Autowired
	DropDownListMapper dropDownListMapper;
	
	public List<DropDownList> queryAll(){
		return dropDownListMapper.queryAll();
	}
	
	public List<DropDownList> queryList(){
		return dropDownListMapper.queryList();
	}

	public DropDownList selectByPrimaryKey(String id) {
		return dropDownListMapper.selectByPrimaryKey(id);
	}

	public boolean insertSelective(DropDownList dropDownList) {
		return dropDownListMapper.insertSelective(dropDownList) == 1;
	}

	public boolean updateByPrimaryKeySelective(DropDownList dropDownList) {
		return dropDownListMapper.updateByPrimaryKeySelective(dropDownList) == 1;
	}

	public boolean deleteByPrimaryKey(String id) {
		return dropDownListMapper.deleteByPrimaryKey(id) == 1;
	}
	
	public boolean close(DropDownList dropDownList){
		dropDownList.setCloseFlg("Y");
		return updateByPrimaryKeySelective(dropDownList);
	}

}
