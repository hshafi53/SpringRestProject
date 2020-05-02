package com.request.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerDetail {

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@JsonProperty("customerId")
	private Long customerId;
	@JsonProperty("customerName")
	private String customerName;

	@Override
	public String toString() {
		String toString = "{";
		if (customerId != null) {
			toString += ",customerId:\"" + customerId + "\"";
		}
		if (customerName != null) {
			toString += ",customerName:\"" + customerName + "\"";
		}

		return toString;
	}

}
