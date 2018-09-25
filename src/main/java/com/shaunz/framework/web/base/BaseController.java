package com.shaunz.framework.web.base;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.shaunz.framework.authority.user.entity.User;
import com.shaunz.framework.common.utils.IStringUtil;
import com.shaunz.framework.core.YgdrasilConst;

/**
 * @version 1.0.0
 * @author Shaun Deng
 * @since 2016-06-21
 */
@Controller
public class BaseController {
	protected static final String FORWARD_TO = "forward:";
	protected static final String REDIRECT_TO = "redirect:";
	
	@Autowired
	protected MessageSource messageSource;
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	
	protected  Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ModelAttribute 
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){ 
        this.request = request; 
        this.response = response; 
        this.session = request.getSession(); 
    }
	
	public String getParameter(String key){
		if(StringUtils.isEmpty(key)) return null;
		return request.getParameter(key);
	}
	
	public void setAttribuate(String key,Object o){
		request.setAttribute(key, o);
	}
	
	@ExceptionHandler
	public ModelAndView ExceptionHandler(HttpServletRequest request,Exception ex){
		StringPrintWriter spw=new StringPrintWriter();
		ex.printStackTrace(spw); 
		long timeStamp = new Date().getTime();
		logger.error("[ExceptionHandler: "+timeStamp+"]\n"+spw.getString());
		
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("exception",spw.getString());
		resultMap.put("ReferenceNo", ""+timeStamp);
		return new ModelAndView("error/exception",resultMap);
	}
	
	public class StringPrintWriter extends PrintWriter{
		public StringPrintWriter(){  
	        super(new StringWriter());  
	    }  
	     
	    public StringPrintWriter(int initialSize) {
	          super(new StringWriter(initialSize));  
	    }  
	     
	    public String getString() {  
	          flush();  
	          return ((StringWriter) this.out).toString();  
	    }  
	     
	    @Override  
	    public String toString() {  
	        return getString();  
	    }  
	}
	
	protected Map<String, Object> generateResultMap() {
		Map<String, Object> resultMap = new HashMap<String,Object>();
		return resultMap;
	}
	
	protected User getUser() {
		User user = (User)session.getAttribute("user");
		return user;
	}
	
	protected String convertToJsonString(Object obj) {
		if(obj == null)
			return "{\"object\":\"null\"}";
		String jsonStr = JSONObject.toJSONStringWithDateFormat(obj, YgdrasilConst.DATE_FORMART, SerializerFeature.WriteDateUseDateFormat);
		//jsonStr = jsonStr.replaceAll("\"", "'");
		return jsonStr;
	}
	
	protected String formSubmitResult(boolean flag,String msgKey,Object[] params,Locale locale){
		return formSubmitResult(flag, msgKey, params, locale, null);
	}
	
	protected String formSubmitResult(boolean flag,String msgKey,Object[] params,Locale locale,String otherMsg){
		Map<String, String> results = new HashMap<String, String>();
		List<Object> paramsLst = new ArrayList<Object>(Arrays.asList(params));
		String operateFlag = "";
		if(flag){
			results.put("result", "success");
			operateFlag = messageSource.getMessage("common.success",null,locale);
		} else {
			results.put("result", "failed");
			operateFlag = messageSource.getMessage("common.failed",null,locale);
			
		}
		if(IStringUtil.notBlank(otherMsg)){
			paramsLst.add(operateFlag+":\n"+otherMsg);
		} else {
			paramsLst.add(operateFlag);
		}
		results.put("message",messageSource.getMessage(msgKey,paramsLst.toArray(),locale));
		return convertToJsonString(results);
	}
	
	@InitBinder
	protected void dateBinder(WebDataBinder binder) {
	            //The date format to parse or output your dates
	    SimpleDateFormat dateFormat = new SimpleDateFormat(YgdrasilConst.DATE_FORMART);
	            //Create a new CustomDateEditor
	    CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
	            //Register it as custom editor for the Date type
	    binder.registerCustomEditor(Date.class, editor);
	}
}
