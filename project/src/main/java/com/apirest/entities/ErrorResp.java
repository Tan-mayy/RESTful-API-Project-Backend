package com.apirest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ErrorResp {
	@Id
	private int code;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "ErrorResp [code=" + code + ", message=" + message + "]";
	}
	public ErrorResp(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public ErrorResp() {
		super();
	}
	private String message;

}
