package com.mindtree.letswork.module.booking.exception;

import com.mindtree.letswork.exception.LetsWorkException;

public class PaymentApplicationException extends LetsWorkException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PaymentApplicationException() {
		super();
	}

	public PaymentApplicationException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public PaymentApplicationException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public PaymentApplicationException(String arg0) {
		super(arg0);
	}

	public PaymentApplicationException(Throwable arg0) {
		super(arg0);
	}
	
}
