package com.shaunz.framework.authority.user.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.shaunz.framework.common.utils.IStringUtil;
import com.shaunz.framework.core.BaseEntity;
import com.shaunz.framework.core.YgdrasilConst;

public class User extends BaseEntity{
    @NotNull
    @Size(min=2,max=100)
    private String loginName;

    @NotNull
    @Size(min=8,max=100)
    private String password;

    private String orgPath;

    private String aliasNm;

    private String gender;

    @NotNull
    private String email;

    private String closeFlg;

    private String lockUp;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    
    private String rememberMe;
    
    private String inputUserNM;
    
    private String inputPwd;
    
    private String attemptSignTimes;
    
    private String startTimeString;
    
    private String endTimeString;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getOrgPath() {
        return orgPath;
    }

    public void setOrgPath(String orgPath) {
        this.orgPath = orgPath == null ? null : orgPath.trim();
    }

    public String getAliasNm() {
        return aliasNm;
    }

    public void setAliasNm(String aliasNm) {
        this.aliasNm = aliasNm == null ? null : aliasNm.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getCloseFlg() {
        return closeFlg;
    }

    public void setCloseFlg(String closeFlg) {
        this.closeFlg = closeFlg == null ? null : closeFlg.trim();
    }

    public String getLockUp() {
        return lockUp;
    }

    public void setLockUp(String lockUp) {
        this.lockUp = lockUp == null ? null : lockUp.trim();
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

	public String getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(String rememberMe) {
		this.rememberMe = rememberMe;
	}

	public String getInputUserNM() {
		return inputUserNM;
	}

	public void setInputUserNM(String inputUserNM) {
		this.inputUserNM = inputUserNM;
	}

	public String getInputPwd() {
		return inputPwd;
	}

	public void setInputPwd(String inputPwd) {
		this.inputPwd = inputPwd;
	}

	public String getAttemptSignTimes() {
		return attemptSignTimes;
	}

	public void setAttemptSignTimes(String attemptSignTimes) {
		this.attemptSignTimes = attemptSignTimes;
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

	public void attmptSignTimesPlusOne(){
		if(IStringUtil.isBlank(attemptSignTimes)){
			attemptSignTimes = "0";
		}
		int times = 0;
		try {
			times = Integer.valueOf(attemptSignTimes) +1;
		} catch (Exception e) {
			logger.error(e);
			times = 0;
		}
		attemptSignTimes = ""+times;
	}
	
	public void deSensitive(){
	    this.id = null;
	    this.loginName = null;
	    this.orgPath = null;
		this.password = null;
		this.email = null;
		this.inputPwd = null;
		this.lockUp = null;
		this.closeFlg = null;
	}
	
	public boolean isAvaliableData(){
		return IStringUtil.notBlank(id) || IStringUtil.notBlank(loginName)
				|| IStringUtil.notBlank(email);
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