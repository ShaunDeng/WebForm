package com.shaunz.framework.authority.function.entity;

import java.util.ArrayList;
import java.util.List;

import com.shaunz.framework.core.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Function extends BaseEntity{
    private String name;

    private String parentId;

    private String url;

    private String closeFlg;
    
    private String tableNm;
    
    private String icon;
    
    private List<String> grantedAuthority;
	
	public void setAuthority(String authorityId){
		if(this.grantedAuthority == null){
			this.grantedAuthority = new ArrayList<String>();
		}
		if(!grantedAuthority.contains(authorityId)){
			grantedAuthority.add(authorityId);
		}
	}
	
}