package com.shaunz.framework.authority.role.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.shaunz.framework.core.BaseEntity;
import com.shaunz.framework.core.YgdrasilConst;

public class Role extends BaseEntity{

    @NotNull
    @Size(min=2,max=100)
    private String name;

    private String parentId;

    private String closeFlg;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    
    private String startTimeString;
    
    private String endTimeString;

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

    public String getCloseFlg() {
        return closeFlg;
    }

    public void setCloseFlg(String closeFlg) {
        this.closeFlg = closeFlg == null ? null : closeFlg.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    
	public String getStartTimeString() {
		return startTimeString;
	}

	public void setStartTimeString(String startTimeString) {
		this.startTimeString = startTimeString;
	}

	public String getEndTimeString() {
		return endTimeString;
	}

	public void setEndTimeString(String endTimeString) {
		this.endTimeString = endTimeString;
	}

	public void dateConverter(){
		SimpleDateFormat dateFormat = new SimpleDateFormat(YgdrasilConst.DATE_FORMART);
		if(startTime != null){
			this.startTimeString = dateFormat.format(startTime);
		}
		if(endTime != null){
			this.endTimeString = dateFormat.format(endTime);
		}
	}
}