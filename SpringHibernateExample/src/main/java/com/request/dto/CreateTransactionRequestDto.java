package com.request.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateTransactionRequestDto {

	@JsonProperty("body")
	private CreateTransactionRequestBody createTransactionBody;

	@JsonIgnore
	public CreateTransactionRequestBody getCreateTransactionBody() {
		return createTransactionBody;
	}

	public void setCreateTransactionBody(CreateTransactionRequestBody createTransactionBody) {
		this.createTransactionBody = createTransactionBody;
	}

	public String toString() {
		String toString = "{";

		toString += ",body:" + getCreateTransactionBody().toString();
		toString += "}";
		return toString;
	}

}
