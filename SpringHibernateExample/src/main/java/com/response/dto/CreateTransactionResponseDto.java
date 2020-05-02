package com.response.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateTransactionResponseDto {

	@JsonProperty("body")
	private CreateTransactionResponseBody body;

	public CreateTransactionResponseBody getBody() {
		return body;
	}

	public void setBody(CreateTransactionResponseBody body) {
		this.body = body;
	}

	@Override
	public String toString() {
		String toString = "{";

		toString += ",body:" + getBody().toString();
		toString += "}";

		return toString;
	}

}
