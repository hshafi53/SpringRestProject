package com.request.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateTransactionRequestBody {
	/*
	 * @JsonProperty("transactionid")
	 * 
	 * private Long transactionId;
	 */
	@JsonProperty("transtype")
	private String transType;
	@JsonProperty("paymenttype")
	private String paymentType;
	@JsonProperty("currencycode")
	private String currencyCode;
	/*
	 * @JsonProperty("itemtotal")
	 * 
	 * private Long itemTotal;
	 */
	/*
	 * @JsonProperty("totalamount")
	 * 
	 * private Long totalAmount;
	 */
	@JsonProperty("status")

	private int Status;

	@JsonProperty("items")
	private List<ItemDesc> itemDesc;

	public List<ItemDesc> getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(List<ItemDesc> itemDesc) {
		this.itemDesc = itemDesc;
	}

	public CustomerDetail getCustomerDetail() {
		return customerDetail;
	}

	public void setCustomerDetail(CustomerDetail customerDetail) {
		this.customerDetail = customerDetail;
	}

	@JsonProperty("custdetail")
	private CustomerDetail customerDetail;

	@Override
	public String toString() {
		String toString = "{";

		/*
		 * if (transactionRefId != null) { toString += ",transactionRefId:\"" +
		 * transactionRefId + "\""; }
		 */
		if (transType != null) {
			toString += ",transtype:\"" + transType + "\"";
		}
		if (paymentType != null) {
			toString += ",paymenttype:\"" + paymentType + "\"";
		}

		if (currencyCode != null) {
			toString += ",currencycode:\"" + currencyCode + "\"";
		}
		if (itemDesc != null) {
			toString += ",items:[";
			for (ItemDesc item : itemDesc) {
				toString += item.toString() + "\"";
				toString += ",";
			}
			toString.substring(0, toString.lastIndexOf(',') - 1);
			toString += "]";
		}
		if (customerDetail != null) {
			toString += ",custdetail:\"" + customerDetail.toString() + "\"";
		}

		/*
		 * if (itemTotal != null) { toString += ",itemtotal:\"" + itemTotal + "\""; }
		 */
		/*
		 * if (totalAmount != null) { toString += ",totalamount:\"" + totalAmount +
		 * "\""; }
		 */

		return toString;
	}

	/*
	 * public Long getTransactionRefId() { return transactionRefId; }
	 * 
	 * public void setTransactionRefId(Long transactionRefId) {
	 * this.transactionRefId = transactionRefId; }
	 */

	/*
	 * public Integer getStatus() { return status; }
	 * 
	 * public void setStatus(Integer status) { this.status = status; }
	 */

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	/*
	 * public Long getItemTotal() { return itemTotal; }
	 * 
	 * public void setItemTotal(Long itemTotal) { this.itemTotal = itemTotal; }
	 */

	/*
	 * public Long getTotalAmount() { return totalAmount; }
	 * 
	 * public void setTotalAmount(Long totalAmount) { this.totalAmount =
	 * totalAmount; }
	 */

}
