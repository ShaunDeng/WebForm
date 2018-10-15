package com.shaunz;

import java.util.Collections;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.shaunz.*.*.*.dao")
@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
public class WebFormSpringBootApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(WebFormSpringBootApplication.class, args);
	}

	/* (non-Javadoc)
	 * @see org.springframework.boot.web.servlet.support.SpringBootServletInitializer#onStartup(javax.servlet.ServletContext)
	 */
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// TODO Auto-generated method stub
		super.onStartup(servletContext);
		
		//This will set to use COOKIE only
		servletContext.setSessionTrackingModes(Collections.singleton(SessionTrackingMode.COOKIE));
		
		/*This will prevent any JS on the page from accessing the
		 * cookie - it will only be used/accessed by the HTTP transport
		 * mechanism in use*/
		SessionCookieConfig sessionCookieConfig = servletContext.getSessionCookieConfig();
		sessionCookieConfig.setHttpOnly(true);
	}
}
