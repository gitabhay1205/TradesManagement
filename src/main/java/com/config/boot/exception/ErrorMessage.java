package com.config.boot.exception;

import java.time.LocalDate;
import java.util.Date;

public class ErrorMessage {
	

	public ErrorMessage(int errorCode, String errorMessage, LocalDate timeStamp, String httpStatus) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.timeStamp = timeStamp;
		this.httpStatus = httpStatus;
	}
	public LocalDate getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDate timeStamp) {
		this.timeStamp = timeStamp;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(String httpStatus) {
		this.httpStatus = httpStatus;
	}
	private int errorCode;
	private String errorMessage;
	private LocalDate timeStamp;
	private String httpStatus;

}
