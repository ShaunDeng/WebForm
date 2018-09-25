package com.shaunz.framework.authority.signin.controller;

import java.util.Locale;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shaunz.framework.authority.user.entity.User;
import com.shaunz.framework.authority.user.service.UserService;
import com.shaunz.framework.common.utils.IStringUtil;
import com.shaunz.framework.web.base.BaseController;
import com.shaunz.webform.web.home.entity.HomePage;

@Controller
public class SignInController extends BaseController{
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/signIn.html",method=RequestMethod.GET)
	public String signInPage(){
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			return redirct2MngmtPlant();
		}
		return "SignIn";
	}
	
	@RequestMapping(value="/signUp.html",method=RequestMethod.GET)
	public String signUpPage(){
		return "SignUp";
	}
	
	@RequestMapping(value="/signIn",method=RequestMethod.POST)
	public String doSignIn(User user,Model model,Locale locale){
		String msg = "";
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getInputUserNM(), user.getInputPwd());
		token.setRememberMe(IStringUtil.notBlank(user.getRememberMe()));
		try {
			subject.login(token);
			if(subject.isAuthenticated()){
				user = userService.findUser(user.getInputUserNM());
				clearAttemptSignTimes(user);
				session.setAttribute("user", user);
				SavedRequest savedRequest = WebUtils.getSavedRequest(request);
				if(savedRequest == null || savedRequest.getRequestUrl() == null){
					return redirct2MngmtPlant();
				} else {
					session.setAttribute("signIn", "success");
					return FORWARD_TO + filterProjectNmFromURL(savedRequest.getRequestUrl());
				}
			} else {
				return "SignIn";
			}
		} catch (IncorrectCredentialsException e) {
			msg = messageSource.getMessage("signin.pwdIncorrect", new Object[]{user.getInputUserNM()},locale);
            model.addAttribute("errMsg", msg);
            addSignInFailedTimes(user);
        } catch (ExcessiveAttemptsException e) {
        	msg = messageSource.getMessage("signin.signinTimesLimit", null,locale);
            model.addAttribute("errMsg", msg);
        } catch (LockedAccountException e) {
        	msg = messageSource.getMessage("signin.accountLocked", new Object[]{user.getInputUserNM()},locale);
            model.addAttribute("errMsg", msg);
            addSignInFailedTimes(user);
        } catch (DisabledAccountException e) {
        	msg = messageSource.getMessage("signin.accountDisabled", new Object[]{user.getInputUserNM()},locale);
            model.addAttribute("errMsg", msg);
            addSignInFailedTimes(user);
        } catch (ExpiredCredentialsException e) {
        	msg = messageSource.getMessage("signin.accountExpired", new Object[]{user.getInputUserNM()},locale);
            model.addAttribute("errMsg", msg);
            addSignInFailedTimes(user);
        } catch (UnknownAccountException e) {
        	msg = messageSource.getMessage("signin.unkownAccount", new Object[]{user.getInputUserNM()},locale);
            model.addAttribute("errMsg", msg);
        } catch (UnauthorizedException e) {
        	msg = messageSource.getMessage("signin.unauthorized", null,locale);
            model.addAttribute("errMsg", msg);
        } catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
        	Locale currentLocale = LocaleContextHolder.getLocale();
        	model.addAttribute("locale", currentLocale);
		}
		return "SignIn";
	}
	
	@RequestMapping(value="/signout")
	public String signOut(){
		SecurityUtils.getSubject().logout();
		return REDIRECT_TO + "index.html";
	}
	
	@RequestMapping(value="/signCheck",method=RequestMethod.GET)
	@ResponseBody
	public String signCheck(){
		User user = null;
		if(SecurityUtils.getSubject() != null && SecurityUtils.getSubject().isAuthenticated()){
			user = (User)session.getAttribute("user");
			if(user != null){
				try {
					user = (User) user.clone();
					user.deSensitive();
				} catch (CloneNotSupportedException e) {
					logger.error(e.getMessage());
				}
			}
		}
		return convertToJsonString(user);
	}
	
	private void addSignInFailedTimes(User user){
		user = userService.findUser(user.getInputUserNM());
		User updateUsr = new User();
		updateUsr.setId(user.getId());
		updateUsr.setAttemptSignTimes(user.getAttemptSignTimes());
		updateUsr.attmptSignTimesPlusOne();
		boolean flag = userService.updateUserByPrimaryKeySelective(updateUsr);
		if(!flag)
			logger.warn("Sign failed! and collect attempt times failed.");
	}
	
	private void clearAttemptSignTimes(User user){
		User updateUsr = new User();
		updateUsr.setId(user.getId());
		updateUsr.setAttemptSignTimes("0");
		user.setAttemptSignTimes("0");
		boolean flag = userService.updateUserByPrimaryKeySelective(updateUsr);
		if(!flag)
			logger.warn("Sign success! but clear attempt times failed.");
	}
	
	private String redirct2MngmtPlant(){
		return REDIRECT_TO + "managePlant.html";
	}
	
	private String filterProjectNmFromURL(String url){
		HomePage homePageObj = (HomePage)request.getServletContext().getAttribute("homePageObject");
		String projectNm = homePageObj.getProjectNm().toUpperCase();
		String tmpUrl = url.toUpperCase();
		if(tmpUrl.contains(projectNm)){
			url = url.substring(tmpUrl.indexOf(projectNm) + projectNm.length());
		}
		return url;
	}
}
