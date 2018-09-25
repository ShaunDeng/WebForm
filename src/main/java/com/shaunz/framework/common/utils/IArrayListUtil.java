package com.shaunz.framework.common.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shaunz.framework.authority.function.entity.Function;
import com.shaunz.framework.core.BaseEntity;

public class IArrayListUtil {
	
	public static boolean isBlankList(List<?> list) {
		return list == null || list.size() == 0;
	}
	
	public static boolean isBlankArray(Object[] objects) {
		return objects == null || objects.length == 0;
	}
	
	public static Map<String, Function> entityLst2Map(List<Function> list){
		Map<String, Function> map = null;
		Function baseEntity = null;
		if(!isBlankList(list)){
			map = new HashMap<String, Function>();
			for (int i = 0; i < list.size(); i++) {
				baseEntity = list.get(i);
				map.put(baseEntity.getId(), baseEntity);
			}
		}
		return map;
	}
	
}
