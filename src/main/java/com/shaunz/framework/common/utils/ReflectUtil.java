package com.shaunz.framework.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Reflect code tool
 * @since 2016-07-01
 * @author Shaun
 * @version 1.0.0
 */
public class ReflectUtil {
	/**
	 * Get Field object of the specified field name
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Field getFieldByFieldName(Object obj, String fieldName) {
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
			}
		}
		return null;
	}

	/**
	 * Get the value of the specified field name
	 * @param obj
	 * @param fieldName
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Object getValueByFieldName(Object obj, String fieldName)
			throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		Field field = getFieldByFieldName(obj, fieldName);
		Object value = null;
		if(field!=null){
			if (field.isAccessible()) {
				value = field.get(obj);
			} else {
				field.setAccessible(true);
				value = field.get(obj);
				field.setAccessible(false);
			}
		}
		return value;
	}

	/**
	 * Set the value of the specified field name
	 * @param obj
	 * @param fieldName
	 * @param value
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void setValueByFieldName(Object obj, String fieldName,
			Object value) throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		Field field = obj.getClass().getDeclaredField(fieldName);
		if (field.isAccessible()) {
			field.set(obj, value);
		} else {
			field.setAccessible(true);
			field.set(obj, value);
			field.setAccessible(false);
		}
	}
	
	/**
	 * Get Method object of the specified method name
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Method getMethodByMethodName(Object obj, String methodName,Class<?>... parameterTypes) {
		Class<?> superClass = obj.getClass();
		try {
			return superClass.getDeclaredMethod(methodName, parameterTypes);
		} catch (NoSuchMethodException e) {
			
		} catch (SecurityException e) {
			
		}
		return null;
	}
	
	/**
	 * Get Method object of the specified method name
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Method[] getAllMethods(Object obj) {
		Class<?> superClass = obj.getClass();
		try {
			return superClass.getDeclaredMethods();
		} catch (SecurityException e) {
			
		}
		return null;
	}
	
	/**
	 * Call the Method of the specified method name with parameters provided.
	 * @param obj
	 * @param methodNm
	 * @param parameters
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T callMethod(Method method,Object obj,Object... parameters) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		return (T)method.invoke(obj, parameters);
	}
}
