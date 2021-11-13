package com.bkap.entity;

public class BkResponse {
	private int id;
    private String message;
    
	public BkResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BkResponse(int id, String message) {
		super();
		this.id = id;
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
