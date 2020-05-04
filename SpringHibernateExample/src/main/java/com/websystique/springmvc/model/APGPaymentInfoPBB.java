package com.websystique.springmvc.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "APG_PaymentInfo_PBB")
public class APGPaymentInfoPBB {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PaymentInfoPBBID")
	private Long paymentInfoPBBID;

	@Column(name = "PBBRefID")
	private String pbbRefID;

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
	 * @return the paymentInfoPBBID
	 */
	public Long getPaymentInfoPBBID() {
		return paymentInfoPBBID;
	}

	/**
	 * @param paymentInfoPBBID the paymentInfoPBBID to set
	 */
	public void setPaymentInfoPBBID(Long paymentInfoPBBID) {
		this.paymentInfoPBBID = paymentInfoPBBID;
	}

	/**
	 * @return the pbbRefID
	 */
	public String getPbbRefID() {
		return pbbRefID;
	}

	/**
	 * @param pbbRefID the pbbRefID to set
	 */
	public void setPbbRefID(String pbbRefID) {
		this.pbbRefID = pbbRefID;
	}

	/**
	 * @return the ccName
	 */
	public String getCcName() {
		return ccName;
	}

	/**
	 * @param ccName the ccName to set
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
	 * @param ccDataKey the ccDataKey to set
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
	 * @param ccDisplay the ccDisplay to set
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
	 * @param expiryMonth the expiryMonth to set
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
	 * @param expiryYear the expiryYear to set
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
	 * @param submitInfo the submitInfo to set
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
	 * @param ccType the ccType to set
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
	 * @param paymentID the paymentID to set
	 */
	public void setPaymentID(Long paymentID) {
		this.paymentID = paymentID;
	}

	/**
	 * @return the createdDate
	 */
	public Timestamp getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

}
