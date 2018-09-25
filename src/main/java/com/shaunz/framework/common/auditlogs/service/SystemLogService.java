package com.shaunz.framework.common.auditlogs.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shaunz.framework.authority.user.entity.User;
import com.shaunz.framework.common.SequenceGenerator;
import com.shaunz.framework.common.auditlogs.dao.SystemLogMapper;
import com.shaunz.framework.common.auditlogs.entity.SystemLog;
import com.shaunz.framework.common.utils.IArrayListUtil;
import com.shaunz.framework.common.utils.IStringUtil;
import com.shaunz.framework.common.utils.JsonUtil;
import com.shaunz.framework.core.BaseEntity;
import com.shaunz.framework.web.base.BaseService;

@Service
public class SystemLogService extends BaseService{
	@Autowired
	private SystemLogMapper systemLogMapper;
	@Autowired
	private SequenceGenerator sequenceGenerator;
	
	public boolean log(String optType,String functionId,List<Object> inputEntities,List<Object> outputEntities,User operator){
		boolean retFlag = true;
		if(IStringUtil.isBlank(optType) && IStringUtil.isBlank(functionId)){
			logger.warn("optType and functionId is empty");
			return false;
		}
		if(IArrayListUtil.isBlankList(inputEntities) 
				|| IArrayListUtil.isBlankList(outputEntities)
				|| inputEntities.size() != outputEntities.size()){
			logger.warn("inputEntities or outputEntities is empty");
			return false;
		}
		SystemLog systemLog = new SystemLog();
		systemLog.setFunctionId(functionId);
		systemLog.setOptType(optType);
		systemLog.setUserId(operator != null?operator.getId():null);
		systemLog.setOptTime(new Date());
		
		try {
			for (int i = 0; i < outputEntities.size(); i++) {
				SystemLog sysLog = (SystemLog) systemLog.clone();
				sysLog.setId("" + sequenceGenerator.getnextLogSequenceNo());
				String content = "";
				Object obj = outputEntities.get(i);
				if(obj instanceof BaseEntity){
					BaseEntity outputEntity = (BaseEntity)outputEntities.get(i);
					content = JsonUtil.toJsonString(outputEntity);
					sysLog.setTargetId(outputEntity.getId());
					sysLog.setOptFlag(outputEntity.getOptFlag());
					sysLog.setContent(content);
					
				} else {
					sysLog.setTargetId(obj.toString());
					sysLog.setOptFlag(true);
				}
				retFlag = retFlag && (systemLogMapper.insertSelective(sysLog) == 1);
				if(!retFlag){
					logger.warn("Insert " + content + " failed.");
				}
			}
		} catch (CloneNotSupportedException e) {
			logger.error(e.getMessage());
		}
		
		return retFlag;
	}
	
	public List<SystemLog> queryAll(SystemLog systemLog){
		return systemLogMapper.queryAll();
	}
	
}
