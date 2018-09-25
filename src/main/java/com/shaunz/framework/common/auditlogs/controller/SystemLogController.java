package com.shaunz.framework.common.auditlogs.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shaunz.framework.common.auditlogs.entity.SystemLog;
import com.shaunz.framework.common.auditlogs.service.SystemLogService;
import com.shaunz.framework.web.base.BaseController;

@Controller
public class SystemLogController extends BaseController{
	@Autowired
	SystemLogService systemLogService;
	
	@RequiresPermissions("12.query")
	@RequestMapping(value="/sysLog",method=RequestMethod.GET)
	@ResponseBody
	public String queryList(SystemLog systemLog) {
		List<SystemLog> systemLogs = systemLogService.queryAll(systemLog);
		return convertToJsonString(systemLogs);
	}
}
