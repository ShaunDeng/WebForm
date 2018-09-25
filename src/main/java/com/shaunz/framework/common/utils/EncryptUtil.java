package com.shaunz.framework.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;



/**
 * Encrypt and Decrypt
 * @author Shaun
 * @since 2016-07-01
 */
public class EncryptUtil
{
	private static Logger logger = Logger.getLogger(EncryptUtil.class);	
	
	private static final String ALG_STRING="AES";
	private static final String ALG_STRING_CIPHER="AES/CBC/PKCS5Padding";
	private static final String ALG_STRING_MD="MD5";
	private static final String DEFAULT_STRING="Sh@uN2.com";
	private static final String MD5="MD5";
	private static final String SHA="SHA";

	private static final byte[] initvector = { (byte) 0xcb, (byte) 0x53, (byte) 0x03,
			(byte) 0x0f, (byte) 0xe0, (byte) 0x79, (byte) 0x9d, (byte) 0xdc,
			(byte) 0x80, (byte) 0xa9, (byte) 0x83, (byte) 0xf1, (byte) 0x03,
			(byte) 0xb6, (byte) 0x59, (byte) 0x83 };
    
	/**
     * Use default key to encrypt String
     * @param str
     * @param pass
     * @return
     */
	public static String encryptString(String str)
	{
		try
		{
			
			return base64Encode(encrypt(str.getBytes("UTF-8"), DEFAULT_STRING));
		}
		catch (Exception e)
		{
			return str;
		}
	}
    
	/**
	 * Use default key to decrypt String
	 * @param str
	 * @param pass
	 * @return
	 */
	public static String decryptString(String str)
	{
		try
		{
			return new String(decrypt(base64Decode(str), DEFAULT_STRING), "UTF-8");
		}
		catch (Exception e)
		{
			return str;
		}
	}
	
	/**
	 * Encrypt String via reversing bytes
	 * @param str
	 * @return
	 */
    public static String eventEncrypt(String str)
    {
    	char[] rechar=str.toCharArray();
    	for (int i=0;i<rechar.length;i++)
    	{
    		rechar[i]=Character.reverseBytes(rechar[i]);
    	}
    	return new String(rechar);
    }
 
	/**
	 * Decrypt String via reversing bytes
	 * @param str
	 * @return
	 */   
    public static String eventDecrypt(String str)
    {
    	char[] rechar=str.toCharArray();
    	for (int i=0;i<rechar.length;i++)
    	{
    		rechar[i]=Character.reverseBytes(rechar[i]);
    	}
    	return new String(rechar);
    }
    
    /**
     * Use specified pass to encrypt bytes 
     * @param bytes
     * @param pass
     * @return
     */
    public static byte[] encrypt(byte[] bytes, String pass) {
		try
		{
			IvParameterSpec ipSpec = new IvParameterSpec(initvector);
			SecretKey key = new SecretKeySpec(genKeyFromPassword(pass),
					ALG_STRING);
			Cipher cipher = Cipher.getInstance(ALG_STRING_CIPHER);
			cipher.init(Cipher.ENCRYPT_MODE, key, ipSpec);
			byte[] cipherStr = cipher.doFinal(bytes);
			return cipherStr;
		}
		catch (Exception e)
		{
			return null;
		}
    }
    
    /**
     * Use specified pass to encrypt bytes 
     * @param bytes
     * @param pass
     * @return
     */
    public static byte[] decrypt(byte[] bytes, String pass) {
		try
		{
			IvParameterSpec ipSpec = new IvParameterSpec(initvector);
			SecretKey key = new SecretKeySpec(genKeyFromPassword(pass),
					ALG_STRING);
			Cipher cipher = Cipher.getInstance(ALG_STRING_CIPHER);
			cipher.init(Cipher.DECRYPT_MODE, key, ipSpec);
			byte[] strB = cipher.doFinal(bytes);
			return strB;
		}
		catch (Exception e)
		{
			return null;
		}
    }
    
    /**
     * Use specified pass to encrypt String
     * @param str
     * @param pass
     * @return
     */
	public static String encryptString(String str, String pass)
	{
		try
		{
			return base64Encode(encrypt(str.getBytes("UTF-8"), pass));
		}
		catch (Exception e)
		{
			return null;
		}
	}
    
	/**
	 * Use specified pass to decrypt String
	 * @param str
	 * @param pass
	 * @return
	 */
	public static String decryptString(String str, String pass)
	{
		try
		{
			return new String(decrypt(base64Decode(str), pass), "UTF-8");
		}
		catch (Exception e)
		{
			return null;
		}
	}

	/**
	 * 8*3 to 6*4
	 * @param bytes
	 * @return
	 */
	private static String base64Encode(byte[] bytes)
	{
		return new String(Base64.encodeBase64(bytes));
	}
	/**
	 * 6*4 to 8*3
	 * @param str
	 * @return
	 * @throws java.io.IOException
	 */
	private static byte[] base64Decode(String str)
			throws java.io.IOException
	{
		return Base64.decodeBase64(str.getBytes("utf-8"));
	}
	
	private static byte[] genKeyFromPassword(String str)
	{
		return md5sum(str.toString().getBytes());
	}

