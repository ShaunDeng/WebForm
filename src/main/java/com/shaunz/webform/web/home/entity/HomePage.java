package com.shaunz.webform.web.home.entity;

import java.io.Serializable;
import java.util.List;

import com.shaunz.webform.web.carousel.entity.Carousel;
import com.shaunz.webform.web.marketinfo.entity.MarketInfo;
import com.shaunz.webform.web.navigationbar.entity.NavigationBar;

public class HomePage implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String title;
	private String description;
	private String keywords;
	private String iconURL;
	private String projectNm;
	private String companyNm;
	private String homeUrl;
	private String systemYear;
	private List<NavigationBar> navigationBars;
	private List<Carousel> carousels;
	private List<MarketInfo> marketInfos;
	private String signInURL;
	private String signUpURL;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getIconURL() {
		return iconURL;
	}
	public void setIconURL(String iconURL) {
		this.iconURL = iconURL;
	}
	public String getProjectNm() {
		return projectNm;
	}
	public void setProjectNm(String projectNm) {
		this.projectNm = projectNm;
	}
	public String getCompanyNm() {
		return companyNm;
	}
	public void setCompanyNm(String companyNm) {
		this.companyNm = companyNm;
	}
	public String getHomeUrl() {
		return homeUrl;
	}
	public void setHomeUrl(String homeUrl) {
		this.homeUrl = homeUrl;
	}
	public String getSystemYear() {
		return systemYear;
	}
	public void setSystemYear(String systemYear) {
		this.systemYear = systemYear;
	}
	public List<NavigationBar> getNavigationBars() {
		return navigationBars;
	}
	public void setNavigationBars(List<NavigationBar> navigationBars) {
		this.navigationBars = navigationBars;
	}
	public List<Carousel> getCarousels() {
		return carousels;
	}
	public void setCarousels(List<Carousel> carousels) {
		this.carousels = carousels;
	}
	public List<MarketInfo> getMarketInfos() {
		return marketInfos;
	}
	public void setMarketInfos(List<MarketInfo> marketInfos) {
		this.marketInfos = marketInfos;
	}
	public String getSignInURL() {
		return signInURL;
	}
	public void setSignInURL(String signInURL) {
		this.signInURL = signInURL;
	}
	public String getSignUpURL() {
		return signUpURL;
	}
	public void setSignUpURL(String signUpURL) {
		this.signUpURL = signUpURL;
	}
}
