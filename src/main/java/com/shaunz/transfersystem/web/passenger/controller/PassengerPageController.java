package com.shaunz.transfersystem.web.passenger.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shaunz.framework.web.base.BaseController;
import com.shaunz.transfersystem.web.passenger.entity.Passenger;
import com.shaunz.transfersystem.web.passenger.service.PassengerService;

@Controller
public class PassengerPageController extends BaseController{
	@Autowired
	PassengerService passengerService;
	
	@RequestMapping(value="/passenger/passenger_lst.html",method=RequestMethod.GET)
	public String lstPage(){
		return "passenger/passenger_lst";
	}
	@RequestMapping(value="/passenger/passenger_add.html",method=RequestMethod.GET)
	public String addPage(){
		return "passenger/passenger_add";
	}
	@RequestMapping(value="/passenger/passenger_edit.html",method=RequestMethod.GET)
	public ModelAndView editPage(String id){
		Map<String, Object> result = new HashMap<String, Object>();
		Passenger passenger = passengerService.selectByPrimaryKey(id);
		passenger.dateConverter();
		result.put("passenger", passenger);
		return new ModelAndView("passenger/passenger_edit",result);
	}
}
