package com.shaunz.webform.web.common;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;

import com.shaunz.framework.common.source.service.SourceService;
import com.shaunz.framework.web.base.BaseService;
import com.shaunz.webform.web.carousel.dao.CarouselMapper;
import com.shaunz.webform.web.carousel.entity.Carousel;
import com.shaunz.webform.web.home.entity.HomePage;
import com.shaunz.webform.web.marketinfo.dao.MarketInfoMapper;
import com.shaunz.webform.web.marketinfo.entity.MarketInfo;
import com.shaunz.webform.web.navigationbar.dao.NavigationBarMapper;
import com.shaunz.webform.web.navigationbar.entity.NavigationBar;

@Service
public class HomePageGenerator extends BaseService{

	@Resource
	private NavigationBarMapper navigationBarMapper;
	
	@Resource
	private SourceService sourceTableGenerator;
	
	@Resource
	private CarouselMapper carouselMapper;
	
	@Resource
	private MarketInfoMapper marketInfoMapper;
	
	
	public void generateHomePage(ServletContext servletContext) {
		HomePage homePage = new HomePage();
		
		homePage.setTitle(getHomePageParameterBy("project_nm"));
		homePage.setDescription(getHomePageParameterBy("description"));
		homePage.setIconURL(getHomePageParameterBy("icon_url"));
		homePage.setKeywords(getHomePageParameterBy("keywords"));
		homePage.setProjectNm(getHomePageParameterBy("project_nm"));
		homePage.setHomeUrl(getHomePageParameterBy("home_url"));
		homePage.setCompanyNm(getHomePageParameterBy("company"));
		homePage.setSystemYear(getSystemYear());
		homePage.setSignInURL(getHomePageParameterBy("signin_url"));
		homePage.setSignUpURL(getHomePageParameterBy("signup_url"));
		
		List<NavigationBar> allNavigationBars = navigationBarMapper.queryAllNavigationBar();
		homePage.setNavigationBars(allNavigationBars);
		
		List<Carousel> allCarousels = carouselMapper.quaryAll();
		homePage.setCarousels(allCarousels);
		
		List<MarketInfo> marketInfos = marketInfoMapper.quaryAll();
		homePage.setMarketInfos(marketInfos);
		
		
		servletContext.setAttribute("homePageObject", homePage);
		logger.info("Set homePageObject into Servlet Context success!");
	}
	
	private String getHomePageParameterBy(String name){
		return sourceTableGenerator.getHomePageParameterby(name);
	}
	
	private String getSystemYear(){
		return ""+Calendar.getInstance().get(Calendar.YEAR);
	}
}
