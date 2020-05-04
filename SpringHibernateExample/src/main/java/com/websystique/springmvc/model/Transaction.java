package com.websystique.springmvc.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "APG_Transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TransactionID")
	private Long transactionID;

	/*
	 * @Column(name = "partnerkey") private String partnerKey;
	 * 
	 * @Column(name = "partnerrefno") private String partnerRefno;
	 */

	@Column(name = "transtype")
	private String transType;

	@Column(name = "paymenttype")
	private String paymentType;

	/*
	 * @Column(name = "paymentsource") private String paymentSource;
	 * 
	 * @Column(name = "title") private String title;
	 */

	@Column(name = "currencycode")
	private String currencyCode;

	/*
	 * @Column(name = "currencyexp") private Integer currencyExp;
	 * 
	 * @Column(name = "shippingamount") private Long shippingAmount;
	 * 
	 * @Column(name = "taxamount") private Long taxAmount;
	 */
	@Column(name = "itemtotalamount")
	private Long itemTotal;

	public Long getItemTotal() {
		return itemTotal;
	}

	public void setItemTotal(Long itemTotal) {
		this.itemTotal = itemTotal;
	}

	@Column(name = "totalamount")
	private Long totalAmount;

	@Column(name = "status")
	private Integer status;

	@Column(name = "transactionrefid")
	private Long transactionRefId;

	@Column(name = "Balance")
	private Long balance;

	@Column(name = "Transaction_Time")
	private Timestamp createDateUTC;
	/*
	 * @OneToOne(mappedBy = "transactionID", fetch = FetchType.EAGER, cascade = {
	 * CascadeType.ALL })
	 * 
	 * @JoinColumn(name = "TransactionID") private TransactionUser user;
	 * 
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "transactionid", cascade = {
	 * CascadeType.ALL }) private List<TransactionItem> itemList;
	 */
	/*
	 * @Column(name = "responseURL") private String responseURL;
	 * 
	 * @Column(name = "createdateUTC") private Timestamp createDateUTC;
	 * 
	 * @Column(name = "lastmodifiedUTC") private Timestamp lastModifiedUTC;
	 * 
	 * @Column(name = "channelkey") private String channelKey;
	 * 
	 * @Column(name = "isepp") private Boolean isepp;
	 * 
	 * @Column(name = "AgentId") private String agentId;
	 * 
	 * @Column(name = "isValidation") private Boolean isValidation;
	 * 
	 * @Column(name = "isDirectLink") private Boolean isDirectLink;
	 * 
	 * @Column(name = "isQuickPayCSC") private Boolean isQuickPayCSC;
	 * 
	 * @OneToOne(mappedBy = "transactionID", fetch = FetchType.EAGER, cascade = {
	 * CascadeType.ALL })
	 * 
	 * @JoinColumn(name = "TransactionID") private TransactionUser user;
	 * 
	 * @OneToOne(mappedBy = "transactionID", fetch = FetchType.EAGER, cascade = {
	 * CascadeType.ALL })
	 * 
	 * @JoinColumn(name = "TransactionID") private TransactionDiscountInfo
	 * transactionDiscountInfo;
	 * 
	 * @OneToOne(mappedBy = "transactionID", cascade = { CascadeType.ALL }, fetch =
	 * FetchType.LAZY) private APGTransactionEppInfo apgTransactionEppInfo;
	 */

	// @OneToMany(fetch = FetchType.EAGER, mappedBy = "transaction", cascade = {
	// CascadeType.ALL })
	// private Long paymentID;

	/*
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "transaction", cascade = {
	 * CascadeType.ALL }) private List<TransactionItem> itemList;
	 */
	/*
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "transaction", cascade = {
	 * CascadeType.ALL }) private List<APGAdditionalCharges> additionalChargesList;
	 * 
	 * @OneToOne(mappedBy = "transaction", cascade = { CascadeType.ALL }, fetch =
	 * FetchType.LAZY) private APGRecurring recurringID;
	 */

	/*
	 * public List<TransactionItem> getItemList() { return itemList; }
	 * 
	 * public void setItemList(List<TransactionItem> itemList) { this.itemList =
	 * itemList; }
	 * 
	 * public TransactionUser getUser() { return user; }
	 * 
	 * public void setUser(TransactionUser user) { this.user = user; }
	 */

	public Timestamp getCreateDateUTC() {
		return createDateUTC;
	}

	public void setCreateDateUTC(Timestamp string) {
		this.createDateUTC = string;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public Long getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(Long transactionID) {
		this.transactionID = transactionID;
	}

	/*
	 * public String getPartnerKey() { return partnerKey; }
	 * 
	 * public void setPartnerKey(String partnerKey) { this.partnerKey = partnerKey;
	 * }
	 * 
	 * public String getPartnerRefno() { return partnerRefno; }
	 * 
	 * public void setPartnerRefno(String partnerRefno) { this.partnerRefno =
	 * partnerRefno; }
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

	/*
	 * public String getPaymentSource() { return paymentSource; }
	 * 
	 * public void setPaymentSource(String paymentSource) { this.paymentSource =
	 * paymentSource; }
	 * 
	 * public String getTitle() { return title; }
	 * 
	 * public void setTitle(String title) { this.title = title; }
	 */

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	/*
	 * public Integer getCurrencyExp() { return currencyExp; }
	 * 
	 * public void setCurrencyExp(Integer currencyExp) { this.currencyExp =
	 * currencyExp; }
	 * 
	 * public Long getShippingAmount() { return shippingAmount; }
	 * 
	 * public void setShippingAmount(Long shippingAmount) { this.shippingAmount =
	 * shippingAmount; }
	 * 
	 * public Long getTaxAmount() { return taxAmount; }
	 * 
	 * public void setTaxAmount(Long taxAmount) { this.taxAmount = taxAmount; }
	 */
	/*
	 * public Long getItemTotalAmount() { return itemTotalAmount; }
	 * 
	 * public void setItemTotalAmount(Long itemTotalAmount) { this.itemTotalAmount =
	 * itemTotalAmount; }
	 */

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getTransactionRefId() {
		return transactionRefId;
	}

	public void setTransactionRefId(Long transrefId) {
		this.transactionRefId = transrefId;
	}

	/*
	 * public String getResponseURL() { return responseURL; }
	 * 
	 * public void setResponseURL(String responseURL) { this.responseURL =
	 * responseURL; }
	 * 
	 * public Timestamp getCreateDateUTC() { return createDateUTC; }
	 * 
	 * public void setCreateDateUTC(Timestamp createDateUTC) { this.createDateUTC =
	 * createDateUTC; }
	 * 
	 * public Timestamp getLastModifiedUTC() { return lastModifiedUTC; }
	 * 
	 * public void setLastModifiedUTC(Timestamp lastModifiedUTC) {
	 * this.lastModifiedUTC = lastModifiedUTC; }
	 * 
	 * public String getChannelKey() { return channelKey; }
	 * 
	 * public void setChannelKey(String channelKey) { this.channelKey = channelKey;
	 * }
	 * 
	 * public Boolean getIsepp() { return isepp; }
	 */

	/*
	 * public void setIsepp(Boolean isepp) { this.isepp = isepp; }
	 * 
	 * public String getAgentId() { return agentId; }
	 * 
	 * public void setAgentId(String agentId) { this.agentId = agentId; }
	 * 
	 * public TransactionUser getUser() { return user; }
	 * 
	 * public void setUser(TransactionUser user) { this.user = user; }
	 * 
	 * public List<TransactionItem> getItemList() { return itemList; }
	 * 
	 * public void setItemList(List<TransactionItem> itemList) { this.itemList =
	 * itemList; }
	 */
	/*
	 * public List<APGAdditionalCharges> getAdditionalChargesList() { return
	 * additionalChargesList; }
	 * 
	 * public void setAdditionalChargesList(List<APGAdditionalCharges>
	 * additionalChargesList) { this.additionalChargesList = additionalChargesList;
	 * }
	 */

	/*
	 * public APGTransactionEppInfo getApgTransactionEppInfo() { return
	 * apgTransactionEppInfo; }
	 * 
	 * public void setApgTransactionEppInfo(APGTransactionEppInfo
	 * apgTransactionEppInfo) { this.apgTransactionEppInfo = apgTransactionEppInfo;
	 * }
	 */

	/*
	 * public APGRecurring getRecurringID() { return recurringID; }
	 * 
	 * public void setRecurringID(APGRecurring recurringID) { this.recurringID =
	 * recurringID; }
	 */

	/**
	 * @return the isValidation
	 */
	/*
	 * public Boolean getIsValidation() { return isValidation; }
	 * 
	 *//**
		 * @param isValidation the isValidation to set
		 *//*
			 * public void setIsValidation(Boolean isValidation) { this.isValidation =
			 * isValidation; }
			 * 
			 * public TransactionDiscountInfo getTransactionDiscountInfo() { return
			 * transactionDiscountInfo; }
			 * 
			 * public void setTransactionDiscountInfo(TransactionDiscountInfo
			 * transactionDiscountInfo) { this.transactionDiscountInfo =
			 * transactionDiscountInfo; }
			 * 
			 * public Boolean getIsDirectLink() { return isDirectLink; }
			 * 
			 * public void setIsDirectLink(Boolean isDirectLink) { this.isDirectLink =
			 * isDirectLink; }
			 */

	@Override
	public String toString() {
		return "Transaction [transactionID=" + transactionID + ", transType=" + transType + ", paymentType="
				+ paymentType + ", currencyCode=" + currencyCode + ", itemTotalAmount="
				+ (itemTotal != null ? itemTotal : "null") + ", totalAmount="
				+ (totalAmount != null ? totalAmount : "null") + ", status=" + status + ", transactionRefId="
				+ transactionRefId + "]";
	}

	/*
	 * public Long getPaymentID() { return paymentID; }
	 * 
	 * public void setPaymentID(Long paymentID) { this.paymentID = paymentID; }
	 */

	/*
	 * public Boolean getIsQuickPayCSC() { return isQuickPayCSC; }
	 * 
	 * public void setIsQuickPayCSC(Boolean isQuickPayCSC) { this.isQuickPayCSC =
	 * isQuickPayCSC; }
	 */

}
