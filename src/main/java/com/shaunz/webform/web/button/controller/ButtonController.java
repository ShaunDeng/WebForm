package com.shaunz.webform.web.button.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shaunz.framework.common.SequenceGenerator;
import com.shaunz.framework.common.auditlogs.ShaunzAuditLog;
import com.shaunz.framework.web.base.BaseController;
import com.shaunz.webform.web.button.ButtonService;
import com.shaunz.webform.web.button.entity.Button;
import com.shaunz.webform.web.carousel.service.CarouselService;
import com.shaunz.webform.web.home.entity.HomePage;
import com.shaunz.webform.web.marketinfo.service.MarketInfoService;

@Controller
public class ButtonController extends BaseController{
	@Autowired
	SequenceGenerator sequenceGenerator;
	@Autowired
	ButtonService buttonService;
	@Autowired
	CarouselService carouselService;
	@Autowired
	MarketInfoService marketInfoService;
	
	@RequestMapping(value="/button/button_lst.html",method=RequestMethod.GET)
	public String lstPage(){
		return "button/button_lst";
	}
	@RequestMapping(value="/button/button_add.html",method=RequestMethod.GET)
	public String addPage(){
		return "button/button_add";
	}
	@RequestMapping(value="/button/button_edit.html",method=RequestMethod.GET)
	public ModelAndView editPage(String id){
		Map<String, Object> result = new HashMap<String, Object>();
		Button button = buttonService.selectByPrimaryKey(id);
		result.put("button", button);
		return new ModelAndView("button/button_edit",result);
	}
	
	@RequiresPermissions("9.query")
	@RequestMapping(value="/button",method=RequestMethod.GET)
	@ResponseBody
	public String lst(){
		List<Button> buttons = buttonService.queryList();
		return convertToJsonString(buttons);
	}
	
	@RequiresPermissions("9.add")
	@RequestMapping(value="/button",method=RequestMethod.POST)
	@ResponseBody
	@ShaunzAuditLog(optType="add",functionId="9")
	public String add(Button button,BindingResult bindingResult,Locale locale){
		Map<String, String> results = new HashMap<String, String>();
		if(bindingResult.hasErrors()){
			results.put("result", "error");
			results.put("message", messageSource.getMessage("validation.failed",null,locale));
		} else {
			button.setId(""+sequenceGenerator.getNextMngmtSequenceNo());
			button.setCloseFlg("N");
			boolean flag = buttonService.insertSelective(button);
			button.setOptFlag(flag);
			if(flag){
				refreshHomepage();
			}
			return formSubmitResult(flag, "common.addMsg", new Object[]{messageSource.getMessage("button.title", null, locale),button.getName()}
					, locale);
		}
		return convertToJsonString(results);
	
	}
	
	@RequiresPermissions("9.update")
	@RequestMapping(value="/button",method=RequestMethod.PUT)
	@ResponseBody
	@ShaunzAuditLog(optType="update",functionId="9")
	public String edit(Button button,Locale locale){
		boolean flag = buttonService.updateByPrimaryKeySelective(button);
		button.setOptFlag(flag);
		if(flag){
			refreshHomepage();
		}
		return formSubmitResult(flag, "common.updateMsg", new Object[]{messageSource.getMessage("button.title", null, locale),button.getName()}
		, locale);
	}
	
	@RequiresPermissions("9.delete")
	@RequestMapping(value="/button/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	@ShaunzAuditLog(optType="delete",functionId="9")
	public String delete(@PathVariable("id") String id,Locale locale){
		Button button = buttonService.selectByPrimaryKey(id);
		boolean flag = buttonService.close(button);
		if(flag){
			refreshHomepage();
		}
		return formSubmitResult(flag, "common.deleteMsg", new Object[]{messageSource.getMessage("button.title", null, locale),button.getName()}
		, locale);
	}
	
	private void refreshHomepage(){
		HomePage homePage = null;
		try {
			homePage = (HomePage)request.getServletContext().getAttribute("homePageObject");
			homePage.setCarousels(carouselService.quaryAll());
			homePage.setMarketInfos(marketInfoService.quaryAll());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
