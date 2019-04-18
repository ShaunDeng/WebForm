package com.shaunz.transfersystem.web.driver.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.shaunz.framework.common.utils.CustomerDateAndTimeDeserialize;
import com.shaunz.framework.core.BaseEntity;
import com.shaunz.framework.core.YgdrasilConst;

public class Driver extends BaseEntity{
    private String name;

    private String gender;

	@JsonDeserialize(using=CustomerDateAndTimeDeserialize.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    private Date dayOfBirth;
	
	private String dayOfBirthString;

    private String phoneNumber;

    private String idNumber;

    private String driveLicenseNumber;

    private String drivingYears;

    private String closeFlg;
    
	public void dateConverter(){
		SimpleDateFormat dateFormat = new SimpleDateFormat(YgdrasilConst.DATE_FORMART);
		if(dayOfBirth != null){
			this.dayOfBirthString = dateFormat.format(dayOfBirth);
		}
	}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

	public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber == null ? null : idNumber.trim();
    }

    public String getDriveLicenseNumber() {
        return driveLicenseNumber;
    }

    public void setDriveLicenseNumber(String driveLicenseNumber) {
        this.driveLicenseNumber = driveLicenseNumber == null ? null : driveLicenseNumber.trim();
    }

    public String getDrivingYears() {
        return drivingYears;
    }

    public void setDrivingYears(String drivingYears) {
        this.drivingYears = drivingYears == null ? null : drivingYears.trim();
    }

    public String getCloseFlg() {
        return closeFlg;
    }

    public void setCloseFlg(String closeFlg) {
        this.closeFlg = closeFlg == null ? null : closeFlg.trim();
    }
}