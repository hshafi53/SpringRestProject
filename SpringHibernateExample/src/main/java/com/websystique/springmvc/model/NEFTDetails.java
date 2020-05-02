package com.websystique.springmvc.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NEFT_Details")
public class NEFTDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TransactionID")
	private Long transactionID;

	@Column(name = "Sender_Name")
	private String sender_Name;
	@Column(name = "Sender_Accountno")
	private Long sender_Accountno;
	public Long getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(Long transactionID) {
		this.transactionID = transactionID;
	}

	public String getSender_Name() {
		return sender_Name;
	}

	public void setSender_Name(String sender_Name) {
		this.sender_Name = sender_Name;
	}

	public Long getSender_Accountno() {
		return sender_Accountno;
	}

	public void setSender_Accountno(Long sender_Accountno) {
		this.sender_Accountno = sender_Accountno;
	}

	public String getSender_Accounttype() {
		return sender_Accounttype;
	}

	public void setSender_Accounttype(String sender_Accounttype) {
		this.sender_Accounttype = sender_Accounttype;
	}

	public String getSender_IFSC() {
		return sender_IFSC;
	}

	public void setSender_IFSC(String string) {
		this.sender_IFSC = string;
	}

	public String getBeneficiary_Name() {
		return beneficiary_Name;
	}

	public void setBeneficiary_Name(String beneficiary_Name) {
		this.beneficiary_Name = beneficiary_Name;
	}

	public Long getBeneficiary_Accountno() {
		return beneficiary_Accountno;
	}

	public void setBeneficiary_Accountno(Long beneficiary_Accountno) {
		this.beneficiary_Accountno = beneficiary_Accountno;
	}

	public String getBeneficiary_Accounttype() {
		return beneficiary_Accounttype;
	}

	public void setBeneficiary_Accounttype(String beneficiary_Accounttype) {
		this.beneficiary_Accounttype = beneficiary_Accounttype;
	}

	public String getBeneficiary_IFSC() {
		return beneficiary_IFSC;
	}

	public void setBeneficiary_IFSC(String string) {
		this.beneficiary_IFSC = string;
	}

	public int getAMOUNT() {
		return AMOUNT;
	}

	public void setAMOUNT(int aMOUNT) {
		AMOUNT = aMOUNT;
	}

	public Timestamp getTransferdate() {
		return transferdate;
	}

	public void setTransferdate(Timestamp transferdate) {
		this.transferdate = transferdate;
	}

	@Column(name = "Sender_Accounttype")
	private String sender_Accounttype;
	@Column(name = "Sender_IFSC")
	private String sender_IFSC;
	@Column(name = "Beneficiary_Name")
	private String beneficiary_Name;
	@Column(name = "Beneficiary_Accountno")
	private Long beneficiary_Accountno;
	@Column(name = "Beneficiary_Accounttype")
	private String beneficiary_Accounttype;

	@Column(name = "Beneficiary_IFSC")
	private String beneficiary_IFSC;

	@Column(name = "AMOUNT")
	private int AMOUNT;

	@Column(name = "Transfer_Date")
	private Timestamp transferdate;
}
