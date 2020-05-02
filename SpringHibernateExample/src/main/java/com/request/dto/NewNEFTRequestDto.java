package com.request.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NewNEFTRequestDto {
	@JsonProperty("body")
	private NewNEFTRequestBody createTransactionBody;

	@JsonIgnore
	public NewNEFTRequestBody getCreateTransactionBody() {
		return createTransactionBody;
	}

	public void setCreateTransactionBody(NewNEFTRequestBody createTransactionBody) {
		this.createTransactionBody = createTransactionBody;
	}

	public String toString() {
		String toString = "{";

		toString += ",body:" + getCreateTransactionBody().toString();
		toString += "}";
		return toString;
	}

}
