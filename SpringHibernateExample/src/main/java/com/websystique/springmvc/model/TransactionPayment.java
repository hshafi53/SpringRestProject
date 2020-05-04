package com.websystique.springmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "APG_TransactionPayment")
public class TransactionPayment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PaymentID")
	private Long paymentID;

	@Column(name = "PaymentRefID")
	private Long paymentRefID;

	@Column(name = "PaymentType")
	private String paymentType;

	/*
	 * @Column(name = "PaymentProviderKey") private String paymentProviderKey;
	 */

	@Column(name = "Amount")
	private String amount;

	@Column(name = "Status")
	private Integer status;

	/*
	 * @Column(name = "errorcode") private String errorCode;
	 * 
	 * @Column(name = "errordesc") private String errorDesc;
	 * 
	 * @Column(name = "createddateutc") private Timestamp createdDateUTC;
	 * 
	 * @Column(name = "LastModifiedUTC") private Timestamp lastModifiedUTC;
	 * 
	 * @Column(name = "RetryCount") private Integer retryCount;
	 */

	@Column(name = "TransType")
	private String transType;

	/*
	 * @Column(name = "BankApprovalCode") private String bankApprovalCode;
	 * 
	 * @Column(name = "ApprovalDate") private Timestamp approvalDate;
	 * 
	 * @Column(name = "CCValidation") private Boolean ccValidation;
	 * 
	 * @Column(name = "CardReplacement") private Boolean cardReplacement;
	 */
	/*
	 * @Column(name = "MID") private String MID;
	 */

	@Column(name = "Bank")
	private String bank;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "transactionid")
	 */
	private Long transactionid;

	/*
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "paymentID", cascade = {
	 * CascadeType.ALL }) private List<TransactionPaymentRefund>
	 * transactionPaymentRefund;
	 */
	/*
	 * @Column(name = "FavCardDataKey") private String favCardDataKey;
	 * 
	 * @Column(name = "ipAddress") private String ipAddress;
	 * 
	 * @Column(name = "userAgent") private String userAgent;
	 */

	public Long getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(Long transactionid) {
		this.transactionid = transactionid;
	}

	/**
	 * @return the paymentID
	 */
	public Long getPaymentID() {
		return paymentID;
	}

	/**
	 * @param paymentID the paymentID to set
	 */
	public void setPaymentID(Long paymentID) {
		this.paymentID = paymentID;
	}

	/**
	 * @return the paymentRefID
	 */
	public Long getPaymentRefID() {
		return paymentRefID;
	}

	/**
	 * @param paymentRefId2 the paymentRefID to set
	 */
	public void setPaymentRefID(Long paymentRefId2) {
		this.paymentRefID = paymentRefId2;
	}

	/**
	 * @return the paymentType
	 */
	public String getPaymentType() {
		return paymentType;
	}

	/**
	 * @param paymentType the paymentType to set
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	/**
	 * @return the paymentProviderKey
	 */
	/*
	 * public String getPaymentProviderKey() { return paymentProviderKey; }
	 * 
	 *//**
		 * @param paymentProviderKey the paymentProviderKey to set
		 *//*
			 * public void setPaymentProviderKey(String paymentProviderKey) {
			 * this.paymentProviderKey = paymentProviderKey; }
			 */

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
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the errorCode
	 */
	/*
	 * public String getErrorCode() { return errorCode; }
	 * 
	 *//**
		 * @param errorCode the errorCode to set
		 */
	/*
	 * public void setErrorCode(String errorCode) { this.errorCode = errorCode; }
	 * 
	 *//**
		 * @return the errorDesc
		 */
	/*
	 * public String getErrorDesc() { return errorDesc; }
	 * 
	 *//**
		 * @param errorDesc the errorDesc to set
		 */
	/*
	 * public void setErrorDesc(String errorDesc) { this.errorDesc = errorDesc; }
	 * 
	 *//**
		 * @return the createdDateUTC
		 */
	/*
	 * public Timestamp getCreatedDateUTC() { return createdDateUTC; }
	 * 
	 *//**
		 * @param createdDateUTC the createdDateUTC to set
		 */
	/*
	 * public void setCreatedDateUTC(Timestamp createdDateUTC) { this.createdDateUTC
	 * = createdDateUTC; }
	 * 
	 *//**
		 * @return the lastModifiedUTC
		 */
	/*
	 * public Timestamp getLastModifiedUTC() { return lastModifiedUTC; }
	 * 
	 *//**
		 * @param lastModifiedUTC the lastModifiedUTC to set
		 */
	/*
	 * public void setLastModifiedUTC(Timestamp lastModifiedUTC) {
	 * this.lastModifiedUTC = lastModifiedUTC; }
	 * 
	 *//**
		 * @return the retryCount
		 */
	/*
	 * public Integer getRetryCount() { return retryCount; }
	 * 
	 *//**
		 * @param retryCount the retryCount to set
		 *//*
			 * public void setRetryCount(Integer retryCount) { this.retryCount = retryCount;
			 * }
			 */

	/**
	 * @return the transType
	 */
	public String getTransType() {
		return transType;
	}

	/**
	 * @param transType the transType to set
	 */
	public void setTransType(String transType) {
		this.transType = transType;
	}

	/**
	 * @return the bankApprovalCode
	 */
	/*
	 * public String getBankApprovalCode() { return bankApprovalCode; }
	 * 
	 *//**
		 * @param bankApprovalCode the bankApprovalCode to set
		 */
	/*
	 * public void setBankApprovalCode(String bankApprovalCode) {
	 * this.bankApprovalCode = bankApprovalCode; }
	 * 
	 *//**
		 * @return the approvalCode
		 */
	/*
	
	*//**
		 * @return the ccValidation
		 */
	/*
	 * public Boolean getCcValidation() { return ccValidation; }
	 * 
	 *//**
		 * @return the approvalDate
		 */
	/*
	 * public Timestamp getApprovalDate() { return approvalDate; }
	 * 
	 *//**
		 * @param approvalDate the approvalDate to set
		 */
	/*
	 * public void setApprovalDate(Timestamp approvalDate) { this.approvalDate =
	 * approvalDate; }
	 * 
	 *//**
		 * @param ccValidation the ccValidation to set
		 */
	/*
	 * public void setCcValidation(Boolean ccValidation) { this.ccValidation =
	 * ccValidation; }
	 * 
	 *//**
		 * @return the cardReplacement
		 */
	/*
	 * public Boolean getCardReplacement() { return cardReplacement; }
	 * 
	 *//**
		 * @param cardReplacement the cardReplacement to set
		 *//*
			 * public void setCardReplacement(Boolean cardReplacement) {
			 * this.cardReplacement = cardReplacement; }
			 */

	/**
	 * @return the mID
	 */

	/**
	 * @return the bank
	 */
	public String getBank() {
		return bank;
	}

	/**
	 * @param bank the bank to set
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}

	/**
	 * @return the transaction
	 */
	/*
	 * public Transaction getTransaction() { return transaction; }
	 * 
	 *//**
		 * @param transaction the transaction to set
		 *//*
			 * public void setTransaction(Transaction transaction) { this.transaction =
			 * transaction; }
			 */

	/**
	 * @return the mID
	 */
	/*
	 * public String getMID() { return MID; }
	 * 
	 *//**
		 * @param mID the mID to set
		 *//*
			 * public void setMID(String mID) { MID = mID; }
			 */

	/*
	 * public String getFavCardDataKey() { return favCardDataKey; }
	 * 
	 * public void setFavCardDataKey(String favCardDataKey) { this.favCardDataKey =
	 * favCardDataKey; }
	 */
	/*
	 * public List<TransactionPaymentRefund> getTransactionPaymentRefund() { return
	 * transactionPaymentRefund; }
	 * 
	 * public void setTransactionPaymentRefund(List<TransactionPaymentRefund>
	 * transactionPaymentRefund) { this.transactionPaymentRefund =
	 * transactionPaymentRefund; }
	 */

	/*
	 * public String getIpAddress() { return ipAddress; }
	 * 
	 * public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
	 * 
	 * public String getUserAgent() { return userAgent; }
	 * 
	 * public void setUserAgent(String userAgent) { this.userAgent = userAgent; }
	 */

	@Override
	public String toString() {
		return "TransactionPayment [paymentID=" + paymentID + ", paymentRefID=" + paymentRefID + ", paymentType="
				+ paymentType + ", amount=" + amount + ", status=" + status + ",  transType=" + transType + ", bank="
				+ bank + "]";
	}

}
