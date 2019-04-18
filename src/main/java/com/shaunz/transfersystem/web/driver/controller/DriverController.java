package com.shaunz.transfersystem.web.driver.controller;

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
import com.shaunz.transfersystem.web.driver.entity.Driver;
import com.shaunz.transfersystem.web.driver.service.DriverService;

@RestController
public class DriverController extends BaseController{
	@Autowired
	SequenceGenerator sequenceGenerator;
	@Autowired
	DriverService driverService;
	
	@RequiresPermissions("24.query")
	@GetMapping("/driver")
	public String authorLst(){
		List<Driver> drivers = driverService.queryList();
		return convertToJsonString(drivers);
	}
	
	@RequiresPermissions("24.add")
	@PostMapping("/driver")
	@ShaunzAuditLog(optType="add",functionId="24")
	public String authorAdd(Driver driver,BindingResult bindingResult,Locale locale){
		Map<String, String> results = new HashMap<String, String>();
		if(bindingResult.hasErrors()){
			results.put("result", "error");
			results.put("message", messageSource.getMessage("validation.failed",null,locale));
		} else {
			driver.setId(""+sequenceGenerator.getNextTransferSequenceNo());
			driver.setCloseFlg("N");
			boolean flag = driverService.insertSelective(driver);
			driver.setOptFlag(flag);
			return formSubmitResult(flag, "common.addMsg", new Object[]{messageSource.getMessage("driver.title", null, locale),driver.getName()}
					, locale);
		}
		return convertToJsonString(results);
	
	}
	
	@RequiresPermissions("24.update")
	@PutMapping("/driver")
	@ShaunzAuditLog(optType="update",functionId="24")
	public String authorEdit(Driver driver,Locale locale){
		boolean flag = driverService.updateByPrimaryKeySelective(driver);
		driver.setOptFlag(flag);
		return formSubmitResult(flag, "common.updateMsg", new Object[]{messageSource.getMessage("driver.title", null, locale),driver.getName()}
		, locale);
	}
	
	@RequiresPermissions("24.delete")
	@DeleteMapping("/driver")
	@ShaunzAuditLog(optType="delete",functionId="24")
	public String authorDelete(@PathVariable("id") String id,Locale locale){
		Driver driver = driverService.selectByPrimaryKey(id);
		boolean flag = driverService.closeAuthor(driver);
		return formSubmitResult(flag, "common.deleteMsg", new Object[]{messageSource.getMessage("driver.title", null, locale),driver.getName()}
		, locale);
	}
}
