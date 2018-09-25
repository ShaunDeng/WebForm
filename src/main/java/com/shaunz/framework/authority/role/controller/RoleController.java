package com.shaunz.framework.authority.role.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shaunz.framework.authority.function.entity.Function;
import com.shaunz.framework.authority.function.service.FunctionService;
import com.shaunz.framework.authority.role.entity.Role;
import com.shaunz.framework.authority.role.service.RoleService;
import com.shaunz.framework.common.SequenceGenerator;
import com.shaunz.framework.common.auditlogs.ShaunzAuditLog;
import com.shaunz.framework.common.utils.IArrayListUtil;
import com.shaunz.framework.common.utils.IStringUtil;
import com.shaunz.framework.web.base.BaseController;

@Controller
public class RoleController extends BaseController{
	@Autowired
	private RoleService roleService;
	@Autowired
	private FunctionService functionService;
	
	@Autowired
	private SequenceGenerator sequenceGenerator;
	
	@RequiresPermissions("3.query")
	@RequestMapping(value="/role",method=RequestMethod.GET)
	@ResponseBody
	public String roleLst(Role role) {
		List<Role> roles = null;
		roles = roleService.queryList(role);
		return convertToJsonString(roles);
	}
	
	@RequiresPermissions("3.add")
	@RequestMapping(value="/role",method=RequestMethod.POST)
	@ResponseBody
	@ShaunzAuditLog(optType="add",functionId="3")
	public String roleAdd(@Valid Role role,BindingResult bindingResult,Locale locale){
		Map<String, String> results = new HashMap<String, String>();
		if(bindingResult.hasErrors()){
			results.put("result", "error");
			results.put("message", messageSource.getMessage("validation.failed",null,locale));
		} else {
			role.setId(""+sequenceGenerator.getNextMngmtSequenceNo());
			role.setCloseFlg("N");
			boolean flag = roleService.insertSelective(role);
			role.setOptFlag(flag);
			return formSubmitResult(flag, "common.addMsg", new Object[]{messageSource.getMessage("role.title", null, locale),role.getName()}
					, locale);
		}
		return convertToJsonString(results);
	}
	
	@RequiresPermissions("3.update")
	@RequestMapping(value="/role",method=RequestMethod.PUT)
	@ResponseBody
	@ShaunzAuditLog(optType="update",functionId="3")
	public String roleEdit(Role role,Locale locale){
		boolean flag = roleService.updateByPrimaryKeySelective(role);
		role.setOptFlag(flag);
		return formSubmitResult(flag, "common.updateMsg", new Object[]{messageSource.getMessage("role.title", null, locale),role.getName()}
		, locale);
	}
	
	@RequiresPermissions("3.delete")
	@RequestMapping(value="/role/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	@ShaunzAuditLog(optType="del",functionId="3")
	public String roleDelete(@PathVariable("id") String id,Locale locale){
		Role role = roleService.selectByPrimaryKey(id);
		boolean flag = roleService.closeRole(role);
		return formSubmitResult(flag, "common.deleteMsg", new Object[]{messageSource.getMessage("role.title", null, locale),role.getName()}
		, locale);
	}
	
	@RequiresPermissions("3.update")
	@RequestMapping(value="/role/grant")
	@ResponseBody
	@ShaunzAuditLog(optType="grant",functionId="3")
	public String grantAuthority(String id,HttpServletRequest request,Locale locale){
		List<Function> allFunctions = functionService.queryAllFunctions();
		List<Function> authorizedFunctions = new ArrayList<Function>();
		if(!IArrayListUtil.isBlankList(allFunctions)){
			Function function = null;
			for (int i = 0; i < allFunctions.size(); i++) {
				function = allFunctions.get(i);
				if(IStringUtil.notBlank(request.getParameter(function.getId()+"_query"))){
					function.setAuthority("4");
				}
				if(IStringUtil.notBlank(request.getParameter(function.getId()+"_add"))){
					function.setAuthority("1");
				}
				if(IStringUtil.notBlank(request.getParameter(function.getId()+"_update"))){
					function.setAuthority("3");
				}
				if(IStringUtil.notBlank(request.getParameter(function.getId()+"_delete"))){
					function.setAuthority("2");
				}
				if(!IArrayListUtil.isBlankList(function.getGrantedAuthority())){
					authorizedFunctions.add(function);
				}
			}
		}
		Role role = roleService.selectByPrimaryKey(id);
		boolean flag = roleService.authorityGrant(id, authorizedFunctions);
		return formSubmitResult(flag, "common.grantMsg", new Object[]{messageSource.getMessage("role.title", null, locale),role.getName()}
		, locale);
	}
}
