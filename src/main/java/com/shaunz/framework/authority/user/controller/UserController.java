package com.shaunz.framework.authority.user.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shaunz.framework.authority.user.entity.User;
import com.shaunz.framework.authority.user.service.UserService;
import com.shaunz.framework.common.SequenceGenerator;
import com.shaunz.framework.common.auditlogs.ShaunzAuditLog;
import com.shaunz.framework.common.utils.IStringUtil;
import com.shaunz.framework.web.base.BaseController;

@Controller
public class UserController extends BaseController{
	@Autowired
	private UserService userService;
	@Autowired
	private SequenceGenerator sequenceGenerator;

	@RequestMapping(value="/crrentUser",method=RequestMethod.GET)
	@ResponseBody
	public String getCurrentUsr(){
		Subject subject = SecurityUtils.getSubject();
		User usrRet = null;
		if(subject.isAuthenticated()){
			User user = (User)session.getAttribute("user");
			try {
				usrRet = (User) user.clone();
				usrRet.deSensitive();
			} catch (CloneNotSupportedException e) {
				logger.error(e.getMessage());
			}
		}
		return convertToJsonString(usrRet);
	}
	
	@RequiresPermissions("2.query")
	@RequestMapping(value="/user",method=RequestMethod.GET)
	@ResponseBody
	public String userList(User user){
		List<User> result = null;
		if(user != null && user.isAvaliableData()){
			result = userService.queryLst(user);
		} else {
			result = userService.queryAll();
		}
		return convertToJsonString(result);
	}
	
	@RequiresPermissions("2.add")
	@RequestMapping(value="/user",method=RequestMethod.POST)
	@ResponseBody
	@ShaunzAuditLog(optType="add",functionId="2")
	public String addUser(@Valid User user,BindingResult bindingResult,Locale locale){
		Map<String, String> results = new HashMap<String, String>();
		if(bindingResult.hasErrors()){
			results.put("result", "error");
			results.put("message", messageSource.getMessage("validation.failed",null,locale));
		} else {
			boolean flag = true;
			User tmpUser = userService.findUserByEmail(user.getEmail());
			if(tmpUser != null && IStringUtil.notBlank(tmpUser.getId())){
				results.put("result", "error");
				results.put("message", messageSource.getMessage("validation.existEmail",new Object[]{user.getEmail()},locale));
				flag = false;
			}
			tmpUser = userService.findUserByNm(user.getLoginName());
			if(tmpUser != null && IStringUtil.notBlank(tmpUser.getId())){
				results.put("result", "error");
				results.put("message", messageSource.getMessage("validation.existLoginNm",new Object[]{user.getLoginName()},locale));
				flag = false;
			}
			user.setOptFlag(flag);
			if(flag){
				user.setId(""+sequenceGenerator.getNextMngmtSequenceNo());
				user.setCloseFlg("N");
				flag = userService.addNewUser(user);
				return formSubmitResult(flag, "common.addMsg", new Object[]{messageSource.getMessage("user.title", null, locale),user.getLoginName()}
				,locale);
			}
		}
		
		return convertToJsonString(results);
	}
	
	@RequiresPermissions("2.update")
	@RequestMapping(value="/user",method=RequestMethod.PUT)
	@ResponseBody
	@ShaunzAuditLog(optType="update",functionId="2")
	public String updateUser(User user,Locale locale){
		boolean flag = userService.updateUserByPrimaryKeySelective(user);
		user.setOptFlag(flag);
		return formSubmitResult(flag, "common.updateMsg", new Object[]{messageSource.getMessage("user.title", null, locale),user.getLoginName()}
			, locale);
	}
	
	@RequiresPermissions("2.delete")
	@RequestMapping(value="/user/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	@ShaunzAuditLog(optType="delete",functionId="2")
	public String deleteUser(@PathVariable("id") String id,Locale locale){
		User user = userService.selectByPrimaryKey(id);
		boolean flag = userService.closeUser(user);
		return formSubmitResult(flag, "common.deleteMsg", new Object[]{messageSource.getMessage("user.title", null, locale),user.getLoginName()}
				, locale);
	}
	
	@RequiresPermissions("2.update")
	@RequestMapping(value="/user/grant")
	@ResponseBody
	@ShaunzAuditLog(optType="grant",functionId="2")
	public String grantRole(String id,String roleIds,Locale locale){
		User user = userService.selectByPrimaryKey(id);
		String[] roleIdArr = roleIds.split(",");
		boolean flag = userService.roleGrant(roleIdArr, id);
		return formSubmitResult(flag, "common.grantMsg", new Object[]{messageSource.getMessage("user.title", null, locale),user.getLoginName()}
		, locale);
	}
	
}
