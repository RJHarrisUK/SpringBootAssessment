package com.poke.util;

@SuppressWarnings("serial")
public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String exception) {
		super("Id supplied does not exist. Id: " + exception);
	}

}
