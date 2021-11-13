package com.example.projectblooddonation.entity;

import java.sql.Date;

public class DonationPlaces {
    private int idPlace;
    private String namePlace;
    private Date dayStart;
    private Date dayEnd;
    private String timeOpen;
    private String address;

    public DonationPlaces(int idPlace, String namePlace, Date dayStart, Date dayEnd, String timeOpen, String address) {
        this.idPlace = idPlace;
        this.namePlace = namePlace;
        this.dayStart = dayStart;
        this.dayEnd = dayEnd;
        this.timeOpen = timeOpen;
        this.address = address;
    }

    public DonationPlaces(int idPlace, String namePlace) {
        this.idPlace = idPlace;
        this.namePlace = namePlace;
    }

    public int getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(int idPlace) {
        this.idPlace = idPlace;
    }

    public String getNamePlace() {
        return namePlace;
    }

    public void setNamePlace(String namePlace) {
        this.namePlace = namePlace;
    }

    public Date getDayStart() {
        return dayStart;
    }

    public void setDayStart(Date dayStart) {
        this.dayStart = dayStart;
    }

    public Date getDayEnd() {
        return dayEnd;
    }

    public void setDayEnd(Date dayEnd) {
        this.dayEnd = dayEnd;
    }

    public String getTimeOpen() {
        return timeOpen;
    }

    public void setTimeOpen(String timeOpen) {
        this.timeOpen = timeOpen;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return namePlace;
    }
}
