package com.shaunz.framework.common.utils;

import com.alibaba.fastjson.JSONObject;

public class JsonUtil {
	public static JSONObject toJsonObj(Object object){
		return JSONObject.parseObject(object.toString());
	}
	
	public static String toJsonString(Object object){
		if(object == null)
			return "{object:\"null\"}";
		String jsonStr = JSONObject.toJSONString(object);
		return jsonStr;
	}
}
