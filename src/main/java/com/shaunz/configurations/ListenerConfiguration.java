package com.shaunz.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shaunz.framework.common.listener.ShaunzApplicationListener;

@Configuration
public class ListenerConfiguration {
	
	@Bean
	public ShaunzApplicationListener shaunzApplicationListener(){
		return new ShaunzApplicationListener();
	}
	
}
