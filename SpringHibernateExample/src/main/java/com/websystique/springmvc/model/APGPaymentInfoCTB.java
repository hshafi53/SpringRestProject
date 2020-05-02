package com.websystique.springmvc.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "APG_PaymentInfo_CTB")
public class APGPaymentInfoCTB {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PaymentInfoCTBID")
	private Long paymentInfoCTBID;

	@Column(name = "CitibankRefID")
	private String citiBankRefID;

	@Column(name = "CCName")
	private String ccName;

	@Column(name = "CCDataKey")
	private String ccDataKey;

	@Column(name = "CCDisplay")
	private String ccDisplay;

	@Column(name = "ExpiryMonth")
	private String expiryMonth;

	@Column(name = "ExpiryYear")
	private String expiryYear;

	@Column(name = "SubmitInfo")
	private String submitInfo;

	@Column(name = "CCType")
	private String ccType;

	@Column(name = "PaymentID")
	private Long paymentID;

	@Column(name = "createdDate")
	private Timestamp createdDate;

	/**
	 * @return the paymentInfoCTBID
	 */
	public Long getPaymentInfoCTBID() {
		return paymentInfoCTBID;
	}

	/**
	 * @param paymentInfoCTBID
	 *            the paymentInfoCTBID to set
	 */
	public void setPaymentInfoCTBID(Long paymentInfoCTBID) {
		this.paymentInfoCTBID = paymentInfoCTBID;
	}

	/**
	 * @return the citiBankRefID
	 */
	public String getCitiBankRefID() {
		return citiBankRefID;
	}

	/**
	 * @param citiBankRefID
	 *            the citiBankRefID to set
	 */
	public void setCitiBankRefID(String citiBankRefID) {
		this.citiBankRefID = citiBankRefID;
	}

	/**
	 * @return the ccName
	 */
	public String getCcName() {
		return ccName;
	}

	/**
	 * @param ccName
	 *            the ccName to set
	 */
	public void setCcName(String ccName) {
		this.ccName = ccName;
	}

	/**
	 * @return the ccDataKey
	 */
	public String getCcDataKey() {
		return ccDataKey;
	}

	/**
	 * @param ccDataKey
	 *            the ccDataKey to set
	 */
	public void setCcDataKey(String ccDataKey) {
		this.ccDataKey = ccDataKey;
	}

	/**
	 * @return the ccDisplay
	 */
	public String getCcDisplay() {
		return ccDisplay;
	}

	/**
	 * @param ccDisplay
	 *            the ccDisplay to set
	 */
	public void setCcDisplay(String ccDisplay) {
		this.ccDisplay = ccDisplay;
	}

	/**
	 * @return the expiryMonth
	 */
	public String getExpiryMonth() {
		return expiryMonth;
	}

	/**
	 * @param expiryMonth
	 *            the expiryMonth to set
	 */
	public void setExpiryMonth(String expiryMonth) {
		this.expiryMonth = expiryMonth;
	}

	/**
	 * @return the expiryYear
	 */
	public String getExpiryYear() {
		return expiryYear;
	}

	/**
	 * @param expiryYear
	 *            the expiryYear to set
	 */
	public void setExpiryYear(String expiryYear) {
		this.expiryYear = expiryYear;
	}

	/**
	 * @return the submitInfo
	 */
	public String getSubmitInfo() {
		return submitInfo;
	}

	/**
	 * @param submitInfo
	 *            the submitInfo to set
	 */
	public void setSubmitInfo(String submitInfo) {
		this.submitInfo = submitInfo;
	}

	/**
	 * @return the ccType
	 */
	public String getCcType() {
		return ccType;
	}

	/**
	 * @param ccType
	 *            the ccType to set
	 */
	public void setCcType(String ccType) {
		this.ccType = ccType;
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

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

}
