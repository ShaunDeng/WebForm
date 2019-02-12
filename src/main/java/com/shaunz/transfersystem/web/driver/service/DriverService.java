package com.shaunz.transfersystem.web.driver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shaunz.framework.web.base.BaseService;
import com.shaunz.transfersystem.web.driver.dao.DriverMapper;
import com.shaunz.transfersystem.web.driver.entity.Driver;

@Service
public class DriverService extends BaseService{
	@Autowired
	DriverMapper driverMapper;
	
	public List<Driver> queryList(){
		return driverMapper.queryList();
	}

	public Driver selectByPrimaryKey(String id) {
		return driverMapper.selectByPrimaryKey(id);
	}

	public boolean insertSelective(Driver author) {
		return driverMapper.insertSelective(author) == 1;
	}

	public boolean updateByPrimaryKeySelective(Driver author) {
		return driverMapper.updateByPrimaryKeySelective(author) == 1;
	}

	public boolean deleteByPrimaryKey(String id) {
		return driverMapper.deleteByPrimaryKey(id) == 1;
	}
	
	public boolean closeAuthor(Driver author){
		author.setCloseFlg("Y");
		return updateByPrimaryKeySelective(author);
	}
}
