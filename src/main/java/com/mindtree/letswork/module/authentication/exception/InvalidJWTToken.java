package com.mindtree.letswork.module.authentication.exception;

public class InvalidJWTToken extends AuthApplicationException {
	private static final long serialVersionUID = 1L;
	public InvalidJWTToken() {
		super();
	}

	public InvalidJWTToken(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public InvalidJWTToken(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InvalidJWTToken(String arg0) {
		super(arg0);
	}

	public InvalidJWTToken(Throwable arg0) {
		super(arg0);
	}

}
