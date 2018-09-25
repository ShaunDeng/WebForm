package com.shaunz.framework.common.source.service;

import java.lang.reflect.Method;

import org.springframework.stereotype.Service;

import com.shaunz.framework.common.source.entity.Source;
import com.shaunz.framework.common.utils.ReflectUtil;
import com.shaunz.framework.web.base.BaseService;

/**
 * 1st bit radio
 * 2nd bit checkbox
 * 3rd bit number
 * @author dengxiong90@foxmail.com
 *
 */
@Service
public class SourceValidator extends BaseService{
	private Validator validator =  new Validator();
	
	public String validate(Source source){
		StringBuffer validateMsg = new StringBuffer("");
		try {
			int validatorIndex = Integer.valueOf(source.getValidator()).intValue();
			Method[] methods = ReflectUtil.getAllMethods(validator);
			if(validatorIndex > 0){
				for (int i = 0; i < methods.length; i++) {
					int methodIndex = Integer.valueOf(methods[i].getName().substring(methods[i].getName().indexOf("_"))).intValue();
					if((validatorIndex & (1 << methodIndex))>0){
						String mString = ReflectUtil.callMethod(methods[i],validator, source);
						if(validateMsg.length() > 0){
							validateMsg.append("\n");
						}
						validateMsg.append(mString);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return validateMsg.toString();
	}
	
	private class Validator{
		String method_1(Source source){
			return "method_1";
		}
		
		String method_2(Source source){
			return "method_2";
		}
		
		String method_3(Source source){
			return "method_3";
		}
	}
	
}
