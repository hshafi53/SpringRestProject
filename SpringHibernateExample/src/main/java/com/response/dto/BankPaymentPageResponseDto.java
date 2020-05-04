package com.response.dto;

public class BankPaymentPageResponseDto {

	private String paymentStatus;
	private String amount;
	private String bankRefNo;
	private String paymentId;
	private String paymentDate;
	private String responseURL;
	private String responseCode;
	private String responseDesc;
	private MerchantBankResponseDto merchantBankResponseDto;
	private String ccDataKey;
	private String transactionRefId;
	private Boolean isValidation;
	private String partnerName;
	private String partnerUserKey;
	private Integer paymentStatusCode;

	/**
	 * @return the isValidation
	 */
	public Boolean getIsValidation() {
		return isValidation;
	}

	/**
	 * @param isValidation the isValidation to set
	 */
	public void setIsValidation(Boolean isValidation) {
		this.isValidation = isValidation;
	}

	/**
	 * @return the responseCode
	 */
	public String getResponseCode() {
		return responseCode;
	}

	/**
	 * @param responseCode the responseCode to set
	 */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * @return the responseDesc
	 */
	public String getResponseDesc() {
		return responseDesc;
	}

	/**
	 * @param responseDesc the responseDesc to set
	 */
	public void setResponseDesc(String responseDesc) {
		this.responseDesc = responseDesc;
	}

	/**
	 * @return the paymentStatus
	 */
	public String getPaymentStatus() {
		return paymentStatus;
	}

	/**
	 * @param paymentStatus the paymentStatus to set
	 */
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * @return the bankRefNo
	 */
	public String getBankRefNo() {
		return bankRefNo;
	}

	/**
	 * @param bankRefNo the bankRefNo to set
	 */
	public void setBankRefNo(String bankRefNo) {
		this.bankRefNo = bankRefNo;
	}

	/**
	 * @return the paymentId
	 */
	public String getPaymentId() {
		return paymentId;
	}

	/**
	 * @param paymentId the paymentId to set
	 */
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	/**
	 * @return the paymentDate
	 */

	/**
	 * @return the responseURL
	 */
	public String getResponseURL() {
		return responseURL;
	}

	/**
	 * @return the paymentDate
	 */

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

	/**
	 * @param responseURL the responseURL to set
	 */
	public void setResponseURL(String responseURL) {
		this.responseURL = responseURL;
	}

	public MerchantBankResponseDto getMerchantBankResponseDto() {
		return merchantBankResponseDto;
	}

	public void setMerchantBankResponseDto(MerchantBankResponseDto merchantBankResponseDto) {
		this.merchantBankResponseDto = merchantBankResponseDto;
	}

	public String getCcDataKey() {
		return ccDataKey;
	}

	public void setCcDataKey(String ccDataKey) {
		this.ccDataKey = ccDataKey;
	}

	public String getTransactionRefId() {
		return transactionRefId;
	}

	public void setTransactionRefId(String transactionRefId) {
		this.transactionRefId = transactionRefId;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public String getPartnerUserKey() {
		return partnerUserKey;
	}

	public void setPartnerUserKey(String partnerUserKey) {
		this.partnerUserKey = partnerUserKey;
	}

	public Integer getPaymentStatusCode() {
		return paymentStatusCode;
	}

	public void setPaymentStatusCode(Integer paymentStatusCode) {
		this.paymentStatusCode = paymentStatusCode;
	}

}
