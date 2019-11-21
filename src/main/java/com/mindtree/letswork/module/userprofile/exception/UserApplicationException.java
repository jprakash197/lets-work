package com.mindtree.letswork.module.userprofile.exception;

@SuppressWarnings("serial")
public class UserApplicationException extends Exception{

	public UserApplicationException() {
		super();
	}

	public UserApplicationException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public UserApplicationException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public UserApplicationException(String arg0) {
		super(arg0);
	}

	public UserApplicationException(Throwable arg0) {
		super(arg0);
	}

}
