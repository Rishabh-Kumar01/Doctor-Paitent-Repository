package com.hospital.handler;

public class AppointmentPendingException extends RuntimeException {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public AppointmentPendingException(String message) {
		super();
		this.message = message;
	}
}
