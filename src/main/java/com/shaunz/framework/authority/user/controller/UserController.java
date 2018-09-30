package com.shaunz.framework.authority.user.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shaunz.framework.authority.user.entity.User;
import com.shaunz.framework.authority.user.service.UserService;
import com.shaunz.framework.common.SequenceGenerator;
import com.shaunz.framework.common.auditlogs.ShaunzAuditLog;
import com.shaunz.framework.common.utils.IStringUtil;
import com.shaunz.framework.core.YgdrasilConst;
import com.shaunz.framework.web.base.BaseController;

@RestController
public class UserController extends BaseController{
	@Autowired
	private UserService userService;
	@Autowired
	private SequenceGenerator sequenceGenerator;
	
	@GetMapping("/user/userNmExistCk")
	public boolean existUser(String loginName){
		User user = userService.findUserByNm(loginName);
		return (user == null || !user.isAvaliableData());
	}
	
	@GetMapping("/user/userEmailExistCk")
	public boolean existUserEmail(String email){
		User user = userService.findUserByEmail(email);
		return (user == null || !user.isAvaliableData());
	}
	
	@RequiresPermissions("2.query")
	@GetMapping("/user")
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
	@PostMapping("/user")
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
				user.encryptSensitiveMsg();
				flag = userService.addNewUser(user);
				return formSubmitResult(flag, "common.addMsg", new Object[]{messageSource.getMessage("user.title", null, locale),user.getLoginName()}
				,locale);
			}
		}
		
		return convertToJsonString(results);
	}
	
	@RequiresPermissions("2.update")
	@PutMapping("/user")
	@ShaunzAuditLog(optType="update",functionId="2")
	public String updateUser(User user,Locale locale){
		user.setLoginName(null);
		user.setEmail(null);
		if(IStringUtil.isBlank(user.getPassword()) && user.getPassword().contains(YgdrasilConst.PWD_PLACEHOLDER)){
			user.setPassword(null);
		} else {
			user.encryptSensitiveMsg();
		}
		boolean flag = userService.updateUserByPrimaryKeySelective(user);
		user.setOptFlag(flag);
		return formSubmitResult(flag, "common.updateMsg", new Object[]{messageSource.getMessage("user.title", null, locale),user.getLoginName()}
			, locale);
	}
	
	@RequiresPermissions("2.delete")
	@DeleteMapping("/user/{id}")
	@ShaunzAuditLog(optType="delete",functionId="2")
	public String deleteUser(@PathVariable("id") String id,Locale locale){
		User user = userService.selectByPrimaryKey(id);
		boolean flag = userService.closeUser(user);
		return formSubmitResult(flag, "common.deleteMsg", new Object[]{messageSource.getMessage("user.title", null, locale),user.getLoginName()}
				, locale);
	}
	
	@RequiresPermissions("2.update")
	@RequestMapping(value="/user/grant")
	@ShaunzAuditLog(optType="grant",functionId="2")
	public String grantRole(String id,String roleIds,Locale locale){
		User user = userService.selectByPrimaryKey(id);
		String[] roleIdArr = roleIds.split(",");
		boolean flag = userService.roleGrant(roleIdArr, id);
		return formSubmitResult(flag, "common.grantMsg", new Object[]{messageSource.getMessage("user.title", null, locale),user.getLoginName()}
		, locale);
	}
}
