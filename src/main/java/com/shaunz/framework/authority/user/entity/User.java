package com.shaunz.framework.authority.user.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.shaunz.framework.common.utils.EncryptUtil;
import com.shaunz.framework.common.utils.IStringUtil;
import com.shaunz.framework.core.BaseEntity;
import com.shaunz.framework.core.YgdrasilConst;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class User extends BaseEntity{
    @NotNull
    @Size(min=2,max=100)
    private String loginName;

    @NotNull
    @Size(min=8,max=100)
    private String password;

    @Size(max=100)
    private String orgPath;

    @Size(min=2,max=100)
    private String aliasNm;

    @NotNull
    private String gender;

    @NotNull
    private String email;

    private String closeFlg;

    @NotNull
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

	public void attmptSignTimesPlusOne(){
		if(IStringUtil.isBlank(attemptSignTimes)){
			attemptSignTimes = "0";
		}
		int times = 0;
		try {
			times = Integer.valueOf(attemptSignTimes) +1;
		} catch (Exception e) {
			log.error(e.getMessage());
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
	
	public void encryptSensitiveMsg(){
		setPassword(EncryptUtil.encryptString(getPassword()));
	}
	
	public void decryptSensitiveMsg(){
		setPassword(EncryptUtil.decryptString(getPassword()));
	}
}