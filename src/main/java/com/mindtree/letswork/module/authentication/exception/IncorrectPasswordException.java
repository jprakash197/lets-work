package com.mindtree.letswork.module.authentication.exception;

public class IncorrectPasswordException extends AuthApplicationException {
	private static final long serialVersionUID = 1L;
	public IncorrectPasswordException() {
		super();
	}

	public IncorrectPasswordException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public IncorrectPasswordException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public IncorrectPasswordException(String arg0) {
		super(arg0);
	}

	public IncorrectPasswordException(Throwable arg0) {
		super(arg0);
	}

}
