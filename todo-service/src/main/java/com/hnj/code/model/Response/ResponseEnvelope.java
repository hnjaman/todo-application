package com.hnj.code.model.Response;

import lombok.Data;

@Data
public class ResponseEnvelope<T> {

	private T data;
	private boolean success;
	private String message;
	private Integer errorCode;
	private Integer responseCode;

	public ResponseEnvelope(T data, boolean success, String message, Integer errorCode, Integer responseCode){
		this.success = success;
		this.message = message;
		this.errorCode = errorCode;
		this.responseCode = responseCode;
	}

	public ResponseEnvelope(boolean success, String message, Integer responseCode) {
		this.success = success;
		this.message = message;
		this.responseCode = responseCode;
	}

	public ResponseEnvelope(T data, boolean success, String message) {
		this.data = data;
		this.success = success;
		this.message = message;
	}

	public ResponseEnvelope(T data, boolean success, String message, Integer responseCode) {
		this.data = data;
		this.success = success;
		this.message = message;
		this.responseCode = responseCode;
	}
}
