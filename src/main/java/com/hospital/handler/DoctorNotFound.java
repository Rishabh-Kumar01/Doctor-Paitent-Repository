package com.hospital.handler;

public class DoctorNotFound extends RuntimeException {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public DoctorNotFound(String message) {
		super();
		this.message = message;
	}
}
