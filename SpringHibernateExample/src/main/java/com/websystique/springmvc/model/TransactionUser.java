package com.websystique.springmvc.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customer_account")
public class TransactionUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CUSTOMER_ID")
	private Long transactionUserID;

	@Column(name = "CUSTOMER_NAME")
	private String customername;

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long finalbal) {
		this.amount = finalbal;
	}

	@Column(name = "AMOUNT")
	private Long amount;

	public Long getAccount_no() {
		return account_no;
	}

	public void setAccount_no(Long account_no) {
		this.account_no = account_no;
	}

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	@Column(name = "Account_Number")
	private Long account_no;
	@Column(name = "Account_Type")
	private String account_type;
	@Column(name = "IFSC")
	private String ifsc;

	/*
	 * @OneToOne(cascade = { CascadeType.ALL })
	 * 
	 * @JoinColumn(name = "TransactionID")
	 */

	private Long transactionID;

	public Long getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(Long transactionID) {
		this.transactionID = transactionID;
	}

	/**
	 * @return the transaction
	 */
	public Long getTransaction() {
		return transactionID;
	}

	/**
	 * @param transaction the transaction to set
	 */
	public void setTransaction(Long transactionID) {
		this.transactionID = transactionID;
	}

	/**
	 * @return the transactionUserID
	 */
	public Long getTransactionUserID() {
		return transactionUserID;
	}

	/**
	 * @param transactionUserID the transactionUserID to set
	 */
	public void setTransactionUserID(Long transactionUserID) {
		this.transactionUserID = transactionUserID;
	}

	/**
	 * @return the partnerUserKey
	 */

	@Override
	public String toString() {
		return "TransactionUser [transactionUserID=" + transactionUserID + ",]";
	}

}
