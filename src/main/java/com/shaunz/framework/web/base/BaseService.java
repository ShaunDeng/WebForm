package com.shaunz.framework.web.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BaseService {
	protected  Logger logger = LoggerFactory.getLogger(BaseService.class);
}
