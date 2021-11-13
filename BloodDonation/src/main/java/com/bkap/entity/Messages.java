package com.bkap.entity;

public class Messages {
	private int id;
	private String mess;
	public Messages(int id, String mess) {
		super();
		this.id = id;
		this.mess = mess;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMess() {
		return mess;
	}
	public void setMess(String mess) {
		this.mess = mess;
	}

}
