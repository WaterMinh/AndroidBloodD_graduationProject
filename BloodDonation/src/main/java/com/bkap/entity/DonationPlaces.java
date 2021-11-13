package com.bkap.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DonationPlaces")

public class DonationPlaces {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "idPlace")
	private Integer idPlace;
	@Column (name = "namePlace")
	private String namePlace;
	@Column (name = "dayStart")
	private Date dayStart;
	@Column (name = "dayEnd")
	private Date dayEnd;
	@Column (name = "timeOpen")
	private String timeOpen;
	@Column (name = "address")
	private String address;
	
	
	public DonationPlaces() {
		super();
	}

	

	public DonationPlaces(Integer idPlace, String namePlace, Date dayStart, Date dayEnd, String timeOpen,
			String address) {
		super();
		this.idPlace = idPlace;
		this.namePlace = namePlace;
		this.dayStart = dayStart;
		this.dayEnd = dayEnd;
		this.timeOpen = timeOpen;
		this.address = address;
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



	public String getTimeHappen() {
		return timeOpen;
	}



	public void setTimeHappen(String timeHappen) {
		this.timeOpen = timeHappen;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}
	
	



}
