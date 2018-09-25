package com.shaunz.webform.web.blogmap.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shaunz.framework.common.utils.IArrayListUtil;
import com.shaunz.framework.common.utils.IStringUtil;
import com.shaunz.framework.web.base.BaseController;
import com.shaunz.webform.web.blog.service.BlogService;
import com.shaunz.webform.web.blogmap.entity.BlogMap;
import com.shaunz.webform.web.blogmap.service.BlogMapService;

@Controller
public class BlogMapController extends BaseController{
	@Autowired
	private BlogMapService blogMapService;
	@Autowired
	private BlogService blogService;
	@RequestMapping(value="/blogmap/blog_order.html",method=RequestMethod.GET)
	public ModelAndView blogSettingPage(String pageId,String pageType){
		Map<String, Object> result = new HashMap<String, Object>();
		BlogMap blogMap = new BlogMap(pageId,pageType,null,null);
		List<BlogMap> blogMaps = blogMapService.queryList(blogMap);
		result.put("pageId", pageId);
		result.put("pageType", pageType);
		List<Map<String, String>> choosedBlogMaps = new ArrayList<Map<String,String>>();
		if(!IArrayListUtil.isBlankList(blogMaps)){
			for (int i = 0; i < blogMaps.size(); i++) {
				blogMap = blogMaps.get(i);
				Map<String, String> map = new HashMap<String, String>();
				map.put("pageId", blogMap.getPageId());
				map.put("pageType", blogMap.getPageType());
				map.put("blogId", blogMap.getBlogId());
				map.put("orderId", blogMap.getOrderId());
				map.put("blogNm", blogService.selectByPrimaryKey(blogMap.getBlogId()).getName());
				choosedBlogMaps.add(map);
			}
			
		}
		result.put("choosedBlogMaps", choosedBlogMaps);
		return new ModelAndView("blogmap/blog_order",result);
	}
	
	@RequiresPermissions("16.update")
	@RequestMapping(value="/blogmap/order")
	@ResponseBody
	public String order(String pageId,String pageType,HttpServletRequest request,Locale locale){
		BlogMap blogMap = new BlogMap(pageId,pageType,null,null);
		List<BlogMap> blogMaps = blogMapService.queryList(blogMap);
		if(!IArrayListUtil.isBlankList(blogMaps)){
			for (int i = 0; i < blogMaps.size(); i++) {
				blogMap = blogMaps.get(i);
				String order = request.getParameter(blogMap.getBlogId()+"_orderId") == null?"":request.getParameter(blogMap.getBlogId()+"_orderId");
				if(IStringUtil.notBlank(order)){
					blogMap.setOrderId(order);
				}
			}
			
		}
		boolean flag = blogMapService.updateOrderByPageNBlog(blogMaps);
		return formSubmitResult(flag, "common.grantMsg", new Object[]{messageSource.getMessage("role.title", null, locale),pageId}
		, locale);
	}
}
