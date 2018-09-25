package com.shaunz.webform.web.navigationbar.controller;

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
import com.shaunz.framework.common.utils.IArrayListUtil;
import com.shaunz.framework.web.base.BaseController;
import com.shaunz.webform.web.blogmap.entity.BlogMap;
import com.shaunz.webform.web.blogmap.service.BlogMapService;
import com.shaunz.webform.web.home.entity.HomePage;
import com.shaunz.webform.web.navigationbar.entity.NavigationBar;
import com.shaunz.webform.web.navigationbar.service.NavigationBarService;

@Controller
public class NavigationBarController extends BaseController{
	private static final String PAGE_TYPE_NAVBAR = "tb_navigation_bar";
	@Autowired
	private SequenceGenerator sequenceGenerator;
	@Autowired
	private NavigationBarService navigationBarService;
	@Autowired
	private BlogMapService blogMapService;
	
	@RequestMapping(value="/navigationbar/navigationbar_lst.html",method=RequestMethod.GET)
	public String lstPage(){
		return "navigationbar/navigationbar_lst";
	}
	@RequestMapping(value="/navigationbar/navigationbar_add.html",method=RequestMethod.GET)
	public String addPage(){
		return "navigationbar/navigationbar_add";
	}
	@RequestMapping(value="/navigationbar/navigationbar_edit.html",method=RequestMethod.GET)
	public ModelAndView editPage(String id){
		Map<String, Object> result = new HashMap<String, Object>();
		NavigationBar navigationBar = navigationBarService.selectByPrimaryKey(id);
		result.put("navigationBar", navigationBar);
		return new ModelAndView("navigationbar/navigationbar_edit",result);
	}
	@RequestMapping(value="/navigationbar/navigationbar_blog.html",method=RequestMethod.GET)
	public ModelAndView blogPage(String id){
		Map<String, Object> result = new HashMap<String, Object>();
		NavigationBar navigationBar = navigationBarService.selectByPrimaryKey(id);
		result.put("navigationBar", navigationBar);
		String selectedBlogIds = "";
		BlogMap blogMap = new BlogMap(navigationBar.getId(),PAGE_TYPE_NAVBAR,null,null);
		List<BlogMap> choosedBlogMaps = blogMapService.queryList(blogMap);
		if(!IArrayListUtil.isBlankList(choosedBlogMaps)){
			for (int i = 0; i < choosedBlogMaps.size(); i++) {
				blogMap = choosedBlogMaps.get(i);
				selectedBlogIds += blogMap.getBlogId();
				if(i != choosedBlogMaps.size() -1){
					selectedBlogIds += ",";
				}
			}
		}
		result.put("selectedBlogIds", selectedBlogIds);
		return new ModelAndView("navigationbar/navigationbar_blog",result);
	}
	
	@RequiresPermissions("5.query")
	@RequestMapping(value="/navigationbar",method=RequestMethod.GET)
	@ResponseBody
	public String lst(){
		List<NavigationBar> authors = navigationBarService.queryList();
		return convertToJsonString(authors);
	}
	
	@RequiresPermissions("5.add")
	@RequestMapping(value="/navigationbar",method=RequestMethod.POST)
	@ResponseBody
	@ShaunzAuditLog(optType="add",functionId="5")
	public String add(NavigationBar navigationBar,BindingResult bindingResult,Locale locale){
		Map<String, String> results = new HashMap<String, String>();
		if(bindingResult.hasErrors()){
			results.put("result", "error");
			results.put("message", messageSource.getMessage("validation.failed",null,locale));
		} else {
			navigationBar.setId(""+sequenceGenerator.getNextMngmtSequenceNo());
			navigationBar.setCloseFlg("N");
			boolean flag = navigationBarService.insertSelective(navigationBar);
			navigationBar.setOptFlag(flag);
			if(flag){
				refreshNavigationBarInHomepage();
			}
			return formSubmitResult(flag, "common.addMsg", new Object[]{messageSource.getMessage("navigationbar.title", null, locale),navigationBar.getName()}
					, locale);
		}
		return convertToJsonString(results);
	
	}
	
	@RequiresPermissions("5.update")
	@RequestMapping(value="/navigationbar",method=RequestMethod.PUT)
	@ResponseBody
	@ShaunzAuditLog(optType="update",functionId="5")
	public String edit(NavigationBar navigationBar,Locale locale){
		boolean flag = navigationBarService.updateByPrimaryKeySelective(navigationBar);
		navigationBar.setOptFlag(flag);
		if(flag){
			refreshNavigationBarInHomepage();
		}
		return formSubmitResult(flag, "common.updateMsg", new Object[]{messageSource.getMessage("navigationbar.title", null, locale),navigationBar.getName()}
		, locale);
	}
	
	@RequiresPermissions("5.delete")
	@RequestMapping(value="/navigationbar/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	@ShaunzAuditLog(optType="delete",functionId="5")
	public String delete(@PathVariable("id") String id,Locale locale){
		NavigationBar navigationBar = navigationBarService.selectByPrimaryKey(id);
		boolean flag = navigationBarService.closeAuthor(navigationBar);
		if(flag){
			refreshNavigationBarInHomepage();
		}
		return formSubmitResult(flag, "common.deleteMsg", new Object[]{messageSource.getMessage("navigationbar.title", null, locale),navigationBar.getName()}
		, locale);
	}
	
	@RequiresPermissions("5.update")
	@RequestMapping(value="/navigationbar/relate",method=RequestMethod.POST)
	@ResponseBody
	@ShaunzAuditLog(optType="update",functionId="5")
	public String relate(String id,String blogIds,Locale locale){
		NavigationBar navigationBar = navigationBarService.selectByPrimaryKey(id);
		String[] blogIdArr = blogIds.split(",");
		boolean flag = blogMapService.blogRelate(blogIdArr, PAGE_TYPE_NAVBAR, navigationBar.getId());
		return formSubmitResult(flag, "common.grantMsg", new Object[]{messageSource.getMessage("navigationbar.title", null, locale),navigationBar.getName()}
		, locale);
	}
	
	private void refreshNavigationBarInHomepage(){
		HomePage homePage = null;
		try {
			homePage = (HomePage)request.getServletContext().getAttribute("homePageObject");
			homePage.setNavigationBars(navigationBarService.queryAllNavigationBar());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
	}

}
