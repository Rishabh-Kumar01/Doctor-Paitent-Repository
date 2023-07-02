package com.hospital.handler;

import java.util.List;

public class ResponseError {
	private String info;
	private List<String> messages;

	public ResponseError() {
	}

	public ResponseError(String info, List<String> messages) {
		super();
		this.info = info;
		this.messages = messages;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
}
