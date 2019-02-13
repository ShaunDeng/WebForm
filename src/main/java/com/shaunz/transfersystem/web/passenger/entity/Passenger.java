package com.shaunz.transfersystem.web.passenger.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.shaunz.framework.common.utils.CustomerDateAndTimeDeserialize;
import com.shaunz.framework.core.BaseEntity;
import com.shaunz.framework.core.YgdrasilConst;

public class Passenger extends BaseEntity{
    private String name;

    private String phoneNumber;

    private String gender;

	@JsonDeserialize(using=CustomerDateAndTimeDeserialize.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    private Date dayOfBirth;
	
	private String dayOfBirthString;

    private String closeFlg;
    
	public void dateConverter(){
		SimpleDateFormat dateFormat = new SimpleDateFormat(YgdrasilConst.DATE_FORMART);
		if(dayOfBirth != null){
			this.dayOfBirthString = dateFormat.format(dayOfBirth);
		}
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public Date getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(Date dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }
    
	public String getDayOfBirthString() {
		return dayOfBirthString;
	}

	public void setDayOfBirthString(String dayOfBirthString) {
		this.dayOfBirthString = dayOfBirthString;
	}

	public String getCloseFlg() {
        return closeFlg;
    }

    public void setCloseFlg(String closeFlg) {
        this.closeFlg = closeFlg == null ? null : closeFlg.trim();
    }
}