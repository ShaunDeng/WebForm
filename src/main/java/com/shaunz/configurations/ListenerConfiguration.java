package com.shaunz.configurations;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shaunz.framework.common.listener.ShaunzApplicationListener;

@Configuration
public class ListenerConfiguration {
	
	@Bean
	public ShaunzApplicationListener shaunzApplicationListener(){
		return new ShaunzApplicationListener();
	}
	
	@Bean
	public ServletWebServerFactory serverFactory(){
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
		tomcat.addAdditionalTomcatConnectors(createsStandardConnector());
		return tomcat;
	}
	
	private Connector createsStandardConnector(){
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setPort(80);
		return connector;
	}
}
