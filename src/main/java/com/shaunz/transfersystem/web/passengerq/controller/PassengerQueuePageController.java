package com.shaunz.transfersystem.web.passengerq.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shaunz.framework.web.base.BaseController;
import com.shaunz.transfersystem.web.passengerq.entity.PassengerQueue;
import com.shaunz.transfersystem.web.passengerq.service.PassengerQueueService;

@Controller
public class PassengerQueuePageController extends BaseController{
	@Autowired
	PassengerQueueService passengerQueueService;
	
	@RequestMapping(value="/passengerQueue/passengerQueue_lst.html",method=RequestMethod.GET)
	public String lstPage(){
		return "passengerQueue/passengerQueue_lst";
	}
	@RequestMapping(value="/passengerQueue/passengerQueue_add.html",method=RequestMethod.GET)
	public String addPage(){
		return "passengerQueue/passengerQueue_add";
	}
	@RequestMapping(value="/passengerQueue/passengerQueue_edit.html",method=RequestMethod.GET)
	public ModelAndView editPage(String id){
		Map<String, Object> result = new HashMap<String, Object>();
		PassengerQueue passengerQueue = passengerQueueService.selectByPrimaryKey(id);
		passengerQueue.dateConverter();
		result.put("passengerQueue", passengerQueue);
		return new ModelAndView("passengerQueue/passengerQueue_edit",result);
	}

}
