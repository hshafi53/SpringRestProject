package com.response.dto;

/**
 * 
 * @author alokb
 *
 */
public class ValidationResponseDto {

	private int code;
	private String message;

	public ValidationResponseDto(int code, String message) {
		this.code = code;
		this.message = message;

	}

	public ValidationResponseDto() {

	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
