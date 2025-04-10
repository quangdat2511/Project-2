package com.javaweb.myexception;

public class DistrictNotFoundException extends RuntimeException{
	public DistrictNotFoundException(String message) {
		super(message);
	}
}