package com.shaunz.webform.web.navigationbar.entity;

import java.util.List;

import com.shaunz.framework.core.BaseEntity;
import com.shaunz.webform.web.dropdownlist.entity.DropDownList;

public class NavigationBar extends BaseEntity{

    private String name;

    private String type;

    private String url;

    private String active;
    
    private List<DropDownList> downLists;
    
    private String closeFlg;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active == null ? null : active.trim();
    }

	public List<DropDownList> getDownLists() {
		return downLists;
	}

	public void setDownLists(List<DropDownList> downLists) {
		this.downLists = downLists;
	}

	public String getCloseFlg() {
		return closeFlg;
	}

	public void setCloseFlg(String closeFlg) {
		this.closeFlg = closeFlg;
	}
}