package com.shaunz.webform.web.blogmap.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shaunz.framework.common.utils.IArrayListUtil;
import com.shaunz.framework.web.base.BaseService;
import com.shaunz.webform.web.blogmap.dao.BlogMapMapper;
import com.shaunz.webform.web.blogmap.entity.BlogMap;

@Service
public class BlogMapService extends BaseService{
	@Autowired
	private BlogMapMapper blogMapMapper;
    public boolean insert(BlogMap record){
    	return blogMapMapper.insert(record) == 1;
    }

    public boolean insertSelective(BlogMap record){
    	return blogMapMapper.insertSelective(record) == 1;
    }
    
    public List<BlogMap> queryList(BlogMap record){
    	return blogMapMapper.queryList(record);
    }
    
    public boolean delete(BlogMap record){
    	return blogMapMapper.delete(record) == 1;
    }
    
	public boolean blogRelate(String[] blogIds,String pageType, String pageId){
		boolean flag = false;
		List<BlogMap> oldLst = blogMapMapper.queryList(new BlogMap(pageId, pageType, null, null));
		List<BlogMap> newLst = new ArrayList<BlogMap>();
		for (int i = 0; i < blogIds.length; i++) {
			BlogMap blogMap = null;
			for (int j = 0; j < oldLst.size(); j++) {
				blogMap = oldLst.get(j);
				if(blogIds[i].equals(blogMap.getBlogId())){
					newLst.add(blogMap);
					break;
				}
				blogMap = null;
			}
			if(blogMap == null){
				blogMap = new BlogMap();
				blogMap.setBlogId(blogIds[i]);
				blogMap.setPageType(pageType);
				blogMap.setPageId(pageId);
				newLst.add(blogMap);
			}
		}
		int deletedRows = 0;
		if(!IArrayListUtil.isBlankList(oldLst)){
			deletedRows = blogMapMapper.delete(new BlogMap(pageId, pageType, null, null));
		}
		int insertedRows = 0;
		for (int i = 0; i < newLst.size(); i++) {
			insertedRows += blogMapMapper.insertSelective(newLst.get(i));
		}
		if((deletedRows == (oldLst==null?0:oldLst.size()))
				&&(insertedRows == newLst.size()))
			flag = true;
		return flag;
	}
	
	public boolean updateOrderByPageNBlog(List<BlogMap> blogMaps){
		boolean flag = false;
		int effectRows = 0;
		if(!IArrayListUtil.isBlankList(blogMaps)){
			for (int i = 0; i < blogMaps.size(); i++) {
				effectRows += blogMapMapper.updateOrderByPageNBlog(blogMaps.get(i));
			}
		}
		if(effectRows == (blogMaps == null?0:blogMaps.size())){
			flag = true;
		}
		return flag;
	}
}
