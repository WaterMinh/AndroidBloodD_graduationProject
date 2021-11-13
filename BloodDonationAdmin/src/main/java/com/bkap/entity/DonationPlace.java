package com.bkap.entity;

import java.sql.Date;


public class DonationPlace {
	private Integer idPlace;
	private String namePlace;
	private Date dayStart;
	private Date dayEnd;
	private String address;
	private String timeOpen;
	
	public DonationPlace() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DonationPlace(Integer idPlace, String namePlace, Date dayStart, Date dayEnd, String address,
			String timeOpen) {
		super();
		this.idPlace = idPlace;
		this.namePlace = namePlace;
		this.dayStart = dayStart;
		this.dayEnd = dayEnd;
		this.address = address;
		this.timeOpen = timeOpen;
	}

	public DonationPlace(String namePlace, Date dayStart, Date dayEnd, String address, String timeOpen) {
		super();
		this.namePlace = namePlace;
		this.dayStart = dayStart;
		this.dayEnd = dayEnd;
		this.address = address;
		this.timeOpen = timeOpen;
	}

	public Integer getIdPlace() {
		return idPlace;
	}

	public void setIdPlace(Integer idPlace) {
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTimeOpen() {
		return timeOpen;
	}

	public void setTimeOpen(String timeOpen) {
		this.timeOpen = timeOpen;
	}

	
	
}
