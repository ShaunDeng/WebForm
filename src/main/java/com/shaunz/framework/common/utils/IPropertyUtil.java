package com.shaunz.framework.common.utils;

import java.util.Properties;

import lombok.extern.slf4j.Slf4j;


/**
 * Deal with the property file
 * @since 2016-01-01
 * @author Shaun
 * @version 1.0
 */
@Slf4j
public class IPropertyUtil {
	private Properties manager = null;

	/**
	 * private construct function used to create IPropertyUtil instance
	 * @method IPropertyUtil
	 * @param props
	 */
	private IPropertyUtil(String props){
		manager = new Properties();
		try {
			manager.load(getClass().getResourceAsStream(props));
		} catch (Exception e) {
			log.error("[IPropertyUtil#IPropertyUtil]"+ e.getMessage());
		}
	}
    /**   
     * return an IPropertyUtil instance
     * @param propertyPath
     * @return IPropertyUtil instance
     */
    public static IPropertyUtil getInstance(String propertyPath) {
       return new IPropertyUtil(propertyPath);
    }
    /**
     * return the value of specified property name
     * @method getProperty
     * @param name
     * @return String
     */
    public String getProperty(String name) {
        return getProperty(name, null);
    }
    /**
     * return the value of specified property name and return defaultValue if not found
     * @param name
     * @param defaultValue
     * @return String
     */
    public String getProperty(String name, String defaultValue) {
        return manager.getProperty(name,defaultValue);
    }
    
}
