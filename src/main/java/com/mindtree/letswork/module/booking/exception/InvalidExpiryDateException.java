package com.mindtree.letswork.module.booking.exception;

public class InvalidExpiryDateException extends PaymentApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidExpiryDateException() {
		super();
	}

	public InvalidExpiryDateException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InvalidExpiryDateException(String arg0) {
		super(arg0);
	}

	public InvalidExpiryDateException(Throwable arg0) {
		super(arg0);
	}
	

}
