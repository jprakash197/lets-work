package com.mindtree.letswork.module.venue.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class CityNotFoundException extends VenueException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CityNotFoundException(String message)
	{
		super(message);
	}

	public CityNotFoundException() {
		super();
	}

	public CityNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public CityNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public CityNotFoundException(Throwable arg0) {
		super(arg0);
	}
	
}
