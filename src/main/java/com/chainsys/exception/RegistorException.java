package com.chainsys.exception;

public class RegistorException extends Exception {

	private static final long serialVersionUID = 1L;

	public String getUserNameLoginMessage() {
		return "Sorry, User Already exist!";
	}
}
