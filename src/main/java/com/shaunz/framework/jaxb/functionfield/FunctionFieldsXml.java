package com.shaunz.framework.jaxb.functionfield;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.shaunz.framework.common.utils.IArrayListUtil;

@XmlRootElement(name="functions")
@XmlAccessorType(XmlAccessType.FIELD)
public class FunctionFieldsXml {
	@XmlElement(name="function")
	private List<Function> functions;
	@XmlTransient
	private Map<String, Function> functionMap;
	
	public void init(){
		if(!IArrayListUtil.isBlankList(functions)){
			functionMap = new HashMap<String, Function>();
			for (int i = 0; i < functions.size(); i++) {
				functionMap.put(functions.get(i).getId(), functions.get(i));
			}
		}
	}
	
	public Function getFunctionBy(String functionId){
		if(functionMap != null){
			return functionMap.get(functionId);
		}
		return null;
	}
}
