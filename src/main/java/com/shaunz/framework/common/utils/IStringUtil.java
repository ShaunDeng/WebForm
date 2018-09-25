package com.shaunz.framework.common.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @since 2016-07-01
 * @author Shaun
 * @version 1.0.0
 *
 */
public class IStringUtil {

	/**
	 * Convert json String to Map<String, String>
	 * @method convertJsonToMap
	 * @param jsonStr
	 * @return Map<String, String>
	 */
	public static Map<String, String> convertJsonToMap(String jsonStr)
	{
		Map<String, String> map = new LinkedHashMap<String, String>(); 
		String repStr = jsonStr.replace("[", "").replace("]", "").trim(); 
        String[] arrayStr = repStr.split(","); 
        for (int i = 0; i < arrayStr.length; i++) { 
            String[] stringArray = arrayStr[i].split(":"); 
            map.put(stringArray[0], stringArray[1]); 
        }
        return map;
	}

	/**
	 * @method notBlank
	 * @param str 
	 * @return null|""  false, otherwise true 
	 */
	public static boolean notBlank(String str){
		return str != null && !"".equals(str.trim());
	}
	
	/**
	 * @method isBlank
	 * @param str
	 * @return null|""  true, otherwise false 
	 */
	public static boolean isBlank(String str){
		return str == null || "".equals(str.trim());
	}
	
	/**
	 * @method isTrueNotBlank
	 * @param str
	 * @return null|""|"null"|"NULL"..  false, otherwise true 
	 */
	public static boolean isTrueNotBlank(String str){
		return str != null && !"".equals(str.trim())&&!"null".equalsIgnoreCase(str.trim());
	}
	
	/**
	 * Convert unicode to String 
	 * @param unicodeStr
	 * @return
	 */
    public static String unicodeToChinese(String unicodeStr) {  
        StringBuilder buf = new StringBuilder();  
        if (IStringUtil.notBlank(unicodeStr)){
        	for (int i = 0; i < unicodeStr.length(); i++) {  
        		char char1 = unicodeStr.charAt(i);  
        		if (char1 == '\\' && isUnicodeAt(unicodeStr, i)) {  
        			String cStr = unicodeStr.substring(i + 2, i + 6);  
        			int cInt = Integer.parseInt(cStr,16);  
        			buf.append((char) cInt);
        			i = i + 5;  
        		} else {  
        			buf.append(char1);  
        		}  
        	}  
        }
        return buf.toString();  
    }  
  
    /**
     * Check if the specified location include unicode
     * @method isUnicodeAt
     * @param unicodeStr
     * @param i
     * @return
     */
    private static boolean isUnicodeAt(String unicodeStr, int i) {  
        int len = unicodeStr.length();  
        int remain = len - i;
        if (remain < 5)  
            return false;
        char flag2 = unicodeStr.charAt(i + 1);  
        if (flag2 != 'u')  
            return false;  
        String nextFour = unicodeStr.substring(i + 2, i + 6);  
        return isHexStr(nextFour);  
    }  
  
    /**
     * Check if the specified String is hex String(1-9 a-f A-F)
     * @param str
     * @return
     */
    private static boolean isHexStr(String str) {  
        for (int i = 0; i < str.length(); i++) {  
            char ch = str.charAt(i);  
            boolean isHex = ch >= '0' && ch <= '9' || ch >= 'a' && ch <= 'f'  
                    || ch >= 'A' && ch <= 'F';  
            if (!isHex)  
                return false;  
        }  
        return true;  
    }  
    /** 
     * Convert Chinese to Unicode
     * @param str 
     * @return 
     */  
    public static String chineseToUnicode(String str){  
        String result="";  
        for (int i = 0; i < str.length(); i++){
            int chr1 = (char) str.charAt(i);  
            if(isChineseCharacter((char) str.charAt(i))){
                result+="\\u" + Integer.toHexString(chr1);  
            }else{
                result+=str.charAt(i);  
            }  
        }  
        return result;  
    }  
  
    /** 
     * Check if it's Chinese Character
     * @param c 
     * @return 
     */  
    private static boolean isChineseCharacter(char c) {  
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);  
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS  
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS  
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A  
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION  
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION  
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {  
            return true;  
        }  
        return false;  
    }  
}
