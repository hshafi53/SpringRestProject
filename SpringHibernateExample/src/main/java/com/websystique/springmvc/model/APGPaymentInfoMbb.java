package com.websystique.springmvc.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author alokb
 *
 */
@Entity
@Table(name = "APG_paymentInfo_MBB")
public class APGPaymentInfoMbb {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PaymentInfoMBBID")
	private Long paymentInfoMBBID;

	@Column(name = "MBBRefID")
	private String mbbRefId;

	@Column(name = "CCName")
	private String ccName;

	/*
	 * @Column(name = "CCDataKey") private String ccDataKey;
	 */
	@Column(name = "CCDisplay")
	private String ccDisplay;

	/*
	 * @Column(name = "ExpiryMonth") private String expiryMonth;
	 * 
	 * @Column(name = "ExpiryYear") private String expiryYear;
	 * 
	 * @Column(name = "SubmitInfo") private String submitInfo;
	 */
	@Column(name = "CCType")
	private String ccType;

	@Column(name = "PaymentID")
	private Long paymentID;
	
	@Column(name = "PIN")
	private Long pin;

	@Column(name = "CUSTOMER_ID")
	private Long customerId;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getPin() {
		return pin;
	}

	public void setPin(Long pin) {
		this.pin = pin;
	}

	/*
	 * @Column(name = "createdDate") private Timestamp createdDate;
	 */

	/**
	 * @return the paymentInfoMBBID
	 */
	public Long getPaymentInfoMBBID() {
		return paymentInfoMBBID;
	}

	/**
	 * @param paymentInfoMBBID
	 *            the paymentInfoMBBID to set
	 */
	public void setPaymentInfoMBBID(Long paymentInfoMBBID) {
		this.paymentInfoMBBID = paymentInfoMBBID;
	}

	/**
	 * @return the paymentID
	 */
	public Long getPaymentID() {
		return paymentID;
	}

	/**
	 * @param paymentID
	 *            the paymentID to set
	 */
	public void setPaymentID(Long paymentID) {
		this.paymentID = paymentID;
	}

	public String getMbbRefId() {
		return mbbRefId;
	}

	public void setMbbRefId(String mbbRefId) {
		this.mbbRefId = mbbRefId;
	}

	public String getCcName() {
		return ccName;
	}

	public void setCcName(String ccName) {
		this.ccName = ccName;
	}

	/*
	 * public String getCcDataKey() { return ccDataKey; }
	 * 
	 * public void setCcDataKey(String ccDataKey) { this.ccDataKey = ccDataKey; }
	 */

	public String getCcDisplay() {
		return ccDisplay;
	}

	public void setCcDisplay(String ccDisplay) {
		this.ccDisplay = ccDisplay;
	}

	/*
	 * public String getExpiryMonth() { return expiryMonth; }
	 * 
	 * public void setExpiryMonth(String expiryMonth) { this.expiryMonth =
	 * expiryMonth; }
	 * 
	 * public String getExpiryYear() { return expiryYear; }
	 * 
	 * public void setExpiryYear(String expiryYear) { this.expiryYear = expiryYear;
	 * }
	 * 
	 * public String getSubmitInfo() { return submitInfo; }
	 * 
	 * public void setSubmitInfo(String submitInfo) { this.submitInfo = submitInfo;
	 * }
	 */

	public String getCcType() {
		return ccType;
	}

	public void setCcType(String ccType) {
		this.ccType = ccType;
	}

	/*
	 * public Timestamp getCreatedDate() { return createdDate; }
	 * 
	 * public void setCreatedDate(Timestamp createdDate) { this.createdDate =
	 * createdDate; }
	 */

	
}
