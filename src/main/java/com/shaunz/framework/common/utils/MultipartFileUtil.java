package com.shaunz.framework.common.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

/**
 * 基于Springmvc的文件上传下载工具
 * @since 2016-07-01
 * @author Shaun
 * @version 1.0.0
 */
public class MultipartFileUtil{
	private static Logger logger = LoggerFactory.getLogger(MultipartFileUtil.class);
	private final static String SYS_SPRTR = File.separator;
	
	public final static String FILE_TP_HTML= "text/html";
	public final static String FILE_TP_XML="text/xml";
	public final static String FILE_TP_XHTML="application/xhtml+xml";
	public final static String FILE_TP_TEXT="text/plain";
	public final static String FILE_TP_RTF="application/rtf";
	public final static String FILE_TP_PDF="application/pdf";
	public final static String FILE_TP_MSWORD="application/msword";
	public final static String FILE_TP_PNG="image/png";
	public final static String FILE_TP_GIF="image/gif";
	public final static String FILE_TP_JPEG="image/jpeg";
	public final static String FILE_TP_GZIP="application/x-gzip";
	public final static String FILE_TP_TAR="application/x-tar";
	public final static String FILE_TP_BINARY="application/octet-stream";
	
	/**
	 * 验证文件类型
	 * @param file 目标文件
	 * @param fileType 文件类型
	 * @return true 符合类型要求
	 */
	public static boolean fileTypeValidate(MultipartFile file,String[] fileType){
		boolean flag = false;
		if(file != null && !file.isEmpty() && fileType !=null && fileType.length > 0){
			for (int i = 0; i < fileType.length; i++) {
				flag = fileTypeValidate(file,fileType[i]);
				if(flag){
					break;
				}
			}
		}
		return flag;
	}
	/**
	 * 验证文件类型
	 * @param file 目标文件
	 * @param fileType 文件类型
	 * @return true 符合类型要求
	 */
	public static boolean fileTypeValidate(MultipartFile file,String fileType){
		boolean flag = false;
		if(file != null && !file.isEmpty() && fileType !=null && fileType.trim() != ""){
			if(fileType.equals(file.getContentType())){
				flag = true;
			}
		}
		return flag;
	}
	/**
	 * 验证文件大小
	 * @param file 目标文件
	 * @param maxSize 允许的最大字节数
	 * @param minSize 允许的最小字节数
	 * @return true 符合大小要求
	 * @throws Exception
	 */
	public static boolean fileSizeValidate(MultipartFile file,long minSize,long maxSize) throws Exception{
		boolean flag = false;
		try {
			if(file != null && !file.isEmpty()){
				if((file.getSize() <= maxSize) && (file.getSize() >=minSize)){
					flag = true;
				}
			}
		} catch (Exception e) {
			logger.error("MultipartFileUploadUtil.fileSizeValidate", e);
			throw new Exception(e.getMessage());
		}
		return flag;
	}
	/**
	 * 验证文件大小
	 * @param file 目标文件
	 * @param maxSize 允许的最大字节数
	 * @return true 符合大小要求
	 * @throws Exception
	 */
	public static boolean fileSizeValidate(MultipartFile file,long maxSize) throws Exception{
		return fileSizeValidate(file,0,maxSize);
	}
	/**
	 * 上传指定文件，当文件名冲突时自动重命名.重命名规则:文件名后加"_1","_2"...
	 * @param file 目标文件
	 * @param webRootPath  WEB项目根目录
	 * @param relativePath	目标文件存储的相对路径，不包含文件名
	 * @return String 目标文件的相对路径(含文件名),"-1"为存储失败
	 * @throws Exception
	 */
	public static String uploadFileReNmIfExist(MultipartFile file,String webRootPath,String relativePath) throws Exception{
		return uploadFileReNmIfExist(file,webRootPath,relativePath,file.getName());
	}
	/**
	 * 上传指定文件，当文件名冲突时自动重命名.重命名规则:文件名后加"_1","_2"...
	 * @param file 目标文件
	 * @param webRootPath  WEB项目根目录
	 * @param relativePath	目标文件存储的相对路径，不包含文件名
	 * @param fileNm 自定义文件名(无后缀)
	 * @return String 目标文件的相对路径(含文件名),"-1"为存储失败
	 * @throws Exception
	 */
	public static String uploadFileReNmIfExist(MultipartFile file,String webRootPath,String relativePath,String fileNm) throws Exception{
		return uploadFileReNmIfExist(file,webRootPath,relativePath,fileNm,0);
	}
	private static String uploadFileReNmIfExist(MultipartFile file,String webRootPath,String relativePath,String fileNm,int index) throws Exception{
		String filePath = "-1";
		if(!file.isEmpty()){
			try {
				if("\\".equals(SYS_SPRTR) && relativePath.startsWith("\\\\")){
					relativePath = relativePath.replaceFirst("\\\\", "");
				} else if("/".equals(SYS_SPRTR) && relativePath.startsWith("/")){
					relativePath = relativePath.replaceFirst("/", "");
				}
				String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
				String fileDirct =webRootPath + relativePath
						+ fileNm + (index == 0?"":"_"+index) + fileSuffix;
				File targetDirct = new File(fileDirct);
				if(targetDirct.exists()){
					filePath = uploadFileReNmIfExist(file,webRootPath,relativePath,fileNm,++index);
				} else {
					file.transferTo(targetDirct);
					filePath = SYS_SPRTR+relativePath+ fileNm + (index == 0?"":"_"+index) + fileSuffix;
					if("\\".equals(SYS_SPRTR)){
						filePath = filePath.replaceAll("\\\\", "/");
					}
				}
			} catch (Exception e) {
				logger.error("MultipartFileUploadUtil.uploadFileReNmIfExist", e);
				throw new Exception(e.getMessage());
			}
		}
		return filePath;
	}
	/**
	 * 上传指定文件
	 * @param file 目标文件
	 * @param webRootPath  WEB项目根目录
	 * @param relativePath	目标文件存储的相对路径,不包含文件名
	 * @return boolean true:成功,false:失败
	 * @throws Exception
	 */
	public static boolean uploadFile(MultipartFile file,String webRootPath,String relativePath) throws Exception{
		boolean flag = false;
		if(!file.isEmpty()){
			try {
				if("\\".equals(SYS_SPRTR) && relativePath.startsWith("\\\\")){
					relativePath = relativePath.replaceFirst("\\\\", "");
				} else if("/".equals(SYS_SPRTR) && relativePath.startsWith("/")){
					relativePath = relativePath.replaceFirst("/", "");
				}
				String fileDirct =webRootPath + relativePath
						+ file.getOriginalFilename();
				File targetDirct = new File(fileDirct);
				if(targetDirct.exists()){
					return false;
				} else {
					file.transferTo(targetDirct);
					flag = true;
				}
			} catch (Exception e) {
				logger.error("MultipartFileUploadUtil.uploadFile", e);
				throw new Exception(e.getMessage());
			}
		}
		return flag;
	}
	/**
	 * 上传指定文件
	 * @param file 目标文件
	 * @param fileDirct	目标文件存储的绝对路径,不包含文件名
	 * @return boolean true:成功,false:失败
	 * @throws Exception
	 */
	public static boolean uploadFile(MultipartFile file,String fileDirct) throws Exception{
		boolean flag = false;
		if(!file.isEmpty()){
			try {
				String filePath =fileDirct
						+ file.getOriginalFilename();
				File targetDirct = new File(filePath);
				if(targetDirct.exists()){
					return false;
				} else {
					file.transferTo(targetDirct);
					flag = true;
				}
			} catch (Exception e) {
				logger.error("MultipartFileUploadUtil.uploadFile", e);
				throw new Exception(e.getMessage());
			}
		}
		return flag;
	}
	/**
	 * 删除指定文件
	 * @param webRootPath WEB项目根目录
	 * @param filePath 目标文件存储的相对路径,包含文件名
	 * @return true 当删除成功或者指定文件不存在时
	 * @throws Exception
	 */
	public static boolean deleteFile(String webRootPath,String filePath) throws Exception{
		boolean flag = false;
		try {
			if("\\".equals(SYS_SPRTR) && filePath.startsWith("\\\\")){
				filePath = filePath.replaceFirst("\\\\", "");
			} else if("/".equals(SYS_SPRTR) && filePath.startsWith("/")){
				filePath = filePath.replaceFirst("/", "");
			}
			flag = deleteFile(webRootPath+filePath);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return flag;
	}
	/**
	 * 删除指定文件
	 * @param filePath 目标文件存储的绝对路径,包含文件名
	 * @return true 当删除成功或者指定文件不存在时
	 * @throws Exception
	 */
	public static boolean deleteFile(String filePath) throws Exception{
		boolean flag = false;
		try {
			File file = new File(filePath);
			if(!file.exists()){
				return true;
			} else {
				flag = file.delete();
			}
		} catch (Exception e) {
			logger.error("MultipartFileUploadUtil.deleteFileIfExists", e);
			throw new Exception(e.getMessage());
		}
		return flag;
	}
	
	/**
	 * 下载指定文件到前台页面（采用二进制传输）
	 * @param response HttpServletResponse 对象
	 * @param file 目标文件
	 * @return true or false
	 * @throws Exception
	 */
	public static boolean downloadFile(HttpServletResponse response,File file) throws Exception{
		return downloadFile(response,file,"application/octet-stream");
	}
	/**
	 * 下载指定文件到前台页面（采用非二进制传输，请自行设置content-type）
	 * @param response HttpServletResponse 对象
	 * @param file 目标文件
	 * @param responseContentTp 响应头类型
	 * @return true or false
	 * @throws Exception
	 */
	public static boolean downloadFile(HttpServletResponse response,File file,String responseContentTp) throws Exception{//
		boolean flag = false;
		FileInputStream fileInputStream = null;
		BufferedInputStream bufferedInputStream = null;
		OutputStream outputStream = null;
		if(file == null || !file.isFile()){
			return flag;
		}
		try {
			response.reset();
			response.setHeader("Content-Type", responseContentTp);
			response.setHeader("Content-Disposition", "attachment;filename="+
					URLEncoder.encode(file.getName(), "UTF-8"));
			response.setHeader("Content-Length", "" + file.length());
			outputStream = response.getOutputStream();
			fileInputStream = new FileInputStream(file);
			bufferedInputStream = new BufferedInputStream(fileInputStream);
		    byte[] contents = new byte[1024*1024*2];  
		    int bytesNum = 0;
		    while((bytesNum = bufferedInputStream.read(contents)) !=-1){
		    	outputStream.write(contents, 0, bytesNum);
		    }
		    outputStream.flush();
		    flag = true;
		} catch (Exception e) {
			logger.error("MultipartFileUploadUtil.deleteFileIfExists", e);
			flag = false;
			throw new Exception(e.getMessage());
		} finally {
			try {
				if(bufferedInputStream != null){
					bufferedInputStream.close();
				}
				if(fileInputStream != null){
					fileInputStream.close();
				}
				if(outputStream != null){
					outputStream.close();
				}
			} catch (Exception ex) {
				logger.error("MultipartFileUploadUtil.deleteFileIfExists", ex);
			}
		}
	    return flag;
	}
	
	/**
	 * 兼容$.ajaxFileUpload 的回调函数,调用此方法的控制器不用设置返回值
	 * @param response HttpServletResponse 对象
	 * @param responseObject 推荐使用HashMap<String,String>
	 * @throws Exception
	 */
	public static void responseWithJsonString(HttpServletResponse response,  
	        Object responseObject) throws Exception{  
	    JSONObject responseJSONObject = (JSONObject) JSONObject.toJSON(responseObject);
	    response.setCharacterEncoding("UTF-8");  
	    response.setContentType("text/html; charset=utf-8");  
	    PrintWriter out = null;  
	    try {  
	        out = response.getWriter();  
	        out.append(responseJSONObject.toString());
	    } catch (IOException e) {  
	    	throw new Exception(e.getMessage());
	    } finally {  
	        if (out != null) {  
	            out.close();  
	        }  
	    }  
	}  
	
	/**
	   * Zip a list of file into one zip file.
	   * 
	   * @param files
	   *          files to zip
	   * @param targetZipFile
	   *          target zip file
	   * @throws IOException
	   *           IO error exception can be thrown when copying ...
	   */
	  public static void zipFile(final List<File> files, final File targetZipFile) throws IOException {
	    try {
	      FileOutputStream  fos = new FileOutputStream(targetZipFile);
	      ZipOutputStream zos = new ZipOutputStream(fos);
	      byte[] buffer = new byte[128];
	      for (int i = 0; i < files.size(); i++) {
	        File currentFile = files.get(i);
	        if (!currentFile.isDirectory()) {
	          ZipEntry entry = new ZipEntry(currentFile.getName());
	          FileInputStream fis = new FileInputStream(currentFile);
	          zos.putNextEntry(entry);
	          int read = 0;
	          while ((read = fis.read(buffer)) != -1) {
	            zos.write(buffer, 0, read);
	          }
	          zos.closeEntry();
	          fis.close();
	        }
	      }
	      zos.close();
	      fos.close();
	    } catch (FileNotFoundException e) {
	      logger.error(e.getMessage());
	    }
	  }
	  
	  public static void download(HttpServletRequest request,HttpServletResponse response,File outputFile) throws IOException{
		  String mimeType = ((HttpServletRequest)request).getServletContext().getMimeType(outputFile.getPath());
          
          if (mimeType == null) {
              mimeType = "application/octet-stream";
          }

          response.setContentType(mimeType);
          response.addHeader("Content-Disposition", "attachment; filename=" + outputFile.getName());
          response.setContentLength((int) outputFile.length());

          OutputStream os = response.getOutputStream();
          FileInputStream fis = new FileInputStream(outputFile);
          byte[] buffer = new byte[4096];
          int b = -1;

          while ((b = fis.read(buffer)) != -1) {
              os.write(buffer, 0, b);
          }

          fis.close();
          os.close();
	  }
}
