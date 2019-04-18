package com.shaunz.transfersystem.web.vehicle.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shaunz.framework.web.base.BaseController;
import com.shaunz.transfersystem.web.vehicle.entity.Vehicle;
import com.shaunz.transfersystem.web.vehicle.service.VehicleService;

@Controller
public class VehiclePageController extends BaseController{
	@Autowired
	VehicleService vehicleService;
	
	@RequestMapping(value="/vehicle/vehicle_lst.html",method=RequestMethod.GET)
	public String lstPage(){
		return "vehicle/vehicle_lst";
	}
	@RequestMapping(value="/vehicle/vehicle_add.html",method=RequestMethod.GET)
	public String addPage(){
		return "vehicle/vehicle_add";
	}
	@RequestMapping(value="/vehicle/vehicle_edit.html",method=RequestMethod.GET)
	public ModelAndView editPage(String id){
		Map<String, Object> result = new HashMap<String, Object>();
		Vehicle vehicle = vehicleService.selectByPrimaryKey(id);
		result.put("vehicle", vehicle);
		return new ModelAndView("vehicle/vehicle_edit",result);
	}
}
