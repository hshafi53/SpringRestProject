package com.request.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemDesc {

	/*
	 * @JsonProperty(index = 1, value = "partneritemref") private String
	 * partnerItemRef;
	 */
	@JsonProperty("name")
	private String name;
	@JsonProperty("qty")
	private Integer qty;
	@JsonProperty("unitprice")
	private Long unitPrice;

	/*
	 * @JsonIgnore public String getPartneritemref() { return partnerItemRef; }
	 * 
	 * public void setPartneritemref(String partneritemref) { this.partnerItemRef =
	 * partneritemref; }
	 */

	@JsonIgnore
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	@JsonIgnore
	public Long getUnitprice() {
		return unitPrice;
	}

	public void setUnitprice(long unitprice) {
		this.unitPrice = unitprice;
	}

	@Override
	public String toString() {
		String toString = "{";
		/*
		 * if (partnerItemRef != null) { toString += "partneritemref:\"" +
		 * partnerItemRef + "\""; }
		 */
		if (name != null) {
			toString += ",name:\"" + name + "\"";
		}
		if (qty != null) {
			toString += ",qty:\"" + qty + "\"";
		}
		if (unitPrice != null) {
			toString += ",unitprice:\"" + unitPrice + "\"";
		}
		return toString;

	}

	

}
