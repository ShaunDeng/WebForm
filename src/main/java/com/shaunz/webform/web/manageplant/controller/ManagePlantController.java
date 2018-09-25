package com.shaunz.webform.web.manageplant.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shaunz.framework.authority.function.entity.Function;
import com.shaunz.framework.authority.function.service.FunctionService;
import com.shaunz.framework.authority.role.entity.Role;
import com.shaunz.framework.authority.role.service.RoleService;
import com.shaunz.framework.authority.user.entity.User;
import com.shaunz.framework.authority.user.service.UserService;
import com.shaunz.framework.common.utils.IArrayListUtil;
import com.shaunz.framework.web.base.BaseController;

@RequiresRoles("admin")
@Controller
public class ManagePlantController extends BaseController{
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	@Autowired
	FunctionService functionService;
	
	
	@RequestMapping(value="/managePlant.html",method=RequestMethod.POST)
	public String managePlantPage(){
		return "managePlantPage";
	}
	
	@RequestMapping(value="mngpages/dashboard.html",method=RequestMethod.GET)
	public String dashboardPage(){
		return "mngpages/dashboard";
	}
	
	@RequestMapping(value="mngpages/detail.html",method=RequestMethod.GET)
	public ModelAndView detailPage(String functionId,String objId){
		Map<String, String> result = new HashMap<String, String>();
		result.put("functionId", functionId);
		result.put("objId", objId);
		return new ModelAndView("mngpages/detail_page",result);
	}
	
	@RequestMapping(value="/mngpages/account_lst.html",method=RequestMethod.GET)
	public String accountLstPage(){
		return "mngpages/account_lst";
	}
	
	@RequestMapping(value="/mngpages/account_add.html",method=RequestMethod.GET)
	public String accountAddPage(){
		return "mngpages/account_add";
	}
	
	@RequestMapping(value="/mngpages/account_edit.html",method=RequestMethod.GET)
	public ModelAndView accountEditPage(String id){
		Map<String, Object> result = new HashMap<String, Object>();
		User user = userService.selectByPrimaryKey(id);
		user.dateConverter();
		result.put("user", user);
		return new ModelAndView("mngpages/account_edit", result);
	}
	
	@RequestMapping(value="/mngpages/account_grant.html",method=RequestMethod.GET)
	public ModelAndView userGrantPage(String userId){
		Map<String, Object> result = new HashMap<String, Object>();
		User user = userService.selectByPrimaryKey(userId);
		user.dateConverter();
		result.put("user", user);
		List<Role> grantedRoles = roleService.getRolesByUsrId(user.getId());
		String grantedRolesString = "";
		if(!IArrayListUtil.isBlankList(grantedRoles)){
			for (int i = 0; i < grantedRoles.size(); i++) {
				Role role = grantedRoles.get(i);
				grantedRolesString += role.getId();
				if(i < grantedRoles.size()-1){
					grantedRolesString += ",";
				}
			}
		}
		result.put("selectedRoleIds", grantedRolesString);
		return new ModelAndView("mngpages/account_grant", result);
	}
	
	@RequestMapping(value="/mngpages/role_lst.html",method=RequestMethod.GET)
	public String roleLstPage(){
		return "mngpages/role_lst";
	}
	
	@RequestMapping(value="/mngpages/role_add.html",method=RequestMethod.GET)
	public String roleAddpage(){
		return "mngpages/role_add";
	}
	
	@RequestMapping(value="/mngpages/role_edit.html",method=RequestMethod.GET)
	public ModelAndView roleEditPage(String id){
		Map<String, Object> result = new HashMap<String, Object>();
		Role role = roleService.selectByPrimaryKey(id);
		role.dateConverter();
		result.put("role", role);
		return new ModelAndView("mngpages/role_edit", result);
	}
	
	@RequestMapping(value="/mngpages/role_grant.html",method=RequestMethod.GET)
	public ModelAndView roleGrantPage(String id){
		Map<String, Object> result = new HashMap<String, Object>();
		List<Function> functions = functionService.queryAllFunctions();
		
		List<Map<String, Object>> functionAuthorityMap = roleService.getAuthorityBy(id);
		if(!IArrayListUtil.isBlankList(functionAuthorityMap)){
			Map<String, Function> functionMap = IArrayListUtil.entityLst2Map(functions);
			Map<String, Object> functionAuthority = null;
			String functionId = null;
			Function function = null;
			for (int i = 0; i < functionAuthorityMap.size(); i++) {
				functionAuthority = functionAuthorityMap.get(i);
				functionId = (String)functionAuthority.get("function_id");
				function = functionMap.get(functionId);
				function.setAuthority((String)functionAuthority.get("authority_id"));
			}
		}
		
		result.put("functions", functions);
		Role role = roleService.selectByPrimaryKey(id);
		role.dateConverter();
		result.put("role", role);
		return new ModelAndView("mngpages/role_grant", result);
	}
	
	@RequestMapping(value="/mngpages/function_lst.html",method=RequestMethod.GET)
	public String functionLstPage(){
		return "mngpages/function_lst";
	}
	
	@RequestMapping(value="/mngpages/syslog_lst.html",method=RequestMethod.GET)
	public String syslogLstPage(){
		return "mngpages/syslog_lst";
	}
}
