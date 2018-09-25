package com.shaunz.webform.web.author.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shaunz.framework.common.SequenceGenerator;
import com.shaunz.framework.common.auditlogs.ShaunzAuditLog;
import com.shaunz.framework.web.base.BaseController;
import com.shaunz.webform.web.author.entity.Author;
import com.shaunz.webform.web.author.service.AuthorService;

@Controller
public class AuthorContoller extends BaseController{
	@Autowired
	SequenceGenerator sequenceGenerator;
	@Autowired
	AuthorService authorService;
	
	@RequestMapping(value="/author/author_lst.html",method=RequestMethod.GET)
	public String authorLstPage(){
		return "author/author_lst";
	}
	@RequestMapping(value="/author/author_add.html",method=RequestMethod.GET)
	public String authorAddPage(){
		return "author/author_add";
	}
	@RequestMapping(value="/author/author_edit.html",method=RequestMethod.GET)
	public ModelAndView authorEditPage(String id){
		Map<String, Object> result = new HashMap<String, Object>();
		Author author = authorService.selectByPrimaryKey(id);
		result.put("author", author);
		return new ModelAndView("author/author_edit",result);
	}
	
	@RequiresPermissions("14.query")
	@RequestMapping(value="/author",method=RequestMethod.GET)
	@ResponseBody
	public String authorLst(){
		List<Author> authors = authorService.queryList();
		return convertToJsonString(authors);
	}
	
	@RequiresPermissions("14.add")
	@RequestMapping(value="/author",method=RequestMethod.POST)
	@ResponseBody
	@ShaunzAuditLog(optType="add",functionId="14")
	public String authorAdd(Author author,BindingResult bindingResult,Locale locale){
		Map<String, String> results = new HashMap<String, String>();
		if(bindingResult.hasErrors()){
			results.put("result", "error");
			results.put("message", messageSource.getMessage("validation.failed",null,locale));
		} else {
			author.setId(""+sequenceGenerator.getNextMngmtSequenceNo());
			author.setCloseFlg("N");
			boolean flag = authorService.insertSelective(author);
			author.setOptFlag(flag);
			return formSubmitResult(flag, "common.addMsg", new Object[]{messageSource.getMessage("author.title", null, locale),author.getName()}
					, locale);
		}
		return convertToJsonString(results);
	
	}
	
	@RequiresPermissions("14.update")
	@RequestMapping(value="/author",method=RequestMethod.PUT)
	@ResponseBody
	@ShaunzAuditLog(optType="update",functionId="14")
	public String authorEdit(Author author,Locale locale){
		boolean flag = authorService.updateByPrimaryKeySelective(author);
		author.setOptFlag(flag);
		return formSubmitResult(flag, "common.updateMsg", new Object[]{messageSource.getMessage("author.title", null, locale),author.getName()}
		, locale);
	}
	
	@RequiresPermissions("14.delete")
	@RequestMapping(value="/author/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	@ShaunzAuditLog(optType="delete",functionId="14")
	public String authorDelete(@PathVariable("id") String id,Locale locale){
		Author author = authorService.selectByPrimaryKey(id);
		boolean flag = authorService.closeAuthor(author);
		return formSubmitResult(flag, "common.deleteMsg", new Object[]{messageSource.getMessage("author.title", null, locale),author.getName()}
		, locale);
	}
}
