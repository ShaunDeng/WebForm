package com.shaunz.webform.web.blog.controller;

import com.shaunz.framework.common.SequenceGenerator;
import com.shaunz.framework.common.auditlogs.ShaunzAuditLog;
import com.shaunz.framework.web.base.BaseController;
import com.shaunz.webform.web.blog.entity.Blog;
import com.shaunz.webform.web.blog.service.BlogService;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BlogController extends BaseController{

	@Autowired
	SequenceGenerator sequenceGenerator;
	@Autowired
	BlogService blogService;
	
	@RequestMapping(value="/blog/blog_lst.html",method=RequestMethod.GET)
	public String lstPage(){
		return "blog/blog_lst";
	}
	@RequestMapping(value="/blog/blog_add.html",method=RequestMethod.GET)
	public String addPage(){
		return "blog/blog_add";
	}
	@RequestMapping(value="/blog/blog_edit.html",method=RequestMethod.GET)
	public ModelAndView editPage(String id){
		Map<String, Object> result = new HashMap<String, Object>();
		Blog blog = blogService.selectByPrimaryKey(id);
		result.put("blog", blog);
		return new ModelAndView("blog/blog_edit",result);
	}
	
	@RequiresPermissions("16.query")
	@RequestMapping(value="/blog",method=RequestMethod.GET)
	@ResponseBody
	public String lst(){
		List<Blog> blogs = blogService.queryList();
		return convertToJsonString(blogs);
	}
	
	@RequiresPermissions("16.add")
	@RequestMapping(value="/blog",method=RequestMethod.POST)
	@ResponseBody
	@ShaunzAuditLog(optType="add",functionId="16")
	public String add(Blog blog,BindingResult bindingResult,Locale locale){
		Map<String, String> results = new HashMap<String, String>();
		if(bindingResult.hasErrors()){
			results.put("result", "error");
			results.put("message", messageSource.getMessage("validation.failed",null,locale));
		} else {
			blog.setId(""+sequenceGenerator.getNextMngmtSequenceNo());
			blog.setCloseFlg("N");
			boolean flag = blogService.insertSelective(blog);
			blog.setOptFlag(flag);
			return formSubmitResult(flag, "common.addMsg", new Object[]{messageSource.getMessage("blog.title", null, locale),blog.getName()}
					, locale);
		}
		return convertToJsonString(results);
	
	}
	
	@RequiresPermissions("16.update")
	@RequestMapping(value="/blog",method=RequestMethod.PUT)
	@ResponseBody
	@ShaunzAuditLog(optType="update",functionId="16")
	public String edit(Blog blog,Locale locale){
		boolean flag = blogService.updateByPrimaryKeySelective(blog);
		blog.setOptFlag(flag);
		return formSubmitResult(flag, "common.updateMsg", new Object[]{messageSource.getMessage("blog.title", null, locale),blog.getName()}
		, locale);
	}
	
	@RequiresPermissions("16.delete")
	@RequestMapping(value="/blog/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	@ShaunzAuditLog(optType="delete",functionId="16")
	public String delete(@PathVariable("id") String id,Locale locale){
		Blog blog = blogService.selectByPrimaryKey(id);
		boolean flag = blogService.close(blog);
		return formSubmitResult(flag, "common.deleteMsg", new Object[]{messageSource.getMessage("blog.title", null, locale),blog.getName()}
		, locale);
	}

}
