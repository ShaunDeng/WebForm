package com.shaunz.transfersystem.web.passenger.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import com.shaunz.transfersystem.web.passenger.entity.Passenger;
import com.shaunz.transfersystem.web.passenger.service.PassengerService;

@RestController
public class PassengerController extends BaseController{
	@Autowired
	SequenceGenerator sequenceGenerator;
	@Autowired
	PassengerService passengerService;
	
	@RequiresPermissions("25.query")
	@GetMapping("/passenger")
	public String lst(){
		List<Passenger> passengers = passengerService.queryList();
		return convertToJsonString(passengers);
	}
	
	@RequiresPermissions("25.add")
	@PostMapping("/passenger")
	@ShaunzAuditLog(optType="add",functionId="25")
	public String add(Passenger passenger,BindingResult bindingResult,Locale locale){
		Map<String, String> results = new HashMap<String, String>();
		if(bindingResult.hasErrors()){
			results.put("result", "error");
			results.put("message", messageSource.getMessage("validation.failed",null,locale));
		} else {
			passenger.setId(""+sequenceGenerator.getNextTransferSequenceNo());
			passenger.setCloseFlg("N");
			boolean flag = passengerService.insertSelective(passenger);
			passenger.setOptFlag(flag);
			return formSubmitResult(flag, "common.addMsg", new Object[]{messageSource.getMessage("passenger.title", null, locale),passenger.getName()}
					, locale);
		}
		return convertToJsonString(results);
	
	}
	
	@RequiresPermissions("25.update")
	@PutMapping("/passenger")
	@ShaunzAuditLog(optType="update",functionId="25")
	public String edit(Passenger passenger,Locale locale){
		boolean flag = passengerService.updateByPrimaryKeySelective(passenger);
		passenger.setOptFlag(flag);
		return formSubmitResult(flag, "common.updateMsg", new Object[]{messageSource.getMessage("passenger.title", null, locale),passenger.getName()}
		, locale);
	}
	
	@RequiresPermissions("25.delete")
	@DeleteMapping("/passenger/{id}")
	@ShaunzAuditLog(optType="delete",functionId="25")
	public String delete(@PathVariable("id") String id,Locale locale){
		Passenger passenger = passengerService.selectByPrimaryKey(id);
		boolean flag = passengerService.closePassenger(passenger);
		return formSubmitResult(flag, "common.deleteMsg", new Object[]{messageSource.getMessage("passenger.title", null, locale),passenger.getName()}
		, locale);
	}
}
