package com.shaunz.transfersystem.web.order.entity;

public class OrderDetail {
    private String id;

    private String hdrId;

    private String passengerId;

    private String departurePlace;

    private String destination;

    private String income;

    private String closeFlg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getHdrId() {
        return hdrId;
    }

    public void setHdrId(String hdrId) {
        this.hdrId = hdrId == null ? null : hdrId.trim();
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

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income == null ? null : income.trim();
    }

    public String getCloseFlg() {
        return closeFlg;
    }

    public void setCloseFlg(String closeFlg) {
        this.closeFlg = closeFlg == null ? null : closeFlg.trim();
    }
}