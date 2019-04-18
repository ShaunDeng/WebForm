package com.shaunz.transfersystem.web.passengerq.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shaunz.framework.web.base.BaseService;
import com.shaunz.transfersystem.web.passengerq.dao.PassengerQueueMapper;
import com.shaunz.transfersystem.web.passengerq.entity.PassengerQueue;

@Service
public class PassengerQueueService extends BaseService{
	@Autowired
	PassengerQueueMapper passengerQueueMapper;
	public List<PassengerQueue> queryList(){
		return passengerQueueMapper.queryList();
	}

	public PassengerQueue selectByPrimaryKey(String id) {
		return passengerQueueMapper.selectByPrimaryKey(id);
	}

	public boolean insertSelective(PassengerQueue passengerQueue) {
		return passengerQueueMapper.insertSelective(passengerQueue) == 1;
	}

	public boolean updateByPrimaryKeySelective(PassengerQueue passengerQueue) {
		return passengerQueueMapper.updateByPrimaryKeySelective(passengerQueue) == 1;
	}

	public boolean deleteByPrimaryKey(String id) {
		return passengerQueueMapper.deleteByPrimaryKey(id) == 1;
	}
	
	public boolean closePassengerQueue(PassengerQueue passengerQueue){
		passengerQueue.setCloseFlg("Y");
		return updateByPrimaryKeySelective(passengerQueue);
	}
}
