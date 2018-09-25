package com.shaunz.webform.web.image.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.shaunz.framework.common.SequenceGenerator;
import com.shaunz.framework.common.auditlogs.ShaunzAuditLog;
import com.shaunz.framework.common.utils.MultipartFileUtil;
import com.shaunz.framework.core.YgdrasilConst;
import com.shaunz.framework.web.base.BaseController;
import com.shaunz.webform.web.carousel.service.CarouselService;
import com.shaunz.webform.web.home.entity.HomePage;
import com.shaunz.webform.web.image.entity.Image;
import com.shaunz.webform.web.image.service.ImageService;
import com.shaunz.webform.web.marketinfo.service.MarketInfoService;

@Controller
public class ImageController extends BaseController{
	@Autowired
	SequenceGenerator sequenceGenerator;
	@Autowired
	ImageService imageService;
	@Autowired
	CarouselService carouselService;
	@Autowired
	MarketInfoService marketInfoService;
	
	@RequestMapping(value="/image/image_lst.html",method=RequestMethod.GET)
	public String lstPage(){
		return "image/image_lst";
	}
	@RequestMapping(value="/image/image_add.html",method=RequestMethod.GET)
	public String addPage(){
		return "image/image_add";
	}
	@RequestMapping(value="/image/image_edit.html",method=RequestMethod.GET)
	public ModelAndView editPage(String id){
		Map<String, Object> result = new HashMap<String, Object>();
		Image image = imageService.selectByPrimaryKey(id);
		result.put("image", image);
		return new ModelAndView("image/image_edit",result);
	}
	
	@RequiresPermissions("10.query")
	@RequestMapping(value="/image",method=RequestMethod.GET)
	@ResponseBody
	public String lst(){
		List<Image> images = imageService.queryList();
		return convertToJsonString(images);
	}
	
	@RequiresPermissions("10.add")
	@RequestMapping(value="/image",method=RequestMethod.POST)
	@ResponseBody
	@ShaunzAuditLog(optType="add",functionId="10")
	public String add(MultipartFile file,Image image,BindingResult bindingResult,Locale locale){
		Map<String, String> results = new HashMap<String, String>();
		if(bindingResult.hasErrors()){
			results.put("result", "error");
			results.put("message", messageSource.getMessage("validation.failed",null,locale));
		} else {
			String imageUrl = null;
			try {
				if(file != null){
					String webRootDir = ((HttpServletRequest)request).getServletContext().getRealPath("/");
					imageUrl = MultipartFileUtil.uploadFileReNmIfExist(file, webRootDir, YgdrasilConst.CUSTOMER_IMAGE_PATH, "img_"+new Date().getTime());
					image.setUrl(imageUrl);
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			image.setId(""+sequenceGenerator.getNextMngmtSequenceNo());
			image.setCloseFlg("N");
			boolean flag = imageService.insertSelective(image);
			image.setOptFlag(flag);
			if(flag){
				refreshHomepage();
			}
			return formSubmitResult(flag, "common.addMsg", new Object[]{messageSource.getMessage("image.title", null, locale),image.getName()}
					, locale);
		}
		return convertToJsonString(results);
	
	}
	
	@RequiresPermissions("10.update")
	@RequestMapping(value="/image/edition",method=RequestMethod.POST)
	@ResponseBody
	@ShaunzAuditLog(optType="update",functionId="10")
	public String edit(MultipartFile file,Image image,Locale locale){
		String imageUrl = null;
		try {
			if(file != null){
				String webRootDir = ((HttpServletRequest)request).getServletContext().getRealPath("/");
				imageUrl = MultipartFileUtil.uploadFileReNmIfExist(file, webRootDir, YgdrasilConst.CUSTOMER_IMAGE_PATH, "img_"+new Date().getTime());
				image.setUrl(imageUrl);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		boolean flag = imageService.updateByPrimaryKeySelective(image);
		image.setOptFlag(flag);
		if(flag){
			refreshHomepage();
		}
		return formSubmitResult(flag, "common.updateMsg", new Object[]{messageSource.getMessage("image.title", null, locale),image.getName()}
		, locale);
	}
	
	@RequiresPermissions("10.delete")
	@RequestMapping(value="/image/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	@ShaunzAuditLog(optType="delete",functionId="10")
	public String delete(@PathVariable("id") String id,Locale locale){
		Image image = imageService.selectByPrimaryKey(id);
		boolean flag = imageService.close(image);
		if(flag){
			refreshHomepage();
		}
		return formSubmitResult(flag, "common.deleteMsg", new Object[]{messageSource.getMessage("image.title", null, locale),image.getName()}
		, locale);
	}
	
	private void refreshHomepage(){
		HomePage homePage = null;
		try {
			homePage = (HomePage)request.getServletContext().getAttribute("homePageObject");
			homePage.setCarousels(carouselService.quaryAll());
			homePage.setMarketInfos(marketInfoService.quaryAll());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}


}
