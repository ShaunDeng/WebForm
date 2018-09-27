package com.shaunz;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shaunz.framework.shiro.realm.ShaunzRealm;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

@Configuration
public class ShiroConfiguration {
	@Bean
	public ShiroFilterFactoryBean shiroFilter(org.apache.shiro.mgt.SecurityManager securityManager){
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setLoginUrl("/index.html");
		//shiroFilterFactoryBean.setSuccessUrl(successUrl);
		shiroFilterFactoryBean.setUnauthorizedUrl("/error/unauthorized");
		
		Map<String, Filter> filterMap = new LinkedHashMap<String,Filter>();
		//filterMap.put("kickout", kickoutSessionControlFilter());
		shiroFilterFactoryBean.setFilters(filterMap);
		
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
		/*List<SysPermissionInit> list = SysPermissionInitService.selectAll();
		for(SysPermissionInit sysPermissionInit : list){
			filterChainDefinitionMap.put(sysPermissionInit.getUrl(), sysPermissionInit.getPermissionInit());
		}
		*/
		filterChainDefinitionMap.put("/index.html", "anon");
		filterChainDefinitionMap.put("/navBar.html", "anon");
		filterChainDefinitionMap.put("/dropDownLst.html", "anon");
		filterChainDefinitionMap.put("/marketInfo.html", "anon");
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/webresources/**", "anon");
		filterChainDefinitionMap.put("/customerResources/**", "anon");
		filterChainDefinitionMap.put("/signIn.html", "anon");
		filterChainDefinitionMap.put("/signUp.html", "anon");
		filterChainDefinitionMap.put("/signCheck", "anon");
		filterChainDefinitionMap.put("/signIn", "anon");
		//filterChainDefinitionMap.put("/index.jsp", "anon");
		filterChainDefinitionMap.put("/**", "authc");
		
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		
		return shiroFilterFactoryBean;
	}
	
	@Bean
	public EhCacheManager getEhCacheManager() {
		EhCacheManager em = new EhCacheManager();
		em.setCacheManagerConfigFile("classpath:ehcache.xml");
		return em;
	}


	@Bean
	public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}


	@Bean
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
		daap.setProxyTargetClass(true);
		return daap;
	}
	
	@Bean
	public org.apache.shiro.mgt.SecurityManager securityManager(){
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(shaunzRealm());
		securityManager.setCacheManager(getEhCacheManager());
		securityManager.setRememberMeManager(rememberMeManager());
		
		return securityManager;
	}
	
	@Bean
	public ShaunzRealm shaunzRealm(){
		return new ShaunzRealm(shiroEncacheManager());
	}
	
	@Bean
	public CacheManager shiroEncacheManager(){
		return new MemoryConstrainedCacheManager();
	}
	
	@Bean
	public SimpleCookie rememberMeCookie(){
		SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
		simpleCookie.setMaxAge(2592000);
		return simpleCookie;
	}
	
	@Bean
	public CookieRememberMeManager rememberMeManager(){
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		cookieRememberMeManager.setCookie(rememberMeCookie());
		try {
			cookieRememberMeManager.setCipherKey(org.apache.commons.codec.binary.Base64.decodeBase64("Sh@uN2.com".getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return cookieRememberMeManager;
	}
	
	/**
	 * Use shiro tag in thymeleaf
	 * @return
	 */
	@Bean
	public ShiroDialect shiroDialect(){
		return new ShiroDialect();
	}
}
