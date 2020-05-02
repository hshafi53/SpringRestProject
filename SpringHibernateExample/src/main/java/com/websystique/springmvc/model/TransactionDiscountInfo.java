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
@Table(name = "APG_TransactionDiscountInfo")
public class TransactionDiscountInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DiscountInfoID")
	private Long discountInfoID;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "TransactionID")
	private Transaction transactionID;

	@Column(name = "BankName")
	private String bankName;

	@Column(name = "ccType")
	private String ccType;

	public Long getDiscountInfoID() {
		return discountInfoID;
	}

	public void setDiscountInfoID(Long discountInfoID) {
		this.discountInfoID = discountInfoID;
	}

	public Transaction getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(Transaction transactionID) {
		this.transactionID = transactionID;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCcType() {
		return ccType;
	}

	public void setCcType(String ccType) {
		this.ccType = ccType;
	}

	@Override
	public String toString() {
		return "TransactionDiscountInfo [discountInfoID=" + discountInfoID + ", bankName=" + bankName + ", ccType="
				+ ccType + "]";
	}

}
