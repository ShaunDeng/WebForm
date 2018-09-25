package com.shaunz.framework.common.source.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shaunz.framework.common.source.entity.Source;
import com.shaunz.framework.common.source.service.SourceService;
import com.shaunz.framework.common.utils.IStringUtil;
import com.shaunz.framework.core.YgdrasilConst;
import com.shaunz.framework.web.base.BaseController;

@Controller
public class SourceController extends BaseController{
	@Autowired
	private SourceService sourceTableGenerator;
	
	@RequestMapping(value="/systemparam_lst.html",method=RequestMethod.GET)
	public String sysParamPage(){
		return "system/systemparam_lst";
	}
	
	@RequestMapping(value="/source",method=RequestMethod.GET)
	@ResponseBody
	public String lst(String[] groupNms){
		List<HashMap<String, Source>> result = new ArrayList<HashMap<String,Source>>();
		for (int i = 0; i < groupNms.length; i++) {
			String groupNm = groupNms[i];
			HashMap<String, Source> sources = sourceTableGenerator.getSourceBy(groupNm);
			result.add(sources);
		}
		return convertToJsonString(result);
	}
	
	@RequestMapping(value="/source/{groupNm}/{name}",method=RequestMethod.PUT)
	@ResponseBody
	public String edit(@PathVariable(value="groupNm") String groupNm,@PathVariable("name") String name,String value,Locale locale){
		String msg = sourceTableGenerator.updateSource(groupNm, name, value);
		boolean flag = YgdrasilConst.SUCCESS.equals(msg);
		return formSubmitResult(flag, "common.updateMsg", new Object[]{messageSource.getMessage("source.title", null, locale),name}
		, locale,IStringUtil.notBlank(msg)?msg:null);
	}
}
