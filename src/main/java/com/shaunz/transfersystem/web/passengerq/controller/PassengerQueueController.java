package com.shaunz.transfersystem.web.passengerq.controller;

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
import com.shaunz.transfersystem.web.passengerq.entity.PassengerQueue;
import com.shaunz.transfersystem.web.passengerq.service.PassengerQueueService;

@RestController
public class PassengerQueueController extends BaseController{
	@Autowired
	SequenceGenerator sequenceGenerator;
	@Autowired
	PassengerQueueService passengerQueueService;
	
	@RequiresPermissions("26.query")
	@GetMapping("/passengerQueue")
	public String lst(){
		List<PassengerQueue> passengerQueues = passengerQueueService.queryList();
		return convertToJsonString(passengerQueues);
	}
	
	@RequiresPermissions("26.add")
	@PostMapping("/passengerQueue")
	@ShaunzAuditLog(optType="add",functionId="26")
	public String add(PassengerQueue passengerQueue,BindingResult bindingResult,Locale locale){
		Map<String, String> results = new HashMap<String, String>();
		if(bindingResult.hasErrors()){
			results.put("result", "error");
			results.put("message", messageSource.getMessage("validation.failed",null,locale));
		} else {
			passengerQueue.setId(""+sequenceGenerator.getNextTransferSequenceNo());
			passengerQueue.setCloseFlg("N");
			boolean flag = passengerQueueService.insertSelective(passengerQueue);
			passengerQueue.setOptFlag(flag);
			return formSubmitResult(flag, "common.addMsg", new Object[]{messageSource.getMessage("passengerQueue.title", null, locale),passengerQueue.getDestination()}
					, locale);
		}
		return convertToJsonString(results);
	
	}
	
	@RequiresPermissions("26.update")
	@PutMapping("/passengerQueue")
	@ShaunzAuditLog(optType="update",functionId="26")
	public String edit(PassengerQueue passengerQueue,Locale locale){
		boolean flag = passengerQueueService.updateByPrimaryKeySelective(passengerQueue);
		passengerQueue.setOptFlag(flag);
		return formSubmitResult(flag, "common.updateMsg", new Object[]{messageSource.getMessage("passengerQueue.title", null, locale),passengerQueue.getDestination()}
		, locale);
	}
	
	@RequiresPermissions("26.delete")
	@DeleteMapping("/passengerQueue/{id}")
	@ShaunzAuditLog(optType="delete",functionId="26")
	public String delete(@PathVariable("id") String id,Locale locale){
		PassengerQueue passengerQueue = passengerQueueService.selectByPrimaryKey(id);
		boolean flag = passengerQueueService.closePassengerQueue(passengerQueue);
		return formSubmitResult(flag, "common.deleteMsg", new Object[]{messageSource.getMessage("passengerQueue.title", null, locale),passengerQueue.getDestination()}
		, locale);
	}
}
