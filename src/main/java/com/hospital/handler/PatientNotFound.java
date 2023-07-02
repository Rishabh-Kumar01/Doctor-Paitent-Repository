package com.hospital.handler;

public class PatientNotFound extends RuntimeException {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public PatientNotFound(String message) {
		super();
		this.message = message;
	}
}
