package com.shaunz.framework.web.filters.xss;



import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XSSHttpServletRequestWrapper extends HttpServletRequestWrapper {

	HttpServletRequest orgRequest = null;

	/**
	 * @param request
	 */
	public XSSHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		orgRequest = request;
	}

	/**
	 * 覆盖getParameter方法，将参数名和参数值都做xss过滤。<br/>
	 * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取<br/>
	 */
	public String getParameter(String name) {
		String value = super.getParameter(sanitize(name));
		if (value != null) {
			value = sanitize(value);
		}
		return value;
	}
	
	public String[] getParameterValues(String parameter) {

		String[] values = super.getParameterValues(parameter);
		if (values == null) {
			return null;
		}
		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodedValues[i] = sanitize(values[i]);
		}
		return encodedValues;
	}
	public Map getParameterMap() {
		Map map = super.getParameterMap();
		Iterator iter = (map.keySet() != null) ? map.keySet().iterator() : null;
		String key = null;
		String[] values = null;
		if (iter != null) {
			while (iter.hasNext()) {
				key = (String) iter.next();
				if (key != null) {
					values = (String[]) map.get(key);
					for (int i = 0; i < values.length; i++)
						values[i] = sanitize(values[i]);
				}
			}
		}

		return map;
	}

	/**
	 * 覆盖getHeader方法，将参数名和参数值都做xss过滤。<br/>
	 * 如果需要获得原始的值，则通过super.getHeaders(name)来获取<br/>
	 * getHeaderNames 也可能需要覆盖
	 */
	public String getHeader(String name) {
		String value = super.getHeader(sanitize(name));
		if (value != null) {
			value = sanitize(value);
		}
		return value;
	}
	/**
	 * 利用 HtmlSanitizer.sanitize(s);函数处理xss问题 
	 * 
	 * @param s
	 * @return
	 */
	private static String sanitize(String s) {
		return xssEncode(s);
	}
	/**
	 * 将容易引起xss漏洞的半角字符直接替换成全角字符
	 * 
	 * @param s
	 * @return
	 */
	private static String xssEncode(String s) {
		if (s == null || "".equals(s.trim())) {
			return s;
		}
		StringBuilder sb = new StringBuilder(s.length() + 16);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '>':
				sb.append('＞');// 全角大于号
				break;
			case '<':
				sb.append('＜');// 全角小于号
				break;
			case '\'':
				sb.append('‘');// 全角单引号
				break;
			case '\"':
				sb.append('“');// 全角双引号
				break;
			case '&':
				sb.append('＆');// 全角
				break;
			case '\\':
				sb.append('＼');// 全角斜线
				break;
			case '#':
				sb.append('＃');// 全角井号
				break;
			default:
				sb.append(c);
				break;
			}
		}
		return sb.toString();
	}

	/**
	 * 获取最原始的request
	 * 
	 * @return
	 */
	public HttpServletRequest getOrgRequest() {
		return orgRequest;
	}

	/**
	 * 获取最原始的request的静态方法
	 * 
	 * @return
	 */
	public static HttpServletRequest getOrgRequest(HttpServletRequest req) {
		if (req instanceof XSSHttpServletRequestWrapper) {
			return ((XSSHttpServletRequestWrapper) req).getOrgRequest();
		}

		return req;
	}

}
