package com.hospital.handler;

public class ReceptionistNotFound extends RuntimeException {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ReceptionistNotFound(String message) {
		super();
		this.message = message;
	}
}
