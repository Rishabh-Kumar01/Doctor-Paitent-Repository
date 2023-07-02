package com.hospital.handler;

public class AppointmentNotFound extends RuntimeException {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public AppointmentNotFound(String message) {
		super();
		this.message = message;
	}
}
