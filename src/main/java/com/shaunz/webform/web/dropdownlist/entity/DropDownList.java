package com.shaunz.webform.web.dropdownlist.entity;

import com.shaunz.framework.core.BaseEntity;

public class DropDownList extends BaseEntity{
    private String name;

    private String url;

    private String type;

    private String parentId;
    
    private String closeFlg;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

	public String getCloseFlg() {
		return closeFlg;
	}

	public void setCloseFlg(String closeFlg) {
		this.closeFlg = closeFlg;
	}
    
}