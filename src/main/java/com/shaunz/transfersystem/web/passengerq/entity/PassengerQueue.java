package com.shaunz.transfersystem.web.passengerq.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.shaunz.framework.common.utils.CustomerDateAndTimeDeserialize;
import com.shaunz.framework.core.BaseEntity;
import com.shaunz.framework.core.YgdrasilConst;

public class PassengerQueue extends BaseEntity{
    private String passengerId;

    private String departurePlace;

    private String destination;

	@JsonDeserialize(using=CustomerDateAndTimeDeserialize.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    private Date registerTime;
	
	private String registerTimeString;

	@JsonDeserialize(using=CustomerDateAndTimeDeserialize.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    private Date departureTime;
	
	private String departureTimeString;

    private String capacity;

    private String closeFlg;
    
	public void dateConverter(){
		SimpleDateFormat dateFormat = new SimpleDateFormat(YgdrasilConst.DATE_FORMART);
		if(registerTime != null){
			this.registerTimeString = dateFormat.format(registerTime);
		}
		if(departureTime != null){
			this.departureTimeString = dateFormat.format(departureTime);
		}
	}

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId == null ? null : passengerId.trim();
    }

    public String getDeparturePlace() {
        return departurePlace;
    }

    public void setDeparturePlace(String departurePlace) {
        this.departurePlace = departurePlace == null ? null : departurePlace.trim();
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination == null ? null : destination.trim();
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }
    
	public String getRegisterTimeString() {
		return registerTimeString;
	}

	public void setRegisterTimeString(String registerTimeString) {
		this.registerTimeString = registerTimeString;
	}

	public Date getDepartureTime() {
        return departureTime;
    }
	
	public String getDepartureTimeString() {
		return departureTimeString;
	}

	public void setDepartureTimeString(String departureTimeString) {
		this.departureTimeString = departureTimeString;
	}

	public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity == null ? null : capacity.trim();
    }

    public String getCloseFlg() {
        return closeFlg;
    }

    public void setCloseFlg(String closeFlg) {
        this.closeFlg = closeFlg == null ? null : closeFlg.trim();
    }
}