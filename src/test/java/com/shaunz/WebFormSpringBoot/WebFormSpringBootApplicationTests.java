package com.shaunz.WebFormSpringBoot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.shaunz.WebFormSpringBootApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebFormSpringBootApplication.class)
@WebAppConfiguration
public class WebFormSpringBootApplicationTests {

	@Test
	public void contextLoads() {
	}

}
