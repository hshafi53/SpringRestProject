package com.response.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValidateCardResponsedto {
	
	@JsonProperty("pin")

	private Long pin;
	
	public String toString() {
		String toString = "{";
		if(pin!= null){
			toString += "PIN:\""+pin+"\""; 
		}
		
		toString += "}";
		return toString;
	
	
	}

	public Long getPin() {
		return pin;
	}

	public void setPin(Long pin) {
		this.pin = pin;
	}
	

}
