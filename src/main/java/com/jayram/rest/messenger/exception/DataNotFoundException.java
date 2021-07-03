package com.jayram.rest.messenger.exception;

public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2257930762011244461L;

	public DataNotFoundException(String message){
		super(message);
	}
}
