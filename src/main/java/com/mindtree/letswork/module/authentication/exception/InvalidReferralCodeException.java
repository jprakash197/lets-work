package com.mindtree.letswork.module.authentication.exception;

public class InvalidReferralCodeException extends AuthApplicationException{
	private static final long serialVersionUID = 1L;
	public InvalidReferralCodeException() {
		super();
	}

	public InvalidReferralCodeException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public InvalidReferralCodeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InvalidReferralCodeException(String arg0) {
		super(arg0);
	}

	public InvalidReferralCodeException(Throwable arg0) {
		super(arg0);
	}

}
