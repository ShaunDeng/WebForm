package com.shaunz.framework.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

public class JavaXmlBindUtil {
	private static Logger logger = Logger.getLogger(JavaXmlBindUtil.class);
	/**
	 * POJO to XML OutputStream
	 * @method marShallBean
	 * @param clazz POJO Class
	 * @param target POJO instance
	 * @param os XML OutputStream
	 * @throws JAXBException
	 */
	public static void marShallBean(Object target,OutputStream os,Class<?> ... clazz)throws JAXBException{
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.marshal(target, os);
		} catch (JAXBException e) {
			logger.error("JavaXmlBindUtil#marShallBean"+e.getMessage());
			throw e;
		}
	}
	
	/**
	 * POJO to XML String
	 * @method marShallBeanToXmlStr
	 * @param clazz POJO Class
	 * @param target POJO instance
	 * @param os XML OutputStream
	 * @return
	 * @throws JAXBException
	 */
	public static String marShallBean(Object target,Class<?> ... clazz)throws JAXBException{
		String str = "";
		OutputStream os = null;
		try {
			os = new ByteArrayOutputStream();
			JavaXmlBindUtil.marShallBean(target, os,clazz);
			str = os.toString();
		} catch (JAXBException e) {
			logger.error("JavaXmlBindUtil#marShallBean"+e.getMessage());
			throw e;
		} finally {
			if(os != null)
				try {
					os.close();
				} catch (IOException e) {
					logger.error("JavaXmlBindUtil#marShallBean"+e.getMessage());
				}
		}
		return str;
	}
	
	/**
	 * XML InputStream to POJO
	 * @method unMarShallXml
	 * @param clazz POJO Class
	 * @param is XML InputStream
	 * @return
	 * @throws JAXBException
	 */
	public static Object unMarShallXml(InputStream is,Class<?> ... clazz)throws JAXBException{
		Object target = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			target = unmarshaller.unmarshal(is);
		} catch (JAXBException e) {
			logger.error("JavaXmlBindUtil#unMarShallXml"+e.getMessage());
			throw e;
		}
		return target;
	}
	
	/**
	 * XML String to POJO
	 * @method unMarShallXml
	 * @param clazz POJO Class
	 * @param str XML String
	 * @return
	 * @throws JAXBException
	 */
	public static Object unMarShallXml(String str,Class<?> ... clazz)throws JAXBException{
		Object target = null;
		InputStream input = null;
		try {
			str = str.replaceAll("<\\?xml\\s+version=(['\"])1.0(['\"])\\s+encoding=(['\"])UTF-8(['\"])\\s*\\?>", "");
			Pattern pattern = Pattern.compile("\t|\r|\n");
            Matcher matcher = pattern.matcher(str);
            str = matcher.replaceAll("");
			input = new ByteArrayInputStream(str.getBytes());
			target = unMarShallXml(input, clazz);
		} catch (JAXBException e) {
			logger.error("JavaXmlBindUtil#unMarShallXml"+e.getMessage());
			throw e;
		} finally {
			if(input != null)
				try {
					input.close();
				} catch (IOException e) {
					logger.error("JavaXmlBindUtil#unMarShallXml"+e.getMessage());
				}
		}
		return target;
	}
	
}
