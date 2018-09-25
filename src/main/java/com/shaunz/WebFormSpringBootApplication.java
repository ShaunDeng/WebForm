package com.shaunz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.shaunz.*.*.*.dao")
@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
public class WebFormSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebFormSpringBootApplication.class, args);
	}
}
