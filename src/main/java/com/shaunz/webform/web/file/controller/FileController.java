package com.shaunz.webform.web.file.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.shaunz.configurations.ShaunzProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.shaunz.framework.common.utils.IArrayListUtil;
import com.shaunz.framework.common.utils.IStringUtil;
import com.shaunz.framework.common.utils.MultipartFileUtil;
import com.shaunz.framework.web.base.BaseController;

@Controller
public class FileController extends BaseController{
	@Autowired
	private ShaunzProperties shaunzProperties;
	private static String[] IMAGE_SUBFIX = {"png","bmp","dib","jpg","jpeg","jpe","jfif","gif","notify()","tiff"};
	@RequestMapping(value="/file/image_lst.html")
	public String imagePage(){
		return "file/image_lst";
	}
	@RequestMapping(value="/file/image_upload.html")
	public String imageUploadPage(){
		return "file/image_upload";
	}
	@RequestMapping(value="/file/document_lst.html")
	public String docPage(){
		return "file/document_lst";
	}
	
	@RequestMapping(value="/file/imageLst",method=RequestMethod.GET)
	@ResponseBody
	public String imageLst(String path){
		List<Map<String, String>> imageLst = null;
		String imageDir = shaunzProperties.getHome()+shaunzProperties.getImageFolder()+(path.equals("/")?"":path);
		File imageFolder = new File(imageDir);
		if(imageFolder.exists()){
			imageLst = listFilesForFolder(imageFolder,shaunzProperties.getHome(),path);
		}
		return convertToJsonString(imageLst);
	}
	
	@RequestMapping(value="/file/image",method=RequestMethod.POST)
	@ResponseBody
	public String uploadImage(MultipartFile file,String fileNm,String path,Locale locale){
		String imageUrl = null;
		boolean flag = false;
		try {
			if(file != null){
				if(IStringUtil.isBlank(fileNm)){
					fileNm = "img_"+new Date().getTime();
				}
				imageUrl = MultipartFileUtil.uploadFileReNmIfExist(file, shaunzProperties.getHome(), shaunzProperties.getImageFolder(),fileNm);
				if(IStringUtil.notBlank(imageUrl)){
					flag = true;
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return formSubmitResult(flag, "common.addMsg", new Object[]{messageSource.getMessage("image.title", null, locale),imageUrl}
				, locale);
	}
	
	@RequestMapping(value = "/file/image", method = RequestMethod.GET)
    @ResponseBody
    public void downloadImage(String fileNms,String path,HttpServletResponse response) throws Exception {
		String imageDir = shaunzProperties.getHome()+shaunzProperties.getImageFolder()+(path.equals("/")?"":path);
		String[] fileNmArr = fileNms.split(",");
		List<File> images = new ArrayList<File>();
		if(!IArrayListUtil.isBlankArray(fileNmArr)){
			for (int i = 0; i < fileNmArr.length; i++) {
				String imagePath = imageDir+fileNmArr[i];
				File image = new File(imagePath);
				if(image.exists()){
					images.add(image);
				}
			}
		}
		
		try {
			File outputFile = new File(shaunzProperties.getHome()+shaunzProperties.getTmpFolder()+"download_"+new Date().getTime()+".zip");
			if(images.size() ==1){
				outputFile = images.get(0);
			} else if(images.size() > 1){
				outputFile.createNewFile();
				MultipartFileUtil.zipFile(images, outputFile);
			}
			MultipartFileUtil.download(request, response, outputFile);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
	
	@RequestMapping(value="/file/image",method=RequestMethod.DELETE)
	@ResponseBody
	public String deleteImage(String fileNms,String path,Locale locale){
		boolean flag = false;
		List<String> failedLst = new ArrayList<String>();
		String imageDir = shaunzProperties.getHome()+shaunzProperties.getImageFolder()+(path.equals("/")?"":path);
		String[] fileNmArr = fileNms.split(",");
		if(!IArrayListUtil.isBlankArray(fileNmArr)){
			for (int i = 0; i < fileNmArr.length; i++) {
				String imagePath = imageDir+fileNmArr[i];
				File image = new File(imagePath);
				if(image.exists()){
					boolean deleted = image.delete();
					if(!deleted){
						failedLst.add(image.getName());
					}
				}
			}
		}
		if(IArrayListUtil.isBlankList(failedLst)){
			flag = true;
		} else {
			fileNms = failedLst.toArray().toString();
		}
		return formSubmitResult(flag, "common.deleteMsg", new Object[]{messageSource.getMessage("image.title", null, locale),fileNms}
		, locale);
	}
	
	@RequestMapping(value="/file/folder",method=RequestMethod.POST)
	@ResponseBody
	public String createFolder(String name,String path,Locale locale){
		boolean flag = false;
		String imageDir = shaunzProperties.getHome()+shaunzProperties.getImageFolder()+(path.equals("/")?"":path);
		File folder = new File(imageDir+name);
		if(folder.isDirectory()&&!folder.exists()){
			flag = folder.mkdir();
		}
		
		return formSubmitResult(flag, "common.deleteMsg", new Object[]{messageSource.getMessage("image.title", null, locale),name}
		, locale);
	}
	
	private List<Map<String, String>> listFilesForFolder(File folder,String webRootPath,String folderPath){
		List<Map<String, String>> imageLst = new ArrayList<Map<String,String>>();
		for (final File fileEntry : folder.listFiles()) {
			Map<String, String> fileProfile = new HashMap<String, String>();
			fileProfile.put("url", "/"+fileEntry.getPath().replace(webRootPath, ""));
			fileProfile.put("name", fileEntry.getName());
			fileProfile.put("value", fileEntry.getName());
			fileProfile.put("folderPath", folderPath);
	        if (fileEntry.isDirectory()) {
	        	fileProfile.put("type", "folder");
	        } else {
	        	fileProfile.put("type", "file");
	        }
	        imageLst.add(fileProfile);
	    }
		return imageLst;
	}
	
	
}