	private static byte[] md5sum(byte[] buffer)
	{
		try
		{
			MessageDigest md5 = MessageDigest.getInstance(ALG_STRING_MD);
			md5.update(buffer);
			return md5.digest();
		}
		catch (NoSuchAlgorithmException e)
		{
			logger.error("[EncryptUtil#md5sum] " + e.getMessage());
		}
		return null;
	}
	
	/**
	 * Encrypt file via the given pass
	 * @param efile
	 * @param pass
	 * @return
	 */
	public static boolean encryptFile(File efile,String pass)
	{
		try
		{
			char[] buffer=new char[1024];
			InputStreamReader sfilereader=new InputStreamReader(new FileInputStream(efile),"UTF-8");
		    StringWriter swriter=new StringWriter();
		    while (true)
		    {
		      int re=sfilereader.read(buffer);
		      if (re==-1)
		    	  break;
		      swriter.write(buffer,0,re);		      
		    }
		    String str=swriter.toString();
		    swriter.close();
		    sfilereader.close();
		    if (EncryptUtil.decryptString(str,pass)!=null)
		       return true;
		    String dstring=EncryptUtil.encryptString(str,pass);
		    if (dstring==null)
		    	return false;		    
		    OutputStreamWriter dfilewriter=new OutputStreamWriter(new FileOutputStream(efile),"UTF-8");
		    dfilewriter.write(dstring);
		    dfilewriter.close();
		} catch(Exception e) {
			logger.error("[EncryptUtil#encryptFile] " +efile.getName() , e);
			return false;
		}
		return true;
	}
	
	/**
	 * Decrypt file via the given pass
	 * @param dfile
	 * @param pass
	 * @return
	 */
	public static boolean decryptFile(File dfile,String pass)
	{
		try
		{
			char[] buffer=new char[1024];
			InputStreamReader sfilereader=new InputStreamReader(new FileInputStream(dfile),"UTF-8");
		    StringWriter swriter=new StringWriter();
		    while (true)
		    {
		      int re=sfilereader.read(buffer);
		      if (re==-1)
		    	  break;
		      swriter.write(buffer,0,re);		      
		    }
		    String dstr=EncryptUtil.decryptString(swriter.toString(),pass);
		    swriter.close();
		    sfilereader.close();
		    if (dstr==null)
		    	return false;
		    OutputStreamWriter dfilewriter=new OutputStreamWriter(new FileOutputStream(dfile),"UTF-8");
		    dfilewriter.write(dstr);
		    dfilewriter.close();
		}catch(Exception e)
		{
			logger.error("[EncryptUtil#decryptFile] " + dfile.getName() , e);
			return false;
		}
		return true;	
	}
    
	/**
	 * Use given pass to decrypt file return bytes
	 * @param dfile
	 * @param pass
	 * @return
	 */
	public static byte[] decryptFileToString(File dfile,String pass)
	{
		byte[] restr=null;
		try
		{
			char[] buffer=new char[1024];
			InputStreamReader sfilereader=new InputStreamReader(new FileInputStream(dfile),"UTF-8");
		    StringWriter swriter=new StringWriter();
		    while (true)
		    {
		      int re=sfilereader.read(buffer);
		      if (re==-1)
		    	  break;
		      swriter.write(buffer,0,re);		      
		    }
		    
		    String dstr=EncryptUtil.decryptString(swriter.toString(),pass);
		    if (dstr!=null)
		      restr=EncryptUtil.decryptString(swriter.toString(),pass).getBytes("UTF-8");
		    sfilereader.close();
		    swriter.close();		    
		} catch(Exception e) {
			logger.error("[EncryptUtil#decryptFileToString] " + dfile.getName() , e);
			return restr;
		}
		return restr;	
	}
	
	public static String getHash(String source, String hashType) {
	    StringBuilder sb = new StringBuilder();
	    MessageDigest md5;
	    try {
	      md5 = MessageDigest.getInstance(hashType);
	      md5.update(source.getBytes());
	      for (byte b : md5.digest()) {
	        sb.append(String.format("%02X", b));
	      }
	      return sb.toString();
	    } catch (NoSuchAlgorithmException e) {
	    	logger.error("[EncryptUtil#getHash] " + source + ",hashType" , e);
	    }
	    return null;
	}
	
	public static String MD5Encrypt(String source) {
	    StringBuilder sb = new StringBuilder();
	    MessageDigest md5;
	    try {
	      md5 = MessageDigest.getInstance(MD5);
	      md5.update(source.getBytes());
	      for (byte b : md5.digest()) {
	        sb.append(String.format("%02X", b));
	      }
	      return sb.toString();
	    } catch (NoSuchAlgorithmException e) {
	    	logger.error(e.getMessage());
	    }
	    return null;
	}
	public static String SHAEncrypt(String source) {
	    StringBuilder sb = new StringBuilder();
	    MessageDigest md5;
	    try {
	      md5 = MessageDigest.getInstance(SHA);
	      md5.update(source.getBytes());
	      for (byte b : md5.digest()) {
	        sb.append(String.format("%02X", b));
	      }
	      return sb.toString();
	    } catch (NoSuchAlgorithmException e) {
	    	logger.error(e.getMessage());
	    }
	    return null;
	}
	public static void main(String[] args) {
		String s="yJzROAz1RREm4WqVA5OGIg==";
		
		System.out.println(EncryptUtil.decryptString(s));
		
	}
}

