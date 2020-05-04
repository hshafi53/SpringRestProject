package com.response.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MerchantBankResponseDto {

	@JsonProperty("sig")
	private String signature;
	@JsonProperty("resultdescription")
	private String resultDescription;
	@JsonProperty("result")
	private String result;
	@JsonProperty("errorcode")
	private String errorCode;
	@JsonProperty("transactionid")
	private String transactionId;
	@JsonProperty("partnerrefno")
	private String partnerRefNo;
	@JsonProperty("bankappcode")
	private String bankAppCode;
	@JsonProperty("bankappdate")
	private String bankAppDate;
	@JsonProperty("timestamp")
	private String timestamp;
	@JsonProperty("providerkey")
	private String providerKey;
	@JsonProperty("bankrefid")
	private String bankRefId;
	@JsonProperty("bankname")
	private String bankName;
	@JsonProperty("providerrefno")
	private String providerRefNo;
	@JsonProperty("paymentdate")
	private String paymentDate;

	public String generateSignature() {
		String signatureString = null;
		if (this.bankAppCode != null) {
			signatureString += this.getBankAppCode();
		}
		if (this.bankAppDate != null) {
			signatureString += this.getBankAppDate();
		}
		if (this.bankName != null) {
			signatureString += this.getBankName();
		}
		if (this.bankRefId != null) {
			signatureString += this.getBankRefId();
		}
		if (this.errorCode != null) {
			signatureString += this.getErrorCode();
		}
		if (this.partnerRefNo != null) {
			signatureString += this.getPartnerRefNo();
		}
		if (this.paymentDate != null) {
			signatureString += this.getPaymentDate();
		}
		if (this.providerKey != null) {
			signatureString += this.getProviderKey();
		}
		if (this.result != null) {
			signatureString += this.getResult();
		}
		if (this.transactionId != null) {
			signatureString += this.getTransactionId();
		}

		return signatureString;

	}

	/**
	 * @return the signature
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * @param signature the signature to set
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}

	/**
	 * @return the resultDescription
	 */
	public String getResultDescription() {
		return resultDescription;
	}

	/**
	 * @param resultDescription the resultDescription to set
	 */
	public void setResultDescription(String resultDescription) {
		this.resultDescription = resultDescription;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * @return the partnerRefNo
	 */
	public String getPartnerRefNo() {
		return partnerRefNo;
	}

	/**
	 * @param partnerRefNo the partnerRefNo to set
	 */
	public void setPartnerRefNo(String partnerRefNo) {
		this.partnerRefNo = partnerRefNo;
	}

	/**
	 * @return the bankAppCode
	 */
	public String getBankAppCode() {
		return bankAppCode;
	}

	/**
	 * @param bankAppCode the bankAppCode to set
	 */
	public void setBankAppCode(String bankAppCode) {
		this.bankAppCode = bankAppCode;
	}

	/**
	 * @return the bankAppDate
	 */
	public String getBankAppDate() {
		return bankAppDate;
	}

	/**
	 * @param bankAppDate the bankAppDate to set
	 */
	public void setBankAppDate(String bankAppDate) {
		this.bankAppDate = bankAppDate;
	}

	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the providerKey
	 */
	public String getProviderKey() {
		return providerKey;
	}

	/**
	 * @param providerKey the providerKey to set
	 */
	public void setProviderKey(String providerKey) {
		this.providerKey = providerKey;
	}

	/**
	 * @return the bankRefId
	 */
	public String getBankRefId() {
		return bankRefId;
	}

	/**
	 * @param bankRefId the bankRefId to set
	 */
	public void setBankRefId(String bankRefId) {
		this.bankRefId = bankRefId;
	}

	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @param bankName the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * @return the providerRefNo
	 */
	public String getProviderRefNo() {
		return providerRefNo;
	}

	/**
	 * @param providerRefNo the providerRefNo to set
	 */
	public void setProviderRefNo(String providerRefNo) {
		this.providerRefNo = providerRefNo;
	}

	/**
	 * @return the paymentDate
	 */
	public String getPaymentDate() {
		return paymentDate;
	}

	/**
	 * @param paymentDate the paymentDate to set
	 */
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

}
