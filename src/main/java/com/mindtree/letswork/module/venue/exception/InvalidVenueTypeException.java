package com.mindtree.letswork.module.venue.exception;

public class InvalidVenueTypeException extends VenueException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidVenueTypeException() {
	}

	public InvalidVenueTypeException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public InvalidVenueTypeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InvalidVenueTypeException(String arg0) {
		super(arg0);
	}

	public InvalidVenueTypeException(Throwable arg0) {
		super(arg0);
	}

}
