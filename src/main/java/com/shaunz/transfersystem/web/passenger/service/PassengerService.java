package com.shaunz.transfersystem.web.passenger.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shaunz.framework.web.base.BaseService;
import com.shaunz.transfersystem.web.passenger.dao.PassengerMapper;
import com.shaunz.transfersystem.web.passenger.entity.Passenger;

@Service
public class PassengerService extends BaseService{
	@Autowired
	PassengerMapper passengerMapper;
	public List<Passenger> queryList(){
		return passengerMapper.queryList();
	}

	public Passenger selectByPrimaryKey(String id) {
		return passengerMapper.selectByPrimaryKey(id);
	}

	public boolean insertSelective(Passenger passenger) {
		return passengerMapper.insertSelective(passenger) == 1;
	}

	public boolean updateByPrimaryKeySelective(Passenger passenger) {
		return passengerMapper.updateByPrimaryKeySelective(passenger) == 1;
	}

	public boolean deleteByPrimaryKey(String id) {
		return passengerMapper.deleteByPrimaryKey(id) == 1;
	}
	
	public boolean closePassenger(Passenger passenger){
		passenger.setCloseFlg("Y");
		return updateByPrimaryKeySelective(passenger);
	}
}
