package com.mindtree.letswork.module.venue.exception;

import com.mindtree.letswork.exception.LetsWorkException;

public class VenueException extends LetsWorkException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VenueException() {
		super(); 
	}

	public VenueException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	} 

	public VenueException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public VenueException(String arg0) {
		super(arg0);
	}

	public VenueException(Throwable arg0) {
		super(arg0);
	}

}
