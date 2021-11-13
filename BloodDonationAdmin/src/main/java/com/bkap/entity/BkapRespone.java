package com.bkap.entity;

public class BkapRespone {
	private int code;
	private boolean success;
	private String message;

	
	public BkapRespone() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BkapRespone(int code, Boolean success, String message) {
		super();
		this.code = code;
		this.success = success;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
