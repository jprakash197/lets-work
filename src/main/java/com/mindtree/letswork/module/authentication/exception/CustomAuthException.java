package com.mindtree.letswork.module.authentication.exception;

import java.util.Date;

public class CustomAuthException{
	
	private String message;
	private Date timestamp;
	private String details;
	
	public CustomAuthException() {}
	public CustomAuthException(String message, Date timestamp, String details) {
		super();
		this.message = message;
		this.timestamp = timestamp;
		this.details = details;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}

}
