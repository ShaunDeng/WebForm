package com.shaunz.framework.common.auditlogs.entity;

import java.util.Date;

import com.shaunz.framework.core.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemLog extends BaseEntity{
    private String userId;

    private String functionId;

    private String targetId;

    private String optType;

    private Date optTime;

    private String content;
    
    private String operatorAliasNm;
    
    private String functionNm;
    
    
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}