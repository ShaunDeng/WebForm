package com.shaunz.transfersystem.web.driver.entity;

import java.util.Date;

import com.shaunz.framework.core.BaseEntity;

public class Driver extends BaseEntity{
    private String name;

    private String gender;

    private Date dayOfBirth;

    private String phoneNumber;

    private String idNumber;

    private String driveLicenseNumber;

    private String drivingYears;

    private String closeFlg;

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