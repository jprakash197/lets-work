package com.mindtree.letswork.module.authentication.exception;

import com.mindtree.letswork.exception.LetsWorkException;

public class AuthApplicationException extends LetsWorkException {

	private static final long serialVersionUID = 1L;

	public AuthApplicationException() {
		super();
	}

	public AuthApplicationException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public AuthApplicationException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public AuthApplicationException(String arg0) {
		super(arg0);
	}

	public AuthApplicationException(Throwable arg0) {
		super(arg0);
	}

}
