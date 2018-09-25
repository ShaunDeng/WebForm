package com.shaunz.framework.authority.function.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shaunz.framework.authority.function.entity.Function;
import com.shaunz.framework.authority.function.service.FunctionService;
import com.shaunz.framework.authority.user.entity.User;
import com.shaunz.framework.common.treemenu.TreeMenu;
import com.shaunz.framework.common.utils.IArrayListUtil;
import com.shaunz.framework.jaxb.functionfield.FunctionFieldsXml;
import com.shaunz.framework.web.base.BaseController;

@Controller
public class FunctionController extends BaseController{
	
	@Autowired
	private FunctionService functionService;

	@RequestMapping(value="/functions",method=RequestMethod.GET)
	@ResponseBody
	public String generateMngmtTree(Locale locale){
		User user = getUser();
		List<Function> allAuthorizedFunctions = functionService.queryAllAuthorizedFunctionByUsrId(user.getId());
		allAuthorizedFunctions = functionNmI18n(allAuthorizedFunctions,locale);
		TreeMenu treeMenu = functionService.generateMngmtTree(allAuthorizedFunctions);
		return convertToJsonString(treeMenu);
	}
	
	@RequestMapping(value="/object/{functionId:[\\d]+}/{objId:[\\d]+}",method=RequestMethod.GET)
	@ResponseBody
	public String findObjBy(@PathVariable("functionId")long functionId, @PathVariable("objId")long objId){
		Map<String, Object> obj = functionService.findObjectDetailBy(""+functionId, ""+objId);
		return convertToJsonString(obj);
	}
	
	@RequestMapping(value="/function",method=RequestMethod.GET)
	@ResponseBody
	public String functionLst(){
		List<Function> functions = functionService.queryAllFunctions();
		return convertToJsonString(functions);
	}
	
	@RequestMapping(value="/choosePage.html",method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView choosePage(String functionId,String relatedInput,String multiselect,String selectedIds,Locale locale){
		Map<String, Object> result = new HashMap<String, Object>();
		List<String> tableHeader = getTableHeader(functionId, locale);
		List<String> tableColumn = getTableColumn(functionId);
		result.put("relatedInput", relatedInput);
		result.put("tableHeader", convertToJsonString(tableHeader));
		result.put("tableColumn", convertToJsonString(tableColumn));
		result.put("functionId", functionId);
		result.put("multiselect", multiselect);
		result.put("selectedIds", selectedIds);
		return new ModelAndView("choosePage",result);
	}
	
	@RequestMapping(value="/choose/{functionId:[\\d]+}",method=RequestMethod.GET)
	@ResponseBody
	public String chooseLst(@PathVariable("functionId")long functionId){
		List<Map<String, Object>> list = functionService.queryObjLstby(""+functionId);
		return convertToJsonString(list);
	}
	
	private List<Function> functionNmI18n(List<Function> functions,Locale locale){
		if (!IArrayListUtil.isBlankList(functions)) {
			for (int i = 0; i < functions.size(); i++) {
				Function function = functions.get(i);
				function.setName(messageSource.getMessage("menuTree."+function.getName(), null, locale));
			}
		}
		return functions;
	}
	
	private List<String> getTableColumn(String functionId){
		FunctionFieldsXml functionFieldsXml = (FunctionFieldsXml)request.getServletContext().getAttribute("functionFieldsXml");
		com.shaunz.framework.jaxb.functionfield.Function function = functionFieldsXml.getFunctionBy(functionId);
		return function.getTableColumn();
	}
	
	private List<String> getTableHeader(String functionId,Locale locale){
		List<String> result = new ArrayList<String>();
		FunctionFieldsXml functionFieldsXml = (FunctionFieldsXml)request.getServletContext().getAttribute("functionFieldsXml");
		com.shaunz.framework.jaxb.functionfield.Function function = functionFieldsXml.getFunctionBy(functionId);
		List<String> headerKeys = function.getTableHeader();
		if(!IArrayListUtil.isBlankList(headerKeys)){
			for (int i = 0; i < headerKeys.size(); i++) {
				String headerKey = headerKeys.get(i);
				if("#".equals(headerKey)){
					result.add(headerKey);
				} else {
					String i18Key = function.getTitle() + "." + headerKey;
					result.add(messageSource.getMessage(i18Key, null, locale));
				}
			}
		}
		return result;
	}
}
