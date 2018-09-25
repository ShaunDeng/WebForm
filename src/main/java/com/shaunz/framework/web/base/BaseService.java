package com.shaunz.framework.web.base;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class BaseService {
	protected  Logger logger = Logger.getLogger(this.getClass());
}
