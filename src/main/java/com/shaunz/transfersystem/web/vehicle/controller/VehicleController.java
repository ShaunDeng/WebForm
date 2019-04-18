package com.shaunz.transfersystem.web.vehicle.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shaunz.framework.common.SequenceGenerator;
import com.shaunz.framework.common.auditlogs.ShaunzAuditLog;
import com.shaunz.framework.web.base.BaseController;
import com.shaunz.transfersystem.web.vehicle.entity.Vehicle;
import com.shaunz.transfersystem.web.vehicle.service.VehicleService;

@RestController
public class VehicleController extends BaseController{
	@Autowired
	SequenceGenerator sequenceGenerator;
	@Autowired
	VehicleService vehicleService;

	@RequiresPermissions("23.query")
	@GetMapping("/vehicle")
	public String lst(){
		List<Vehicle> vehicles = vehicleService.queryList();
		return convertToJsonString(vehicles);
	}
	
	@RequiresPermissions("23.add")
	@PostMapping("/vehicle")
	@ShaunzAuditLog(optType="add",functionId="23")
	public String add(Vehicle vehicle,BindingResult bindingResult,Locale locale){
		Map<String, String> results = new HashMap<String, String>();
		if(bindingResult.hasErrors()){
			results.put("result", "error");
			results.put("message", messageSource.getMessage("validation.failed",null,locale));
		} else {
			vehicle.setId(""+sequenceGenerator.getNextTransferSequenceNo());
			vehicle.setCloseFlg("N");
			boolean flag = vehicleService.insertSelective(vehicle);
			vehicle.setOptFlag(flag);
			return formSubmitResult(flag, "common.addMsg", new Object[]{messageSource.getMessage("vehicle.title", null, locale),vehicle.getBrand()}
					, locale);
		}
		return convertToJsonString(results);
	
	}
	
	@RequiresPermissions("23.update")
	@PutMapping("/vehicle")
	@ShaunzAuditLog(optType="update",functionId="23")
	public String edit(Vehicle vehicle,Locale locale){
		boolean flag = vehicleService.updateByPrimaryKeySelective(vehicle);
		vehicle.setOptFlag(flag);
		return formSubmitResult(flag, "common.updateMsg", new Object[]{messageSource.getMessage("vehicle.title", null, locale),vehicle.getBrand()}
		, locale);
	}
	
	@RequiresPermissions("23.delete")
	@DeleteMapping("/vehicle/{id}")
	@ShaunzAuditLog(optType="delete",functionId="23")
	public String delete(@PathVariable("id") String id,Locale locale){
		Vehicle vehicle = vehicleService.selectByPrimaryKey(id);
		boolean flag = vehicleService.closeVehicle(vehicle);
		return formSubmitResult(flag, "common.deleteMsg", new Object[]{messageSource.getMessage("vehicle.title", null, locale),vehicle.getBrand()}
		, locale);
	}
}
