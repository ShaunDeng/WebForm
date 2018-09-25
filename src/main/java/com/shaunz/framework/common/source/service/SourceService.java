package com.shaunz.framework.common.source.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shaunz.framework.common.source.dao.SourceMapper;
import com.shaunz.framework.common.source.entity.Source;
import com.shaunz.framework.common.utils.IStringUtil;
import com.shaunz.framework.core.YgdrasilConst;
import com.shaunz.framework.web.base.BaseService;

@Service
public class SourceService extends BaseService{
	@Resource
	private SourceMapper sourceMapper;
	@Autowired
	private SourceValidator sourceValidator;
	private static HashMap<String, HashMap<String,Source>> sourceTables;
	
	private List<Source> retrieveWholeSourceTable(){
		return sourceMapper.quaryAll();
	}
	
	public SourceService(){
		
	}
	
	public void init() {
		List<Source> sources = retrieveWholeSourceTable();
		Source source;
		
		if(sourceTables == null){
			sourceTables = new HashMap<String, HashMap<String,Source>>();
		}
		if(sources != null && sources.size() > 0){
			for (int i = 0; i < sources.size(); i++) {
				HashMap<String,Source> tmpLst;
				
				source = sources.get(i);
				if(sourceTables.containsKey(source.getGroupNm())){
					tmpLst = sourceTables.get(source.getGroupNm());
				} else {
					tmpLst = new HashMap<String,Source>();
					sourceTables.put(source.getGroupNm(), tmpLst);
				}
				tmpLst.put(source.getName(), source);
			}
		} else {
			logger.warn("System retrieved nothing from tb_source...");
		}
		logger.info("tb_source generation finished...");
	}
	
	public HashMap<String,Source> getSourceBy(String groupNm){
		return sourceTables.get(groupNm);
	}
	
	public String getSourceValueBy(String groupNm,String name){
		String value = "";
		HashMap<String,Source> sources = sourceTables.get(groupNm);
		Source source;
		if(sources != null && sources.size() > 0){
			source = sources.get(name);
			if(source != null){
				value = source.getValue();
			}
		}
		return value;
	}
	
	public String getHomePageParameterby(String name){
		return getSourceValueBy("homepage",name);
	}
	
	public String getSystemParameterBy(String name){
		return getSourceValueBy("System",name);
	}
	
	public String updateSource(String groupNm,String name,String value){
		Source source = sourceTables.get(groupNm).get(name);
		if(source != null && IStringUtil.notBlank(source.getId())){
			String validateMsg = sourceValidator.validate(source);
			if(IStringUtil.isBlank(validateMsg)){
				source.setValue(value);
				if(sourceMapper.updateByPrimaryKey(source) == 1){
					return YgdrasilConst.SUCCESS;
				}
			} else {
				return validateMsg;
			}
		}
		return YgdrasilConst.FAILED;
	}
}
