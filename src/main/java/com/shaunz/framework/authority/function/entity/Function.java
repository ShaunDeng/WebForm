package com.shaunz.framework.authority.function.entity;

import java.util.ArrayList;
import java.util.List;

import com.shaunz.framework.core.BaseEntity;

public class Function extends BaseEntity{
    private String name;

    private String parentId;

    private String url;

    private String closeFlg;
    
    private String tableNm;
    
    private List<String> grantedAuthority;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getCloseFlg() {
        return closeFlg;
    }

    public void setCloseFlg(String closeFlg) {
        this.closeFlg = closeFlg == null ? null : closeFlg.trim();
    }

	public String getTableNm() {
		return tableNm;
	}

	public void setTableNm(String tableNm) {
		this.tableNm = tableNm;
	}

	public List<String> getGrantedAuthority() {
		return grantedAuthority;
	}

	public void setGrantedAuthority(List<String> grantedAuthority) {
		this.grantedAuthority = grantedAuthority;
	}
	
	public void setAuthority(String authorityId){
		if(this.grantedAuthority == null){
			this.grantedAuthority = new ArrayList<String>();
		}
		if(!grantedAuthority.contains(authorityId)){
			grantedAuthority.add(authorityId);
		}
	}
	
}