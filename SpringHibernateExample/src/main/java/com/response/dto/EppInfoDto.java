package com.response.dto;

public class EppInfoDto {

	private String installmentPlanCode;

	private String monthTerm;

	private Long channelProviderId;

	public String getInstallmentPlanCode() {
		return installmentPlanCode;
	}

	public void setInstallmentPlanCode(String installmentPlanCode) {
		this.installmentPlanCode = installmentPlanCode;
	}

	public String getMonthTerm() {
		return monthTerm;
	}

	public void setMonthTerm(String monthTerm) {
		this.monthTerm = monthTerm;
	}

	public Long getChannelProviderId() {
		return channelProviderId;
	}

	public void setChannelProviderId(Long channelProviderId) {
		this.channelProviderId = channelProviderId;
	}

}
