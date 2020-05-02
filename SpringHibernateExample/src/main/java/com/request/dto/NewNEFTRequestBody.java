package com.request.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewNEFTRequestBody {

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

	public void setSender_IFSC(String sender_IFSC) {
		this.sender_IFSC = sender_IFSC;
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

	public void setBeneficiary_IFSC(String beneficiary_IFSC) {
		this.beneficiary_IFSC = beneficiary_IFSC;
	}

	public int getAMOUNT() {
		return AMOUNT;
	}

	public void setAMOUNT(int aMOUNT) {
		AMOUNT = aMOUNT;
	}

	@JsonProperty("sender_name")
	private String sender_Name;
	@JsonProperty("sender_accountno")
	private Long sender_Accountno;
	@JsonProperty("sender_accounttype")
	private String sender_Accounttype;
	@JsonProperty("sender_ifsc")
	private String sender_IFSC;
	@JsonProperty("beneficiary_name")
	private String beneficiary_Name;
	@JsonProperty("beneficiary_accountno")
	private Long beneficiary_Accountno;
	@JsonProperty("beneficiary_accounttype")
	private String beneficiary_Accounttype;

	@JsonProperty("beneficiary_ifsc")
	private String beneficiary_IFSC;

	@JsonProperty("amount")
	private int AMOUNT;

	@Override
	public String toString() {
		String toString = "{";
		if (sender_Name != null) {
			toString += ",sender_name:\"" + sender_Name + "\"";
		}
		if (sender_Accountno != null) {
			toString += ",sender_accountno:\"" + sender_Accountno + "\"";
		}

		if (sender_Accounttype != null) {
			toString += ",sender_accounttype:\"" + sender_Accounttype + "\"";
		}
		if (sender_IFSC != null) {
			toString += ",sender_ifsc:\"" + sender_IFSC + "\"";
		}
		if (beneficiary_Name != null) {
			toString += ",beneficiary_name:\"" + beneficiary_Name + "\"";
		}
		if (beneficiary_Accountno != null) {
			toString += ",beneficiary_accountno:\"" + beneficiary_Accountno + "\"";
		}
		if (beneficiary_Accounttype != null) {
			toString += ",beneficiary_accounttype:\"" + beneficiary_Accounttype + "\"";
		}
		if (beneficiary_IFSC != null) {
			toString += ",beneficiary_ifsc:\"" + beneficiary_IFSC + "\"";
		}
		if (AMOUNT != 0) {
			toString += ",amount:\"" + AMOUNT + "\"";
		}

		return toString;
	}

}
