package com.shaunz.WebFormSpringBoot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.shaunz.WebFormSpringBootApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebFormSpringBootApplication.class)
@WebAppConfiguration
public class WebFormSpringBootApplicationTests {

	@Test
	public void contextLoads() {
	}

}
