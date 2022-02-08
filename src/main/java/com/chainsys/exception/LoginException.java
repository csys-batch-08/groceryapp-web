package com.chainsys.exception;

public class LoginException extends Exception {

	private static final long serialVersionUID = 1L;

	public String getUserNameLoginMessage() {
		return "Sorry, username or password incorrect!";
	}

}
