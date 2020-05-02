package com.response.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateTransactionResponseBody  {
	
	@JsonProperty("result")
	private Boolean result;
	@JsonProperty("errorcode")
	private String errorCode;
	@JsonProperty("transactionid")

	private Long transactionId;
	@JsonProperty("resultuimessage")
	private String resultUIMessage;
	@JsonProperty("Token")
	private String token;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	@JsonProperty("timestamp")
	private Timestamp timeStamp;
	
	
	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Timestamp transtime) {
		this.timeStamp = transtime;
	}
	public String getResultUIMessage() {
		return resultUIMessage;
	}
	public void setResultUIMessage(String resultUIMessage) {
		this.resultUIMessage = resultUIMessage;
	}
	public Boolean getResult() {
		return result;
	}
	public void setResult(Boolean result) {
		this.result = result;
	}
	public String getErrorcode() {
		return errorCode;
	}
	public void setErrorcode(String errorcode) {
		this.errorCode = errorcode;
	}
	public Long getTransactionid() {
		return transactionId;
	}
	public void setTransactionid(Long transrefId) {
		this.transactionId = transrefId;
	}
	
	@Override
	public String toString() {
		String toString = "{";
		if(result!= null){
			toString += "result:\""+result+"\""; 
		}
		if(errorCode!= null){
			toString += "errorcode:\""+errorCode+"\""; 
		}
		if(transactionId!= null){
			toString += "transactionid:\""+transactionId+"\""; 
		}
		toString += "}";
		return toString;
	
	
	}
}
