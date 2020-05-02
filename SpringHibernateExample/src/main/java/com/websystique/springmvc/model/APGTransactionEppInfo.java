package com.websystique.springmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @author alokb Entity class for table APG_Transaction_EPPInfo
 */
@Entity
@Table(name = "APG_Transaction_EPPInfo")
public class APGTransactionEppInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EppInfoID", unique = true, nullable = false)
	private Long eppInfoId;

	@Column(name = "PlanCode")
	private String planCode;

	@Column(name = "MonthTerms")
	private Integer monthTerms;

	@OneToOne(fetch = FetchType.LAZY)/*( cascade = { CascadeType.ALL })*/
	@JoinColumn(name = "TransactionID")
	private Transaction transactionID;
	
	@Column(name = "ChannelProviderId")
	private Long channelProviderId;

	public Long getEppInfoId() {
		return eppInfoId;
	}

	public void setEppInfoId(Long eppInfoId) {
		this.eppInfoId = eppInfoId;
	}

	public String getPlanCode() {
		return planCode;
	}

	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}

	public Integer getMonthTerms() {
		return monthTerms;
	}

	public void setMonthTerms(Integer monthTerms) {
		this.monthTerms = monthTerms;
	}

	public Transaction getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(Transaction transactionID) {
		this.transactionID = transactionID;
	}

	public Long getChannelProviderId() {
		return channelProviderId;
	}

	public void setChannelProviderId(Long channelProviderId) {
		this.channelProviderId = channelProviderId;
	}

	@Override
	public String toString() {
		return "APGTransactionEppInfo [eppInfoId=" + eppInfoId + ", planCode=" + planCode + ", monthTerms=" + monthTerms
				+ ", channelProviderId=" + channelProviderId + "]";
	}

}
