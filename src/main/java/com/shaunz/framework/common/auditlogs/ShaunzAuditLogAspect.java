package com.shaunz.framework.common.auditlogs;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.shaunz.framework.authority.user.entity.User;
import com.shaunz.framework.common.auditlogs.service.SystemLogService;
import com.shaunz.framework.common.utils.IArrayListUtil;

@Aspect
@Component
public class ShaunzAuditLogAspect {
	private static Logger logger = Logger.getLogger(ShaunzAuditLogAspect.class);
	@Autowired
	SystemLogService systemLogService;
	
	private User operator;
	private List<Object> inputParams = new ArrayList<Object>();
	private List<Object> outputParams = new ArrayList<Object>();
	private Class<?>[] ignoreClasses = {BindingResult.class,Locale.class,MultipartFile.class,BeanPropertyBindingResult.class};
	
	@Pointcut("@annotation(com.shaunz.framework.common.auditlogs.ShaunzAuditLog)")
	public void pointCut(){
		
	}
	
	@Before("pointCut()")
	public void doBefore(JoinPoint joinPoint){
		try {
			operator = (User)getHttpServletRequest().getSession().getAttribute("user");
			inputParams = getArgs(joinPoint);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
	}
	
	@After("pointCut()")
	public void doAfter(JoinPoint joinPoint){
		ShaunzAuditLog shaunzAuditLog = null;
		Subject subject = null;
		List<Object> inputEntities = null;
		List<Object> outputEntities = null;
		try {
			shaunzAuditLog = (ShaunzAuditLog)getAspectAnnotation(joinPoint,ShaunzAuditLog.class);
			outputParams = getArgs(joinPoint);
			inputEntities = getChangedEntities(inputParams);
			outputEntities = getChangedEntities(outputParams);
			boolean logFlag = systemLogService.log(shaunzAuditLog.optType(), shaunzAuditLog.functionId(), 
					inputEntities, outputEntities, operator);
			if(!logFlag){
				logger.warn("Loging failed: OptType:" + shaunzAuditLog.optType() 
				+" FunctionId:" + shaunzAuditLog.functionId() 
				+" OperatorId:" + operator.getId());
			}
		} catch (Exception e) {
			logger.error(e.getClass() + e.getMessage());
		} finally {
			shaunzAuditLog = null;
			subject = null;
			inputEntities = null;
			outputEntities = null;
		}
	}
	
	/*
	@Around("pointCut()")
	public void doAround(JoinPoint joinPoint){
		ShaunzAuditLog shaunzAuditLog = null;
		try {
			shaunzAuditLog = (ShaunzAuditLog)getAspectAnnotation(joinPoint,ShaunzAuditLog.class);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}*/
	
	@AfterThrowing(pointcut="pointCut()",throwing="e")
	public void doAfterThrowing(JoinPoint joinPoint,Exception e){
		ShaunzAuditLog shaunzAuditLog = null;
		try {
			shaunzAuditLog = (ShaunzAuditLog)getAspectAnnotation(joinPoint,ShaunzAuditLog.class);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		
	}
	
	private Object getAspectAnnotation(JoinPoint joinPoint,Class<?> clazz) throws Exception{
		Object aspectAnnotaion = null;
		Annotation[] allAnnotations = getMethodAnnotation(joinPoint);
		if(allAnnotations != null && allAnnotations.length > 0){
			for (int i = 0; i < allAnnotations.length; i++) {
				Annotation annotation = allAnnotations[i];
				if(annotation.annotationType().equals(clazz)){
					aspectAnnotaion = annotation;
				}
			}
			return aspectAnnotaion;
		} else {
			return aspectAnnotaion;
		}
	}
	
	private Annotation[] getMethodAnnotation(JoinPoint joinPoint) throws Exception {
		Signature signature = joinPoint.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();
		if (method != null) {
			return method.getDeclaredAnnotations();
		}
		return null;
	}
	
	private HttpServletRequest getHttpServletRequest(){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();  
        ServletRequestAttributes sra = (ServletRequestAttributes)ra;  
        HttpServletRequest request = sra.getRequest();
        return request;
    }
	
	private List<Object> getArgs(JoinPoint joinPoint){
		List<Object> params = new ArrayList<Object>();
		Object[] objects = joinPoint.getArgs();
		if(!IArrayListUtil.isBlankArray(objects)){
			for (int i = 0; i < objects.length; i++) {
				params.add(objects[i]);
			}
		}
		return params;
	}
	
	private List<Object> getChangedEntities(List<Object> args){
		List<Object> entities = new ArrayList<Object>();
		if(!IArrayListUtil.isBlankList(args)){
			for (int i = 0; i < args.size(); i++) {
				Object object = args.get(i);
				for (int j = 0; j < ignoreClasses.length; j++) {
					if(object.getClass().equals(ignoreClasses[j])){
						break;
					} else {
						if(j == ignoreClasses.length -1){
							entities.add(object);
						} else {
							continue;
						}
					}
				}
			}
		}
		return entities;
	}
}
