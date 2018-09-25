package com.shaunz.webform.web.home.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shaunz.framework.web.base.BaseController;
import com.shaunz.webform.web.blog.entity.Blog;
import com.shaunz.webform.web.home.entity.HomePage;
import com.shaunz.webform.web.home.service.HomeService;
import com.shaunz.webform.web.navigationbar.entity.NavigationBar;

@Controller
public class HomeController extends BaseController{
	private static final String PAGE_TYPE_NAVBAR = "tb_navigation_bar";
	private static final String PAGE_TYPE_DROPDOWNLST = "tb_dropdown_list";
	private static final String PAGE_TYPE_MARKET = "tb_market_info";
	private static final String PAGE_TYPE_CAROUSEL = "tb_carousel";
	
	@Resource
	private HomeService homeService;
	
	@RequestMapping(value="/index.html",method=RequestMethod.GET)
    public ModelAndView homePage(HttpServletRequest request){
		String navBarId = getDefaultActivePage(request);
		Map<String, Object> resultMap = generateResultMap();
		resultMap.put("navBarId", navBarId);
    	return new ModelAndView("index",resultMap);
    }
	
	@RequestMapping(value="/",method=RequestMethod.GET)
    public ModelAndView defaultHomePage(HttpServletRequest request){
    	return homePage(request);
    }
	
	@RequestMapping(value="/navBar.html",method=RequestMethod.GET)
	public ModelAndView navBarPage(String navBarId){
		List<Blog> blogs = homeService.selectByPageId(PAGE_TYPE_NAVBAR, navBarId);
		Map<String, Object> resultMap = generateResultMap();
		resultMap.put("navBarId", navBarId);
		resultMap.put("blogs", blogs);
		return new ModelAndView("blog",resultMap);
	}
	
	@RequestMapping(value="/dropDownLst.html",method=RequestMethod.GET)
	public ModelAndView dropDownLstPage(String dropDownLstId){
		List<Blog> blogs = homeService.selectByPageId(PAGE_TYPE_DROPDOWNLST, dropDownLstId);
		Map<String, Object> resultMap = generateResultMap();
		resultMap.put("dropDownLstId", dropDownLstId);
		resultMap.put("blogs", blogs);
		return new ModelAndView("blog",resultMap);
	}
	
	@RequestMapping(value="/carousel.html",method=RequestMethod.GET)
	public ModelAndView carouselPage(String carouselId) {
		List<Blog> blogs = homeService.selectByPageId(PAGE_TYPE_CAROUSEL, carouselId);
		Map<String, Object> resultMap = generateResultMap();
		resultMap.put("marketInfoId", carouselId);
		resultMap.put("blogs", blogs);
		return new ModelAndView("blogWithRightSideBar",resultMap);
	}
	
	@RequestMapping(value="/marketInfo.html",method=RequestMethod.GET)
	public ModelAndView marketPage(String marketInfoId) {
		List<Blog> blogs = homeService.selectByPageId(PAGE_TYPE_MARKET, marketInfoId);
		Map<String, Object> resultMap = generateResultMap();
		resultMap.put("marketInfoId", marketInfoId);
		resultMap.put("blogs", blogs);
		return new ModelAndView("blogWithRightSideBar",resultMap);
	}
	
	@RequestMapping(value="/managePlant.html",method=RequestMethod.GET)
	public String managePlantPage(){
		return "managePlantPage";
	}
	
	private String getDefaultActivePage(HttpServletRequest request){
		String activePageId = "1";
		HomePage homePage = (HomePage)request.getServletContext().getAttribute("homePageObject");
		List<NavigationBar> navigationBars = homePage.getNavigationBars();
		NavigationBar nvigationBar;
		if(navigationBars != null){
			for (int i = 0; i < navigationBars.size(); i++) {
				nvigationBar = navigationBars.get(i);
				if("active".equals(nvigationBar.getActive())){
					activePageId = nvigationBar.getId();
					break;
				}
			}
		}
		return activePageId;
	}
}
