package com.bkap.model;

import com.bkap.entity.DonationPlaces;
import com.bkap.entity.History;

public class HistoryResponse {
	private History history;
	private DonationPlaces place;
	
	
	public HistoryResponse() {
		super();
	}


	public HistoryResponse(History history, DonationPlaces place) {
		super();
		this.history = history;
		this.place = place;
	}


	public History getHistory() {
		return history;
	}


	public void setHistory(History history) {
		this.history = history;
	}


	public DonationPlaces getPlace() {
		return place;
	}


	public void setPlace(DonationPlaces place) {
		this.place = place;
	}
	
	
	
	

}
