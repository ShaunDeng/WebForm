package com.shaunz.transfersystem.web.driver.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shaunz.framework.web.base.BaseController;
import com.shaunz.transfersystem.web.driver.entity.Driver;
import com.shaunz.transfersystem.web.driver.service.DriverService;

@Controller
public class PageController extends BaseController{
	@Autowired
	DriverService driverService;
	
	@RequestMapping(value="/driver/driver_lst.html",method=RequestMethod.GET)
	public String authorLstPage(){
		return "driver/driver_lst";
	}
	@RequestMapping(value="/driver/driver_add.html",method=RequestMethod.GET)
	public String authorAddPage(){
		return "driver/driver_add";
	}
	@RequestMapping(value="/driver/driver_edit.html",method=RequestMethod.GET)
	public ModelAndView authorEditPage(String id){
		Map<String, Object> result = new HashMap<String, Object>();
		Driver driver = driverService.selectByPrimaryKey(id);
		result.put("driver", driver);
		return new ModelAndView("driver/driver_edit",result);
	}
}
