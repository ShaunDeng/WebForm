package com.shaunz.transfersystem.web.vehicle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shaunz.framework.web.base.BaseService;
import com.shaunz.transfersystem.web.vehicle.dao.VehicleMapper;
import com.shaunz.transfersystem.web.vehicle.entity.Vehicle;

@Service
public class VehicleService extends BaseService{
	@Autowired
	VehicleMapper vehicleMapper;
	
	public List<Vehicle> queryList(){
		return vehicleMapper.queryList();
	}

	public Vehicle selectByPrimaryKey(String id) {
		return vehicleMapper.selectByPrimaryKey(id);
	}

	public boolean insertSelective(Vehicle vehicle) {
		return vehicleMapper.insertSelective(vehicle) == 1;
	}

	public boolean updateByPrimaryKeySelective(Vehicle vehicle) {
		return vehicleMapper.updateByPrimaryKeySelective(vehicle) == 1;
	}

	public boolean deleteByPrimaryKey(String id) {
		return vehicleMapper.deleteByPrimaryKey(id) == 1;
	}
	
	public boolean closeVehicle(Vehicle vehicle){
		vehicle.setCloseFlg("Y");
		return updateByPrimaryKeySelective(vehicle);
	}
}
